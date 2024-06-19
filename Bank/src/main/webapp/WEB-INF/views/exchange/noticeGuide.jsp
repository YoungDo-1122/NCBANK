<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>noticeGuide.jsp - 환율알림 안내</title>
</head>
<body>
	<h2>=====환율알림안내=====</h2>
	
	<div id="b101985">
		<h2 class="tit_dep2 tit_dep2_cont">환율알림서비스란?</h2>
		<ul class="list_type1">
			<li>일정 기간 내에 고객님께서 등록하신 환율범위 내로 환율이 도달하는 경우, 문자메시지 또는 이메일로 고객님께
				안내 드리는 서비스입니다.</li>
		</ul>
		<h2 class="tit_dep2 tit_dep2_cont s6">신청안내</h2>
		<ul class="list_type1">
			<li>신청가능시간: 365일 24시간 신청 가능</li>
			<li>신청가능통화: 국민은행에서 환율을 고시하는 모든 통화</li>
			<li>환율종류: 매매기준율, 현찰사실때/파실때, 송금 보내실때/받으실때</li>
			<li>신청유효기간: 신청일로부터 3개월 (3개월 경과 시, 서비스 자동종료)</li>
		</ul>
		<h2 class="tit_dep2 tit_dep2_cont s6">알림안내</h2>
		<ul class="list_type1">
			<li>알림전송조건: 일정 기간 내에 등록하신 환율범위 내로 환율이 도달하는 경우</li>
			<li>알림시간/방법: 09:10 ~ 16:00, 문자메시지(SMS) 또는 이메일 안내</li>
		</ul>
		<h2 class="tit_dep2 tit_dep2_cont s6">유의사항</h2>
		<ul class="list_type1">
			<li>3개월 내 등록하신 환율에 도달하지 않았거나 등록하신 환율에 도달하여 알림안내를 받은 경우<br>서비스가
				자동종료 되므로 추가로 알림을 받고자 하는 경우, 다시 서비스를 신청하시기 바랍니다.
			</li>
			<li>환율알림서비스 문자메시지(SMS)/이메일을 받은 시점과 고객님의 거래 시점의 환율은 다를 수 있습니다.</li>
		</ul>
		<p class="s5 tar">준법감시인 심의필 제 2021-1364-4호 (2021.06.01) 유효기간 :
			2023.05.31</p>
		
	</div>
	
	<div class="btnArea">
		<a href="${root}exchange/noticeRegister">등록</a>
	</div>
	
</body>
</html>