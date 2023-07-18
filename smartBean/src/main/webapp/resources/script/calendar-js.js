var calendar = null;
$(document).ready(function() {
	var Calendar = FullCalendar.Calendar;
	var Draggable = FullCalendar.Draggable;

	var containerEl = document.getElementById('external-events');
	var calendarEl = document.getElementById('calendar');

	new Draggable(containerEl, {
		itemSelector: '.fc-event',
		eventData: function(eventEl) {
			return {
				title: eventEl.innerText
			};
		}
	});

	calendar = new Calendar(calendarEl, {
		headerToolbar: {
			left: 'prev,next today',
			center: 'title',
			right: 'dayGridMonth,timeGridWeek,timeGridD4ay'
		},
		editable: true,
		droppable: true,
		eventRender: function(info) {
			var eventTitle = info.event.title;
			var eventElement = info.el;

			if (eventTitle === '휴가') {
				eventElement.style.backgroundColor = '#FF6A89';
			} else if (eventTitle === '재택근무') {
				eventElement.style.backgroundColor = '#329632';
			} else if (eventTitle === '출장') {
				eventElement.style.backgroundColor = '#FF8200';
			}
		}
	});

	// 캘린더 불러오기
	calendar.render();

	// <<<<<<<<<<<<<<< calendar >>>>>>>>>>>>>>>
	var newCalendarName = "";

	// 캘린더 만들기
	$("#create-calendar-btn").on("click", function() {
		$("#create-calendar-popup").fadeIn();
	});

	// 캘린더 생성 정보 저장 및 관리 버튼 추가					 수정 : id를 만들고 -> id에 calendarNo(db에서 꺼내) , checked 속성값 있으면 선택된 애들의 아이디값만 가져와  
	$("#save-newCalendar-button").on("click", function() {
		var calendarCheckboxId = 'calendar-' + calendarNo;

		var newCalendar = document.createElement("div");
		newCalendar.className = "calendar-checkbox";
		newCalendar.innerHTML = ` 
    <input type="checkbox" id="${calendarCheckboxId}" class="calendar-checkbox-input" data-owner="${newCalendarOwner}"> 
    <span>${newCalendarName} (${newCalendarOwner})</span>
    <button class="admin-calendar-btn">EDIT</button>
`;
		$("#calendar-list").append(newCalendar);
	});

	function edit (calendarNo){
		console.log(calendarNo);
	};

	// 사용자의 모든 캘린더 불러오기
	$.ajax({
		"url": "/Calendar_ReadAction",
		"method": "POST"
	}).done(function(calendarList) {
		calendarList.forEach(calendar => {
			const name = calendar.name;
			const owner = calendar.owner;
			var calendarNo = calendar.no;
			$("#calendar-list").append(`
                <div class="calendar-checkbox">
                    <input type="checkbox" id="${name}" class="calendar-checkbox-input" data-owner="${owner}">
                    <span>${name} (${owner})</span>
                    <button class="admin-calendar-btn" id="${calendarNo}" onclick="edit(this.id)")>EDIT</button>
                </div>`
			);
		console.log(calendarNo);
		});
	}).fail(function() {
		console.error("fail read calendars");
	});

	var id_check = null; 
	
	// 캘린더 관리 팝업 열 때 calendarNo를  id_check에 가져오기
	$(document).on("click", ".admin-calendar-btn", function() {
	    $("#admin-newCalendar-popup").fadeIn();
	
	    var calendarName = $(this).prev().prev().attr('id');
	    document.getElementById("newCalendar-popup-title").innerHTML = calendarName;
	    
	    id_check = $(this).attr("id"); // id_check 업데이트
	    console.log(id_check);
	});

	// 캘린더 관리 팝업 닫기
	$(document).on("click", "#close-newCalendar-button", function(e) {
		e.preventDefault();
		$("#admin-newCalendar-popup").fadeOut();
	});

	// 캘린더 삭제 버튼
	$("#delete-calendar-button").on("click", function() {
	    var calendarId = id_check; // id_check 사용
	
	    $.ajax({
	        url: "/DeleteCalendar",
	        method: "POST",
	        data: { calendarId: calendarId }
	    })
		location.href = "calendar";
	});

	
	// 캘린더 이름 수정하기 버튼
	$("#update-calendar-button").on("click", function() {
	    var calendarId = id_check; // id_check 사용
	    var newCalendarName = $("#new-calendar-name-input").val();

		let obj = {
			"calendarId" : calendarId,
			"newCalendarName" : newCalendarName
		}
	
	    $.ajax({
	        url: "/UpdateCalendar",
	        method: "POST",
	        data: obj
	    })

		location.href = "calendar";
	});

	// 사용자 공유 기능							수정 : email getPrmeter 받아와서 배열 만들고 배열 순회하면서 그거 다 shere_Calendar 테이블에 저장
	$("#share-button").on("click", function() {
		var userEmail = $("#user-share-input").val();

		if (userEmail.trim() !== "") {
			var emails = userEmail.split("/"); // 이메일을 '/'로 분리하여 배열로 저장

			emails.forEach(function(email) {
				var listItem = $("<div>", { class: "user-share-list-item" });
				listItem.text(email);

				var deleteButton = $("<button>", { class: "delete-share-button" });
				deleteButton.text("(멤버 삭제)");
				deleteButton.on("click", function() {
					listItem.remove();
				});

				listItem.append(deleteButton);
				$(".user-share-list").append(listItem);

				$.ajax({
					url: '/ShareCalendarRequest',
					type: 'POST',
					data: { email: email, calendarNo: calendarId },
					success: function(data) {
						console.log("Success: ", data);
					},
					error: function(error) {
						console.log("Error: ", error);
					}
				});
			});
		}
	});
	// <<<<<<<<<<<<<<< task >>>>>>>>>>>>>>>
	// task 추가 버튼
	$("#add-task-button").on("click", function() {
		$("#add-task-popup").fadeIn();
	});

	// Task 이름 중복 확인
	function taskNameDupl(taskName) {
		var taskElements = $(".fc-event-main");
		var duplicate = false;

		taskElements.each(function() {
			if ($(this).text().trim().toLowerCase() === taskName.trim().toLowerCase()) {
				duplicate = true;
				return false; // 중복 발견 시 반복문 종료
			}
		});

		return duplicate;
	}


	// 추가한 task 저장 버튼
	$("#save-task-button").on("click", function() {
		var taskName = $("#newTaskTitle").val();

		if (taskName.trim() !== "") {
			if (taskNameDupl(taskName)) {
				alert("이미 존재하는 Task입니다.");
			} else {
				var newEvent = document.createElement("div");
				newEvent.className = "fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event";
				newEvent.innerHTML = `
          <div class='fc-event-main'>${taskName}</div>
          <button class="admin-task-btn">Task 관리</button>
        `;
				containerEl.insertBefore(newEvent, containerEl.lastChild);
				$("#add-task-popup").fadeOut();
				$("#newTaskTitle").val("");

				// Task 관리 버튼 클릭 시 관리 팝업 표시
				$(newEvent).find(".admin-task-btn").on("click", function() {
					var taskName = $(this).siblings(".fc-event-main").text();
					$("#task-popup-title").text(taskName);
					$("#admin-task-popup").fadeIn();
				});
			}
		}
	});

	/* 사용자 TASK 불러오기 */
	/* $.ajax({
		"url": `/Task_Read`,
		"method": "GET"
	}).done(function(response) {
		console.log(response);
		response.forEach(task => {
			const name = task.name;


			var newEvent = document.createElement("div");
			newEvent.className = "fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event";
			newEvent.innerHTML = `
					<div class='fc-event-main'>${name}</div>
					<button class="admin-task-btn">Task 관리</button>
				`;

			containerEl.insertBefore(newEvent, containerEl.lastChild);
			$("#add-task-popup").fadeOut();
			$("#newTaskTitle").val("");

			// Task 관리 버튼 클릭 시 관리 팝업 표시
			$(newEvent).find(".admin-task-btn").on("click", function() {
				var taskName = $(this).siblings(".fc-event-main").text();
				$("#task-popup-title").text(taskName);
				$("#admin-task-popup").fadeIn();
			});

		});

	}).fail(function() {

	}); */


	// Task 관리 팝업 닫기
	$("#close-task-button").on("click", function() {
		$("#admin-task-popup").fadeOut();
	});

	// Task 삭제 기능
	$("#delete-task-button").on("click", function() {
		var taskName = $("#task-popup-title").text();
		var eventEl = $(".fc-event-main").filter(function() {
			return $(this).text() === taskName;
		}).closest(".fc-event");

		if (eventEl) {
			eventEl.remove();
			$("#admin-task-popup").fadeOut();
		}
	});

	// Task 이름 변경 기능
	$("#update-task-button").on("click", function() {
		var newTaskName = $("#new-task-name-input").val();
		var taskName = $("#task-popup-title").text();
		var eventEl = $(".fc-event-main").filter(function() {
			return $(this).text() === taskName;
		});

		if (eventEl && newTaskName.trim() !== "") {
			eventEl.text(newTaskName);
			$("#task-popup-title").text(newTaskName);
			$("#new-task-name-input").val("");
		}

		$("#admin-task-popup").fadeOut();
	});

	// <<<<<<<<<<<<<<< event >>>>>>>>>>>>>>>
	// 전체 일정 저장 &&& 서버에 저장된 이벤트 데이터 가져오기
	// 1. 전체 이벤트 데이터 추출 --> 2. 추출된 데이터를 ajax로 서버에 전송하여 DB에 저장
	$("#save-button").on("click", function() {
		var allEvent = calendar.getEvents();
		console.log(allEvent); // 확인용

		var events = [];
		for (var i = 0; i < allEvent.length; i++) {
			var obj = {
				calendarNo: $("#calendars option:selected").val(), // 선택한 캘린더 no를 어떻게 불러오지
				taskNo: null,
				name: allEvent[i]._def.title,
				email: '', // 세션에 저장된 로그..?
				title: allEvent[i]._def.title,
				content: $("#event-description").val(),
				start: allEvent[i]._instance.range.start.toISOString().split("T")[0], // "start":"2023-07-18T00:00:00.000Z" 파싱 
				end: allEvent[i]._instance.range.end.toISOString().split("T")[0],
				allday: allEvent[i]._def.allday,
			}

			events.push(obj);
		}

		var jsondata = JSON.stringify(events);
		console.log(jsondata);

		$.ajax({
			url: '/EventCreate',
			type: 'post',
			data: { 'events': jsondata },
			dataType: 'json',
			success: function(response) {
				console.log(response);
			},
			error: function(error) {
				console.log(error);
			}
		});
	});

	// 캘린더에 등록된 이벤트 수정
	$('#calendar').on('click', '.fc-daygrid-event', function() {
		var eventElement = this;
		var eventTitle = $(this).find('.fc-event-main').text();
		var isPredefinedTask = $(this).hasClass('predefined-task');
		var eventPopup = $('#event-popup');
		var closeButton = $('#close-event-button');
		var deleteButton = $('#delete-event-button');
		var saveButton = $('#save-event-button');
		var allDayCheckbox = $('#all-day-checkbox');
		var startDateInput = $('#start-date');
		var endDateInput = $('#end-date');
		var eventDescriptionInput = $('#event-description');

		eventPopup.fadeIn();

		// 이벤트 타이틀 입력
		$('#event-title-input').val(eventTitle);

		// 기존 task인 경우 이벤트 타이틀 입력 비활성화 ---> 이거 안되는듯(07/14 02:20)
		if (isPredefinedTask) {
			$('#event-title-input').prop('disabled', true);
		} else {
			$('#event-title-input').prop('disabled', false);
		}

		deleteButton.off('click').on('click', function() {
			eventElement.remove();
			eventPopup.fadeOut();
		});

		closeButton.off('click').on('click', function() {
			eventPopup.fadeOut();
		});

	});

	// 서버에서 저장된 이벤트 데이터 가져오기 ----> 모든 이벤트 불러와짐. 필터링 필요 수정 : 조회하려면 캘린더no랑 이벤트No가 필요한데 두개가 해당해야 이벤트 조회가 가능.
	// share_Event에서 외래키로 받아와서 select * from share_Event where 선택한캘린더번호=?
	$.ajax({
		url: "/EventRequest",
		method: "GET",
		dataType: "json",
		success: function(response) {
			var eventData = response;
			console.log(response);

			for (var i = 0; i < eventData.length; i++) {
				var event = eventData[i];
				var newEvent = {
					id: event.no,
					title: event.title,
					start: event.start,
					end: event.end,
					allDay: event.all_day == "true"
				};

				calendar.addEvent(newEvent);
			}
		},
		error: function(xhr, status, error) {
			console.log("Error: " + error);
		}
	});

});