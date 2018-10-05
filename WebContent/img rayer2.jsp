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

body { font-family: sans-serif; }

/* ---- grid ---- */

.grid {
  background: #DDD;
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

}
</style>
<head>
</head>
<body>
<h1>Masonry - imagesLoaded progress</h1>

<div class="grid">
  <div class="grid-sizer"></div>
  
  <div class="grid-item">
  <a href=#>
    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/82/orange-tree.jpg" />
  </a>
  </div>

  <div class="grid-item">
  <a href=#>
    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/82/submerged.jpg" />
    </a>
  </div>
  <div class="grid-item">
  <a href=#>
    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/82/look-out.jpg" />
    </a>
  </div>
  <div class="grid-item">
  <a href=#>
    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/82/one-world-trade.jpg" />
    </a>
  </div>
  <div class="grid-item">
  <a href=#>
    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/82/drizzle.jpg" />
    </a>
  </div>
  <div class="grid-item">
  <a href=#>
    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/82/cat-nose.jpg" />
    </a>
  </div>
  <div class="grid-item">
  <a href=#>
    <img src="https://static1.squarespace.com/static/554b5e7ce4b0149371f10a93/59775c56f9a61e6bb7e5db1f/59775cacd1758e6e542b9ff5/1502706803981/performance.jpg?format=500w" />
  </a>
  </div>
  <div class="grid-item">
  <a href=#>
    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/82/contrail.jpg" />
  </a>
  </div>
  <div class="grid-item">
  <a href=#>
    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/82/golden-hour.jpg" />
  </a>
  </div>
  <div class="grid-item">
  <a href=#>
    <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/82/flight-formation.jpg" />
 </a>
  </div>
  
  <div class="grid-item">
  <a href=#>
    <img src="https://static1.squarespace.com/static/554b5e7ce4b0149371f10a93/59775c56f9a61e6bb7e5db1f/59775cfdd482e9a886609de9/1502706746778/clienia+premium.jpg?format=500w" />
  </a>
  </div>
  
    
</div>
</body>
</html>
