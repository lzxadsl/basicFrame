/**
 * 系统公用JS
 * @author 李智贤
 * @data 2015-09-10
 */

(function($){
	
	$.extend({
		getBrowserMsg:function(){
			/**
			 * 获取浏览器类型
			 */
			var Sys = {};
			var ua = navigator.userAgent.toLowerCase();
			var s;
			(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
			(s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
			(s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
			(s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
			(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
			return Sys
		}
	});
})(jQuery);
