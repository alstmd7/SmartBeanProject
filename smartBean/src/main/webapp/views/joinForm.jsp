<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<title>회원가입</title>
</head>

<style>
    /* 전체 초기화 스타일 */
    * {
        padding: 0;
        margin: 0;
        border: none;
        box-sizing: border-box;
    }

    /* body 스타일 */
    body {
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 14px;
        font-family: 'Roboto', sans-serif;
        font-family: Arial, sans-serif;
        background-color: #fafafa;
        height: 100vh;
    }

    /* 회원정보 변경 부분 */
    .join-the-membership {
        width: 400px;
        height: 500px;
        padding: 40px;
        box-sizing: border-box;
        border: 1px solid #000000;
        border-radius: 6px;
        background-color: #fff;
    }

    /* 제목부분 */
    .join-the-membership>h1 {
        text-align: center;
        font-family: 'Lobster', cursive;
        font-size: 2.5rem;
        color: #d73434;
        margin-bottom: 20px;

    }

    /* 입력 필드 스타일 */
    #Join>input {
        width: 100%;
        height: 48px;
        padding: 0 10px;
        box-sizing: border-box;
        margin-bottom: 16px;
        border: 1px solid #ddd;
        border-radius: 6px;
        background-color: #F8F8F8;
        font-size: 14px;
    }


    /* 버튼 스타일 */
    #Join>input[type="button"] {
        color: #fff;
        font-size: 16px;
        background-color: #d73434;
        margin-top: 20px;
    }
</style>

<body>
	<section>
		<div class="join-the-membership">

	        <!-- 제목 -->
	        <h1>join-the-membership</h1>
	
			<form action="Join" method="POST" id="Join">
				<p>이메일</p>
				<input type="text" id="email" name="email" value="${requestScope.email }">
				<p>비밀번호</p>
				<input type="password" id="password" name="password" value="${requestScope.password }">
				<p>이름</p>
				<input type="text" id="name" name="name" value="${requestScope.name }">
				
				<ul>	
					<li class="error" id="error-email">이메일을 입력해주세요.</li>
					<li class="error" id="error-password">비밀번호를 입력해주세요.</li>
					<li class="error" id="error-name">이름을 입력해주세요.</li>
				</ul>
				<input class="error" name="error-dupl" value="${requestScope.dupl} }">
				
				<input type="button" value="회원가입" onclick="checkValue(form)">
			</form>
			
	    </div>
	</section>
	
	<script src="../resources/script/vaildation_join.js"></script>
</body>
</html>