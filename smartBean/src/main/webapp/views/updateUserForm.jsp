<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <title>Insert title here 회원정보 변경</title>
</head>

<body>
    <c:if test="${empty sessionScope.log }">
        <c:redirect url="login"></c:redirect>
    </c:if>
    
    <jsp:include page="/header"></jsp:include>
	
	<main>
	    <div class="edit-member">
	        <!-- 제목 -->
	        <h1>edit member</h1>

	        <form action="UpdateUser" method="POST" id="UpdateUser">
	        	<div>
		        	<label for="email">이메일</label>
		            <input type="text" id="email" name="email" readonly value="${sessionScope.log }">
	            </div>
	            <div>
		            <label>닉네임</label>
		            <input type="text" id="name" name="name" value="${sessionScope.name }">
	            </div>
	            <div>
	            	<label>비밀번호</label>
	            	<input type="password" id="password" name="password" placeholder="현재 비밀번호">
	            </div>
	            <div>
	            	<label></label>
		        	<input type="password" id="new_password" name="new_password" placeholder="새로운 비밀번호">
		        </div>
	            <input type="submit" value="회원정보 수정">
	        </form>
	        
	        <button id="deleteUser-btn" onclick="location.href='DeleteUserRequest'">회원탈퇴</button>
	    </div>
    </main>
    
    <jsp:include page="/footer"></jsp:include>

    <script src="../resources/script/vaildation_join.js"></script>
</body>

</html> 