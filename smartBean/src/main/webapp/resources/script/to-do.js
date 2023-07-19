$(window).on('load', function() {
	document.getElementById('inputDate').value = new Date().toISOString().substring(0, 10);
	printWeek();
});

//진행률 표시
function updateProgress(progressId, check_id) {
	if (check_id != null) {
		var check = 0;

		if ($('#' + check_id).is(":checked")) {
			check = 1;
		}

		let obj = {
			"check": check,
			"no": check_id
		};

		$.ajax({
			"url": `/CheckTodo`,
			type: "POST",
			data: obj
		});

		var checkboxes = document.querySelectorAll('td:nth-child(' + progressId + ') input[type="checkbox"]');
		var progress = document.getElementById('progress' + progressId);
		var total = checkboxes.length;
		var checkedCount = 0;
		for (var i = 0; i < checkboxes.length; i++) {
			if (checkboxes[i].checked) {
				checkedCount++;
			}
		}
		var percentage = (checkedCount / total) * 100;
		progress.innerHTML = '진행률: ' + percentage.toFixed(0) + '%';
	}
}

//일정표 나오기
function printWeek() {
	// LoadTodo
	$.ajax({
		"url": `/LoadTodo`,
		"method": "GET"
	}).done(function (response) {
		var inputDate = document.getElementById("inputDate");
		if (!inputDate.value) {
			var currentDate = new Date();
			var year = currentDate.getFullYear();
			var month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
			var day = currentDate.getDate().toString().padStart(2, '0');
			var today = year + '-' + month + '-' + day;
			inputDate.value = today;
		}

		var date = new Date(inputDate.value);
		var firstDay = new Date(date);
		firstDay.setDate(date.getDate() - date.getDay());
		var days = ["일", "월", "화", "수", "목", "금", "토"];
		var datesAndWeekdays = [];

		for (var i = 0; i < 7; i++) {
			var currentDate = new Date(firstDay);
			currentDate.setDate(firstDay.getDate() + i);
			var dayOfWeek = days[currentDate.getDay()];
			var options = { day: 'numeric', month: 'short' };
			var dateStr = currentDate.toLocaleDateString('ko-KR', options);
			var dateAndWeekdayStr = dateStr + " (" + dayOfWeek + ")";
			datesAndWeekdays.push(dateAndWeekdayStr);
		}

		var table = "<br><table><tr>";
		for (var i = 0; i < datesAndWeekdays.length; i++) {
			table += "<th>" + datesAndWeekdays[i] + 
			
			"<button type='submit' id='button" + (i + 1) + "' onclick='showList(" + (i + 1) + ")'>+</button>" +
                    "<div id='list" + (i + 1) + "' class='hidden'>"+
                    "<form action='CreateTodo' method='POST' >"+
                    "<p>날짜선택</p>" +
                    "<input type='date' id='target_at_add' name='target_at_add' value=''>" +
                    "<p>일정 추가</p>" +
                    "<input type='text' id='content_add' name='content_add' value=''>" +
                    "<button type='submit' id='buttone' onclick='addTask()'>추가 하기</button>" +
                    "</form>"+
		            "</div>"+
		            
			"</th>";
		}

		table += "</tr><tr>";
		for (var i = 0; i < datesAndWeekdays.length; i++) {
			table += "<td>"
			var dayTd = (currentDate.getDate() - 6 + i);
			var checkDate = currentDate.getFullYear() + "-" + 
			((currentDate.getMonth() + 1) < 10 ? "0" + (currentDate.getMonth() + 1) : (currentDate.getMonth() + 1)) + "-" +
			(dayTd < 10 ? "0" + dayTd : dayTd);
			response.forEach(todo => {
				var no = todo.no;
				var content = todo.content;
				var check = todo.check;
				var target_at = todo.target_at;
				
				if (target_at == checkDate) {
					table += '<li>';
					if (check == 0) {
						table += "<input type='checkbox' id='check" + no + "' onclick='updateProgress(" + (i + 1) + ", this.id)'>";
					} else {
						table += "<input type='checkbox' id='check" + no + "' onclick='updateProgress(" + (i + 1) + ", this.id)' checked>";
					}
					table += "<a href='#pop_info_1' id = '" + no + "' class='btn_open' onclick='showPopup(" + (i + 1) + ", \"pop_info_1\", this.id)'>"+content+"</a>" + "</li>";
				}
			});
			table += "<li class = 'add-button'><br><span id='progress" + (i + 1) + "'>진행률: 0%</span></li>"+"</td>";
		}
		table += "</tr></table>";
		document.getElementById("output").innerHTML = table;
		updateProgress();

	}).fail(function () {

	});

}

// 추가버튼 나오기,닫기
function showList(listId) {
    var listElements = [];
    for (var i = 1; i <= 7; i++) {
        listElements.push(document.getElementById('list' + i));
    }

    for (var i = 0; i < listElements.length; i++) {
        if (i === listId - 1) {
            if (listElements[i].style.display === 'block') {
                listElements[i].style.display = 'none';
            } else {
                listElements[i].style.display = 'block';
            }
        } else {
            listElements[i].style.display = 'none';
        }
    }
}


//삭제하기
function deleteTasks() {
	$.ajax({
		"url": `/DeleteTodo`,
		type: "post",
		data: { "todoNo": todoId },
	}).done(function() {		
		location.href='todoList';
	}).fail(function() {
		
	});
}

//수정하기
function editTasks() {
	let obj={
		"no":todoId,
		"target_at":$('#dateInput').val(),
		"content":$('#editText').val(),
	};
	
	$.ajax({
		"url": `/UpdateTodo`,
		type: "post",
		data: obj,
	}).done(function() {		
		location.href='todoList';
	}).fail(function() {
		
	});
}


var todoId;
//팝업 열기
function showPopup(tdIndex, popupId, id_check) {
	todoId = id_check;
	$.ajax({
		"url": `/GetTodo`,
		type: "GET",
		data: { "todoNo": id_check },
	}).done(function(todo) {
		$('#dateInput').val(todo.target_at);
		$('#editText').val(todo.content);
	}).fail(function() {
		
	});
	
	var popup = document.getElementById(popupId);
	popup.style.display = 'block';
    document.body.style.overflow = 'hidden';
	
}

//팝업 닫기
function closePopup() {
	var popups = document.querySelectorAll('.pop_wrap');
	popups.forEach(function (popup) {
		popup.style.display = 'none';
	});
    document.body.style.overflow = 'auto';
}

// Add event listeners for closing popups for each "닫기" button
var closePopupButtons = document.querySelectorAll('.btn_close');
closePopupButtons.forEach(function (button) {
	button.addEventListener('click', function (event) {
		event.preventDefault();
		closePopup();
	});
});

