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
<link rel="stylesheet" href="css/nav.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style>
section {
	height: auto;
}

* {
	box-sizing: border-box;
}

body {
	font-family: Arial, Helvetica, sans-serif;
}

/* Create two columns/boxes that floats next to each other */
#tabs {
	float: left;
	left: 20px;
	width: 23%;
	height: 100%; /* only for demonstration, should be removed */
	padding: 20px;
}

/* Style the list inside the menu */
#tabs ul {
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


/* force scrollbar */
html {
	overflow-y: scroll;
}

body {
	font-family: sans-serif;
}
/* ---- grid ---- */
.grid {
	background: #fff;
}
/* clear fix */
.grid:after {
	content: '';
	display: block;
	clear: both;
}
/* ---- .grid-item ---- */
.grid-sizer, .grid-item {
	width: 20%;
	margin: 2%;
}

.grid-item {
	float: left;
}

.grid-item img {
	display: block;
	max-width: 100%;
}

.grid-item :hover {
	opacity: 0.3;
}
/*=================================================  */
.main {
	text-align: center;
}

#img :hover {
	opacity: 0.3;
}

#img {
	width: 250px;
	height: 182px;
}

.hashtag:before {
	content: "#";
}

#content {
	text-align: center;
}
</style>
<script>
	var count = 1;
	var page = 1;

	function showmore() {
		count += 1;
		$.ajax({
			type : "get",
			url : "getBestReviewCityBytag.do",
			data : {
				/*             "cblist" : "${cblist}",
				 */"tag" : "맛집",
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
		var city = "${city}";
		var location = "${location}";
		var pageNo = "${pageNo}";
		$("#tabs").tabs();

		$.ajax({
			type : "get",
			url : "getBestReviewCityBytag.do",
			data : {
				"tag" : "맛집",
				"city" : city,
				"location" : location,
				"pageNo" : count
			},

			success : function(data) {
				//$('#tab-1').html(data);
				$('#tab-1').html(data);
				$('#tab-2').html("");
				$('#tab-3').html("");
			}//callback
		});//ajax

		$('#tabs a').click(function() {
			var str = $(this).html();
			var loca = {
				//         "cblist" : "${cblist}",
				"tag" : str
			};

			$.ajax({
				type : "get",
				url : "getBestReviewCityBytag.do",
				data : loca,

				success : function(data) {
					if (str == '관광') {
						$('#tab-2').html(data);
						$('#tab-1').html("");
						$('#tab-3').html("");
						count = 1;
					} else if (str == '숙소') {
						$('#tab-3').html(data);
						$('#tab-1').html("");
						$('#tab-2').html("");
						count = 1;
					} else {
						//$('#tab-1').html(data);
						$('#tab-1').html(data);
						$('#tab-2').html("");
						$('#tab-3').html("");
						count = 1;
					}
				}//callback
			});//ajax
		});//click
	});//tab
</script>
<script type="text/javascript" src="js/nav.js"></script>
</head>
<body>
	<header>
		<nav class="navbar navbar-defalt navbar-fixed-top"
			style="background-color: #fff">
			<div id="header">
				<div class="container">
					<div class="navbar-header" style="margin-top: 15px">
						<button type="button" class="navbar-toggle" id="menuSpan"
							data-toggle="collapse" data-target="#myNavbar">
							<span class="icon-bar"></span> <span class="icon-bar"
								style="margin-top: 2px"></span> <span class="icon-bar"></span>
						</button>
						<a href="index.jsp"><img src="img/main_logo2.png" width="150"
							height="47"
							style="background-color: #FFFAE5 /* #0F6A8B  #F5EED2*/"></a>
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
								data-toggle="dropdown" href="#" id="userMenu"> <span
									class="glyphicon glyphicon-user white"> <span
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
											<li><a href="register.jsp"><i
													class="fas fa-user-plus"></i>&nbsp;&nbsp;����媛���</a></li>
										</ul>
									</c:otherwise>
								</c:choose></li>
						</ul>
					</div>
					<!-- myNavbar -->
				</div>
				<!-- container -->
			</div>
			<!-- header -->
			<div id="line"></div>
		</nav>
	</header>
	<!-- header -->
	<div id="line"></div>
	<div style="height: 150px;"></div>
	<section>
		<nav id="tabs" style="overflow-y: scroll; height: 800px; width: 25%">
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
			<div style="margin-left: 650px; margin-top: 150px">
				<c:forEach items="${locationList}" var="location">
      				<a href="getAttraction.do?city=${city}&&location=${location}"><h2>${location}</h2></a><br>
   				</c:forEach>
			</div>
		</article>
	</section>
</body>
</html>