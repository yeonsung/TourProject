<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="css/nav.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 	<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
 	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>
	<script src="http://d3js.org/d3.v3.min.js"></script>
	<script type="text/javascript" src="js/nav.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
#carousel_con {
	width: 600px;
	height: 350px;
}
::-webkit-scrollbar-track {
	background: #EAEAEA;
	border-radius: 5px;
}
::-webkit-scrollbar-thumb {
	background: #D3D3D3;
	border-radius: 5px;
}
::-webkit-scrollbar-thumb:hover {
	background: #ADADAD;
}
</style>

<style>

section {
	height: 600px;
}

.carousel-inner>.item>img {
   top: 0;
   left: 0%;
   min-width: 360px;
   min-height: 200px;
}

* {
   box-sizing: border-box;
}

body {
background-color:DFE8E4;
	font-family: Arial, Helvetica, sans-serif;
	background-color: rgba(249, 248, 244, 0.5);
}

/* Create two columns/boxes that floats next to each other */
nav {
   float: left;
   left: 20px;
   width: 23%;
   height: 120%; /* only for demonstration, should be removed */
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

.overlay {
   position: absolute;
   bottom: 0;
   left: 100%;
   right: 0;
   background-color: gray;
   opacity: 0.6;
   overflow: hidden;
   width: 0;
   height: 100%;
   transition: .5s ease;
}

.container:hover .overlay {
   width: 100%;
   left: 0;
}

.text {
   color: white;
   font-size: 20px;
   position: absolute;
   top: 50%;
   left: 50%;
   -webkit-transform: translate(-50%, -50%);
   -ms-transform: translate(-50%, -50%);
   transform: translate(-50%, -50%);
   white-space: nowrap;
}

.image {
   display: block;
   width: 100%;
   height: auto;
}

.overlay {
	position: absolute;
	bottom: 0;
	left: 100%;
	right: 0;
	background-color: gray;
	opacity: 0.4;
	overflow: hidden;
	width: 0;
	height: 100%;
	transition: .5s ease;
}

.container:hover .overlay {
	width: 100%;
	left: 0;
}

.text {
	color: white;
	font-size: 20px;
	position: absolute;
	top: 50%;
	left: 50%;
	-webkit-transform: translate(-50%, -50%);
	-ms-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
	white-space: nowrap;
}

.image {
	display: block;
	width: 100%;
	height: auto;
}
</style>

<script>
/* 	var page = 1;
	$("#tabs").scroll(
			function() { alert("zsdzsd");
				/* if ($("#tabs").scrollTop() == 100) {
					console.log(++page);
					$("#tab-1").append(
							"<h1>Page " + page + "aaaaaaaaaaaaa<br>aaaaa<br>");
				} 
			});*/
	
	var page = 1;

	function showmore() {
		count += 1;
		$.ajax({
			type : "get",
			url : "getBestReviewBytag.do",
			data : {
				"location" : "${location}",
				"tag" : $('#distinguish').html(),
				"pageNo" : count,
				"size" : $('#listSize').html()
			//더보기 누르기 전의 갯수.
			},

			success : function(data) {
				$('#tab-1').html(data);
				$('#tab-2').html("");
				$('#tab-3').html("");
			}//callback
		});//ajax 
	}
	
	$(function() {
		
		$("#tabs").tabs();

		if ($("#tabs").height() < $(window).height()) {
			//alert($('#tabs').scrollTop());
		//alert("There isn't a vertical scroll bar");
		}

		$.ajax({
			type : "get",
			url : "getBestReviewBytag.do",
			data : {
				"location" : "${location}",
				"tag" : $('nav a').html()
			},

			success : function(data) {
				//$('#tab-1').html(data);
				$('#tab-1').html(data);
				$('#tab-2').html("");
				$('#tab-3').html("");
			}//callback
		});//ajax

		$('#thatdiv div:eq(0)').addClass('active');

		$('nav a').click(function() {
			var str = $(this).html();
			var loca = {
				"location" : "${location}",
				"tag" : str
			};

			$.ajax({
				type : "get",
				url : "getBestReviewBytag.do",
				data : loca,

				success : function(data) {
					if (str == '관광') {
						$('#tab-2').html(data);
						$('#tab-1').html("");
						$('#tab-3').html("");
					} else if (str == '숙소') {
						$('#tab-3').html(data);
						$('#tab-1').html("");
						$('#tab-2').html("");
					} else {
						//$('#tab-1').html(data);
						$('#tab-1').html(data);
						$('#tab-2').html("");
						$('#tab-3').html("");
					}
				}//callback
			});//ajax
		});//click
	});//tab
</script>


<script type="text/javascript" src="js/nav.js"></script>

</head>
<body>
	<header style="background-color:#FFFAE5;border-top: 7px solid transparent ;
	border-bottom: 7px solid transparent ;
	border-color: #f4ecc8; padding-bottom: 8px;">
		<div class="container" >
			<div class="navbar-header" style="margin-top: 15px">
				<button type="button" class="navbar-toggle" id="menuSpan"
					data-toggle="collapse" data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"
						style="margin-top: 2px"></span> <span class="icon-bar"></span>
				</button>
				<a href="index.jsp"><img src="img/main_logo2.png" width="150" height="47"></a>
			</div>
			<!-- navbar-header -->

			<div class="collapse navbar-collapse navbar-right" id="myNavbar"
				style="margin-top: 15px">
				<form class="navbar-form navbar-left" action="getdata.do">
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
					</a> <c:choose>
							<c:when test="${vo != null}">
								<ul class="dropdown-menu">
									<li><a href="logout.do"><span
											class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;로그아웃</a></li>
									<li><a href="myreviews.do?id=${sessionScope.vo.id}"><span
											class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;내가 쓴 글</a></li>
									<li><a href="scrap.do?id=${sessionScope.vo.id}"><span
											class="glyphicon glyphicon-bookmark"></span>&nbsp;&nbsp;스크랩</a></li>
									<li><a href="write.jsp"><span
											class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;글쓰기</a></li>
									<li><a href="registerupdate.do?id=${sessionScope.vo.id}"><span
											class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;정보 수정</a></li>
								</ul>
							</c:when>

							<c:otherwise>
								<ul class="dropdown-menu">
									<li><a href="login.jsp"><span
											class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;로그인</a></li>
									<li><a href="register.jsp"><i class="fas fa-user-plus"></i>&nbsp;&nbsp;회원가입</a></li>
								</ul>
							</c:otherwise>
						</c:choose></li>
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
		<nav id="tabs" style="overflow: scroll">
			<h1 align="center">BEST REVIEWS</h1>
			<ul>
				<li><a href="javascript:void(0)">맛집</a></li>
				<li><a href="javascript:void(0)">관광</a></li>
				<li><a href="javascript:void(0)">숙소</a></li>
			</ul>
			<div id="tab-1"></div>
			<div id="tab-2"></div>
			<div id="tab-3"></div>
		</nav>

		<article>
			<p>
			<h1 align="center" style="margin-bottom: 30px; color:rgb(116,191,237); font-weight: bold;">${requestScope.location}</h1>
			<div class="container" id="carousel_con">
				<div id="myCarousel" class="carousel slide" data-ride="carousel">

					<!-- Wrapper for slides -->
					<div class="carousel-inner" id="thatdiv">
						<c:forEach var="festivalVO" items="${flist}">
							<c:if test="${festivalVO.img ne null}">
								<div class="item">
									<img src="${festivalVO.img}"
										style="width: 600px; height: 350px;" class="image">
									<div class="overlay">
										<div class="text">${festivalVO.location}<br>${festivalVO.city}<br>${festivalVO.festivalName}<br>${festivalVO.startDate}부터<br>${festivalVO.endDate}까지</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
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

			<!-- City List -->
			<!-- City List -->
			<!-- City List -->
			<!-- City List -->
			<!-- City List -->
			<!-- City List -->
			<!-- City List -->

			<c:forEach items="${clist}" var="rList">

				<div align="center" class="col-sm-2">
					<hr>
					<br><a style="font-size: 25px; color: rgb(116,191,237); text-decoration:none; font-weight: bold;"
						href="getAttraction.do?city=${rList}&&location=${requestScope.location}">${rList}</a><br>
					<br>
				</div>
			</c:forEach>
			<hr>
			<br> <br>


		</article>
	</section>
	<footer></footer>
</body>
</html>