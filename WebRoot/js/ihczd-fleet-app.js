/**
 * 改良自定义控件
 **/
var ihczd_fleet_app = {
	serializeJsonObjByForm:function(form){//根据表单对象获取表单数据 ,返回类型为json对象
		var serializeObj={}; 
		var array=form.serializeArray(); 
		$(array).each(function(){ 
			if(serializeObj[this.name]){ 
				if($.isArray(serializeObj[this.name])){ 
					serializeObj[this.name].push(this.value); 
				}else{ 
					serializeObj[this.name]=[serializeObj[this.name],this.value]; 
				} 
			}else{ 
				serializeObj[this.name]=this.value; 
			} 
		}); 
		return serializeObj; 
	}
};

/**
 * 下拉框(对原生的select做了简单的封装)
 * @author lizx
 * @version 1.0
 * @date 2015-11-20
 */
(function($){
	$.fn.appSelect = function(opt,agrs){
		var value = null;
		var $this = $(this),
			data = $this.data('app.select'),
			options = $.extend({},$.fn.appSelect.defaults,typeof opt === 'object' && opt);
		if($this[0] && $this[0].tagName != 'SELECT') alert('下拉框请使用select标签');
		if (typeof opt === 'string'){
	       	if (!data){
	       		return;
	        }
	       	value = data[opt](agrs);
        }
		
		if (!data) {
            $this.data('app.select', (data = new AppSelect($this,options)));
        }
		return typeof value === 'undefined' ? this : value;
	};
	//默认属性
	$.fn.appSelect.defaults = {
			url    : null, //请求路径
			params : {},   //请求参数
			data   : [],   //数据[{key:value},{key:value}]
	        textField  : 'text',//显示文本字段
	        valueField : 'id',//隐藏文本字段
	        emptyText:null,//空项显示文本
	        selText:null,//根据该文本设置选中
	        loading:true,//控件一初始化，马上执行数据加载
	        onChange:function(val,text,data){}
	};
	var AppSelect = function(el,opt){
		this.$el = el;
		this.options = opt;
		this.init();
	};
	//初始化控件
	AppSelect.prototype.init = function(){
		var opt = this.options;
		var $select = this.$el;
		if(opt.url && opt.loading){
			$.ajax({
				url:opt.url,
				type:'post',
				data:opt.params,
				dataType:'json',
				success:function(data){
					opt.data = data;
					initOptions($select,opt);
					this.initFinish = true;
				},
				error:function(err){
					alert('数据加载失败');
				}
			});
		}else{
			initOptions($select,opt);
			this.initFinish = true;
		}
		//绑定选中事件
		$select.unbind('change').change(function(e){
			var selected = $select.find('option:selected')[0];
    		if(opt.onChange && $.isFunction(opt.onChange))
    			opt.onChange.call(this,selected.value,selected.text,$(selected).data('jsonData'));
    	});
	};
	//重新加载
	AppSelect.prototype.reload = function(args){
		var $select = this.$el
		var opt = this.options;
		args = args || {};
		opt = $.extend({},opt,args);
		if(opt.url){
			$.ajax({
				url:opt.url,
				type:'post',
				data:opt.params,
				dataType:'json',
				success:function(data){
					opt.data = data;
					$select.html('');
					if(opt.emptyText)
						$select.append('<option value="">'+opt.emptyText+'</option>');
					var initValue = $.trim($select.attr('value'));//初始值，select上的value值
					$.each(opt.data,function(index,obj){
						if(opt.selText && opt.selText == obj[opt.textField]){//设置指定文本选中，特殊情况使用
							$select.append('<option value="'+obj[opt.valueField]+'" selected>'+obj[opt.textField]+'</option>');
							opt.onChange.call(this,obj[opt.valueField],obj[opt.textField],obj);
						}else{
							$select.append('<option value="'+obj[opt.valueField]+'">'+obj[opt.textField]+'</option>');
						}
						$select.find('option:last').data('jsonData',obj);
					});
				},
				error:function(err){
					alert('数据加载失败');
				}
			});
		}
	};
	//获取选中值
	AppSelect.prototype.getValue = function(){
		var $select = this.$el;
		var selected = $select.find('option:selected')[0];
		return selected.value;
	};
	//获取选中文本
	AppSelect.prototype.getText = function(){
		var $select = this.$el;
		var selected = $select.find('option:selected')[0];
		return selected.text;
	};
	//根据valueField 字段的值，设置选择
	AppSelect.prototype.select = function(value){
		var $this = this;
		var $select = $this.$el;
		if($this.options.initFinish){
			$select.find('option[value="'+value+'"]').attr("selected",true);
		}
		else{
			var int = setInterval(function(){
				if($this.options.initFinish){
					clearInterval(int);
					$select.find('option[value="'+value+'"]').attr("selected",true);
				}
			},1000);
		}
	};
	//初始化下拉项
	function initOptions(el,opt){
		var $select = el;
		var initValue = $.trim($select.attr('value'));//初始值，select上的value值
		if(opt.data.length > 0){
			$select.html('');
			if(opt.emptyText)
				$select.prepend('<option value="">'+opt.emptyText+'</option>');
			$.each(opt.data,function(index,obj){
				if(opt.selText && opt.selText == obj[opt.textField]){//设置指定文本选中，特殊情况使用
					$select.append('<option value="'+obj[opt.valueField]+'" selected>'+obj[opt.textField]+'</option>');
					opt.onChange.call(this,obj[opt.valueField],obj[opt.textField],obj);
				}
				else if(initValue == obj[opt.valueField]){//值相等是，设置选中
					$select.append('<option value="'+obj[opt.valueField]+'" selected>'+obj[opt.textField]+'</option>');
					opt.onChange.call(this,obj[opt.valueField],obj[opt.textField],obj);
				}else{
					$select.append('<option value="'+obj[opt.valueField]+'">'+obj[opt.textField]+'</option>');
				}
				$select.find('option:last').data('jsonData',obj);
			});
		}else{
			if(opt.emptyText)
				$select.prepend('<option value="">'+opt.emptyText+'</option>');
			$select.find('option[value="'+initValue+'"]').attr("selected",true);
			var selected = $select.find('option:selected')[0];
			opt.onChange.call(this,selected.value,selected.text);
		}
		opt.initFinish = true;
	}
})(jQuery);