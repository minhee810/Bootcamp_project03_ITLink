<%@page import="com.team2.dao.BoardDAO"%>
<%@page import="com.team2.db.MySQLConnector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("b_No"));
	MySQLConnector mysql = new MySQLConnector();
	BoardDAO number =  mysql.selectNum(num);
	
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정 페이지 : 등록된 게시물 제목 및 링크를 수정하는 페이지</title>
<link rel="stylesheet" href="Style.css">
<script type="text/javascript">
function mainList() {
	document.location = "ITList.jsp";
}	
</script>
</head>
<body>
	<%--
		효경 : 수정 페이지 작성한 값을 보내기
	--%>
	<h1 onclick="mainList()">ITLink</h1>
	<h2 align="center">게시물 수정</h2>
	<form action="Update">
	
		
		<h2 style="text-align: left; margin-left:10%;"><input type="text" name="b_No" hidden="" value="<%= num%>"/>	</h2>
		제목 : <input type="text" name="b_Title" value="<%= number.getB_Title() %>" required="required"><br />
		링크 : <input type="text" name="b_Link" value="<%= number.getB_Link() %>" required="required"><br />
		
		
		
		<input type="submit" value="수정" />
		<input type="button" value="목록" onclick="mainList()" />
	</form>
	
</body>
</html>