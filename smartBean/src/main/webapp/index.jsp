<%@page import="util.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<div class="card-container">

			<div class="calendar-card">
				<a href="calendar">
					<div class="card-content">
						<div class="div-calendar">
							<div class="image-wrapper">
								<img src="../resources/img/calendarImg.svg" alt="Calendar Icon">
							</div>
							<h2 class="card-title">Calendar</h2>
						</div>
						<h2>번거로운 일정 확인은 안녕,</h2>
						<h3>구성원과 일정을 공유해요!</h3>
					</div>
				</a>
			</div>


			<div class="todo-card">
				<a href="todoList">
					<div class="card-content">
						<div class="div-todoList">
							<div class="image-wrapper">
								<img src="../resources/img/togoImg.svg" alt="Todo Icon">
							</div>
							<h2 class="card-title">To-do List</h2>
						</div>
						<h2>가슴 벅찬 목표에 한발짝 가까이,</h2>
						<h3>목표 점검도 손쉽게 진행률과 함께 확인해요!</h3>
					</div>
				</a>
			</div>
		</div>
	</main>
	
	<c:if test="${empty sessionScope.log }">
		<div class="login-nav">
			<ul>
				<li><a href="login">LOGIN&nbsp;&nbsp;&nbsp;></a></li>
				<li><a href="join">JOIN&nbsp;&nbsp;&nbsp;></a></li>
			</ul>
		</div>
	</c:if>


	<jsp:include page="/footer"></jsp:include>

</body>
</html>