<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/top_menu.css">
<nav>
	<div class="brandlogo">
		<a class="navbar-brand" href="${root}main"> <img
			src="${root}/img/ncbank_logo.png" /></a>
		<p>NC BANK</p>
	</div>
	<ul id="main-menu">
		<li><a href="${root}account/accountCheck">조회</a>
			<ul id=sub-menu>
				<li><a href="${root}account/accountCheck">계좌조회</a></li>
				<li><a href="${root}trans/transferCheck">이체내역 조회</a></li>
				<li><a href="${root}auto/transferAutoCheck">자동이체 조회</a></li>
			</ul></li>
		<li><a href="${root}trans/transfer">이체</a>
			<ul id=sub-menu>
				<li><a href="${root}account/accountCreate">계좌개설</a></li>
				<li><a href="${root}trans/transfer">계좌이체</a></li>
				<li><a href="${root}auto/transferAuto">자동이체 등록</a></li>
			</ul></li>
		<li><a href="${root}groupAccount/about">모임</a>
			<ul id=sub-menu>
				<li><a href="${root}groupAccount/about">모임통장이란</a></li>
				<li><a href="${root}groupAccount/create">모임통장개설</a></li>
				<li><a href="${root}groupAccount/management">모임통장 관리</a>
			</ul></li>
		<li><a href="${root}exchange/rateInquiry">환전</a>
			<ul id=sub-menu>
				<li><a href="${root}exchange/rateInquiry">환율 조회</a></li>
				<li><a href="${root}exchange/notice">환율 알림 서비스</a></li>
				<li><a href="${root}exchange/exchangeAsk">환전 신청</a></li>
				<li><a href="${root}exchange/exchangeHistory">환전 내역 조회</a></li>
			</ul></li>
		<li><a href="${root}board/main?board_info_idx=1">게시판</a>
			<ul id=sub-menu>
				<li><a href="${root}board/main?board_info_idx=1">공지사항</a>
				<li><a href="${root}board/main?board_info_idx=2">자유게시판</a>
			</ul></li>
	</ul>
	<ul class="login">
		<c:choose>
			<c:when test="${loginUserBean.userLogin == true }">
				<li class="nav-item"><a href="${root}user/mypage"
					class="nav-link">마이페이지</a></li>
				<li class="nav-item"><a href="${root}user/logout"
					class="nav-link">로그아웃</a></li>
			</c:when>
			<c:otherwise>
				<li class="nav-item"><a href="${root}user/login"
					class="nav-link">로그인</a></li>
				<li class="nav-item"><a href="${root}user/join"
					class="nav-link">회원가입</a></li>
			</c:otherwise>
		</c:choose>
		
		<!-- 쳇봇 -->
		<li class="nav-item"> <!-- fa-2x : 크기조절 -->
			<i class="fa-brands fa-reddit fa-2x chatBotIcon"></i>
			<!-- <i class="fa-brands fa-bilibili fa-2x chatBot"></i> -->
		</li>
		
	</ul>
	
</nav>

<script type="text/javascript">
		$(document).ready(function() { // 문서가 완전히 로드된 후 이벤트 핸들러 설정
			$('.chatBotIcon').on("click", function(e) {
				e.preventDefault();

				var url = "${root}/chat"
				var name = "[Beta] AI Chatbot"
				var width = 600;
	            var height = 700;
	            var left = (window.screen.width / 2) - (width / 2);
	            var top = (window.screen.height / 2) - (height / 2);
	            var option = "width=" + width + ", height=" + height + ", top=" + top + ", left=" + left
	             	+ ", munubar=no, scrollbars=yes";
				window.open(url, name, option);
			}); // .rateCalculator
			
         }); // $(document).ready
</script>
<!-- 아이콘 -->
<script src="https://kit.fontawesome.com/c9b4b00f98.js"
	crossorigin="anonymous"></script>
