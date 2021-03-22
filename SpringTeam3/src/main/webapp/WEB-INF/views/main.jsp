<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME</title>
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
		<h1 class="jumbotron">메인 페이지</h1>
	</div>
 	<div class="row">
 		<div class="content-center">
 		<div style="width: 700px; height: 500px; margin: auto;">
 		
			<c:if test="${vo.size()>0 }">
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
				  <!-- Indicators -->
				  <ol class="carousel-indicators">
				    <c:forEach begin="0" end="${vo.size()-1}" var="index">
				    	 <li data-target="#carousel-example-generic" data-slide-to="${index }" class="${index==0? 'active':'' }"></li>
				    </c:forEach>
				    <!-- <li data-target="#carousel-example-generic" data-slide-to="2"></li> -->
				  </ol>
				  
				  <!-- Wrapper for slides -->
				  <div class="carousel-inner" role="listbox">
				    <c:forEach items="${vo}" var="vo" varStatus="status">
					    <div class="item ${status.index ==0? 'active':'' }" data-spy="affix" data-offset-top="60" data-offset-bottom="200">
					      <a href="/sellboard/read/${vo.bnum}"><img style="width:700px; height: 500px;" src="/resources/upload${vo.content}" alt="판매상품 사진"></a>
					      <div class="carousel-caption">
					      	<h3>${vo.title}</h3>
						   	<p>${vo.price}원</p>
					      </div>
					    </div>
					    
				    </c:forEach>
				  </div>
				
				  <!-- Controls -->
				  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
				    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				    <span class="sr-only">Previous</span>
				  </a>
				  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
				    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				    <span class="sr-only">Next</span>
				  </a>
				</div>
			</c:if>
		
		</div>
		</div><!-- center -->
 	</div><!--row  -->
 </div><!-- container -->

<script type="text/javascript">
	$(document).ready(function() {
		
	});
</script>
</body>
</html>