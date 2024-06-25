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
<!-- 아이콘 -->
<script src="https://kit.fontawesome.com/c9b4b00f98.js"
	crossorigin="anonymous"></script>

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
	
	<div class="contentBox">

        <div class="top">
            <div class="topSubjectArea">
                <i class="fa-brands fa-reddit fa-3x chatBotIcon"></i>
                <h1 class="subject">ChatBot</h1>
            </div>
            <div class="logo">
                <img src="${root}img/NCBankIcon_2.png" alt="NCBank" class="iconStyle01">
                <p class="logoName">NC뱅크</p>
            </div>
        </div>

        <div class="content">
            <form action="${root}chat" method="get">
                <div class="questionArea">
                    <i class="fa-solid fa-comment-dots fa-3x questionIcon"></i>
                    <!--
                <label for="prompt" class="questionLab">Enter any prompt&nbsp;:&nbsp;</label>
                -->
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

        </div> <!-- div.content -->

    </div> <!-- div.contentBox -->
	
</body>
</html>
 