<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
	.contents{
		padding-top: 80px;
	}
   	#header {
      	border-bottom: 7px solid transparent;
      	-moz-border-imag: -moz-linear-gradient(left, DarkGreen, #64AB4C);
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
<script type="text/javascript" src="js/nav.js"></script>
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
		                  	<ul class="dropdown-menu">
		                     	<li><a href="#"><span class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;로그아웃</a></li>
		                     	<li><a href="myreviews.do?id=yun"><span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;내가 쓴 글</a></li>
		                     	<li><a href="scrap.do?id=yun"><span class="glyphicon glyphicon-bookmark"></span>&nbsp;&nbsp;스크랩</a></li>
		                     	<li><a href="#"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;글 작성</a></li>
		                     	<li><a href="#"><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;정보 수정</a></li>
		                  	</ul>
	               		</li>
					</ul>
				</div> <!-- myNavbar -->
      		</div> <!-- container -->
		</div> <!-- header -->
    	<div id="line"></div>
	</nav>

   	<div style="height: 150px"></div>
   
	<h1 align="center">Scrap</h1><br><br>
	
	<c:forEach items="${lvo.list}" var="rList">
		<a href="#">
			<div align="center" class="col-sm-4">
				<hr>
				${rList.date}<br>
				<img src="${rList.mainImage}" width="350"><br>
				${rList.title}&nbsp;&nbsp;
				<a href="deleteScrap.do?reviewNum=${rList.reviewNum}&&id=${rList.id}"><input type="button" value="삭제"></a>
				<hr><br><br>
			</div>
		</a>
	</c:forEach>
	
	<br><br>
	
	<div align="center" class="col-sm-12">  
		<c:set var="pb" value="${lvo.pb}"></c:set>
		<c:if test="${pb.previousPageGroup}">
			<ul class="pagination pagination-sm">
	    		<li><a href="scrap.do?id=yun&&pageNo=${pb.startPageOfPageGroup-1}">&#60;</a></li>
	  		</ul>
		</c:if>
		
		<c:forEach var="i" begin="${pb.startPageOfPageGroup}" end="${pb.endPageOfPageGroup}">
			<c:choose>
				<c:when test="${pb.nowPage!=i}">
					<ul class="pagination">
		    			<li><a href="scrap.do?id=yun&&pageNo=${i}">${i}</a></li>
		    		</ul>
				</c:when>
				<c:otherwise>
					<ul class="pagination">
		    			<li><a href="#">${i}</a></li>
		    		</ul>
				</c:otherwise>
			</c:choose>
			&nbsp;
		</c:forEach>
		
		<c:if test="${pb.nextPageGroup}">
			<ul class="pagination pagination-sm">
	    		<li><a href="scrap.do?id=yun&&pageNo=${pb.endPageOfPageGroup+1}">&#62;</a></li>
	  		</ul>
		</c:if>	
	</div>			
</body>
</html>