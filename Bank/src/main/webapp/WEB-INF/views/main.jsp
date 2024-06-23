<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NC뱅크</title>

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
 <script>
    $(document).ready(function() {
        <c:if test="${not empty redirectAfterLogin}">
          console.log('Redirect URL is: ${redirectAfterLogin}'); // 디버깅 메시지
          if (confirm('수락 페이지로 돌아가시겠습니까?')) {
            window.location.href = '${pageContext.request.contextPath}/${redirectAfterLogin}';
          }
        </c:if>
    });
  </script>
</head>
<body>

	<div class="container">
		<c:import url="/WEB-INF/views/include/top_menu.jsp" />
		<div class="mainhome">
			<div class="carousel-wrapper">
				<div class="carousel">
					<button class="carousel-button prev">&#10094;</button>
					<img src="img/Bank1.png" alt="캐러셀 이미지 1" class="active"> <img
						src="img/Bank2.png" alt="캐러셀 이미지 2"> <img
						src="img/Bank3.jpg" alt="캐러셀 이미지 3">
					<button class="carousel-button next">&#10095;</button>
				</div>
			</div>
				<div class="quickLink">
					<div class="quickLink-title">
						<ul>
							<!--알아서 링크 거시고요 -->
							<li><a href="#"> <i
									class="fa-solid fa-magnifying-glass-dollar fa-3x"></i>
									<div>조회</div>
							</a></li>
							<li><a href="#"> <i
									class="fa-solid fa-money-bill-transfer fa-3x"></i>
									<div>이체</div>
							</a></li>
							<li><a href="#"> <i class="fa-solid fa-users fa-3x"></i>
									<div>모임</div>
							</a></li>
							<li><a href="#"> <img src="img/환율.png" alt="빠른링크4">
									<div>환율</div>
							</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- 스크립트 코드 js파일로 이동후 임포트 시킨거 -->
			<%-- <script type="text/javascript" src="${root}js/style_main.js"></script>--%>
			<div class="bottom_menu">
				<div class="board bd1">
					<div class="tit">
						<h4>공지사항</h4>
						<a
							href="${root}board/main?board_info_idx=${board_list[0].board_info_idx}">더보기
							> </a>
					</div>
					<ul>
						<c:forEach var='obj' items="${list[0]}">
							<li><a
								href="${root}board/read?board_info_idx=${board_list[0].board_info_idx}&content_idx=${obj.content_idx}&page=1">${obj.content_subject}</a></li>
						</c:forEach>
					</ul>
				</div>

				<div class="board bd2">
					<div class="tit">
						<h4>새소식</h4>
						<a
							href="${root}board/main?board_info_idx=${board_list[1].board_info_idx}">더보기
							> </a>

					</div>
					<ul>
						<c:forEach var='obj' items="${list[1]}">
							<li><a
								href="${root}board/read?board_info_idx=${board_list[1].board_info_idx}&content_idx=${obj.content_idx}&page=1">${obj.content_subject}</a></li>
						</c:forEach>
					</ul>
				</div>
				
				<!-- 환율 정보 -->
				<div class="miniRateView">
					<h2>미니뷰 테스트</h2>
					<script>
				        $(document).ready(function() {
				        	 var ISOCodes = "USD,JPY,EUR,CNH"; // 여러 통화 코드를 쉼표로 구분된 문자열로 정의

				             $.ajax({
				                 url: "${root}exchange/miniRateInquiry",
				                 type: "GET",
				                 data: { ISOCode: ISOCodes },
				                 success: function(data) {
				                     $(".miniExchangeRate").html(data); // 응답 데이터를 miniExchangeRate div에 삽입
				                 },
				                 error: function() {
				                     alert("환율 정보를 가져오는 데 실패했습니다.");
				                 }
				             });
				        });
				    </script>
				    <div class="miniExchangeRate"></div>
				    	
				    
			</div>
				
		</div>
		<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
	</div>




</body>
<script src="https://kit.fontawesome.com/c9b4b00f98.js"
	crossorigin="anonymous"></script>
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

document.querySelector('.carousel .prev').addEventListener('click', showPrevImage);
document.querySelector('.carousel .next').addEventListener('click', showNextImage);

setInterval(showNextImage, 3000);

</script>
</html>