var calendar = null;

$(document).ready(function() {
	// document.getElementById('start-date').value = new Date().toISOString().substring(0, 10) -> timezone 문제 발생 
	// document.getElementById('end-date').value = new Date().toISOString().substring(0, 10)
	// document.getElementById('start-date').value = new Date().toLocaleDateString('en-CA');
	// document.getElementById('end-date').value = new Date().toLocaleDateString('en-CA'); --> 이러면 모든 이벤트에 오늘 날짜가 들어감 

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

	// 모든 캘린더 불러오기
	$.ajax({
		"url": "/Calendar_ReadAction", // 서블릿 URL 변경
		"method": "POST" // 메서드를 POST로 변경
	}).done(function(calendarList) { // 반환된 캘린더 목록 처리
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
		console.error("Error while fetching calendars");
	});

	/* 공유중인 캘린더 불러오기 */
	$.ajax({
		"url": `/LoadShare`,
		"method": "GET"
	}).done(function(response) {
		console.log(response);
		response.forEach(share => {
			const email = share.email;
			const name = share.name;
			console.log(email + ", " + name);
			$('#calendar-list').append(
				`<input type="checkbox" id="${name}" class="calendar-checkbox-input" data-owner="${email}">
	        	<span>${name} (${email})</span>
	        	<button class=".admin-calendar-btn">캘린더 관리</button>`)
		});

	}).fail(function() {

	});

	// 캘린더 관리 팝업 닫기
	$("#close-newCalendar-button").on("click", function() {
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

	// 캘린더 이름 변경 기능
	$("#update-calendar-button").on("click", function() {
		var newCalendarName = $("#new-calendar-name-input").val();
		var calendarSpan = $("#newCalendar-popup-title").siblings("span");

		if (newCalendarName.trim() !== "") {
			calendarSpan.text(newCalendarName);
			$("#new-calendar-name-input").val("");
		}
	});


	// 전체 일정 저장 &&& 서버에 저장된 이벤트 데이터 가져오기
	// 1. 전체 이벤트 데이터 추출 --> 2. 추출된 데이터를 ajax로 서버에 전송하여 DB에 저장
	$("#save-button").on("click", function() {
		var allEvent = calendar.getEvents();
		console.log(allEvent); // 확인용

		var events = [];
		for (var i = 0; i < allEvent.length; i++) {
			var obj = {
				title: allEvent[i]._def.title,
				allday: allEvent[i]._def.allday,
				start: allEvent[i]._instance.range.start.toISOString().split("T")[0], // "start":"2023-07-18T00:00:00.000Z" 파싱 
				end: allEvent[i]._instance.range.end.toISOString().split("T")[0]
			};

			events.push(obj);
		}

		var jsondata = JSON.stringify(events);
		console.log(jsondata);
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

		saveButton.off('click').on('click', function() {
			/* var newEventTitle = $('#event-title-input').val();
			var allDay = allDayCheckbox.prop('checked');
			var startDate = allDay ? moment(startDateInput.val()) : 
    		(eventElement.start ? moment(startDateInput.val() + 'T' + eventElement.start.format('HH:mm:ss')) : moment(startDateInput.val()));
			var endDate = allDay ? moment(endDateInput.val()).add(1, 'day') : 
   			 (eventElement.end ? moment(endDateInput.val() + 'T' + eventElement.end.format('HH:mm:ss')) : moment(endDateInput.val()));

			var description = eventDescriptionInput.val(); */
			// 클릭한 이벤트의 ID 가져오기 ---> 안됨
		    var eventId = eventElement.id;
		
		    // AJAX 요청을 통해 서버로 이벤트 ID 전송하여 날짜 데이터 조회
		    $.ajax({
		      url: '/GetEventDate',
		      method: 'POST',
		      data: { eventId: eventId },
		      dataType: 'json',
		      success: function(response) {
		        // 서버에서 전달받은 날짜 데이터를 input에 설정
		        startDateInput.val(response.startDate);
		        endDateInput.val(response.endDate);
		      },
		      error: function(xhr, status, error) {
		        console.log('Error: ' + error);
		      }
		    });


			// 캘린더에 일정 추가 --->view claendar 필터링 되고 확인해야함 
			/* $("#save-event-button").click(function() {
				var selectedCalendars = $("#calendars").val();
				var title = $("#event-title-input").val();
				var start = $("#start-date").val();
				var end = $("#end-date").val();
				var description = $("#event-description").val();

				$.ajax({
					url: "/Event_CreateAction",
					type: "POST",
					data: {
						"calendars": selectedCalendars,
						"title": title,
						"start": start,
						"end": end,
						"description": description
					},
					success: function(data) {
						if (data.status === "success") {
							// TODO: 성공 처리
						} else {
							// TODO: 에러 처리
						}
					}
				});
			}); */

			// 이벤트 이름 수정 ----> 이거 때문에 오류나서 주석처리/수정해야됨
			/* $(eventElement).find('.fc-event-main').text(newEventTitle);

			eventElement.title = newEventTitle;
			eventElement.start = startDate;
			eventElement.end = endDate;
			eventElement.allDay = allDay;
			eventElement.extendedProps = eventElement.extendedProps || {};
			eventElement.extendedProps.description = description;

			console.log('Updated Event:', eventElement); */

			// 이벤트 삭제 기능 ---> 재확인 필요 
			/* deleteButton.on("click", function() {
			    var eventId = $("#delete-event-popup").data("eventId");
			
			    if (eventId) {
			        // AJAX를 통해 서버에 이벤트 삭제 요청
			        $.ajax({
			            url: "/EventDelete",
			            method: "POST",
						data: { eventNo: eventId },
			            dataType: "text",
			            success: function(response) {
							console.log("Delete success:", response);
			                // 페이지 리로드
			                window.location.reload();
			            },
			            error: function(xhr, status, error) {
			                 console.log("Delete error:", error);
			            }
			        });
			    }
			}); */
			
			// 이벤트 삭제
				// 이벤트가 생성될 때 각 이벤트의 no 값을 해당 DOM 요소의 data 속성에 저장하고, 이벤트를 클릭했을 때 이 data 속성의 값을 가져오는걸로 바꿔야
			$(".fc-event-title.fc-sticky").on("click", function() {
	   			 var eventId = this.no; // 이벤트의 no 값 가져오기
	   			 $("#delete-event-button").data("eventId", eventId); // 가져온 no 값을 delete 버튼의 data 속성에 저장
			});

			$("#delete-event-button").on("click", function() {
				var eventId = $(this).data("eventId"); // 삭제 버튼의 data 속성에서 이벤트의 no 값을 가져옵니다.

				if (eventId) {
					$.ajax({
						url: "/EventDelete",
						type: "POST",
						data: { eventNo: eventId },
						dataType: "json",
						success: function(response) {
							if (response.success) {
								alert(response.message);
								window.location.reload();
							} else {
								alert(response.message);
							}
						},
						error: function(error) {
							console.log("Error: ", error);
						}
					});
				}
			});


			eventPopup.fadeOut();
		});
	});

	// 서버에서 저장된 이벤트 데이터 가져오기 ----> 모든 이벤트 불러와짐. 필터링 필요
	$.ajax({
		url: "/Event_RequestAction",
		method: "GET",
		dataType: "json",
		success: function(response) {
			var eventData = response;
			console.log(response);

			for (var i = 0; i < eventData.length; i++) {
				var event = eventData[i];
				var newEvent = {
					title: event.title,
					start: event.start,
					end: event.end,
					allDay: event.all_day
				};

				calendar.addEvent(newEvent);
			}

			// EventVo의 필드를 추출하고 If 조건으로 사용자가 캘린더에 드래그해서 저장한 영역의 날짜와 DB에서 가져온 날짜가 같으면 append
			var calendarEvents = calendar.getEvents();
			for (var i = 0; i < calendarEvents.length; i++) {
				var event = calendarEvents[i];
				var eventDate = event.start.toISOString().split("T")[0]; // 이벤트의 날짜 정보

				for (var j = 0; j < eventData.length; j++) {
					var eventDataDate = eventData[j].start.split(" ")[0]; // DB에서 가져온 이벤트의 날짜 정보

					if (eventDate === eventDataDate) {
						var eventHtml = `
	                        <a class="fc-daygrid-event fc-daygrid-block-event fc-h-event fc-event fc-event-draggable fc-event-resizable fc-event-start fc-event-end fc-event-future">
	                            <div class="fc-event-main">
	                                <div class="fc-event-main-frame">
	                                    <div class="fc-event-title-container">
	                                        <div class="fc-event-title fc-sticky">${eventData[j].title}</div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="fc-event-resizer fc-event-resizer-end"></div>
	                        </a>
	                    `;
						$('.fc-daygrid-day-events').append(eventHtml);
					}
				}
			}
		},
		error: function(xhr, status, error) {
			console.log("Error: " + error);
		}
	});

});