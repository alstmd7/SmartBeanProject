<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>task 생성</title>
</head>
<body>
	<c:if test="${empty sessionScope.log }">
		<c:redirect url="login"></c:redirect>
	</c:if>
	
	<form action="task" method="POST">
		<input type="text" id="name" name="name">
		<input type="button" id="submit-btn" onclick="checkValue(form)">
	</form>
	
</body>
</html>