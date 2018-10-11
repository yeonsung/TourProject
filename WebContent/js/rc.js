var
	carousel = document.querySelector('.carousel'),
	figure = carousel.querySelector('figure'),
	nav = carousel.querySelector('nav'),
//	numImages = figure.childElementCount,
//	theta =  1 * Math.PI / numImages,
	currImage = 0
;
	
nav.addEventListener('click', onClick, true);


function onClick(e) {
	e.stopPropagation();
	var standard = "0.7853981634";
	var t = e.target;
	if (t.tagName.toUpperCase() != 'BUTTON')
		return;
	if(numImages==8){
		if (t.classList.contains('next')) {
			currImage--;
		}
		else {
			currImage++;
		}
	}
	else{
		if (t.classList.contains('next')) {
			if(0<currImage)
				currImage--;
		}
		else {
			if(currImage<numImages-1)
				currImage++;
		}
	}
	
	figure.style.transform = `rotateY(${standard * (currImage-1)}rad)`;
}
//`rotateY(${currImage * -theta}rad)`
$('.checkbox').click(function(){
	  $(this).toggleClass('is-checked');
	  
	});