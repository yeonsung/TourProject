<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/nav.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
            		<a href="index.jsp"><img src="img/main_logo2.png" width="150"></a>
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

   	<div style="height: 150px"></div>
   
	<h1 align="center">Scrap</h1><br><br>
	
	<c:forEach items="${lvo.list}" var="rList">
		
			<div align="center" class="col-sm-4">
				<hr>
				${rList.date}<br>
				<a href="checkReview.do?num=${rList.reviewNum}"><img src="${rList.mainImage}" width="350" height="200"></a><br><br>
				<a href="checkReview.do?num=${rList.reviewNum}">${rList.title}</a>&nbsp;&nbsp;
				<a href="deleteScrap.do?reviewNum=${rList.reviewNum}&&id=${rList.id}"><input type="button" value="삭제"></a>
				<hr><br><br>
			</div>
		
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