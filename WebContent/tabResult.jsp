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
		<c:forEach var="relist" items="${relist}" step="1">
			<tr>
				<td><img src=${relist.mainImage}><br></td>
			</tr>
			<tr>
				<td style="font-size: 20px;">#${relist.city}</td>
			</tr>
			<tr>
				<td style="font-size: 20px;"><b>${relist.title}</b></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>