<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
						<td><h3>조회 / 이체</h3></td>
					</tr>
					<tr>
						<td>
							<ul>
								<li><a href="${root}account/accountCreate">계좌 개설</a></li>
								<li><a href="${root}account/accountCheck">계좌 조회</a></li>
								<li><a href="${root}trans/transfer">계좌 이체</a></li>
								<li><a href="${root}trans/transferCheck">이체내역 조회</a></li>
								<li><a href="${root}account/transferAuto">자동이체 등록</a></li>
								<li><a href="${root}/account/transferAutoFix">자동이체 수정</a></li>
							</ul>
						</td>
					</tr>
				</table>
			</div>
			<div class="col-md-10">
				<table>
					<tr>
						<th><h2>입 / 출금 계좌</h2></th>
					</tr>
					<tr>
						<td>
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
											<td>${account.ac_balance}</td>
											<td><fmt:formatDate value="${account.ac_date}"
													pattern="yyyy년 M월 d일 EEEE HH:mm:ss" /></td>
											<td>
												<button
													onclick="window.location.href='${root}trans/transferCheck'">이체내역
													조회</button>
												<button value="이체">계좌 이체</button>
											</td>
										</tr>
										<c:set var="totalBalance"
											value="${totalBalance + account.ac_balance}" />
									</c:forEach>
									<tr>
										<th colspan="4" class="total">입 / 출금계좌 총 잔액</th>
										<td>${totalBalance}원</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>