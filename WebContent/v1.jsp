<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style>
.carousel-inner>.item>img {
	top: 0;
	left: 0;
	min-width: 100%;
	min-height: 400px;
}

* {
	box-sizing: border-box;
}

body {
	font-family: Arial, Helvetica, sans-serif;
}

/* Style the header */
header {
	text-align: center;
	font-size: 35px;
}

/* Create two columns/boxes that floats next to each other */
nav {
	float: left;
	width: 30%;
	height: 300px; /* only for demonstration, should be removed */
	padding: 20px;
}

/* Style the list inside the menu */
nav ul {
	list-style-type: none;
	padding: 0;
}

article {
	float: left;
	padding: 20px;
	width: 70%;
	height: 300px; /* only for demonstration, should be removed */
}

/* Clear floats after the columns */
section:after {
	content: "";
	display: table;
	clear: both;
}

/* Style the footer */
footer {
	padding: 10px;
	text-align: center;
	color: white;
}
</style>
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>
</head>
<body>
	<header>
		<h2>여기 뭐냐 타이틀이랑 로그인 상태 등등의 것들</h2>
	</header>
	<hr>
	<section>
		<nav id="tabs">
			<ul>
				<li><a href="#tabs-1">전체</a></li>
				<li><a href="#tabs-2">먹거리</a></li>
				<li><a href="#tabs-3">사진</a></li>
				<li><a href="#tabs-4">숙소</a></li>
			</ul>
			<div id="tabs-1">
				<p>여긴 전체 사진들</p>
			</div>
			<div id="tabs-2">
				<p>여긴 먹거리 사진들</p>
			</div>
			<div id="tabs-3">
				<p>여긴 사진들?</p>
			</div>
			<div id="tabs-4">
				<p>여긴 숙소</p>
			</div>
		</nav>

		<article>
			<p>
			<h1 align="center" style="margin-bottom: 30px" >경 기 도</h1>
			<div class="container">
				<div id="myCarousel" class="carousel slide" data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner">
						<div class="item active">
							<img src="img/la.jpg" alt="Los Angeles" style="width: 100%;">
						</div>

						<div class="item">
							<img src="img/chicago.jpg" alt="Chicago" style="width: 100%;">
						</div>

						<div class="item">
							<img src="img/ny.jpg" alt="New york" style="width: 100%;">
						</div>
					</div>

					<!-- Left and right controls -->
					<a class="left carousel-control" href="#myCarousel"
						data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#myCarousel"
						data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>
			</div>
			</p>


			<p>여기 사진이랑 로케이션</p>
		</article>
	</section>

	<footer>
		<p>여기 푸터</p>
	</footer>
</body>
</html>