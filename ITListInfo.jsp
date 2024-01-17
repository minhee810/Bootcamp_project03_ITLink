<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.team2.dao.BoardDAO,com.team2.db.MySQLConnector"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	int num = Integer.parseInt(request.getParameter("b_No"));

	MySQLConnector mysql = new MySQLConnector();
	
	BoardDAO selectDetail= mysql.selectNum(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Style.css">
<title>게시글 상세보기 페이지: 원하는 글을 선택하면 해당글의 내용을 상세히 볼 수 있는 페이지</title>
<script type="text/javascript">

	function updateB(num){	//수정 페이지로 이동
		document.location = "ITUpdate.jsp?b_No="+num;
	}
	
	function deleteB(num){
		document.location = "Delete?b_No="+num;
	}
	
	function backlist() {
		document.location="listAll";
	}
	
	function uplike(num) {
		document.location="uplike?uppage="+num;
	}
	function mainList() {
		document.location="ITList.jsp";
	}

</script>

</head>
<body>
<h1 onclick="mainList()">ITLink</h1>

	<h2 style="text-align: left; margin-left: 10%;"><%= selectDetail.getB_Title() %> 글 세부정보</h2>
	<hr color="black">
	<br />
	<table border="1" style="width:100%;">
		<tr>
			<td class="thh">글 번호</td>
			<td class="tqq"><%= selectDetail.getB_No() %></td>
		</tr>
		<tr>
			<td class="thh">제목</td>
			<td class="tqq"><%= selectDetail.getB_Title() %></td>
		</tr>
		<tr>
			<td class="thh">링크</td>
			<td class="tqq"><a href="<%= selectDetail.getB_Link() %>"> <%= selectDetail.getB_Link() %></a></td>
		</tr>
		<tr>
			<td class="thh">카테고리</td>
			<td class="tqq"><%= selectDetail.getB_Category() %></td>
		</tr>
		<tr>
			<td class="thh">등록 날짜</td>
			<td class="tqq"><%= selectDetail.getReg_date() %></td>
		</tr>	
		<tr>
			<td class="thh">조회수</td>
			<td class="tqq"><%= selectDetail.getB_hit() %></td>
		</tr>
		<tr>
			<td class="thh">좋아요</td>
			<td class="tqq"><%= selectDetail.getB_like() %></td>
		</tr>
	</table>
	
		
	<c:choose>
		<c:when
			test="${sessionScope.admin == 'admin' && sessionScope.adminpw == '1234'}">
		<button class="btnn" onclick="backlist()">목록</button>		
		<button class="btnn" onclick="updateB(<%= selectDetail.getB_No()%>)">수정</button>
		<button class="btnn" onclick="deleteB(<%= selectDetail.getB_No()%>)">삭제</button>

		</c:when>
		<c:otherwise>
			<button style="border-radius:100px; border:none; margin-top:20px; display:inline-block;" class="uplikeButton" onclick="uplike(<%= selectDetail.getB_No()%>)"><img alt="like" src="./image/like.png" width="50px;"   /></button>
			<button class="btnn" onclick="backlist()">목록</button>	
		</c:otherwise>
		
	</c:choose>
	<style>
	.btnn{
	cursor:pointer;
	padding:10px 15px;
	}
	</style>
</body>
</html>
