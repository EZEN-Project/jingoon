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
<script src="/resources/js/cart.js?vs=0.14" type="text/javascript"></script>
</head>
<body>
	<div class="row text-right">
		<c:choose>
			<c:when test="${empty login}">
				<a href="/member/login">로그인</a>
			</c:when>
			<c:otherwise>
				<span>${login.userId} 님, 환영합니다.</span>
				<a href="/member/logout">로그아웃</a>
			</c:otherwise>
		</c:choose>
<!-- 장바구니 버튼 -->
		<button class="btn btn-primary btn-md goCart" type="button">
			<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
		 	 장바구니 <span id="cartCount" class="badge"></span>
		</button>	
	</div>

<script type="text/javascript">
	$(document).ready(function() {
		getCartCount();
		goCart();
		
	});

</script>

</body>
</html>