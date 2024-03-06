var swiper_num = 0;
const swipe_num_max = 3;

function swipe_prev(){
	if(swiper_num <= 0)
		swiper_num = 2;
	else
		swiper_num--;
	
	var swipeImgs = document.getElementsByClassName("swipe_imgs");
	var swipeLines = document.getElementsByClassName("swiper_lines");
	for(var i = 0; i < swipe_num_max; i++){
		if(i == swiper_num){
			swipeImgs[i].style.display = "block";
			swipeLines[i].style.backgroundColor = "black";
		}
		else{
			swipeImgs[i].style.display = "none";
			swipeLines[i].style.backgroundColor = "gray";
		}
	}
}

function swipe_next(){
	if(swiper_num >= 2)
		swiper_num = 0;
	else
		swiper_num++;
	
	var swipeImgs = document.getElementsByClassName("swipe_imgs");
	var swipeLines = document.getElementsByClassName("swiper_lines");
	for(var i = 0; i < swipe_num_max; i++){
		if(i == swiper_num){
			swipeImgs[i].style.display = "block";
			swipeLines[i].style.backgroundColor = "black";
		}
		else{
			swipeImgs[i].style.display = "none";
			swipeLines[i].style.background = "gray";
		}
	}
}

