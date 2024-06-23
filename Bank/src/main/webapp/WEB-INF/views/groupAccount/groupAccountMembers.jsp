<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>모임원 관리</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="${root}css/management.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script>
// 카카오 초기화
Kakao.init('ff8ba07dd1c6c1c318c25c022ce8bb5e'); // 앱 키

function sendKakaoLink(groupNum) {
    Kakao.Link.sendCustom({
        templateId: 109036, // 템플릿 ID
        templateArgs: {
            'group_num': groupNum // 동적 group_num 전달
        }
    });
}
</script>
<style>
footer {
      text-align: center;
      padding: 10px;
      background: #f8f9fa;
      flex-shrink: 0;
    }
</style>
</head>
<body>
    <div class="container">
        <div class="row">
            <c:import url="/WEB-INF/views/include/top_menu.jsp" />
            <div class="col-md-2">
                <h3>모임통장 관리</h3>
                <ul>
                    <li><a href="${root}groupAccount/management">모임통장 정보</a></li>
                    <li><a href="${root}groupAccount/members" class="active">모임원 관리</a></li>
                    <li><a href="${root}groupAccount/book">회비 관리</a></li>
                </ul>
            </div>
            <div class="col-md-10">
                <div class="main">
                    <div class="traveltitle">
                        <div class="idboxtitle">모임원 관리</div>
                        <hr />
                    </div>
                    <div class="contents">
                        <div class="contents-1">
                            <div class="group71">
                                <table id="groupMembersTable" class="table">
                                    <thead>
                                        <tr>
                                            <th>이름</th>
                                            <th>전화번호</th>
                                            <th>모임장 여부</th>
                                            <th>가입 날짜</th>
                                            <th>액션</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="member" items="${groupMembers}">
                                            <tr>
                                                <td>${member.name}</td>
                                                <td>${member.phone}</td>
                                                <td><c:if test="${member.isLeader}">모임장</c:if><c:if test="${!member.isLeader}">모임원</c:if></td>
                                                <td>${member.joinDate}</td>
                                                <td>
                                                    <button class="btn btn-danger btn-sm" onclick="removeMember(${member.id})">삭제</button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <button class="btn btn-primary" onclick="inviteToKakao()">모임원 초대하기</button>
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
function removeMember(memberId) {
    if(confirm('정말로 이 회원을 삭제하시겠습니까?')) {
        $.ajax({
            url: '${root}groupAccount/removeMember',
            type: 'POST',
            data: { id: memberId },
            success: function(response) {
                location.reload();
            },
            error: function(err) {
                console.log('Error removing member:', err);
            }
        });
    }
}
</script>
</html>
