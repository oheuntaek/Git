<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	/* 전체 여백 크기 초기화, 기본 글자색 지정  */
	* {
		padding: 0;
		margin: 0;
		color: #333;
	}
	body{
		padding: 20px 30px;
		
	}
	/* form 영역의 크기 및 테두리 */
	#login fieldset {
		width: 270px;
		padding: 15px;
		border: 1px solid #7BAEB5;
		position: relative;
	}
	#login legend{
	 	display: none;
	}
	/* 입력항목 설정 */
	#login label {
		display: inline-block; 
		width: 80px;
		font-size: 14px;
		font-weight: bold;
		padding-left: 10px;
		margin-bottom: 10px;
	}
	#login input[type='text'], input[type='password']{
		border: 1px solid #ccc;
		padding: 3px 10px;
		width: 150px;
		vertical-align: middle;
		font-size: 12px;
		line-height: 150%;
	}
	/* 버튼 크기 지정 */
	#login input[type='submit']{
		width: 270px;
		height: 20px;
	}
	/* 활성화 input에 적용할 스타일 */
	.active{
		border: 1px solid #f00;
		background: #98bf21;
	}
	/* 로딩 박스 */
	#login{
		position: relative;
	}
	#login fieldset .loader{
		position: absolute;
		left: 0; 
		top: 0;
		width: 100%;
		height: 100%;
		background: rgba(0, 0, 0, 0.3);
		display: none; 
	}
	/* 이미지를 로딩박스 한가운데 배치하기 */
	#login .loader img{
		position: absolute;
		left: 50%;
		top: 50%;
		margin-left: -5px;
		margin-top: -5px; 
	}
</style>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		// focus, blur 이벤트 처리
		$("#user_name, #user_password").bind({
			"focus": function() {
				$(this).addClass("active");
			},
			"blur": function() {
				$(this).removeClass("active");
			}
		});
		// submit 이벤트 처리 : 입력값 검사
		$("#login").bind("submit", function() {
			if(!$("#user_name").val()){
				alert("아이디를 입력하세요");
				$("#user_name").focus();
				return false; // submit 취소
			}
			if(!$("#user_password").val()){
				alert("비밀번호를 입력하세요");
				$("#user_password").focus();
				return false; // submit 취소
			}
			$("#login .loader").show();
			// 가능하다면 이곳에서 Ajax로 로그인 처리를 합니다.
			// 3초후에 loader 없애기
			setTimeout(function() {
				alert("안녕하세요. " + $("#user_name").val() + "님");
				$("#login .loader").hide();
			}, 3000);
			return false // 테스트 용
		});
	});


</script>
</head>
<body>
<form action="" id="login">
	<fieldset>
		<legend>로그인</legend>
		<div>
			<label for="user_name">아이디</label>
			<input type="text" name="user_name" id="user_name">
		</div>
		<div>
			<label for="user_password">비밀번호</label>
			<input type="password" name="user_password" id="user_password">
		</div>
		<div>
			<input type="submit" value="로그인">
		</div>
		<div class="loader"><img alt="" src="img/loader.gif"> </div>
	</fieldset>
</form>
</body>
</html>