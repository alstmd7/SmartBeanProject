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
     
</style>

<body>
	<jsp:include page="/header"></jsp:include>
	
	<main>
		<div class="join-the-membership">

	        <!-- 제목 -->
	        <h1>join-the-membership</h1>
	
			<form action="Join" method="POST" id="Join">
				<input type="text" id="email" name="email" value="${requestScope.email }" placeholder="이메일">
				<input class="error" name="error-dupl" value="${requestScope.dupl} ">
				<input type="password" id="password" name="password" value="${requestScope.password }" placeholder="비밀번호">
				<input type="text" id="name" name="name" value="${requestScope.name }" placeholder="닉네임">
				
				<ul>	
					<li class="error" id="error-email">이메일을 입력해주세요.</li>
					<li class="error" id="error-password">비밀번호를 입력해주세요.</li>
					<li class="error" id="error-name">이름을 입력해주세요.</li>
				</ul>

				<input type="button" value="회원가입" onclick="checkValue(form)">
			</form>
			
	    </div>
	</main>
	
	<jsp:include page="/footer"></jsp:include>
	
	<script src="../resources/script/vaildation_join.js"></script>
</body>
</html>