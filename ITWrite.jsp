<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기 페이지 : 사용자에게 유익할 수 있는 글을 관리자가 작성하는 페이지</title>
</head>
<link rel="stylesheet" href="Style.css">
<script type="text/javascript">
	function WriteServlet() {
		document.form.submit();
	}
	function mainList() {
		document.location = "ITList.jsp";
	}	
	
</script>
<body>
<h1 onclick="mainList()">ITLink</h1>
	<h2 style="text-align: left; margin-left:10%;">게시물 글쓰기</h2>
	<form action="WriteServlet" method="POST">
		
		<hr color="black">
		<table border="1" style="width: 80%; margin: 0 auto; margin-top: 33px;">
			
			<tr>
				<td 
					bgcolor="" align="center" style="width: 150px;">카테고리</td>
				<td>
				<select name="CATEGORY">
					<option>IT 기술 블로그</option>
					<option>코딩 테스트</option>
					<option>자바 기초</option>
				</select>
				</td>
			</tr>
			<tr>	
				<td bgcolor="" align="center">제목</td>		
			
				<td>
					<input type="text" align="left" name="TITLE" required="required" autofocus size="30px" />
				</td>
			</tr>
			<tr>
				<td bgcolor="" align="center">링크</td>
				<td><input type="text" align="left" name="LINK" size="55%"></td>
			</tr>
		</table>
		<div style="float: right; margin-top: 10px; margin-right: 10%;">
			<input type ="submit" name = CONTENT value = "저장" onclick="WriteServlet()" />
			<input type = "reset" value ="취소" />
			<input type="button" value="목록" onclick="mainList()" />
		</div>
	</form>
</body>
</html>