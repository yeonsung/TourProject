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
		<c:forEach var="vo" items="${rlist}" step="1">
			<tr>
				<td style="font-size: 20px;">${vo.location}</td>
			</tr>
			<tr>
				<td style="font-size: 20px;">${vo.city}</td>
			</tr>
			<tr>
				<td style="font-size: 20px;"><b>${vo.title}</b></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>