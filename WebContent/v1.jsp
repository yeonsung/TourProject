<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
.contents {
	padding-top: 80px;
}

header {
	border-bottom: 7px solid transparent;
	-moz-border-imag: -moz-linear-gradient(left, DarkGreen, #64AB4C);
	/* #CEF6EC #A4A4A4 #BDBDBD #AEB404*/
	-webkit-border-image: -webkit-linear-gradient(left, DarkGreen, #64AB4C);
	border-image: linear-gradient(to right, DarkGreen, #64AB4C);
	border-image-slice: 1;
	margin-top: 8px;
	padding-bottom: 8px;
	font: 67.5% "Lucida Sans Unicode", "Bitstream Vera Sans",
		"Trebuchet Unicode MS", "Lucida Grande", Verdana, Helvetica,
		sans-serif;
	font-size: 14px;
}

.caret {
	margin-left: 10px
}

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

/* Create two columns/boxes that floats next to each other */
nav {
	float: left;
	left: 20px;
	width: 23%;
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
	color: black;
}

tr td {
	font-size: 30px;
}
</style>
<script>
	$(function() {
		
		$("#tabs").tabs();
		$('nav a').click(function() {
			var str = $(this).html();
			$.ajax({
				type:"get",
				url:"getBestReviewBytag.do",
				data :"location=${requestScope.location}&&tag="+str,
				
				success:function(data){
					if(str=="맛집"){
						$('#tab-1').html(data);	
						$('#tab-2').html("")
						$('#tab-3').html("")
					}
					else if(str=='관광'){
						$('#tab-2').html(data);	
						$('#tab-1').html("")
						$('#tab-3').html("")
					}
					else if(str=='숙소'){
						$('#tab-3').html(data);	
						$('#tab-1').html("")
						$('#tab-2').html("")
					}
				}//callback
			});//ajax
		});//on
	});//tab
</script>


<script type="text/javascript">
	$(function() {
		//================================ menu ================================

		$('#myNavbar>ul li').click(function() {
			var scrollPosition = $($(this).attr('data-target')).offset().top;
			$('body, html').animate({
				scrollTop : scrollPosition
			}, 500); //animate
		}); //click

		$('#menuSpan .icon-bar').css('background', 'green');

		$('#myNavbar li a').css({
			'color' : 'black',
			'font-weight' : 'bold'
		}); //css

		$('#myNavbar li a').hover(function() {
			//상단 메뉴바 마우스 올려놨을 때
			$(this).css({
				'color' : 'green',
				'background' : 'rgba(242, 242, 242, 0.5)'
			}); //css

		}, function() {
			$(this).css({
				'color' : 'black',
				'background' : 'white'
			}); //css
		}); //hover

		$('.dropdown-menu').css({
			'margin-top' : '9px',
			'min-width' : '12px',
			'border-radius' : '2px'
		}); //css
	}); //ready
</script>

</head>
<body>
	<header>
		<div class="container">
			<div class="navbar-header" style="margin-top: 15px">
				<button type="button" class="navbar-toggle" id="menuSpan"
					data-toggle="collapse" data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"
						style="margin-top: 2px"></span> <span class="icon-bar"></span>
				</button>
				<img src="img/main_logo.png" width="150">
			</div>
			<!-- navbar-header -->

			<div class="collapse navbar-collapse navbar-right" id="myNavbar"
				style="margin-top: 15px">
				<form class="navbar-form navbar-left" action="/action_page.php">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Search"
							name="search" id="myInput">
						<div class="input-group-btn">
							<button class="btn btn-default" type="submit">
								<i class="glyphicon glyphicon-search"></i>
							</button>
						</div>
					</div>
				</form>

				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"> <span
							class="glyphicon glyphicon-user text-success"> <span
								class="caret" style="margin-left: 10px"></span>
						</span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#"><span
									class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;로그아웃</a></li>
							<li><a href="myreviews.do?id=yun"><span
									class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;내가 쓴 글</a></li>
							<li><a href="scrap.do?id=yun"><span
									class="glyphicon glyphicon-bookmark"></span>&nbsp;&nbsp;스크랩</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;글
									작성</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;정보
									수정</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- myNavbar -->
		</div>
		<!-- container -->
	</header>
	<!-- header -->
	<div id="line"></div>
	<div style="height: 70px;"></div>
	<section>
		<nav id="tabs">
			<h1 align="center">BEST REVIEWS</h1>
			<ul>
				<li><a href="javascript:void(0)">맛집</a></li>
				<li><a href="javascript:void(0)">관광</a></li>
				<li><a href="javascript:void(0)">숙소</a></li>
			</ul>
			<div id="tab-1">
				
			</div>
			<div id="tab-2">
				
			</div>
			<div id="tab-3">
				
			</div>
		</nav>

		<article>
			<p>
			<h1 align="center" style="margin-bottom: 30px">${requestScope.location}</h1>
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
			<br> <br>
			<table align="center">
				<c:forEach var="clist" items="${clist}" step="1">
					<tr>
						<td>dd</td>
						<td>${clist}</td>
					</tr>
				</c:forEach>
			</table>
		</article>
	</section>

	<footer>
		<p>여기 푸터</p>
	</footer>
</body>
</html>