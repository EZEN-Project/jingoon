<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
	h1{
		text-align: center;
	}


</style>
</head>
<body>
<!-- 로그인&로그아웃 -->
    <div class="container-fluid">
        <jsp:include page="/WEB-INF/views/header/loginHeader.jsp"></jsp:include>
       	
    </div>
    <hr>
	<div class="container">
		<div class="row">
			<h1 class="jumbotron">글쓰기 화면</h1>
		</div>
		<hr>
		<!--class="row"  -->

		<div class="row">
			<form action="/qaboard/insert" method="post">
				<div class="form-group">
					<label for="title">제목</label> <input class="form-control"
						id="title" name="title">
				</div>

				<div class="form-group">
					<label for="writer">작성자</label> <input class="form-control"
						id="writer" name="writer" value="${login.id}" readonly="readonly"
						>
				</div>

				<div class="form-group">
					<label for="content">내용</label>
					<textarea rows="5" class="form-control" id="content" name="content"></textarea>
				</div>
			</form>
			<button type="submit" class="btn btn-primary" id="btn-insert">등록</button>
			<button class="btn btn-primary" id="cancel">취소</button>
		</div>


	</div>
	<!--class = container  -->
<script type="text/javascript">
$(document).ready(function() {
	$("#btn-insert").click(function() {
		$("form").submit();
	});
	$("#cancel").click(function() {
		history.back();
	});
});		
</script>

</body>
</html>