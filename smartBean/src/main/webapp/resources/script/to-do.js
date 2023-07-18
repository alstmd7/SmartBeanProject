$(window).on('load', function() {
	document.getElementById('inputDate').value = new Date().toISOString().substring(0, 10);
	printWeek();
});

var todoId;

function select(id_check) {
	todoId = id_check;
	$.ajax({
		"url": `/GetTodoAction`,
		type: "GET",
		data: { "todoNo": id_check },
	}).done(function(todo) {		
		$('#todoDate').val(todo.target_at);
		$('#todoContent').val(todo.content);
	}).fail(function() {
		
	});
}

function remove(){
	$.ajax({
		"url": `/DeleteTodoAction`,
		type: "post",
		data: { "todoNo": todoId },
	}).done(function() {		
		location.href='todoList';
	}).fail(function() {
		
	});
}

function edit(){
	let obj={
		"no":todoId,
		"target_at":$('#todoDate').val(),
		"content":$('#todoContent').val()
	};
	
	$.ajax({
		"url": `/UpdateTodoAction`,
		type: "post",
		data: obj,
	}).done(function() {		
		location.href='todoList';
	}).fail(function() {
		
	});
}



function printWeek() {
// LoadTodo
$.ajax({
	"url": `/LoadTodo`,
	"method": "GET"
}).done(function(response) {
	
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
			var options = { day: 'numeric' };
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
			var dayTd = (currentDate.getDate()-6 + i);
			var checkDate = currentDate.getFullYear() + "-" + 
			((currentDate.getMonth() + 1) < 10 ? "0" + (currentDate.getMonth() + 1) : (currentDate.getMonth() + 1)) + "-" +
			(dayTd < 10 ? "0" + dayTd : dayTd);

			response.forEach(todo => {
			const no = todo.no;
			const content = todo.content;
			const check = todo.check;
			const target_at = todo.target_at;

			if (target_at == checkDate) {
				
				table += '<li>';
				if (check === 0) {
					table += "<li><input type='checkbox' class='checkTodo' id='" + no + "'>";
				} else {
					table += "<input type='checkbox' class='checkTodo' id='" + no + "'>";
				}
				
				table += "<div onclick='select(this.id)' id='" + no + "'> " + content + "</div></li>";
			}
			
			});

			table += "<li><br><span id='progress" + (i + 1) + "'>진행률: 0%</span></li></td>";

		}
		table += "</tr></table>";

		document.getElementById("output").innerHTML = table;
		
}).fail(function() {

});



}

function showList(listId) {
var list1 = document.getElementById('list1');
var list2 = document.getElementById('list2');
var list3 = document.getElementById('list3');
var list4 = document.getElementById('list4');

var noneElements = document.getElementsByClassName('none');

if (listId === 1) {
    list1.style.display = 'block';
    list2.style.display = 'none';
    list3.style.display = 'none';
    list4.style.display = 'none';
} else if (listId === 2) {
    list1.style.display = 'none';
    list2.style.display = 'block';
    list3.style.display = 'none';
    list4.style.display = 'none';

    // Show elements with class "none"
    for (var i = 0; i < noneElements.length; i++) {
        noneElements[i].style.display = 'block';
    }
} else if (listId === 3) {
    list1.style.display = 'none';
    list2.style.display = 'none';
    list3.style.display = 'block';
    list4.style.display = 'none';
} else if (listId === 4) {
    list1.style.display = 'none';
    list2.style.display = 'none';
    list3.style.display = 'none';
    list4.style.display = 'block';
}
}



function addTask() {
var dateInput = document.getElementById('dateInput');
var taskInput = document.getElementById('text');

var date = dateInput.value;
var task = taskInput.value;

dateInput.value = '';
taskInput.value = '';
}


function deleteTasks() {

	
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



function editTasks() {
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