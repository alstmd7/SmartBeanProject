<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>To-do list</title>
    <link href="./JJWresete.css" rel="stylesheet">
    <link href="./To-do css.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">

    <script>
        

        function showList(listId) {
            var list1 = document.getElementById('list1');
            var list2 = document.getElementById('list2');

            if (listId === 1) {
                list1.style.display = 'block';
                list2.style.display = 'none';
            } else if (listId === 2) {
                list1.style.display = 'none';
                list2.style.display = 'block';
            }
        }

        function addTask() {
            var dateInput = document.getElementById('dateInput');
            var taskInput = document.getElementById('text');

            var date = dateInput.value;
            var task = taskInput.value;

            // 여기에 일정 추가하는 로직을 구현합니다.
            // 예를 들어, 데이터베이스에 저장하거나 다른 곳에 기록하는 등의 작업을 수행할 수 있습니다.

            // 일정을 추가한 후에 입력 필드를 초기화합니다.
            dateInput.value = '';
            taskInput.value = '';

            alert('일정이 추가되었습니다.');
        }
    </script>

    <style>
       
    </style>
</head>

<body>
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
            <button type="submit" id="button2" onclick="addTask()">추가 하기</button>
        </ol>
        
        <button type="submit" id="button2" onclick="showList(2)">To-do list 삭제</button>

        <ol id="list2">
            <p>날짜선택</p>
            <input type="date" id="dateInput" name="date" value="">
            <p>삭제할 일정 </p>
            <input type="text" id="text" name="task" value="">
            <button type="submit" id="button2" onclick="deleteTask()">삭제 하기</button>
        </ol>

    </div>

    <div class="check">
        <label for="inputDate">날짜 선택:</label>
        <input type="date" id="inputDate" required>
    </div>
    <button onclick="printWeek()">조회</button>
    <div id="output"></div>

    <script>
        function deleteTask() {
            var dateInput = document.getElementById('dateInput');
            var taskInput = document.getElementById('text');

            var date = dateInput.value;
            var task = taskInput.value;

            // 여기에 일정 삭제하는 로직을 구현합니다.
            // 예를 들어, 데이터베이스에서 해당 일정을 삭제하거나 기록을 업데이트하는 등의 작업을 수행할 수 있습니다.

            // 일정을 삭제한 후에 입력 필드를 초기화합니다.
            dateInput.value = '';
            taskInput.value = '';

            alert('일정이 삭제되었습니다.');
        }
    </script>
</body>

</html>