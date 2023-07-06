<%@page import="util.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>똑똑빈</title>
</head>
<body>
<%
DBManager.getConnection();
%>
	
	<main>
		<div class="div-calendar">
			<a href="#"><h2>Calendar</h2></a> <!-- 링크 설정 필요 -->
		</div>
		<div class="div-todoList">
			<a href="#"><h2>To-do list</h2></a> <!-- 링크 설정 필요 -->
		</div>
		<div class="login-nav">
			<ul>
				<li><a href="login">LOGIN&nbsp;&nbsp;&nbsp;></a></li>
				<li><a href="join">JOIN&nbsp;&nbsp;&nbsp;></a></li>
			</ul>
		</div>
	</main>
	
</body>
</html>