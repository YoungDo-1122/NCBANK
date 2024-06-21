<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NC BANK</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/readingboard.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

	<c:import url="/WEB-INF/views/include/top_menu.jsp" />

	<!-- read에 보여줄 것  : 제목,등록일 , 내용,파일 -->
	<div class="container mt-5">
		<div class="top">
			<div class="title-group">
				<label for="content_subject">제목</label> <input type="text"
					id="content_subject" name="content_subject" class="form-control"
					value="${readContentBean.content_subject}" readonly="readonly" />
			</div>
			<div class="date-group">
				<label for="content_date">작성날짜</label> <input type="text"
					id="content_date" name="content_date" class="form-control"
					value="${readContentBean.content_date}" disabled="disabled" />
			</div>
		</div>
		<div class="content-group">
			<label for="content_text">내용</label>
			<textarea id="board_content" name="board_content" class="form-control" disabled="disabled">${readContentBean.content_text}</textarea> 
		<%-- 	<input type="text" id="board_content" name="board_content" class="form-control"
				value="${readContentBean.content_text}" disabled="disabled" /> --%>
		</div>

		<div class="form-group">
			<div class="text-right">
				<a
					href="${root}board/main?board_info_idx=${board_info_idx}&page=${page}"
					class="btn btn-primary">목록보기</a>
				<%-- 	<c:if test="${loginUserBean.user_idx == readContentBean.content_writer_idx}"/> --%>
			</div>
		</div>
	</div>

	<c:import url="/WEB-INF/views/include/bottom_info.jsp" />
	
</body>
 <script>
        function autoResizeTextarea(textarea) {
            textarea.style.height = 'auto';
            textarea.style.height = textarea.scrollHeight + 'px';
        }

        document.addEventListener('DOMContentLoaded', function() {
            var textarea = document.getElementById('board_content');
            autoResizeTextarea(textarea);

            textarea.addEventListener('input', function() {
                autoResizeTextarea(textarea);
            });
        });
    </script>
</html>