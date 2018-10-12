<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
	<!-- <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/themes/base/jquery-ui.css" /> -->
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
	
	<script>
	$(function() {
		
		$('.haha').hover(function(){
			$('.haha').css('overflow-y','auto');
		},function(){
			$('.haha').css('overflow-y','hidden');
		});

/* 		setTimeout(function () {
			effect();
		}, 1000); */
		$.ajax({
			type : "get",
			url : "getRecentReviews.do",
			data : "pageNo=1",

			success : function(data) {
				$('.haha').html(data);
				effect();
				effect2();
			}
		});
		
	});
	function effect(){
		TweenMax.staggerTo($('#states path,text'), 1, {opacity:"1", width:"100", ease:Bounce.easeIn}, 0.1);
	}
	function effect2(){
		TweenLite.to($('#label-경기도'), 1, {y:131});	
	} 
	
</script>
<style>
@font-face{
	font-family: 'BMDOHYEON_ttf';
	src:url(font/BMDOHYEON_ttf.ttf) format('truetype');
}
	body{
		font-family: BMDOHYEON_ttf;
	}
	::-webkit-scrollbar {
	width: 10px;
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
	
	#states path,text{
		opacity:0;
	}
	a,a:hover{text-decoration: none}
	
   	section,#tabs{
   		height:600px;
   	}
	#tabs a{
		cursor:pointer;
	}
	.haha{
		margin-top:161px; 
		max-height:700px;
		display:inline-block;
		overflow-y : hidden;
		overflow-x : hidden;
		font-family: BMDOHYEON_ttf;
		border: 1px gray double;
		border-radius:50px;
		
	}
</style>

<script type="text/javascript" src="js/nav.js"></script>
</head>
<body style="background-color: rgba(249, 248, 244, 0.5)/* #EEF4F2 */">


   	<nav class="navbar navbar-defalt navbar-fixed-top" style="background-color: #fff">
      	<div id="header"> 
      		<div class="container">
         		<div class="navbar-header" style="margin-top: 15px">
            		<button type="button" class="navbar-toggle" id="menuSpan" data-toggle="collapse" data-target="#myNavbar">
	                    <span class="icon-bar"></span>
	               		<span class="icon-bar" style="margin-top: 2px"></span>
	               		<span class="icon-bar"></span>
            		</button>
            		<a href="index.jsp"><img src="img/main_logo2.png" width="150" height="47" style="background-color: #FFFAE5/* #0F6A8B  #F5EED2*/"></a>
         		</div> <!-- navbar-header -->
         		
	         	<div class="collapse navbar-collapse navbar-right" id="myNavbar" style="margin-top: 15px">
	            	<form class="navbar-form navbar-left" action="getdata.do">
	               		<div class="input-group">
	                  		<input type="text" class="form-control" placeholder="Search" name="search" id="myInput">
	                  		<div class="input-group-btn">
	                     		<button class="btn btn-default" type="submit">
	                        		<i class="glyphicon glyphicon-search"></i>
	                     		</button>
	                  		</div>
	               		</div>
	            	</form>
	            	
	            	<ul class="nav navbar-nav navbar-right">
	               		<li class="dropdown">
		                  	<a class="dropdown-toggle" data-toggle="dropdown" href="#" id="userMenu">
		                     	<span class="glyphicon glyphicon-user white">
		                     		<span class="caret" style="margin-left: 10px"></span>
		                     	</span>
		                  	</a>
		                  	<c:choose>
		                  	 	<c:when test="${vo != null}">
			                  	 	<ul class="dropdown-menu">
			                     	<li><a href="logout.do"><span class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;로그아웃</a></li>
			                     	<li><a href="myreviews.do?id=${sessionScope.vo.id}"><span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;내가 쓴 글</a></li>
			                     	<li><a href="scrap.do?id=${sessionScope.vo.id}"><span class="glyphicon glyphicon-bookmark"></span>&nbsp;&nbsp;스크랩</a></li>
			                     	<li><a href="write.jsp"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;글쓰기</a></li>
			                     	<li><a href="registerupdate.do?id=${sessionScope.vo.id}"><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;정보 수정</a></li>
			                  		</ul>
		                  		</c:when>
		      
		                  		<c:otherwise>
		                  			<ul class="dropdown-menu">
			                     	<li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;濡�洹몄��</a></li>
			                     	<li><a href="register.jsp"><i class="fas fa-user-plus"></i>&nbsp;&nbsp;����媛���</a></li>
			                  		</ul>
		                  		</c:otherwise>
		                  	</c:choose>
	               		</li>
					</ul>
				</div>
				<!-- myNavbar -->
			</div>
			<!-- container -->
		</div>
		<!-- header -->
		<div id="line"></div>
	</nav>
	<div class="row">
		<div class="col-lg-6 col-md-12 col-sm-12">
			<div id="container" style="display: inline-block; margin-top: 161px;"></div>
		</div>
<!-- 		<div class="col-lg-4" style="margin-top:161px;">
 -->
			<!-- <section>
				<nav id="tabs">
					<h1 align="center" style="cursor:default">RECENT REVIEWS</h1>
					<ul>
						<li><a href="javascript:void(0)">맛집</a></li>
						<li><a href="javascript:void(0)">관광</a></li>
						<li><a href="javascript:void(0)">숙소</a></li>
					</ul>
					<div id="tab-1"></div>
					<div id="tab-2"></div>
					<div id="tab-3"></div>
				</nav>

			</section> -->
			<div class="col-lg-5 haha col-md-12 col-sm-12">
				
			</div>
<!-- 		</div>
 -->	</div>
	<div class="footer" style="display: none;">
		
	</div>
	<form action="locationpage.do">
		<input type="hidden" name="location" value="">
	</form>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/2.0.2/TweenMax.min.js"></script>
	<script src="js/script.js"></script>

   <div style="height: 100px"></div>
   
   <div style="background-color: #DDDDDD; margin-top: 20px; padding-top: 50px; padding-bottom: 50px">
		<h2 align="center" style="color: gray">footer</h2>
   </div>
</body>

</html>