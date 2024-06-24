<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='root' value="${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ChatBot</title>
<!-- Bootstrap CDN -->

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>

<link rel="stylesheet" href="${root}css/chat.css">

<body>
	<c:import url="/WEB-INF/views/include/top_menu.jsp" />
	
	<div class="contentBox">
        <h1 class="subject">[Beta]&nbsp;AI ChatBot</h1>

        <form action="${root}chat" method="get">
            <div class="questionArea">
                <label for="prompt" class="questionLab">Enter any prompt&nbsp;:&nbsp;</label>

                <div class="questionInputArea">
                    <input type="text" class="questionInput" id="prompt" name="prompt" required>

                    <button type="submit" class="btnStyle01">질문</button>
                </div>
            </div>

        </form>
        <div class="answerArea">
            <h3 class="answerSubject">AI Response&nbsp;:&nbsp;</h3>
            <p class="answerResult">${response}</p>
        </div>
    </div>
	
	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
	
</body>
</html>
 