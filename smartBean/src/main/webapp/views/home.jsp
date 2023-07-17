<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
    <title>마이페이지</title>
</head>

<body>
    <c:if test="${empty sessionScope.log }">
        <c:redirect url="login"></c:redirect>
    </c:if>
    
    <jsp:include page="/header"></jsp:include>

	<main>
	    <div class="edit-member">
	        <h1>My Page</h1>
			<button class="home-btn" onclick="location.href='calendar'">Calendar</button>
	        <button class="home-btn" onclick="location.href='todoList'">To-do List</button>
	        <button class="home-btn" onclick="location.href='updateUser'">회원정보 수정</button>
	    </div>
    </main>
    
    <jsp:include page="/footer"></jsp:include>
</body>

</html>