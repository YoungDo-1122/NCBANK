<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>exchange/rateChart</title>

<!-- 통계 그래프/차트 를 만들기 위한 라이브러리 -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
	
	<!-- DB에 데이터 채우고 다시 -->
	<h2>환율차트</h2>
	
	<label for="currency">통화 선택:</label>
	<select id="currency" name="currency">
		<c:forEach var="obj" items="${ExchangeRateList}">
			<c:if test="${'KRW' != obj.code_money.toUpperCase().trim()}">
				<option class="op_code_money" value="${obj.code_money}">${obj.code_money}&nbsp;&nbsp;(${obj.code_money_name})</option>
			</c:if>
		</c:forEach>
	
		<option value="USD">USD</option>
		<option value="EUR">EUR</option>
		<option value="JPY">JPY</option>
	</select>

	<label for="period">기간 선택:</label>
	<select id="period" name="period">
		<option value="1week">1주</option>
		<option value="1month">1개월</option>
		<option value="3month">3개월</option>
	</select>

	<canvas id="exchangeRateChart" width="400" height="200"></canvas>

<script>
	
	// 선택한 통화의 기간별 : 1주 | 1개월 | 3개월
	// 기간 기준을 어캐잡지?
	
	// 객체 배열을 어캐 나눌지 고민좀.
	//const exchangeRates = {}
	
	/*
		${obj.code_date}; // 일시
		${obj.code_money}; // 통화코드
		${obj.ex_buy}; // 구매시
		${obj.ex_sell}; // 판매시
		${obj.ex_standard}; // 매매 기준율
		${obj.code_money_name}; // 통화코드명
	*/
	/*
	<c:forEach var="obj" items="${ExchangeRateList}">
		exchangeRates.standard["${obj.code_money}"] = ${obj.ex_standard};
		exchangeRates.buy["${obj.code_money}"] = ${obj.ex_buy};
		exchangeRates.sell["${obj.code_money}"] = ${obj.ex_sell};
	</c:forEach>
	*/

	// 레스트 풀 ( 프로트에서 바로 DB ) -> 이거안쓰면 모든 데이터가 ,DB에 존재 해야함
	// 임의의 기간별 환율 데이터 
	const exchangeRateData = {
		USD: {
	        dates: ["2024-06-07", "2024-06-08", "2024-06-09", "2024-06-10", "2024-06-11"],
	        rates: [1100.5, 1110.0, 1125.0, 1130.0, 1140.5]
	    },
	    EUR: {
	        dates: ["2024-06-07", "2024-06-08", "2024-06-09", "2024-06-10", "2024-06-11"],
	        rates: [1300.7, 1310.0, 1325.0, 1330.0, 1340.7]
	    },
	    JPY: {
	        dates: ["2024-06-07", "2024-06-08", "2024-06-09", "2024-06-10", "2024-06-11"],
	        rates: [10.25, 10.30, 10.35, 10.40, 10.45]
	    }
	};
	
	// period 보여질 기간
	function getRecentDates(period) {
		const today = new Date();
		let startDate;
		
		// 시작날짜 설정
		switch (period) {
		case "1week":
			startDate = new Date(today.getTime() - 7 * 24 * 60 * 60 * 1000); // 7일 전
			break;
			
		case "1month":
			startDate = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate()); 
			break;
			
		case "3month":
			startDate = new Date(today.getFullYear(), today.getMonth() - 3, today.getDate()); 
			break;
			
		default:
			startDate = new Date(today.getFullYear(), today.getMonth(), today.getDate()); 
			break;
		}
		
		const formattedStartDate = formatDate(startDate);
		const recentDates = [];
		
		// 시작날짜보다 뒤에 있는 날짜 걸러내기
		for (var i = 0; i < exchangeRateData.USD.dates.length; i++) {
			if (exchangeRateData.USD.dates[i] >= formattedStartDate) {
				recentDates.push(exchangeRateData.USD.dates[i]);
			}
		} // for
		
		return recentDates;
	} // getRecentDates
	
	function formatDate(date) { // 포멧 설정 yyyy-MM-dd
		const year = date.getFullYear();
		let month = date.getMonth() + 1;
		if (month < 10) {
			month = '0' + month;
		}
		let day = date.getDate();
		if (day < 10) {
			day = '0' + day;
		}
		return `${year}-${month}-${day}`;
	} // formatDate
	
	// 차트 객체 저장 변수
	let exchangeRateChart;
	
	function renderChart(currency, period) {
		const ctx = document.getElementById('exchangeRateChart').getContext('2d');
		const dates = getRecentDates(period);
		const rates = [];
	    
		for (let i = 0; i < dates.length; i++) {
			const index = exchangeRateData[currency].dates.indexOf(dates[i]);
			if (index !== -1) {
				rates.push(exchangeRateData[currency].rates[index]);
			}
		}

		if (exchangeRateChart) {
			exchangeRateChart.destroy();
		}

		exchangeRateChart = new Chart(ctx, {
			type: 'line',
			data: {
				labels: dates,
				datasets: [{
					label: `${currency} 환율 (${period})`,
					data: rates,
					backgroundColor: 'rgba(75, 192, 192, 0.2)',
					borderColor: 'rgba(75, 192, 192, 1)',
					borderWidth: 1,
					fill: false
				}]
			},
			options: {
				scales: {
					y: {
						beginAtZero: false
					}
				}
			}
		});
	} // renderChart

	document.getElementById('currency').addEventListener('change', function() {
		const selectedCurrency = this.value;
		const selectedPeriod = document.getElementById('period').value;
		renderChart(selectedCurrency, selectedPeriod);
	});

	$("#ISOSelBox").change(function() {
		console.log("ISOSelBox.change()");
		
		var ISOCode = $('#ISOSelBox').val();
		console.log("ISOCode : " + ISOCode);
	});

	// 초기 그래프 렌더링
	renderChart('USD', '1week');

</script>

</body>
</html>
