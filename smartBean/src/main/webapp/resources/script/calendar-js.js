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
			right: 'dayGridMonth,timeGridWeek,timeGridDay'
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

	calendar.render();

	$("#create-calendar-btn").on("click", function() {
		$("#create-calendar-popup").fadeIn();
	});

	$("#save-newCalendar-button").on("click", function() {
		var newCalendarName = $("#newCalendar-name-input").val();
		var newCalendarOwner = $("#newCalendar-owner-input").val();

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

			$("#" + newCalendarName).on("change", function() {
				var isChecked = $(this).prop("checked");
				var calendarName = $(this).attr("id");

				if (isChecked) {
					calendar.addEventSource({
						events: [],
						id: calendarName
					});
				} else {
					calendar.getEventSources().forEach(function(source) {
						if (source.id === calendarName) {
							source.remove();
						}
					});
				}
			});
		}
	});

	$("#save-button").on("click", function() {
		var events = calendar.getEvents();
		console.log(events);
	});

	containerEl.style.overflowY = 'auto';

	$("#add-task-button").on("click", function() {
		$("#add-task-popup").fadeIn();
	});

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

	function taskNameDupl(taskName) {
		var taskNames = $(".fc-event-main").map(function() {
			return $(this).text();
		}).get();
		return taskNames.includes(taskName);
	}
	
// ------------------- 이벤트 수정 
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
