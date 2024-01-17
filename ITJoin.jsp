<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 홈페이지</title>
<style>
body {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.login-container {
	text-align: center;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
	background-color: #f2f2f2;
}
</style>
<script type="text/javascript">
	function join() {
		document.location = "ITJoin.jsp";
	}
	function log() {
		document.location = "ITLogin.jsp";
	}
</script>
</head>
<body>
	<div class="login-container">
		<form action="InsertUServlet" method="post">
			<h1 align="center">회원가입 화면</h1>
			<label>아이디 : </label> 
			<input type="text" name="u_id" maxlength="10" required /> <br /> 

			<label>비밀번호 : </label> 
			<input type="password" name="u_pw" maxlength="10" required /> <br /> 

			<label>이름 : </label> 
			<input type="text" name="u_name" maxlength="10" required /> <br />

			<label>닉네임 : </label> 
			<input type="text" name="uWriter" maxlength="10" required /> <br /> 

			<input type="submit" value="가입" onclick="join()" /> 
			<input type="reset" value="취소" onclick="log()" />
		</form>
	</div>
</body>
</html>