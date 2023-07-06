<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<title>회원가입</title>
</head>
<body>
	<form action="Join" method="POST">
		<p>이메일</p>
		<input type="text" id="email" name="email">
		<p>비밀번호</p>
		<input type="password" id="password" name="password">
		<p>이름</p>
		<input type="text" id="name" name="name">
		
		<input type="button" value="회원가입" onclick="checkValue(form)">
	</form>
	
	<script src="../resources/script/vaildation_join.js"></script>
</body>
</html>