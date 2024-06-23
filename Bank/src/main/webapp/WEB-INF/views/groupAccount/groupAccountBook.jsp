<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회비 관리</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="${root}css/management.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="row">
            <c:import url="/WEB-INF/views/include/top_menu.jsp" />
            <div class="col-md-2">
                <h3>모임통장 관리</h3>
                <ul>
                    <li><a href="${root}groupAccount/management">모임통장 정보</a></li>
                    <li><a href="${root}groupAccount/members">모임원 관리</a></li>
                    <li><a href="${root}groupAccount/book" class="active">회비 관리</a></li>
                </ul>
            </div>
            <div class="col-md-10">
                <div class="main">
                    <div class="traveltitle">
                        <div class="idboxtitle">회비 관리</div>
                        <hr />
                    </div>
                    <div class="contents">
                        <div class="contents-1">
                            <div class="group71">
                                <table id="feesTable" class="table">
                                    <thead>
                                        <tr>
                                            <th>날짜</th>
                                            <th>내역</th>
                                            <th>금액</th>
                                            <th>잔액</th>
                                            <th>액션</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="fee" items="${feesList}">
                                            <tr>
                                                <td>${fee.date}</td>
                                                <td>${fee.description}</td>
                                                <td>${fee.amount}</td>
                                                <td>${fee.balance}</td>
                                                <td>
                                                    <button class="btn btn-danger btn-sm" onclick="removeFee(${fee.id})">삭제</button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <button class="btn btn-primary" onclick="location.href='${root}groupAccount/addFee'">회비 추가</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                <footer>
    <c:import url="/WEB-INF/views/include/bottom_info.jsp" />
  </footer>
    </div>
</body>
<script>
function removeFee(feeId) {
    if(confirm('정말로 이 회비 내역을 삭제하시겠습니까?')) {
        $.ajax({
            url: '${root}groupAccount/removeFee',
            type: 'POST',
            data: { id: feeId },
            success: function(response) {
                location.reload();
            },
            error: function(err) {
                console.log('Error removing fee:', err);
            }
        });
    }
}
</script>
</html>
