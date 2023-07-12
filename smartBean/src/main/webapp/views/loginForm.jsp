<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>

<body>
	<jsp:include page="/header"></jsp:include>

	<main>
		<div class="login-section">
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
	    </div>
	</main>
	
	<jsp:include page="/footer"></jsp:include>

    <script src="../resources/script/vaildation_login.js"></script>
</body>

</html>