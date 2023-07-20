<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<link rel="shortcut icon" href="/resources/img/favicon.ico">
	<meta property="og:title" content="스마트한 업무관리 ㅣ 똑똑빈">
	<meta property="og:description" content="똑똑한 당신에겐 똑똑한 업무관리가 어울려요.">
	<meta property="og:image" content="/resources/img/metaImg.svg">
	
	
	<title>스마트한 업무관리 | 똑똑빈</title>
	<link href="/resources/style/style.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>

<body>
	<header>
		<div id="logo-space">
        	<a  href="index.jsp"><img src="/resources/img/logo.png"></a>
        	<div class="title"><span class="sub-title">스마트한 업무관리,</span><span class="main-title">똑똑빈</span></div>
        </div>
        <c:if test="${!empty sessionScope.log }">
        <div>
        	<button class="logout-btn" onclick="location.href='home'">${sessionScope.name }님</button>
        	<button class="logout-btn" onclick="location.href='Logout'">로그아웃</button>
        </div>
        </c:if>
    </header>
</body>

</html>