<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<!-- plugin 참조 -->
<script type="text/javascript"
	src="plugins/xdomain/jquery.xdomainajax.js"></script>
<style type="text/css">
ul li {
	border-bottom: 1px dotted #7baeb5;
	display: block;
	width: auto;
	padding: 13px 10px 10px 10px;
	color: #222;
	text-decoration: none;
}

ul li:first-child {
	border-top: 1px dotted #7baeb5;
}
</style>
<script type="text/javascript">
	$(function() {
		alert("test");
		// 페이지 열리면 데이터 로드
		var url = "http://www.weather.go.kr/weather/forecast/mid-term-xml.jsp";
		$.get(url, {"stnId": "109"}, function(data) {
			/* alert("test2");
			// 읽은 결과의 responseText속성을 사용하여 XML 본문을 얻는다.
			var xml_text = data.responseText;
			// XML본문을 jQuery 객체로 변환
			var xml = $(xml_text);
			// wid태그에서 오늘 날짜 추출
			var header = xml.find("header");
			var tm = header.find("tm");
			alert("tm = " + tm);
			// 날짜 출력
			$("#date").html(tm); */
		}).fail(function() {
			$("#result").html("에러");
		});
	});
</script>
</head>
<body>
	<h1 class="title">
		서울 경기 인천 날씨정보 <span id="date">날짜</span>
	</h1>
	<!-- 데이터 출력될 곳 -->
	<ul id="list"></ul>
	<p id="result"></p>
</body>
</html>