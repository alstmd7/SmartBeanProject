<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${empty sessionScope.log }">
		<c:redirect url="login"></c:redirect>
	</c:if>

	<h3>${sessionScope.name }님 환영합니다.</h3>
	<button onclick="location.href='UpdateUserRequest'">회원정보 수정</button>
	<button onclick="location.href='Logout'">로그아웃</button>
	<button onclick="location.href='DeleteUserRequest'">회원탈퇴</button>
</body>
</html>