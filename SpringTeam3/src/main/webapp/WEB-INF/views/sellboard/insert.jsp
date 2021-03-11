<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 게시글 등록</title>
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
 	   .fileDrop{
      width: 100%;
      height: 200px;
      border: 1px dotted red;
      margin: auto;
   }

   .uploadedList li{
      list-style: none;
      margin-top: 20px;
   }
 
 </style>
</head>
<body>
<div class="container">
	<div class="row">
		<h1 class="jumbotron">상품 게시글 등록</h1>
	</div>
	
	<div class="row">
		<form action="/sellboard/insert" method="post">
		
			<div class="input-group">
			  <span class="input-group-addon" id="title">제목</span>
			  <input  name="title" id="title" type="text" class="form-control" aria-describedby="basic-addon1">
			</div><p>
			
			<div class="input-group">
			  <span class="input-group-addon" id="writer">작성자</span>
			  <input name="writer" id="writer" type="text" class="form-control" aria-describedby="basic-addon1">
			</div><p>
			
			<div class="input-group">
			  <span class="input-group-addon" id="cost">원가</span>
			  <input name="cost" id="cost" type="text" class="form-control"  aria-describedby="basic-addon1">
			</div><p>
			
			<div class="input-group">
			  <span class="input-group-addon" id="price">판매가</span>
			  <input name="price" id="price" type="text" class="form-control"  aria-describedby="basic-addon1">
			</div><p>
			
			<div class="input-group">
			  <span class="input-group-addon" id="bcount">상품수량</span>
			  <input name="bcount" id="bcount" type="text" class="form-control"  aria-describedby="basic-addon1">
			</div><p>
			
			<div class="input-group">
			  <span class="input-group-addon" id="content">상세설명</span>
			  <textarea rows="5"  class="form-control" id="content" name="content"></textarea>
			</div><p>
				
		</form>
		
		
				<div class="form-group">
		            <label>썸네일파일을 올려주세요</label>
		            <div class="fileDrop"></div>  
		            <ul class="clearfix uploadedList"></ul>
				</div>
     		    
     		    
     		
				
				<div class="form-group">
		            <button id="insert_btn_insert" class="btn btn-primary" type="submit">등록</button>
		            <button id="insert_btn_list" class="btn btn-info">목록으로 되돌아가기</button>
				</div>
	</div>
	
</div>


<script type="text/javascript">
	$(document).ready(function(){
		
        $(".fileDrop").on("dragenter dragover", function(event) {
            event.preventDefault();
         });
         
         $(".fileDrop").on("drop", function(event) {
            event.preventDefault();
            
            var files = event.originalEvent.dataTransfer.files;
            var file = files[0];
            
            var formData = new FormData();
            formData.append("file", file);
            
            $.ajax({
               type : 'post',
               url : '/sellboard/upload',
               processData : false,
               contentType : false,
               data : formData,
               dataType : 'text',
               success : function(data) {
                  var str = makeHtmlCode(data);
                  
                  $(".uploadedList").append(str);
               }
            });
            
         });
		
		 $("#insert_btn_insert").click(function() {
		             
		             var str = "";
		             $(".delbtn").each(function(index) {
		                var data = $(this).attr("data-src");
		                str += "<input name= 'files["+index+"]' type = 'hidden' value='"+data+"'>";
		             });
		             
		             $("form").append(str);
		             
		             $("form").submit();
		          });
		
		
	});
	
	
	

</script>
</body>
</html>