<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>user_join.jsp</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<script>
   function checkUserIdExist() {
      // let var 상관없음, 근데 let은 재 선언 불가
      // $("#user_id") : Jquery - id값으로 값을 가져옴
      var id = $("#id").val()
      if (0 >= id.length) { // 아이디를 입력하지 않은경우 예외처리
         alert('아이디를 입력해 주세요.')
         return
      }
      
      // ajax 통신
      $.ajax({
         // #1 요청할 주소 - 상대경로로 만들어줌
         url: '${root}/user/checkUserIdExist/' + id,
         // #2 요청 타입
         type: 'get',
         // #3 응답 결과
         dataType: 'text', // text, exel, json 등
         // #4 성공 시 호출할 함수
         success: function(result) {
            // trim() : 양쪽의 공백을 짤라준다.
            if('true' == result.trim()){
                  alert('사용할 수 있는 아이디입니다')
                  $("#idExistCheck").val('true')
              } else if('false' == result.trim()){
                  alert('사용할 수 없는 아이디 입니다')
                  $("#idExistCheck").val('false')
              }
         }
      }) // $.ajax
   } // checkUserIdExist
   
   // 사용자가 아이디란에 입력한 상태
   function resetUserIdExist(){
      $("#idExistCheck").val('false')
   }
   
   $(document).ready(function() {
      const joinDate = new Date();
      const formattedDate = joinDate.toISOString().split('T')[0]; // yyyy-mm-dd 형식으로 변환
      $('#join_date').val(formattedDate);   
      
      
    });
   
   function sendSMSVerification() {
       alert('SMS 인증 기능은 아직 구현되지 않았습니다.');
       // 실제 SMS 인증 로직을 이곳에 추가
   }
</script>
<body>
   <c:import url="../include/top_menu.jsp" />
   <div class="container" style="margin-top: 100px">
      <div class="row">
         <div class="col-sm-3"></div>
         <div class="col-sm-6">
            <div class="card shadow">
               <div class="card-body">
                  <!-- form은 넘어갈때 내부의 input 요소를 다 가져간다 -->
                  <!-- modelAttribute="joinUserBean" : 객체를 가지고 UserBean에서 get set을 붙이고 감 -->
                  <form:form action="${root}/user/join_pro" method="post"
                     modelAttribute="mBean">
                     <form:hidden path="idExistCheck" />
                     <div class="form-group">
                        <!-- form: 의 path는 id,class,name 통합 -->
                        <form:label path="name">이름</form:label>
                        <form:input path="name" placeholder="이름" class='form-control' />
                        <form:errors path="name" style='color:red' />
                     </div>

                     <div class="form-group">
                        <form:label path="resident">주민번호</form:label>
                        <form:input path="resident" placeholder="주민번호 입력('-'까지 입력해주세요)"
                           class='form-control' />
                        <%--    <form:input path="resident" placeholder="앞자리 (######)" class='form-control' maxlength="6"/>
                        <form:password path="resident" placeholder="뒤자리 (#######)" class='form-control' maxlength="7"/> --%>
                        <form:errors path="resident" style='color:red' />
                     </div>

                     <div class="form-group">
                        <form:label path="id">아이디</form:label>
                        <div class="input-group">
                           <form:input path="id" class='form-control' placeholder="아이디"
                              onkeypress="resetUserIdExist()" />
                           <div class="input-group-append">
                              <button type="button" class="btn btn-primary"
                                 onclick="checkUserIdExist()">중복확인</button>
                           </div>
                        </div>
                        <form:errors path="id" style='color:red' />
                     </div>

                     <div class="form-group">
                        <form:label path="pwd">비밀번호</form:label>
                        <form:password path="pwd" placeholder="비밀번호"
                           class='form-control' />
                        <form:errors path='pwd' style='color:red' />
                     </div>

                     <div class="form-group">
                        <form:label path="pwd2">비밀번호 확인</form:label>
                        <form:password path="pwd2" placeholder="비밀번호 확인"
                           class='form-control' />
                        <form:errors path='pwd2' style='color:red' />
                     </div>

                     <div class="form-group">
                        <form:label path="address">주소</form:label>
                        <form:input path="address" placeholder="주소를 입력하세요."
                           class='form-control' />
                     </div>
                     <div class="form-group">
                        <form:label path="phone">전화번호</form:label>
                        <form:input path="phone" placeholder="전화번호 ('-'까지 입력해주세요)"
                           class='form-control' />
                        <div class="input-group-append">
                           <button type="button" class="btn btn-primary"
                              onclick="sendSMSVerification()">SMS 인증</button>
                        </div>
                        <form:errors path='phone' style='color:red' />
                     </div>
                     <div class="form-group">
                        <form:label path="email" type="email">이메일</form:label>
                        <form:input path="email" type="email" placeholder="(선택)이메일"
                           class='form-control' />
                     </div>
                     <div class="form-group">
                        <form:hidden path="join_date" />
                     </div>
                     <div class="form-group">
                        <div class="text-right">
                           <form:button class='btn btn-primary'>회원가입</form:button>
                        </div>
                     </div>
                  </form:form>
               </div>
            </div>
         </div>
         <div class="col-sm-3"></div>
      </div>
   </div>
   <c:import url="../include/bottom_info.jsp" />
</body>
</html>