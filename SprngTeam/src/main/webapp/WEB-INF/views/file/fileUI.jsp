<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<script src="/resources/js/upload.js" type="text/javascript"></script>

<style type="text/css">
	.fileDrop{
		width: 100%;
		height: 100px;
		border: 1px red dotted;
	}
	.uploadList{
		list-style: none;
	}
	.deleteFile{
		cursor: pointer;
	}
</style>

</head>
<body>
<!-- 첨부 파일 -->
		
			<label class="fileInsert">업로드할 파일을 드랍하세요</label>
			<div class="fileDrop fileInsert">
			
			</div>
			<ul class="clearfix uploadList">
				
			</ul>


<script type="text/javascript">
	$(document).ready(function() {
			
		// 기존 이벤트 막기
		$(".fileDrop").on("dragenter dragover", function(e) {
			e.preventDefault();
		})
		
		// 드랍파일 업로드&업로드 파일 출력		
		$(".fileDrop").on("drop", function(e) {
			e.preventDefault();
			var files = e.originalEvent.dataTransfer.files;
			var file = files[0];
			var formData = new FormData();
			formData.append("file", file);
			$.ajax({
				type : 'post',
				url : '/ajaxtest',
				processData : false,
				contentType : false,
				data : formData,
				dataType : 'text',
				success : function(data) {
					$(".uploadList").append(uploadImage(data));
				}
			});
		})
		
	});
</script>
</body>
</html>