<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>
<!-- 결제확인 창 Modal -->
		
			<div class="modal fade" id="payModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">결제 확인</h4>
						</div>
						<div align="right" class="modal-body">
							내 포인트 : <span id="point"></span> 원<br>
							결제 비용	: <span id="payPrice"></span> 원<br>
							    잔액 : <span id="balance"></span> 원<br>
							<h1>결제하시겠습니까?</h1>
							비밀번호 확인<form action="/cart/pay" method="post"><input class="form-control" id="pw" type="password"></form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="payModal_btn_payCS" data-dismiss="modal">결제 취소</button>
							<button id="payModal_btn_pay" type="button" class="btn btn-primary" data-dismiss="modal">결제 하기</button>
						</div>
					</div>
				</div>
			</div>
		

<script type="text/javascript">
$(document).ready(function() {
	
	// 결제 버튼을 누르면 결제창 보이기
	$(".cartTotalPrice").click(function() {
		
		// 포인트 가져오기
		$.getJSON("/member/getPoint", function(map){
			
			$("#point").text(map.point);
		
			// 총 가격 얻기
			var totalPrice=$(".cartTotalPrice").children().children().children().text();
			$("#payPrice").text(totalPrice);
			var payPrice = $("#payPrice").text();
			
			// 잔액계산
			var point = map.point;
			console.log(point);
			var balance = Number(point) - Number(totalPrice);
			$("#balance").text(balance);
	
			if(balance<0){
				alert("현재 잔액: "+point+", 잔액이 부족합니다. 충전해주세요");
				return;
			}if(Number(totalPrice)<=0){
				// 장바구니 결제 가격이 0이하일 때
				alert("장바구니에 구매상품을 추가해주세요");
				return;
			}
			
			$.getJSON("/cart/getCartList", function(data){
				var ok=1;// 수량 부족 체크
				var totalTitle ="";
				for (i in data.cartList) {
					var cart = data.cartList[i];
					var sellNo = cart.sellboardNo;
					var sellboard = data[sellNo];
					var amount = cart.amount;
					var title = sellboard.title;
					var bcount = sellboard.bcount;
				
			 		if(bcount<amount){
						totalTitle += title;
						ok=0;
						break;
					}
				};
			
				if(ok==1){
					$("#payModal").modal("show");	
				}else{
					alert("구매 상품 중 "+totalTitle+" 의 제품 수량이 부족합니다.");
				}
			});	// 장바구니목록의 수량체크
		});	//	포인트 가져오기
	});	// 결제버튼
	
	// 모달창 결제버튼 누르면
	$("#payModal_btn_pay").click(function() {
		// 비밀번호 입력 확인
		var pw = $("#pw").val();
		if(pw == ""){
			alert("비밀번호를 입력해주세요.");
			return;
		}
		// 결제 진행. 비밀번호가 틀리면 실패!
		$.ajax({
			type: "post",
			url: "/cart/pay",
			headers: {
				"Content-Type": "application/Json",
				"X-HTTP-Method-Override": "post"
			},
			data: JSON.stringify({
				pw : pw
			}),
			dataType: "text",
			success: function(result) {
				$("#pw").val("");
				if(result>=0){
					alert("결제 완료!")
					location.assign("/mypage/list");
				}else if(result==-3){
					alert("결제취소: 비밀번호가 틀렸습니다");
					return;
				}else if(result==-1){
					alert("결제취소: 포인트가 부족합니다");
					return;
				}else if(result==-2){
					alert("결제취소: 판매수량이 부족합니다");
					return;
				}

			}

		});
	});
	
	// 결제취소 비밀번호 지우기
	$("#payModal_btn_payCS").click(function() {
		$("#pw").val("");	
	});
	
	
	
});

</script>

</body>
</html>