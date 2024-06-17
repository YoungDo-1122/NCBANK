<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var='root' value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>acceptinvite</title>
  <link rel="stylesheet" href="${root}css/createnext.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f8f9fa;
      margin: 0;
      padding: 0;
    }
    .main {
      max-width: 600px;
      margin: auto;
      padding: 20px;
      text-align: center;
      background-color: #ffffff;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      border-radius: 8px;
      margin-top: 50px;
    }
    .info {
      text-align: left;
      margin-top: 20px;
    }
    .info span {
      display: block;
      margin: 5px 0;
    }
    .btn-invite {
      margin-top: 20px;
      background-color: #ffeb00;
      color: #3c1e1e;
      border: none;
      padding: 10px 20px;
      font-size: 16px;
      border-radius: 5px;
      cursor: pointer;
    }
    .btn-invite:hover {
      background-color: #ffd700;
    }
  </style>
</head>
<body>
  <c:import url="/WEB-INF/views/include/top_menu.jsp" />
  <div class="main">
    <h2>모임통장 초대</h2>
    <p>모임통장에 초대되었습니다. 수락하시겠습니까?</p>
    <form method="post" action="${root}groupAccount/acceptInvite">
      <input type="hidden" name="group_num" value="${group_num}" />
      <button type="submit" name="action" value="accept" class="btn btn-primary">수락</button>
      <button type="submit" name="action" value="decline" class="btn btn-secondary">거절</button>
    </form>
  </div>
  <c:import url="/WEB-INF/views/include/bottom_info.jsp" />
</body>
</html>
