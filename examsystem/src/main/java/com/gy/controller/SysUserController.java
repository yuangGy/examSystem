package com.gy.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gy.model.MyToken;
import com.gy.model.SysUser;
import com.gy.service.SysUserService;

@Controller
@RequestMapping("/sysUserController")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	@RequestMapping("/code")
	public void  getCode(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException {
		 int width = 900;//验证码宽度
	     int height = 240;//验证码高度
	     int codeCount = 4;//验证码个数
	     int lineCount = 123;//混淆线个数
	    char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
	            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
	            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		 //定义随机数类
        Random r = new Random();
        //定义存储验证码的类
        StringBuilder builderCode = new StringBuilder();
        //定义画布
        BufferedImage buffImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //得到画笔
        Graphics g = buffImg.getGraphics();
        //1.设置颜色,画边框
        g.setColor(Color.black);
        g.drawRect(0,0,width,height);
        //2.设置颜色,填充内部
        g.setColor(Color.white);
        g.fillRect(1,1,width-2,height-2);
        //3.设置干扰线
        g.setColor(Color.blue);
 
        for (int i = 0; i < lineCount; i++) {
            g.drawLine(r.nextInt(width),r.nextInt(width),r.nextInt(width),r.nextInt(width));
        }
        //4.设置验证码
        g.setColor(Color.blue);
        //4.1设置验证码字体
        g.setFont(new Font("微软雅黑",Font.LAYOUT_LEFT_TO_RIGHT,170));
        for (int i = 0; i < codeCount; i++) {
            char c = codeSequence[r.nextInt(codeSequence.length)];
            builderCode.append(c);
            g.drawString(c+"",150*(i+1),175);
        }
        //5.输出到屏幕
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(buffImg,"png",sos);
        //6.保存到session中
//        HttpSession session = request.getSession();
        session.setAttribute("codeValidate",builderCode.toString());
        //7.禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        //8.关闭sos
        sos.close();
	}
	
	@RequestMapping("/syslogin")
	public String syslogin(SysUser sysUser,HttpServletRequest request,Model model,String code) throws ServletException, IOException {
		String realCode = (String) request.getSession().getAttribute("codeValidate");
		if(!realCode.equalsIgnoreCase(code)) {
			model.addAttribute("messge","验证码错误");
			model.addAttribute("sysUser",sysUser);
			return "sys/syslogin";
		}
		return sysUserService.login(sysUser,request.getRemoteAddr(),model);
	}
}
