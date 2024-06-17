<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>exchange/notice.jsp - 환율알림</title>
</head>



<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${root}/css/exchange/notice.css">
<link rel="stylesheet" href="${root}/css/exchange/tType.css">
<link rel="stylesheet" href="${root}/css/exchange/bType.css">

<link rel="stylesheet" href="${root}/css/exchange/LP.css">

<body>
	
	<c:import url="../include/top_menu.jsp" />
	
	<div class="contentWarp">
		<h2>환율알림서비스</h2>

		<div id="CP">

			<div id="b030375">

				<script type="text/javascript">
					function HelpOpenPopWin(f, actionUrl, Option) {
						window.open("", f.target, Option);
						f.action = actionUrl;
						f.submit();
					}
				</script>

				<!-- 
				<form name="HelpPopupForm" id="HelpPopupForm" method="post"
					target="HelpPop" action="">
					<input type="hidden" name="QSL" value="F">
				</form>
				 -->

				<!-- Navigation 시작 -->
				<h4 class="blind">페이지 위치</h4>
				<div class="linemap">
					<a href="/quics?page=obank">개인뱅킹</a> <a href="/quics?page=C101313">외환</a>
					<a href="/quics?page=C101318">환율</a> <a href="/quics?page=C101335">환율알림서비스</a>
					<em>안내</em>
				</div>
				<!-- Navigation 끝 -->
			</div>

			<div id="b031064">
				<!-- Title 시작 -->
				<div class="toparea">
					<h1 class="h1_title">환율알림서비스</h1>
				</div>

				<ul class="tabMenu">

					<li class="on"><a href="/quics?page=C101428">안내</a> <!-- 서브 Tab 시작 -->
					</li>
					<!-- 서브 Tab 끝 -->
					<li><a href="/quics?page=C101427">신청/변경/해지</a> <!-- 서브 Tab 시작 -->
					</li>
					<!-- 서브 Tab 끝 -->
				</ul>

				<h2 class="blind">안내</h2>

				<!-- Title 끝 -->
			</div> <!-- div.b030375 -->
			
			
			<!-- 환율 안내 jsp 임포트 -->
			<!-- 상단 tab메뉴 선택에따라 임포트 변경 -->
			<c:import url="./noticeGuide.jsp" />
			
			<!-- 등록을 누른다 -> 로그인이 안되어있다면 -> 로그인으로. -->

			
			<div class="btnArea">
				<a href="/quics?page=C101427">
					<button type="button"	class="btn-action c1">등록</button>
				</a>
			</div>
			
			
		</div> <!-- div.CP -->
	
		<!-- 환율 차트 판업 -->
		<button class="rateChartBtn bType02">환율차트</button>
		<script type="text/javascript">
			/* .calculatorBtn 을 눌렀을때 판업창 열기 */
			$(document).ready(function() { // 문서가 완전히 로드된 후 이벤트 핸들러 설정

				$('.rateChartBtn').on("click", function(e) {
					e.preventDefault();

					var url = "${root}/exchange/rateChart"
					var name = "환율 차트"
					var option = "width = 650px, height = 650px, top = 200px"
					window.open(url, name, option);
				});
			}); // $(document).ready
		</script>

	</div> <!-- div.contentWarp -->
	
	<c:import url="../include/bottom_info.jsp" />

</body>
</html>