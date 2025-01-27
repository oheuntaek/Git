<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
* {
	padding: 0;
	margin: 0;
	color: #333;
}

ul {
	list-style: none;
}

#container {
	padding: 30px 20px;
}

h1 {
	font-size: large;
	border-left: 10px solid #7baeb5;
	border-bottom: 1px solid #7baeb5;
	padding: 10px;
	width: auto;
}

#comment_write {
	padding: 20px 15px;
	border-bottom: 1px solid #7baeb5;
}

#comment_write label {
	display: inline-block;
	width: 80px;
	font-size: 14px;
	font-weight: bold;
	margin-bottom: 10px;
}

#comment_write input[type='text'], textarea#comment {
	border: 1px solid #ccc;
	vertical-align: middle;
	padding: 3px 10px;
	font-size: 12px;
	line-height: 150%;
}

textarea#comment {
	width: 380px;
	height: 90px;
}

.comment_item {
	font-size: 13px;
	color: #333;
	padding: 15px;
	border-bottom: 1px dotted #ccc;
	line-height: 150%;
}
.comment_item .writer{
	color: #555;
	line-height: 200%;
}

.comment_item .writer input{
	vertical-align: middle;
}
.comment_item .writer .name{
	color: #222;
	font-size: 14px;
	font-weight: bold;
}
</style>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		// 저장하기 버튼 클릭 : submit 이벤트
		$("#comment_form").submit(function() {
			// 입력검사
			if(!$("#user_name").val()){
				alert("이름을 입력하세요");
				$("#user_name").focus();
				return false;
			}
			if(!$("#comment").val()){
				alert("내용을 입력하세요");
				$("#comment").focus();
				return false;
			}
			// 작성일시 만들기
			var date = new Date();
			var yy = date.getFullYear();
			var mm = date.getMonth()+1;
			var dd = date.getDate();
			var hh = date.getHours();
			var mi = date.getMinutes();
			var ss = date.getSeconds();
			// 자리수 보정
			if(mm < 10) mm ="0" + mm;
			if(dd < 10) dd ="0" + dd;
			if(hh < 10) hh ="0" + hh;
			if(mi < 10) mi ="0" + mi;
			if(ss < 10) ss ="0" + ss;
			
			var today = yy + "-" + mm + "-" + dd + " " +  hh + ":" +  
						mi + ":" + ss;
			// 새로운 글이 추가될 때 사용될 li 태그 생성
			var new_li = $("<li>");
			new_li.addClass("comment_item");
			// 작성자 정보가 지정될 <p>태그
			var writer_p = $("<p>");
			writer_p.addClass("writer");
			// 작성자 정보의 이름이 지정될 <span>태그
			var name_span = $("<span>");
			name_span.addClass("name");
			name_span.html($("#user_name").val()+"님");
			// 작성일시를 표시할 <span>태그
			var date_span = $("<span>");
			date_span.html(" / " + today + " ");
			// 삭제하기 버튼용 <input>태그
			var del_input = $("<input>");
			del_input.attr({
				"type" : "button",
				"value" : "삭제하기"
			});
			//alert("test211");
			del_input.addClass("delete_btn");
			// 내용을 표시할 <p>태그
			var content_p = $("<p>");
			content_p.html($("#comment").val());
			/* 조립하기 */
			// 첫번째 줄 <p> 태그에 이름, 날짜, 버튼 추가
			writer_p.append(name_span).append(date_span).append(del_input);
			// <li>태그에 <p>태그 두개 추가
			new_li.append(writer_p).append(content_p);
			// <ul>태그에 <li>태그 추가
			$("ul#comment_list").append(new_li);
			
			// 입력내용 지우기
			$("#user_name").val("");
			$("#comment").val("");
			return false;
		});
		// 삭제 버튼 클릭시의 동작을 미리 지정
		$(document).on("click",".delete_btn",function(){
			if(confirm("정말 선택하신 항목을 삭제하시겠습니까?")){
				$(this).parents(".comment_item").remove();
			}
		});
	});
</script>
</head>
<body>
	<div id="container">
		<h1>jQuery Comment</h1>
		<div id="comment_write">
			<form action="" id="comment_form">
				<div>
					<label for="user_name">작성자</label> <input type="text"
						name="user_name" id="user_name"> <input type="submit"
						value="저장하기">
				</div>
				<div>
					<label for="comment">덧글 내용</label>
					<textarea rows="" cols="" name="comment" id="comment"></textarea>
				</div>
			</form>
		</div>
		<!-- 덧글 목록 -->
		<ul id="comment_list">
			<!-- 여기에 동적 생성 요소가 들어가게 됩니다. -->
		</ul>
	</div>
</body>
</html>