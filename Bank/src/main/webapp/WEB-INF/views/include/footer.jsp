<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<footer>
      <!-- <div class="bottom_bar" style="width: 100% !important;"></div> -->    
      
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
          <a href="#"><img src="./img/youtube5050.png" alt="youtube"></a>
          <a href="#"><img src="./img/instagram5050.png" alt="instagram"></a>
          <a href="#"><img src="./img/facebook5050.png" alt="facebook"></a>
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