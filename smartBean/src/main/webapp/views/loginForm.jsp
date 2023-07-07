<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form action="Login" method="POST">
		<p>이메일</p>
		<input type=text id="email" name="email">
		<p>비밀번호</p>
		<input type=text id="password" name="password">
		
		<input type="button" value="로그인" onclick="checkValue(form)">
	</form>
	
	<script src="../resources/script/vaildation_login.js"></script>
</body>
</html>