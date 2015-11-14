!function ($){
	var iframe = function(){
		alert('ni hao a');
	};
	$.fn.ifra = iframe();
	console.log("src:"+$('iframe').attr('src'));
}(jQuery);