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
	            <p class="lead">회원 정보 수정</p>
	            </div>
	        </div>
	       
	        	<form action="/member/update" id="form" name="form" method="post">
	        	 <input id="pw" name="pw" type="password" class="hidden" value="${memberVO.pw}" >
					<div class="form-group">
		        	  <div class=".col-xs-12 .col-md-8">
					    <div class="input-group">
					      <span id="span1" class="input-group-addon">ID</span>
					     	 <input name="id" class="form-control" value="${memberVO.id}" readonly="readonly" placeholder="id를 입력하세요">
					    </div><!-- /input-group -->
					  </div><!-- /.col-lg-6 -->
				  </div><!-- /.form-group -->
				  
				  <div class="form-group">
					<div class=".col-xs-12 .col-md-8">
						<div class="input-group">
						  <span class="input-group-addon" id="email">email</span>
						  <input name="email" type="email" class="form-control" value="${memberVO.email}" required placeholder="email을입력하세요" aria-describedby="email">
						</div>
					</div>
				</div>
								
				<div class="form-group">
					<div class=".col-xs-12 .col-md-8">
						<div class="input-group">
						  <span class="input-group-addon" id="name">이름</span>
						  <input name="name" type="text" class="form-control" value="${memberVO.name }" readonly="readonly" required placeholder="이름을 입력하세요" aria-describedby="name">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class=".col-xs-12 .col-md-8">
						<div class="input-group">
						  <span class="input-group-addon" id="phone">전화번호</span>
						  <input name="phone" type="tel" maxlength="11" class="form-control" value="${memberVO.phone }" required placeholder="전화번호를 -를 제외하고 입력하세요" aria-describedby="phone">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class=".col-xs-12 .col-md-8">
						<div class="input-group">
						  <span class="input-group-addon" id="birth">생일</span>
						  <input name="birth" type="date" class="form-control" value="${memberVO.birth }" aria-describedby="birth">
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class=".col-xs-12 .col-md-8">
						<div class="input-group">
							<input type="button" class="btn btn-default" onclick="sample4_execDaumPostcode()" value="주소 검색"><br>
							<input type="text" class="form-control" id="sample4_postcode" readonly placeholder="우편번호"> 
							<input type="text" class="form-control" name="address" value="${memberVO.address}" id="sample4_roadAddress" required readonly placeholder="도로명주소"> 
							<input type="hidden" class="form-control" id="sample4_jibunAddress" readonly placeholder="지번주소">
							<span id="guide" style="color: #999; display: none"></span> 
							
							<input type="text" class="form-control" id="sample4_detailAddress" placeholder="상세주소">
							<input type="text" class="form-control" id="sample4_extraAddress" placeholder="참고항목">
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class=".col-xs-12 .col-md-8">
						<div class="input-group">	
							<input class="btn btn-primary" type="submit" value="저장">
							<input id="member_update_btn_delete" class="btn btn-danger" type="button" value="탈퇴하기">
						</div>
					</div>
				</div>
				</form>
				</div>
	        </div><!-- /.row -->
		</div><!--/.container  -->

	
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>

  	<script type="text/javascript">
		$(document).ready(function(){
			$("#member_update_btn_delete").click(function(e) {
				e.preventDefault();
				var deleteConfig = $("#pw").val();
				var inputConfig= prompt("탈퇴확인: 비밀번호를 입력하세요");
				if(inputConfig == deleteConfig){
					$("#form").attr("action", "/member/updateD");
					$("#form").submit();
				}
				
			});
			
			
		});
	</script>
</body>
</html>