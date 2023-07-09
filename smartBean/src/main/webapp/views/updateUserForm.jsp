<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<c:if test="${empty sessionScope.log }">
		<c:redirect url="login"></c:redirect>
	</c:if>
	
	<form action="UpdateUser" method="POST">
		<p>이메일</p>
		<input type="text" id="email" name="email" value="${sessionScope.log }" readonly>
		<p>비밀번호</p>
		<input type="password" id="password" name="password" value="${sessionScope.password }">
		<p>새로운 비밀번호</p>
		<input type="password" id="new_password" name="new_password">
		<p>닉네임</p>
		<input type="text" id="name" name="name" value="${requestScope.name }">
		
		<input type="button" id="submit-btn" value="변경" onclick="checkValue(form)">
	</form>
	
	<script src="../resources/script/vaildation_join.js"></script>
</body>
</html>