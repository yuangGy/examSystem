package com.gy.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gy.model.Options;
import com.gy.model.Subject;
import com.gy.service.ExamMainService;
import jxl.Sheet;
import jxl.Workbook;

@Controller
@RequestMapping("/examMainController")
public class ExamMainController {
	@Autowired
	private ExamMainService examMainService;

	@RequestMapping("/exammain")
	public String exammain(Model model,HttpSession session) {
		int simpleScore = 0;
		int manyScore = 0;

		List<Subject> simpleSubjects = examMainService.findAllSimpleSucjects();
		for (Subject subject : simpleSubjects) {
			simpleScore += subject.getQuestionScore();
		}
		List<Subject> manySubjects = examMainService.findAllManySucjects();
		for (Subject subject : manySubjects) {
			manyScore += subject.getQuestionScore();
		}
		model.addAttribute("simpleScore", simpleScore);
		model.addAttribute("manyScore", manyScore);
		session.setAttribute("simpleSubjects", simpleSubjects);
		session.setAttribute("manySubjects", manySubjects);
		return "exam/exammain";
	}

	@RequestMapping("/simpleList")
	public String simpleList(Model model) {
		List<Subject> simpleSubjects = examMainService.sysFindAllSimpleSucjects();
		model.addAttribute("simpleSubjects", simpleSubjects);
		return "sys/syssimplequestion";
	}

	@RequestMapping("/manyList")
	public String manyList(Model model) {
		List<Subject> manySubjects = examMainService.sysFindAllManySucjects();
		model.addAttribute("manySubjects", manySubjects);
		return "sys/sysmanyquestion";
	}

	@RequestMapping("/addUI")
	public String addUI(String type, Model model) {
		model.addAttribute("type", type);
		return "sys/addUI";
	}

	@RequestMapping("/addsubject")
	@ResponseBody
	public String addsubject(String type, String questionScore, String questionName, String[] sysoption,
			String[] optionsDesc) {
		if (sysoption == null) {
			return "no";
		} else if ("单选题".equals(type.trim()) && sysoption.length > 1) {
			return "type0";
		}

		return examMainService.addNewQuestion(type, questionScore, questionName, sysoption, optionsDesc);
	}

	@RequestMapping("/deleteQuestion")
	public String deleteQuestion(String questionId) {
		examMainService.deleteQuestion(questionId);
		return "sys/sysmanyquestion";
	}

	@RequestMapping("/updateQuestionUI")
	public String updateQuestionUI(String questionId, String type, Model model) {
		Subject simpleSubject = examMainService.sysFindSimpleSucject(Integer.parseInt(questionId));
		List<Options> optionsList = new ArrayList<>();
		List<String> answerList = new ArrayList<>();
		for (String options : simpleSubject.getOptions()) {
			System.out.println(options);
			String[] strings = options.split("~~");
			Options option = new Options();
			option.setOptionsFlag(strings[0]);
			option.setOptionsDesc(strings[1]);
			optionsList.add(option);
		}
		for (String string : simpleSubject.getAnswersFlag()) {
			answerList.add(string);
		}
		if (Integer.parseInt(type) == 0) {

			model.addAttribute("type", 0);
		} else {
			model.addAttribute("type", 1);
		}
		model.addAttribute("answerList", answerList);
		model.addAttribute("optionsList", optionsList);
		model.addAttribute("simpleSubject", simpleSubject);
		return "sys/sysupdate";
	}

	@RequestMapping("/updatesubject")
	@ResponseBody
	public String updatesubject(String type, String questionId, String questionScore, String questionName,
			String[] sysoption, String[] optionsDesc) {
		if (sysoption == null) {
			return "no";
		} else if ("单选题".equals(type.trim()) && sysoption.length > 1) {
			return "type0";
		}

		if ("单选题".equals(type.trim())) {
			if ("yes".equals(examMainService
					.updateQuestion(type, questionId, questionScore, questionName, sysoption, optionsDesc).trim())) {
				return "simple";
			}
			return "fail";
		} else {
			if ("yes".equals(examMainService
					.updateQuestion(type, questionId, questionScore, questionName, sysoption, optionsDesc).trim())) {
				return "many";
			}
			return "fail";
		}
	}

	@RequestMapping("/randquestion")
	public String randquestion() {

		return "sys/sysrand";

	}

	@RequestMapping("/savarand")
	@ResponseBody
	public String savarand(String simpleScore, String manyScore) {
		boolean flag = examMainService.saveQuestions(simpleScore, manyScore);
		if (flag) {

			return "yes";
		}
		return "no";
	}

	@RequestMapping("/getScore")
	@ResponseBody
	public String getScore(String[] simples, String[] many,String[] simplesFlag,String[] manyFlag,HttpSession session) {
		List<Subject>simpleSubjects = (List<Subject>) session.getAttribute("simpleSubjects");
		List<Subject>manySubjects = (List<Subject>) session.getAttribute("manySubjects");
		int score = 0;
		for(int i=0;i<simples.length;i++) {
			if(simples[i].equals(simplesFlag[i])) {
				score += simpleSubjects.get(i).getQuestionScore();
			}
		}
		for(int i=0;i<many.length;i++) {
			if(many[i].equals(manyFlag[i])) {
				score += manySubjects.get(i).getQuestionScore();
			}
		}
		return score+"";
	}

	@Test
	public void getAllByExcel() {
		String quetionName = null;
		String questionScore = null;
		String type = null;
		List<String> options = new ArrayList<>();
		String answer = null;
		try {
			Workbook rwb = Workbook.getWorkbook(new File("C:\\Users\\Gy\\Desktop\\hqyj\\test2.xls"));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = rs.getRows();// 得到所有的行

			System.out.println(clos + " rows:" + rows);
			for (int i = 1; i < rows; i++) {
				// 第一个是列数，第二个是行数
				int j = 0;
				quetionName = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列 所以这里得j++
				questionScore = rs.getCell(j++, i).getContents();
				type = rs.getCell(j++, i).getContents();
				options.add(rs.getCell(j++, i).getContents());
				options.add(rs.getCell(j++, i).getContents());
				options.add(rs.getCell(j++, i).getContents());
				options.add(rs.getCell(j++, i).getContents());
				options.add(rs.getCell(j++, i).getContents());
				options.add(rs.getCell(j++, i).getContents());
				options.add(rs.getCell(j++, i).getContents());
				answer = rs.getCell(j++, i).getContents();
			}
			System.out.println(quetionName + questionScore + type + options + answer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
