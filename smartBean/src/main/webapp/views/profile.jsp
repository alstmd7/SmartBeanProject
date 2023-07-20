<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<style>
.profile {
	float: left;
	width: 450px;
	margin: 0 1%;
	border: 1px solid #bebebe;
	border-radius: 10px;
	margin-bottom: 20px;
	transition: transform 0.3s ease;
	padding: 30px;
	box-sizing: border-box; 
}

.profile:hover {
	transform: translateY(-5px);
}

.profile img {
	display: block;
	width: 100%;
	border-radius: 10px;
}

.profile ul {
	padding: 15px;
	background-color: #f0f0f0;
	list-style: none;
	text-align: left;
}

.profile li {
	margin-bottom: 10px;
	height: 30px;
	line-height: 30px;
}

}
.profile li:last-child {
	margin-bottom: 0;
}

.profile a {
	color: black;
}

img {
	width: 100%;
	height: 500px;
}

.img {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-wrap: wrap;
	gap: 40px;
}

strong {
	font-size: 1.3rem;
	color: #000069;
}

#icon {
	width: 30px;
	height: 30px;
	margin-right: 10px;
}

.info-row {
	display: flex;
	align-items: center;
	margin-bottom: 10px;
}

.info-row:last-child {
	margin-bottom: 0;
}

.info-row img {
	width: 30px;
	height: 30px;
	margin-right: 10px;
}

.info-row a {
	color: black;
}
</style>
</head>

<body>
	<jsp:include page="/header"></jsp:include>
	<main>
		<div class="img">

			<div class="profile-box">
				<div class="profile">
					<img src="../resources/img/bsm.jpg" alt="My Image">
					<ul>
						<li><strong>백승민(Seungmin Baek)</strong></li>
						<div class="info-row">
							<img src="../resources/img/Email.png" alt="My Image" id="icon">
							<span>qortmdals120@naver.com</span>
						</div>
						<div class="info-row">
							<img src="../resources/img/GitHub.png" alt="My Image" id="icon">
							<a href="https://github.com/alstmd7/SmartBeanProject.git"
								target="_blank">https://github.com/JangJaeWone</a>
						</div>
						<div class="info-row">
							<img src="../resources/img/Phone.png" alt="My Image" id="icon">
							<span>010-8308-2525</span>
						</div>
					</ul>
				</div>
			</div>

			<div class="profile-box">
				<div class="profile">
					<img src="../resources/img/hhs.jpg" alt="My Image">
					<ul>
						<li><strong>한희수(Heesoo Han)</strong></li>
						<div class="info-row">
							<img src="../resources/img/Email.png" alt="My Image" id="icon">
							<span>juntu09@gmail.com</span>
						</div>
						<div class="info-row">
							<img src="../resources/img/GitHub.png" alt="My Image" id="icon">
							<a href="https://github.com/hee-duck/sallye" target="_blank">https://github.com/hee-duck/sallye</a>
						</div>
						<div class="info-row">
							<img src="../resources/img/Phone.png" alt="My Image" id="icon">
							<span>010-7220-8935</span>
						</div>
					</ul>
				</div>
			</div>


			<div class="profile-box">
				<div class="profile">
					<img src="../resources/img/jjw.jpg" alt="My Image">
					<ul>
						<li><strong>장재원(Jaewon Jang)</strong></li>
						<div class="info-row">
							<img src="../resources/img/Email.png" alt="My Image" id="icon">
							<span>lokcdown7739@naver.com</span>
						</div>
						<div class="info-row">
							<img src="../resources/img/GitHub.png" id="icon"> <a
								href="https://github.com/JangJaeWone" target="_blank">https://github.com/JangJaeWone</a>
						</div>
						<div class="info-row">
							<img src="../resources/img/Phone.png" id="icon"> <span>010-7709-2808</span>
						</div>
					</ul>
				</div>
			</div>


		</div>
	</main>

	<jsp:include page="/footer"></jsp:include>
</body>

</html>