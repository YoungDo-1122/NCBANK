<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="root" value="${pageContext.request.contextPath}/"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/teststyle_top.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>환전 신청</h1>
		<tr>
	       <th>환전신청금액</th>
	       <td>
				<div>
					<select name="통화코드">
						<option>JPY , USD 등등 나오게</option>
					</select>
					<input type="text" />
					<button></button>
				</div>
	       
	       </td>
	   </tr>
	   
	   <tr>
	       <th>환전예상금액</th>
	       <td>${trade.trade_money}</td>
	   </tr>
	   <tr>
	       <th>Trade Rate</th>
	       <td>${trade.trade_rate}</td>
	   </tr>
	   <tr>
	       <th>Trade Type</th>
	       <td>${trade.trade_type}</td>
	   </tr>
	   <tr>
	       <th>Bank Code</th>
	       <td>${trade.code_bank}</td>
	   </tr>
	   <tr>
	       <th>User Number</th>
	       <td>${trade.user_num}</td>
	   </tr>
	   <tr>
	       <th>Currency Code</th>
	       <td>${trade.code_money}</td>
	   </tr>
	
</body>
</html>