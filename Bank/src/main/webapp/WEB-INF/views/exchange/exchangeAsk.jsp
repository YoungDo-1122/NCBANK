<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/teststyle_top.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환전 신청</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/exchangeAsk.css">
</head>
<body>

<div class="sidebar">
    <div class="sidebar-item" onclick="toggleMenu('exchange-rate-menu')">
        환율
        <div id="exchange-rate-menu" class="submenu" style="display: none;">
            <ul>
                <li><a href="#">환율정보</a></li>
                <li><a href="#">환율계산기</a></li>
            </ul>
        </div>
    </div>
    <div class="sidebar-item" onclick="toggleMenu('exchange-menu')">
        환전
        <div id="exchange-menu" class="submenu" style="display: none;">
            <ul>
                <li><a href="#">환전신청</a></li>
                <li><a href="#">환전조회</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="main-content">
    <h1>환전 신청</h1>
    <form:form action="${root}exchange/exchangeAskSuccess" method="post" modelAttribute="createExchangeBean">

        <div class="form-group">
            <form:label path="code_money">환전신청금액</form:label>
            <div class="input-group">
                <form:select path="code_money" id="code_money" style="width: 100px;">
                    <form:option value="AED">AED</form:option>
                    <form:option value="AUD">AUD</form:option>
                    <form:option value="BHD">BHD</form:option>
                    <form:option value="BND">BND</form:option>
                    <form:option value="CAD">CAD</form:option>
                    <form:option value="CHF">CHF</form:option>
                    <form:option value="CNH">CNH</form:option>
                    <form:option value="DKK">DKK</form:option>
                    <form:option value="EUR">EUR</form:option>
                    <form:option value="GBP">GBP</form:option>
                    <form:option value="HKD">HKD</form:option>
                    <form:option value="IDR">IDR</form:option>
                    <form:option value="JPY">JPY</form:option>
                    <form:option value="KRW">KRW</form:option>
                    <form:option value="KWD">KWD</form:option>
                    <form:option value="MYR">MYR</form:option>
                    <form:option value="NOK">NOK</form:option>
                    <form:option value="NZD">NZD</form:option>
                    <form:option value="SAR">SAR</form:option>
                    <form:option value="SEK">SEK</form:option>
                    <form:option value="SGD">SGD</form:option>
                    <form:option value="THB">THB</form:option>
                    <form:option value="USD">USD</form:option>
                </form:select>
                <form:input path="trade_money" class="form-control" id="trade_money_input" onkeyup="getExchangeMoney(); hideExchangeTable();" style="flex: 2;"/>
                <button type="button" id="trade_money_btn">환전예상금액 확인</button>
            </div>
        </div>

        <table border="1" id="exchangeTable" style="display: none;">
            <tr>
                <th>원화금액</th>
                <td id="exchange_payMoney" style="width: 200px;"></td>
            </tr>
            <tr>
                <th>현재고시환율</th>
                <td id="exchange_cash_buying_rate"></td>
            </tr>
            <tr>
                <th>적용환율</th>
                <td id="trade_rate"></td>
            </tr>
            <tr>
                <th>우대금액</th>
                <td id="preferential_money"></td>
            </tr>
        </table>
        
        <!-- 히든으로 숨겨가기(원화금액, 현재고시환율, 적용환율, 우대금액) -->
        <form:hidden path="exchange_payMoney" id="hidden_exchange_payMoney"/>
        <form:hidden path="exchange_cash_buying_rate" id="hidden_exchange_cash_buying_rate"/>
        <form:hidden path="trade_rate" id="hidden_trade_rate"/>
        <form:hidden path="preferential_money" id="hidden_preferential_money"/>
        
        <h1>고객정보 입력</h1>
        <div class="form-group">
            <form:label path="account">출금계좌선택</form:label>
            <form:select path="account" id="account">
                <c:forEach var="accountItem" items="${getAccountList}">
                    <form:option value="${accountItem.account}">${accountItem.account}</form:option>
                </c:forEach>
            </form:select>
        </div>

        <h1>수령정보 입력</h1>
        <div class="form-group">
            <!-- 수정된 부분 시작 -->
            <input type="text" id="searchAddr" name="searchAddr"></input>
            <button type="button" id="searchAddrBtn" class="btn btn-secondary" onclick="searchBank()">조회</button>
            <!-- 수정된 부분 끝 -->
        </div>

        <div class="form-group">
		    <form:label path="code_bank_name">수령희망지점</form:label>
		    <form:select path="code_bank_name" id="code_bank_name">
		        <c:forEach var="codeBankitem" items="${codeBankNameList}">
		            <option value="${codeBankitem.code_bank_name}">${codeBankitem.code_bank_name}</option>
		        </c:forEach>
		    </form:select>
		   
		</div>
        
        <div class="form-group">
            <form:label path="trade_reservation_date">수령희망일</form:label>
            <form:input path="trade_reservation_date" type="date" class="form-control" id="trade_reservation_date"/>
        </div>

        <div class="form-group">
            <div class="text-right">
                <button type="submit" class="btn btn-primary">다음</button>
            </div>
        </div>
        
    </form:form>
