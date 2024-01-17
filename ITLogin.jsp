<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>로그인 화면 : 사용자가 로그인하는 페이지</title>
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
  function join(){
		document.location ="ITJoin.jsp";
		}
  </script>
</head>
<body>
	<div class="login-container">
		<form action="LogServlet" method="post">
			<h1>로그인 페이지</h1>		
			<label>아이디 : </label>
		 	<input type="text" name="ID" maxlength="10" required /> <br /> 	
						 	
	 		<label>비밀번호 : </label>
	 		<input type="password" name="PW" maxlength="10" required /> <br />
	
	 		<input type="submit" value="로그인" /> 
	 		<input type="button" value="회원가입" onclick="join()" />
		</form>
	 </div>
</body>
</html>
