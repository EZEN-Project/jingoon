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
	h1{
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
		<h1 class="jumbotron">결제 내역</h1>
	</div>	
	<hr>
	<div id="row">
		<div id="form-group">
			<div id="list" >
		
		
			</div>
		</div>
	</div>	
</div><!-- container -->

<script type="text/javascript">
	$(document).ready(function() {
		$("#list").on("click",".btn-delete", function() {
				var backEle = $(this);
	            var sellnum = $(this).attr("data-listNo");
	            var yesOrNo = confirm("정말로 삭제하겠습니까?");
	            if(yesOrNo){
	               $.ajax({
	                  type : 'put',
	                  url : "/mypage",
	                  headers : {
	                     "Content-Type" : "application/json",
	                     "X-HTTP-Method-Override" : "put"
	                  },
	                  data : JSON.stringify({
	                     sellnum : sellnum
	                     
	                  }),
	                  dataType : "text",
	                  success : function(result) {
	                	  if (result =="o") {
	                		  backEle.parent().remove();
	                		 
						}
	                	  
	                  }
	          
						});
	            }
		});
		list();
		function list() {
			$.getJSON("/mypage", function(data){
				
				console.log(data);
				var str="";	
				var arr = data;
				
				for(var i=0; i<arr.length; i++){
					var list = arr[i];
					var sellnum = list.sellNum;
					var sellboardno = list.sellboardNo;
					var buynum = list.buyNum;
					var amount = list.amount;
					var aprice = list.aPrice;
					var groupnum = list.groupNum;
					var img = list.img;
					if (0 < groupnum) {
					
						str +='<div>'+
							  /* '<a href=""><img src="'+img+'" alt=""></a>'+ */
							  '<span>상품 번호:'+sellnum+'</span>'+							
							  '<span>상품 게시판 번호:'+sellboardno+'</span>'+							
							  '<span>구매자 번호:'+buynum+'</span>'+
							  '<span>구매 개수:'+amount+'개</span>'+
							  '<span>구매 총가격:'+aprice+'원</span>'+													
						      '<button data-listNo="'+sellnum+'" class="btn btn-danger btn-xs btn-delete">삭제</button>'+
						      '</div>'
						      '<br>';
					};
				};
				
				$("#list").html(str);
				
			});
			
		}
		
	});
</script>
</body>
</html>