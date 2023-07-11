<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>To-do list</title>
    <link href="./To-do css.css" rel="stylesheet">
    <script src="./To-do JS.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
</head>

<body onload="printWeek()">
    <div class="To-do-list">
        <h1>To-do list</h1>
        <ul>
            <li>이름: 장재원</li>
        </ul>

        <button type="submit" id="button1" onclick="showList(1)">To-do list 추가</button>

        <ol id="list1">
            <p>날짜선택</p>
            <input type="date" id="dateInput" name="date" value="">
            <p>일정 추가</p>
            <input type="text" id="text" name="task" value="">
            <button type="submit" id="button1" onclick="addTask()">추가 하기</button>
        </ol>

        <button type="submit" id="button2" onclick="showList(2)">To-do list 삭제</button>

        <ol id="list2">
            <p>삭제할 일정</p>
            <button type="submit" id="button2" onclick="deleteTasks()">선택한 일정 삭제</button>
        </ol>

        <button type="submit" id="button3" onclick="showList(3)">To-do list 수정</button>

        <ol id="list3">
            <p>수정할 일정</p>
            <input type="text" id="editText" name="task" value="">
            <button type="submit" id="button33" onclick="editTasks()">선택한 일정 수정</button>
        </ol>

        <button type="submit" id="button4" onclick="showList(4)">To-do list 일정이동</button>

        <ol id="list4">
            <p>날짜선택</p>
            <input type="date" id="dateInput" name="date" value="">
            <button type="submit" id="button44" onclick="editTasks()">선택한 날짜로 이동</button>
        </ol>


    </div>

    <div class="check">
        <label for="inputDate">날짜 선택:</label>
        <input type="date" id="inputDate" required>
    </div>
    <button onclick="printWeek()">조회</button>
    <div id="output"></div>

    
</body>

</html>



