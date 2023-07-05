<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>히더기캘린더</title>
<link rel='stylesheet' href='main.css'/>
<style>
.fc-event {
	margin-top: 5px;
	cursor: move
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src='main.js'></script>
<script src='ko.js'></script>
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.5.1/index.global.min.js'></script>

<script>
	var calendar = null;
		
	$(document).ready(function() {
		Calendar = FullCalendar.Calendar;
		var Draggable = FullCalendar.Draggable;

		var containerEl = document.getElementById('external-events'); // 캘린더 옆 이벤트 객체 
		var calendarEl = document.getElementById('calendar'); // 달력에 들어갈 캘린더
		var checkbox = document.getElementById('drop-remove'); // 

		// initialize the external events(이벤트 객체를 드래그 할 수 있도록 연동)
		// -----------------------------------------------------------------

		new Draggable(containerEl, {
			itemSelector : '.fc-event',
			eventData : function(eventEl) {
				return {
					title : eventEl.innerText
				};
			}
		});

		// initialize the calendar
		// -----------------------------------------------------------------

		calendar = new Calendar(calendarEl, {
			headerToolbar : {
				left : 'prev,next today',
				center : 'title',
				right : 'dayGridMonth,timeGridWeek,timeGridDay'
			},
			editable : true, // 수정 가능 여부
			droppable : true, // 드롭항목제거 체크박스를 선택하고 이벤트를 드래그하면 해당 이벤트가 목록에서 제거됨 
			drop : function(info) {
				// is the "remove after drop" checkbox checked?
				if (checkbox.checked) {
					// if so, remove the element from the "Draggable Events" list
					info.draggedEl.parentNode.removeChild(info.draggedEl);
				}
			},
			locale : 'ko'
		});

		calendar.render();
	});
	
	// 정보 저장 
	function allSave(){
		// 1. 전체 이벤트 데이터 추출(calendar.getEvents())
		var allEvent = calendar.getEvents();
		var events = new Array();
		
		for(var i = 0; i < allEvent.length; i++){
			var obj = new Object();
			
			obj.title = allEvent[i]._def.title;					// 이벤트(task 명칭)
			obj.allDay = allEvent[i]._def.allDay;				// 하루 종일의 이벤트인지 알려주는 boolean
			obj.start = allEvent[i]._instance.range.start; 	// 시작 날짜 및 시간 
			obj.end = allEvent[i]._instance.range.end; 		// 종료 날짜 및 시간 
			
			events.push(obj); // 전체 이벤트가 배열 형태로, json 객체 형태로 events에 저장
		}
		
		var jsondata = JSON.stringify(events);	// string 형태로 저장 
		console.log(jsondata);
		
		savedata(jsondata);
	}
	
		function savedata(jsondata){
		$.ajax({
			type: 'POST',
			url: "savedata.jsp"
			data: {"alldata" : jsondata},
			dataType: 'text',
			asyne: fales
		})
		.done(funtion(result)){
			
		}
		.fail(funtion(request, status, error){
			alert("ERROR : " + error);
		})
	} 
	
	// 2. 추출된 데이터를 ajax로 서버에 전송하여 DB 저장
	
	
	
</script>

<!-- <script>
	$(document).ready(function() {
		var calendarEl = document.getElementById('calendar');
		var calendar = new FullCalendar.Calendar(calendarEl, {
			initialView : 'dayGridMonth',
			locale : 'ko'
		});
		calendar.render();
	});
</script> -->
</head>
<body>
	<div id='external-events'
		style="float: left; width: 15%; padding-right: 30px; padding-left: 20px">
		<p>
			<strong>똑똑히수</strong>
		</p>

		<div
			class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>연차</div>
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
			<div class='fc-event-main'>출장</div>
		</div>
		<div
			class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>재택근무</div>
		</div>

		<p>
			<input type='checkbox' id='drop-remove' /> <label for='drop-remove'>드롭항목제거</label>
		</p>
	</div>

	<div style="float: left; width: 80%">
		<div
			style="height: 30px; text-aline: center; margin-bottom: 20px; font-size: 30px; font-weight: bold">
			<div style="width: 60%; float: left; text-align: right">타이틀타이틀타이틀타이틀</div>
			<div style="width: 40%; float: left; text-align: right">
				<button
					style="width: 70px; height: 40px; background-color: black; color: white; certical-align: middle; font-size: 15px; cursor: pointer"
					onclick="javascript:allSave();">저장</button>
			</div>
		</div>
		<div id='calendar'></div>
	</div>
</body>
</html>