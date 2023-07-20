	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
   
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <link href="../resources/style/todo_style.css" rel="stylesheet">
    <link rel="shortcut icon" href="/resources/img/favicon.ico">
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
		    </div>
		
		    <div class="check">
		        <label for="inputDate">날짜 선택:</label>
		        <input type="date" id="inputDate" required>
		    </div>
		    <button onclick="printWeek()" id="button">조회</button>
		    
		    <!--<button type="submit" id="button0" onclick="showList(1)">일정 추가</button>
		
		          <div id="list1" class="hidden">
		        	<form action="CreateTodo" method="POST">
		        		<div id="border">
		        		<li class="schedule">날짜선택</li>
			            <input type="date" id="target_at_add" name="target_at_add" value="">
			            <li class="schedule">일정 추가</li>
			            <input type="text" id="content_add" name="content_add" value="">

			            </div>
			            
			            <button type="submit" id="button0" onclick="addTask()">추가 하기</button>
		        	</form>
		        </div>-->
		    
		    <div id="output"></div>
			
			<!--  -->
			<div id="pop_info_1" class="pop_wrap" style="display:none;">
				<div class="pop_inner">
					<button type="button" class="btn_close">닫기</button>
					<p>날짜선택</p>
					<input type="date" id="dateInput" name="date" value="">
					<p>일정</p>
					<input type="text" id="editText" name="task" value="">
					
					<div class="col-del">
					
					<button type="button" id="correction" onclick="editTasks()">수정</button>
					<button type="button" id="delete" form="todoNo" onclick="deleteTasks()">삭제</button>
					
					</div>
				</div>
			</div>
	    </div>
	</main>
	
    <jsp:include page="/footer"></jsp:include>
    
   <script src="../resources/script/to-do.js"></script> 
</body>

</html>
