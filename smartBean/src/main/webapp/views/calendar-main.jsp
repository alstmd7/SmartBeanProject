<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />

<title>일잘러의 업무관리 | 똑똑빈</title>
<link
	href='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.5/main.min.css'
	rel='stylesheet' />
<link href='../resources/style/calendar-style.css' rel='stylesheet' />
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.5/main.min.js'></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src='../resources/script/calendar-js.js'></script>
<!-- <script src="/WEB-INF/resources/script/ko.js"></script> -->

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>


</head>
<body>
	<jsp:include page="header"></jsp:include>

	<!-- 테스트 -->
	<!-- 	<form method="post" action="CalendarAction">
    	<input type="text" name="newCalendarEmail" placeholder="Email">
   	 	<input type="text" name="newCalendarName" placeholder="Name">
    	<button type="submit">Submit</button>
	</form> -->

	<div id='external-events'>
		<p>
			<strong>Create calendar</strong>
		</p>
		<div id='create-calendar'>
			<button id='create-calendar-btn'>새로운 캘린더 생성</button>
		</div>

		<!-- create-calendar 추가 팝업  -->
		<div id="create-calendar-popup" style="display: none;">
			<input type="text" id="newCalendar-name-input" name="캘린더 이름"
				placeholder="new calendar title"><br /> <input type="text"
				id="newCalendar-owner-input" name="관리자 이메일"
				placeholder="calendar owner"><br />
			<!-- 			<input type="text" id="newCalendar-share-input" placeholder="공유자 입력(다중공유: , 입))"> -->
			<button id="save-newCalendar-button">만들기</button>
		</div>

		<div id="calendar-list"></div>



		<!-- Event 수정 Popup 팝업 -->
		<div id="event-popup" style="display: none;">
			<button id="close-event-button">닫기</button>
			<button id="delete-event-button">삭제</button><br>
			<!-- 이벤트 타이틀 입력 -->
			<input type="text" id="event-title-input" name="이벤트 타이틀" placeholder="이벤트 타이틀 입력"><br>
			<label> <input type="checkbox" id="all-day-checkbox" checked>하루종일</label> <br>
			<label for="start-date">시작일:</label> <input type="date" id="start-date"> <br>
			<label for="end-date">종료일:</label> <input type="date" id="end-date"> <br>

			<p>세부내용:</p>
			<textarea id="event-description"></textarea><br>

			<button id="save-event-button">저장</button>
		</div>

		<p>
			<strong>Task list</strong>
		</p>
		<!-- Task 추가 버튼 -->
		<button id="add-task-button">Task 추가</button>
		<div
			class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main' style="background-color: #FF6A89;">휴가</div>
		</div>
		<div
			class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>오전반차</div>
		</div>
		<div
			class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>오후반차</div>
		</div>
		<div
			class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main' style="background-color: #329632;">재택근무</div>
		</div>
		<div
			class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main' style="background-color: #FF8200;">출장</div>
		</div>

		<!-- Task 추가 팝업 -->
		<div id="add-task-popup">
			<input type="text" id="task-name-input" placeholder="task title 입력">
			<button id="save-task-button">Task 저장</button>
		</div>


	</div>

	<!-- calendar area -->
	<div id='calendar-container'>
				<!-- 캘린더 전체 저장하기 버튼 -->
		<button id="save-button">SAVE</button>
		<div id='calendar'></div>
	</div>

	<jsp:include page="footer"></jsp:include>
</body>
</html>
