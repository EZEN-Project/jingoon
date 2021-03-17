<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
	h1{
		text-align: center;
	}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<h1 class="jumbotron">회원정보 수정</h1>
	</div>
	<hr>
	<div class="row">
		<form action="/mypage/update" method="post">
				<div class="form-group">
					<label for="id">아이디</label>
					<input class="form-control" id="id" name="id" value="${vo.id}" readonly="readonly">
				</div>
				<div class="form-group">
					<label for="pw">비밀번호</label>
					<input class="form-control" id="pw" name="pw">
				</div>
				<div class="form-group">
					<label for="name">이름</label>
					<input class="form-control" id="name" name="name" value="${vo.name}">
				</div>
				<div class="form-group">
					<label for="email">Email</label>
					<input class="form-control" id="email" name="email" value="${vo.email}" >
				</div>
				<div class="form-group">
					<label for="address">주소</label>
					<input class="form-control" id="address" name="address" value="${vo.address}">
				</div>
				<button type="submit" class="btn btn-primary">등록</button>					
				
			</form>
	</div>


</div>
</body>
</html>