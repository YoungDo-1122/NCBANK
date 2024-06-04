
/* 전체 */
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
}

/* [본문] */
.container {
  margin: 100px;
}

/* [헤더] */
/* [헤더] 전체 */
.main_bar {
  height: 100px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #004080;
  padding: 20px;
  color: white;
}


.main_bar .logo img {
  width: 120px;
  height: 120px;
}

/* [헤더] 메인메뉴 */
.main_bar .main_menu {
  display: flex;
  list-style-type: none;
}

/* [헤더] 메인메뉴 목록 */
.main_bar .main_menu>li {
  position: relative;
  margin-right: 20px;
}

/* [헤더] 메인메뉴 목록 글자*/
.main_bar .main_menu>li>a {
  color: white;
  text-decoration: none;
  text-align: center;
  margin-right: 50px;
  font-size: 20px;
}

.main_bar .main_menu>li:hover .main_submenu {
  display: block;
}

.main_bar .main_submenu {
  display: none;
  position: absolute;
  top: 100%;
  left: 0;
  background-color: #004080;
  list-style-type: none;
  padding: 0;
  margin-top: 30px;
}

.main_bar .main_submenu li {
  margin: 0;
}

.main_bar .main_submenu li a {
  color: white;
  text-decoration: none;
  padding: 10px 20px;
  display: block;
  white-space: nowrap;
  background-color: black;
}

.main_bar .main_button {
  display: flex;
  align-items: center;
  margin-top: -10px;
}

.main_bar .main_button>button {
  background-color: #0080ff;
  border: none;
  color: white;
  padding: 10px;
  margin-left: 30px;
  cursor: pointer;
  width: 90px;
  height: 50px;
  min-width: 90px;
}

/* [콘테이너] 카라우셀 */

.carousel-wrapper {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.carousel {
  width: 50%;
  min-width: 600px;
  overflow: hidden;
  position: relative;
  
}

.carousel img {
  width: 100%;
  height: auto;
  display: none;
}

.carousel img.active {
  display: block;
}

.carousel .carousel-button {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  background-color: rgba(0, 0, 0, 0.5);
  border: none;
  color: white;
  padding: 10px;
  cursor: pointer;
  z-index: 10;
}

.carousel .prev {
  left: 0;
}

.carousel .next {
  right: 0;
}

/* [콘테이너] 빠른링크 */
.quickLink{  
  float:right;
  width:600px;
  height:100%;
  margin-top:10px;

}
.quickLink ul{
  padding:0;
  overflow: hidden;
}
.quickLink ul li{
  float:left;
  width:33%;
  text-align:center;
  margin:10px 0;
  list-style:none;
}

.quickLink ul li a img{
  display:block;
  margin:0 auto;
  width:100px;
  height:100px;
  border-radius:100%;
  border:1px solid #ddd;
  line-height:150px;
}

.quickLink ul li a p {
  margin-top:15px;
  font-weight:600;
  color:#666;
  /* 밑줄 왜 안 없어지냐 */
  text-decoration: none; 
}

/* [본문] 공지사항 */

.content {
  width: 100%;  /* 부모 요소의 너비에 맞추기 */
  min-width: 1300px;  /* 최소 너비 지정 */
  height: 300px;
  margin: 20px auto;  /* 중앙 정렬 */
  display: flex;
  align-items: center;
  justify-content: center;
  
}

.tabMenu{  
  float:left; 
  width:40%;
  min-width: 677px;   
  height:100%;   
  margin-top:10px;

}
.tabMenu input[type="radio"] {
  display:none;  
}
.tabMenu label {  
  display:inline-block;  
  margin:0 0;  
  padding:15px 25px;  
  font-weight:600;   
  text-align: center;   
  color:#aaa;  
  border:1px solid transparent;  
}
.tabMenu label:hover {  
  color:#222;  
  cursor:pointer;
}
.tabMenu input:checked + label {  
  color:#b00;
  border:1px solid #ddd;
  background-color: #eee;
}

.tabContent {  
  display:none;
  padding:20px 0 0;
  border-top:1px solid #ddd;
}

#notice ul {
  list-style: disc;
  margin-left:10px;
  
}
#notice ul li a{
  font-size:16px;
  line-height:2;
  text-decoration: none;
}
.tabContent2 {  
  display:none;
  padding:20px 0 0;
  border-top:1px solid #ddd;
}

#notice2 ul {
  list-style: disc;
  margin-left:30px;
}
#notice2 ul li a{
  font-size:16px;
  line-height:2;
  text-decoration: none;
}

#tab1:checked ~ #notice, 
#tab2:checked ~ #notice2 {
  display:block;
}

/* [본문] 실시간 환율 정보 */
.exchange_rate{  
  float:right;
  width:40%;
  min-width: 677px;   
  height:100%;   
  margin-top:10px;

}

.exchange_rate select{
  display:inline-block;  
  margin:0 0;  
  padding:15px 25px;
  font-weight:600;   
  text-align: center;   
  color:#aaa;  
  border:1px solid black;  
}


.exchange_rate img{
  display:block;
  margin:0 auto;
  width:100px;
  height:100px;
  border:1px solid #ddd;
  line-height:150px;
  align-items: center;
  justify-content: center;
}

/* [푸터] */

footer{   
  width:100%;   
  height:100px;  
  display: ruby-text;
  background: #eee;
  border-top:1px solid dimgray;
  align-items: center;
  justify-content: center;
     
  
}
.bottom_bar{
  width:70%;
  height:20px;
  position:relative;

  
  
}
.bottom_bar ul {
  list-style: none;
  text-decoration: none; 

  
  
}
.bottom_bar ul li{
  display: inline-block; 
  padding:5px 20px;
  border-right:2px solid #ddd;   
   
}

.bottom_bar ul li:last-child{
  border:none;   
}
.bottom_bar ul li a, .bottom_bar ul li a:visited {  
  font-size:15px;   
  color:#666;
  text-decoration: none; 
}

.address{
  font-weight: bold;
  padding-left: 60px;

}

.sns {
  float: right; 
  width: 250px;
  height: 100px;
  padding-right: 50px;
}
.sns a{
  display: inline-block;
  margin-top: 30px;
  padding-right: 20px;
} 
