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
							<button type="button" class="btn btn-default" data-dismiss="modal">결제 취소</button>
							<button id="payModal_btn_pay" type="button" class="btn btn-primary" data-dismiss="modal">결제 하기</button>
						</div>
					</div>
				</div>
			</div>
		

<script type="text/javascript">
$(document).ready(function() {
	// 결제 버튼을 누르면 결제창 보이기
	
	$(".cartTotalPrice").click(function() {
		/*	// 회원정보에서 point 얻기
			var userId = ${login.id}// 로그인 구현시 적용 
		$.getJSON("/member"+ userId, function(point) {
			$("#point").text(point); */
			$("#point").text(11000000);// 포인트 test용 로그인 적용 후 삭제
			var point = $("#point").text();
			
			// 총 가격 얻기
			var totalPrice=$(".cartTotalPrice").children().children().children().text();
			$("#payPrice").text(totalPrice);
			var payPrice = $("#payPrice").text();
			
			// 잔액계산
			var balance = Number(point) - Number(totalPrice);
			$("#balance").text(balance);
	
			if(balance<0){
				alert("현재 잔액: "+point+", 잔액이 부족합니다. 충전해주세요");
				return;
			}
			$("#payModal").modal("show");
			
	//	});
		
	});
	
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
				if(result==1){
					location.assign("/mypage/list");
				}else{
					alert("비밀번호가 틀렸습니다");
					return;
				}
			}

		});
	});
	
	
});

</script>

</body>
</html>