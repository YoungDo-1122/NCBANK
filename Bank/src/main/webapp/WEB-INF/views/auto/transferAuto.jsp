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
<title>자동이체 등록</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/accountCreate.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script>
	function formatAccount(account) {
		if (account.startsWith("005")) {
			return account.replace(/(\d{3})(\d{8})(\d{2})(\d{1})/,
					"$1-$2-$3-$4");
		}
		return account;
	}
	function updateTransferDayOptions() {
		var autoTypeSelect = document.getElementById("auto_type");
		var transferDaySelect = document.getElementById("auto_next_date");
		var selectedValue = autoTypeSelect.value;

		transferDaySelect.innerHTML = "";

		if (selectedValue == "0") {
			document.getElementById("transferDayRow").style.display = "none";
		} else if (selectedValue == "1") {
			document.getElementById("transferDayRow").style.display = "table-row";
			var daysOfWeek = [ "일", "월", "화", "수", "목", "금", "토" ];
			daysOfWeek.forEach(function(day, index) {
				var option = document.createElement("option");
				option.value = index + 1;
				option.text = day;
				transferDaySelect.appendChild(option);
			});
		} else if (selectedValue == "2") {
			document.getElementById("transferDayRow").style.display = "table-row";
			for (var day = 1; day <= 31; day++) {
				var option = document.createElement("option");
				option.value = day;
				option.text = day;
				transferDaySelect.appendChild(option);
			}
			var lastDayOption = document.createElement("option");
			lastDayOption.value = "32";
			lastDayOption.text = "말일";
			transferDaySelect.appendChild(lastDayOption);
		}
	}

	document.addEventListener("DOMContentLoaded", function() {
		document.getElementById("auto_type").addEventListener("change",
				updateTransferDayOptions);
		updateTransferDayOptions(); // 페이지 로드 시 초기화
	});
</script>
</head>
<body>
	<div class="container">
		<c:import url="/WEB-INF/views/include/top_menu.jsp" />
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-2">
					<div class="transfer">이체</div>
					<div class="transferhr">
						<hr />
						<ul>
							<li><a href="${root}account/accountCreate">계좌 개설</a></li>
							<li><a href="${root}trans/transfer">계좌 이체</a></li>
							<li><a href="${root}auto/transferAuto">자동이체 등록</a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-10">
					<form:form modelAttribute="autoBean"
						action="${root}auto/transferAuto" method="post">
						<table>
							<tr>
								<th colspan="2"><h3>자동이체 등록</h3></th>
							</tr>
							<tr>
								<td>자동이체명</td>
								<td><form:input path="auto_name" placeholder="자동이체명"
										required="required" /> <form:errors path="auto_name"
										cssClass="error" /></td>
							</tr>
							<tr>
								<td>출금계좌</td>
								<td><form:select path="from_account" id="account"
										required="required">
										<form:option value="">선택</form:option>
										<c:forEach var="account" items="${accounts}">
											<form:option value="${account.account}">
												<c:choose>
													<c:when test="${account.ac_type == 0}">[저축예금]&nbsp;<script>
														document
																.write(formatAccount("${account.account}"));
													</script>
													</c:when>
													<c:when test="${account.ac_type == 1}">[모임통장]&nbsp;<script>
														document
																.write(formatAccount("${account.account}"));
													</script>
													</c:when>
													<c:when test="${account.ac_type == 2}">[적금통장]&nbsp;<script>
														document
																.write(formatAccount("${account.account}"));
													</script>
													</c:when>
												</c:choose>
											</form:option>
										</c:forEach>
									</form:select></td>
							</tr>
							<tr>
								<td>비밀번호 확인</td>
								<td><input type="password" name="ac_password"
									placeholder="숫자 4자리" maxlength="4" id="ac_password"
									required="required" /> &nbsp;<form:errors path="from_account"
										cssClass="error" /></td>
							</tr>
							<tr>
								<td>입금은행</td>
								<td><form:select path="code_organ" required="required">
										<form:option value="">선택</form:option>
										<c:forEach var="codeOrgan" items="${codeOrganNames}">
											<form:option value="${codeOrgan.code_organ}">${codeOrgan.code_organ_name}</form:option>
										</c:forEach>
									</form:select></td>
							</tr>
							<tr>
								<td>입금계좌</td>
								<td><form:input path="to_account"
										placeholder="입금계좌 ('-'빼고)" required="required" /> <form:errors
										path="to_account" cssClass="error" /></td>
							</tr>
							<tr>
								<td>이체금액</td>
								<td><form:input path="auto_money" placeholder="이체금액"
										required="required" /> <form:errors path="auto_money"
										cssClass="error" /></td>
							</tr>
							<tr>
								<td>이체주기</td>
								<td><form:select path="auto_type" id="auto_type"
										required="true">
										<form:option value="0">매일</form:option>
										<form:option value="1">매주</form:option>
										<form:option value="2">매월</form:option>
									</form:select></td>
							</tr>
							<tr id="transferDayRow">
								<td>이체일</td>
								<td><form:select path="auto_next_date" id="auto_next_date">
									</form:select></td>
							</tr>
							<tr id="transferEndDateRow">
								<td>이체 종료일</td>
								<td><form:input type="date" path="auto_end" id="auto_end" /></td>
							</tr>
							<tr>
								<td colspan="2" style="text-align: center; margin-top: 20px;">
									<button type="submit">자동이체 등록</button>
								</td>
							</tr>
						</table>
					</form:form>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
	</div>
</body>
</html>
