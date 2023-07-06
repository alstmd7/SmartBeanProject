<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="Login" method="POST">
		<p>이메일</p>
		<input type=text id="email" name="email">
		<p>비밀번호</p>
		<input type=text id="password" name="password">
		
		<!-- 함수연결필요 -->
		<input type="submit" value="로그인">
	</form>
</body>
</html>