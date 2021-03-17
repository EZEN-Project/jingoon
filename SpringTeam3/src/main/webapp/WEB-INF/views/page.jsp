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
<div class="row">
			<nav aria-label="Page navigation">
  				<ul class="pagination">
    				<li>
      					<a href="/mypage/adminlist/${to.curPage <= 1? 1 : to.curPage-1}" aria-label="Previous">
        					<span aria-hidden="true">&laquo;</span>
      					</a>
    				</li><!-- "<<" -->
    				
    				<c:forEach var="idx" end="${to.stopPageNum}" begin="${to.beginPageNum}">
    				
    					<li class="${to.curPage == idx ? 'active':'' }"><a href="/mypage/adminlist/${idx}">${idx}</a></li>
    					
    				</c:forEach>
    				<li>
				      <a href="/mypage/adminlist/${to.curPage < to.totalPage ? to.curPage+1 : to.totalPage }" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li><!-- ">>" -->
    				
  				</ul>
			</nav>
		</div><!--class = row  -->
</body>
</html>