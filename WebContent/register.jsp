<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Website Font style -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
 <style>
    body {
        background: #f8f8f8;
        padding: 30px 0;
    }
    
    #registerFrm{
        padding: 25px;
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
                <form class="form-horizontal" action="DispatcherServlet" method="post" name="registerForm" id="registerFrm">
                    <input type="hidden" name="command" value="register">
                    <div class="form-group">
                        <label for="name" class="cols-sm-2 control-label">아이디</label>
                        <div class="cols-sm-5">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fas fa-user"></i></span>
                                <input type="text" class="form-control" name="id" id="id" placeholder="아이디를 입력하세요." required autofocus/>
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

                    <div class="form-group">
                        <label for="confirm" class="cols-sm-2 control-label">비밀번호 확인</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fas fa-lock"></i></span>
                                <input type="password" class="form-control" name="confirm" id="confirm" placeholder="비밀번호를 한번 더 입력하세요." required/>
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
                        <label for="ssn" class="cols-sm-2 control-label">주민등록번호</label>
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
                                <input type="text" class="form-control" name="mail" id="mail" placeholder="이메일 주소를 입력하세요." required/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="tel" class="cols-sm-2 control-label">전화번호</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fas fa-phone"></i></span>
                                <input type="text" class="form-control" name="tel" id="tel" placeholder="전화번호를 입력하세요." required/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group ">
                        <button type="submit" class="btn btn-primary btn-lg btn-block login-button">가입하기</button>
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