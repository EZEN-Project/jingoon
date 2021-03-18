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
<script src="/resources/js/cart.js?vs=0.37" type="text/javascript"></script>

</head>
<body>
	<div style="margin: 15px 15px 15px;" class="row text-right">
<!-- 로그인버튼	 -->

		<c:choose>
			<c:when test="${empty login}">
				<a href="/member/login">로그인</a>
			</c:when>
			<c:otherwise>
				<span><a href="/member/read">${login.name}</a> 님, 환영합니다.</span>
				<a href="/member/logout">로그아웃</a>
			</c:otherwise>
		</c:choose>
<!-- 장바구니 버튼 -->
		<a class="${login.mType == 1 ? '' :'hidden' }" href="/cart/list">
		<button class="btn btn-primary btn-md goCart" type="button">
			<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
		 	 장바구니 <span id="cartCount" class="badge"></span>
		</button></a>
<!-- 매출차트 버튼-->
		<a class="${login.mType == 1004 ? '' :'hidden' }" target="_blank" href="/mypage/sales">
			<button class="btn btn-danger btn-md goSales" type="button">매출</button>
		</a>
<!-- 마이페이지 버튼 -->		
		<a href="/mypage/mypage"><button class="btn btn-primary btn-md" type="button">
			<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
		 	 마이페이지
		</button></a>	
	
	</div><!-- row -->
	<div class="row">
		<ol class="breadcrumb">
			<li><a href="/">HOME</a></li>
		  	<li><a href="/sellboard/list">쇼핑</a></li>
			<li class="${empty login ? 'hidden' : ''}"><a href="/mypage/list">결제내역</a></li>
		</ol>

	</div>

<script type="text/javascript">
	$(document).ready(function() {
		var name ='${login.name}';
		console.log(name);
		if(name != ""){
			getCartCount();
		}
	});

</script>

</body>
</html>