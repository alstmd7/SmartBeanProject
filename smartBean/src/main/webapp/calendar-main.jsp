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
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.11.5/main.min.js'></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- <script src="/WEB-INF/resources/script/ko.js"></script> -->

<script>
	document.addEventListener('DOMContentLoaded', function() {
		var Calendar = FullCalendar.Calendar;
		var Draggable = FullCalendar.Draggable;

		var containerEl = document.getElementById('external-events');
		var calendarEl = document.getElementById('calendar');
		var checkbox = document.getElementById('drop-remove');

		// 이벤트(task)
		// -----------------------------------------------------------------
		new Draggable(containerEl, {
			itemSelector : '.fc-event',
			eventData : function(eventEl) {
				return {
					title : eventEl.innerText
				};
			}
		});

		// calendar
		// -----------------------------------------------------------------
		var calendar = new Calendar(calendarEl, {
			headerToolbar : {
				left : 'prev,next today',
				center : 'title',
				right : 'dayGridMonth,timeGridWeek,timeGridDay'
			},
			editable : true,
			droppable : true, 
		});

		calendar.render();
		
		// 캘린더 추가 버튼 클릭 이벤트
		$("#create-calendar-btn").on("click", function() {
			$("#create-calendar-popup").fadeIn();
		});
		
		// 캘린더 추가 저장 버튼 클릭 이벤트
		$("#save-newCalendar-button").on("click", function() {
			var newCalendarName = $("#newCalendar-name-input").val();
			var newCalendarOwner = $("#newCalendar-owner-input").val();
			if (newCalendarName.trim() !== "") {
				var newCalendar = document.createElement("div");
				newCalendar.className = "calendar-checkbox";
				newCalendar.innerHTML = `
					<input type="checkbox" id="calendar-checkbox-${newCalendarName}" class="calendar-checkbox-input" data-owner="${newCalendarOwner}">
					<label for="calendar-checkbox">${newCalendarName}</label>
				`;
				$("#calendar-list").append(newCalendar);
				$("#create-calendar-popup").fadeOut();
				$("#newCalendar-name-input").val("");
				$("#newCalendar-owner-input").val("");
				// 체크박스 클릭 이벤트
				$("#" + newCalendarName).on("change", function() {
					var isChecked = $(this).prop("checked");
					var calendarName = $(this).attr("id");
					if (isChecked) {
						// 달력에 이벤트 추가
						calendar.addEventSource({
							events: [],
							id: calendarName
						});
					} else {
						// 달력에서 이벤트 제거
						calendar.getEventSources().forEach(function(source) {
							if (source.id === calendarName) {
								source.remove();
							}
						});
					}
				});
			}
		});
		
		// 저장하기 버튼 클릭 이벤트
		$("#save-button").on("click", function() {
			var events = calendar.getEvents(); // 현재 캘린더의 모든 이벤트 가져오기
			console.log(events); // 콘솔에 이벤트 데이터 출력 (테스트용)
		});
		 
		// external-events를 스크롤 가능하도록 변경(event가 많아져서 영역을 넘어갈 경우)
		containerEl.style.overflowY = 'auto';
		
		// Task 추가 버튼 클릭 이벤트
		$("#add-task-button").on("click", function() {
			$("#add-task-popup").fadeIn();
		});

		// Task 저장 버튼 클릭 이벤트
		$("#save-task-button").on("click", function() {
			var taskName = $("#task-name-input").val();
			if (taskName.trim() !== "") {
				var newEvent = document.createElement("div");
				newEvent.className = "fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event";
				newEvent.innerHTML = "<div class='fc-event-main'>" + taskName + "</div>";
				containerEl.insertBefore(newEvent, containerEl.lastChild);
				$("#add-task-popup").fadeOut();
				$("#task-name-input").val("");
			}
		});
		
		// calendar에 등록된 task click event
		$('#calendar').on('click', '.fc-daygrid-event', function() {
		  var eventTitle = $(this).find('.fc-event-main').text();
		  var eventPopup = $('#event-popup');
		  var editButton = $('#edit-event-button');
		  var deleteButton = $('#delete-event-button');
		  var allDayCheckbox = $('#all-day-checkbox');
		  var startDateInput = $('#start-date');
		  var endDateInput = $('#end-date');
		  var eventDescriptionInput = $('#event-description');

		  $('#event-title').text(eventTitle);

		  eventPopup.fadeIn();

		  // 이벤트 팝업 상세 구조
		  var event = $(this).data('event');
		  if (event) {
		    if (event.allDay) {
		      allDayCheckbox.prop('checked', true);
		      startDateInput.hide();
		      endDateInput.hide();
		    } else {
		      allDayCheckbox.prop('checked', false);
		      startDateInput.show();
		      endDateInput.show();
		      startDateInput.val(event.start.toISOString().split('T')[0]);
		      endDateInput.val(event.end.toISOString().split('T')[0]);
		    }
		    eventDescriptionInput.val(event.extendedProps.description);
		  }

		  // Edit event button click event
		  editButton.off('click').on('click', function() {
		    // Handle edit event functionality here
		    // You can access the event details using the variables defined above
		  });

		  // Delete event button click event
		  deleteButton.off('click').on('click', function() {
		    // Handle delete event functionality here
		    // You can access the event details using the variables defined above
		    eventPopup.fadeOut();
		  });

		  // Save event button click event
		  $('#save-event-button').off('click').on('click', function() {
		    // Handle save event functionality here
		    // You can access the event details using the variables defined above
		    eventPopup.fadeOut();
		  });
		});


	});
