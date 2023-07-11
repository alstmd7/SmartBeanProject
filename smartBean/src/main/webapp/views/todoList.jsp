<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<link href="../resources/style/todo_style.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
<title>To-do list</title>
</head>
<body>
	<c:if test="${empty sessionScope.log }">
		<c:redirect url="login"></c:redirect>
	</c:if>
    
	<div class="To-do-list">
		
		<h1>To-do list</h1>
		<p>${sessionScope.name } 님</p>

		<button type="submit" id="button1" onclick="showList(1)">To-do list 추가</button>
		
		<form action="CreateTodo" method="POST">
			<p>날짜선택</p>
			<input type="date" id="target_at" name="target_at">
			<p>일정 추가</p>
			<input type="text" id="content" name="content">
			<input type="submit" value="일정추가">
		</form>

		<button type="submit" id="button2" onclick="showList(2)">To-do list 삭제</button>
		
		<form action="">
			<p>날짜선택</p>
			<input type="date" id="dateInput" name="date">
			<p>삭제할 일정</p>
			<input type="text" id="text" name="task" value="">
			<button type="submit" id="button2" onclick="ValueCheck()">삭제하기</button>
		</form>
	</div>
	
	<div class="check">
		<label for="inputDate">날짜 선택:</label> <input type="date" id="inputDate" required>
	</div>
	<button onclick="printWeek()">조회</button>
	<div id="output"></div>
	<div>
		<input type="text" id="todoList">
		<div>
			<c:forEach var="todo" items="${todo}">
				<c:set var="target" value="${todo.target_at}"></c:set>
				<div id="checkTodo">
					<p id="target">${todo.target_at}</p>
					<p>${todo.content}"</p>
					<p>${todo.check}</p>
				</div>
			</c:forEach>
		</div>
	</div>


	<script src="../resources/script/to-do.js"></script>
</body>

</html>