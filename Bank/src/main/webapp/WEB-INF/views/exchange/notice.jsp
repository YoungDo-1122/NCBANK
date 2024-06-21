<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>exchange/notice.jsp - 환율알림</title>
</head>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="${root}css/exchange/notice.css">

    <body>
        <c:import url="../include/top_menu.jsp" />
		
		
        <div class="contentWarp">

            <div class="LP">
                <div class="LPWrap">
                    <div class="LPC01">
                        <h2>외환</h2>
                        <ul>
                            <li><a href="${root}exchange/rateInquiry">환율 조회</a></li>
                            <li><a href="${root}exchange/notice">환율 알림 서비스</a></li>
                            <li><a href="${root}exchange/exchangeAsk">환전 신청</a></li>
                            <li><a href="${root}exchange/exchangeHistory">환전 내역 조회</a></li>
                        </ul>
                        
                        <ul>
                            <li class="rateCalculator">환율 계산기</li>
                        </ul>
                    </div>
                </div>
            </div>

            <div id="CP">
                <div id="title">
                    <div class="toparea">
                        <h1 class="h1_title">환율알림서비스</h1>
                    </div>
                    <ul class="tabMenu">

                        <c:choose>
                            <c:when test="${1 == noticContentIndex}">
                                <li class="on"><a href="${root}exchange/notice">안내</a></li>
                                <li><a href="${root}exchange/noticeRegister">신청/변경/해지</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${root}exchange/notice">안내</a></li>
                                <li class="on"><a href="${root}exchange/noticeRegister">신청/변경/해지</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
                <!-- div.title -->
                <!-- 환율 안내 관련 jsp 임포트 - 상황에 따라 임포트 변경 -->
                <!-- 1:안내 2:등록 3:변경 4:등록/변경 성공 5:삭제 성공 -->
                <c:choose>

                    <c:when test="${1 == noticContentIndex}">
                        <c:import url="./noticeGuide.jsp" />
                    </c:when>

                    <c:when test="${2 == noticContentIndex}">
                        <c:import url="./noticeRegister.jsp" />
                    </c:when>

                    <c:when test="${3 == noticContentIndex}">
                        <c:import url="./noticeEdit.jsp" />
                    </c:when>

                    <c:when test="${4 == noticContentIndex}">
                        <c:import url="./noiticeRegisterEditSuccess.jsp" />
                    </c:when>

                    <c:when test="${5 == noticContentIndex}">
                        <c:import url="./noticeDeleteSuccess.jsp" />
                    </c:when>

                    <c:otherwise>
                        <c:import url="./noticeGuide.jsp" />
                    </c:otherwise>
                </c:choose>

            </div> <!-- div.CP -->

        </div> <!-- div.contentWarp -->


        
        <c:import url="../include/bottom_info.jsp" />
        
        <script type="text/javascript">
            $(document).ready(function () { // 문서가 완전히 로드된 후 이벤트 핸들러 설정
                
            	$('.rateCalculator').on("click", function (e) {
                    e.preventDefault();

                    var url = "${root}/exchange/calculator"
                    var name = "환율 계산기"
                    var option = "width = 650px, height = 650px, top = 200px"
                    window.open(url, name, option);
                });
            	
                $('.rateChartBtn').on("click", function (e) {
                    e.preventDefault();

                    var url = "${root}/exchange/rateChart"
                    var name = "환율 차트"
                    var option = "width = 650px, height = 650px, top = 200px"
                    window.open(url, name, option);
                });

            }); // $(document).ready
        </script>

    </body>

</html>