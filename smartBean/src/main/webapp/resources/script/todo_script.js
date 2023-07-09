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
                    "<input type='checkbox' name='hobby' value='h1' onchange='updateProgress(this)'> 일정1<br>"+
                    "<input type='checkbox' name='hobby' value='h2' onchange='updateProgress(this)'> 일정2<br>"+
                    "<input type='checkbox' name='hobby' value='h3' onchange='updateProgress(this)'> 일정3"+
                    "<li class='progress'>진행률: 0%</li>"+"</td>";
                }

            }
            table += "</tr></table>";
           
            document.getElementById("output").innerHTML = table; // 결과 출력
        }

        function updateProgress(checkbox) {
            var checkboxes = document.getElementsByName('hobby');
            var progressElement = checkbox.parentNode.querySelector('.progress');
            var checkedCount = 0;

            for (var i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].checked) {
                    checkedCount++;
                }
            }

            var progressPercentage = (checkedCount / checkboxes.length) * 100;
            progressElement.textContent = "진행률: " + progressPercentage.toFixed(0) + "%";

            if (checkedCount === checkboxes.length) {
                progressElement.textContent = "진행률: 100%";
            }
        }