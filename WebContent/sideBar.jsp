<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<title>W3.CSS</title>
<style>
#img :hover{
  opacity: 0.3;
}
#img{
width: 250px;
height: 182px;


}
.hashtag:before {

content:"#";


  
}

</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>
<div class="w3-bar w3-black">
  <a href="#" class="w3-bar-item w3-button w3-mobile">여행</a>
  <a href="#" class="w3-bar-item w3-button w3-mobile">풍경</a>
  <a href="#" class="w3-bar-item w3-button w3-mobile">사진</a>
  <a href="#" class="w3-bar-item w3-button w3-mobile">자연</a>
</div>
<div class="w3-sidebar w3-bar-block w3-large" style="width:20%">
  <a href="#" class="w3-bar-item w3-button w3-hover-none w3-hover-text-grey" ><img src ="https://static1.squarespace.com/static/554b5e7ce4b0149371f10a93/59770012b8a79bc2cb19286c/5977002d5016e17b22b38779/1502727891080/demand-and-supply-Erik-Johansson.jpg?format=750w" id="img"><br><b class="hashtag">가평</b> <b class="hashtag">빠지</b></a>
  <a href="#" class="w3-bar-item w3-button w3-hover-none w3-hover-text-grey" ><img src="https://static1.squarespace.com/static/554b5e7ce4b0149371f10a93/59770012b8a79bc2cb19286c/597703b95016e17b22b39f3d/1502724880750/full-moon-service-Erik-Johansson.jpg?format=500w" id="img"><br><b class="hashtag">기장</b> <b class="hashtag">카페</b></a>
  <a href="#" class="w3-bar-item w3-button w3-hover-none w3-hover-text-grey" ><img src="https://static1.squarespace.com/static/554b5e7ce4b0149371f10a93/59770012b8a79bc2cb19286c/59770066d7bdcef00d1f9db3/1502870327418/landfall-Erik-Johansson.jpg?format=750w" id="img"><br><b class="hashtag">기장</b> <b class="hashtag">카페</b></a>
	
</div>

<div style="margin-left:20%">

<div class="w3-container w3-dark-grey">
  <h1>My Page</h1>
</div>

<div class="w3-container">
<p>When you mouse over the links inside a sidebar, the background color will change to grey.</p>
<p>You can turn off the hover effect with the w3-hover-none class</p>
<p>And you can use the w3-hover-text-color if you only to highlight the text color on hover.</p>
</div>

</div>
      
</body>
</html>
