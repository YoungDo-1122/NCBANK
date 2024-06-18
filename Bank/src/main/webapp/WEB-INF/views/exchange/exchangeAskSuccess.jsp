<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/teststyle_top.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환전 신청 완료</title>
</head>
<body>
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
    
    <button>
    	<a href="${root}exchange/exchangeHistory">환전 조회하기</a>
   	</button>
</body>
</html>
