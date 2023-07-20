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
	
	var eventNum = 0;
	calendar = new Calendar(calendarEl, {
		eventClick: function(info) {
			eventNum = info.event.id;
			$('#event-popup').fadeIn();
			
			if(eventNum != 0) {
				$.ajax({
					url: "/Event_get",
					method: "GET",
					data: {"no":eventNum}
				}).done(function(event){
					$('#task-title').val(event.name);
					$('#event-title-input').val(event.title);
					$('#start-date').val(event.start);
					$('#end-date').val(event.end);
					$('#event-description').val(event.content);
					
					/*var checkCalBox = "";
					$('input:checkbox[name=checkCalBox]').each(function(index) {
						if ($(this).is(":checked") == true) {
							checkCalBox += $(this).val();
						}
					})
					console.log(checkCalBox);

					$.ajax({
						url: "/Event_Update",
						method: "POST",
						data: obj
					})*/
				});
				
			} else {
				console.log("startDate",startDate);
				console.log("taskTitle",taskTitle);

				$('#task-title').val(taskTitle);
				$('#event-title-input').val(taskTitle);
				$('#start-date').val(startDate);
				$('#end-date').val(startDate);
			}
			console.log("Clicked event ID: ", info.event.id);
		},
		
		headerToolbar: {
			left: 'prev,next today',
			center: 'title',
			right: 'dayGridMonth,timeGridWeek,timeGridD4ay'
		},
		editable: true,
		droppable: true,
		displayEventTime: false,
		
		// task를 드래그해서 이벤트 생성
		eventReceive: function(info) {
			var calNo = myCalendarNo;
			var title = info.event.title;
			var startDateObj = info.event.start;
			var startDate = startDateObj.getFullYear() + '-'
				+ ('0' + (startDateObj.getMonth() + 1)).slice(-2) + '-'
				+ ('0' + startDateObj.getDate()).slice(-2);

			// console.log(calNo, title, startDate);
			
			var checkNum = "";

			$('input:checkbox[name=chkbox]').each(function(index) {
				if ($(this).is(":checked") == true) {
					checkNum += $(this).val();
				}
			})

			$.ajax({
				url: "/EventCreate",
				method: "POST",
				data: {
					calendarNo: calNo,
					name: title,
					start: startDate,
					checkNum: checkNum
				}
			})
		}
	});

	// 캘린더 불러오기
	calendar.render();

	// <<<<<<<<<<<<<<< calendar >>>>>>>>>>>>>>>
	// 내 캘린더 번호 가져오기
	var myCalendarNo = null;
	$.ajax({
		url: '/GetMyCalendarNo',
		type: 'POST'
	}).done(function(myCal) {
		myCalendarNo = myCal.no;
	}).fail(function() {

	});

	var newCalendarName = "";

	// 캘린더 만들기
	$("#create-calendar-btn").on("click", function() {
		$("#create-calendar-popup").fadeIn();
	});

	// 캘린더 생성 정보 저장 및 관리 버튼 추가
	$("#save-newCalendar-button").on("click", function() {
		var calendarCheckboxId = 'calendar-' + calendarNo;
		console.log("calendarCheckboxId : ", calendarCheckboxId);
		var newCalendar = document.createElement("div");
		newCalendar.className = "calendar-checkbox";
		newCalendar.innerHTML = ` 
    <input type="checkbox" id="${calendarCheckboxId}" class="calendar-checkbox-input" data-owner="${newCalendarOwner}" onclick="selectBox()"> 
    <span>${newCalendarName} (${newCalendarOwner})</span>
    `;
		if (calendarCheckboxId != myCalendarNo) {
			newCalendar.innerHTML += '<button class="admin-calendar-btn">EDIT</button>';
		}

		$("#calendar-list").append(newCalendar);
	});

	// 사용자의 모든 캘린더 불러오기
	$.ajax({
		"url": "/Calendar_ReadAction",
		"method": "POST"
	}).done(function(calendarList) {
		console.log(calendarList);
		calendarList.forEach(calendar => {
			const name = calendar.name;
			const email = calendar.email;
			var calendarNo = calendar.no;
			var chkBoxCalNo = "checkEvent" + calendar.no;
			if(calendar.p_code != 0){
				$("#calendar-list").append(`
                <div class="calendar-checkbox">
                    <input type="checkbox" name="chkbox" value="${chkBoxCalNo}" class="calendar-checkbox-input" data-owner="${email}" checked>
                    <span>${name} (${email})</span>
                </div>`
				);
			} else {
				$("#calendar-list").append(`
                <div class="calendar-checkbox">
                    <input type="checkbox" name="chkbox" value="${chkBoxCalNo}" class="calendar-checkbox-input" data-owner="${email}" checked>
                    <span>${name} (${email})</span>
                    <button class="admin-calendar-btn" id="${calendarNo}")>EDIT</button>
                </div>`
				);
			}
		});
		
		var checkNum = "";
		
		$('input:checkbox[name=chkbox]').each(function(index) {
			if ($(this).is(":checked") == true) {
				checkNum += $(this).val();
			}
		})
		
		if(checkNum != "") {
			$.ajax({
				url: "/EventRequest",
				method: "GET",
				data: {"checkNum":checkNum}
			}).done(function(response) {
				response.forEach(event => {
					var newEvent = {
						id: event.no,
						name: event.name,
						title: event.title,
						start: event.start,
						end: event.end,
						allDay: event.all_day == "true"
					};
					calendar.addEvent(newEvent);
				});
			});
		}
	
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
			"calendarId": calendarId,
			"newCalendarName": newCalendarName
		};

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
	// 모든 task 불러오기
	$.ajax({
		"url": "/TaskRead",
		"method": "GET"
	}).done(function(responseList) {
		responseList.forEach(task => {
			const name = task.name;
			var taskNo = task.no;
			$("#task-list").append(`
                <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event' data-task_title=${name}>
			<div class='fc-event-main' id='${taskNo}'>${name}</div>`
			);
			// console.log(taskNo);
		});
	}).fail(function() {
		console.error("fail read task");
	});

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
				newEvent.setAttribute("data-task_title", taskName);
				newEvent.innerHTML = `
          <div class='fc-event-main'>${taskName}</div>
        `;
				containerEl.insertBefore(newEvent, containerEl.lastChild);
				$("#add-task-popup").fadeOut();
				$("#newTaskTitle").val("");

				// Task 관리 버튼 클릭 시 관리 팝업 표시
				$(newEvent).find(".fc-event-main").on("click", function() {
					var taskName = $(this).text();
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
	// 서버에서 저장된 이벤트 데이터 가져오기 ---->
	
	/* $(document).on('mousedown', '#main', function(e) {
		console.log('mousedown: ', e.target);
	});


	$('#main').mouseup(function(e) {
		console.log('mouseup: ', e.target);
	}); */


	$("#end-date").on("input", function() {
		endDate = $(this).val();
		console.log("end date:", endDate);
	});

	// 일정 추가할 캘린더의 값을 콘솔에 표시하는 이벤트
	$("#calendars").on("change", function() {
		var selectedOptions = $(this).find("option:selected");
		selectedOptions.each(function() {
			var selectedCalNo = $(this).val();
			var selectedCalName = $(this).text();
			console.log("Selected calendar No:", selectedCalNo);		// 확인용
			console.log("Selected calendar Name:", selectedCalName);	// 확인용 
		});
	});
	
	$('#delete-event-button').off('click').on('click', function() {
		this.remove();
		$('#event-popup').fadeOut();
		$.ajax({
			url: "/EventDelete",
			method: "POST",
			data: {"no":eventNum}
		}).done(function(){
			location.reload(true);
		});
		
	});

	$('#close-event-button').off('click').on('click', function() {
		$('#event-popup').fadeOut();
	});
	
	$('#save-event-button').on('click', function() {
		
		let obj = {
			"no": eventNum,
			"title": $('#event-title-input').val(),
			"start": $('#start-date').val(),
			"end": $('#end-date').val(),
			"content": $('#event-description').val(),
		};

		$.ajax({
			url: "/Event_Update",
			method: "POST",
			data: obj
		}).done(function(){
			/*var checkCalBox = "";
			$('input:checkbox[name=checkCalBox]').each(function(index) {
				if ($(this).is(":checked") == true) {
					checkCalBox += $(this).val();
				}
			})
			console.log(checkCalBox);

			$.ajax({
				url: "/Event_Update",
				method: "POST",
				data: obj
			})*/
			
			location.reload(true);
		});
		
		
	});
	
	

});

