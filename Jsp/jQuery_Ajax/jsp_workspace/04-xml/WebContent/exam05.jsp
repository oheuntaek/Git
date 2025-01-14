<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
*{
	padding: 0;
	margin: 0;
	color: #333;
}
ul{
	list-style: none;
}
#container{
	padding: 30px 20px;
}
h1{
	font-size: large;
	border-left: 10px solid #7BAEB5;
	border-bottom: 1px solid #7BAEB5;
	padding: 10px;
	width: auto;
}
div#comment_write{
	padding: 20px 15px;
	border-bottom: 1px solid #7BAEB5;
}
div#comment_write label{
	display: inline-block;
	width:80px;
	font-size: 14px;
	font-weight: bold;
	margin-bottom: 10px;
}
input[name='user_name'], textarea[name='comment'] {
	border: 1px solid #ccc;
	vertical-align: middle;
	padding: 3px 10px;
	font-size: 12px;
	line-height: 150%;
}
textarea[name='comment'] {
	width: 380px;
	height: 90px;
}
/* 앞으로 생성될 태그의 CSS 설정 */
.comment_item{
	font-size: 13px;
	color: #333;
	padding: 15px;
	border-bottom: 1px dotted #CCC;
	line-height: 150%
}
.comment_item .writer {
	color: #555;
	line-height: 200%;
}
.comment_item .writer input {
	vertical-align: middle;
}
.comment_item .writer .name{
	color: #222;
	font-weight: bold;
	font-size: 14px;
}
</style>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	/* 새로운 글을 화면에 추가하기 위한 함수 */
	function addNewItem(num, writer, content, datetime) {
		//alert("num" +num + "\n"+"user_name" +user_name + "\n"+"content" + content+ "\n"+"datetime" + datetime+ "\n")
		// 새로운 덧글이 추가될 li태그
		var new_li = $("<li>");
		new_li.attr("data-num", num);
		new_li.addClass("comment_item");
		// 작성자 정보가 지정될 <p>태그
		var writer_p = $("<p>");
		writer_p.addClass("writer");
		// 작성자 정보의 이름
		var name_span = $("<span>");
		name_span.addClass("name");
		name_span.html(writer + "님");
		// 작성일시
		var date_span = $("<span>");
		date_span.html(" / " + datetime + " ");
		// 삭제하기 버튼
		var del_input = $("<input>");
		del_input.attr({
			"type" : "button",
			"value" : "삭제하기"
		});
		del_input.addClass("delete_btn");
		// 내용
		var content_p = $("<p>");
		content_p.html(content);
		// 조립하기
		writer_p.append(name_span).append(date_span).append(del_input);
		new_li.append(writer_p).append(content_p);
		$("#comment_list").append(new_li);
	}
	/* 맨 마지막에 작동 */
	$(function() {
		/* 기본 덧글 목록 불러오기 */
		$.get("ajax/comment/comment_list.xml",{},function(data){
			// 읽어들인 XML에서 "item"태그를 찾아 반복처리
			$(data).find("item").each(function() {
				var num = $(this).find("num").text();
				var writer = $(this).find("writer").text();
				var content = $(this).find("content").text();
				var datetime = $(this).find("datetime").text();
				addNewItem(num, writer, content, datetime);
			});
		}).fail(function() {
			alert("덧글 목록을 불러오는데 실패하였습니다. 잠시후에 다시 시도해 주세요");
		});
		/* 덧글 내용 저장 이벤트 */
		$("#comment_form").submit(function() {
			// 작성자 이름에 대한 입력 검사
			if(!$("#user_name").val()){
				alert("이름을 입력하세요");
				$("#user_name").focus();
				return false;
			} else if(!$("#comment").val()){
				alert("내용을 입력하세요");
				$("#comment").focus();
				return false;
			}
			// 글저장을 위한 post방식의 Ajax 연동 처리
			var url = "ajax/comment/comment_write.jsp";
			$.post(url, $(this).serialize(), function(data) {
				alert("test2");
				// XML에서 "result"태그의 값을 추출해서 식으로 변환    / eval : boolean값으로 변환
				var result = eval($(data).find("result").text());
				// 결과가 저장 실패를 의미한다면, 에러메시지 출력후 처리 중단
				if(!result){
					alert($(data).find("message").text());
					return false;
				}
				alert("test3");
				// 데이터 추출
				var num = $(data).find("num").text();
				var writer = $(data).find("writer").text();
				var content = $(data).find("content").text();
				var datetime = $(data).find("datetime").text();
				alert("result" +result + "\n"+"num" +num + "\n"+"user_name" +writer + "\n"+"content" + content+ "\n"+"datetime" + datetime+ "\n");
				// 함수 호출하여 li태그 추가
				addNewItem(num, writer, content, datetime);
			}).fail(function() {
				alert("덧글 목록을 불러오는데 실패하였습니다. 잠시후에 다시 시도해 주세요");
			});
			return false;
		});
		/* 삭제 버튼 클릭시에 항목 삭제하도록 미리 지정  */
		$(document).on("click",".delete_btn", function() {
			if(confirm("정말 선택하신 항목을 삭제하시겠습니까?")){
				// 덧글 삭제를 할 JSP 파일
				var url = "ajax/comment/comment_delete.jsp";
				// 글 번호 얻기
				var num = $(this).parents("li").attr("data-num");
				// 삭제 대상
				var target = $(this).parents(".comment_item");
				// 글 번호를 통하여 삭제를 요청함
				$.post(url, {"num": num}, function(data) {
					// result 값 추출
					var result = eval($(data).find("result").text());
					// 결과 메시지
					var message = $(data).find("message").text();
					// 메시지 출력
					alert(message);
					// DB에서 데이터가 삭제되면, 해당 내용을 화면에서 삭제
					if(result){
						target.remove();
					}
				}).fail(function() {
					alert("덧글 삭제에 실패했습니다. 잠시후에 다시 시도해 주세요" );
				});
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
				<label for="user_name">작성자</label>
				<input type="text" name="user_name" id="user_name">
				<input type="submit" value="저장하기">
			</div>
			<div>
				<label for="comment">덧글 내용</label>
				<textarea name="comment" id="comment"></textarea>
			</div>
		</form>
	</div>
	<ul id="comment_list">
		<!-- 여기에 동적 생성 요소가 들어감 -->
	</ul>
</div>
</body>
</html>