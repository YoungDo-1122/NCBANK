<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/teststyle_bottom.css">
<script>
	document.addEventListener('DOMContentLoaded', function() {
		const currencySelect = document.getElementById('currencySelect');
		const currencyImage = document.getElementById('currencyImage');

		currencySelect.addEventListener('change', function() {
			const selectedValue = currencySelect.value;
			currencyImage.src = `img/${selectedValue}.png`;
			currencyImage.alt = `${selectedValue} 이미지`;
		});
	});
</script>
<footer>
	<!-- <div class="bottom_bar" style="width: 100% !important;"></div> -->

	<div class="bottom_bar">
		<a href="#" class="tit">대표전화 1588-9999</a>
		<div class="info">
			<dl>
				<dt>대표전화</dt>
				<dd>
					<ul>
						<li>1588-9999, 1599-9999, 1644-9999</li>
						<li><span>해외에서 국내로 걸 때</span>+82-2-6300-9999</li>
						<li class="groupLine">대출 단기연체 및 만기안내<br> <em class="s1">1588-9008</em></li>
						<li><span>해외에서 국내로 걸 때</span>+82-2-3702-1240</li>
					</ul>
				</dd>
				<dt>상담전용</dt>
				<dd>
					<ul>
						<li>1800-9999<span>(분실 및 사고신고 제외)</span></li>
						<li class="groupLine">투자상품 승낙확인<br> <em class="s1">1833-3938</em></li>
						<li><span>해외에서 국내로 걸 때</span>+82-1833-3938</li>
					</ul>
				</dd>
				<dt>기업전용</dt>
				<dd>1599-9499</dd>
				<dt>기업(B2B)</dt>
				<dd>1566-9944</dd>
				<dt>외국인전용</dt>
				<dd>1599-4477</dd>
				<dt>어르신전용</dt>
				<dd>1644-3308</dd>
			</dl>
			<div class="sns">
				<a href="www.youtube.com"><img src="img/youtube5050.png"
					alt="youtube"></a> <a href="www.instagram.com"><img
					src="img/instagram5050.png" alt="instagram"></a> <a
					href="www.facebook.com"><img src="img/facebook5050.png"
					alt="facebook"></a>
			</div>
		</div>
	</div>
</footer>