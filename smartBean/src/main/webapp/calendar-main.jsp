<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />

<title>일잘러의 업무관리 | 똑똑빈</title>
<link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.5/main.min.css' rel='stylesheet' />
<link href='calendar-style.css' rel='stylesheet' />
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.5/main.min.js'></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src='calendar-js.js'></script>
<!-- <script src="/WEB-INF/resources/script/ko.js"></script> -->

</head>
<body>
	<div id='external-events'>
		<p><strong>Create calendar</strong></p>
		<div id='create-calendar'>
			<button id='create-calendar-btn'>캘린더 추가</button>
		</div>

		<!-- create-calendar 추가 팝업  -->
		<div id="create-calendar-popup" style="display: none;">
			<input type="text" id="newCalendar-name-input" placeholder="new calendar title">
			<input type="text" id="newCalendar-owner-input" placeholder="calendar owner">
<!-- 			<input type="text" id="newCalendar-share-input" placeholder="공유자 입력(다중공유: , 입))"> -->
			<button id="save-newCalendar-button">만들기</button>
		</div>

		<div id="calendar-list"></div>


		<p><strong>Task list</strong></p>
		<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>휴가</div>
		</div>
		<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>오전반차</div>
		</div>
		<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>오후반차</div>
		</div>
		<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>재택근무</div>
		</div>
		<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>출장</div>
		</div>

		<!-- Task 추가 버튼 -->
		<button id="add-task-button">Task 추가</button>
		
		<!-- Task 추가 팝업 -->
		<div id="add-task-popup">
			<input type="text" id="task-name-input" placeholder="task title 입력">
			<button id="save-task-button">Task 저장</button>
		</div>
		
		
		
		<!-- Event 수정 Popup 팝업 -->
	 	<div id="event-popup" style="display: none;">
			<h2 id="event-title"></h2>
			<button id="close-event-button">닫기</button>
			<button id="delete-event-button">삭제</button><br>
			<label> <input type="checkbox" id="all-day-checkbox" checked>하루종일 </label> <br>
			<label for="start-date">시작일:</label>
			<input type="date" id="start-date"> <br>
			<label for="end-date">종료일:</label>
			<input type="date" id="end-date"> <br>
	
			<p>세부내용:</p>
			<textarea id="event-description"></textarea><br>
	
			<button id="save-event-button">저장</button>
		</div>
	
		<!-- 캘린더 전체 저장하기 버튼 -->
		<button id="save-button">일정 저장</button>
	</div>

	<!-- calendar area -->
	<div id='calendar-container'>
		<div id='calendar'></div>
	</div>

</body>
</html>
