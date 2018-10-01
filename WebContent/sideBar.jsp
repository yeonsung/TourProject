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



</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<body>

<div class="w3-sidebar w3-bar-block w3-large" style="width:25%">
  <a href="#" class="w3-bar-item w3-button">여행&nbsp;&nbsp;풍경&nbsp;&nbsp;사진&nbsp;&nbsp;자연</a>
  <a href="#" class="w3-bar-item w3-button w3-hover-none" id="img"><img src ="https://static1.squarespace.com/static/554b5e7ce4b0149371f10a93/59770012b8a79bc2cb19286c/5b616e6b1ae6cf9b7ccb8212/1533894013773/EXPECTATIONS_FINAL.jpg?format=500w"><b>#가평 #빠지</b></a>
  <a href="#" class="w3-bar-item w3-button w3-hover-none w3-hover-text-red">Red</a>
  <a href="#" class="w3-bar-item w3-button w3-hover-none w3-hover-text-grey">Grey</a>
  <a href="#" class="w3-bar-item w3-button w3-hover-none w3-hover-text-green">Green</a>
  <a href="#" class="w3-bar-item w3-button w3-hover-none w3-hover-text-blue">Blue</a>
</div>

<div style="margin-left:25%">

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
