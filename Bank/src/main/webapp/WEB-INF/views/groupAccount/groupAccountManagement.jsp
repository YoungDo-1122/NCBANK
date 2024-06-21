<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>모임통장 관리</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="${root}css/createnext.css"> -->
<link rel="stylesheet" href="${root}css/management.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
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

<script type="text/javascript">
    function formatDate(timestamp) {
        var date = new Date(timestamp);
        var year = date.getFullYear();
        var month = ('0' + (date.getMonth() + 1)).slice(-2);
        var day = ('0' + date.getDate()).slice(-2);
        return year + '-' + month + '-' + day;
    }

    function formatAutoType(autoType) {
        return autoType == 0 ? '주간' : '월간';
    }

    function formatAutoNextDate(autoType, autoNextDate) {
        return autoType == 0 ? autoNextDate + '요일' : autoNextDate + '일';
    }

    function fetchAccountInfo(account) {
        $.ajax({
            url: '${root}groupAccount/groupAccountInfo',
            type: 'GET',
            data: { account: account },
            success: function(data) {
                 $('#auto_balance').text(data.auto_balance + '원');
                 $('#auto_type').text(formatAutoType(data.auto_type));
                 $('#auto_next_date').text(formatAutoNextDate(data.auto_type, data.auto_next_date));
                 $('#auto_end').text(formatDate(data.auto_end));
            },
            error: function(err) {
                console.log('Error fetching account info:', err);
            }
        });

        $.ajax({
            url: '${root}groupAccount/groupInfo',
            type: 'GET',
            data: { account: account },
            success: function(data) {
                $('#group_joindate').text(formatDate(data.group_joindate));
                $('#group_leader').text(data.group_leader);
                $('#group_name').text(data.group_name);
                $('#group_num').val(data.group_num); // Hidden input에 group_num 설정
            },
            error: function(err) {
                console.log('Error fetching group info:', err);
            }
        });

        $.ajax({
            url: '${root}groupAccount/groupMembers',
            type: 'GET',
            data: { account: account },
            success: function(membersData) {
                $.ajax({
                    url: '${root}groupAccount/groupMemberDetails',
                    type: 'GET',
                    data: { account: account },
                    success: function(detailsData) {
                        var membersHtml = '';
                        $.each(membersData, function(index, member) {
                            var detail = detailsData[index];
                            membersHtml += '<tr><td>' + member.name + '</td><td>' + member.phone + '</td><td>' + (detail.group_leader == '1' ? '모임장' : '모임원') + '</td><td>' + formatDate(detail.group_joindate) + '</td></tr>';
                        });
                        $('#groupMembersTable tbody').html(membersHtml);
                    },
                    error: function(err) {
                        console.log('Error fetching member details info:', err);
                    }
                });
            },
            error: function(err) {
                console.log('Error fetching members info:', err);
            }
        });

        $.ajax({
            url: '${root}groupAccount/groupLeader',
            type: 'GET',
            data: { account: account },
            dataType: 'json',
            success: function(data) {
                console.log('Group Leader:', data); // 디버그용 로그
                if (data && data.name) {
                    $('#group_leader_name').text(data.name); // 모임장 이름 표시
                } else {
                    console.error('Received invalid data:', data);
                }
            },
            error: function(err) {
                console.log('Error fetching group leader info:', err);
            }
        });

        $.ajax({
            url: '${root}groupAccount/totalBalance',
            type: 'GET',
            data: { account: account },
            success: function(data) {
                $('#ac_balance').text(data.ac_balance + '원');
            },
            error: function(err) {
                console.log('Error fetching group info:', err);
            }
        });
    }

    function inviteToKakao() {
        var groupNum = $('#group_num').val();
        console.log("Inviting to Kakao with group_num: " + groupNum); // 디버그용 로그 추가
        if (groupNum && groupNum != '0') {
            sendKakaoLink(groupNum);
        } else {
            alert('모임 정보를 먼저 선택하세요.');
        }
    }
</script>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-2">
                <h3>메뉴</h3>
                <ul>
                    <li><a href="${root}groupAccount/management">모임통장 관리</a></li>
                    <li><a href="#">메뉴 항목 2</a></li>
                    <li><a href="#">메뉴 항목 3</a></li>
                </ul>
            </div>
            <div class="col-md-10">
                <c:import url="/WEB-INF/views/include/top_menu.jsp" />
                <div class="main">
                    <div class="traveltitle">
                        모임통장 관리
                        <hr />
                    </div>
                    <div class="contents">
                        <div class="contents-1">
                            <div class="group71">
                                <form action="${root}groupAccount/groupAccountOpened" method="get">
                                    <div class="flexClass">
                                        <span class="idbox">모임통장 전환 계좌</span> 
                                        <select id="accounts" name="accounts" class="rec6" onchange="fetchAccountInfo(this.value)">
                                            <option value="" selected>선택</option>
                                            <c:forEach var="groupAccount" items="${groupAccountList}">
                                                <option value="${groupAccount.account}">[NC뱅크]${groupAccount.account}</option>
                                            </c:forEach>
                                        </select> 
                                    </div>
                                </form>
                                <br>
                                <div class="section">
                                    <h3>모임통장 정보</h3>
                                    <table class="table">
                                        <tr>
                                            <th>자동이체 금액</th>
                                            <td><span id="auto_balance"></span></td>
                                        </tr>
                                        <tr>
                                            <th>자동이체 유형</th>
                                            <td><span id="auto_type"></span></td>
                                        </tr>
                                        <tr>
                                            <th>자동이체 날짜</th>
                                            <td><span id="auto_next_date"></span></td>
                                        </tr>
                                        <tr>
                                            <th>자동이체 종료일</th>
                                            <td><span id="auto_end"></span></td>
                                        </tr>
                                    </table>
                                </div>
                                <br>
                                <div class="section">
                                    <h3>모임 정보</h3>
                                    <table class="table">
                                        <tr>
                                            <th>가입 날짜</th>
                                            <td><span id="group_joindate"></span></td>
                                        </tr>
                                        <tr>
                                            <th>모임장</th>
                                            <td><span id="group_leader_name"></span></td>
                                        </tr>
                                        <tr>
                                            <th>모임 이름</th>
                                            <td><span id="group_name"></span></td>
                                        </tr>
                                        <tr>
                                            <th>모임통장 총 잔액</th>
                                            <td><span id="ac_balance"></span></td>
                                        </tr>
                                    </table>
                                </div>
                                <br>
                                <div class="section">
                                    <h3>모임원 정보</h3>
                                    <table id="groupMembersTable" class="table">
                                        <thead>
                                            <tr>
                                                <th>이름</th>
                                                <th>전화번호</th>
                                                <th>모임장 여부</th>
                                                <th>가입 날짜</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <!-- 모임원 정보-->
                                        </tbody>
                                    </table>
                                </div>
                                <input type="hidden" id="group_num" value="">
                            </div>
                        </div>
                    </div>
                </div>
                <button class="btn btn-primary btn-invite" onclick="inviteToKakao()">카카오톡으로 모임원 초대하기</button>
                <div class="footer">
                    <%-- <c:import url="/WEB-INF/views/include/bottom_info.jsp" /> --%>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
