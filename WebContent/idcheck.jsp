<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('input[type=button]').click(function() {
			var of = window.opener.document.registerForm;
			if(${flag} == 'true'){ 
				of.id.value = "";
				of.id.focus();
			} else{
				of.password.focus();
				of.flag.value = of.id.value; 
				of.id.readOnly = true;
			}
				
			self.close(); 
		});
	});
</script>
<style>
body {
	margin: 10px
}
</style>
</head>
	<c:set var="message" value="해당 ID는 이미 사용중입니다."/>
	<c:if test="${flag == 'false'}">
		<c:set var="message" value="해당 ID는 사용 가능합니다."/>
	</c:if>
<body>
	<div>
	<br><b>${param.id}</b>, ${message}<br><br>
	<input type="button" class="btn btn-primary" value="확인"> <!-- String 으로 값을 넘겨야한다. 인자값으로 넘어가기 때문에 타입을 지정할 수 없다. -->
	</div>
</body>
</html>