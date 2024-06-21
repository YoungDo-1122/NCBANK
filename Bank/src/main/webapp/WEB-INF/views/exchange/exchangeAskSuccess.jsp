<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/teststyle_top.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환전 신청 완료</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/exchangeAskSuccess.css">
<style>
@charset "UTF-8";

body {
    font-family: Arial, sans-serif;
    margin: 0;
    display: flex;
    flex-direction: column;
}

.top-menu {
    width: 100%;
    position: fixed;
    top: 0;
    z-index: 1000;
}

.main-content-container {
    display: flex;
    margin-top: 100px; /* Adjust this value according to the height of your top menu */
}

.sidebar {
    width: 200px;
    background-color: #1E3A5F; /* 신한은행 색상 */
    color: white;
    padding: 10px;
    height: calc(100vh - 100px); /* Adjust this value according to the height of your top menu */
    box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
    position: fixed;
    top: 100px; /* Adjust this value according to the height of your top menu */
    left: 0;
}

.sidebar-item {
    padding: 10px;
    cursor: pointer;
    border-bottom: 1px solid #3A5673; /* 신한은행 색상 */
    background-color: #1E3A5F; /* 신한은행 색상 */
    color: white;
    transition: background-color 0.3s;
}

.sidebar-item:hover {
    background-color: #3A5673; /* 신한은행 색상 */
}

.main-content {
    margin-left: 220px;
    padding: 20px;
    width: calc(100% - 220px);
}

h1 {
    color: #0072BC; /* 신한은행 색상 */
    font-size: 24px;
    margin-bottom: 20px;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

table, th, td {
    border: 1px solid #dddddd;
    padding: 12px;
    text-align: left;
}

th {
    background-color: #f2f2f2;
}

button {
    background-color: #0072BC; /* 신한은행 색상 */
    color: white;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
    border-radius: 4px;
    transition: background-color 0.3s;
    margin-right: 10px;
    text-decoration: none;
}

button:hover {
    background-color: #005C99; /* 신한은행 색상 */
}

button a {
    color: white;
    text-decoration: none;
}

</style>
</head>
<body>
<div class="top-menu">
    <c:import url="/WEB-INF/views/include/top_menu.jsp" />
</div>
<div class="main-content-container">
    <div class="sidebar">
        <div class="sidebar-item" onclick="toggleMenu('exchange-rate-menu')">
            환율
            <div id="exchange-rate-menu" class="submenu" style="display: none;">
                <ul>
                    <li><a href="#">환율정보</a></li>
                    <li><a href="#">환율계산기</a></li>
                </ul>
            </div>
        </div>
        <div class="sidebar-item" onclick="toggleMenu('exchange-menu')">
            환전
            <div id="exchange-menu" class="submenu" style="display: none;">
                <ul>
                    <li><a href="#">환전신청</a></li>
                    <li><a href="#">환전조회</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="main-content">
        <h1>환전 신청 완료</h1>
        <table border="1">
            <tr>
                <th>환전신청금액</th>
                <td>${createExchangeBean.code_money} ${createExchangeBean.trade_money}</td>
            </tr>
            <tr>
                <th>현재고시환율</th>
                <td>${createExchangeBean.exchange_cash_buying_rate}</td>
            </tr>
            <tr>
                <th>적용환율</th>
                <td>${createExchangeBean.trade_rate}</td>
            </tr>
            <tr>
                <th>우대금액</th>
                <td>${createExchangeBean.preferential_money}</td>
            </tr>
            <tr>
                <th>출금계좌번호</th>
                <td>${createExchangeBean.account}</td>
            </tr>
            <tr>
                <th>수령희망날짜</th>
                <td>${createExchangeBean.trade_reservation_date}</td>
            </tr>
            <tr>
                <th>수령희망 지점번호</th>
                <td>${createExchangeBean.code_bank}</td>
            </tr>
            <tr>
                <th>수령희망 지점이름</th>
                <td>${createExchangeBean.code_bank_name}</td>
            </tr>
        </table>
        
        <div class="text-right" style="margin-top: 20px;">
            <button>
                <a href="${root}main">메인페이지 가기</a>
            </button>
            <button>
                <a href="${root}exchange/exchangeHistory">환전 조회하기</a>
            </button>
        </div>
    </div>
</div>

<script>
	//왼쪽 사이드바 실행
    function toggleMenu(menuId) {
        var menus = document.getElementsByClassName('submenu');
        for (var i = 0; i < menus.length; i++) {
            menus[i].style.display = 'none';
        }
        var menu = document.getElementById(menuId);
        if (menu.style.display === 'none') {
            menu.style.display = 'block';
        } else {
            menu.style.display = 'none';
        }
    }
</script>
</body>
</html>
