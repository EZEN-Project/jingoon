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
</head>
<body>
<div class="container-fluid">
        <jsp:include page="/WEB-INF/views/header/loginHeader.jsp"></jsp:include>
       	
    </div>
    <hr>
  <div class="container">
      <div class="row">
         <h1 class="jumbotron" style="text-align: center;">게시글 수정</h1>
      </div><!--class="row"  -->
      
      <div class="row">
         <form action="/qaboard/update" method="post">
            <div class="form-group">
               <label for="bnum">글번호</label>
               <input value="${vo.bnum}" id="bnum" name="bnum" class="form-control" readonly>
            </div>
            
            <div class="form-group">
               <label for="writer">작성자</label>
               <input value="${vo.writer}" id="writer" name="writer" class="form-control" readonly="readonly">
            </div>
            
            <div class="form-group">
               <label for="title">제목</label>
               <input value="${vo.title}" id="title" name="title" class="form-control">
            </div>
            
            <div class="form-group">
               <label for="content">내용</label>
               <textarea id="content" name="content" class="form-control">${vo.content }</textarea>
            </div>
         
         </form>
         
              
         <div class="form-group">
            <button class="btn btn-warning" id="update_btn_update">수정</button>
            <button id="update_btn_back" class="btn btn-default">되돌아가기</button>
         </div>
         
         
      </div><!--class="row"  -->
      
      
   </div><!--class="container"  -->
   <script type="text/javascript">
      $(document).ready(function() {
         var bnum = ${vo.bnum};
         
         
        $("#update_btn_update").click(function() {
			$("form").submit();
		});
         
        
         
         $("#update_btn_back").click(function() {
            history.back();
         });
      });
   
   
   </script>
</body>
</html>