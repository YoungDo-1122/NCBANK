<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<style>
.nav-item.dropdown .nav-link {
	font-size: 23px;
}

.nav-item.dropdown:hover .dropdown-menu {
	display: block;
}
</style>
.nav-item.dropdown .nav-link { font-size: 23px; }
.nav-item.dropdown:hover .dropdown-menu { display: none; }
</style>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/teststyle_main.css">

<<<<<<< Updated upstream
<nav>
	<a class="navbar-brand" href="${root}/main"> <img
		src="${root}/img/ncbank_logo.png" style="height: 100px" /></a>
	<!-- 	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navMenu">
			<span class="navbar-toggler-icon"></span> 
		</button>-->
	<!-- dropdown 메뉴 -->
	<ul id="main-menu">
		<li><a href="#">조회</a>
			<ul id=sub-menu>
				<li><a href="${root}account/accountCheck">계좌조회</a></li>
				<li><a href="${root}trans/transferCheck">이체내역 조회/</a></li>
			</ul></li>
		<li><a href="#">이체</a>
			<ul id=sub-menu>
				<li><a href="${root}account/accountCreate">계좌생성</a></li>
				<li><a href="${root}trans/transfer">이체</a></li>
				<li><a href="${root}account/transferAuto">자동이체 등록</a></li>
				<li><a href="${root}account/transferAutoFix">자동 이체 수정</a></li>
			</ul></li>
		<li><a href="#">모임</a>
			<ul id=sub-menu>
				<li><a href="${root}groupAccount/about">모임통장이란</a></li>
				<li><a href="${root}groupAccount/create">모임통장개설</a></li>
				<li><a href="${root}groupAccount/invite">모임원 초대하기</a></li>
				<li><a href="${root}groupAccount/management">모임통장 관리</a>
			</ul></li>
		<li><a href="#">환전</a>
			<ul id=sub-menu>
				<li><a href="${root}exchange/rateInquiry">환전1</a></li>
				<li><a href="${root}exchange/notice">환전2</a></li>
				<li><a href="${root}exchange/exchangeAsk">환전 신청</a></li>
				<li><a href="${root}exchange/exchangeHistory">환전 내역 조회</a></li>
			</ul></li>
		<li><a href="#">게시판</a>
			<ul id=sub-menu>
				<li><a href="${root}board/main?board_info_idx=1">공지사항</a>
				<li><a href="${root}board/main?board_info_idx=2">새소식</a>
			</ul></li>
	</ul>

	<ul class="login">
		<c:choose>
			<c:when test="${loginUserBean.userLogin == true }">
				<li class="nav-item"><a href="${root}user/modify"
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
	</ul>
</nav>
=======
<nav
	class="navbar navbar-expand-md bg-primary  navbar-dark fixed-top shadow-lg">
	<!-- bg-primary : 파란색 -->
	<a class="navbar-brand" href="${root}main"> <img
		src="${root}/img/ncbank_logo.png" style="height: 45px" />
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navMenu">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navMenu">
		<ul class="navbar-nav">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbardrop">조회 및 이체</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="${root}account/accountCreate">계좌생성</a> 
					<a class="dropdown-item" href="${root}account/accountCheck">계좌조회</a> 
					<a class="dropdown-item" href="${root}account/transferCheck">이체내역 조회</a> 
					<a class="dropdown-item" href="${root}account/transfer">이체</a>
					<a class="dropdown-item" href="${root}account/transferAuto">자동이체 등록</a> 
					<a class="dropdown-item" href="${root}/account/transferAutoFix">자동 이체 수정</a>
				</div>
			</li>
			<li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle" href="#" id="navbardrop"> 모임통장</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="${root}groupAccount/about">모임통장이란</a>
					<a class="dropdown-item" href="${root}groupAccount/create">모임통장 개설</a> 
					<a class="dropdown-item" href="${root}groupAccount/acceptInvite">모임원 초대하기</a> 
					<a class="dropdown-item" href="${root}groupAccount/management">모임통장 관리</a>
				</div>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbardrop"> 환전 </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="${root}exchange/exchangeRateApi">환전api</a>
					<a class="dropdown-item" href="${root}exchange/exchangeHistory">환전 내역 조회</a> 
					<a class="dropdown-item" href="${root}groupAccount/invite">환전3</a>
					<a class="dropdown-item" href="${root}groupAccount/management">환전4</a>
				</div></li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbardrop"> 게시판 </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="${root}groupAccount/about">게시판1</a>
					<a class="dropdown-item" href="${root}groupAccount/create">게시판2</a>
					<a class="dropdown-item" href="${root}groupAccount/invite">게시판3</a>
					<a class="dropdown-item" href="${root}groupAccount/management">게시판4</a>
				</div>
			</li>
		</ul>
		<ul class="navbar-nav ml-auto">
			<c:choose>
				<c:when test="${loginUserBean.userLogin == true }">
					<li class="nav-item"><a href="${root}user/modify"
						class="nav-link">마이페이지</a></li>
					<li class="nav-item"><a href="${root}user/logout"
						class="nav-link">로그아웃</a></li>
				</c:when>
				<c:otherwise>
					<!-- 로그아웃시 -->
					<li class="nav-item"><a href="${root}user/login"
						class="nav-link">로그인</a></li>
					<li class="nav-item"><a href="${root}user/join"
						class="nav-link">회원가입</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</nav>
>>>>>>> Stashed changes
