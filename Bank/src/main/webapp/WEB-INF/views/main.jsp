<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NC은행</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	<div class="container">
		<div class="carousel-wrapper">
			<div class="carousel">
				<button class="carousel-button prev">&#10094;</button>
				<img src="img/Bank1.png" alt="캐러셀 이미지 1" class="active"> <img
					src="img/Bank2.png" alt="캐러셀 이미지 2"> <img src="img/Bank3.jpg"
					alt="캐러셀 이미지 3">
				<button class="carousel-button next">&#10095;</button>
			</div>
			<div class="quickLink">
				<ul>
					<li><a href="#"> <img src="img/NCBank.png" alt="빠른링크1">
							<p>조회</p>
					</a></li>
					<li><a href="#"> <img src="img/NCBank2.png" alt="빠른링크2">
							<p>이체</p>
					</a></li>
					<li><a href="#"> <img src="img/NCBank3.png" alt="빠른링크3">
							<p>환율</p>
					</a></li>
				</ul>
			</div>
		</div>
	</div>

	<!-- 스크립트 코드 js파일로 이동후 임포트 시킨거 -->
	<script type="text/javascript" src="${root}/js/style_main.js"></script>

	<div class="content">
		<div class="tabMenu">
			<input type="radio" id="tab1" name="tabs" checked> <label
				for="tab1">공지사항</label> <input type="radio" id="tab2" name="tabs">
			<label for="tab2">새소식</label>
			<div id="notice" class="tabContent">
				<ul>
					<li><a href="#">공지사항 제목1</a></li>
					<li><a href="#">공지사항 제목2</a></li>
					<li><a href="#">공지사항 제목3</a></li>
					<li><a href="#">공지사항 제목4</a></li>
					<li><a href="#">공지사항 제목5</a></li>
				</ul>
			</div>
			<div id="notice2" class="tabContent2">
				<ul>
					<li><a href="#">새소식 제목1</a></li>
					<li><a href="#">새소식 제목2</a></li>
					<li><a href="#">새소식 제목3</a></li>
					<li><a href="#">새소식 제목4</a></li>
					<li><a href="#">새소식 제목5</a></li>
				</ul>
			</div>
		</div>
		<div class="exchange_rate">
			<select id="currencySelect" name="currency">
				<option value="USD">달러($)</option>
				<option value="JPY">엔(¥)</option>
				<option value="EUR">유로(€)</option>
			</select> <img id="currencyImage" src="img/그웬2.jpg" alt="통화 이미지">
		</div>
	</div>

	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />

</body>
</html>