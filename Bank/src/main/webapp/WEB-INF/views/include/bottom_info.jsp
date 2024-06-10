<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<link rel="stylesheet"
   href="${pageContext.request.contextPath}/css/teststyle_bottom.css">

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

<footer>
      <!-- <div class="bottom_bar" style="width: 100% !important;"></div> -->    
      
      <c:set var="root" value="${pageContext.request.contextPath}" />
      
      <div class="bottom_bar">
        <ul>
          <li><a href="#">회사 소개</a></li>
          <li><a href="#">개인정보 처리방침</a></li>
          <li><a href="#">고객센터</a></li>
          <li><a href="#">오시는 길</a></li>
        </ul>
        <p class="address">서울특별시 종로구 종로12길 15</p>
      </div>
      <div class="sns">
          <a href="#"><img src="${root}/img/youtube5050.png" alt="youtube"></a>
          <a href="#"><img src="${root}/img/instagram5050.png" alt="instagram"></a>
          <a href="#"><img src="${root}/img/facebook5050.png" alt="facebook"></a>
      </div>  
    </footer>  
    
    <script>
      document.addEventListener('DOMContentLoaded', function() {
          const currencySelect = document.getElementById('currencySelect');
          const currencyImage = document.getElementById('currencyImage');

          currencySelect.addEventListener('change', function() {
              const selectedValue = currencySelect.value;
              currencyImage.src = `img/${selectedValue}.png`;
              currencyImage.alt = `${selectedValue} 이미지`;
          });
      });
  </script>