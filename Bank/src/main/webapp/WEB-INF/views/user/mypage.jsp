<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" content="width=device-width, initial-scale=1">
<title>마이페이지</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/joinform.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	function execDaumPostCode() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 도로명 주소와 지번 주소 변수
				var roadAddr = data.roadAddress; // 도로명 주소
				var jibunAddr = data.jibunAddress; // 지번 주소
				var zonecode = data.zonecode; // 우편번호

				// 해당 필드에 데이터를 설정합니다.
				document.getElementById('add1').value = zonecode;
				document.getElementById('add2').value = roadAddr;
				/* document.getElementById('add3').value = jibunAddr; */

				// address 필드에 합쳐진 주소를 설정합니다.
				document.getElementById('address').value = roadAddr ? roadAddr
						: jibunAddr;
			}
		}).open();
	}

	//휴대폰번호 인증번호 보내기 버튼 클릭 이벤트
	function sendSMSVerification() {
		var to = $('input[name="phone"]').val();
		$.ajax({
			url : '${root}user/memberPhoneCheck',
			type : 'POST',
			data : {
				to : to
			},
			dataType : 'text',
			success : function(data) {
				if (data !== 'error') {
					alert('인증번호가 전송되었습니다.');

					$('#certifyCheck').click(function() {
						const userNum = $('#verificationCode').val();
						if (data == userNum) {
							alert('인증 성공하였습니다.');
						} else {
							alert('인증 실패하였습니다. 다시 입력해주세요.');
						}
					});
				} else {
					alert('인증번호 전송에 실패하였습니다.');
				}
			},
			error : function() {
				alert("에러가 발생했습니다.");
			}
		});
	}
</script>
<body>
	<c:import url="../include/top_menu.jsp" />

	<div class="container">
		<h2 class="text-primary">마이페이지</h2>
		<form:form action="${root}user/modify_pro" method="post"
			modelAttribute="users">
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-danger">${errorMessage}</div>
			</c:if>
			<table>
				<tr>
					<td>이름</td>
					<td><input type="text" value="${users.name}"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<td>아이디</td>
					<td><input type="text" value="${users.id}" readonly="readonly" /></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="phone" value="${users.phone}" /></td>
					<td>
						<button type="button" class="btn btn-primary"
							onclick="sendSMSVerification()">인증번호 받기</button>
					</td>
				</tr>
				<tr>
					<td>인증번호</td>
					<td><input type="text" id="verificationCode" /></td>
					<td>
						<button id="certifyCheck" type="button" class="btn btn-primary"
							onclick="certifyCheck()">인증번호 확인</button>
					</td>

				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" value="${user.address}" /></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="password" placeholder="비밀번호를 입력하세요" /></td>				
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" value="${user.email}" /></td>
				</tr>
				<tr>
					<td style="text-align: center;">
						<button value="수정" type="submit">수정하기</button>
					</td>
				</tr>
			</table>
		</form:form>

	</div>

	<c:import url="../include/bottom_info.jsp" />
</body>
</html>