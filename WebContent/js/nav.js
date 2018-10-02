$(function() {
   		//================================ menu ================================
   		
   		 $('#myNavbar>ul li').click(function() {
    		var scrollPosition = $($(this).attr('data-target')).offset().top;
    		$('body, html').animate({
    			scrollTop: scrollPosition
    		}, 500); //animate
		}); //click

      	$('#menuSpan .icon-bar').css('background', 'green');
      
      	$('#myNavbar li a').css({
         	'color' : 'black',
         	'font-weight' : 'bold'
      	}); //css
      
      	$('#myNavbar li a').hover(function() { 
         	//상단 메뉴바 마우스 올려놨을 때
           	$(this).css({
              	'color' : 'green',
              	'background' : 'rgba(242, 242, 242, 0.5)'
           	}); //css
         
      	}, function() {
         	$(this).css({
              	'color' : 'black',
              	'background' : 'white'
           	}); //css
      	}); //hover
      
      	$('.dropdown-menu').css({
         	'margin-top' : '9px',
         	'min-width' : '12px',
         	'border-radius': '2px'
      	}); //css
   	}); //ready