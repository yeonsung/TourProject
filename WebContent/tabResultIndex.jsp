<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
	a {text-decoration: none}
</style>
</head>
<body>
	<table align="center">
		<c:forEach var="vo" items="${rlist.list}" step="1">
			<tr>
				<td style="font-size: 20px;padding:10px">${vo.location} ${vo.city}</td>
			
				<td style="font-size: 20px;"><b><a href="checkReview.do?num=${vo.reviewNum}">${vo.title}</a></b></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>