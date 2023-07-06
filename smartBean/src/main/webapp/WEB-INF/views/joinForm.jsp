<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<form method="POST" action="Join">
		<p>이메일</p>
		<input type="text" id="email" name="email">
		<p>비밀번호</p>
		<input type="password" id="password" name="password">
		<p>이름</p>
		<input type="text" id="name" name="name">
		
		<input type="button" id="submit" value="회원가입" onclick="checkForm(form)">
	</form>
	
	<script src="resources/script/validation-join.js"></script>
</body>
</html>