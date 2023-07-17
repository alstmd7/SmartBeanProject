<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
    <title>회원탈퇴</title>
</head>

<body>
	<c:if test="${empty sessionScope.log }">
        <c:redirect url="login"></c:redirect>
    </c:if>
	
	<jsp:include page="/header"></jsp:include>

    <main>
        <div class="delete-box">
        	
        	<div class="text-title">
				<h2>이용약관</h2>
			</div>
			<div class="text_content">
				<p class="title">제6조 [회원정보의 변경 및 탈퇴]</p>
				<p>① 회원은 “회사”의 웹사이트 “내 정보 관리” 페이지에서 언제든지 자신의 개인정보를 열람하고 수정할 수
					있습니다.</p>
				<p>② 회원이 전항의 변경사항을 수정하지 않아 발생한 불이익에 대하여 회사는 책임지지 않습니다.</p>
				<p>③ 회원은 “회사”의 웹사이트 “내 정보 관리” 페이지에서 언제든지 탈퇴 신청이 가능하며, “회사”는 즉시 또는
					7일 이내 탈퇴를 처리합니다.</p>
				<br />
			</div>
			<form action="DeleteUser" method="POST">
				<div>
					<div class="delete-title">
						<h2>회원정보 확인</h2>
					</div>
					<div class="input-box">
						<label for="email">이메일</label> <input type="text" id="email"
							name="email" readonly value="${sessionScope.log }">
					</div>
					<div class="input-box">
						<label for="password">비밀번호</label> <input type="password"
							id="password" name="password" placeholder="비밀번호">
					</div>
				</div>
				
				<div class="text_content">
					<p>
						<input type="checkbox" id="deleteAgree"> <label
							for="agree">위 내용에 동의하십니까?</label>
					</p>
				</div>

				<button id="delete-btn" class="delete-btn" disabled>회원탈퇴</button>
			</form>
		</div>	
    </main>

	<script src="../resources/script/vaildation_delete.js"></script>
	
	<jsp:include page="/footer"></jsp:include>
</body>

</html>