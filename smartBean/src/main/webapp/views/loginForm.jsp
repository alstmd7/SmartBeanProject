<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert title here</title>
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
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

    /* 로그인 래퍼 스타일 */
    .login-wrapper {
        width: 400px;
        height: 400px;
        padding: 40px;
        box-sizing: border-box;
        border: 1px solid #000000;
        border-radius: 6px;
        background-color: #fff;
    }

    /* 로그인 래퍼 내 제목 스타일 */
    .login-wrapper>h2 {
        text-align: center;
        font-family: 'Lobster', cursive;
        font-size: 2.5rem;
        /* color: #6A24FE; */
        color: #d73434;
        margin-bottom: 20px;

    }

    /* 로그인 폼 내 입력 필드 스타일 */
    #login-form>input {
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

    /* 로그인 폼 내 입력 필드의 플레이스홀더 스타일 */
    #login-form>input::placeholder {
        color: #080808;
    }

    /* 로그인 폼 내 제출 버튼 스타일 */
    #login-form>input[type="submit"] {
        color: #fff;
        font-size: 16px;
        background-color: #d73434;
        margin-top: 20px;
    }

    /* 로그인 폼 내 체크박스 스타일 */
    #login-form>input[type="checkbox"] {
        display: none;
    }

    /* 로그인 폼 내 체크박스 레이블 스타일 */
    #login-form>label {
        color: #999999;
    }

    /* 로그인 폼 내 체크박스 스타일 */
    #login-form input[type="checkbox"]+label {
        cursor: pointer;
        padding-left: 26px;
        background-image: url("checkbox.png");
        background-repeat: no-repeat;
        background-size: contain;
    }

    /*체크박스 색상변경*/
    :root {
        accent-color: red;
    }

    /* 로그인 폼 내 체크박스 선택된 스타일 */
    #login-form input[type="checkbox"]:checked+label {
        background-image: url("checkbox-active.png");
        background-repeat: no-repeat;
        background-size: contain;

    }

    /* 비밀번호 찾기 링크 스타일 */
    .findPassword a {
        color: #003569;
        font-size: 12px;
        text-decoration: none;
    }
</style>

<body>
	<section>
		<!-- 로그인 래퍼 -->
	    <div class="login-wrapper">
	        <!-- 제목 -->
	        <h2>Smart bin</h2>
	        <!-- 로그인 폼 -->
	        <form method="post" action="Login" id="login-form">
	            <!-- 사용자 이름/이메일 입력 필드 -->
	            <input type="text" id="email" name="email" placeholder="이메일">
	            <!-- 비밀번호 입력 필드 -->
	            <input type="password" id="password" name="password" placeholder="비밀번호">
	            <!-- 아이디 저장 체크박스 -->
	            <label for="remember-check">
	                <input type="checkbox" id="remember-check">아이디 저장하기
	            </label>
	            <ul>
					<li class="error" id="error-email">이메일을 입력해주세요.</li>
					<li class="error" id="error-password">비밀번호를 입력해주세요.</li>
				</ul>
	            <!-- 로그인 버튼 -->
	            <input type="button" value="로그인" onclick="checkValue(form)">
	        </form>
	
	        <!-- 비밀번호 찾기 링크 -->
	        <div class="findPassword">
	            <a href="">비밀번호를 잊으셨나요?</a>
	        </div>
	    </div>
	</section>

    <script src="../resources/script/vaildation_login.js"></script>
</body>

</html>