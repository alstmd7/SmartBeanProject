var calendar = null;
// 커밋 테스트
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
	// 캘린더 만들기
	$("#create-calendar-btn").on("click", function() {
		$("#create-calendar-popup").fadeIn();
	});

	// 캘린더 생성 정보 저장 및 관리 버튼 추가
	$("#save-newCalendar-button").on("click", function() {
		var newCalendarName = $("#newCalendar-name-input").val();
		var newCalendarOwner = $("#newCalendar-owner-input").val();

		if (newCalendarName.trim() !== "" && newCalendarOwner.trim() !== "") {
			var newCalendar = document.createElement("div");
			newCalendar.className = "calendar-checkbox";
			newCalendar.innerHTML = `
        <input type="checkbox" id="${newCalendarName}" class="calendar-checkbox-input" data-owner="${newCalendarOwner}">
        <span>${newCalendarName} (${newCalendarOwner})</span>
        <button class="admin-calendar-btn">캘린더 관리</button>
      `;

			$("#calendar-list").append(newCalendar);
			$("#create-calendar-popup").fadeOut();
			$("#newCalendar-name-input").val("");
			$("#newCalendar-owner-input").val("");

			$("#" + newCalendarName).on("change", function() {
				var isChecked = $(this).prop("checked");
				var calendarName = $(this).attr("id");

				/* if (isChecked) {
				  // 캘린더에 이벤트 소스 추가
				} else {
				  // 캘린더에서 이벤트 소스 제거
				} */
			});

			// 캘린더 관리 버튼 클릭 시 관리 팝업 표시
			$(newCalendar).find(".admin-calendar-btn").on("click", function() {
				$("#admin-newCalendar-popup").fadeIn();
			});

			// 캘린더 관리 팝업에 생성한 캘린더 이름 표시
			$("#newCalendar-popup-title").text(newCalendarName);
		}
	});


	// 사용자의 모든 캘린더 불러오기
	$.ajax({
		"url": "/Calendar_ReadAction",
		"method": "POST"
	}).done(function(calendarList) {
		calendarList.forEach(calendar => {
			const name = calendar.name;
			const owner = calendar.owner;
			$("#calendar-list").append(`
                <div class="calendar-checkbox">
                    <input type="checkbox" id="${name}" class="calendar-checkbox-input" data-owner="${owner}">
                    <span>${name} (${owner})</span>
                    <button class="admin-calendar-btn">캘린더 관리</button>
                </div>`
			);
		});
	}).fail(function() {
		console.error("fail read calendars");
	});

	// 캘린더 관리 팝업 열기
	$(document).on("click", ".admin-calendar-btn", function() {
		$("#admin-newCalendar-popup").fadeIn();
	});


	// 캘린더 관리 팝업 닫기 ---> UpdateCalendar 500 에러
	$("#close-newCalendar-button").off("click").on("click", function() {
		$("#admin-newCalendar-popup").fadeOut();
	});

	// 사용자 공유 기능
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
			});

			$.ajax({
				url: "/ShareCalendar_RequestAction", // 서버의 URL
				method: "POST", // POST 메소드 사용
				data: { email: userEmail }, // 전송할 데이터 (이메일 정보)
				success: function(response) {
					// 서버에서의 처리가 성공적으로 완료된 경우
					console.log("이메일 정보 전송 및 처리 완료");

				},
				error: function(xhr, status, error) {
					// 요청이 실패한 경우
					console.log("AJAX 요청 실패: " + error);
				}
			});
		}
	});


	// 캘린더 삭제 기능
	 $("#delete-calendar-button").on("click", function() {
		var calendarId = $(".calendar-checkbox-input:checked").attr("id");

		if (calendarId) {
			// 캘린더 삭제
			$("#" + calendarId).parent().remove();
		}
	}); 

// 캘린더 삭제 버튼 클릭 시 이벤트 핸들러
$(document).on("click", "#delete-calendar-button", function() {
	var selectedCheckboxes = $(".calendar-checkbox-input:checked");  // 체크된 모든 체크박스 가져오기
	if (selectedCheckboxes.length === 0) {  // 체크된 체크박스가 없을 경우
		alert('삭제할 캘린더를 선택해주세요.');
		return;
	}
	selectedCheckboxes.each(function() {  // 각 체크된 체크박스에 대해
		var calendarId = $(this).attr("id");  // 캘린더 ID 가져오기
		$.post("/Calendar_DeleteAction", { calendarId: calendarId }, function(response) {
			alert(response);  // 응답 메시지 표시
			location.reload();  // 페이지 새로 고침
		});
	});
});

	
	// 캘린더 이름 변경 기능
	/* $("#update-calendar-button").on("click", function() {
		var newCalendarName = $("#new-calendar-name-input").val();
		var calendarSpan = $("#newCalendar-popup-title").siblings("span");

		if (newCalendarName.trim() !== "") {
			calendarSpan.text(newCalendarName);
			$("#new-calendar-name-input").val("");
		}
	}); */


	$("#update-calendar-button").on("click", function() {
		var calendarId = $(this).data("calendar-id");  // 수정 버튼에 설정된 캘린더 ID 가져오기
		var newCalendarName = $("#new-calendar-name-input").val();  // 새로운 캘린더 이름 가져오기

		if (newCalendarName.trim() !== "") {
			$.ajax({
				type: "POST",
				url: "/Calendar_UpdateAction",
				data: {
					"calendarId": calendarId,  // 수정 버튼에 설정된 캘린더 ID 전달
					"newCalendarName": newCalendarName
				}
			});
		} else {
			alert("올바른 캘린더 ID와 새로운 캘린더 이름을 입력해주세요." + calendarId);
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
	$.ajax({
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

	});


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

	// 서버에서 저장된 이벤트 데이터 가져오기 ----> 모든 이벤트 불러와짐. 필터링 필요
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