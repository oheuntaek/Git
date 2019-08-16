<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload= function() {
	if (${su>0}) {
		alert("수정 성공");
		location.href="scoreList.do?pg=${pg}";
	} else if (${su==0}){
		alert("수정 실패");
		history.back();
	} else if (${su<0}){
		alert("점수를 잘못입력하셨습니다.");
		history.back();
	} 
}
</script>
</head>
<body>
<c:if test="${su>0 }">
	성적을 수정하였습니다.
</c:if>
<c:if test="${su==0 }">
	성적을 수정하지 못했습니다.
</c:if>
<c:if test="${su<0 }">
	점수를 제대로 입력하세요.
</c:if>
</body>
</html>