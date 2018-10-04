<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style type="text/css">
td td{
font-size: 0.5em;}
</style>
</head>
<body>
	<table align="center">
		<c:forEach var="relist" items="${relist}" step="1">
			<tr>
				<td><img src=${relist.img}></td>
				<td>${relist.city}</td>
				<td>${relist.like}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>