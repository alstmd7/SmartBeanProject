<%@page import="util.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일잘러의 업무관리 | 똑똑빈</title>
</head>
<body>
<%
DBManager.getConnection();
%>

<jsp:include page="/header"></jsp:include>
	
	<main>
		<div class="div-calendar">
			<a href="calendar"><h2>Calendar</h2></a> <!-- 링크 설정 필요 -->
		</div>
		<div class="div-todoList">
			<a href="todoList"><h2>To-do list</h2></a> <!-- 링크 설정 필요 -->
		</div>
		<c:if test="${empty sessionScope.log }">
		<div class="login-nav">
			<ul>
				<li><a href="login">LOGIN&nbsp;&nbsp;&nbsp;></a></li>
				<li><a href="join">JOIN&nbsp;&nbsp;&nbsp;></a></li>
			</ul>
		</div>
		</c:if>
	</main>
	
<jsp:include page="/footer"></jsp:include>
	
</body>
</html>