</div>

<script>
	// 수령정보 위치 설정
	var codeBankList = {
	    <c:forEach var="codeBankitem" items="${codeBankNameList}">
	        '${codeBankitem.code_bank_address}': '${codeBankitem.code_bank_name}'<c:if test="${codeBankitem != codeBankNameList[codeBankNameList.size() - 1]}">,</c:if>
	    </c:forEach>
	};
	console.log("codeBankList: ", codeBankList);
	
	function searchBank() {
		// 검색 키워드
	    var keyword = document.getElementById("searchAddr").value.toLowerCase();
		// Select
	    var $select = document.getElementById("code_bank_name");
	
	    // 기존 옵션 제거
	    while ($select.options.length > 0) {
	        $select.remove(0);
	    }
	
	    var hasOptions = false;
	
	    // 키워드와 일치하는 옵션만 추가
	    for (var address in codeBankList) {
	        if (address.toLowerCase().indexOf(keyword) !== -1) {
	            var option = document.createElement("option");
	            option.value = codeBankList[address];
	            option.text = codeBankList[address];
	            $select.appendChild(option);
	            hasOptions = true;
	        }
	    }
	
	    if (!hasOptions) {
	        var option = document.createElement("option");
	        option.value = "";
	        option.text = "일치하는 결과가 없습니다";
	        $select.appendChild(option);
	    }
	}

 	// -- 환전 신청 JavaScript --

    // 우대율
    const preferentialRate = {
        USD_Prefer: 0.9, 
        JPY_Prefer: 0.8, EUR_Prefer: 0.8, 
        CNH_Prefer: 0.5, GBP_Prefer: 0.5, CAD_Prefer: 0.5, AUD_Prefer: 0.5, NZD_Prefer: 0.5, HKD_Prefer: 0.5, SGD_Prefer: 0.5, CHF_Prefer: 0.5, THB_Prefer: 0.5, 
        MYR_Prefer: 0.2, VND_Prefer: 0.2, IDR_Prefer: 0.2, PHP_Prefer: 0.2, TWD_Prefer: 0.2
    };

    // 살때 Spread
    const spread = {
        USD_Spread: 0.0175, JPY_Spread: 0.0175, EUR_Spread: 0.0198, GBP_Spread: 0.0199, CHF_Spread: 0.0199, CAD_Spread: 0.0199, 
        AUD_Spread: 0.0199, NZD_Spread: 0.0199, HKD_Spread: 0.0199, SGD_Spread: 0.0200, SEK_Spread: 0.0250, DKK_Spread: 0.0250, NOK_Spread: 0.0250, 
        CNH_Spread: 0.0600, SAR_Spread: 0.0500, AED_Spread: 0.0500, 
        THB_Spread: 0.0596, IDR_Spread: 0.0800, KWD_Spread: 0.0600, 
        BHD_Spread: 0.0600, MYR_Spread: 0.0600
    };

    // 환전 신청 JavaScript
    var tradeMoney;
    var tradeRate = {
        <c:forEach var="item" items="${exchangeAskList}">
            '${item.code_money}': ${item.ex_standard}<c:if test="${item != exchangeAskList[exchangeAskList.size() - 1]}">,</c:if>
        </c:forEach>
    };
    console.log("tradeRate: "+tradeRate)
    function hideExchangeTable() {
        document.getElementById("exchangeTable").style.display = "none";
    }

    document.getElementById("trade_money_input").addEventListener("input", hideExchangeTable);

    function getExchangeMoney() {
        tradeMoney = document.getElementById("trade_money_input").value;
        var selectedCurrency = document.getElementById("code_money").value;
        // rate = 매매기준율(ex_standard)
        var rate = tradeRate[selectedCurrency];
        console.log("rate: "+rate)
        
        // Spread와 Prefer를 구하는 부분
        var spreadKey = selectedCurrency + '_Spread';
        var preferKey = selectedCurrency + '_Prefer';
        console.log("spreadKey: "+spreadKey)
        console.log("preferKey: "+preferKey)
        
        var selectedSpread = spread[spreadKey] || 0;
        var selectedPreferentialRate = preferentialRate[preferKey] || 0;
        
        // #1. Spread X
        if(selectedCurrency === "KRW" || selectedCurrency === "BND"){
            document.getElementById("exchange_payMoney").innerText = 		 Math.round(tradeMoney * rate) + "원";
            document.getElementById("exchange_cash_buying_rate").innerText = Math.round((rate + Number.EPSILON) * 100) / 100 + "원";
            document.getElementById("trade_rate").innerText = 				 Math.round((rate + Number.EPSILON) * 100) / 100 + "원";
            document.getElementById("preferential_money").innerText = 		 0 + "원";
            
            document.getElementById("hidden_exchange_payMoney").value = 		Math.round(tradeMoney * rate);
            document.getElementById("hidden_exchange_cash_buying_rate").value = Math.round((rate + Number.EPSILON) * 100) / 100;
            document.getElementById("hidden_trade_rate").value = 				Math.round((rate + Number.EPSILON) * 100) / 100;
            document.getElementById("hidden_preferential_money").value = 		0;
        }
        
        // #2. Spread O
        // #2-1. 우대율 O
        if(selectedCurrency === "AUD" || selectedCurrency === "CAD" || selectedCurrency === "CHF" || 
            selectedCurrency === "CNH" || selectedCurrency === "EUR" || selectedCurrency === "GBP" || 
            selectedCurrency === "HKD" || selectedCurrency === "IDR" || selectedCurrency === "JPY" || 
            selectedCurrency === "MYR" || selectedCurrency === "NZD" || selectedCurrency === "SGD" || 
            selectedCurrency === "THB" || selectedCurrency === "USD" ) {
            
            if(selectedCurrency === "JPY" || selectedCurrency === "IDR" ){
                document.getElementById("exchange_payMoney").innerText = 		 (Math.round(((((rate + rate * selectedSpread * (1 - selectedPreferentialRate)) * tradeMoney)/100 + Number.EPSILON) * 100)) / 100).toFixed(2) + "원";
                document.getElementById("exchange_cash_buying_rate").innerText = (Math.round((((rate + rate * selectedSpread) + Number.EPSILON) * 100)) / 100).toFixed(2) + "원";
                document.getElementById("trade_rate").innerText = 				 (Math.round((((rate + rate * selectedSpread * (1 - selectedPreferentialRate)) + Number.EPSILON) * 100)) / 100).toFixed(2) + "원";
                document.getElementById("preferential_money").innerText = 		 (Math.round((((tradeMoney * rate * selectedSpread * selectedPreferentialRate) + Number.EPSILON) * 100)/100) / 100).toFixed(2) + "원";
                
                document.getElementById("hidden_exchange_payMoney").value = 		Math.round(((((rate + rate * selectedSpread * (1 - selectedPreferentialRate)) * tradeMoney) / 100 + Number.EPSILON) * 100)) / 100;
                document.getElementById("hidden_exchange_cash_buying_rate").value = Math.round((((rate + rate * selectedSpread) + Number.EPSILON) * 100)) / 100;
                document.getElementById("hidden_trade_rate").value = 				Math.round((((rate + rate * selectedSpread * (1 - selectedPreferentialRate)) + Number.EPSILON) * 100)) / 100;
                document.getElementById("hidden_preferential_money").value = 		Math.round((((tradeMoney * rate * selectedSpread * selectedPreferentialRate) + Number.EPSILON) * 100)/100) / 100;
            } else {
                document.getElementById("exchange_payMoney").innerText = 		 (Math.round(((((rate + rate * selectedSpread * (1 - selectedPreferentialRate)) * tradeMoney) + Number.EPSILON) * 100)) / 100).toFixed(2) + "원";
                document.getElementById("exchange_cash_buying_rate").innerText = (Math.round((((rate + rate * selectedSpread) + Number.EPSILON) * 100)) / 100).toFixed(2) + "원";
                document.getElementById("trade_rate").innerText = 				 (Math.round((((rate + rate * selectedSpread * (1 - selectedPreferentialRate)) + Number.EPSILON) * 100)) / 100).toFixed(2) + "원";
                document.getElementById("preferential_money").innerText = 		 (Math.round((((tradeMoney * rate * selectedSpread * selectedPreferentialRate) + Number.EPSILON) * 100)) / 100).toFixed(2) + "원";
                
                document.getElementById("hidden_exchange_payMoney").value = 		Math.round(((((rate + rate * selectedSpread * (1 - selectedPreferentialRate)) * tradeMoney) + Number.EPSILON) * 100)) / 100;
                document.getElementById("hidden_exchange_cash_buying_rate").value = Math.round((((rate + rate * selectedSpread) + Number.EPSILON) * 100)) / 100;
                document.getElementById("hidden_trade_rate").value = 				Math.round((((rate + rate * selectedSpread * (1 - selectedPreferentialRate)) + Number.EPSILON) * 100)) / 100;
                document.getElementById("hidden_preferential_money").value = 		Math.round((((tradeMoney * rate * selectedSpread * selectedPreferentialRate) + Number.EPSILON) * 100)) / 100;
            }
        }

        // #2-2. 우대율X
        if(selectedCurrency === "AED" || selectedCurrency === "BHD" || selectedCurrency === "KWD" || 
            selectedCurrency === "DKK" || selectedCurrency === "NOK" || selectedCurrency === "SAR" || 
            selectedCurrency === "SEK") {
                
            document.getElementById("exchange_payMoney").innerText = 		 (Math.round(((((rate + rate * selectedSpread) * tradeMoney) + Number.EPSILON) * 100)) / 100).toFixed(2) + "원";
            document.getElementById("exchange_cash_buying_rate").innerText = (Math.round((((rate + rate * selectedSpread) + Number.EPSILON) * 100)) / 100).toFixed(2) + "원";
            document.getElementById("trade_rate").innerText = 				 (Math.round((((rate + rate * selectedSpread) + Number.EPSILON) * 100)) / 100).toFixed(2) + "원";
            document.getElementById("preferential_money").innerText = 		 0 + "원";
            
            document.getElementById("hidden_exchange_payMoney").value = 		Math.round(((((rate + rate * selectedSpread) * tradeMoney) + Number.EPSILON) * 100)) / 100;
            document.getElementById("hidden_exchange_cash_buying_rate").value = Math.round((((rate + rate * selectedSpread) + Number.EPSILON) * 100)) / 100;
            document.getElementById("hidden_trade_rate").value = 				Math.round((((rate + rate * selectedSpread) + Number.EPSILON) * 100)) / 100;
            document.getElementById("hidden_preferential_money").value = 		0;
        }
    }

    document.getElementById("trade_money_btn").addEventListener("click", function() {
        var table = document.getElementById("exchangeTable");
        getExchangeMoney();
        table.style.display = "block";
    });

    document.getElementById("code_money").addEventListener("change", function() {
        var table = document.getElementById("exchangeTable");
        table.style.display = "none";
    });

    // 사이드바
    function toggleMenu(menuId) {
        var menus = document.getElementsByClassName('submenu');
        for (var i = 0; i < menus.length; i++) {
            menus[i].style.display = 'none';
        }
        var menu = document.getElementById(menuId);
        if (menu.style.display === 'none') {
            menu.style.display = 'block';
        } else {
            menu.style.display = 'none';
        }
    }
</script>
</body>
</html>