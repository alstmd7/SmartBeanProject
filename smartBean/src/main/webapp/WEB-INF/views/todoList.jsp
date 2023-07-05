<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/style/todo_style.css" rel="stylesheet">

</head>
<body>
	<div class="To-do-list">
	    <h1>To-do list</h1>
	    <ul>
	        <li>이름 : 장재원</li>
	    </ul>
	</div>
	<div class="check">
	    <label for="inputDate">날짜 선택:</label>
	    <input type="date" id="inputDate" required>
	</div>
	<button onclick="printWeek()">조회</button>
	<div id="output"></div>
	<script src="resources/script/todo_script.js"></script>
</body>
</html>