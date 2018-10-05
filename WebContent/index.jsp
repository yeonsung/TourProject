<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
	
	<!-- <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/themes/base/jquery-ui.css" /> -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 	<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
 	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>
	<script src="http://d3js.org/d3.v3.min.js"></script>
	<script type="text/javascript" src="js/nav.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
	$(function() {
		
		$("#tabs").tabs();
		$.ajax({
			type:"get",
			url:"getRecentReviews.do",
			data :"tag=맛집",
			
			success:function(data){
				$('#tab-1').html(data);	
				$('#tab-2').html("");
				$('#tab-3').html("");
			}
		});
		
		$('#tabs a').click(function() {
			var str = $(this).html();
			$.ajax({
				type:"get",
				url:"getRecentReviews.do",
				data :"tag="+str,
				
				success:function(data){
					if(str=="맛집"){
						$('#tab-1').html(data);	
						$('#tab-2').html("");
						$('#tab-3').html("");
					}
					else if(str=='관광'){
						$('#tab-2').html(data);	
						$('#tab-1').html("");
						$('#tab-3').html("");
					}
					else if(str=='숙소'){
						$('#tab-3').html(data);	
						$('#tab-1').html("");
						$('#tab-2').html("");
					}
				}//callback
			});//ajax
		});
	});
	</script>
<style>
	.contents{
		padding-top: 80px;
	}
   	#header {
      	border-bottom: 7px solid transparent;
      	-moz-border-imag: -moz-linear-gradient(left, DarkGreen, #64AB4C); /* #CEF6EC #A4A4A4 #BDBDBD #AEB404*/
		-webkit-border-image: -webkit-linear-gradient(left, DarkGreen, #64AB4C);
      	border-image: linear-gradient(to right, DarkGreen, #64AB4C);
      	border-image-slice: 1;
      	margin-top: 8px;
      	padding-bottom: 8px;
      	font: 67.5% "Lucida Sans Unicode", "Bitstream Vera Sans", "Trebuchet Unicode MS", "Lucida Grande", Verdana, Helvetica, sans-serif;
      	font-size: 14px;
   	}
   
   	.caret {
      	margin-left: 10px
   	}
</style>

<script type="text/javascript">
   	$(function() {
   		//================================ menu ================================
   		
   		 $('#myNavbar>ul li').click(function() {
    		var scrollPosition = $($(this).attr('data-target')).offset().top;
    		$('body, html').animate({
    			scrollTop: scrollPosition
    		}, 500); //animate
		}); //click

      	$('#menuSpan .icon-bar').css('background', 'green');
      
      	$('#myNavbar li a').css({
         	'color' : 'black',
         	'font-weight' : 'bold'
      	}); //css
      
      	$('#myNavbar li a').hover(function() { 
         	//���� 硫��대� 留��곗�� �щ�ㅻ�⑥�� ��
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
         	'border-radius': '2px'
      	}); //css
   	}); //ready
</script>

</head>
<body>


   	<nav class="navbar navbar-defalt navbar-fixed-top" style="background-color: #fff">
      	<div id="header">
      		<div class="container">
         		<div class="navbar-header" style="margin-top: 15px">
            		<button type="button" class="navbar-toggle" id="menuSpan" data-toggle="collapse" data-target="#myNavbar">
	                    <span class="icon-bar"></span>
	               		<span class="icon-bar" style="margin-top: 2px"></span>
	               		<span class="icon-bar"></span>
            		</button>
            		<a href="index.jsp"><img src="img/main_logo.png" width="150"></a>
         		</div> <!-- navbar-header -->
         		
	         	<div class="collapse navbar-collapse navbar-right" id="myNavbar" style="margin-top: 15px">
	            	<form class="navbar-form navbar-left" action="/action_page.php">
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
		                  	<a class="dropdown-toggle" data-toggle="dropdown" href="#">
		                     	<span class="glyphicon glyphicon-user text-success">
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
			                     	<li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;로그인</a></li>
			                     	<li><a href="register.jsp"><i class="fas fa-user-plus"></i>&nbsp;&nbsp;회원가입</a></li>
			                  		</ul>
		                  		</c:otherwise>
		                  	</c:choose>
	               		</li>
					</ul>
				</div> <!-- myNavbar -->
      		</div> <!-- container -->
		</div> <!-- header -->
    	<div id="line"></div>
	</nav>
	<div class="row">
		<div class="col-lg-7">
			<div id="container" style="display:inline-block; margin-top:161px;"></div>
		</div>
		<div class="col-lg-4" style="margin-top:161px;">
			<section>
				<nav id="tabs">
					<h1 align="center">RECENT REVIEWS</h1>
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
		
			</section>
		</div>
	</div>
	<div class="footer" style="display:none;">
		<p>이 지도는 <a href="http://d3js.org/" target="_blank">D3.js</a>로 만들었으며 <a href="http://bl.ocks.org/mbostock/2206340" target="_blank">mbostock’s block #2206340</a>을 보고 만든 것입니다.</p>
	</div>
	<form action="locationpage.do"><input type="hidden" name="location" value=""></form>
	<script src="js/script.js"></script>
	

   <div style="height: 100px"></div>
</body>

</html>