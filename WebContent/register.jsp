<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>회원가입</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<!-- Website Font style -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
</head>
<script type="text/javascript">
	$(function() {
		$('form').submit(
				function(event) {
					var password = $('#password').val();
					var passwordcheck = $('#passwordcheck').val();
					var ssn = $('#ssn').val();
					var tel = $('#tel').val();

					if ($('input[name=flag]').val() == "false") {
						alert("아이디 중복확인을 눌러주세요.");
						return false;
					}
					if (!(password.length >= 6 && password.length <= 15)) {
						alert("비밀번호는 6글자 이상, 15글자 이하입니다.");
						return false;
					}
					if (password.toUpperCase() == password
							|| password.toLowerCase() == password) {
						alert("비밀번호는 대소문자를 포함해서 사용하세요.");
						return false;
					}
					if (!(ssn.length >= 0 && ssn.length <= 6)) {
						alert("주민등록번호 예)900101");
						return false;
					}
					if (!(tel.length >= 0 && tel.length <= 11)) {
						alert("전화번호 예)01012345678");
						return false;
					}
				});
		$("#tel").keyup(function() {
			$(this).val($(this).val().replace(/[^0-9]/g, ""));
		});

		$("#ssn").keyup(function() {
			$(this).val($(this).val().replace(/[^0-9]/g, ""));
		});

		$("#username").keyup(function() {
			$(this).val($(this).val().replace(/[^가-힣a-zA-Z]/g, ""));
		});

		$(function() {
			$('#passwordcheck').keyup(
					function() {
						if ($('#password').val() != $('#passwordcheck').val()) {
							$('#passwordcheck').removeClass('alert-success')
									.addClass('alert-warning');
						} else {
							$('#passwordcheck').removeClass('alert-warning')
									.addClass('alert-success');
						}
					});
		});

		$('input[type=button]').click(
				function() {
					if ($('#id').val() == "")
						alert("아이디를 입력하고 눌러주세요.");
					else if ($('#id').val().indexOf(" ") != -1) {
						alert("아이디에 공백은 허용하지 않습니다.");
					}
					/* 공백이 없을 경우(공백이 아닌 경우) : -1 리턴 (window 창 띄우기)
					 * 공백이 있는 경우(공백인 경우) : 해당 문자가 있는 자릿 수 리턴(-1 이 아니다.) (공백 안됨 이라고 알람뜸) */
					else {
						window.open("idcheck.do?id=" + $('#id').val(),
								"Window Title",
								"width=300, height=150, top=100, left=400");
						window.opener();
					}
				});
	});
