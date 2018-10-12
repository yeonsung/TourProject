<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>로그인</title>
</head>
<style>
    body {
        background: #f8f8f8;
        padding: 60px 0;
    }
    #login-form > div {
        margin: 15px 0;
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
	<div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="text-center">방방곡곡 로그인</h2>
            </div>
            <div class="modal-body">
                <form action="login.do" method="post" name="loginForm" id="loginFrm">
                    <input type="hidden" name="command" value="login">
                    <div class="form-group">
                        <input type="text" class="form-control input-lg" name="id" placeholder="아이디" required autofocus/>
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control input-lg" name="password" placeholder="비밀번호" />
                    </div>

                    <div class="form-group">
                        <input type="submit" class="btn btn-block btn-lg btn-primary" value="로그인" />
                        <br>
                        <span class="pull-left"><a href="findidpass.jsp">아이디 및 비밀번호 찾기</a></span>
                        <span class="pull-right"><a href="register.jsp">회원가입</a></span>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>