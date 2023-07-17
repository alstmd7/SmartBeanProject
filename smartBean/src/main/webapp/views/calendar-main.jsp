<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="model.calendar.CalendarDao" %>
<%@ page import="model.calendar.CalendarVo" %>
<%@ page import="model.share.ShareDao" %>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />

<title>일잘러의 업무관리 | 똑똑빈</title>
<link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.5/main.min.css' rel='stylesheet' />
<link href='../resources/style/calendar-style.css' rel='stylesheet' />
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.5/main.min.js'></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src='../resources/script/calendar-js.js'></script>
<!-- <script src="/WEB-INF/resources/script/ko.js"></script> -->

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<c:if test="${empty sessionScope.log }"><c:redirect url="login"></c:redirect></c:if>
	<jsp:include page="/header"></jsp:include>

	<div id='external-events'>
		<!-- =============== CALENDAR =================== -->
		<!-- 새로운 캘린더 생성 -->
			<button id="create-calendar-btn">새로운 캘린더 만들기</button>

		<!-- 로그인한 사용자의 캘린더 -->
<!-- 
			<input type="checkbox" id="user-calendar-checkbox"
				class="calendar-checkbox-input">
				<label for="user-calendar-checkbox">${sessionScope.name}님 캘린더</label>
		</div> -->
		
		<!-- 캘린더 리스트 -->
		<div id="calendar-list"></div>
		
			<!-- 캘린더 생성 팝업 -->
		<div id="create-calendar-popup" style="display: none;">
			<form method="post" action="CreateCalendar" id="create-calendar-form">
				<input type="text" id="create-calendar-popup" name="calendarName" placeholder="new calendar title"><br>
				<input type="text" id="newCalendar-owner-input" name="owner" value="${sessionScope.log}" placeholder="calendar owner"><br>
				<input type="submit" id="save-newCalendar-button" value="생성">
			</form>
		</div> 
		
			<!-- 새로 생성한 캘린더 관리 팝업 -->
		<form method="post" action="UpdateCalendar" id="update-calendar-form">
			<div id="admin-newCalendar-popup" style="display: none;">
			  <h2 id="newCalendar-popup-title"></h2>
			  <button id="close-newCalendar-button">닫기</button> 					<!-- 수정 : 이벤트 동작 안하게 하거나 밖으로 빼거나 ******** -->
			  <button id="delete-calendar-button">캘린더 삭제</button><br>
			  <p>공유할 멤버의 이메일(여러명은 '/'로 분리)</p>
			  <input type="text" id="user-share-input" name="user-share-input" placeholder="사용자 이메일">
			  <button id="share-button">공유하기</button>
			  <br>
			  <div class="user-share-list"></div>
			  
			  <p>캘린더 이름 변경</p>
			  <!-- <input type="text" id="new-calendar-name-input-${calendarId}" placeholder="변경할 캘린더 이름"> -->
			  <input type="text" id="new-calendar-name-input" placeholder="변경할 캘린더 이름">
			  <button id="update-calendar-button">수정하기</button>
			</div>
		</form>
		
		
		<!-- =============== EVENT =================== -->
		<!-- Event 수정 Popup 팝업 -->
		<div id="event-popup" style="display: none;">
			<button id="close-event-button">닫기</button>
			<button id="delete-event-button">삭제</button>
			<br>
			<!-- 이벤트 타이틀 입력 -->
			<input type="text" id="event-title-input" name="이벤트 타이틀" placeholder="이벤트 타이틀 입력"><br> <label>
			<!-- <input type="checkbox" id="all-day-checkbox" checked>하루종일</label> <br> -->
			<label for="start-date">시작일:</label> <input type="date" id="start-date"> <br>
			<label for="end-date">종료일:</label> <input type="date" id="end-date"> <br>
			<h4>일정 추가할 캘린더 위치</h4>
				<select id="calendars" multiple>
				    <% 
				    // 사용자의 이메일 정보를 세션에서 가져옴
				    String email = (String) session.getAttribute("log");
				    
				    // DB 접근을 위한 객체 생성
				    CalendarDao calendarDao = CalendarDao.getInstance();
				    ShareDao shareDao = ShareDao.getInstance();
				    
				    // 로그인한 사용자와 일치하는 모든 캘린더 가져오기
				    ArrayList<CalendarVo> userCalendars = calendarDao.getUserCalendars(email);
				    
				    // 공유받은 캘린더 가져오기
				    ArrayList<CalendarVo> sharedCalendars = shareDao.getSharedCalendars(email);
				    
				    // 기본 캘린더와 공유받은 캘린더 합치기
				    ArrayList<CalendarVo> calendars = new ArrayList<>();
				    calendars.addAll(userCalendars);
				    calendars.addAll(sharedCalendars);
				    
				    for (CalendarVo calendar : calendars) { %>
				        <option value="<%= calendar.getNo() %>"><%= calendar.getName() %></option>
				    <% } %>
				</select>
			<p>상세내용</p>
			<textarea id="event-description"></textarea>
			<br>

			<button id="save-event-button">저장</button>
		</div>
		

		<!-- =============== TASK =================== -->
		<!-- Task 추가/관리 버튼 -->
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
		<form method="post" action="CreateTask" id="create-task-form">
			<div id="add-task-popup" style="display: none;">
			  <input type="text" id="task-name-input" name="name" placeholder="task title 입력">
			  <button id="save-task-button">Task 저장</button>
			</div>
		</form>
		
		<!-- Task 관리 팝업 -->
		<div id="admin-task-popup" style="display: none;">
		  <h2 id="task-popup-title"></h2>
		  <button id="close-task-button">닫기</button>
		  <br>
		  
		  <button id="delete-task-button">Task 삭제</button><br>
		  <p>Task 이름 변경</p>
		  <input type="text" id="new-task-name-input" placeholder="변경할 Task 이름">
		  <button id="update-task-button">수정하기</button>
		</div>


	</div> <!-- external-events -->

	<!-- calendar area -->
	<div id='calendar-container'>
		<!-- 캘린더 전체 저장하기 버튼 -->
		<button id="save-button">SAVE</button>
		
		<div id='calendar'></div>
	</div>

	<jsp:include page="/footer"></jsp:include>
</body>
</html>
