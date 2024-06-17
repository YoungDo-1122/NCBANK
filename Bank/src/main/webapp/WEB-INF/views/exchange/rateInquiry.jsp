<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>exchange/rateInquiry</title>
</head>

<!-- 
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> 
 -->

<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>


<link rel="stylesheet" href="${root}css/exchange/rateInquiry.css">
<link rel="stylesheet" href="${root}css/exchange/tType.css">
<link rel="stylesheet" href="${root}css/exchange/bType.css">
<link rel="stylesheet" href="${root}css/exchange/LP.css">

<script type="text/javascript">
	/*
	var ret = window.open(url, name, specs, replace) -> 새로만들어진 창객체 반환 | 실패 null | 닫기 ret.close()
	[name]
	_blank : 새창 (기본값)
	_parent : 부모 프레임에 열림
	_self : 현재 페이지 대체
	_top : 로드된 프레임셋 대체
	 */
	/* .calculatorBtn 을 눌렀을때 판업창 열기 */
	$(document).ready(function() { // 문서가 완전히 로드된 후 이벤트 핸들러 설정

		$('.calculatorBtn').on("click", function(e) {
			e.preventDefault();

			var url = "${root}exchange/calculator"
			var name = "환율 계산기"
			var option = "width = 650px, height = 650px, top = 200px"
			window.open(url, name, option);
		});
	}); // $(document).ready
	
</script>

<style>
.carbtn {
	background-color: green;
}

.carbtn:hover {
	background-color: lime;
}
</style>

<body>

	<c:import url="../include/top_menu.jsp" />

	<div class="contentWarp">

		<div class="LP">
			<div class="LPWrap">
				<div class="LPC01">
					<h2 class="wrapTitle">외환</h2>
					<ul class="ulType01">
						<li><a href="#">테스트링크1</a></li>
						<li><a href="#">테스트링크2</a></li>
						<li><a href="#">테스트링크3</a></li>
					</ul>

					<ul>
						<li>test1</li>
						<li>test2</li>
						<li>test3</li>
					</ul>

				</div>
				<!-- div.LPC01 -->

			</div>
			<!-- div.LPWrap -->
		</div>
		<!-- div.LP -->

		<div class="CP">
			<form action="${root}exchange/rateInquiryDate" method="get">
				<table class="tType02">
					<colgroup>
						<!-- 테이블의 열 그룹 정의 -->
						<col style="width: 130px">
						<col style="background-color: yellow;">
					</colgroup>

					<tbody>
						<tr>
							<th>조회일</th>
							<td>
								<div class="btnArea">
									<button type="button" class="bType02" onclick="today()">오늘</button>
									<button type="button" class="bType02" onclick="yesterday()">전일</button>
									<button type="button" class="btn" onclick="tomorrow()">다음일</button>
								</div> 
								<!-- required="required" : 반드시 폼 값을 채워야 한다. --> 
								<input	type="date" id="inquiryDate" name="inquiryDate"	required="required"> 
								<script type="text/javascript">
									// 서버에서 전달된 날짜 값 -> js 변수
									var inquiryDate = "${inquiryDate2}";
									// input에 기본값으로 설정
									document.getElementById("inquiryDate").value = inquiryDate;
								</script>
							</td>

						</tr>
					</tbody>
				</table>

				<div class="submitArea">
					<input type="submit" class="bType01" value="조회" />
				</div>
				
			</form>

			<button class="calculatorBtn bType02">환율계산기</button>
			
			<!-- Starg~End 날짜 기간의 환율 데이터 추가 test -->
			<a href="${root}exchange/addRateInquiry_DateRange">범위기간DB추가</a>

			<!-- 메일 전송 test -->
			<a href="${root}exchange/sendNoticeMail">메일전송</a>
			
			<div class="inquiryTable">
				<p class="txtRateBox">
					<span class="fl"> 기준일시 | <strong>${inquiryDate1}</strong>&nbsp;
					</span> <span class="fr"> (테스트용)기준일시 | <strong>${inquiryDate2}</strong>&nbsp;
					</span>
				</p>

				<table class="tType01">
					<thead>
						<tr>
							<th rowspan="2">구분</th>
							<th rowspan="2" colspan="2">통화표시</th>
							<th rowspan="2">매매기준율</th>
							<th colspan="2">송금</th>
						</tr>

						<tr>
							<th>받으실 때</th>
							<th>보내실 때</th>
						</tr>
					</thead>
					<!-- API 데이터를 가져와서 For Each -->
					<tbody>
						<c:forEach var="bean" items="${ExchangeRateList}">
							<tr>
								<td class="tLeft">${bean.code_money_name}</td>
								<td><img src="${root}img/Flags/${bean.code_money}.png"
									onerror="this.style.display='none'"></td>
								<td>${bean.code_money}</td>
								<td>${bean.ex_standard}</td>
								<td class="tRight">${bean.ex_buy}</td>
								<td class="tRight">${bean.ex_sell}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<c:if test="${null == ExchangeRateList or empty ExchangeRateList}">
					<p class="exRateNotFound">
						<span>해당 날짜의 환율 정보가 없습니다.</span>
					</p>
				</c:if>

			</div>
			<!-- div.inquiryTable -->
		</div>
		<!-- div.CP -->

	</div>
	<!-- div.contentWarp -->


	<c:import url="../include/bottom_info.jsp" />
	
	<script type="text/javascript" src="${root}js/exchange/inquiryDateBtn.js"></script>
	
</body>
</html>