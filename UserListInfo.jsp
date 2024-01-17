
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.sql.*"%>
<%@ page import="com.team2.db.MySQLConnector"%>
<%@ page import="com.team2.dao.UsersDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- DB연결 -->
<%
MySQLConnector mysql = new MySQLConnector();
mysql.connectMySQL();
// 쿼리 사용하여 원하는 정보만 불러오기 
ArrayList<UsersDAO> UserInfoList = mysql.querySelectUserInfoList();
pageContext.setAttribute("UserInfoList", UserInfoList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저들의 리스트 페이지 : 사용자들의 개인 정보 목록들을 한번에 볼 수 있도록 보여주는 페이지</title>
<link rel="stylesheet" href="Style.css">
<script type="text/javascript">
	function backList() {
		document.location = "ITList.jsp";
	}

	function mainList() {
		document.location = "ITList.jsp";
	}
</script>

</head>
<body>
	<h1 onclick="mainList()">ITLink</h1>

	<h2 style="text-align: left; margin-left: 10%;">사용자 정보</h2>
	
	<hr color="black">
	<table border="1" style="width: 80%; margin: 0 auto; margin-top: 33px;">
		<thead>
			<tr>
				<th>번호</th>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>닉네임</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${UserInfoList}" var="info" varStatus="status">
				<tr>
					<td align="center">${status.count}</td>
					<td align="center">${info.u_id}</td>
					<td align="center">${info.u_pw}</td>
					<td align="center">${info.u_name}</td>
					<td align="center">${info.uWriter}</td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
	<div style="float: right; margin-top: 10px; margin-right: 10%;">
		<button value="이전" onclick="backList()">목록</button>
	</div>
</body>
</html>