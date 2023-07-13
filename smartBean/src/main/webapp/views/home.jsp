<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
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

    /* 래퍼 스타일 */
    .edit-member {
        width: 520px;
        height: 500px;
        padding: 40px;
        box-sizing: border-box;
        border: 1px solid #000000;
        border-radius: 6px;
        background-color: #fff;
    }


    /* 제목 스타일 */
    h3 {
        text-align: center;
        /* font-family: 'Lobster', cursive; */
        font-size: 1.5rem;
        color: #d73434;
        margin-bottom: 20px;
        box-sizing: border-box;
        border-bottom: 2px solid #000000;
        margin-bottom: 50px;
    }

    /*버튼 스타일 */
    button {
        color: #fff;
        font-size: 16px;
        background-color: #d73434;
        margin: 20px 15%;
        width: 70%;
        height: 48px;
        padding: 0 10px;
        box-sizing: border-box;
        border: 1px solid #ddd;
        border-radius: 6px;
        font-size: 1.5rem;
    }
</style>


<body>
    <c:if test="${empty sessionScope.log }">
        <c:redirect url="login"></c:redirect>
    </c:if>

	<jsp:include page="/header"></jsp:include>

    <div class="edit-member">


        <h3>${sessionScope.name }님 환영합니다.</h3>

        <form action="UpdateUser" method="POST" id="UpdateUser">
            <button id="submit-btn" onclick="location.href='UpdateUserRequest'">회원정보 수정</button><br>
            <button id="submit-btn" onclick="location.href='Logout'">로그아웃</button><br>
            <button id="submit-btn" onclick="location.href='DeleteUserRequest'">회원탈퇴</button>

        </form>
    </div>
    
    <jsp:include page="/footer"></jsp:include>
</body>

</html>