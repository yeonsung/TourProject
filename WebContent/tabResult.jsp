<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center">
		<c:forEach var="relist" items="${relist.list}"  varStatus="vlist">
			<c:if test="$('vList.count').count/3==0"></c:if>
			<tr>
				<td><img src=${relist.mainImage}>
				<img width="300px" height="250px" src="./img/la.jpg"><br></td>
			</tr>
			<tr>
				<td style="font-size: 20px;"><a href="#">#${relist.city}</a></td>
			</tr>
			<tr>
				<td style="font-size: 20px;"><a href="checkReview.do?num=${relist.reviewNum}"><b>${relist.title}</b></a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>