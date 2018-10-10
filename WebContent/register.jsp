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
<script type="text/javascript">
		$(function() {
			$('form').submit(function(event) {
				var password = $('#password').val();
				var passcheck = $('#passcheck').val();
				var ssn = $('#ssn').val();
				var tel = $('#tel').val();
				
				if($('input[name=flag]').val() == "false"){
					alert("아이디 중복확인을 눌러주세요.");
					return false;
				}
				if(!(password.length>=6 && password.length<=15)){
					alert("비밀번호는 6글자 이상, 15글자 이하입니다.");
					return false;
				}
				if(password.toUpperCase()==password||password.toLowerCase()==password){
					alert("비밀번호는 대소문자를 혼합해서 사용하세요.");
					return false;
				}
				if(!(ssn.length>=0 && ssn.length<=6)){
					alert("주민등록번호 예)900101");
					return false;
				}
				if(!(tel.length>=0 && tel.length<=11)){
					alert("전화번호 예)01012345678");
					return false;
				}
			});
			$("#tel").keyup(function(){
				$(this).val($(this).val().replace(/[^0-9]/g,""));
				});
			
			$("#ssn").keyup(function(){
				$(this).val($(this).val().replace(/[^0-9]/g,""));
				});
			
			$("#username").keyup(function(){
				$(this).val($(this).val().replace(/[^가-힣a-zA-Z]/g,""));
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
			$('input[type=button]').click(function () {
				if($('#id').val() == "")
					alert("아이디를 입력하고 눌러주세요.");
				else if($('#id').val().indexOf(" ") != -1){
					alert("아이디에 공백은 허용하지 않습니다.");
				}
				/* 공백이 없을 경우(공백이 아닌 경우) : -1 리턴 (window 창 띄우기)
				* 공백이 있는 경우(공백인 경우) : 해당 문자가 있는 자릿 수 리턴(-1 이 아니다.) (공백 안됨 이라고 알람뜸) */
				else {
					window.open("idcheck.do?id=" + $('#id').val(), "Window Title", "width=300, height=200, top=100, left=400");
					window.opener();
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
    #id {
        width: 84.7%;
    }
    .btn-sm {
        padding: 7px 10px;
    }
 </style>
</head>
<body>
 <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="text-center">방귀곡성 회원가입</h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="register.do" method="post" name="registerForm" id="registerFrm">
                    <div class="form-group">
                        <label for="name" class="cols-sm-2 control-label">아이디</label>
                        <div class="cols-sm-5">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fas fa-user"></i></span>
                                <input type="text" class="form-control" name="id" id="id" placeholder="아이디를 입력하세요." required="required"/>&nbsp;
                                <input type="button" class="btn btn-primary btn-sm" value="중복확인">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="cols-sm-2 control-label">비밀번호</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fas fa-lock"></i></span>
                                <input type="password" class="form-control" name="password" id="password" placeholder="비밀번호를 입력하세요." required/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group" id="passcheckform">
                        <label for="confirm" class="cols-sm-2 control-label">비밀번호 확인</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fas fa-lock"></i></span>
                                <input type="password" class="form-control" name="confirm" id="passcheck" placeholder="비밀번호를 한번 더 입력하세요." required/>
                                <span class="glyphicon glyphicon-ok form-control-feedback"></span>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="username" class="cols-sm-2 control-label">이름</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-users"></i></span>
                                <input type="text" class="form-control" name="username" id="username" placeholder="이름을 입력하세요." required/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="ssn" class="cols-sm-2 control-label">주민등록번호(앞자리 6자리)</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fas fa-id-card"></i></span>
                                <input type="text" class="form-control" name="ssn" id="ssn" placeholder="주민등록번호를 입력하세요." required/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="mail" class="cols-sm-2 control-label">이메일</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                <input type="email" class="form-control" name="mail" id="mail" placeholder="이메일 주소를 입력하세요." required/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="tel" class="cols-sm-2 control-label">전화번호</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fas fa-phone"></i></span>
                                <input type="tel" class="form-control" name="tel" id="tel" placeholder="전화번호를 입력하세요." required/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group ">
                        <button type="submit" class="btn btn-primary btn-lg btn-block login-button">가입하기</button>
                        <input type="hidden" name="flag" value="false">
                    </div>

                    <div class="login-register">
                        <span class="pull-right"><a href="login.jsp">로그인</a></span>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>