/**
 * 	장바구니 함수 모음
 */

// 장바구니로 이동
function goCart(){
	$(".goCart").click(function(){
		location.assign("/cart/list");
	});
}

// 장바구니 상품 삭제하기
function cartDelete(cartNo){
	$.ajax({
		type : "delete",
		url : "/cart",
		headers : {
			"Content-Type" : "application/Json",
			"X-HTTP-Method-Override" : "delete"  
		},
		data : JSON.stringify({
			cartNo : cartNo
		}),
		dataType : "text",
		success : function(result){
			if(result<=0){
				alert("삭제실패");
			}else{
				alert("상품이 삭제되었습니다");	
			}
			
		}
	});
}


// 장바구니 상품 추가하기
function cartInsert(sellboardNo, amount){
	$.ajax({
		type : "post",
		url : "/cart",
		headers : {
			"Content-Type" : "application/Json",
			"X-HTTP-Method-Override" : "post"  
		},
		data : JSON.stringify({
			sellboardNo : sellboardNo,
			amount : amount,
			price : price
		}),
		dataType : "text",
		success : function(result){
			alert(result);
			console.log(result)
		}
		
	});
}
// 장바구니 상품 개수 증가
function cartAmountPlusOne(that){
	var cartNo= that.attr("data-cartNo")
	$.ajax({
		type : "put",
		url : "/cart/cartAmountPlus",
		headers : {
			"Content-Type" : "application/Json",
			"X-HTTP-Method-Override" : "put"  
		},
		data : JSON.stringify({
			cartNo : cartNo
		}),
		dataType : "text",
		success : function(amount){
			that.parent().prev().text(`구매 개수: ${amount}개`);
			var price = that.parent().prev().prev().attr("data-price");
			that.parent().next().next().text(`상품 구매 가격: ${price*amount}원`);
			if(amount==2){
				that.parent().next().show();
			}
			//총가격 갱신
			getTotalPrice();
		}
	});
}

// 상품 개수 가져오기
function getAmount(cartNo){
	$.getJSON("/cart/getAmount/"+ cartNo, function(amount){
		if(amount<=1){
			return;
		}
	});
}


// 장바구니 상품 개수 감소
function cartAmountMinusOne(that){
	var cartNo= that.attr("data-cartNo")
	$.getJSON("/cart/getAmount/"+ cartNo, function(amount){
		if(amount<=1){
			alert("1개 이하로 줄일 수 없습니다.");
			that.parent().hide();
			return;
		}
	});
	
	$.ajax({
		type : "put",
		url : "/cart/cartAmountMinus",
		headers : {
			"Content-Type" : "application/Json",
			"X-HTTP-Method-Override" : "put"  
		},
		data : JSON.stringify({
			cartNo : cartNo
		}),
		dataType : "text",
		success : function(amount){
			that.parent().prev().prev().text(`구매 개수: ${amount}개`);
			var price = that.parent().prev().prev().prev().attr("data-price");
			that.parent().next().text(`상품 구매 가격: ${price*amount}원`);
			
			//총가격 갱신
			getTotalPrice();
		}
	});
}


// 장바구니 수량 구하기
function getCartCount(){
	$.getJSON("/cart/getCartCount", function(result){
		if(result <= 0 ){
			$("#cartCount").text("");
			return;
		}
		$("#cartCount").text(result);
	});
}

// 장바구니 목록의 총가격
function getTotalPrice(){
	$.getJSON("/cart/getTotalPrice", function(data){
		str ="";
		str+=`<li>
				<span>총 가격: ${data}원</span>
			  </li>`
		$("#cartTotalPrice").html(str);
	});
}


// 장바구니 목록 가져오기
function getCartList(){
	$.getJSON("/cart/getCartList", function(data){
		// data는 cartList와 sellboardNo(string)를 key로 cartVO와 sellboardVO를 value로 가지고 있다.
		console.log(data);
		var str="";
		for(i in data.cartList){
			var cart = data.cartList[i];
			var sellNo = cart.sellboardNo;
			var sellboard = data[sellNo];
			str += `<li>
						<img alt="상품이미지" src="${sellboard.img}">
						<span>상품 번호: ${cart.sellboardNo}</span>
						<span>상품 이름: ${sellboard.title}</span>
						<span data-price="${sellboard.price}">상품 가격: ${cart.price} 원</span>
						<span>구매 개수: ${cart.amount}개</span>
						<button type="button" class="btn btn-default btn-xs">
						  <span data-cartNo="${cart.cartNo}" class="glyphicon glyphicon-plus plus" aria-hidden="true"></span>
						</button>
						<button type="button" class="btn btn-default btn-xs">
						  <span data-cartNo="${cart.cartNo}" data-amount="${cart.amount}" class="glyphicon glyphicon-minus minus" aria-hidden="true"></span>
						</button>
						<span class="tPrice" date-aPrice="${cart.aPrice}">상품 구매 가격: ${cart.aPrice}원</span>
						
						<button data-cartNo="${cart.cartNo}" class="cartDelete">삭제</button>
					</li>
					`;
		}
		
		$("#cartList").append(str);
	});
}
