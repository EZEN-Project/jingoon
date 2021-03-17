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
        <jsp:include page="/WEB-INF/views/header/adminlistLoginHeader.jsp"></jsp:include>
       	
    </div>
    <hr>
<div class="container">
		<div class="row">
			<h1 class="jumbotron">관리자 페이지</h1>                
		</div>
		<hr>
		
		
		<div class="row">
		<table class="table table-hover">
		<thead>
			<tr>
				<!-- <th>상품</th> -->
				<th>날짜</th>
				<th>판매번호</th>
				<th>구매자번호</th>
				<th>수량</th>
				<th>총가격</th>
				
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${list}" var="dto">
				<tr>
					<%-- <td><a href=""><img src="${dto.img}" alt""></a></td> --%>					
					<td><fmt:formatDate pattern="yyyy-MM-dd" type="date" value="${dto.sellDate}"/> </td>
					<td>${dto.sellNum}</td>
					<td>${dto.buyNum}</td>
					<td>${dto.amount}개</td>
					<td>${dto.aPrice}원</td>
				</tr>
			</c:forEach>
		
		</tbody>
		
		
	</table>
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
	<div class="row">
			<div class="input-group">
				<span class="input-group-addon">
					<select id="searchSel">
						<optgroup label="검색 기준">
							<option value="buynum">구매자번호</option>
							<option value="selldate">날짜</option>
							<option value="sellnum">판매번호</option>
						</optgroup>
					</select>
				</span>
				
		        <input id="keyword" type="text" class="form-control" placeholder="검색어를 입력하세요">
		        
		        <span class="input-group-btn">
		           <button id="searchBtn" class="btn btn-default" type="button">검색</button>
		        </span>
	   		 </div><!-- /input-group -->
			
		</div><!--class = row  -->
		</div><!-- class = row -->

</div>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#searchBtn").click(function() {
		search();
		
	});
	$("#keyword").keydown(function(key) {

		if (key.keyCode == 13) {
			search();			
		}
		});

	function search() {
		var searchType = $("#searchSel option:selected").val();
		var keyword = $("#keyword").val();
		var url = "/smypage/adminlist/"+searchType+"/"+keyword+"/1";
		location.assign(url);
	}

		

	
});

</script>
</body>
</html>