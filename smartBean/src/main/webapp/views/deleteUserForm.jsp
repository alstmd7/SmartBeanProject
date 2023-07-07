<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="DeleteUserForm" get="POST">
		<input type="text" id="password" name="password">
		<input type="button" value="회원탈퇴" onclick="checkValue(form)">
	</form>
</body>
</html>