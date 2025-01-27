<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		//	id로 태그 접근
		$("#title").html("두번째 제목");
		//	자식셀렉터 사용
		$("div#container > h1#title").html("제목입니다.");
		// 자손 셀렉터 사용
		$("div#container div.box").html("안녕하세요");
		// 자식 셀렉터 사용하여 모든 <li>태그 선택
		$("ul > li").html("목록입니다.");
		// 두번째 <li> 태그 선택
		$("ul > li:eq(1)").html("jQuery 고유의 방식도 있습니다.");
		// 복수의 태그 선택
		$("p,pre").html("다중 요소 선택");
		// name 속성이 source인 항목만 선택
		$("pre[name='source']").html("CSS 선택자");
		// <li> 태그의 첫번째와 마지막 항목 선택
		$("ul > li:first-child").html("First-Child");
		$("ul > li:last-child").html("Last-Child");
	});
</script>
<style type="text/css">
div#container {
	width: auto;
	border: 5px solid #ff00ff;
	padding: 10px;
}
div#container > h1#title{
	background-color: #d5d5d5;
	padding: 10px;
}
div#container div.box {
	padding: 10px;
	background-color: #ffff00;
	font: 20px '굴림';
}
div#container > ul > li:first-child, div#container > ul > li:last-child {
	border: 3px dotted red;
	padding: 3px 10px;
}
</style>
</head>
<body>
<div id="container">
	<h1 id="title"></h1>
	<div class="box"></div>
	<ul>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>
<p></p>
<pre></pre>
<pre name ="source"></pre>
</body>
</html>