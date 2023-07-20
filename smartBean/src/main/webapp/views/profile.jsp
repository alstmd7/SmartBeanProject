<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        html {
            font-size: 16px;
        }
        body {
            font-family: Arial, sans-serif;
            margin: 0 auto;
            /* max-width: 800px; */
            padding: 20px;
            display: flex; 
            flex-wrap: wrap; 
            justify-content: space-between; 
        }
        .profile {
            float: left;
            width: 28%;
            margin: 0 1%;
            margin-bottom: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            transition: transform 0.3s ease;
        }
        .profile:hover {
            transform: translateY(-5px);
        }
        .profile img {
            display: block;
            width: 100%;
            border-radius: 10px 10px 0 0;
        }
        .profile ul {
            padding: 15px;
            background-color: #f0f0f0;
            border-radius: 0 0 10px 10px;
            list-style: none;
        }
        .profile li {
            margin-bottom: 10px;
        }
        .profile li:last-child {
            margin-bottom: 0;
        }
        .profile a {
            color: #007bff;
        }
        img{
            width: 100%;
            height: 500px;
        }
        .img{
            margin-left: 10%;
            width: 100%;
        }
        #img{
            float: left;
            width: 30px;
            height: 30px;
            margin-right: 2%; 
        }
    </style>
</head>

<body>
    <div class="img">
        <div class="profile">
            <img src="../resources/img/bsm.jpg" alt="My Image">
            <ul>
                <li><strong>Seungmin Baek</strong></li>
                <li> <img src="../resources/img/Email.png" alt="My Image" id="img"><strong>Email:</strong> qortmdals120@naver.com</li>
                <li> <img src="../resources/img/GitHub.png" alt="My Image" id="img"><strong>GitHub:</strong> <a href="https://github.com/alstmd7/SmartBeanProject.git" target="_blank">https://github.com/JangJaeWone</a></li>
                <li> <img src="../resources/img/Phone.png" alt="My Image" id="img"><strong>Phone:</strong> 010-8308-2525</li>
            </ul>
        </div>

        <div class="profile">
            <img src="../resources/img/hhs.jpg" alt="My Image">
            <ul>
                <li><strong>Han Hee-soo</strong></li>
                <li> <img src="../resources/img/Email.png" alt="My Image" id="img"><strong>Email:</strong> juntu09@gmail.com</li>
                <li> <img src="../resources/img/GitHub.png" alt="My Image" id="img"><strong>GitHub:</strong> <a href="https://github.com/hee-duck/sallye" target="_blank">https://github.com/hee-duck/sallye</a></li>
                <li> <img src="../resources/img/Phone.png" alt="My Image" id="img"><strong>Phone:</strong> 010-7220-8935</li>
            </ul>
        </div>

        <div class="profile">
            <img src="../resources/img/jjw.jpg" alt="My Image">
            <ul>
                <li><strong>Jaewon Jang</strong></li>
                <li> <img src="../resources/img/Email.png" alt="My Image" id="img"><strong>Email:</strong> lokcdown7739@naver.com</li>
                <li> <img src="../resources/img/GitHub.png" id="img"><strong>GitHub:</strong> <a href="https://github.com/JangJaeWone" target="_blank">https://github.com/JangJaeWone</a></li>
                <li> <img src="../resources/img/Phone.png" id="img"><strong>Phone:</strong> 010-7709-2808</li>
            </ul>
        </div>
    </div>
</body>

</html>