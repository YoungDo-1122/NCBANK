<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/teststyle_top.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환전 조회/관리</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/exchangeHistory.css">
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
        <h1>환전 조회/관리</h1>
        <table border="1">
            <thead>
                <tr>
                	<th>선택</th>
                    <th>환전신청일</th>
                    <th>거래구분</th>
                    <th>통화</th>
                    <th>금액</th>
                    <th>적용환율</th>
                    <th>지점 번호</th>
                    <th>지점 전화번호</th>
                    <th>지점 팩스번호</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="trade" items="${tradePlusList}">
                    <tr>
                    	<td><input type="radio" name="selectedTrade" value="${trade.trade_num}"
                                   data-code-money="${trade.code_money}"
                                   data-trade-money="${trade.trade_money}"></td>
                        <td>${trade.trade_date}</td>
                        <td>${trade.trade_type}</td>
                        <td>${trade.code_money}</td>
                        <td>${trade.trade_money}</td>
                        <td>${trade.trade_rate}</td>
                        <td>${trade.code_bank}</td>
                        <td>${trade.code_bank_tel}</td>
                        <td>${trade.code_bank_fax}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        
        
        
        <!-- 페이지 관련 -->
        <div class="pagination-container">
            <ul class="pagination justify-content-center">
                <c:choose>
                    <c:when test="${0 >= pageBean.prevPage}">
                        <li class="page-item disabled"> 
                            <a href="#" class="page-link">이전</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a href="${root}exchange/exchangeHistory?user_num=${user_num}&page=${pageBean.prevPage}" class="page-link">이전</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="idx" begin="${pageBean.min}" end="${pageBean.max}">
                    <c:choose>
                        <c:when test="${idx == pageBean.currentPage}">
                            <li class="page-item active">
                                <a href="#" class="page-link">${idx}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a href="${root}exchange/exchangeHistory?user_num=${user_num}&page=${idx}" class="page-link">${idx}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${pageBean.nextPage > pageBean.pageCnt}">
                        <li class="page-item disabled">
                            <a href="#" class="page-link">다음</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a href="${root}exchange/exchangeHistory?user_num=${user_num}&page=${pageBean.nextPage}" class="page-link">다음</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
        
        <div class="actions">
            <button>
                <a href="${root}main">메인페이지 가기</a>
            </button>
            <button type="submit" a="">되팔기</button>
            <button>
                <a href="${root}exchange/exchangeWallet">환전지갑 확인</a>
            </button>
        </div>
    </div>
</div>
<script>
	// 왼쪽 사이드바 실행
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