</script>

<style>
html, body {
	margin: 0;
	padding: 0;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
}

/* 화면 왼쪽 task, view calendar */
.demo-topbar+#external-events {
	top: 60px;
}

#external-events .fc-event {
	cursor: move;
	margin: 3px 0;
}

#calendar-container {
	position: relative;
	z-index: 1;
	margin-left: 200px;
}

#calendar {
	max-width: 1100px;
	margin: 20px auto;
}

/* external-events 스타일 */
#external-events {
	position: fixed;
	z-index: 2;
	top: 60px; /* 저장하기 버튼이 있으므로 아래로 이동 */
	left: 20px;
	width: 200px;
	padding: 10px;
	border: 1px solid #ccc;
	background: #eee;
}

#create-calendar-btn {
	width: 90%;
	height: 20px;
	/* 	border: solid 1px grey; 
	border-radius: 20px 40px 60px 80px;  */
}

/* Task 추가 팝업 스타일 */
 #add-task-popup {
	display: none;
 	/*position: fixed; 
	top: 50%;
	left: 15%; 
	transform: translate(-50%, -50%);
	width: 450px;
	padding: 20px;
	background: #ccc;
} */

#add-task-popup input[type="text"] {
	width: 60%;
	padding: 10px;
	margin-bottom: 10px;
}

#add-task-popup button {
	padding: 10px 20px;
	background: #fff;
	border: none;
	cursor: pointer;
}

#event-popup {
	position: fixed;
	z-index: 99; ! important;
	top: 50%;
	left: 15%;
	transform: translate(-50%, -50%);
	padding: 20px;
	background: #ccc;
	width: 200px;
	height: 330px;
}

#event-description {
	width: 90%;
	height: 120px;
}
</style>
</head>
<body>
	<div id='external-events'>
		<div id='create-calendar'>
			<button id='create-calendar-btn'>create calendar</button>
		</div>

		<!-- create-calendar 추가 팝업  -->
		<div id="create-calendar-popup" style="display: none;">
			<input type="text" id="newCalendar-name-input" placeholder="new calendar title"> <input type="text" id="newCalendar-owner-input" placeholder="calendar owner">
			<button id="save-newCalendar-button">만들기</button>
		</div>

		<div id="calendar-list"></div>

		<p>
			<strong>Draggable Events</strong>
		</p>

		<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>Event 1</div>
		</div>
		<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>Event 2</div>
		</div>
		<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>Event 3</div>
		</div>
		<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>Event 4</div>
		</div>
		<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>Event 5</div>
		</div>

		<p>
			<!-- 저장하기 버튼 추가 -->
			<button id="save-button">저장하기</button>
		</p>
		<p>
			<!-- Task 추가 버튼 -->
			<button id="add-task-button">Task 추가</button>
		</p>
		
		<!-- Task 추가 팝업 -->
		<div id="add-task-popup">
			<input type="text" id="task-name-input" placeholder="task title 입력">
			<button id="save-task-button">저장</button>
		</div>
		
	</div>

	<div id='calendar-container'>
		<div id='calendar'></div>
	</div>

	<!-- Task 추가 팝업 -->
<!-- 	<div id="add-task-popup">
		<input type="text" id="task-name-input" placeholder="task title 입력">
		<button id="save-task-button">저장</button>
	</div> -->

	<!-- Event 수정 Popup 팝업 -->
<!-- 	<div id="event-popup" style="display: none;">
		<h2 id="event-title"></h2>
		<button id="edit-event-button">수정</button>
		<button id="delete-event-button">삭제</button>
		<br> <label> <input type="checkbox" id="all-day-checkbox"
			checked>하루종일
		</label> <br> <label for="start-date">시작일:</label> <input type="date"
			id="start-date"> <br> <label for="end-date">종료일:</label>
		<input type="date" id="end-date"> <br>

		<p>세부내용:</p>
		<textarea id="event-description"></textarea>
		<br>

		<button id="save-event-button">저장</button>
	</div> -->
</body>
</html>
