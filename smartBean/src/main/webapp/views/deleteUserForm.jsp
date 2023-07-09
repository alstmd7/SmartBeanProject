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
	<form action="DeleteUser" method="POST">
		<input type="text" id="email" name="email" value="${sessionScope.log }" readonly>
		<input type="text" id="password" name="password">
		<input type="button" value="회원탈퇴" onclick="checkValue(form)">
	</form>
	
	<script src="../resources/script/vaildation_login.js"></script>
</body>
</html>