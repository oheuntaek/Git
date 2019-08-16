<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	margin: auto;
}
</style>
</head>
<body>
	<table border="1" width="800" height ="700">
		<tr height ="100">
			<td colspan="2" align="center"><jsp:include page="header.jsp" />
			</td>
		</tr>
		<tr>
			<td valign="top" width="100"><jsp:include page="nav.jsp" /></td>
			<c:if test="${display == null }">
			<td><jsp:include page="section.jsp" /></td>
			</c:if>
			<c:if test="${display != null }">
			<td><jsp:include page="${display }" /></td>
			</c:if>
		</tr>
		<tr height ="100">
			<td colspan="2" align="center"><jsp:include page="footer.jsp" /></td>
		</tr>
	</table>
</body>
</html>