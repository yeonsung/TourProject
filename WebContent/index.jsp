<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Website Font style -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
<style>
	.contents{
		padding-top: 80px;
	}

   	#header {
   		background: #f1f1f1;
      	margin-top: 8px;
      	padding-bottom: 8px;
      	font: 67.5% "Lucida Sans Unicode", "Bitstream Vera Sans", "Trebuchet Unicode MS", "Lucida Grande", Verdana, Helvetica, sans-serif;
      	font-size: 14px;
      	height: 125px;
   	}
   	.caret {
      	margin-left: 10px;
   	}
   	body{
   		background: #2196F3;
   	}
   	.footer {
    padding: 20px;
    text-align: center;
    background: #ddd;
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
            		<img src="img/main_logo.png" width="150">
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
			                     	<li><a href="myreviews.do?id=yun"><span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;내가 쓴 글</a></li>
			                     	<li><a href="scrap.do?id=yun"><span class="glyphicon glyphicon-bookmark"></span>&nbsp;&nbsp;스크랩</a></li>
			                     	<li><a href="#"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;글 작성</a></li>
			                     	<li><a href="registerupdate.do?id="><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;정보 수정</a></li>
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
	</nav>
 
<div class="footer">
  <h2>Footer</h2>
</div>

</body>
</html>