<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>noticeSignUp.jsp - 환율알림신청</title>
</head>
<body>
	<h2>noticeSignUp</h2>
	<div id="b101901">

		<!-- if 환율 알림 등록시. 보여지는 안내문 -->
		<div class="box_type1">
			<ul class="list_type1">
				<li><b>아래와 같이 환율알림서비스를 신청하신 정보가 있습니다. 변경 또는 해지 가능합니다.</b></li>
				<li>선택하신 통화의 환율이 지정하신 알림희망 환율범위에 도달하는 경우 문자메시지(SMS) 또는 이메일을 통해
					안내 드립니다.</li>
				<li>3개월 내 지정하신 알림희망 환율범위에 도달하지 않았거나 도달하여 알림 안내를 받은 경우<br>
					서비스가 자동종료 되므로 추가로 알림을 받고자 하는 경우, 다시 서비스를 신청하시기 바랍니다.
				</li>
			</ul>
		</div>
		
		<!-- 여기를 form:form으로 ? -->
		<table>
			<caption>환율알림서비스 신청/변경/해지에 관한 표</caption>
			<colgroup> <!-- 열 그룹화 : 열단위 스타일링이나 속성 적용을 위해 -->
				<col width="20%">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<!-- scope="row" : th 요소가 해당 행의 데이터 셀들에 대한 헤더 -->
					<!-- b: 굵은 텍스트 | blind : css 로 숨기는 부분 (메모용) -->
					<th scope="row">
						<b class="txt-c4">* <span class="blind">필수입력</span></b>통화종류
					</th>
					<td>
						<input type="hidden" name="신청통화코드" id="신청통화코드" value="JPY">
						JPY(일본 100 엔)
					</td>
				</tr>
				
				<tr>
					<th scope="row">
						<b class="txt-c4">* <span class="blind">필수입력</span></b>환율종류
					</th>
					<td>매매기준율 
						<input type="hidden" name="고시환율기준구분코드" id="no2"	value="1">
					</td>
				</tr>
				
				<tr>
					<th scope="row">
						<b class="txt-c4">* <span class="blind">필수입력</span></b>알림희망환율범위
					</th>
					<td>875.00 ~ 879.00</td>
				</tr>
				
				<tr>
					<th scope="row" rowspan="2">
						<b class="txt-c4">* <span class="blind">필수입력</span></b>알림방법
					</th>
					<td>이메일 : jcjhj***@gmail.com</td>
				</tr>
				
				<tr>
					<td><label>문자메시지(SMS) : </label> 010 - 5736 - ****</td>
				</tr>
			</tbody>
		</table>


		<form name="IBF" method="post" action="">
			<input type="hidden" name="고객식별번호" value=""> 
			<input	type="hidden" name="신청인휴대폰번호"> 
			<input type="hidden" name="업무구분코드"> <input type="hidden" name="전문코드">
			<input	type="hidden" name="통지신청최저환율" value="875.00">
			<input	type="hidden" name="통지신청최고환율" value="879.00"> 
			<input	type="hidden" name="신청인이메일주소4" value="jcjhjg12@gmail.com"> 
			<input	type="hidden" name="신청인휴대폰번호4" value="010 - 5736 - ****">


			<!-- 환율알림서비스가 등록되어 있는 경우 노출 -->
			<div class="btnArea">
				<button type="button" class="btn-action c1"
					onclick="javascript:goView(2,2); return false;">재신청</button>
			</div>

		</form>

	</div>
	
</body>
</html>