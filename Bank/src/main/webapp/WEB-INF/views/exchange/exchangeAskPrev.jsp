<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/teststyle_top.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>exchangeAskPrev</title>
</head>
<body>
	
		<div class="container">
		    <div class="header">
		        <h1>환전 신청</h1>
		    </div>
		    <div class="content">
		        <h2>약관동의</h2>
		        <form action="${root}exchange/exchangeAsk" method="post">
		            <div class="form-group">
		                <input type="checkbox" id="agree_all" name="agree_all">
		                <label for="agree_all">전체동의</label>
		            </div>
		            <div class="form-group">
		                <input type="checkbox" id="agree1" name="agree1">
		                <label for="agree1">[필수] 외환거래기본약관</label>
		            </div>
		
		            <div class="btn-group">
		                <button type="button" class="secondary"><a href="${root }main">이전</a></button>
		                <button type="submit">다음</button>
		            </div>
		        </form>
		    </div>
		</div>
	
		
		<script>
		
		</script>
		
</body>
</html>