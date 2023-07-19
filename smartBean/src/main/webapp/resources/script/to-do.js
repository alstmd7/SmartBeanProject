$(window).on('load', function() {
	document.getElementById('inputDate').value = new Date().toISOString().substring(0, 10);
	printWeek();
});

//진행률 표시
/* ***********************************수정 */
function updateProgress(progressId, check_id) {
	if(check_id != null){
		var check = 0; 
	if($('#'+check_id).is(":checked")) {
		check = 1;
	}
	
	let obj={
		"check":check,
		"no":check_id
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
	progress.innerHTML = '진행률: ' + percentage.toFixed(1) + '%';
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
			table += "<th>" + datesAndWeekdays[i] + "</th>";
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
					/* ***********************************수정  */
					if (check == 0) {
						table += "<input type='checkbox' id='check" + no + "' onclick='updateProgress(" + (i + 1) + ", this.id)'>";
					} else {
						table += "<input type='checkbox' id='check" + no + "' onclick='updateProgress(" + (i + 1) + ", this.id)' checked>";
					}
					table += "<a href='#pop_info_1' id = '" + no + "' class='btn_open' onclick='showPopup(" + (i + 1) + ", \"pop_info_1\", this.id)'>"+content+"</a>" + "</li>";
				}

			});

			//일정추가버튼 추가
			table += "<li><br><span id='progress" + (i + 1) + "'>진행률: 0%</span></li>"+
			"<button type='submit' id='button" + (i + 1) + "' onclick='showList(" + (i + 1) + ")'>일정 추가</button>" +
                    "<div id='list" + (i + 1) + "' class='hidden'>"+
                    "<form action='CreateTodo' method='POST' >"+
                    "<p>날짜선택</p>" +
                    "<input type='date' id='target_at_add' name='target_at_add' value=''>" +
                    "<p>일정 추가</p>" +
                    "<input type='text' id='content_add' name='content_add' value=''>" +
                    "<button type='submit' id='button1' onclick='addTask()'>추가 하기</button>" +
                    "</form>"+
		            "</div>"+
                    "</td>";

		}
		table += "</tr></table>";

		document.getElementById("output").innerHTML = table;
		updateProgress();

	}).fail(function () {

	});

}

//추가버튼 나오기
function showList(listId) {
    var listElements = [];
    for (var i = 1; i <= 7; i++) {
        listElements.push(document.getElementById('list' + i));
    }

    for (var i = 0; i < listElements.length; i++) {
        if (i === listId - 1) {
            listElements[i].style.display = 'block';
        } else {
            listElements[i].style.display = 'none';
        }
    }
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
	
}

//팝업 닫기
function closePopup() {
	var popups = document.querySelectorAll('.pop_wrap');
	popups.forEach(function (popup) {
		popup.style.display = 'none';
	});
}

// Add event listeners for closing popups for each "닫기" button
var closePopupButtons = document.querySelectorAll('.btn_close');
closePopupButtons.forEach(function (button) {
	button.addEventListener('click', function (event) {
		event.preventDefault();
		closePopup();
	});
});

function addTask() {
	var target_at_add = document.getElementById('target_at_add').value();
	var content_add = document.getElementById('content_add').value();

	target_at_add.value = '';
	target_at_add.value = '';
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
	
	var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
	if (checkboxes.length === 0) {
		alert('삭제할 일정을 선택해주세요.');
		return;
	}

	var confirmation = confirm('정말로 선택한 일정을 삭제하시겠습니까?');
	if (confirmation) {
		checkboxes.forEach(function (checkbox) {
			var parentLi = checkbox.parentNode;
			parentLi.remove(); // 해당 checkbox를 감싸는 <li> 요소를 삭제
		});
		alert('일정이 삭제되었습니다.');
	}

	/*document.getElementById('delete-btn').click()*/
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
	
	var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
	if (checkboxes.length === 0) {
		alert('수정할 일정을 선택해주세요.');
		return;
	}

	var editText = document.getElementById('editText').value;

	if (editText === '') {
		alert('수정할 내용을 입력해주세요.');
		return;
	}

	var confirmation = confirm('정말로 선택한 일정을 수정하시겠습니까?');
	if (confirmation) {
		checkboxes.forEach(function (checkbox) {
			var parentLi = checkbox.parentNode;
			checkbox.checked = true; // 체크 상태를 유지하기 위해 체크박스를 다시 체크
			parentLi.innerHTML = "<input type='checkbox' name='hobby' value='h1' onclick='updateProgress(1)'> " + editText + "<br>"; // 해당 checkbox를 감싸는 <li> 요소의 내용을 수정
		});
		alert('일정이 수정되었습니다.');
	}
}