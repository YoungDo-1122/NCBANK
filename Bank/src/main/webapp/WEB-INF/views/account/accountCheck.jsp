<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>계좌 조회</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
	text-align: center;
}

th, td {
	border: 1px solid #EAEAEA;
	padding: 10px;
	text-align: center;
}

hr {
	height: 2px;
	background-color: blue;
	margin-top: 10px;
}

.textfield {
	border: 1px solid rgba(128, 128, 128, 0.5);
}
</style>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<div>
		<h2>입/출금 계좌</h2>
		<hr>
		<table>
			<thead>
				<tr>
					<th>계좌분류</th>
					<th>계좌번호</th>
					<th>계좌개설일</th>
					<th>잔액(원)</th>
					<th>업무</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="totalBalance" value="0" />
				<c:forEach var="account" items="${accounts}">
					<tr>
						<td>${account.ac_name}</td>
						<td>${account.account}</td>
						<td>${account.ac_date}</td>
						<td>${account.ac_balance}</td>
						<td><input type="button" value="조회" /> <input type="button"
							value="이체" /></td>
					</tr>
					<c:set var="totalBalance"
						value="${totalBalance + account.ac_balance}" />
				</c:forEach>
				<tr>
					<td colspan="4" class="total">입/출금계좌 총 잔액</td>
					<td>${totalBalance}원</td>
				</tr>
			</tbody>
		</table>
	</div>
	<br />
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>