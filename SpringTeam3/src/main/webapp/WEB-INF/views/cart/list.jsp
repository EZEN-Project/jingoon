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
			<h1 class="jumbotron" align='center'>장바구니</h1>
		</div><!--row  -->
		<div class="row list-group" align="right">
			<button class="btn btn-danger" id="allDelete" title="장바구니 비우기">모두 삭제</button>
		</div>
		
		<!-- 장바구니 리스트 -->
		<div class="row list-group cartList">
			
		</div><!--row  -->

		<!-- 총 가격  -->
		<div class="row list-group cartTotalPrice">
		</div>
		
		<div class="row">
			<jsp:include page="pay.jsp"></jsp:include>
		</div>
		
	</div><!-- container -->
	

	<script type="text/javascript">
	$(document).ready(function() {
		getCartList();	// 장바구니 목록
		getTotalPrice();	// 장바구니 총 가격
		
		// 장바구니 모두 삭제
		$("#allDelete").click(function() {
			cartAllDelete();
			
		});
		
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
			// 장바구니 상품 개수 줄일 때 
			var cartNo = that.attr("data-cartNo");
			$.getJSON("/cart/getAmount/" + cartNo, function(amount) {
				if (amount <= 1) {
					alert("1개 이하로 줄일 수 없습니다.");
					that.parent().hide();
					return;
				}else{
					
				}
			});
		});
		
		
	});

</script>
</body>
</html>