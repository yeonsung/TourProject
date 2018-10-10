<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Website Font style -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
<link rel="stylesheet" href="css/nav.css">
<script type="text/javascript">
$(function() {
	$('form').submit(function(event) {
		var password = $('#password').val();
		var passcheck = $('#passcheck').val();

		if(!(password.length>=6 && password.length<=15)){
			alert("비밀번호는 6글자 이상, 15글자 이하입니다.");
			return false;
		}
		if(password.toUpperCase()==password||password.toLowerCase()==password){
			alert("비밀번호는 대소문자를 혼합해서 사용하세요.");
			return false;
		}
	});
	 $(function(){
		  $('#passcheck').keyup(function(){
		   if($('#password').val()!=$('#passcheck').val()){
		    $('#passcheckform').removeClass('has-success').addClass('has-error').addClass('has-feedback');
		   }else{
			$('#passcheckform').removeClass('has-error').addClass('has-success').addClass('has-feedback')
			.addClass('glyphicon-ok');
		   }
		 }); 
	});
</script>
 <style>
    body {
        background: #f8f8f8;
        padding: 30px 0;
    }
    
    #registerFrm {
        padding: 25px;
    }
    
    .btn-sm {
        padding: 7px 10px;
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
	<div class="modal-dialog" style="margin-top:100px">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="text-center">회원정보 수정</h2>
            </div>
            <div class="modal-body">
            <c:choose>
			<c:when test="${sessionScope.vo != null}">
                <form class="form-horizontal" action="registerupdate.do" method="post" name="registerForm" id="registerFrm">
                    <div class="form-group">
                        <label for="name" class="cols-sm-2 control-label">아이디</label>
                        <div class="cols-sm-5">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fas fa-user"></i></span>
                                <input type="text" class="form-control" name="id" id="id" value="${sessionScope.vo.id}" readonly/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="cols-sm-2 control-label">비밀번호</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fas fa-lock"></i></span>
                                <input type="password" class="form-control" name="password" id="password" value="${sessionScope.vo.password}"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group" id="passcheckform">
                        <label for="confirm" class="cols-sm-2 control-label">비밀번호 확인</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fas fa-lock"></i></span>
                                <input type="password" class="form-control" name="confirm" id="passcheck" placeholder="비밀번호를 한번 더 입력하세요." />
                                <span class="glyphicon glyphicon-ok form-control-feedback"></span>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="username" class="cols-sm-2 control-label">이름</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-users"></i></span>
                                <input type="text" class="form-control" name="username" id="username" value="${sessionScope.vo.userName}" readonly/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="ssn" class="cols-sm-2 control-label">주민등록번호</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fas fa-id-card"></i></span>
                                <input type="text" class="form-control" name="ssn" id="ssn" readonly/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="mail" class="cols-sm-2 control-label">이메일</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                <input type="email" class="form-control" name="mail" id="mail" value="${sessionScope.vo.mail}"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="tel" class="cols-sm-2 control-label">전화번호</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fas fa-phone"></i></span>
                                <input type="tel" class="form-control" name="tel" id="tel" value="${sessionScope.vo.tel}"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group ">
                        <button type="submit" class="btn btn-primary btn-lg btn-block login-button">수정하기</button>
                        <input type="hidden" name="flag" value="false">
                    </div>

               	</form>
                	</c:when>
					<c:otherwise>
					<script type="text/javascript">
						alert("로그인 후 이용가능합니다.");
						location.href = "login.jsp";
					</script>
					</c:otherwise>
				</c:choose>
            </div>
        </div>
    </div>
</body>

</html>