<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="<%=basePath%>/">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/index.css" />
<script src="/examsystem/js/jquery-1.11.3.min.js"></script>
<title>在线考试系统</title>
</head>
<body>
	<a href="view/sys/syslogin.jsp">后台管理</a>
	<div class="bg">
		<div class="wel">在线考试系统</div>
		<div class="user">
			<div id="yonghu" style="">用户名</div>
			<input id="loginName" type="text" name="loginName" />
		</div>
		<div class="password">
			<div id="yonghu">密&nbsp;&nbsp;&nbsp;码</div>
			<input id="password" class="" type="password" name="password" />
		</div>
		<div class="rem">
			<input type="checkbox" name="" id="" value="" />
			<div id="reb">记住密码</div>
		</div>
		<div class="fg">
			<div style="font-size: 11px; margin-top: 11px;">
				<a style="font-size: 11px;" href="#">忘记密码？</a>
			</div>
		</div>

		<input id="login" class="btn" type="button" value="登录" />
	</div>
</body>
<script type="text/javascript">
	$("#login").click(function() {
		var loginName = $("#loginName").val()
		var password = $("#password").val()
		$.ajax({
			url : "examUserController/examlogin",
			data : {
				"loginName" : loginName,
				"password" : password
			},
			success:function(message){
				if(message=="yes"){
					window.location.href="examMainController/exammain"
				}else{
					alert("用户名或密码错误")
				}
			},
			error:function(){
				alert("服务器错误")
			}
		})
	})
</script>
</html>
