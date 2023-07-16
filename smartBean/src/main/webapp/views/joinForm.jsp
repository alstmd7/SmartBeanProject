<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<title>회원가입</title>
</head>

<body>
	<c:if test="${requestScope.message == '동일한 아이디가 존재합니다.'}">
		<script>
			alert("${requestScope.message}");
		</script>
	</c:if>
	<jsp:include page="/header"></jsp:include>
		
	<main>
		<div class="edit-member">
	        <!-- 제목 -->
	        <h1>join-the-membership</h1>
	
			<form action="Join" method="POST" class="member-form">
				<div>
					<label for="email">이메일</label> <input type="text" id="email"
						name="email" value="${requestScope.email }" placeholder="이메일">	
				</div>
				<span class="error" id="error-email">이메일을 입력해주세요.</span>
				<div>
					<label for="name">이름</label> <input type="text" id="name"
						name="name" value="${requestScope.name }" placeholder="이름">
				</div>
				<span class="error" id="error-name">이름을 입력해주세요.</span>
				<div>
					<label for="password">비밀번호</label> <input type="password"
						id="password" name="password" value="${requestScope.password }"
						placeholder="비밀번호">
				</div>
				<span class="error" id="error-password">비밀번호를 입력해주세요.</span>
				<div>
					<label for="new_password"></label> <input type="password"
						id="check_password" name="check_password" value="${requestScope.password }"
						 placeholder="비밀번호 확인">
				</div>
				<span class="error" id="error-check_password">입력한 비밀번호가 일치하지 않습니다.</span>
				<input type="button" id="join-btn" value="회원가입"
					onclick="checkValue(form)">
			</form>
			
	    </div>
	</main>
	
	<jsp:include page="/footer"></jsp:include>
	
	<script src="../resources/script/vaildation_join.js"></script>
</body>
</html>