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


}
 </style>
</head>
<body>

	<div class="container">
		<div class="row">
			<p id="title"> ${vo.title } </p>
			<p> ${vo.content } </p>
			<p> ${vo.price } </p>
		</div>
			<div class="form-group">
         	   <ul class="clearfix uploadedList"></ul>
            </div>
		
		
			
		
		<hr>
		
			<div class="row">
		         <button id="read_btn_update" class="btn btn-warning">수정</button>
		         <button id="read_btn_delete" class="btn btn-danger">삭제</button>
		         <button id="read_btn_list" class="btn btn-info">목록</button>
			</div><!--class=row  -->
		      
		
		
		
		
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
		
	         
	         $("#read_btn_update").click(function() {
	             location.assign("/sellboard/update/${vo.bnum}");
	          });
	          
	          
	          $("#read_btn_delete").click(function() {
	              $("form").submit();
	           });
	});
	
	

</script>
</body>
</html>