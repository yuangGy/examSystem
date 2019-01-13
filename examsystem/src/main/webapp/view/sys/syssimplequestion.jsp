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
<style type="text/css">
			#dd {
				position: fixed;
				width: 400px;
				height: 400px;
				background: white;
				top: 30%;
				left: 30%;

				display: none;
				z-index: 1000;
			}

			#ee {
				position: fixed;
				width: 100%;
				height: 100%;
				background: black;
				top: 0;
				display: none;
				opacity: 0.5;
				z-index: 1;
			}
		</style>
</head>
<body>
<div id="dd" style="text-align: center;">
			<button onclick="closePol()">关闭</button>
		</div>
		<div id="ee">

		</div>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong class="icon-reorder"> 留言管理</strong>
		</div>
		<div class="padding border-bottom">
			<ul class="search">
				<li>
					<button type="button" class="button border-green" id="checkall">
						<span class="icon-check"></span> 全选
					</button>
					<button type="button" class="button border-green" id="add">
						<span></span> 增加单选题
					</button>
					<button type="submit" class="button border-red">
						<span class="icon-trash-o"></span> 批量删除
					</button>
				</li>
			</ul>
		</div>
		<table class="table table-hover text-center">
			<tr>
				<th width="120">选择</th>
				<th>题目描述</th>
				<th>题目类型</th>
				<th>题目分数</th>
				<th>正确答案</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${simpleSubjects }" var="subject" varStatus="num">
				<tr>
					<td><input type="checkbox" name="id[]"
						value="${subject.questionId }" /></td>
					<td>${subject.questionName }</td>
					<td>单选题</td>
					<td>${subject.questionScore }</td>
					<td><c:forEach items="${subject.answersFlag }" var="answer">
					${answer}
					</c:forEach>
					<c:forEach items="${subject.options }" var="option">
						<input type="hidden" name="option${num.index }" value="${option }" >
					</c:forEach>
					</td>
					<td><div class="button-group">
							<a class="button border-red"
								href="examMainController/deleteQuestion?questionId=${subject.questionId }"
								onclick="upadate()"><span class="icon-trash-o"></span> 删除</a>
						</div>&nbsp;&nbsp;&nbsp;&nbsp;
						<div class="button-group">
							<a class="button border-red"
								href="examMainController/updateQuestionUI?questionId=${subject.questionId }?type=0"
								onclick=""> 修改</a>
						</div>
						<div class="button-group" >
							<a class="button border-red"> 查看</a>
						</div></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="8"><div class="pagelist">
						<a href="">上一页</a> <span class="current">1</span><a href="">2</a><a
							href="">3</a><a href="">下一页</a><a href="">尾页</a>
					</div></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		

		function closePol() {
			// document.getElementById("dd").style.display="none";
			$("#dd").css("display", "none")
			$("#ee").css("display", "none")
			 $("#dd").html("<button onclick='closePol()'>关闭</button>")
		}
		//计时器
		

		
		$(".button-group").click(function() {
			$("#ee").css("display", "block")
			$("#dd").css("display", "block")
			var questionName = $(this).parent().parent().children("td:eq(1)").text()
			var type = $(this).parent().parent().children("td:eq(2)").text()
			var questionScore = $(this).parent().parent().children("td:eq(3)").text()
			var answer = $(this).parent().parent().children("td:eq(4)").text().trim()
			var name = $(this).parent().parent().children("td:eq(4)").children("input").attr("name")
			 var option =[];
			
        $("input[name='"+name+"']").each(function(){
        	option.push($(this).val());
        })
        $("#dd").append("<p>题目： "+questionName+"</p><p>分数： "+questionScore+"</p><p>类型： "+type+"</p>")
        for(i=0;i<option.length;i++){
        	 $("#dd").append("<p>"+option[i]+"</p>")
        }
        $("#dd").append("<p>正确答案： "+answer+"</p>")
		})
		$("#add").click(function() {
			window.location.href = "examMainController/addUI?type=0"
		})

		function del(id) {
			if (confirm("您确定要删除吗?")) {

			}
		}

		$("#checkall").click(function() {
			$("input[name='id[]']").each(function() {
				if (this.checked) {
					this.checked = false;
				} else {
					this.checked = true;
				}
			});
		})

		function DelSelect() {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {
				var t = confirm("您确认要删除选中的内容吗？");
				if (t == false)
					return false;
			} else {
				alert("请选择您要删除的内容!");
				return false;
			}
		}
	</script>
</body>
</html>