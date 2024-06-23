<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>계좌 조회</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/accountCheck.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">

		<c:import url="/WEB-INF/views/include/top_menu.jsp" />
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-2">
					<div class="enquiry">
						<h5>조회</h5>
						<ul>
							<li><a href="${root}account/accountCheck">계좌 조회</a></li>
							<li><a href="${root}trans/transferCheck">이체내역 조회</a></li>
							<li><a href="${root}auto/transferAutoCheck">자동이체 조회</a></li>
						</ul>
					</div>
					<div class="transfer">
						<h5>이체</h5>
						<ul>
							<li><a href="${root}account/accountCreate">계좌 개설</a></li>
							<li><a href="${root}trans/transfer">계좌 이체</a></li>
							<li><a href="${root}auto/transferAuto">자동이체 등록</a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-10">
					<table>
						<tr>
							<th><h3>입 / 출금 계좌</h3></th>
						</tr>
						<tr>
							<td>
								<table>
									<thead>
										<tr>
											<th>계좌분류</th>
											<th>계좌번호</th>
											<th>잔액</th>
											<th>계좌개설일</th>
											<th>업무</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="totalBalance" value="0" />
										<c:forEach var="account" items="${accounts}">
											<tr>
												<td><c:choose>
														<c:when test="${account.ac_type == 0}">
											저축예금
											</c:when>
														<c:when test="${account.ac_type == 1}">
											모임통장
											</c:when>
													</c:choose></td>
												<td>[NC뱅크]${account.account}</td>
												<td>&#8361;&nbsp;${account.ac_balance}</td>
												<td><fmt:formatDate value="${account.ac_date}"
														pattern="yyyy.MM.d" /></td>
												<td>
													<button class="transfer-check-button"
														onclick="window.location.href='${root}trans/transferCheck'">조회</button>
													<button class="transfer-button"
														onclick="window.location.href='${root}trans/transfer'">이체</button>
												</td>
											</tr>
											<c:set var="totalBalance"
												value="${totalBalance + account.ac_balance}" />
										</c:forEach>
										<tr>
											<th colspan="4" class="total">입 / 출금계좌 총 잔액</th>
											<td class="total-balance">&#8361;&nbsp;${totalBalance}</td>
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
	</div>
</body>
</html>