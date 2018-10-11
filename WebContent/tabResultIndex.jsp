<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
	a, a:hover{text-decoration: none}
	td{
		text-align: center;
		
	}
/* 	tr{
		border: 1px #CCCC99 double;
	} */
</style>
<script type="text/javascript">
$(function(){
	$('#states text').hover(function(){
				var labelid = $(this).attr('id').replace('label-','');
				$('.path-'+labelid).css('fill','#FFFAE5');
			},function(){
				var labelid = $(this).attr('id').replace('label-','');
				$('.path-'+labelid).css('fill','#f0ebe0');

			});
	$('#show').click(function(){
		var count = "${count}";
		$.ajax({

			type:"get",
			url:"getRecentReviews.do",
			data :"pageNo="+count,
			
			success:function(data){
				$('.haha').html(data);	
				count++;
			}//callback
		});//ajax	
	});
	
	$('table tr').hover(function(){
		var id = $(this).attr('id');
		$('.path-'+id).css('fill','#FFFAE5');
	},function(){
		var id = $(this).attr('id');
		$('.path-'+id).css('fill','#f0ebe0');
	});
})
</script>
</head>
<body>
	<div>
		<table align="center" width="600">
			<c:forEach var="vo" items="${rlist.list}" step="1">
				<tr style="" height="100" id="${vo.location}">
					<td style="font-size: 20px;padding:10px" >${vo.location} ${vo.city}</td>
				
					<td style="font-size: 20px;"><b><a href="checkReview.do?num=${vo.reviewNum}">${vo.title}</a></b></td>
				</tr>
			</c:forEach>
			<tr style="border:none;">
				<td colspan="2" align="center"><h2 style="cursor: pointer;" id="show">더보기</h2></td>
			</tr>
		</table>
	</div>
</body>
</html>