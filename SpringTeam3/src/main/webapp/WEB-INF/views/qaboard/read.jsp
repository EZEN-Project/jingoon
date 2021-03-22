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

<!-- 로그인&로그아웃 -->
    <div class="container-fluid">
        <jsp:include page="/WEB-INF/views/header/loginHeader.jsp"></jsp:include>
       	
    </div>
    <hr>
 <form action="/qaboard/delete" method="post">
      <input type="hidden" name="bnum" value="${vo.bnum}">      
   </form>

   <div class="container">
      <div class="row">
         <h1 class="jumbotron" style="text-align: center;">글 자세히 보기</h1>
      </div>
     
      <div class="row">
         <div class="form-group">
            <label for="title">제목</label>
            <input class="form-control" readonly id="title" name="title" value="${vo.title}">
         </div>
         <div class="form-group">
            <label for="writer">작성자</label>
            <input class="form-control" readonly id="writer" name="writer" value="${vo.writer}">
         </div>
         <div class="form-group">
            <label for="content">내용</label>
            <textarea class="form-control" id="content" name="content">${vo.content}</textarea>
         </div>
      </div>
      <c:choose>
       <c:when test="${login.id==vo.writer}">
      <div class="row">
         <button id="read_btn_update" class="btn btn-warning">수정</button>
         <button id="read_btn_delete" class="btn btn-danger">삭제</button>
         <button id="reply_form" class="btn">댓글</button>
         <button id="read_btn_list" class="btn btn-info">목록</button>
         
      </div><!--class=row  -->
      </c:when>
      <c:when test="${memberVO.mType == 1004}">
      <div class="row">
     
         <button id="read_btn_delete" class="btn btn-danger">삭제</button>
         <button id="reply_form" class="btn">댓글</button>
         <button id="read_btn_list" class="btn btn-info">목록</button>
         
      </div><!--class=row  -->
      </c:when>
      <c:otherwise>
    	 <button id="read_btn_list" class="btn btn-info">목록</button>
      
      </c:otherwise>
   	</c:choose>
      <div class="row" style="margin-top: 50px;">
         <div class="collapse" id="myCollapse">
           <div class="form-group">
             <label for="replyer">작성자</label>
             <input class="form-control" id="replyer" name="replyer" value="${vo.writer}" readonly="readonly">
           </div>
           
           <div class="form-group">
             <label for="replyText">내용</label>
             <input class="form-control" id="replyText" name="replyText">
           </div>
           
           <div class="form-group">
              <button id="board_read_btn_reply_insert" class="btn btn-info">댓글 등록</button>
              <button id="board_read_btn_reply_reset" class="btn btn-default">초기화</button>
           </div>
         </div>
      </div><!--class = row  -->
      
      <div class="row">
         <div id="replies">
            
         </div>
      </div><!--class = row  -->
      
      
      <div class="row">
         <div id="myModal" class="modal fade" tabindex="-1" role="dialog">
           <div class="modal-dialog" role="document">
             <div class="modal-content">
               <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                 <h4 class="modal-title">hello world</h4>
               </div>
               <div class="modal-body">
                 <input class="form-control" id="modal_replyText"/>
               </div>
               <div class="modal-footer">
                 <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
                 <button id="modal_btn_update" type="button" class="btn btn-primary" data-dismiss="modal">댓글 수정</button>
               </div>
             </div><!-- /.modal-content -->
           </div><!-- /.modal-dialog -->
         </div><!-- /.modal -->
      </div><!-- class = row -->
   
   </div><!--class=container  -->
   <script type="text/javascript">
   $(document).ready(function() {
       
       var bnum = ${vo.bnum};

       
       
       $("#modal_btn_update").click(function() {
          var replyText = $("#modal_replyText").val();
          var rno = $(".modal-title").text();
          $.ajax({
             type : "put",
             url : "/qareply",
             headers : {
                "Content-Type" : "application/json",
                "X-HTTP-Method-Override" : "put"
             },
             data : JSON.stringify({
                rno : rno,
                replyText : replyText
             }),
             dataType : "text",
             success : function(result) {
                if(result == "o"){
                   alert("수정하였습니다.");
                   list(bnum, 1);/*댓글의 페이지를 입력해야 함  */
                }else{
                   alert("수정 실패했습니다.");
                }
                
             }
          });
       });
       
       $("#replies").on("click", ".btn-update", function() {
          var rno = $(this).attr("data-rno");
          $(".modal-title").text(rno);
          
          var replyText = $(this).prev("p").text();
          $("#modal_replyText").val(replyText);
          
          $("#myModal").modal("show");
       });
       
       $("#replies").on("click",".btn-delete", function() {
          var backEle = $(this);
          var rno = $(this).attr("data-rno");
          var yesOrNo = confirm("정말로 삭제하겠습니까?");
          if(yesOrNo){
             $.ajax({
                type : 'delete',
                url : "/qareply",
                headers : {
                   "Content-Type" : "application/json",
                   "X-HTTP-Method-Override" : "delete"
                },
                data : JSON.stringify({
                   rno : rno,
                   bnum : bnum
                }),
                dataType : "text",
                success : function(result) {
                   if(result == "o"){
                      backEle.parent().parent().remove();
                   }else{
                      alert("삭제를 실패했습니다.")
                   }
                }
             });
          }else{
             alert("삭제가 취소되었습니다.")
          }
       });
       
       
       
       $("#board_read_btn_reply_insert").click(function() {
          var replyer = $("#replyer").val();
          var replyText = $("#replyText").val();
          
          $.ajax({
             type : "post",
             url : "/qareply",
             headers : {
                "Content-Type" : "application/json",
                "X-HTTP-Method-Override" : "post"
             },
             data : JSON.stringify({
                bnum : bnum,
                replyer : replyer,
                replyText : replyText
             }),
             dataType : "text",
             success : function(result) {
                if(result == "o"){
                   alert("댓글 입력이 성공했습니다.");
                   replyInputReset();
                   list(bnum, 1);
                   
                }else{
                   alert("댓글 입력이 실패했습니다.");
                }
             }
             
          });
       });
       
       
       
       $("#board_read_btn_reply_reset").click(function() {
          replyInputReset();
       });
       
       
       $("#reply_form").click(function() {
          $("#myCollapse").collapse("toggle");
       });
       
       
       $("#read_btn_delete").click(function() {
    	   var yesOrNo = confirm("정말로 삭제하겠습니까?");
    	   if (yesOrNo) {
    		   $("form").submit();
		   }else {
			   alert("삭제가 취소되었습니다.")
		   };
    	  
       });
       
       
       
       $("#read_btn_update").click(function() {
          location.assign("/qaboard/update/${vo.bnum}");
       });
       
       
       $("#read_btn_list").click(function() {
          location.assign("/qaboard/list/${param.curPage}");
          
       });
       
       
       list(${vo.bnum}, 1);
       
       
       
       function replyInputReset(){
          $("#replyText").val("");
       }
       
       function list(bnum, curPage) {
          $.getJSON("/qareply/"+bnum+"/"+curPage, function(result) {
             var str = '';
             
             var arr = result["list"];
             for(var i=0; i<arr.length; i++){
                var obj = arr[i];
                
                var d = new Date(obj["updateDate"]);
                var year = d.getFullYear();
                var month = d.getMonth()+1 < 10 ? '0'+(d.getMonth()+1) : (d.getMonth()+1);
                var date = d.getDate() < 10 ? '0'+ d.getDate() : d.getDate();
                
                var dateStr = year+"/"+month+"/"+date;
                
                if (${login.id == vo.writer}) { 
                	str += '<div class="panel panel-primary">'+
                    '<div class="panel-heading">'+
                    '<span>글번호: '+obj["rno"]+', <span class="glyphicon glyphicon-user" aria-hidden="true"></span>작성자: '+obj["replyer"]+'</span>'+
                    '<span class="pull-right"><span class="glyphicon glyphicon-time" aria-hidden="true"></span>최종 수정일: '+dateStr+' </span>'+
                   '</div>'+
                   '<div class="panel-body">'+
                     '<p>'+obj["replyText"]+'</p>'+
                     '<button data-rno="'+obj["rno"]+'"class="btn btn-warning btn-xs btn-update">수정</button>'+
                     '<button data-rno="'+obj["rno"]+'"class="btn btn-danger btn-xs btn-delete">삭제</button>'+
                   '</div>'+
                 '</div>';
					
				}else if (${memberVO.mType == 1004}) {
					str += '<div class="panel panel-primary">'+
                    '<div class="panel-heading">'+
                    '<span>글번호: '+obj["rno"]+', <span class="glyphicon glyphicon-user" aria-hidden="true"></span>작성자: '+obj["replyer"]+'</span>'+
                    '<span class="pull-right"><span class="glyphicon glyphicon-time" aria-hidden="true"></span>최종 수정일: '+dateStr+' </span>'+
                   '</div>'+
                   '<div class="panel-body">'+
                     '<p>'+obj["replyText"]+'</p>'+
                     '<button data-rno="'+obj["rno"]+'"class="btn btn-danger btn-xs btn-delete">삭제</button>'+
                   '</div>'+
                 '</div>';					
				}
                else {
					 str += '<div class="panel panel-primary">'+
	                  '<div class="panel-heading">'+
	                   '<span>글번호: '+obj["rno"]+', <span class="glyphicon glyphicon-user" aria-hidden="true"></span>작성자: '+obj["replyer"]+'</span>'+
	                   '<span class="pull-right"><span class="glyphicon glyphicon-time" aria-hidden="true"></span>최종 수정일: '+dateStr+' </span>'+
	                  '</div>'+
	                  '<div class="panel-body">'+
	                    '<p>'+obj["replyText"]+'</p>'+
	                    
	                  '</div>'+
	                '</div>';
					
				}
                
               
                
             }
             
             $("#replies").html(str);
             
          });
       }
       
       
       
    });
   </script>
</body>
</html>