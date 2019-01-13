<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>在线考试系统</title>

<link href="/examsystem/css/main.css" rel="stylesheet" type="text/css" />
<link href="/examsystem/css/iconfont.css" rel="stylesheet"
	type="text/css" />
<link href="/examsystem/css/test.css" rel="stylesheet" type="text/css" />
<script src="/examsystem/js/jquery-1.11.3.min.js"></script>

<!--时间js-->
<script src="/examsystem/js/jquery.countdown.js"></script>
<script>
	document.onkeydown = function(e) {
		var ev = window.event || e;
		var code = ev.keyCode || ev.which;
		if (code == 116) {
			ev.keyCode ? ev.keyCode = 0 : ev.which = 0;
			cancelBubble = true;
			return false;
		}
	} //禁止f5刷新
	document.oncontextmenu = function() {
		return false
	};//禁止右键刷新

	$(function() {
		$('li.option').click(
				function() {
					var examId = $(this).closest('.test_content_nr_main')
							.closest('li').attr('id'); // 得到题目ID
					var cardLi = $('a[href=#' + examId + ']'); // 根据题目ID找到对应答题卡
					// 设置已答题
					if (!cardLi.hasClass('hasBeenAnswer')) {
						cardLi.addClass('hasBeenAnswer');
					}

				});
	});
	window.jQuery(function($) {
		"use strict";

		$('time').countDown({
			with_separators : false
		});
		$('.alt-1').countDown({
			css_class : 'countdown-alt-1'
		});
		$('.alt-2').countDown({
			css_class : 'countdown-alt-2'
		});

	});
</script>
<style>
.hasBeenAnswer {
	background: #5d9cec;
	color: #fff;
}
</style>

</head>
<body>
	<div class="main">
		<!--nr start-->
		<div class="test_main">
			<div class="nr_left">
				<div class="test">
					<form action="" method="post">
						<div class="test_title">
							<p class="test_time">
								<i class="icon iconfont">&#xe6fb;</i><b class="alt-1">00:01</b>
							</p>
							<font><input type="button" id="send" name="test_jiaojuan"
								value="交卷"></font>
						</div>

						<div class="test_content">
							<div class="test_content_title">
								<h2>单选题</h2>
								<p>
									<span>共</span><i class="content_lit">${simpleSubjects.size() }</i><span>题，</span><span>合计</span><i
										class="content_fs">${simpleScore }</i><span>分</span>
								</p>
							</div>
						</div>
						<div class="test_content_nr">
							<ul>
								<c:forEach items="${simpleSubjects }" var="simpleSubject"
									varStatus="num">
									<li id="qu_0_${num.index }">
										<div class="test_content_nr_tt">
											<i>${num.index+1 }</i><span>(${simpleSubject.questionScore }分)</span><font>${simpleSubject.questionName}</font><b
												class="icon iconfont">&#xe881;</b>
										</div>

										<div class="test_content_nr_main">
											<ul>
												<c:forEach items="${simpleSubject.answersFlag }"
													var="answersFlag">
													<input type="hidden" name="flag${num.index+1 }"
														value="${answersFlag }" />
												</c:forEach>
												<c:forEach items="${simpleSubject.options }" var="option"
													varStatus="numoption">
													<li class="option"><input type="radio"
														class="radioOrCheck" name="answer${num.index+1 }"
														id="0_answer_${num.index+1 }_option_${numoption.index+1 }"
														value="${option}" /> <label
														for="0_answer_${num.index+1 }_option_${numoption.index+1 }">
															<p class="ue" style="display: inline;">${option}</p>
													</label></li>
												</c:forEach>
											</ul>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>

						<div class="test_content">
							<div class="test_content_title">
								<h2>多选题</h2>
								<p>
									<span>共</span><i class="content_lit">${manySubjects.size() }</i><span>题，</span><span>合计</span><i
										class="content_fs">${manyScore }</i><span>分</span>
								</p>
							</div>
						</div>
						<div class="test_content_nr">
							<ul>
								<c:forEach items="${manySubjects }" var="manySubject"
									varStatus="num">
									<li id="qu_1_${num.index }">
										<div class="test_content_nr_tt">
											<i>${num.index+1 }</i><span>(${manySubject.questionScore }分)</span><font>${manySubject.questionName}</font><b
												class="icon iconfont">&#xe881;</b>
										</div>

										<div class="test_content_nr_main">
											<ul>
												<c:forEach items="${manySubject.answersFlag }"
													var="answersFlag">
													<input type="hidden" name="flags${num.index+1 }"
														value="${answersFlag }" />
												</c:forEach>
												<c:forEach items="${manySubject.options }" var="option"
													varStatus="numoption">
													<li class="option"><input type="checkbox"
														value="${option}" class="radioOrCheck"
														name="answers${num.index+1 }"
														id="1_answer_${num.index+1 }_option_${numoption.index+1 }" />
														<label
														for="1_answer_${num.index+1 }_option_${numoption.index+1 }">
															<p class="ue" style="display: inline;">${option}</p>
													</label></li>
												</c:forEach>
											</ul>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>

					</form>
				</div>

			</div>
			<div class="nr_right">
				<div class="nr_rt_main">
					<div class="rt_nr1">
						<div class="rt_nr1_title">
							<h1>
								<i class="icon iconfont">&#xe692;</i>答题卡
							</h1>
							<p class="test_time">
								<i class="icon iconfont">&#xe6fb;</i><b id="ti" class="alt-1">00:01</b>
							</p>
						</div>

						<div class="rt_content">
							<div class="rt_content_tt">
								<h2>单选题</h2>
								<p>
									<span>共</span><i class="content_lit">${simpleSubjects.size() }</i><span>题</span>
								</p>
							</div>
							<div class="rt_content_nr answerSheet">
								<ul>
									<c:forEach items="${simpleSubjects }" var="Subject"
										varStatus="num">
										<li><a href="#qu_0_${num.index }">${num.index+1 }</a></li>
									</c:forEach>
								</ul>
							</div>
						</div>

						<div class="rt_content">
							<div class="rt_content_tt">
								<h2>多选题</h2>
								<p>
									<span>共</span><i class="content_lit">${manySubjects.size() }</i><span>题</span>
								</p>
							</div>
							<div class="rt_content_nr answerSheet">
								<ul>
									<c:forEach items="${manySubjects }" var="Subject"
										varStatus="num">
										<li><a href="#qu_1_${num.index }">${num.index+1 }</a></li>
									</c:forEach>
								</ul>
							</div>
						</div>

					</div>

				</div>
			</div>
		</div>
		<!--nr end-->
		<div class="foot"></div>
	</div>




	<div
		style="text-align: center; margin: 50px 0; font: normal 14px/24px 'MicroSoft YaHei';">

	</div>
	<%request.setAttribute("simpleSubjects", request.getAttribute("simpleSubjects")); %>>
