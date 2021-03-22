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
	<div align="right">
	<button class="btn btn-danger btn-lg" id="btn_allDelete">모두삭제</button>
	</div>
	<br>
	<div class="row">
		
			<div id="list" >
		
		
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
	                		  backEle.parent().parent().parent().remove();
	                		 
						}
	                	  
	                  }
	          
						});
	            }
		});

	$("body").on("click", "#btn_allDelete", function() {

		var yesOrNo = confirm("정말로 삭제하겠습니까?");
		if (yesOrNo) {
			$.getJSON("/mypage/allDelete", function(data) {
				
				$(".media").remove();
				
			});
		}
		});
	list();
	function list() {
		$.getJSON("/mypage",function(data) {
			var str = "";
			var arr = data;
			for (var i = 0; i < arr.length; i++) {
			var list = arr[i];
			var sellnum = list.sellNum;
			var sellboardno = list.sellboardNo;
			var buynum = list.buyNum;
			var amount = list.amount;
			var aprice = list.aPrice;
			var groupnum = list.groupNum;
			var img = list.img;
			if (0 < groupnum) {

		str += '<div class="media">'
				+ '<div class="media-left media-middle">'
				+ '<a href="/sellboard/read/'+sellboardno+'"><img class="media-object img-thumbnail" height="164" width="164" src="/resources/upload'+img+'" alt="해당 이미지를 불러올수없습니다.">'
				+ '</a>'
				+ '</div>'
				+ '<div class="media-body">'
				+ '<h4 class="media-heading list-group-item">판매 번호:'
				+ sellnum
				+ '</h4>'
				+ '<div class="list-group-item">구매 개수:'
				+ '<span>'
				+ amount
				+ '개</span>'
				+ '</div>'
				+ '<div class="list-group-item">구매 가격:'
				+ '<span>'
				+ aprice
				+ '원</span>'
				+ '</div>'
				+ '<div class="list-group-item">'
				+ '<button data-listNo="'+sellnum+'" class="btn btn-danger btn-xs btn-delete">삭제</button>'
				+ '</div>'
				+ '</div>'
				+ '</div>';
			}
			;
		}
		;

		$("#list").html(str);

	});

	}

});
</script>
</body>
</html>