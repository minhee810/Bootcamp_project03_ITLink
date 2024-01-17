<%@page import="com.team2.dao.BoardDAO,com.team2.dao.UsersDAO"%>
<%@page import="com.team2.db.MySQLConnector,com.team2.dao.AdminDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	/**
	* 
	* @author 최호준
	*
	*/
	
	//서블릿에서 가져온 데이터
	ArrayList<BoardDAO> sortBoard = (ArrayList<BoardDAO>)request.getAttribute("DBList");
	MySQLConnector mysql = new MySQLConnector();
	ArrayList<UsersDAO> userlist = mysql.querySelectUserInfoList();
	pageContext.setAttribute("sortBoard", sortBoard);
	pageContext.setAttribute("userlist", userlist);
	
	UsersDAO user = (UsersDAO)session.getAttribute("user");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Style.css">
<title>글 목록 페이지 : 카테고리별 글 목록을 볼 수 있는 페이지</title>
<style>
/* select박스 스타일 */
#rangeList {
	position: absolute;
	top: 10;
	right: 0;
	margin-right: 120px; /* 오른쪽 여백 조정 */
}

/* 사용자 정보 스타일 */
.user-info {
	margin-left: 100px;
}

</style>
<script>
	/* 정렬 리스트 분리하여 Servlet으로 보내기*/
	function rangeListChange() {
		var List = document.getElementById("rangeList");
		var RangeList = List.value;

		if (RangeList == "rangeRecent") {
			document.location = "ListRecent";
		} else if (RangeList == "rangeOlder") {
			document.location = "ListOlder";
		} else if (RangeList == "rangeLike") {
			document.location = "ListLike";
		} else if (RangeList == "rangeHit") {
			document.location = "ListHit";
		} else {
			document.location = "listAll";
		}
	}

	function logout() {
		document.location = "logout";
	}
	
	function mainList() {
		document.location = "ITList.jsp";
	}
</script>

</head>
<body>
<h1 onclick="mainList()">ITLink</h1>
	<c:choose>
		<c:when
			test="${sessionScope.admin == 'admin' && sessionScope.adminpw == '1234'}">
			<%-- 관리자 로그인 하였을 때.. --%>
			<h5 style="text-align: right; margin-right:10%;">관리자님이 로그인하였습니다.
				<button style="display:block;  margin-left:0px; display: inline; text-align: left" onclick="logout()">
					로그아웃
				</button>
			</h5>
			
			<hr color="black">
			
			<select id="rangeList"
				style="float: right; margin-right: 10%; margin-bottom: 5px;"
				onchange="rangeListChange()">
				<option value=" ">선택</option>
				<option value="rangeAll">전체</option>
				<option value="rangeRecent">최신순</option>
				<option value="rangeOlder">오래된순</option>
				<option value="rangeLike">좋아요순</option>
				<option value="rangeHit">조회수</option>
			</select>

			<table border="1" style="width: 80%; margin: 0 auto; margin-top: 33px;">
				<thead>
					<tr>
						<th>글 번호</th>
						<th>제목</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>좋아요</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${sortBoard}" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td><a href="ITListInfo.jsp?b_No=${board.b_No }">${board.b_Title}</a></td>
							<td>${board.reg_date}</td>
							<td>${board.b_hit}</td>
							<td>${board.b_like}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div style="float: right; margin-top: 10px; margin-right: 10%;">
				<button onclick="document.location='ITWrite.jsp';">글쓰기</button>
				<button onclick="document.location='UserListInfo.jsp';">사용자
					목록</button>
			</div>
		</c:when>
		<%-- ------------------------------------------------------------------ --%>

		<c:otherwise>
			<%--  사용자 로그인 할 때 --%>
			
	
			<h5 style="text-align: right; margin-right: 10%;">${user.getuWriter()}님이 로그인하셨습니다.
				<button onclick="logout()" style="display:block; margin-left: 0px; display: inline; text-align: left;">
					로그아웃
				</button>
			</h5>
			<hr color="black">
			
			<select id="rangeList" style="float: right; 
				margin-right: 10%; 
				margin-bottom: 5px;"
				onchange="rangeListChange()">
				
				<option value=" ">선택</option>
				<option value="rangeAll">전체</option>
				<option value="rangeRecent">최신순</option>
				<option value="rangeOlder">오래된순</option>
				<option value="rangeLike">좋아요순</option>
				<option value="rangeHit">조회수</option>
			</select>

			<table border="1"  style="width: 80%; margin: 0 auto; margin-top: 33px;">
				<thead>
					<tr>
						<th>글 번호</th>
						<th>제목</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>좋아요</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${sortBoard}" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td onclick="location.href='uphit?upPage='+${board.b_No};">
								<a href="ITListInfo.jsp?b_No=${board.b_No}">${board.b_Title}</a>
							</td>
							<td>${board.reg_date}</td>
							<td>${board.b_hit}</td>
							<td>${board.b_like}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</c:otherwise>

	</c:choose>
	<script>
		function loadAllItems() {
		document.getElementById('rangeList').value = "rangeAll";
		rangeListChange(); // 데이터를 가져오는 함수를 호출합니다.
		}
</script>
</html>


