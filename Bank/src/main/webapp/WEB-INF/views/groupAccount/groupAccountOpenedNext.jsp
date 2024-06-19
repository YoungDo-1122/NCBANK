<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>openedNext</title>
<link rel="stylesheet" href="${root}css/createnext.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script type="text/javascript">
	function confirmAndSubmit() {
		var result = confirm("모임통장을 개설하시겠습니까?");
		if (result) {
			document.getElementById("confirmPasswordForm").submit();
		}
	}
</script>
</head>
<body>
	<div class="main">
		<c:import url="/WEB-INF/views/include/top_menu.jsp" />
		<div class="traveltitle">
			모임통장 계좌개설
			<hr />
		</div>
		<div class="contents">
			<div class="menu1">
				<a href="group">모임약관동의</a>
				<div class="menu1-1">모임통장 개설</div>
				<div class="menuhr">
					<hr />
				</div>
				<a href="groupInvite">모임통장 초대</a>
			</div>
			<div class="contents-1">
				<div class="section-1">
					<div class="contentsBox">
						<span class="contentsText">모임 개설</span><br> <span
							class="contentsText1">맞춤정보입력</span>
					</div>
					<div class="stepper">
						<div class="line"></div>
						<div class="step">
							<div class="circle">1</div>
						</div>
						<div class="step">
							<div class="circle active">2</div>
						</div>
						<div class="step">
							<div class="circle">3</div>
						</div>
					</div>
				</div>
				<div class="group71">
					<div class="flexClass">
						<span class="idbox"> <span class="groupname">${groupname}</span>[<span>${grouptype}</span>]
						</span>
					</div>
					<br />
					<div class="flexClass">
						<span class="idbox">연결 계좌번호</span> <input class="rec6"
							id="group_account" name="group_account"
							value="${ac_name}${accounts}" readonly>
					</div>
					<br />
					<div class="flexClass">
						<span class="idbox">납부일</span> <input type="text"
							id="auto_next_date" name="auto_next_date" class="rec6"
							value="${accountInfo.auto_next_date}" readonly>
					</div>
					<div class="flexClass">
						<span class="idbox">자동이체 여부</span> <input type="text"
							id="auto_type" name="auto_type" class="rec6"
							value="${accountInfo.auto_type}" readonly>
					</div>
					<div class="flexClass">
						<span class="idbox">회비</span> <input type="text" id="auto_balance"
							name="auto_balance" class="rec6"
							value="${accountInfo.auto_balance}" readonly>
					</div>
					<br />
					<form method="post" action="${root}groupAccount/complete"
						id="confirmPasswordForm">
						<input type="hidden" name="groupname" value="${groupname}" /> <input
							type="hidden" name="grouptype" value="${grouptype}" /> <input
							type="hidden" name="accounts" value="${accounts}" /> <input
							type="hidden" name="ac_name" value="${ac_name}" /> <input
							type="hidden" name="auto_next_date"
							value="${accountInfo.auto_next_date}" /> <input type="hidden"
							name="auto_type" value="${accountInfo.auto_type}" /> <input
							type="hidden" name="auto_balance"
							value="${accountInfo.auto_balance}" /> <input type="hidden"
							name="group_num" value="${group_num}" />
						<!-- group_num 추가 -->
						<div class="flexClass">
							<span class="idbox">비밀번호</span> <input type="password"
								name="inputPassword" class="rec6" placeholder="비밀번호를 다시 입력해주세요" />
						</div>
						<br />
						<button type="button" onclick="confirmAndSubmit()">개설하기</button>
					</form>
					<c:if test="${not empty errorMessage}">
						<div class="alert alert-danger">${errorMessage}</div>
						<!-- Hidden fields to preserve values on error -->
						<form method="post"
							action="${root}groupAccount/groupAccountOpenedNext">
							<input type="hidden" name="groupname" value="${groupname}" /> <input
								type="hidden" name="grouptype" value="${grouptype}" /> <input
								type="hidden" name="accounts" value="${accounts}" /> <input
								type="hidden" name="ac_name" value="${ac_name}" /> <input
								type="hidden" name="auto_next_date"
								value="${accountInfo.auto_next_date}" /> <input type="hidden"
								name="auto_type" value="${accountInfo.auto_type}" /> <input
								type="hidden" name="auto_balance"
								value="${accountInfo.auto_balance}" />
						</form>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
	</div>
</body>
</html>
