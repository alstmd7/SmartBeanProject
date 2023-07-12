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
    progress.innerHTML = '진행률: ' + percentage.toFixed(1)*2 + '%';

    if (percentage === 100) {
        for (var i = 0; i < checkboxes.length; i++) {
            checkboxes[i].disabled = true;
        }
    }
}

function printWeek() {
    var inputDate = document.getElementById("inputDate");
    
    var searchSplit = inputDate.value.split("-");
	const search = "" + searchSplit[1] + searchSplit[2] + searchSplit[3];
	if (search !== "") {
		$.ajax({
			"url": `/LoadTodoAction?query=${search}`,
			"method": "GET"
		}).done(function(response) {
			$('#contents-container').empty();

			const list = response;
			console.log('typeof list : ', typeof list);
			
			if(typeof list === 'string') {
				location.href = "login";
			} else {
				list.forEach(book => {
					// 썸네일
					const no = book.no;
					// 제목 
					const title = book.title;
					// 저자
					const author = book.author;
					// 출판사 
					const publisher = book.publisher;
					// 판매가 
					const price = book.price;
					// 상세페이지
					const url = book.url;
	
					if (thumb !== not_found && thumb !== "") {
						$('#output').append(
							`<div class="book">
	                                <a href="${url}">
	                                    <img src="${thumb}">
	                                </a>
	                                <p class="book-title">${no}</p>
	                                <p><span class="book-author">${author}</span> | <span class="book-publisher">${publisher}</span></p>
	                                <p><span class="book-price">${price}</span>원</p>
	                            </div>`
						);
					}
				});
			}

		}).fail(function() {
			location.href = "login";
		});
	}

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
        if (i == 3) {
            table += "<td class='Annual'>" + "<li>연차</li>" + "</td>";
        } else {
            table += "<td>" +
                "<li>"+"<input type='checkbox' name='hobby' value='h1' class='none'>"+"<input type='checkbox' name='hobby' value='h1' onclick='updateProgress(" + (i + 1) + ")'> 일정1" +"</li><br>"+
                "<li>"+"<input type='checkbox' name='hobby' value='h2' class='none'>"+"<input type='checkbox' name='hobby' value='h2' onclick='updateProgress(" + (i + 1) + ")'> 일정2" +"</li><br>"+
                "<li>"+"<input type='checkbox' name='hobby' value='h3' class='none'>"+"<input type='checkbox' name='hobby' value='h3' onclick='updateProgress(" + (i + 1) + ")'> 일정3" +"</li><br>"+
                "<li>"+"<span id='progress" + (i + 1) + "'>진행률: 0%</span>" + "</li>"+"</td>";
        }
    }
    table += "</tr></table>";

    document.getElementById("output").innerHTML = table;
    updateProgress();
    
    
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

    alert('일정이 추가되었습니다.');
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