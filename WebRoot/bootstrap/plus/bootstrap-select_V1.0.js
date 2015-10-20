/**
 * bootstrap 下拉框插件
 * @author 李智贤
 * @version 1.0
 * @date 2015-07-01
 */
(function($){
	 //'use strict';//开启严格模式
	 
	 /**
	  * 调用入口:$('#order_status1').bootstrapSelect({});
	  * options 为对象时是属性设置参数，为字符串时，是调用具体方法，
	  * agrs 为调用方法时的参数
	  */
	 $.fn.bootstrapSelect = function(option,agrs){
		 var value = null;
		
		 this.each(function () {
             var $this = $(this),
             data = $this.data('bootstrap.select'),
             options = $.extend({},$.fn.bootstrapSelect.defaults,$this.data(),
                    typeof option === 'object' && option);
             
             if (typeof option === 'string') {
            	 if (!data) {
                     return;
                 }
            	 value = $.fn.bootstrapSelect.methods[option](this,data.options,agrs);
             }
             if (!data) {
            	 //判断标签类型,如果是input标签则转换成select标签
            	 if($this[0].tagName == 'INPUT'){
                 	var new_el = $($this[0].outerHTML.replace('input','select'))
								                 	.removeAttr('class')
								                 	.removeAttr('type');
                 	$this.replaceWith(new_el);
                 	$this = $(new_el);//$('#'+new_el.attr('id'));
                 }
            	 //为元素添加data属性
                 $this.data('bootstrap.select', (data = new BootstrapSelect($this,options)));
             }
        });
		 
		return typeof value === 'undefined' || value == null ? this : value;
	 };
	 
	 /**
	 * 定义默认配置信息
	 */
	$.fn.bootstrapSelect.defaults = {
			url    : null, //请求路径
			params : {},   //请求参数
			paramsType : '',//参数默认是以表单形势传递，为json时是以json格式传递到后台
			data   : [],   //数据[{key:value},{key:value}]
	        method : 'get',//请求方法
	        textField  : 'text',//显示文本字段
	        valueField : 'id',//隐藏文本字段
	        relationId : null,//级联id
	        emptyText  : '',//空选项文本
	        emptyValue : '',//空选项值
	        separator  : ',',//多选时返回值的分割符
	        enabled	 : false,//是否可编辑
	        cls:'',//自定义样式,多个样式用逗号隔开 class1,class2
	        formatter:function(rec){},//格式化节点	
	        onSelect : function(rec){},
	        onBeforeLoad: function(param){},//param 请求参数
			onLoadSuccess: function(data){},//data加载成功后返回的数据
			onLoadError: function(){}
	};
	
	/**
	 * 控件方法属性
	 */
	$.fn.bootstrapSelect.methods = {
			getValue:function(target,options){
				var rec = [];
				$(target).find('option:selected').each(function(i,v){
					rec.push($(this).val());
				});
				if($(target).attr('multiple')){
					return rec.join(options.separator);
				}
				else{
					return rec[0];
				}
			},//获取下拉框选中值,多选的时候返回的是数组
			getText:function(target,options,args){
				//return target.options[target.selectedIndex].text;
				var rec = [];
				$(target).find('option:selected').each(function(i,v){
					rec.push($(this).text());
				});
				if($(target).attr('multiple')){
					return rec.join(options.separator);
				}
				else{
					return rec[0];
				}
			},
			setValue:function(target,options,values){
				setValues(target,options,values);
			},//设置选中，如果是多选下拉框value 格式：2，3，4
			getData:function(target,options){
				return options.data;
			},//获取data数据，返回值是数组类型
			reload:function(target,options,args){
				args = args || {};
				if(args.url){
					options['url'] = args.url;
				}
				if(args.params){
					options['params'] = args.params;
				}
				//new BootstrapSelect(target,options);
				$(target).data('bootstrap.select').reload();
			},//重新加载，可重定向url和params
			refresh:function(target,options,args){
				//new BootstrapSelect(target,options);
				$(target).data('bootstrap.select').reload();
			},//刷新
			unselect: function(jq, value){
				return jq.each(function(){
					unselect(this, value);
				});
			}
	};
	
	var BootstrapSelect = function (el,options) {
        this.options = options;
        this.$el = el;
        this.$el_ = this.$el.clone();
        this.timeoutId_ = 0;
        this.init();
    };
    
    //初始化控件
    BootstrapSelect.prototype.init = function(){
    	$(this.$el).empty();//初始化前先清空所有option节点
    	this.initServer();
    	//this.initOption();
    };
    //向服务器请求数据
    BootstrapSelect.prototype.initServer = function(){
    	var $this = this;
    	if($this.options.url){
    		$this.options.onBeforeLoad.call(this,$this.options.params);
        	$.ajax({
        		url:$this.options.url,
        		type:$this.options.method,
        		data:$this.options.paramsType == 'json' ? JSON.stringify($this.options.params) : $this.options.params,
        		dataType:'json',
        		//async:false,
        		success: function(jsonLst) {
        			//options['data'] = jsonLst;
        			$this.options.onLoadSuccess.call(this,jsonLst);
        			$this.initOption(jsonLst);
    			},
    			error: function(xhr, textStatus, errorThrown){
    				$this.options.onLoadError.call(this);
    				throw 'ajax 数据加载失败';
    		    }
        	});
    		//loadData($this.options,this.initOption());
    	}
    	else if(!$this.options.data || typeof $this.options.data != 'object'){
    		throw 'data 数据类型有误，必须为数组';
    	}
    	else{
    		$this.initOption();
    	}
    };
    //初始化下拉节点
    BootstrapSelect.prototype.initOption = function(data){
    	var $this = this;
    	if(data){
    		$this.options['data'] = data;
    	}
    	//创建option节点
    	create($this.$el,$this.options);
    	//嵌套外层元素
    	wrapEl($this.$el,$this.options);
    	//设置选中
    	setValues($this.$el,$this.options);
    	
    };
    //重新加载
    BootstrapSelect.prototype.reload = function(){
    	var $this = this;
    	$($this.$el).empty();//先清空所有option节点
    	if($this.options.url){
    		$this.options.onBeforeLoad.call(this,$this.options.params);
        	$.ajax({
        		url:$this.options.url,
        		type:$this.options.method,
        		data:$this.options.paramsType == 'json' ? JSON.stringify($this.options.params) : $this.options.params,
        		dataType:'json',
        		success: function(jsonLst) {
        			$this.options.onLoadSuccess.call(this,jsonLst);
        			//创建option节点
        			$this.options['data'] = jsonLst;
        	    	create($this.$el,$this.options);
    			},
    			error: function(xhr, textStatus, errorThrown){
    				$this.options.onLoadError.call(this);
    				throw 'ajax 数据加载失败';
    		    }
        	});
    	}
    	else{
    		//创建option节点
	    	create($this.$el,$this.options);
    	}
    };
    
    /**
     * 创建option节点
	 * @param target 下拉框
	 * @param options 配置项
	 */
    function create(target,options){
    	$(target).addClass('form-control bootstrap-select');//添加样式
    	$(target).addClass(options.cls.replace(/,/g,' '));
    	var itemsHtml = '<option value='+options.emptyValue+'>'+options.emptyText+'</option>';
    	var data = options.data;//节点数据
    	var valueField = options.valueField;
    	var textField = options.textField;
    	$.each(data,function(index,val){
    		options.formatter.call(this,val);
    		itemsHtml += '<option value='+val[valueField]+'>'+val[textField]+'</option>';
    	});
    	$(target).append(itemsHtml);
    }
    /**
     * 嵌套元素，使之变得可编辑
	 * @param target 下拉框
	 * @param options 配置项
	 */
    function wrapEl(target,options){
    	var width = $(target)[0].clientWidth - 25;
    	var height = $(target)[0].clientHeight - 2;
    	//select z-index 属性值
    	var z_index = $(target).css('z-index') == 'auto' ? 999 : Number($(target).css('z-index'));
    	$(target).wrap('<span style="height:'+(height+5)+'px;display:block;overflow:hidden;position:relative;"></span>');
    	$('<input type="text" value="'+options.emptyText+'" class="bootstrap-select_input" style="z-index:'+(z_index-10)+';padding-left:17px;margin-top:2px;height:'+height+'px;border:0px;position:relative;width:'+width+'px;">').insertAfter($(target));
    	$(target).css({'position':'absolute','left':0,'top':'1px','z-index':z_index});
    	var $input = $(target).next();
    	var isShow = true;//是否显示select在最外层
    	//可编辑的时候开启却换事件
    	if(options.enabled && !$(target).attr('multiple')){
    		var contentDownDivObj =  new ContentDownDiv($(target),$input,options);
    		//下拉框点击事件
        	$(target).unbind('click').click(function(){
        		if(isShow){//显示input
        			$(this).css('z-index',z_index-20);
        		}
        		else{//显示select
            		$(this).css('z-index',z_index);
        		}
        		isShow = true;
        	});
        	//文本框得到焦点
        	$input.unbind('focus').focus(function(){
    			$(this).css('outline','none');
    		});
        	//文本框失去焦点
        	$input.unbind('blur').blur(function(){
        		$(target).css('z-index',z_index);
    		});
        	//
        	$input.unbind('keyup').keyup(function(event){
        		inputKeyUp(event,contentDownDivObj);
        	});
    	}
    	//绑定选择事件
    	$(target).unbind('change').change(function(e){//这边的this是被选中的项
    		var rec = select(this,options);
    		options.onSelect.call(this,rec);
    		$input.val(rec[options.textField]);
    		isShow = false;
    	});
    }
    /**
     * 获取选择项
	 * @param target 下拉框
	 * @param options 配置项
	 */
	function select(target,options){
		var _this = $(target);
		if(options.multiple || $(target).attr('multiple')){//多选
			var rec = [];
			_this.find('option:selected').each(function(i,v){
				var obj = {};
				obj[options.valueField] = $(this).val();
				obj[options.textField] = $(this).text();
				rec.push(obj);
			});
			return rec;
		}else{
			var rec = {};
			rec[options.valueField] = _this.val();
    		rec[options.textField] = target.options[target.selectedIndex].text;
    		return rec;
		}
	}
	
	/**
	 * 设置选中
	 * @param target 下拉框
	 * @param options 配置项
	 */
	function setValues(target,options,value){
		var _this = $(target);
		var $input = _this.next();
		var values = value || (_this.attr('value') == undefined || _this.attr('value') == null ?'': _this.attr('value'));
		_this.find('option').each(function(i,v){
			var option = $(this);//节点
			var optionVal = option.val();//节点值
			var optionText = option.text();//节点文本
			option.removeAttr('selected');//清除原来选择
			//设置选中
			$.each((values+'').split(options.separator),function(i,it){
    			if(it == optionVal){
    				option.attr('selected',true);
    				if(!$(target).attr('multiple')){
    					$input.val(optionText);
    				}
    				return false;//break
    			}
    		});
		});
	}
	
    /**
     * input的下拉div
	 * @param target input
	 * @param options 配置项
	 */
    var ContentDownDiv = function(selectEl,inputEl,options){
    	this.options = options;
        this.$input = inputEl;
        this.$input_ = this.$input.clone();
        this.$select = selectEl;
        this.timeoutId_ = 0;
        this.data_ = this.options.data;
        this.config = {
               //width:下拉框的宽度，默认使用输入框宽度
               width:this.$input.outerWidth() + 25,
               //url：格式url:""用来ajax后台获取数据，返回的数据格式为data参数一样
               url:null,
               /*data：格式{data:[{title:null,result:{}},{title:null,result:{}}]}
               url和data参数只有一个生效，data优先*/
               data:null,
               //callback：选中行后按回车或单击时回调的函数
               callback:null
        };
        this.holdText = null;//输入框中原始输入的内容
        this.init();
    };
    //初始化控件
    ContentDownDiv.prototype.init = function(){
    	if($("body").length > 0 && $("#bigAutocompleteContent").length <= 0){
    		this.createDownDiv();//初始化信息
		}
    	else{
    		this.$contentDownDiv = $('#bigAutocompleteContent');
    	}
    	//this.keyUp(event);
    };
    
    /**
     * 创建下拉div
	 * @param target 下拉框
	 * @param options 配置项
	 */
    ContentDownDiv.prototype.createDownDiv = function(){
    	var _this = this;
    	var style = 'display: none;'
    		+'background-color: #FFFFFF;'
    		+'border: 1px solid #BCBCBC;'
    		+'position: absolute;'
    		+'z-index: 9999;'
    		+'height:300px;'
    		+'max-height: 220px;'
    		+'overflow-x: hidden;'
    		+'overflow-y: auto;';
    	$("body").append('<div id="bigAutocompleteContent" style="'+style+'"></div>');
    	$(document).bind('mousedown',function(event){
			var $target = $(event.target);
			if((!($target.parents().andSelf().is('#bigAutocompleteContent')))){
				_this.hideDownDiv();
			}
		});
		
		//鼠标悬停时选中当前行
		$('#bigAutocompleteContent').delegate('tr', 'mouseover', function() {
			$('#bigAutocompleteContent tr').css('background',null);
			$(this).css('background','none repeat scroll 0 0 #D2DEE8');
		}).delegate('tr', 'mouseout', function() {
			$('#bigAutocompleteContent tr').css('background','');
		});	
		
		//单击选中行后，选中行内容设置到输入框中，并执行callback函数
		$('#bigAutocompleteContent').delegate('tr', 'click', function() {
			console.log(_this.options.valueField);
			$(_this.$select).bootstrapSelect('setValue',$(this).data('jsonData')[_this.options.valueField]);
			_this.$input.val($(this).find('div:last').html());
			_this.hideDownDiv();
		});
		this.$contentDownDiv = $('#bigAutocompleteContent');
    };
    
    //隐藏下拉框
    ContentDownDiv.prototype.hideDownDiv = function(){
		var $contentDownDiv = this.$contentDownDiv;
		if($contentDownDiv.css('display') != 'none'){
			$contentDownDiv.find('tr').css('background',null);
			$contentDownDiv.hide();
		}			
	};
	
	/**
	 * 组装下拉框html内容并显示
	 * @param target ContentDownDiv对象
	 * @param data_ 数据
	 */
    function makeContAndShow(contentDownDiv,data_){
    	var $this = contentDownDiv.$contentDownDiv;
    	var options = contentDownDiv.options;
		if(data_ == null || data_.length <=0 ){
			return;
		}
		var cont = '<table width="100%"><tbody>';
		for(var i=0;i<data_.length;i++){
			cont += '<tr><td><div>' + data_[i][options.textField] + '</div></td></tr>';
		}
		cont += '</tbody></table>';
		$this.html(cont);
		$this.show();
		
		//每行tr绑定数据，返回给回调函数
		$this.find('tr').each(function(index){
			$(this).data('jsonData',data_[index]);
		});
	}	
    
    /**
     * 为输入框绑定keyup事件
     * @param event 事件
     * @param ContentDownDiv 下拉框div对象
     */
    function inputKeyUp(event,ContentDownDiv){
    	var contentDownDiv = ContentDownDiv.$contentDownDiv;
    	var functionalKeyArray = [9,20,13,16,17,18,91,92,93,45,36,33,34,35,37,39,112,113,114,115,116,117,118,119,120,121,122,123,144,19,145,40,38,27];//键盘上功能键键值数组
    	//输入框keyup事件
		var k = event.keyCode;
		var ctrl = event.ctrlKey;
		var isFunctionalKey = false;//按下的键是否是功能键
		for(var i=0;i<functionalKeyArray.length;i++){
			if(k == functionalKeyArray[i]){
				isFunctionalKey = true;
				break;
			}
		}
		//k键值不是功能键或是ctrl+c、ctrl+x时才触发自动补全功能
		if(!isFunctionalKey && (!ctrl || (ctrl && k == 67) || (ctrl && k == 88)) ){
			var offset = ContentDownDiv.$input.offset();
			contentDownDiv.width(ContentDownDiv.config.width);
			var h = ContentDownDiv.$input.outerHeight() + 4;
			contentDownDiv.css({"top":offset.top + h,"left":offset.left});
			var data = ContentDownDiv.data_;
			var keyword_ = $.trim(ContentDownDiv.$input.val());
			
			if(keyword_ == null || keyword_ == ""){
				ContentDownDiv.hideDownDiv();
				return;
			}					
			if(data != null && $.isArray(data) ){
				var data_ = new Array();
				for(var i = 0; i < data.length; i++){
					if(data[i][ContentDownDiv.options.textField].indexOf(keyword_) > -1){
						data_.push(data[i]);
					}
				}
				makeContAndShow(ContentDownDiv,data_);
			}
			ContentDownDiv.holdText = ContentDownDiv.$input.val();
		}
		//回车键
		if(k == 13){
			if(ContentDownDiv.$contentDownDiv.css("display") != 'none'){
				ContentDownDiv.$contentDownDiv.hide();						
			}
		}
    };
})(jQuery);