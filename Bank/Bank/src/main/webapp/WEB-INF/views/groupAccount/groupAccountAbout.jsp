<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>미니 프로젝트</title>
 <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f7f7f7;
            color: #333;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h1 {
            text-align: center;
            color: #4CAF50;
        }
        p {
            line-height: 1.6;
        }
        .features {
            margin: 20px 0;
        }
        .features h2 {
            color: #4CAF50;
        }
        .feature-item {
            margin-bottom: 15px;
        }
        .button {
            display: block;
            width: 200px;
            margin: 20px auto;
            padding: 10px;
            text-align: center;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .button:hover {
            background-color: #45a049;
        }
    </style>

<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

  <div class="container">
        <h1>모임통장 소개</h1>
        <p>모임통장은 친구, 가족, 동료 등과 함께 돈을 모으고 관리할 수 있는 공동 통장입니다. 모임통장을 통해 손쉽게 모임의 비용을 관리하고, 투명하게 자금을 사용할 수 있습니다.</p>
        
        <div class="features">
            <h2>주요 기능</h2>
            <div class="feature-item">
                <h3>간편한 관리</h3>
                <p>모임통장은 스마트폰 앱을 통해 언제 어디서나 간편하게 관리할 수 있습니다.</p>
            </div>
            <div class="feature-item">
                <h3>투명한 자금 사용</h3>
                <p>모임의 모든 자금 흐름을 투명하게 기록하고 확인할 수 있어 신뢰를 구축할 수 있습니다.</p>
            </div>
            <div class="feature-item">
                <h3>자동 정산</h3>
                <p>모임 후 자동으로 비용을 정산해 주어, 불필요한 계산과 오해를 줄일 수 있습니다.</p>
            </div>
        </div>
        
        <a href="#" class="button">더 알아보기</a>
    </div>

<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>

</body>
</html>
