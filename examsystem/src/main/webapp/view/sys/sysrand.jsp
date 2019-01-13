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
<html lang="zh-cn">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body>

	<div class="panel admin-panel">
		<div class="panel-head" id="add">
			<strong><span class="icon-pencil-square-o"></span>根据分数出题</strong>
		</div>
		<div class="body-content">
			<div class="form-x">
				<div class="form-group">
					<div class="label">
						<label>单选题总分：</label>
					</div>
					<div class="field">
						<input type="number" placeholder="请输入单选题一共多少分" class="input w50"
							value="" id="simpleScore" data-validate="required:请输入题目分数" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>多选题总分：</label>
					</div>
					<div class="field">
						<input type="number" placeholder="请输入多选题一共多少分" class="input w50"
							value="" id="manyScore" data-validate="required:请输入题目分数" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" id="send">
							提交</button>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
<script type="text/javascript">
	$("#send").click(function() {
		var simpleScore = $("#simpleScore").val()
		var manyScore = $("#manyScore").val()
		if ((simpleScore + manyScore) <= 0) {
			alert("请输入大于0分的题")
			$("#simpleScore").val("")
			$("#manyScore").val("")
		} else {
			$.ajax({
				url : "examMainController/savarand",
				data : {
					"simpleScore" : simpleScore,
					"manyScore" : manyScore
				},
				type : "post",
				success:function(message){
					if(message=="yes"){
						alert("题目已出好并存入考试题库")
					}else{
						alert("题目中可能没有满足该出题方式的题目")
					}
				},
				error:function(){
					
				}
			})
		}
	})
</script>
</html>
