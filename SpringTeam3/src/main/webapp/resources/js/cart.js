/**
 * 	장바구니 함수 모음
 */

// 장바구니로 이동
function goCart() {
	$(".goCart").click(function() {
		location.assign("/cart/list");
	});
}

// 장바구니 상품 추가하기
function cartInsert(sellboardNo, amount, price) {
	
	$.ajax({
		type: "post",
		url: "/cart",
		headers: {
			"Content-Type": "application/Json",
			"X-HTTP-Method-Override": "post"
		},
		data: JSON.stringify({
			sellboardNo: sellboardNo,
			amount: amount,
			price: price
		}),
		dataType: "text",
		success: function(result) {
			alert(result);
			console.log(result)
		}

	});
}

// 장바구니 수량 구하기 & 장바구니 삭제버튼
function getCartCount() {
	$.getJSON("/cart/getCartCount", function(result) {
		if (result <= 0) {
			$("#cartCount").text("");
			$("#allDelete").hide();
			return;
		}
		$("#cartCount").text(result);
		$("#allDelete").show();
		
	});
}

// 장바구니 목록의 총가격
function getTotalPrice() {
	$.getJSON("/cart/getTotalPrice", function(data) {
		str = "";
		str +=
			`<button title="결제하기" type="button" class="list-group-item">
			<h1 align='right'>총 결제 가격: <span>${data}</span>원 </h1>
		</button>`;
		$(".cartTotalPrice").html(str);
	});
}


// 장바구니 목록 가져오기
function getCartList() {
	$.getJSON("/cart/getCartList", function(data) {
		// data는 cartList와 sellboardNo(string)를 key로 cartVO와 sellboardVO를 value로 가지고 있다.
		console.log(data);
		var str = "";
		for (i in data.cartList) {
			var cart = data.cartList[i];
			var sellNo = cart.sellboardNo;
			var sellboard = data[sellNo];
			str += `
			<div class="media">
				<div class="media-left media-middle">
					<a href="/sellboard/read/${sellboard.bnum}"> <img class="media-object img-thumbnail" height="164" width="164" src="/resources/upload${sellboard.name}" alt="해당이미지를 불러올수 없습니다.">
					</a>
				</div>
				<div  class="media-body">
					<h4 class="media-heading list-group-item"> ${sellboard.title} </h4>
					<div class="list-group-item">상품 가격: 
						<span>${cart.price}</span> 원
					</div>
				
					<div class="list-group-item">구매 개수: 
						<button type="button" class="btn btn-default btn-xs">
							  <span data-cartNo="${cart.cartNo}" data-price="${cart.price}" class="glyphicon glyphicon-minus minus" aria-hidden="true"></span>
						</button>
						<span>${cart.amount}</span>개
						<button type="button" class="btn btn-default btn-xs">
							  <span data-cartNo="${cart.cartNo}" data-price="${cart.price}" class="glyphicon glyphicon-plus plus" aria-hidden="true"></span>
						</button>
						<span>(최대 ${sellboard.bcount}개)</span> 
						<button data-sellboardNo="${cart.sellboardNo}" data-title="${sellboard.title}" data-amount="${cart.amount}" data-cartNo="${cart.cartNo}" class="btn btn-danger btn-md cartDelete">삭제</button>
					</div>
					<div class="list-group-item">구매 가격: 
						<span>${cart.aPrice}</span>원
					</div>
				</div>
			</div>`;
		}
		$(".cartList").html(str);
	});
}

// 장바구니 상품 개수 증가
function cartAmountPlusOne(that) {
	var cartNo = that.attr("data-cartNo");
	var price = that.attr("data-price");
	$.ajax({
		type: "put",
		url: "/cart/cartAmountPlus",
		headers: {
			"Content-Type": "application/Json",
			"X-HTTP-Method-Override": "put"
		},
		data: JSON.stringify({
			cartNo: cartNo
		}),
		dataType: "text",
		success: function(amount) {
			that.parent().prev().text(`${amount}`);
			that.parent().parent().next().children().text(`${price * amount}`);
			if (amount == 2) {
				that.parent().prev().prev().show();
			}
			//총가격 갱신
			getTotalPrice();
		}
	});
}

// 장바구니 상품 개수 감소
function cartAmountMinusOne(that) {
	var cartNo = that.attr("data-cartNo");
	var price = that.attr("data-price");
	$.ajax({
		type: "put",
		url: "/cart/cartAmountMinus",
		headers: {
			"Content-Type": "application/Json",
			"X-HTTP-Method-Override": "put"
		},
		data: JSON.stringify({
			cartNo: cartNo
		}),
		dataType: "text",
		success: function(amount) {
			that.parent().next().text(`${amount}`);
			that.parent().parent().next().children().text(`${price * amount}`);

			//총가격 갱신
			getTotalPrice();
		}
	});
}

// 장바구니 상품 삭제하기
function cartDelete(that) {
	var cartNo = that.attr("data-cartNo");
	$.ajax({
		type: "delete",
		url: "/cart",
		headers: {
			"Content-Type": "application/Json",
			"X-HTTP-Method-Override": "delete"
		},
		data: JSON.stringify({
			cartNo: cartNo
		}),
		dataType: "text",
		success: function(result) {
			if (result <= 0) {
				alert("삭제실패");
			} else {
				//삭제후 태그 삭제
				that.parent().parent().parent().remove();
				// 삭제후 총가격 갱신
				getTotalPrice();
				alert("상품이 삭제되었습니다");
			}

		}
	});
}

// 장바구니 상품 모두 삭제하기
function cartAllDelete() {
	$.getJSON("/cart/allDelete", function(result) {
		if (result <= 0) {
			alert("삭제실패");
		} else {
			//삭제후 태그 삭제
			$(".media").remove();
			// 삭제후 총가격 갱신
			getTotalPrice();
			alert("상품이 모두 삭제되었습니다");
		}
	});
}

// 현제 포인트 확인
function getPoint(){
	$.getJSON("/member/getPoint", function(map){
		console.log(map.point);
	});
}

// 포인트 충전
function addPoint(){
	var point = prompt("충전 포인트입력");
	
	if(!isNaN(point)){	// 숫자입력값인지 검사
		if(Number(point)<=99 || Number(point)>100000000){
			alert("충전 실패: 충전 가능 포인트는 100 ~ 1억 입니다.");
			return;
		}
		$.getJSON("/member/addPoint/"+ point, function(map){
			if(Number(map.point)<0){
				alert("충전 실패: 보유 가능 포인트는 0 ~ 2억 입니다.");
				return;
			}
			alert("현재 포인트: "+map.point);
		});
	}else{
		alert("공백/음수/문자는 입력불가");
	}
}





