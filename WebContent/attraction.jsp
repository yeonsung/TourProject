<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.js"></script>
<script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>
<script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="./masonry.pkgd.js"></script>
<script type="text/javascript">
$(function(){
	$('.grid').masonry({
		  // options
		  itemSelector: '.grid-item',
		  columnWidth: 160
		})
		var $grid = $('.grid').masonry({
			  itemSelector: '.grid-item',
			  percentPosition: true,
			  columnWidth: '.grid-sizer'
			});
			// layout Masonry after each image loads
			$grid.imagesLoaded().progress( function() {
			  $grid.masonry();
			});  
	});

</script>




<style>
{ box-sizing: border-box; }

/* force scrollbar */
html { overflow-y: scroll; }
s
body { font-family: sans-serif; }

/* ---- grid ---- */

.grid {
  background: #fff;
}

/* clear fix */
.grid:after {
  content: '';
  display: block;
  clear: both;
}

/* ---- .grid-item ---- */

.grid-sizer,
.grid-item {
  width: 20%;
  margin : 2%;
  
}

.grid-item {
  float: left;
}

.grid-item img {
  display: block;
  max-width: 100%;
}
.grid-item :hover{
  opacity: 0.3;
}

/*=================================================  */
.main {
text-align:center ;
}

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


#content{
text-align:center;
}




</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<body>
<div class="w3-bar w3-black">
  <a href="#" class="w3-bar-item w3-button w3-mobile">여행</a>
  <a href="#" class="w3-bar-item w3-button w3-mobile">풍경</a>
  <a href="#" class="w3-bar-item w3-button w3-mobile">사진</a>
  <a href="#" class="w3-bar-item w3-button w3-mobile">자연</a>
</div>

<div class="w3-sidebar w3-bar-block w3-large" style="width:20%">
  <a href="checkReview.do?reviewNum=3" class="w3-bar-item w3-button w3-hover-none w3-hover-text-grey" id="content"><img src ="https://static1.squarespace.com/static/554b5e7ce4b0149371f10a93/59770012b8a79bc2cb19286c/5977002d5016e17b22b38779/1502727891080/demand-and-supply-Erik-Johansson.jpg?format=750w" id="img"><br><b class="hashtag">가평</b> <b class="hashtag">빠지</b></a>
  <a href="#" class="w3-bar-item w3-button w3-hover-none w3-hover-text-grey" id="content"><img src="https://static1.squarespace.com/static/554b5e7ce4b0149371f10a93/59770012b8a79bc2cb19286c/597703b95016e17b22b39f3d/1502724880750/full-moon-service-Erik-Johansson.jpg?format=500w" id="img"><br><b class="hashtag">기장</b> <b class="hashtag">카페</b></a>
  <a href="#" class="w3-bar-item w3-button w3-hover-none w3-hover-text-grey" id="content"><img src="https://static1.squarespace.com/static/554b5e7ce4b0149371f10a93/59770012b8a79bc2cb19286c/59770066d7bdcef00d1f9db3/1502870327418/landfall-Erik-Johansson.jpg?format=750w" id="img"><br><b class="hashtag">대구</b> <b class="hashtag">이월드</b></a>
  <a href="#" class="w3-bar-item w3-button w3-hover-none w3-hover-text-grey" id="content"><img src="https://static1.squarespace.com/static/554b5e7ce4b0149371f10a93/59770012b8a79bc2cb19286c/597700989f74560d7f522702/1502870377328/breaking-up-Erik-Johansson.jpg?format=500w" id="img"><br><b class="hashtag">전주</b> <b class="hashtag">한옥마을</b></a>
</div>

<div style="margin-left:20%">

<div class="v3-container w3-dark-grey">
 <h1 class="main">${param.city}</h1>
</div>

 
  
 
 <div class="grid">
 <div class="grid-sizer">
  <c:forEach items="${avo}" var ="avo">
<%--  <c:forEach items="${avo.images}" var ="vo">
 --%>	<div class="grid-item">
		<a href=#>

		<img src="${avo.mainImage}"/>
		</a>
	</div>
<%-- </c:forEach> --%>
</c:forEach>
</div>
 </div> 


  
</div>
</body>
</html>