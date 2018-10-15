<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<title>로그인</title>
</head>
<style>
body {
	background: #DE6262;
	/* fallback for old browsers */
	background: -webkit-linear-gradient(to bottom, #FFB88C, #DE6262);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to bottom, #FFB88C, #DE6262);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	float: left;
	width: 100%;
	height: 937px;
	padding: 160px 0;
	background: linear-gradient(to bottom, #FFB88C, #DE6262);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	float: left;
	width: 100%;
	height: 937px;
	float: left;
	width: 100%;
	height: 937px;
	width: 100%;
	height: 937px;
	height: 937px;
}

.container {
	background: #fff;
	border-radius: 10px;
	box-shadow: 15px 20px 0px rgba(0, 0, 0, 0.1);
}

.carousel-inner {
	border-radius: 0 10px 10px 0;
}

.carousel-caption {
	text-align: left;
	left: 5%;
}

.login-sec {
	padding: 50px 30px;
	position: relative;
}

.login-sec .copy-text {
	position: absolute;
	width: 80%;
	bottom: 20px;
	font-size: 13px;
	text-align: center;
}

.login-sec .copy-text span a {
	font-size: 13px;
}

.login-sec .copy-text img {
	height: 20px;
}

.login-sec .copy-text i {
	color: #FEB58A;
}

.login-sec .copy-text a {
	color: #E36262;
}

.login-sec h2 {
	margin-bottom: 30px;
	font-weight: 800;
	font-size: 30px;
	color: #DE6262;
}

.login-sec h2:after {
	content: " ";
	width: 100px;
	height: 5px;
	background: #FEB58A;
	display: block;
	margin-top: 20px;
	border-radius: 3px;
	margin-left: auto;
	margin-right: auto;
}

.btn-login {
	background: #DE6262;
	color: #fff;
	font-weight: 600;
}

.banner-text {
	width: 70%;
	position: absolute;
	bottom: 40px;
	padding-left: 20px;
}

.banner-text h2 {
	color: #fff;
	font-weight: 600;
}

.banner-text h2:after {
	content: " ";
	width: 100px;
	height: 5px;
	background: #FFF;
	display: block;
	margin-top: 20px;
	border-radius: 3px;
}

.banner-sec {
	padding-right: 0px;
}

.banner-text p {
	color: #fff;
}

.copy-text {
	margin-bottom: 5px;
}
</style>
<script type="text/javascript">
	
</script>
<body>
	<c:if test="${check==false}">
		<script type="text/javascript">
			alert("로그인 실패");
		</script>
	</c:if>
	<div class="login-block">
		<div class="container">
			<div class="row">
				<div class="col-md-4 login-sec">
					<h2 class="text-center">로그인</h2>

					<form class="login-form" action="login.do" method="post"
						name="loginFrm" id="loginFrm">
						<input type="hidden" name="command" value="login">
						<div class="form-group">
							<label for="InputId">아이디</label> <input type="text"
								class="form-control" name="id" placeholder="아이디를 입력해주세요."
								required autofocus>

						</div>
						<div class="form-group">
							<label for="InputPassword">비밀번호</label> <input
								type="password" class="form-control" name="password"
								placeholder="비밀번호를 입력해주세요." required>
						</div>

						<div class="form-check">
							<label class="form-check-label"> <input type="checkbox"
								class="form-check-input"> <small>로그인 상태 유지</small>
							</label>
							<button type="submit" class="btn btn-login float-right">로그인</button>
						</div>
					</form>

					<div class="copy-text">
						<span class="float-left"><a href="findidpass.jsp">아이디 및
								비밀번호 찾기</a></span> <span class="float-right"><a href="register.jsp">회원가입</a></span><br>
						<br> inspired by &nbsp;<img src="img/inspired.png">
					</div>

				</div>
				<div class="col-md-8 banner-sec">
					<div id="carouselExampleIndicators" class="carousel slide"
						data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#carouselExampleIndicators" data-slide-to="0"
								class="active"></li>
							<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
							<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
						</ol>
						<div class="carousel-inner" role="listbox">
							<div class="carousel-item active">
								<img class="d-block img-fluid" src="img/loginimg1.jpg"
									alt="First slide">
								<div class="carousel-caption d-none d-md-block">
									<div class="banner-text">
										<h2>불국사</h2>
										<p>
											<야심찬 통일신라의 꿈을 드러내는 건축물> <br>
											5천년 장구한 세월동안 발전해온 우리 민족문화의<br>
											정수로 천년세월 너머 현대의 무지한 중생들에게<br>
											불국토의 장엄함과 사모하는 마음을 일으키는 불국사 
										</p>
									</div>
								</div>
							</div>
							<div class="carousel-item">
								<img class="d-block img-fluid" src="img/loginimg2.jpg"
									alt="Second slide">
								<div class="carousel-caption d-none d-md-block">
									<div class="banner-text">
										<h2>설악산</h2>
										<p>
											<태백산맥에 있는 강원도의 명산> <br>
											천의 얼굴을 가진 설악산은 계절마다 다양한 색깔로<br>
											바꿔가며 아름다운 경치를 보여주는 눈과 바위의<br>
											산으로 남한 제일의 명산이다 
										</p>
									</div>
								</div>
							</div>
							<div class="carousel-item">
								<img class="d-block img-fluid" src="img/loginimg3.jpg"
									alt="Thrid slide">
								<div class="carousel-caption d-none d-md-block">
									<div class="banner-text">
										<h2>서울 - 한강</h2>
										<p>
											<수도권의 젖줄> <br>
											한반도 중부 지방을 동에서 서로 관통해<br>
											황해로 유입되는 한국의 주요 하천 
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>