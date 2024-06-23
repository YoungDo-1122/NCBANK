<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>이체내역 확인</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/transferCheck.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<div class="enquiry">
					<h3>조회</h3>
					<ul>
						<li><a href="${root}account/accountCheck">계좌 조회</a></li>
						<li><a href="${root}trans/transferCheck">이체내역 조회</a></li>
						<li><a href="${root}auto/transferAutoCheck">자동이체 조회</a></li>
					</ul>
				</div>
				<div class="transfer">
					<h3>이체</h3>
					<ul>
						<li><a href="${root}account/accountCreate">계좌 개설</a></li>
						<li><a href="${root}trans/transfer">계좌 이체</a></li>
						<li><a href="${root}auto/transferAuto">자동이체 등록</a></li>
						<li><a href="${root}auto/transferAutoFix">자동이체 수정</a></li>
					</ul>
				</div>
			</div>
			<div class="col-md-10">
				<c:choose>
					<c:when test="${not empty transfers}">
						<table>
							<tr>
								<td><select id="accountSelect">
										<option value="">계좌를 선택하세요</option>
										<c:forEach var="account" items="${accounts}">
											<option value="${account.account}"
												<c:if test="${account.account eq selectedAccount}">selected</c:if>>${account.account}</option>
										</c:forEach>
								</select>
									<button onclick="filterTransfers()">조회</button></td>
							</tr>
						</table>
						<table>
							<tr>
								<th><h2>입 / 출금 계좌</h2></th>
							</tr>
							<tr>
								<td>
									<table>
										<thead>
											<tr>
												<th>거래유형</th>
												<th>이체금액</th>
												<th>입금계좌</th>
												<th>이체 후 잔액</th>
												<th>출금계좌</th>
												<th>이체 메모</th>
												<th>일시</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="transfer" items="${transfers}">
												<tr>
													<td><c:if test="${transfer.trans_type eq 1}">입금</c:if>
														<c:if test="${transfer.trans_type eq 2}">출금</c:if></td>
													<td><c:if test="${transfer.trans_type eq 1}">+&nbsp;${transfer.trans_money}</c:if>
														<c:if test="${transfer.trans_type eq 2}">-&nbsp;${transfer.trans_money}</c:if></td>
													<td>[${transfer.code_organ_name}]${transfer.to_account}</td>
													<td>&#92;${transfer.trans_balance}</td>
													<td>[NC뱅크]${transfer.from_account}</td>
													<td>${transfer.trans_text}</td>
													<td><fmt:formatDate value="${transfer.trans_date}"
															pattern="yyyy년 M월 d일 EEEE HH:mm:ss" /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</td>
							</tr>
						</table>
					</c:when>
					<c:otherwise>
						<table>
							<tr>
								<td><select id="accountSelect">
										<option value="">선택</option>
										<c:forEach var="account" items="${accounts}">
											<option value="${account.account}">${account.account}</option>
										</c:forEach>
								</select>
									<button onclick="filterTransfers()">조회</button></td>
							</tr>
							<tr>
								<td>
									<p>계좌를 선택해 주세요</p>
								</td>
							</tr>
						</table>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
	<script>
		function filterTransfers() {
			var selectedAccount = document.getElementById("accountSelect").value;
			if (!selectedAccount) {
				alert('계좌를 선택해 주세요');
				return;
			}
			window.location.href = "${root}trans/transferCheck?account="
					+ selectedAccount;
		}
	</script>
</body>
</html>
