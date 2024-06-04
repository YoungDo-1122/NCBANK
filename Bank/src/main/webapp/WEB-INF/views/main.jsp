<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>미니 프로젝트</title>

<style>
<
c










:import





 





url










="/
resources
/










css
/










style










.jsp










"
/
>
</style>

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
					src="img/NCBank2.png" alt="캐러셀 이미지 2"> <img
					src="img/NCBank2.jpe" alt="캐러셀 이미지 3">
				<button class="carousel-button next">&#10095;</button>
			</div>
			<div class="quickLink">
				<ul>
					<li><a href="#"> <img src="resources/img/NCBank2.png"
							alt="빠른링크1">
							<p>조회</p>
					</a></li>
					<li><a href="#"> <img src="img/NCBank2.png" alt="빠른링크2">
							<p>이체</p>
					</a></li>
					<li><a href="#"> <img src="/img/NCBank2.png" alt="빠른링크3">
							<p>환율</p>
					</a></li>
				</ul>
			</div>
		</div>
	</div>

	<script>
    let currentIndex = 0;
    const images = document.querySelectorAll('.carousel img');
    const totalImages = images.length;

    function showImage(index) {
        images.forEach((img, i) => {
            img.classList.toggle('active', i === index);
        });
    }

    function showNextImage() {
        currentIndex = (currentIndex + 1) % totalImages;
        showImage(currentIndex);
    }

    function showPrevImage() {
        currentIndex = (currentIndex - 1 + totalImages) % totalImages;
        showImage(currentIndex);
    }

    document.querySelector('.carousel-button.prev').addEventListener('click', showPrevImage);
    document.querySelector('.carousel-button.next').addEventListener('click', showNextImage);

    setInterval(showNextImage, 3000);
</script>

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
			</select> <img id="currencyImage" src="/img/NCBank2.png" alt="통화 이미지">
		</div>
	</div>

	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />

</body>
</html>