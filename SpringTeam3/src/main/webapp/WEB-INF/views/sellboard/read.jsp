<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${vo.title}</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
 src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
 src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
 src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script
 src="/resources/js/upload.js" type="text/javascript"></script>
 
 <style type="text/css">
 .uploadedList li{
      list-style: none;
      margin-top: 100px;   
   }
#title{
	margin-left: 70px;
	font-size: 35px;
	font-family: sans-serif;
}
#content{
	margin-left: 70px;
	font-size: 20px;
	font-family: sans-serif;
}
#price{
	margin-left: 70px;
	font-family: sans-serif;
}
 </style>
</head>
<body>
    <div class="container-fluid">
        <jsp:include page="/WEB-INF/views/header/loginHeader.jsp"></jsp:include>
       	
    </div>
	<div class="container">
		
			<div class="form-group">
         	   <ul class="clearfix uploadedList"></ul>
            </div>
			
			<div class="row">
				<p id="title"> ${vo.title } </p>
				<p id="content"> ${vo.content } </p>
				<p id="price">$${vo.price } </p>
			</div>
			
			<!-- 구매개수 입력 -->
		<div align="right" class="row">
			<input class="input-group" maxlength=1 type="number" id="amount" min="1" max="99" value="1">
		</div>
		<!-- 장바구니 담기 버튼 -->
		<div align="right" class="row">
			<button id="read_btn_cartInsert" class="btn btn-info">장바구니 담기</button>
		</div>
		
		
		<hr>
		
			
	<div class="row"> <!-- 관리자만 보이는 버튼 -->
		<a class="${login.mType == 1004 ? '' :'hidden' }" target="_blank" href="/sellboard/insert">
			<button id="read_btn_update" class="btn btn-warning">수정</button>
		</a>
		<a class="${login.mType == 1004 ? '' :'hidden' }" target="_blank" href="/sellboard/update">
			<button id="read_btn_delete" class="btn btn-danger">삭제</button>
		</a>
	</div><!--class=row  --><p>
	
	
		
			<div class="row">
				<button id="read_btn_list" class="btn btn-info">목록</button>
			</div>

			
		      
		
		
		
		
		   <form action="/sellboard/delete" method="post">
		      <input type="hidden" name="bnum" value="${vo.bnum}">      
		   </form>
	</div>		   
   
<script type="text/javascript">	
		$(document).ready(function() {
	         
			var bnum = ${vo.bnum};
	         
	         $.getJSON("/sellboard/getAttaches/"+bnum, function(result) {
	        	 
	        	 str = '';
	        	 
	            for (var i = 0; i < result.length; i++) {
					data = result[i];
					str += makeHtmlCode_read(data)
					
				}
	            
	            $(".uploadedList").html(str);
	         });
		
		$.getJSON("/sellboard/getAttaches/" + bnum, function(result) {
			str = '';
			for (var i = 0; i < result.length; i++) {
				data = result[i];
				str += makeHtmlCode_read(data)
			}
			$(".uploadedList").html(str);
		});

		$("#read_btn_update").click(function() {
			location.assign("/sellboard/update/${vo.bnum}");
		});

		$("#read_btn_delete").click(function() {
			$("form").submit();
		});
		
		// 장바구니 상품 입력
		$("#read_btn_cartInsert").click(function() {
			var name ='${login.name}';
			console.log(name);
 			if(name == ""){
				var va = confirm("로그인 하시겠습니까?");
				if(va){
					location.assign("/member/login");
				}
				return;
			} 
			var amount = $("#amount").val();
			var bcount = ${vo.bcount};
			if(amount > bcount){
				alert("최대 구매개수는 판매 개수를 넘을수 없습니다.");
				$("#amount").val(1);
				return;
			}
			var price = ${vo.price};
			cartInsert(bnum, amount, price);
			setTimeout(function() {
					getCartCount();
				}, 1000);
		});
				
	         
	         $("#read_btn_update").click(function() {
	             location.assign("/sellboard/update/${vo.bnum}");
	          });
	          
	          
	          $("#read_btn_delete").click(function() {
	              $("form").submit();
	           });
	          
	          $("#read_btn_list").click(function() {
	        	  location.href='/sellboard/list';
	           });
	          
	});
	
	

</script>
</body>
</html>