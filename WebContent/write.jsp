<%@page import="java.util.ArrayList"%>
<%@page import="model.vo.MemberVO"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <%
  	String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>	 --%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>Insert title here</title>
<style>
::-webkit-scrollbar {
	width: 10px;
}
::-webkit-scrollbar-track {
	background: #EAEAEA;
	border-radius: 5px;
}
::-webkit-scrollbar-thumb {
	background: #D3D3D3;
	border-radius: 5px;
}
::-webkit-scrollbar-thumb:hover {
	background: #ADADAD;
}
.hideme {
	opacity: 0;
}
</style>
	<link rel="stylesheet" href="css/nav.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/smarteditor/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js" charset="utf-8"></script>
<%-- <script type="text/javascript" src="<%=ctx %>/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script> --%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="js/nav.js"></script>
</head>

<body style="background-color: rgba(249, 248, 244, 0.5)">	
<script type="text/javascript">
/* jQuery('#selectBox').change(function() {
	var state = jQuery('#selectBox option:selected').val();
	if(state == '1') {
		jQuery('#layer').show();
	} else {
		jQuery('#layer').hide();
	}
});	 */
/* $('frm').submit(function(){
	 var msg = document.getElementById('smarteditor').value; 
	alert(msg);
	$('frm').submit();}
);  */
function categoryChange(e) {
	  var location_a = ["강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "서동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"];
	  var location_b = ["강화군", "계양구", "남동구", "동구", "미추홀구", "부평구", "서구", "연수구", "옹진군", "중구"];
	  var location_c = ["대덕구", "동구", "서구", "유성구", "중구"];
	  var location_d = ["남구", "달서구", "달성군", "동구", "북구", "서구", "수성구", "중구"];
	  var location_e = ["광산구", "남구", "동구", "북구", "서구"];
	  var location_f = ["남구", "동구", "북구", "울주군", "중구"];
	  var location_g = ["강서구", "금정구", "기장군", "남구", "동구", "동래구", "부산진구", "북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구", "중구", "해운대구"];
	  var location_h = ["가평군", "고양시", "과천시", "광명시", "광주시", "구리시", "군포시", "김포시", "남양주시", "동두천시", "부천시", "성남시", "수원시", "시흥시", "안산시", "안성시", "안양시", "양주시", "양평군", "여주시", "연천군", "오산시", "용인시", "의왕시", "의정부시", "이천시", "파주시", "평택시", "포천시", "하남시", "화성시"];
	  var location_i = ["강릉시", "고성군", "동해시", "삼척시", "속초시", "양구군", "양양군", "영월군", "원주시", "인제군", "정선군", "철원군", "춘천시", "태백시", "평창군", "흥청군", "화천군", "횡성군"];
	  var location_j = ["세종"];
	  var location_k = ["괴산군", "단양군", "보은군", "영동군", "옥천군", "음성군", "제천시", "증평군", "진천군", "청주시", "충주시"];
	  var location_l = ["계룡시", "공주시", "금산군", "논산시", "당진시", "보령시", "부여군", "서산시", "서천군", "아산시", "연기군", "예산군", "천안시", "청양군", "태안군", "홍성군"];
	  var location_m = ["고창군", "군산시", "김제시", "남원시", "무주군", "부안군", "순창군", "완주군", "익산시", "임실군", "장수군", "전주시", "정읍시", "진안군"];
	  var location_n = ["강진군", "고흥군", "곡성군", "광양시", "구례군", "나주시", "담양군", "목포시", "무안군", "보성군", "순천시", "신안군", "여수시", "영광군", "영암군", "완도군", "장성군", "장흥군", "진도군", "함평군", "해남군", "화순군"];
	  var location_o = ["경산시", "경주시", "고령군", "구미시", "군위군", "김천시", "문경시", "봉화군", "상주시", "성주군", "안동시", "영덕군", "영양군", "영주시", "영천시", "예천군", "울릉군", "울진군", "의성군", "청도군", "청송군", "칠곡군", "포항시"];
	  var location_p = ["거제시", "거창군", "고성군", "김해시", "남해군", "밀양시", "사천시", "산청군", "양산시", "의령군", "진주시", "창녕군", "창원시", "통영시", "하동군", "함안군", "함양군", "합천군"];
	  var location_q = ["서귀포시", "제주시"];
	  var location_r = ["광역시/도를 먼저 선택해주세요."];
	  var target = document.getElementById("location2");
	 
	  if(e.value == "서울") var d = location_a;
	  else if(e.value == "인천") var d = location_b;
	  else if(e.value == "대전") var d = location_c;
	  else if(e.value == "대구") var d = location_d;
	  else if(e.value == "광주") var d = location_e;
	  else if(e.value == "울산") var d = location_f;
	  else if(e.value == "부산") var d = location_g;
	  else if(e.value == "경기도") var d = location_h;
	  else if(e.value == "강원도") var d = location_i;
	  else if(e.value == "10") var d = location_j;
	  else if(e.value == "충청북도") var d = location_k;
	  else if(e.value == "충청남도") var d = location_l;
	  else if(e.value == "전라북도") var d = location_m;
	  else if(e.value == "전라남도") var d = location_n;
	  else if(e.value == "경상북도") var d = location_o;
	  else if(e.value == "경상남도") var d = location_p;
	  else if(e.value == "제주도") var d = location_q;
	  else if(e.value == "0") var d = location_r;
	  target.options.length = 0;
	 
	  for (x in d) {
	    var opt = document.createElement("option");
	    opt.value = d[x]+"";
	    opt.innerHTML = d[x];
	    target.appendChild(opt);
	  } 
	  
	 /* function settleFormCheck(){
		 var f	rm = document.settleForm;
	     
	     frm.action = “write.do”;
	     return true;
	} */
}
	
var oEditors = [];
$(function(){
		$('#myNavbar>ul li').click(function() {
			var scrollPosition = $($(this).attr('data-target')).offset().top;
			$('body, html').animate({
				scrollTop : scrollPosition
			}, 500); //animate
		}); //click
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "smarteditor", //textarea에서 지정한 id와 일치해야 합니다. 
          //SmartEditor2Skin.html 파일이 존재하는 경로
          sSkinURI: "smarteditor/SmartEditor2Skin.html",  
          htParams : {
              // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseToolbar : true,             
              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseVerticalResizer : true,     
              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
              bUseModeChanger : true,         
              fOnBeforeUnload : function(){
                   
              }
          }, 
          fOnAppLoad : function(){
              //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
              oEditors.getById["smarteditor"].exec("PASTE_HTML", ["여기에 내용을 써주세요."]);
          },
          fCreator: "createSEditor2"
      });
     
     /*  $("#savebutton").click(function(){
          //id가 smarteditor인 textarea에 에디터에서 대입
          editor_object.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);
           
          // 이부분에 에디터 validation 검증
           
          //폼 submit
          $("#frm").submit();
      }); */
   
      //저장버튼 클릭시 form 전송
      $("#savebutton").click(function(){
          oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);
/*           var b = $("#imageupload").find('img').attr('src');*/
			for(i=1; i<=count;i++){
				var b = $('#img'+i+'').attr('src');
				$('form').append("<input type='hidden' name='img"+i+"' value='"+b+"'>")
          	}
		  $('form').append("<input type='hidden' name='count' value='"+count+"'>")
          $('form').submit();
      });      
})
var count = 0;
function pasteHTML(filepath){
	var id = '${sessionScope.vo.id}';
	count++;
	var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+id+'/'+filepath+'" width="265px" id="img'+count+'"><br>';
	console.log(sHTML);
    <%-- var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+filepath+'">'; --%>
   <%--  var sHTML = '<img src="<%= %>C:/kyj/webpro2/eclipse/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/TourProject/upload/'+id+'/'+filepath+'">'; --%>
   $('#imageupload').append(sHTML);
 	
   /* oEditors.getById["smarteditors"].exec("PASTE_HTML", [sHTML]); */
}
</script>	
<nav class="navbar navbar-defalt navbar-fixed-top" style="background-color: #fff">
      	<div id="header"> 
      		<div class="container">
         		<div class="navbar-header" style="margin-top: 15px">
            		<button type="button" class="navbar-toggle" id="menuSpan" data-toggle="collapse" data-target="#myNavbar">
	                    <span class="icon-bar"></span>
	               		<span class="icon-bar" style="margin-top: 2px"></span>
	               		<span class="icon-bar"></span>
            		</button>
            		<a href="index.jsp"><img src="img/main_logo2.png" width="150" height="47" style="background-color: #FFFAE5/* #0F6A8B  #F5EED2*/"></a>
         		</div> <!-- navbar-header -->
         		
	         	<div class="collapse navbar-collapse navbar-right" id="myNavbar" style="margin-top: 15px">
	            	<form class="navbar-form navbar-left" action="getdata.do">
	               		<div class="input-group">
	                  		<input type="text" class="form-control" placeholder="Search" name="search" id="myInput">
	                  		<div class="input-group-btn">
	                     		<button class="btn btn-default" type="submit">
	                        		<i class="glyphicon glyphicon-search"></i>
	                     		</button>
	                  		</div>
	               		</div>
	            	</form>
	            	
	            	<ul class="nav navbar-nav navbar-right">
	               		<li class="dropdown">
		                  	<a class="dropdown-toggle" data-toggle="dropdown" href="#" id="userMenu">
		                     	<span class="glyphicon glyphicon-user white">
		                     		<span class="caret" style="margin-left: 10px"></span>
		                     	</span>
		                  	</a>
		                  	<c:choose>
		                  	 	<c:when test="${vo != null}">
			                  	 	<ul class="dropdown-menu">
			                     	<li><a href="logout.do"><span class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;로그아웃</a></li>
			                     	<li><a href="myreviews.do?id=${sessionScope.vo.id}"><span class="glyphicon glyphicon-edit"></span>&nbsp;&nbsp;내가 쓴 글</a></li>
			                     	<li><a href="scrap.do?id=${sessionScope.vo.id}"><span class="glyphicon glyphicon-bookmark"></span>&nbsp;&nbsp;스크랩</a></li>
			                     	<li><a href="write.jsp"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;글쓰기</a></li>
			                     	<li><a href="registerupdate.do?id=${sessionScope.vo.id}"><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;정보 수정</a></li>
			                  		</ul>
		                  		</c:when>
		      
		                  		<c:otherwise>
		                  			<ul class="dropdown-menu">
			                     	<li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;로그인</a></li>
			                     	<li><a href="register.jsp"><i class="fas fa-user-plus"></i>&nbsp;&nbsp;회원가입</a></li>
			                  		</ul>
		                  		</c:otherwise>
		                  	</c:choose>
	               		</li>
					</ul>
				</div>
				<!-- myNavbar -->
			</div>
			<!-- container -->
		</div>
		<!-- header -->
		<div id="line"></div>
	</nav>
