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
			<strong><span class="icon-pencil-square-o"></span>增加内容</strong>
		</div>
		<div class="body-content">
			<div class="form-x">
				<div class="form-group">
					<div class="label">
						<label>题目类型：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" readonly="readonly"
							value=" <c:if test="${type==0 }">单选题</c:if><c:if test="${type==1 }">多选题</c:if> "
							id="type" data-validate="required:请选择题目类型" />
						<div class="tips"></div>
					</div>
				</div>

				<div class="form-group">
					<div class="label">
						<label>题目分值：</label>
					</div>
					<div class="field">
						<input id="questionScore" type="number" class="input w50"
							data-validate="required:请输入题目分值" />
						<div class="tips"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>题目描述：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" value="" id="questionName"
							data-validate="required:请输入题目描述" />
						<div class="tips"></div>
					</div>
				</div>
				<div id="options">
					<div class="form-group">
						<div class="label">
							<input type="checkbox" id="sysoption" value="A">A
						</div>
						<div class="field">
							<input type="text" class="input w50" value="" name="optionsDesc"
								data-validate="required:请输入选项内容" />
							<div class="tips"></div>
						</div>
					</div>
					<div class="form-group">
						<div class="label">
							<input type="checkbox" id="sysoption" value="B">B
						</div>
						<div class="field">
							<input type="text" class="input w50" value="" name="optionsDesc"
								data-validate="required:请输入选项内容" />
							<div class="tips"></div>
						</div>
					</div>
				</div>
				<div style="position: relative; left: 400px" class="field">
					<button id="addoption" class="button bg-main " type="submit">添加选项</button>
				</div>
				<hr>
				<div class="clear"></div>

				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button bg-main icon-check-square-o" id="send" >
							提交</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var count = new Array('C', 'D', 'E', 'F', 'G')
	var time = -1
	$("#addoption")
			.click(
					function() {
						time++
						if (time > 4) {
							alert("不能再添加更多选项了")
						} else {

							$("#options")
									.append(
											'<div class="form-group"> <div class="label"> <input type="checkbox" id="sysoption" value="'+count[time]+'" >'
													+ count[time]
													+ ' </div> <div class="field"> <input type="text" class="input w50" value="" name="optionsDesc" data-validate="required:请输入选项内容" /> <div class="tips"></div> </div> </div>')
						}
					})
					
	$("#send").click(function () {
		var type = $("#type").val()
		var questionScore = $("#questionScore").val()
		var questionName = $("#questionName").val()
		var sysoption =[];    
        $('#sysoption:checked').each(function(){
        	sysoption.push($(this).val());   
        });
        var optionsDesc =[];
        $("input[name='optionsDesc']").each(function(){
        	optionsDesc.push($(this).val());
        })
        
        $.ajax({
        	url:"examMainController/addsubject",
        	data:{
        		"type":type,
        		"questionScore":questionScore,
        		"questionName":questionName,
        		"sysoption":sysoption,
        		"optionsDesc":optionsDesc
        	},
        	traditional:true,
        	type:"post",
        	success:function(message){
        		if(message=="no"){
        			alert("请选择至少一个正确的答案")
        		}else if(message=="type0"){
        			alert("单选题只能一个正确的答案！")
        		}else if(message=="yes"){
        			alert("添加成功")
        		}else{
        			alert("添加失败")
				}
        	},
        	error:function(){
        		alert("添加失败")
        	}
        })
	})
</script>
</html>