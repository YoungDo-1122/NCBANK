<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>exchangeApiRates</title>
</head>
<body>

<h1>exchangeApiRates</h1>

<table border="1">
    <thead>
        <tr>
            <th>통화 단위</th>
            <th>국가/통화명</th>
            <th>매매 기준율</th>
            <th>전신환(송금) 받으실때</th>
            <th>전신환(송금) 보내실때</th>
            <th>장부가격</th>
            <th>년환가료율</th>
            <th>서울외국환중개 매매기준율</th>
            <th>서울외국환중개 장부가격</th>
        </tr>
    </thead>
    <tbody>
    <form action="${root}exchange/exchangeRatesApi"></form>
        <c:forEach var="rate" items="${exchangeRates}">
            <tr>
                <td>${rate.curUnit}</td>
                <td>${rate.curNm}</td>
                <td>${rate.dealBasR}</td>
                <td>${rate.ttb}</td>
                <td>${rate.tts}</td>
                <td>${rate.bkpr}</td>
                <td>${rate.yyEfeeR}</td>
                <td>${rate.kftcDealBasR}</td>
                <td>${rate.kftcBkpr}</td>
            </tr>
        </c:forEach>
     </form>
    </tbody>
</table>

</body>
</html>
