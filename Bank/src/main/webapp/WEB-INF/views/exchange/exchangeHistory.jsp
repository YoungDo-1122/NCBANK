<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체환전내역</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/exchangeHistory.css">
</head>
<body>
    <h1>환전조회/관리</h1>
    <!--
    <div class="search-form">
        <form action="${root}exchange/exchangeHistory" method="get">
            <label for="startDate">조회기간:</label>
            <input type="text" id="startDate" name="startDate" value="${startDate}" placeholder="YYYYMMDD">
            <label for="endDate">~</label>
            <input type="text" id="endDate" name="endDate" value="${endDate}" placeholder="YYYYMMDD">
            <button type="submit">조회</button>
        </form>
    </div>
    -->
    <table border="1">
        <thead>
            <tr>
                <th>선택</th>
                <th>환전일</th>
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
	                <td><input type="radio" name="selectedTrade" value="${trade.trade_num}"></td>
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
    
    <div class="actions">
        <button type="button">되팔기</button>
        <button type="button">외화예금입금(이게 환전지갑?)</button>
        <button type="button">수령정보변경</button>
        
    </div>
    
</body>
</html>
