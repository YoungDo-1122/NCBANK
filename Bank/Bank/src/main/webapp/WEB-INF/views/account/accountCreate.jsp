<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>이체</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
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
	<div>
		<h2>계좌 개설</h2>
		<hr>
		<table>
			<tr>
				<td>성명</td>
				<td><input type="text" class="textfield" size="15"
					placeholder=" 성명을 입력하세요" required></td>
			</tr>
			<tr>
				<td>주민등록번호</td>
				<td><input type="text" class="textfield" size="30"
					placeholder=" 주민등록번호를 입력하세요" required></td>
			</tr>
			<tr>
				<td>휴대폰 번호</td>
				<td><input type="text" class="textfield" size="30"
					placeholder=" 휴대폰 번호를 입력하세요" required>&nbsp;<input
					type="button" value="인증번호 발송"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" class="textfield" size="15"
					placeholder=" 주소를 입력하세요" required></td>
			</tr>
			<tr>
				<td>계좌 분류</td>
				<td><select required>
						<option value="savings">예금</option>
						<option value="deposit">적금</option>
						<option value="stock">주식 계좌</option>
				</select></td>
			</tr>
			<tr>
				<td>지점 선택</td>
				<td><select required>
						<option value="savings">예금</option>
						<option value="deposit">적금</option>
						<option value="stock">주식 계좌</option>
				</select></td>
			</tr>
			<tr>
				<td>초기 입금액</td>
				<td>2</td>
			</tr>
			<tr>
				<td>약관동의</td>
				<td><input type="checkbox" required><input
					type="button" value="확인 및 개설"></td>
			</tr>
		</table>
	</div>
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>