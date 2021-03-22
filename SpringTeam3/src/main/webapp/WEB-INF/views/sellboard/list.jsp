<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매 상품 게시판</title>
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
	.iimmgg{
	  list-style: none;
      margin-top: 10px;
      float: left;
      margin-left: 50px;
      margin-bottom: 50px;

	 }
	#atag1:hover {
	color: black;
}
	#atag1:link {
	color: red;
}
	#atag1:visited {
	color: black;
}
	#atag1{ 
	text-decoration: none;
}
	


 
</style>
</head>
<body>

    <div class="container-fluid">
        <jsp:include page="/WEB-INF/views/header/loginHeader.jsp"></jsp:include>
       	
    </div>
    
<div class="container">

    <hr>

		<div class="row">
			<h1 class="jumbotron">판매상품</h1>                
		</div>
		
		
		
		<div class="row">

		</div>
		<hr>

	
	
		<div class="row">
				
					<c:forEach items="${list}" var="dto">
						<div>
							<ul>
								<li class="iimmgg"><a href="/sellboard/read/${dto.bnum}"><img alt="/resources/esc.png" src="/resources/upload/${dto.content }"></a><br>
								<a id="atag1" href="/sellboard/read/${dto.bnum}">${dto.title}<br>$${dto.price}</a></li>
								
	 						</ul>	
	 					</div>
					</c:forEach>				
			
       </div>

</div>
			

</body>
</html>