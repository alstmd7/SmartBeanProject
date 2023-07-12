<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일잘러의 업무관리 | 똑똑빈</title>
<link href="/resources/style/style.css" rel="stylesheet">
</head>

<body>
	<header>
        <img src="/resources/img/logo.png"><!-- <a href="index.html"></a> -->
        <div class="title"><span class="sub-title">스마트한 업무관리,</span><span class="main-title">똑똑빈</span></div>
        <c:if test="${!empty sessionScope.log }">
        	<button class="logout" ></div>
        </c:if>
    </header>
</body>

</html>