<h1 align="center" style="margin-top:161px;"> 글 작성 </h1>
<form action="write.do" method="post" name="frm"> <!--  enctype="multipart/form-data" -->
<!-- <input type="hidden" name="command" value="write"> -->
<table border="1" align="center" width="80%" style="table-layout:fixed;">
	<tr>
		<th align="center">작성자</th>
		<td><input type="text" name="id" value="${sessionScope.vo.id}" readonly="readonly" style="width:99%;"></td>
		<th>지역</th>
		<td>	
			<select id="selectBox" name="loaction" style="width:180px;" onchange="categoryChange(this)" required="required">
				<option value="0">광역시/도</option>
            	<option value="서울">서울특별시</option>
            	<option value="인천">인천광역시</option>
            	<option value="대전">대전광역시</option>
            	<option value="대구">대구광역시</option>
            	<option value="광주">광주광역시</option>
            	<option value="울산">울산광역시</option>
            	<option value="부산">부산광역시</option>
            	<option value="경기도">경기도</option>
            	<option value="강원도">강원도</option>
            	<option value="10">세종특별자치시</option>
            	<option value="충청북도">충청북도</option>
            	<option value="충청남도">충청남도</option>
            	<option value="전라북도">전라북도</option>
            	<option value="전라남도">전라남도</option>
            	<option value="경상북도">경상북도</option>
            	<option value="경상남도">경상남도</option>
            	<option value="제주도">제주특별자치도</option>
           	</select>
        </td>
        <td align="center"> 	
           	<select id="location2" name="city" required="required">
           		<option>광역시/도를 먼저 선택해주세요.</option>	
           	</select>
        </td>
	</tr>
	<tr>
		<th>제목</th>
		<td width="80px" colspan="4"><input type="text" name="title" required="required" style="width:99%;"></td>
	</tr>
	<tr>
		<th>카테고리</th>
		<td colspan="4">
		<input type="checkbox" name="category" value="맛집">먹거리&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="checkbox" name="category" value="자연">자연&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="checkbox" name="category" value="사진">사진
		</td>
	</tr>
	<tr>
		<td colspan="4"><textarea id="smarteditor" rows="30" cols="40" name="smarteditor" style="width:99%;"></textarea></td>
		<td><div style="overflow-y:auto; overflow-x:hidden; width:270px; height:520px;" id="imageupload"></div></td>
	</tr>
</table>
	
<table width="90%">
	<tr>
		<td align="right">
			<input type="button" value="취소">
			<input type="submit" id="savebutton" value="작성">
		</td>
	</tr>	
</table>
</form>
</body>
</html>