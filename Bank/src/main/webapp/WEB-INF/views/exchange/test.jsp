<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>환전 계산기</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
      <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input, select, button {
            width: 100%;
            padding: 10px;
            margin: 5px 0 10px 0;
        }
        #result {
            font-weight: bold;
            margin-top: 20px;
        }
    </style>
</head>
<body>
      <div class="container">
        <h1>환전 계산기</h1>
        <div class="form-group">
            <label for="amount">금액 (USD):</label>
            <input type="number" id="amount" name="amount">
        </div>
        <div class="form-group">
            <label for="currency">변환할 통화:</label>
            <select id="currency" name="currency">
                <option value="EUR">EUR (유로)</option>
                <option value="KRW">KRW (원)</option>
                <option value="JPY">JPY (엔)</option>
                <!-- 필요한 통화를 추가할 수 있습니다. -->
            </select>
        </div>
        <button id="convertBtn">변환</button>
        <div id="result"></div>
    </div>
    <script src="${root}/js/exchange/test.js"></script>
</body>
</html>