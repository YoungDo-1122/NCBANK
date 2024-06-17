<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/teststyle_top.css">
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exchange History</title>
</head>
<body>

<h1>Exchange History</h1>

<button id="showHistoryButton">전체 환전 내역 보기</button>

<table border="1" id="exchangeHistoryTable" style="display: none;">
    <thead>
        <tr>
            <th>Trade Number</th>
            <th>Trade Money</th>
            <th>Trade Rate</th>
            <th>Trade Type</th>
            <th>Bank Code</th>
            <th>User Number</th>
            <th>Currency Code</th>
        </tr>
    </thead>
    <form action="${root}exchange/exchangeHistory" method="get">
	    <tbody>
	        <c:forEach var="trade" items="${tradeList}">
	            <tr>
	                <td>${trade.trade_num}</td>
	                <td>${trade.trade_money}</td>
	                <td>${trade.trade_rate}</td>
	                <td>${trade.trade_type}</td>
	                <td>${trade.code_bank}</td>
	                <td>${trade.user_num}</td>
	                <td>${trade.code_money}</td>
	            </tr>
	        </c:forEach>
	    </tbody>
    </form>
</table>

<!-- JavaScript 코드 -->
<script>
document.getElementById("showHistoryButton").addEventListener("click", function() {
    var table = document.getElementById("exchangeHistoryTable");
    if (table.style.display === "none") {
        table.style.display = "block";
    } else {
        table.style.display = "none";
    }
});
</script>

</body>

</html>
