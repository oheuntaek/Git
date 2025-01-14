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
<script type="text/javascript">
	$(function() {
		$("#mybtn").click(function() {
			$.ajax({
				url: "ajax/xml/xml03.xml",
				type: "get",
				dataType: "xml",
				cache: false,
				timeout: 30000,
				success: function(data) {
					// 전달된 XML데이터에서 값 추출
					var title = $(data).find("title").text();
					var time = $(data).find("time").text();
					var teacher = $(data).find("teacher").text();
					// 추출된 데이터를 화면에 출력하기 위해, HTML 요소를 동적으로 생성
					var div = $("<div>");
					var p1 = $("<p>").html(title);
					var p2 = $("<p>").html(time);
					var p3 = $("<p>").html(teacher);
					//alert("test3");
					// 메소드 체인을 사용한 동적요소의 결합
					div.append(p1).append(p2).append(p3);
					// 화면에 표시
					$("#result").append(div);
				},
				error: function(xhr) {
					$("#result").html("에러코드: " + xhr.status);
				}
			});
		});
	});
</script>
</head>
<body>
	<h1 class="title">$.ajax() 함수를 사용한 XML데이터 읽기</h1>
	<div class="exec">
		<input type="button" value="XML데이터 가져오기" id="mybtn">
	</div>
	<div class="console" id="result"></div>
</body>
</html>