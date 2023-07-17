
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link href="../resources/style/todo_style.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
    <title>To-do list</title>
</head>


<body>
	<c:if test="${empty sessionScope.log }">
		<c:redirect url="login"></c:redirect>
	</c:if>
	
	<jsp:include page="/header"></jsp:include>
	
	<main>
		<div id="todo_area">
			<div class="To-do-list">
		        <h1>To-do list</h1>
		        <ul>
		            <li>${sessionScope.name } 님</li>
		        </ul>
		
		        <button type="submit" id="button1" onclick="showList(1)">To-do list 추가</button>
		
		        <div id="list1" class="hidden">
		        	<form action="CreateTodo" method="POST">
		        		<p>날짜선택</p>
			            <input type="date" id="dateInput" name="date" value="">
			            <p>일정 추가</p>
			            <input type="text" id="text" name="task" value="">
			            <button type="submit" id="button1">추가 하기</button>
		        	</form>
		        </div>
		
		        <button type="submit" id="button2" onclick="showList(2)">To-do list 삭제</button>

				<div id="list2" class="hidden">
					<p>삭제할 일정</p>					
					<button type="submit" id="button2" form="todoNo" onclick="deleteTasks()">선택한
						일정 삭제</button>

					<form action="DeleteTodoAction" method="post">
						<input type="text" name="todoNo" class="hidden">
						<button type="submit" id="delete-btn" class="hidden"></button>
					</form>
				</div>

				<button type="submit" id="button3" onclick="showList(3)">To-do list 수정</button>
			
				
		        <div id="list3" class="hidden">
		            <p>수정할 일정</p>
		            <input type="text" id="editText" name="task" value="">
		            <button type="submit" id="button33" onclick="editTasks()">선택한 일정 수정</button>
		        </div>
		
		        <button type="submit" id="button4" onclick="showList(4)">To-do list 일정이동</button>
		
		        <div id="list4" class="hidden">
		            <p>날짜선택</p>
		            <input type="date" id="dateInput" name="date" value="">
		            <button type="submit" id="button44" onclick="editTasks()">선택한 날짜로 이동</button>
		        </div>
		
		
		    </div>
		
		    <div class="check">
		        <label for="inputDate">날짜 선택:</label>
		        <input type="date" id="inputDate" required>
		    </div>
		    <button onclick="printWeek()" id="button">조회</button>
		    
		    <div id="output"></div>
	    </div>
	</main>
	
    <jsp:include page="/footer"></jsp:include>
    
   <script src="../resources/script/to-do.js"></script> 
</body>

</html>

