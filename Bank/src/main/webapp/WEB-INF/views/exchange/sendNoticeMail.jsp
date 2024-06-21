<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>exchange/sendNoticeMail.jsp</title>
</head>
<link rel="stylesheet" href="${root}css/exchange/sendNoticeMail.css">

<body>
	<h2>인라인 이미지 테스트</h2>
 	<!-- 인라인으로 이미지 첨부 - cid:사전에정의한 인라인명 -->
 	<img src="cid:Bank1" alt="Bank1" onerror="this.onerror=null; this.src='${root}${Bank1Path}';">
 	
	<img src="cid:Bank1" alt="Bank1">
	<img src="cid:Bank2" alt="Bank1">
	<img src="cid:Bank3" alt="Bank1">
	<h2>이미지 테스트</h2>
	<br />
	
	<div class="top">
		<img src="cid:Bank1" style="width:100px; height:100px;"
		alt="NCBank" border="0" class="logoIcon">&nbsp; YDBank
	</div>
	
	<div class="content">
		
		<h2>환율알림서비스 안내</h2>
		<br />
		<p>선택하신 통화가 고객님이 요청하신 환율대에 도달하였습니다.</p>
		
		<!-- 환율알림서비스 신청 내역 -->
		<img src="https://ci3.googleusercontent.com/meips/ADKq_NYNdHOXipJ_RfwIBliViKWLZH21R1UGF2Z_CsKC23YU06scx_BvWrWqFTyqWyLDAVgsRLl-rr9LW6jtGzKsDuLOh9zxlAVyllMeZnJAtGy2uaLTAn9AoeaYqlGB-DP2uQ=s0-d-e1-ft#https://oimg1.kbstar.com/img/oemail/2019/dm_template/bullet_dot_brown2.jpg"
			alt="" class="CToWUd" data-bit="iit">
		<p>환율알림서비스 신청 내역</p>
		
		<p>${ExchangeNoticeBean.rateType}</p> <br />
		<p>${ExchangeNoticeBean.notice_rate}</p> <br />
		<p>${ExchangeNoticeBean.notice_email}</p> <br />
		<p>${ExchangeNoticeBean.notice_date}</p> <br />
		<p>${ExchangeNoticeBean.code_money}</p> <br />
		<p>${noticeDate}</p> <br />

		<table width="720" cellpadding="00" cellspacing="0"
			style="border-top: 1px solid #c1bdba">
			<tbody>
				<tr>
					<th width="20%" style="padding: 10px 5px; background: #f5f5f5; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						신청 일시
					</th>
					<td width="30%" style="padding: 10px 5px; background: #ffffff; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						${noticeDate}
					</td>
					
					<th width="20%"	style="padding: 10px 5px; background: #f5f5f5; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						통화종류
					</th>
					<td width="30%"	style="padding: 10px 5px; background: #ffffff; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						${ExchangeNoticeBean.code_money}
					</td>
				</tr>
				
				<tr>
					<th width="20%"	style="padding: 10px 5px; background: #f5f5f5; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						고시환율기준
					</th>
					<td width="30%"	style="padding: 10px 5px; background: #ffffff; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						<c:choose>
							<c:when test="${1 == ExchangeNoticeBean.rateType}">
								매매기준율
							</c:when>
							<c:when test="${2 == ExchangeNoticeBean.rateType}">
								송금보내실때
							</c:when>
							<c:when test="${3 == ExchangeNoticeBean.rateType}">
								송금받으실때
							</c:when>
							<c:otherwise>
								매매기준율
							</c:otherwise>
						</c:choose>
					</td>
					
					<th width="20%"	style="padding: 10px 5px; background: #f5f5f5; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						통지환율대
					</th>
					<td width="30%"	style="padding: 10px 5px; background: #ffffff; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						${ExchangeNoticeBean.notice_rate}&nbsp;원&nbsp;이하
					</td>
				</tr>
			</tbody>
		</table>
		
		<!-- 환율알림서비스 통지 결과 -->
		<img src="https://ci3.googleusercontent.com/meips/ADKq_NYNdHOXipJ_RfwIBliViKWLZH21R1UGF2Z_CsKC23YU06scx_BvWrWqFTyqWyLDAVgsRLl-rr9LW6jtGzKsDuLOh9zxlAVyllMeZnJAtGy2uaLTAn9AoeaYqlGB-DP2uQ=s0-d-e1-ft#https://oimg1.kbstar.com/img/oemail/2019/dm_template/bullet_dot_brown2.jpg"
			alt="" class="CToWUd" data-bit="iit">
		<p>환율알림서비스 통지 결과</p> <p>기준일시 : ${inquiryDate}</p>
		
		<p>${ExchangeRateBean.code_date}</p> <br />
		<p>${ExchangeRateBean.code_money}</p> <br />
		<p>${ExchangeRateBean.ex_buy}</p> <br />
		<p>${ExchangeRateBean.ex_sell}</p> <br />
		<p>${ExchangeRateBean.ex_standard}</p> <br />
		<p>${ExchangeRateBean.code_money_name}</p> <br />
		
		<!-- 여기 내 데이터에 맞게 테이블 수정해야됨 -->
		<table width="720" cellpadding="00" cellspacing="0"
			style="border-top: 1px solid #c1bdba">
			<tbody>
				<tr>
					<th width="12%" rowspan="2" style="padding: 10px 5px; background: #f5f5f5; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						통화
					</th>
					<th width="12%" rowspan="2" style="padding: 10px 5px; background: #f5f5f5; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						매매<br>기준율
					</th>
					<th width="20%" colspan="2"	style="padding: 10px 5px; background: #f5f5f5; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						전신환
					</th>
					<th width="20%" colspan="2"	style="padding: 10px 5px; background: #f5f5f5; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						현찰
					</th>
					<th width="12%" rowspan="2"style="padding: 10px 5px; background: #f5f5f5; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						여행자<br>수표(T/C)<br>사실
						때
					</th>
					<th width="12%" rowspan="2"style="padding: 10px 5px; background: #f5f5f5; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						대미환산율
					</th>
					<th width="12%" rowspan="2"style="padding: 10px 5px; background: #f5f5f5; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						외화수표<br>파실 때
					</th>
				</tr>
				
				<tr>
					<th width="10%"	style="padding: 10px 5px; background: #f5f5f5; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						보내실	때
					</th>
					<th width="10%"	style="padding: 10px 5px; background: #f5f5f5; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						받으실	때
					</th>
					<th width="10%"	style="padding: 10px 5px; background: #f5f5f5; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						사실 때
					</th>
					<th width="10%"	style="padding: 10px 5px; background: #f5f5f5; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						파실 때
					</th>
				</tr>
				
				<tr>
					<!-- 통화 -->
					<td width="12%"	style="text-align: center; padding: 10px 5px; background: #ffffff; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						${ExchangeRateBean.code_money}
					</td>
					<!-- 매매기준율 -->
					<td width="12%"	style="text-align: center; padding: 10px 5px; background: #ffffff; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						${ExchangeRateBean.ex_standard}
					</td>
					<!-- 전신환 | 보내실 때 -->
					<td width="10%"	style="text-align: center; padding: 10px 5px; background: #ffffff; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						${ExchangeRateBean.ex_sell}
					</td>
					<!-- 전신환 | 받으실 때 -->
					<td width="10%"	style="text-align: center; padding: 10px 5px; background: #ffffff; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						${ExchangeRateBean.ex_buy}
					</td>
					
					<td width="10%"	style="text-align: center; padding: 10px 5px; background: #ffffff; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						893.38
					</td>
					<td width="10%"	style="text-align: center; padding: 10px 5px; background: #ffffff; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						862.66
					</td>
					<td width="12%"	style="text-align: center; padding: 10px 5px; background: #ffffff; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						888.55
					</td>
					<td width="12%"	style="text-align: center; padding: 10px 5px; background: #ffffff; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						0.6346
					</td>
					<td width="12%"	style="text-align: center; padding: 10px 5px; background: #ffffff; border-left: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8; font-size: 13px; color: #222222">
						869.51
					</td>
				</tr>
			</tbody>
		</table>
		
		<ul style="padding: 0 0 0 5px; list-style-type: none; margin: 0">
			<li>· 실제 외환거래 시에는 거래시점의 고시환율이 적용됩니다.</li>
			<li>· 메일 통지 후 환율알림서비스 신청 내용은 자동 해지됩니다. 연장하거나 변경을 원하시면
				환율알림서비스 페이지의 [변경]버튼을 클릭하세요.</li>
		</ul>
		
		<p>본 메일은 NC은행에서 발송한 발신전용 메일입니다.</p>
		<a href="#">홈페이지 바로가기</a>
		
		<p>
			서울특별시 영등포구 국제금융로8길 26(여의도동) (주)NC은행 | 대표번호:1588-9999, 1599-9999<br>
			사업자등록번호 : 201-81-68693 | 대표자 : 박영도 | 통신판매신고 : 중구 제 00368호<br>
			Copyright kookmin bank.All rights reserved.
		</p>

	</div>

</body>
</html>