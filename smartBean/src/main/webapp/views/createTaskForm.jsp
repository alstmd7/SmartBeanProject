<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Task 생성</h1>
	<form action="CreateTask" method="POST">
		<p>캘린더 번호</p>
		<input type="number" id="calendar_no" name="calendar_no">
		<p>이름</p>
		<input type="text" id="name" name="name">
		<input type="submit" value="task추가">
	</form>
</body>
</html>