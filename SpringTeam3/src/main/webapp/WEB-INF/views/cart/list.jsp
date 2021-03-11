<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>

<!-- 로그인&로그아웃 -->
	<div class="container-fluid">
		<jsp:include page="/WEB-INF/views/header/loginHeader.jsp"></jsp:include>
	</div>
	<hr>

	<div class="container">
	
		<div class="row">
			<a class="" href="">쇼핑</a>
		</div><!--row  -->
		
		<!-- 장바구니 리스트 -->
		<div class="row list-group cartList">
			<h1 class="jumbotron" align='center'>장바구니</h1>
		</div><!--row  -->

		<!-- 총 가격  -->
		<div class="row list-group cartTotalPrice">
		</div>
		
		<div class="row">
			<jsp:include page="pay.jsp"></jsp:include>
		</div>

	</div><!-- container -->
	
	<!-- 테스트용 상품입력. 추후 삭제 -->
	<button id="test">test장바구니추가(상품1,2,3)</button>

	<script type="text/javascript">
	$(document).ready(function() {
		// 테스트용 상품입력. 추후 삭제
		$("#test").click(function() {
			cartInsert(1234,1,5000);
			cartInsert(1235,1,6000);
		});
		
		getCartList();	// 장바구니 목록
		getTotalPrice();	// 장바구니 총 가격
				
		// 장바구니 상품 삭제
		$(".cartList").on("click",".cartDelete", function() {
			var that= $(this);
			cartDelete(that);
		});
		
		// 장바구니 상품 개수 증가(+버튼)
		$(".cartList").on("click",".plus", function() {
			var that= $(this);
			cartAmountPlusOne(that);
		
		});
		// 장바구니 상품 개수 감소(-버튼)
		$(".cartList").on("click",".minus", function() {
			var that= $(this);
			cartAmountMinusOne(that);
		});
		
	});

</script>
</body>
</html>