</script>
<style>
.register-block {
	background: #DE6262;
	/* fallback for old browsers */
	background: -webkit-linear-gradient(to bottom, #FFB88C, #DE6262);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to bottom, #FFB88C, #DE6262);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	float: left;
	width: 100%;
	padding: 50px 0;
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

.register-sec {
	padding: 50px 30px;
	position: relative;
}

.register-sec .copy-text {
	position: absolute;
	width: 80%;
	bottom: 15px;
	font-size: 13px;
	text-align: center;
}

.register-sec .copy-text img {
	height: 19px;
}

.register-sec .copy-text i {
	color: #FEB58A;
}

.register-sec .copy-text a {
	color: #E36262;
}

.register-sec h2 {
	margin-bottom: 30px;
	font-weight: 800;
	font-size: 30px;
	color: #DE6262;
}

.register-sec h2:after {
	content: " ";
	width: 100px;
	height: 5px;
	background: #FEB58A;
	display: block;
	margin-top: 20px;
	border-radius: 3px;
	margin-left: auto;
	margin-right: auto
}

.btn-register {
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
	background-size: cover;
	border-radius: 0 10px 10px 0;
	padding: 0;
}

.banner-text p {
	color: #fff;
}

.copy-text {
	margin-bottom: 5px;
}

#id {
	width: 75%;
	display: inline-block;
}
</style>

<body>
	<section class="register-block">
		<div class="container">
			<div class="row">
				<div class="col-md-4 register-sec">
					<h2 class="text-center">회원가입</h2>

					<form class="register-form" action="register.do" method="post"
						name="registerFrm" id="registerFrm">
						<div class="form-group">
							<span class="input-group-addon"><i class="fas fa-user"></i></span>&nbsp;
							<label for="InputId">아이디</label>
							<div>
								<input type="id" class="form-control" name="id" id="id"
									placeholder="아이디를 입력해주세요." required autofocus> <input
									type="button" id="idcheck" class="btn btn-register btn-sm"
									value="중복확인">
							</div>
						</div>
						<div class="form-group">
							<span class="input-group-addon"><i class="fas fa-lock"></i></span>&nbsp;
							<label for="InputPassword">비밀번호(대소문자 포함)</label> <input
								type="password" class="form-control" name="password"
								id="password" placeholder="비밀번호를 입력해주세요." required>
						</div>
						<div class="form-group" id="passwordcheckFrm">
							<span class="input-group-addon"><i class="fas fa-lock"></i></span>&nbsp;
							<label for="InputPasswordCheck">비밀번호 확인</label> <input
								type="password" class="form-control" name="passwordcheck"
								id="passwordcheck" placeholder="비밀번호를 한번 더 입력해주세요." required>
						</div>
						<div class="form-group">
							<span class="input-group-addon"><i class="fa fa-users"></i></span>&nbsp;
							<label for="InputUsername">이름</label> <input type="name"
								class="form-control" name="username" id="username"
								placeholder="이름을 입력해주세요." required>
						</div>
						<div class="form-group">
							<span class="input-group-addon"><i class="fas fa-id-card"></i></span>&nbsp;
							<label for="InputSsn">주민등록번호(앞자리 6자리)</label> <input type="ssn"
								class="form-control" name="ssn" id="ssn"
								placeholder="주민등록번호를 입력해주세요." required>
						</div>
						<div class="form-group">
							<span class="input-group-addon"><i class="fa fa-envelope"></i></span>&nbsp;
							<label for="InputEmail">이메일</label> <input type="email"
								class="form-control" name="mail" id="mail"
								placeholder="이메일 주소를 입력해주세요." required>
						</div>
						<div class="form-group">
							<span class="input-group-addon"><i class="fas fa-phone"></i></span>&nbsp;
							<label for="InputTel">전화번호</label> <input type="tel"
								class="form-control" name="tel" id="tel"
								placeholder="전화번호를 입력해주세요." required>
						</div>

						<div class="form-check">
							<button type="submit" class="btn btn-register float-right">등록</button>
							<input type="hidden" name="flag" value="false">
						</div>
						<br> <br> <br>
					</form>

					<div class="copy-text">
						<span class="float-left"><a href="login.jsp">로그인</a></span><br>
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
								<img class="d-block img-fluid" src="img/registerimg1.jpg"
									alt="First slide">
								<div class="carousel-caption d-none d-md-block">
									<div class="banner-text">
										<h2>돌하르방</h2>
										<p>툭 튀어나온 동그란 두 눈, 굳게 다문 입, 벙거지 같은 모자를 쓰고 있는 머리, 구부정한 자세에
											한쪽 어깨는 치켜올리고 굳게 움켜쥔 두 손으로는 배를 감싸안고 있는 제주의 돌하르방 구멍이 숭숭한 현무암으로
											만들어진 터라 생김새만큼이나 질감도 독특한 돌하르방은 제주도의 상징이자 간판 얼굴로 자리잡고 있다 ‘돌로 만든
											할아버지’라는 뜻의 돌하르방은 오래전부터 아이들 입에서 입으로 전해지던 말이었는데 1971년 제주민속자료
											제2호로 지정되면서부터 정식 명칭으로 굳어졌다</p>
									</div>
								</div>
							</div>
							<div class="carousel-item">
								<img class="d-block img-fluid" src="img/registerimg2.jpg"
									alt="Second slide">
								<div class="carousel-caption d-none d-md-block">
									<div class="banner-text">
										<h2>대전 엑스포</h2>
										<p>대전직할시(현 대전광역시) 유성구 대덕연구단지에서 열린 엑스포 주제는 "새로운 도약으로의 길"이고,
											부제는 "전통기술과 현대과학의 조화"와 "자원의 효율적 이용과 재활용"이다</p>
									</div>
								</div>
							</div>
							<div class="carousel-item">
								<img class="d-block img-fluid" src="img/registerimg3.jpg"
									alt="Thrid slide">
								<div class="carousel-caption d-none d-md-block">
									<div class="banner-text">
										<h2>해미읍성</h2>
										<p>서산 해미읍성은 국내에서도 그 원형이 가장 완벽하게 보존된 평성으로 대표적인 국가 사적이다
											조선시대에 만들어져 600여 년의 역사 속에서 찬란한 문화를 꽃피웠던 곳이다 또한, 호서지방의 심장부로
											충청도를 관장하던 병마절도사가 있었고, 충무공 이순신 장군께서도 근무하셨던 역사적인 장소이다 1천여 명의
											천주교인이 믿음으로 죽음을 극복한 전국 최대의 순교성지로, 동학혁명과 천주교 박해 등 격동의 근대사를 간직한
											곳이기도 하다</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>