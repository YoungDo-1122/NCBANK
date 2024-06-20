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
<title>계좌 조회</title>
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
					<tr>
						<th><h2>계좌 개설</h2></th>
					</tr>
					<tr>
						<td><c:if test="${not empty errorMessage}">
								<div style="color: red;">${errorMessage}</div>
							</c:if> <form:form modelAttribute="accountBean"
								action="${root }account/accountCreate" method="post">
								<table>
									<tr>
										<td>성명</td>
										<td>${users.name}</td>
									</tr>
									<tr>
										<td>계좌 비밀번호</td>
										<td><input type="password" name="acPassword"
											required="true" /></td>
									</tr>
									<tr>
										<td>계좌 비밀번호 확인</td>
										<td><input type="password" name="acPasswordConfirm"
											required="true" /></td>
									</tr>
									<tr>
										<td>계좌 분류</td>
										<td><form:select path="ac_type" required="true">
												<form:option value="">선택</form:option>
												<form:option value="0">저축예금</form:option>
											</form:select></td>
									</tr>
									<tr>
										<td><button type="submit">계좌 개설</button></td>
									</tr>
								</table>
							</form:form></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>