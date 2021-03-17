<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<form class="form-signin" action="loginPost" method="post">
				<h2 class="form-signin-heading">로그인</h2>
				<label for="inputEmail" class="sr-only">ID</label> <input name="id"
					id="input_id" class="form-control" placeholder="ID를 입력하세요" required
					autofocus> <label for="input_password" class="sr-only">Password</label>
				<input name="pw" type="password" id="input_password"
					class="form-control" placeholder="Password" required>
				<div class="checkbox">
					<label> <input type="checkbox" value="remember-me">
						Remember me
					</label>
				</div>
				<button class="btn btn-md btn-primary btn-block" type="submit">로그인</button>
			</form>
		</div>
		<div class="row">
			<a href="/member/insert">
				<button	class="btn btn-md btn-primary btn-block">회원가입</button>
			</a>
		</div>
	</div>


	<script type="text/javascript">
		$(document).ready(function() {

		})
	</script>

</body>
</html>