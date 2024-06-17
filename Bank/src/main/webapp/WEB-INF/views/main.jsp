<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NC은행</title>
<style>
.tabContent li {
	margin-bottom: 5px; /* 원하는 간격 값 */
}
</style>
<!-- Bootstrap CDN -->
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"> -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css"> 	
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
				<!--알아서 링크 거시고요 -->
					<li><a href="#"> <img src="img/조회.png" alt="빠른링크1">
							<p>조회</p>
					</a></li>
					<li><a href="#"> <img src="img/이체.jpg" alt="빠른링크2">
							<p>이체</p>
					</a></li>
					<li><a href="#"> <img src="img/모임.png" alt="빠른링크3">
							<p>모임</p>
					</a></li>
					<li><a href="#"> <img src="환율.png" alt="빠른링크3">
							<p>환율</p>
					</a></li>
				</ul>
			</div>
		</div>
	</div>

	<!-- 스크립트 코드 js파일로 이동후 임포트 시킨거 -->
	<script type="text/javascript" src="${root}/js/style_main.js"></script>

	<!-- 
		<div class="tabMenu">
			<input type="radio" id="tab1" name="tabs" checked> 
			<label for="tab1">공지사항</label> 
			<input type="radio" id="tab2" name="tabs"> 
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
 -->
	<div class="tabMenu">
		<input type="radio" id="tab1" name="tabs" checked> <label
			for="tab1">공지사항</label> <input type="radio" id="tab2" name="tabs">
		<label for="tab2">새소식</label>

		<div id="tab1Content" class="tabContent">
			<!-- 공지사항 탭 내용 -->
			<ul>
				<c:forEach var='obj' items="${list[0]}">
					<li><a
						href="${root}board/read?board_info_idx=${board_list[0].board_info_idx}&content_idx=${obj.content_idx}&page=1">${obj.content_subject}</a></li>
				</c:forEach>
			</ul>
		</div>
		<div id="tab2Content" class="tabContent">
			<ul>
				<c:forEach var='obj' items="${list[1]}">
					<li><a
						href="${root}board/read?board_info_idx=${board_list[1].board_info_idx}&content_idx=${obj.content_idx}&page=1">${obj.content_subject}</a></li>
				</c:forEach>
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


	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />

</body>
<script>
	$(document).ready(function() {
		// 탭 클릭 이벤트 처리
		$('input[name="tabs"]').on('change', function() {
			var tabId = $(this).attr('id');
			$('.tabContent').hide(); // 모든 탭 내용 숨기기
			$('#' + tabId + 'Content').show(); // 선택한 탭 내용 보이기
		});

		// 초기 설정: 첫 번째 탭 내용 보이기
		$('#tab1Content').show();
		$('#tab2Content').hide();
	});
</script>
</html>