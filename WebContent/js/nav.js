
	$(function() {
		//================================ menu ================================

		/*$('#myNavbar>ul li').click(function() {
			var scrollPosition = $($(this).attr('data-target')).offset().top;
			$('body, html').animate({
				scrollTop : scrollPosition
			}, 500); //animate
		}); //click
*/
		$('#menuSpan .icon-bar').css('background', 'white');

		$('#myNavbar li a').css({
			'color' : 'black',
			'font-weight' : 'bold'
		}); //css

		$('#myNavbar li a').hover(function() {
			//���� 硫��대� 留��곗�� �щ�ㅻ�⑥�� ��
			$(this).css({
				'color' : 'black'/* 'rgb(245, 238, 210)' */,/* rgb(15, 106, 139) */
				'background' : 'rgba(244, 236, 200, 0.5)'/* 'rgba(242, 242, 242, 0.5)' */
			}); //css

		}, function() {
			$(this).css({
				'color' : 'black',
				'background' : 'white'
			}); //css
			$('#userMenu').css({
				'color' : 'black',
				'background' : '#FFFAE5'/* 'rgb(245, 238, 210)' *//* 'rgb(15, 106, 139)' */
			}); //css
		}); //hover

		$('.dropdown-menu').css({
			'margin-top' : '9px',
			'min-width' : '12px',
			'border-radius' : '2px'
		}); //css
	}); //ready