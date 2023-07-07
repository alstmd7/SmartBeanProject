document.addEventListener('DOMContentLoaded', function() {
	var Calendar = FullCalendar.Calendar;
	var Draggable = FullCalendar.Draggable;

	var containerEl = document.getElementById('external-events');
	var calendarEl = document.getElementById('calendar');
	var checkbox = document.getElementById('drop-remove');

	// 이벤트(task)
	// -----------------------------------------------------------------
	new Draggable(containerEl, {
		itemSelector: '.fc-event',
		eventData: function(eventEl) {
			return {
				title: eventEl.innerText
			};
		}
	});

	// calendar
	// -----------------------------------------------------------------
	var calendar = new Calendar(calendarEl, {
		headerToolbar: {
			left: 'prev,next today',
			center: 'title',
			right: 'dayGridMonth,timeGridWeek,timeGridDay'
		},
		editable: true,
		droppable: true,
	});

	calendar.render();
	
	// -----------------------------------------------------------------

	// 캘린더 추가 버튼 클릭 이벤트
	$("#create-calendar-btn").on("click", function() {
		$("#create-calendar-popup").fadeIn();
	});

	// 캘린더 추가 저장 버튼 클릭 이벤트
	$("#save-newCalendar-button").on("click", function() {
		var newCalendarName = $("#newCalendar-name-input").val();
		var newCalendarOwner = $("#newCalendar-owner-input").val();
		/* var newCalendarShare = $("#newCalendar-share-input").val().split(","); 
		console.log(newCalendarShare); */

		if (newCalendarName.trim() !== "" && newCalendarOwner.trim() !== "") {
			var newCalendar = document.createElement("div");
			newCalendar.className = "calendar-checkbox";
			newCalendar.innerHTML = `
					<input type="checkbox" id="${newCalendarName}" class="calendar-checkbox-input" data-owner="${newCalendarOwner}">
					<label for="calendar-checkbox-${newCalendarName}">${newCalendarName} (${newCalendarOwner})</label>
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

	// 캘린더 모두 저장하기 버튼 클릭 이벤트
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
	        if (taskNameDupl(taskName)) {
	            alert("이미 존재하는 Task입니다.");
	        } else {
	            var newEvent = document.createElement("div");
	            newEvent.className = "fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event";
	            newEvent.innerHTML = "<div class='fc-event-main'>" + taskName + "</div>";
	            containerEl.insertBefore(newEvent, containerEl.lastChild);
	            $("#add-task-popup").fadeOut();
	            $("#task-name-input").val("");
	        }
	    }
	});
	
	// taskName이 이미 존재하는지 확인하는 함수
	function taskNameDupl(taskName) {
	    var taskNames = $(".fc-event-main").map(function() {
	        return $(this).text();
	    }).get();
	    return taskNames.includes(taskName);
	}



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
		$('#close-event-button').on('click').on('click', function() {
			eventPopup.fadeOut();
		});

		// Delete event button click event
		deleteButton.off('click').on('click', function() {
			eventPopup.fadeOut();
		});

		// Save event button click event
		$('#save-event-button').on('click').on('click', function() {
			eventPopup.fadeOut();
		});
	});

	/* var testChk = true;
	
	if(testChk == true)
		console.log(testChk); */

});