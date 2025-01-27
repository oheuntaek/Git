<%@page import="board.bean.BoardDTO"%>
<%@page import="board.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	int seq = Integer.parseInt(request.getParameter("seq"));
	int pg = Integer.parseInt(request.getParameter("pg"));
	BoardDAO boardDAO = new BoardDAO();
	// 조회수 증가
	boardDAO.updateHit(seq);
	BoardDTO boardDTO = boardDAO.boardView(seq);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" style="border-collapse: collapse;">
	<tr>
		<td colspan="3">
			<font size="5"><%=boardDTO.getSubject() %></font>
		</td>
	</tr>
	<tr align="center">
		<td width="150">글번호 : <%=boardDTO.getSeq() %></td>
		<td width="150">작성자 : <%=boardDTO.getName() %></td>
		<td width="150">조회수 : <%=boardDTO.getHit() %></td>
	</tr>
	<tr>
		<td colspan="3" height="200" valign="top">
			<pre><%=boardDTO.getContent() %></pre>
		</td>
	</tr>
</table>
<input type="button" value="이전목록" 
	onclick="location.href='../main/index.jsp?req=boardList&pg=<%=pg%>'">
<% if(session.getAttribute("memId").equals(boardDTO.getId())) { %>
<input type="button" value="글수정" 
	onclick="location.href='../main/index.jsp?req=boardModifyForm&seq=<%=seq%>&pg=<%=pg%>'">
<input type="button" value="글삭제" 
	onclick="location.href='../main/index.jsp?req=boardDelete&seq=<%=seq%>&pg=<%=pg%>'">
<% } %>
</body>
</html>