</body>
<script type="text/javascript">
	var simples = new Array()
	var many = new Array()
	var simplelength = ${simpleSubjects.size()};
	var manylength = ${manySubjects.size()};
	var realtime = simplelength+manylength
	var simplesFlag = new Array()
	var manyFlag = new Array()
	var time = 0
	function getFlag() {
		for(var i=1;i<=simplelength;i++){
			 $("input[name='flag"+i+"']").each(function(){
				 simplesFlag.push($(this).val());
			})
			
	}
		for(var i=1;i<=manylength;i++){
			
			var flagString =''
			 $("input[name='flags"+i+"']").each(function(){
				 flagString += $(this).val().trim()
			});
				 manyFlag.push(flagString);
	}
	
	}
	
	function getAnswers() {
		for(var i=1;i<=simplelength;i++){
			var arr = $('input:radio[name="answer'+i+'"]:checked').val().split(".")
			 simples.push(arr[0])
			}
			
			for(var i=1;i<=manylength;i++){
					var answersString ='';
					$('input[name="answers'+i+'"]:checked').each(function(){
						var arr = $(this).val().trim().split(".")
			        	answersString+=arr[0]
			        });
					many.push(answersString);
				} 
	}
	
	function checkedAnswers() {
		
		for(var i=1;i<=simplelength;i++){
			if($('input[name="answer'+i+'"]').is(':checked')){
				time++;
			}
		} 
		for(var i=1;i<=manylength;i++){
			if($('input[name="answers'+i+'"]').is(':checked')){
				time++;
			}
		} 
	}
	
	function sendExam() {
		checkedAnswers();
		if(time!=realtime){
			alert("请检查是否做完题目，如果没做完题目无法提交试卷！")
			time=0
		}else{
			getAnswers();
			getFlag();
		 $.ajax({
			url:"getScore",
			data:{
				"simples":simples,
				"many":many,
				"simplesFlag":simplesFlag,
				"manyFlag":manyFlag
			},
			type:"post",
			traditional:true,
			success:function(messge){
				alert("本次考试得分:"+messge)
			},
			error:function(){
				
			}
		}) 
		}
	}
	$("#send").click(function () {
		sendExam();
	})


	$(function () {
		var time;
		var t = setInterval(() => {
		time =$("#ti").text();
		if(time.trim()=="00:00:00"){
			alert("时间到");
			sendExam()
			clearInterval(t);
		}
		}, 1000);
	})
	
</script>
</html>

