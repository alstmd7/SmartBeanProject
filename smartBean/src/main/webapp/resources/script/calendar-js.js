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

	var calendar = new Calendar(calendarEl, {
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


	// 캘린더 관리 팝업 닫기
	$("#close-newCalendar-button").on("click", function() {
		$("#admin-newCalendar-popup").fadeOut();
	});

	// 사용자 공유 기능
	$("#share-button").on("click", function() {
		var userEmail = $("#user-share-input").val();

		if (userEmail.trim() !== "") {
			var listItem = $("<div>", { class: "user-share-list-item" });
			listItem.text(userEmail);

			var deleteButton = $("<button>", { class: "delete-share-button" });
			deleteButton.text("(멤버 삭제)");
			deleteButton.on("click", function() {
				listItem.remove();
			});

			listItem.append(deleteButton);
			$(".user-share-list").append(listItem);
		}
	});

	// 캘린더 삭제 기능  ----------> 안됨 
	$("#delete-calendar-button").on("click", function() {
		var calendarId = $(".calendar-checkbox-input:checked").attr("id");

		if (calendarId) {
			// 캘린더 삭제
			$("#" + calendarId).parent().remove();
		}
	});

	// 캘린더 이름 변경 기능 ----------> 안됨 
	$("#update-calendar-button").on("click", function() {
	  var calendarId = $(".calendar-checkbox-input:checked").attr("id");
	  var newCalendarName = $("#new-calendar-name-input-" + calendarId).val();
	  var calendarSpan = $("#" + calendarId).siblings("span");
	
	  if (newCalendarName.trim() !== "") {
	    calendarSpan.text(newCalendarName);
	    $("#new-calendar-name-input-" + calendarId).val("");
	  }
	});


	// 캘린더 전체 저장 버튼
	$("#save-button").on("click", function() {
		var events = calendar.getEvents();
		console.log(events);
	});

	containerEl.style.overflowY = 'auto';
	
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
		var taskName = $("#task-name-input").val();

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
				$("#task-name-input").val("");

				// Task 관리 버튼 클릭 시 관리 팝업 표시
				$(newEvent).find(".admin-task-btn").on("click", function() {
					var taskName = $(this).siblings(".fc-event-main").text();
					$("#task-popup-title").text(taskName);
					$("#admin-task-popup").fadeIn();
				});
			}
		}
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

		$('#event-title-input').val(eventTitle);

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

		saveButton.off('click').on('click', function() {
			var newEventTitle = $('#event-title-input').val();
			var allDay = allDayCheckbox.prop('checked');
			var startDate = allDay ? moment(startDateInput.val()) : moment(startDateInput.val() + 'T' + eventElement.start.format('HH:mm:ss'));
			var endDate = allDay ? moment(endDateInput.val()).add(1, 'day') : moment(endDateInput.val() + 'T' + eventElement.end.format('HH:mm:ss'));
			var description = eventDescriptionInput.val();

			if (isPredefinedTask) {
				// 기존 task인 경우 이벤트 수정하지 않고 팝업 닫기
				eventPopup.fadeOut();
				return;
			}

			// 이벤트 이름 수정
			$(eventElement).find('.fc-event-main').text(newEventTitle);

			eventElement.title = newEventTitle;
			eventElement.start = startDate;
			eventElement.end = endDate;
			eventElement.allDay = allDay;
			eventElement.extendedProps = eventElement.extendedProps || {};
			eventElement.extendedProps.description = description;

			console.log('Updated Event:', eventElement);

			eventPopup.fadeOut();
		});
	});

});