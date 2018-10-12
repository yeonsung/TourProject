
	/*roca*/
function onClick(e) {
	e.stopPropagation();
	
	var t = e.target;
	if (t.tagName.toUpperCase() != 'BUTTON')
		return;
	
	if (t.classList.contains('next')) {
		currImage++;
	}
	else {
		currImage--;
	}
	
		figure.style.transform = `rotateY(${currImage * -theta}rad)`;
	}


/*===========이미지 팝업 function===========*/
function expImgPop(img){ 
		 img1= new Image(); 
		 img1.src=(img); 
		 imgControll(img); 
		} 
		  
		function imgControll(img){ 
		 if((img1.width!=0)&&(img1.height!=0)){ 
		    viewImage(img); 
		  } 
		  else{ 
		     controller="imgControll('"+img+"')"; 
		     intervalID=setTimeout(controller,20); 
		  } 
		}
		
		
		
/*====팝업창 설정====*/
function viewImage(img){ 
		//이미지 원본 크기?
		 W=img1.width; 
		 H=img1.height; 
		//중앙위치 구해오기
		 LeftPosition=(screen.width-W)/2;
		 TopPosition=(screen.height-H)/2;
		 
		 O="width="+W+",height="+H+",top="+TopPosition+",left="+LeftPosition+",scrollbars=yes"; 
		 imgWin=window.open("","",O); 
		 imgWin.document.write("<html><head><title>이미지상세보기</title></head>");
		 imgWin.document.write("<body topmargin=0 leftmargin=0>");
		 imgWin.document.write("<img src="+img+" onclick='self.close()' style='cursor:pointer;' title ='클릭하시면 창이 닫힙니다.'>");
		 imgWin.document.close();
		}
