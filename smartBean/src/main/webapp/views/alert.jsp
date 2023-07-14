<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${requestScope.message == '회원정보 수정이 완료되었습니다.'}">
		<script>
			alert("${requestScope.message}");
			location.href="/";
		</script>
	</c:if>
	
	<c:if test="${requestScope.message == '이메일 또는 비밀번호가 올바르지 않습니다.'}">
		<script>
			alert("${requestScope.message}");
			location.href="login";
		</script>
	</c:if>
	
	<c:if test="${requestScope.message == '회원가입이 완료되었습니다. 로그인 후 이용해주세요.'}">
		<script>
			alert("${requestScope.message}");
			location.href="login";
		</script>
	</c:if>
</body>
</html>