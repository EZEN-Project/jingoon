<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<!-- 로그인&로그아웃 -->
	<div class="container-fluid">
		<jsp:include page="/WEB-INF/views/header/loginHeader.jsp"></jsp:include>
	</div>
	<hr>

	<div class="container">
		<div class="row">	
			<div class="col-md-12">
	        <div class="py-7">
	            <div class="text-center">
	       	   		<h2>회원정보</h2>
	        	    <p class="lead">회원정보 보기</p>
	            </div>
	        </div>
	        <div align="right" class="form-group">
	        	<button onclick="addPoint()" class="btn btn-info">포인트충전</button>
	        </div>
	        	 <input id="pw" name="pw" type="password" class="hidden" value="${memberVO.pw}" >
	        	 <div class="form-group">
		        	  <div class=".col-xs-12">
					    <div class="input-group">
					      <span id="span1" class="input-group-addon">ID</span>
					     	 <input name="id" id="id" class="form-control" readonly="readonly" value="${memberVO.id }">
					    </div><!-- /input-group -->
					  </div><!-- /.col-lg-6 -->
				  </div><!-- /.form-group -->
				  
				  <div class="form-group">
					<div class=".col-xs-12 .col-md-8">
						<div class="input-group">
						  <span class="input-group-addon" id="email">email</span>
						  <input name="email" type="email" class="form-control" readonly required value="${memberVO.email }" aria-describedby="email">
						</div>
					</div>
				</div>
							
				<div class="form-group">
					<div class=".col-xs-12 .col-md-8">
						<div class="input-group">
						  <span class="input-group-addon" id="name">이름</span>
						  <input name="name" type="text" class="form-control" readonly="readonly" value="${memberVO.name }" aria-describedby="name">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class=".col-xs-12 .col-md-8">
						<div class="input-group">
						  <span class="input-group-addon" id="phone">전화번호</span>
						  <input name="phone" type="tel" class="form-control" readonly required placeholder="전화번호를 -를 제외하고 입력하세요" value="${memberVO.phone }" aria-describedby="phone">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class=".col-xs-12 .col-md-8">
						<div class="input-group">
							<span class="input-group-addon" id="birth">생일</span>
							<input name="birth" type="date" class="form-control" readonly="readonly" value="${memberVO.birth }" aria-describedby="birth">
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class=".col-xs-12 .col-md-8">
						<div class="input-group">
							<span class="input-group-addon" id="address">주소</span>
							<input type="text" class="form-control" name="address" readonly id="sample4_roadAddress" value="${memberVO.address}" readonly placeholder="도로명주소"> 
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class=".col-xs-12 .col-md-8">
						<div class="input-group">	
							<input id="member_read_btn_update" class="btn btn-primary" type="button" value="수정하기">
						</div>
					</div>
				</div>
						
				</div>
	        </div><!-- /.row -->
		</div><!--/.container  -->
<script type="text/javascript">
	$(document).ready(function() {
			
		$("#member_read_btn_update").click(function(){
			var deleteConfig = $("#pw").val();
			var inputConfig = prompt("비밀번호를 입력하세요");
			if(inputConfig == deleteConfig){
				console.log(inputConfig);
				console.log(deleteConfig);
				location.assign("/member/update");
			}
		});
		
	});
</script>
</body>
</html>