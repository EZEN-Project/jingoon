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
	hr{
		color: black;
		background-color: black;
		height: 1px;
		width: 1150px;
	}
	#btn,h1 {
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
		<h1 class="jumbotron">마이페이지</h1>
	</div>
	<hr>
	<div id="btn" class="row">
		<button id="update" type="button" class="btn btn-primary btn-lg">회원정보 수정</button>
		<button id="list" type="button" class="btn btn-info btn-lg">결제내역</button>	
	</div>
		
	
	
	<hr>
	
</div>
<script type="text/javascript">
$(document).ready(function() {
	
	
	$("#update").click(function(event) {
		
		window.open("/member/read");
	});
	$("#list").click(function(event) {
		
		window.open("/mypage/list");
	});

});

</script>



</body>
</html>