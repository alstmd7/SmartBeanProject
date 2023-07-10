function updateProgress(progressId) {
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

    if (percentage === 100) {
        for (var i = 0; i < checkboxes.length; i++) {
            checkboxes[i].disabled = true;
        }
    }
}

function printWeek() {
    var inputDate = document.getElementById("inputDate").value; // 입력한 날짜 가져오기
    var date = new Date(inputDate); // 입력한 날짜로부터 Date 객체 생성
    var firstDay = new Date(date); // 주의 첫 번째 날짜를 저장할 변수
    firstDay.setDate(date.getDate() - date.getDay()); // 입력한 날짜의 요일을 기준으로 주의 첫 번째 날짜 계산
    var days = ["일", "월", "화", "수", "목", "금", "토"]; // 요일을 저장할 배열
    var datesAndWeekdays = []; // 주의 날짜와 요일을 저장할 배열
    for (var i = 0; i < 7; i++) {
        var currentDate = new Date(firstDay);
        currentDate.setDate(firstDay.getDate() + i); // 주의 첫 번째 날짜에 i일을 추가하여 주의 각 날짜 계산
        var dayOfWeek = days[currentDate.getDay()]; // 요일 계산
        var options = { day: 'numeric' };
        var dateStr = currentDate.toLocaleDateString('ko-KR', options); // 한글로 날짜 문자열 생성
        var dateAndWeekdayStr = dateStr + " (" + dayOfWeek + ")"; // 날짜와 요일 문자열 생성
        datesAndWeekdays.push(dateAndWeekdayStr); // 날짜와 요일을 배열에 추가
    }

    var table = "<br><table><tr>";
    for (var i = 0; i < datesAndWeekdays.length; i++) {
        table += "<th>" + datesAndWeekdays[i] + "</th>"; // 날짜와 요일을 테이블 헤더로 추가
    }
    table += "</tr><tr>";
    for (var i = 0; i < datesAndWeekdays.length; i++) {

        if(i == 3){ //예시로 
            table += "<td class='Annual'>" + "<li>연차</li>" + "</td>"; 
        }else{
            table += "<td>"+
                "<input type='checkbox' name='hobby' value='h1' onclick='updateProgress(" + (i + 1) + ")'> 일정1<br>"+
                "<input type='checkbox' name='hobby' value='h2' onclick='updateProgress(" + (i + 1) + ")'> 일정2<br>"+
                "<input type='checkbox' name='hobby' value='h3' onclick='updateProgress(" + (i + 1) + ")'> 일정3<br>"+
                "<span id='progress" + (i + 1) + "'>진행률: 0%</span>"+"</td>";
        }

    }
    table += "</tr></table>";

    document.getElementById("output").innerHTML = table; // 결과 출력
    updateProgress(); // Update progress on checkboxes
}

function showList(listId) {
    var list1 = document.getElementById('list1');
    var list2 = document.getElementById('list2');

    if (listId === 1) {
        list1.style.display = 'block';
        list2.style.display = 'none';
    } else if (listId === 2) {
        list1.style.display = 'none';
        list2.style.display = 'block';
    }
}

function addTask() {
    var dateInput = document.getElementById('dateInput');
    var taskInput = document.getElementById('text');

    var date = dateInput.value;
    var task = taskInput.value;

    // 여기에 일정 추가하는 로직을 구현합니다.
    // 예를 들어, 데이터베이스에 저장하거나 다른 곳에 기록하는 등의 작업을 수행할 수 있습니다.

    // 일정을 추가한 후에 입력 필드를 초기화합니다.
    dateInput.value = '';
    taskInput.value = '';

    alert('일정이 추가되었습니다.');
}

function deleteTask() {
    var dateInput = document.getElementById('dateInput');
    var taskInput = document.getElementById('text');

    var date = dateInput.value;
    var task = taskInput.value;

    // 여기에 일정 삭제하는 로직을 구현합니다.
    // 예를 들어, 데이터베이스에서 해당 일정을 삭제하거나 기록을 업데이트하는 등의 작업을 수행할 수 있습니다.

    // 일정을 삭제한 후에 입력 필드를 초기화합니다.
    dateInput.value = '';
    taskInput.value = '';

    alert('일정이 삭제되었습니다.');
}

const list = document.getElementById('todo');
console.log(todo);

list.forEach(todo => {
	const content = todo.content;
	$('#print').append(
		`<div>
		   	<p>${content}</p>
		</div>`
	);
})


