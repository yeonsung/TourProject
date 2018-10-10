<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID,비밀번호 찾기</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
.container {
	height: 700px;
	width: 400px;
}
</style>
<body>
	<div class="container">
		<h3>아이디, 비밀번호 찾기</h3>
		<p>이름, 주민등록번호(앞6자리), 전화번호를 입력해주세요.</p>
		<form action="findidpass.do" name="findidpassFrm" method="post">
			<div class="form-group">
				<label for="username">이름:</label> <input type="text"
					class="form-control" id="username" name="username"
					placeholder="이름을 입력하세요." required="required"> <label
					for="username">주민등록번호:</label> <input type="text"
					class="form-control" id="ssn" name="ssn"
					placeholder="주민등록번호 앞6자리를 입력하세요." required="required"> <label
					for="username">전화번호:</label> <input type="text"
					class="form-control" id="tel" name="tel" placeholder="전화번호를 입력하세요."
					required="required">
			</div>
			<span class="pull-right"><input type="submit"
				class="btn btn-primary" value="찾기"></span>
		</form>
	</div>
	${check }
	<c:choose>
		<c:when test="${check == 'ok'}">
			<script type="text/javascript">
				alert("아이디: " + "${rvo.id}" + " // " + "비밀번호: "
						+ "${rvo.password}");
				window.document.location.href = "login.jsp";
			</script>
		</c:when>
		<c:when test="${check == 'fail'}">
			<script type="text/javascript">
				alert("입력하신 정보에 해당하는 아이디와 비밀번호가 없습니다.");
			</script>
		</c:when>
		<c:otherwise>
			alert("왜 안될까??");
		</c:otherwise>
	</c:choose>
</body>
</html>