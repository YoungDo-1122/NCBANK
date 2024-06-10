<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

td {
	border: 1px solid #EAEAEA;
	padding: 20px;
	text-align: left;
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
		<h2>출금정보</h2>
		<hr>
		<table>
			<tr>
				<td>출금계좌번호</td>
				<td><c:forEach var="account" items="${accounts}">
						<select>
							<option value="${account.account}">[${account.ac_name}]${account.account}</option>
							<option value="">1</option>
						</select>
					</c:forEach></td>
			</tr>
			<tr>
				<td>계좌비밀번호</td>
				<td><input type="text" class="textfield" size="10"
					placeholder=" 숫자 4자리" /></td>
			</tr>
		</table>
		<br />
		<h2>입금정보</h2>
		<hr>
		<table>
			<tr>
				<td>입금은행</td>
				<td><select>
						<c:forEach var="codeOrganName" items="${codeOrganNames}">
							<option>${codeOrganName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>입금계좌번호</td>
				<td><input type="text" class="textfield" size="20"
					placeholder=" '-'없이 숫자만 입력" /></td>
			</tr>
			<tr>
				<td>이체금액</td>
				<td><input type="text" class="textfield" size="20"
					placeholder=" 이체하실금액" />&nbsp;원&nbsp; <input type="text"
					class="textfield" size="20" placeholder=" 이체한도" disabled="disabled" />&nbsp;원&nbsp;
					<input type="button" value="이체한도조회"><br /> <br /> <input
					type="button" value="100만">&nbsp;<input type="button"
					value="50만">&nbsp;<input type="button" value="10만">&nbsp;<input
					type="button" value="5만">&nbsp;<input type="button"
					value="3만">&nbsp;<input type="button" value="1만">&nbsp;<input
					type="button" value="전액"></td>

			</tr>
			<tr>
				<td>이체 메모</td>
				<td><input type="text" class="textfield"
					placeholder=" (선택) 50자 이내 입력" maxlength="50" size="100" /></td>
			</tr>
		</table>
	</div>

	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>