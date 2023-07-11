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

    /* 회원정보 변경 부분 */
    .edit-member {
        width: 400px;
        height: 540px;
        padding: 40px;
        box-sizing: border-box;
        border: 1px solid #000000;
        border-radius: 6px;
        background-color: #fff;
    }

    /* 제목부분 */
    .edit-member>h1 {
        text-align: center;
        font-family: 'Lobster', cursive;
        font-size: 2.5rem;
        color: #d73434;
        margin-bottom: 20px;

    }

    /* 입력 필드 스타일 */
    #UpdateUser>input {
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


    /* 버튼 스타일 */
    #UpdateUser>input[type="button"] {
        color: #fff;
        font-size: 16px;
        background-color: #d73434;
        margin-top: 20px;
    }
</style>

<body>
    <c:if test="${empty sessionScope.log }">
        <c:redirect url="login"></c:redirect>
    </c:if>

    <div class="edit-member">

        <!-- 제목 -->
        <h1>edit member</h1>

        <form action="UpdateUser" method="POST" id="UpdateUser">

            <p>이메일</p>
            <input type="text" id="email" name="email" value="${sessionScope.log }" readonly>
            <p>비밀번호</p>
            <input type="password" id="password" name="password">
            <p>새로운 비밀번호</p>
            <input type="password" id="new_password" name="new_password">
            <p>닉네임</p>
            <input type="text" id="name" name="name" value="${requestScope.name }">

            <input type="submit" value="회원정보 수정">

        </form>

    </div>

    <script src="../resources/script/vaildation_join.js"></script>
</body>

</html> 