<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${check==true}">
		<script type="text/javascript">
			alert("로그인 성공");
			window.document.location.href="index.jsp";
		</script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			alert("로그아웃 하셨습니다.");
			window.document.location.href="index.jsp";
		</script>
	</c:otherwise>
</c:choose>
</body>
</html>