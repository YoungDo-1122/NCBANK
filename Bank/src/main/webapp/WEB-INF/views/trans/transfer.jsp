<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>계좌 이체</title>
<!-- 부트스트랩 CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<style>
.col-md-10, .col-md-2 {
	border: 1px solid #ddd;
	padding: 10px;
	height: 100%;
}

.container-fluid {
	margin-top: 150px;
}

.col-md-2 table, .col-md-10 table {
	border: 1px solid #ddd;
	width: 100%;
}

.col-md-2 td, .col-md-10 td {
	border: 1px solid #ddd;
	padding: 8px;
}

.col-md-10 th {
	text-align: left;
	border: 1px solid #ddd;
}
</style>
</head>
<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<table>
					<tr>
						<th><h3>조회</h3></th>
					</tr>
					<tr>
						<td>
							<ul>
								<li><a href="${root}account/accountCheck">계좌 조회</a></li>
								<li><a href="${root}trans/transferCheck">이체내역 조회</a></li>

							</ul>
						</td>
					</tr>
					<tr>
						<th>
							<h3>이체</h3>
						</th>
					</tr>
					<tr>
						<td>
							<ul>
								<li><a href="${root}account/accountCreate">계좌 개설</a></li>
								<li><a href="${root}trans/transfer">계좌 이체</a></li>
								<li><a href="${root}account/transferAuto">자동이체 등록</a></li>
								<li><a href="${root}account/transferAutoFix">자동이체 수정</a></li>
							</ul>
						</td>
					</tr>
				</table>
			</div>
			<div class="col-md-10">
				<table>
					<form:form action="${root}trans/transfer_pass" method="post"
						modelAttribute="accountBean" id="passwordForm">
						<table>
							<tr>
								<th><h2>출금 정보</h2></th>
							</tr>
							<tr>
								<th>출금 계좌</th>
							</tr>
							<tr>
								<td><form:select path="account" id="account">
										<form:option value="">선택</form:option>
										<c:forEach var="account" items="${accounts}">
											<form:option value="${account.account}">[${account.ac_type}] ${account.account}</form:option>
										</c:forEach>
									</form:select></td>
							</tr>
							<tr>
								<th>계좌 비밀번호 확인</th>
							</tr>
							<tr>
								<td><input type="password" name="ac_password"
									placeholder="숫자 4자리" maxlength="4" id="ac_password"
									required="required" /> <form:errors path="ac_password"
										cssClass="error" />
							</tr>
						</table>
					</form:form>
					<form:form action="${root}trans/transfer_confirm" method="post"
						modelAttribute="transferBean" id="transferForm">
						<table>
							<tr>
								<td><h2>입금 정보</h2></td>
							</tr>
							<tr>
								<th>입금 은행</th>
							</tr>
							<tr>
								<td><form:select path="code_organ">
										<form:option value="">선택</form:option>
										<c:forEach var="codeOrgan" items="${codeOrganNames}">
											<form:option value="${codeOrgan.code_organ}">${codeOrgan.code_organ_name}</form:option>
										</c:forEach>
									</form:select></td>
							</tr>
							<tr>
								<th>입금계좌</th>
							</tr>
							<tr>
								<td><form:input path="to_account"
										placeholder="입금계좌 ('-'빼고)" required="required" /> <form:errors
										path="to_account" cssClass="error" /></td>
							</tr>
							<tr>
								<th>이체금액</th>
							</tr>
							<tr>
								<td><form:input path="trans_balance" placeholder="이체금액"
										required="required" /> <form:errors path="trans_balance"
										cssClass="error" /></td>
							</tr>
							<tr>
								<th>이체메모</th>
							</tr>
							<tr>
								<td><form:input path="trans_text" placeholder="(선택) 이체메모" />
									<form:errors path="trans_text" cssClass="error" /></td>
							</tr>
							<tr>
								<td>
									<button type="submit">이체</button>
								</td>
							</tr>
						</table>
					</form:form>
				</table>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>
