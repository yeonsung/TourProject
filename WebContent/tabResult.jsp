<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
</script>
</head>

<body>
	<table align="center">
		<c:forEach var="vo" items="${relist.list}">
			<tr>
				<td><img src=${vo.mainImage}><br></td>
			</tr>
			<tr>
				<td style="font-size: 20px;"><a href="getAttraction.do?city=${vo.city}">${vo.city}</a></td>
			</tr>
			<tr>
				<td style="font-size: 20px;"><b><a href="checkReview.do?num=${vo.reviewNum}">${vo.title}</a></b></a></td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${flag!=false}">
		<center><button type="button" style="align-content:center; width: 150px; height: 30px; left: 50%;" id="showMore" onclick="showmore()">더보기</button></center>
	</c:if>
	<div id="distinguish" style="display: none">${param.tag}</div>
	<div id="listSize" style="display: none">${listSize}</div>
</body>
</html>