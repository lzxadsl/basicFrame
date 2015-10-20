/**
 * echarts 图表控件，使用版本2.2.5
 * @author lizhixian
 * @version 1.0
 * @create_date 2015-07-01
 */
(function($){
	var allecharts = {};//页面中所定义的图标集合 key 为id
	var echartsconfig = {};//保存每个图表配置信息
	var isLoadJs = false;//是否已经加载echarts-all.js文件
	/*function importJs(isLoadJs){
		if(!isLoadJs){
			var path = 'js/echarts/echarts-all.js';
			var script = "<script type='text/javascript' src='"+path+"' id='echarts'></script>";
			$('head').append(script);
		}
	}*/
	if(!isLoadJs){
		var path = 'js/echarts/echarts-all.js';
		/*var script = "<script type='text/javascript' src='"+path+"' id='echarts'></script>";
		$('head').append(script);*/
		document.write("<script type='text/javascript' src='"+path+"' id='echarts'></script>");
	}
	
	/**
	 * 图表调用入口（目前只加入了饼图、折线图、柱状图、环形图）
	 * @param configs 图表基本配置信息 是一个对象类型 
	 * 调用方法$('id').showecharts({url:'',title:'',...});
	 * configs:{
	 * 		url    :必填，ajax请求路径,
	 * 				饼图返回数据格式：[{"name":"江苏","value":50.1},{"name":"广东","value":70.13}],
	 * 				折线图返回数据格式:[{name:'邮件营销',type:'line',stack: '总量',
	 *				            	data:[120, 132, 101, 134, 90, 230, 210]//数组值对应X轴坐标各个节点
	 *				        	  },
	 *				        	  {name:'联盟广告',type:'line',stack: '总量',
	 *				                data:[220, 182, 191, 234, 290, 330, 310]
	 *				              }]
	 *  	type   :必填，图表类型 默认是pie，pie（饼图）、line （折线图）、bar（柱状图）、ringPie（环形图）
	 *  	title  :必填，图表标题、
	 * 		params :非必填，{key1:value1,key2:value2} 请求参数
	 * 		subtext:非必填，图表子标题
	 * 		legend :非必填，是否显示图例 true显示,默认为false不显示
	 * 		options:非必填，图表设置信息，图表控件自身的一些设置信息（无定义时自动使用默认配置，根据类型从 $.fn.showecharts.options 中获取） 
	 * 		seriesNm:非必填，饼图鼠标经过时显示标题，折线图无此属性
	 * 		xAxisUnit:非必填，折线图横坐标单位 默认是月
	 * 		total:合计项值
	 * }
	 * 以上参数如未定义 则使用默认配置，参见：$.fn.showecharts.defaults
	 */
	$.fn.showecharts = function(configs,agrs){//当configs为字符串时 agrs 可以是对象参数，也可以是函数，根据调用的方法进行传值
		var id = $(this).attr('id');
		if (typeof configs == 'string'){
			var method = $.fn.showecharts.method[configs];
			if (method){
				return method(id,agrs);
			}
		} 
		configs = configs || {};//配置信息
		configs['id'] = id;//添加id属性
		var newconfigs = $.extend(true,{},$.fn.showecharts.defaults,configs);//跟默认配置合并，无配置的项使用默认配置
		var echart = create(newconfigs);
		allecharts[id] = echart;//保存图表
		echartsconfig[id] = newconfigs;//保存配置信息
		return echart;
	};
	
	/**
	 * 定义默认配置信息
	 */
	$.fn.showecharts.defaults = {
			url    : null, //请求路径
			type   : 'pie',//图表类型
			title  : '',   //图表标题
	  		params : {},   //请求参数
	  		json   : false, //参数默认是以表单形势传递，为true时是以json格式传递到后台
	  		subtext: '',   //图表子标题
	  		legend : false,//是否显示图例 true显示,默认为false不显示 
	  		options: null, //图表设置信息，图表控件自身的一些设置信息（无定义时自动使用默认配置,根据类型从 $.fn.showecharts.options 中获取） 
	  		seriesNm:'',   //图鼠标经过时显示标题，折线图无此属性
	  		xAxisData:null,//折线图横坐标，默认值是根据返回来的数据长度来设置的 0-返回值长度
	  		xAxisUnit:'月',//折线图横坐标单位 默认是月
	  		selfButtons:{},//自定义按钮
	  		total:0//合计项
	};
	
	//创建图表,
	function create(configs){
		//importJs(isLoadJs);//首次加载时引入JS
		isLoadJs = true;
		var echart = echarts.init(document.getElementById(configs.id));//初始化echarts组件
		echart.showLoading({
            animation:false,
            text : '数据加载中，请稍后...',
            textStyle : {fontSize : 20}
        }); 
		var options = {};//控件自身设置参数
		//是否有自定义配置
		if(configs.options != undefined && configs.options != null){
			options = configs.options;
		}
		else{
			options = $.fn.showecharts.options[configs.type](configs);//根据图表类型 加载相应的设置参数
			configs['options'] = options;
		}
		//数据加载完毕后显示图表
		$.ajax({
			url:configs.url ,type: 'post',async: true,timeout: 30000,
			data:configs.json == true ? JSON.stringify(configs.params) : configs.params,
			async:false,
			success: function(jsonLst) {
				setSeries(configs,jsonLst);
				echart.setOption(configs.options);
				echart.hideLoading();
				
			},
			error: function(xhr, textStatus, errorThrown) {
				alert(textStatus);
		    }
	    });
		return echart;
	}

	//组装数据
	function setSeries(configs,jsonLst){
		var type = configs.type;
		var series = [];
		var legendData = [];
		//是否显示legend属性
		if(configs.legend){
			$.each(jsonLst,function(n,value){
				legendData.push(value['name']);//根据得到的数据自动设置legend
			});
		}
		//标准饼图
		if(type == 'pie'){
			if(jsonLst == null || jsonLst.length < 1){
				jsonLst = [{"name":"没有任何数据","value":0}];
			}
			else{
				$.each(jsonLst,function(n,value){
					configs.total += Number(value.value ? value.value : 0);
				});
			}
			configs.options['total'] = rendererZhMoney(parseFloat(configs.total).toFixed(2));//添加合计项
			series = [{
				radius : '65%',
	            center: ['50%', '60%'],    
	            type: 'pie',
	            name: configs.seriesNm,
	            data:jsonLst,
	            itemStyle:{ 
	                normal:{ 
	                      label:{ 
	                        show: true, 
	                        formatter: '{b} : ({d}%)' 
	                      }, 
	                      labelLine :{show:true} 
	                } 
	            } 
	        }];
			configs.options.legend = {
		        orient : 'vertical',
		        x : 'left',
		        y: 'center',
		        data:legendData
		    };
		}
		//折线图
		else if(type == 'line'){
			if(jsonLst == null || jsonLst.length < 1){
				jsonLst = [{"name":"没有任何数据","data":[0]}];
			}
			var xAxisData = [];//程序根据传入的单位，自动创建X轴各坐标节点
			if(configs.xAxisData != null){//有自定义X轴显示数组
				xAxisData = configs.xAxisData;
			}else{//没定义，程序自动处理
				for(var i = 1; i <= jsonLst[0].data.length; i++){
					xAxisData.push(i+configs.xAxisUnit);
				}
			}
			
			$.each(jsonLst,function(index,obj){//如果数据是字符串类型1,2,3，则转换成数组
				var newData = [];
				if(typeof obj.data == 'string'){
					var data = obj.data.split(',');
					if(data.length != xAxisData.length){//当后台取回的数据数组长度跟X轴点数不匹配时做处理
						for(var l = 0; l < xAxisData.length; l++){newData.push(0);}//初始化数组默认值
						var xdata = obj.xdata.split(',');
						$.each(xdata,function(indx,val){
							newData[$.inArray(val,xAxisData)] = parseFloat(data[indx]).toFixed(2);//保留两位小数
						});
						obj.data = newData;
					}
					else{
						obj.data = data;
					}
				}
			});
			series = jsonLst;
			configs.options.xAxis[0].data = xAxisData;//添加到参数配置中
			configs.options.legend = {
				orient : 'vertical',
				x : 'right',
				y: 'center',
		        data:legendData
		    };
		}
		//柱状图
		else if(type=='bar'){
			if(jsonLst == null || jsonLst.length < 1){
				jsonLst = [{"name":"没有任何数据","data":[0]}];
			}
			var xAxisData = [];//程序根据传入的单位，自动创建X轴各坐标节点
			if(configs.xAxisData != null){//有自定义X轴显示数组
				xAxisData = configs.xAxisData;
			}else{//没定义，程序自动处理
				for(var i = 1; i <= jsonLst[0].data.length; i++){
					xAxisData.push(i+configs.xAxisUnit);
				}
			}
			
			$.each(jsonLst,function(index,obj){//如果数据是字符串类型1,2,3，则转换成数组
				var newData = [];
				if(typeof obj.data == 'string'){
					var data = obj.data.split(',');
					if(data.length != xAxisData.length){//当后台取回的数据数组长度跟X轴点数不匹配时做处理
						for(var l = 0; l < xAxisData.length; l++){newData.push(0);}//初始化数组默认值
						var xdata = obj.xdata.split(',');
						$.each(xdata,function(indx,val){
							newData[$.inArray(val,xAxisData)] = parseFloat(data[indx]).toFixed(2);//保留两位小数
						});
						obj.data = newData;
					}
					else{
						obj.data = data;
					}
				}
			});
			series = jsonLst;
			configs.options.xAxis[0].data = xAxisData;//添加到参数配置中
			configs.options.legend = {
				orient : 'vertical',
				x : 'right',
				y: 'center',
		        data:legendData
		    };
		}
		//环形图
		if(type == 'ringPie'){
			if(jsonLst == null || jsonLst.length < 1){
				jsonLst = [{"name":"没有任何数据","value":0}];
			}
			else{
				$.each(jsonLst,function(n,value){
					configs.total += Number(value.value ? value.value : 0);
				});
			}
			configs.options['total'] = rendererZhMoney(parseFloat(configs.total).toFixed(2));//添加合计项
			series = [{
				radius : ['50%', '70%'],
	            type: 'pie',
	            name: configs.seriesNm,
	            data:jsonLst,
	            itemStyle:{ 
	                normal:{ 
	                      label:{ 
	                        show: true, 
	                        formatter: '{b} : ({d}%)' 
	                      }, 
	                      labelLine :{show:true} 
	                },
	                emphasis : {
	                    label : {
	                        show : true,
	                        position : 'center',
	                        textStyle : {
	                            fontSize : '30',
	                            fontWeight : 'bold'
	                        }
	                    }
	                } 
	            } 
	        }];
			configs.options.legend = {
		        orient : 'vertical',
		        x : 'left',
		        y: 'center',
		        data:legendData
		    };
		}
		
		configs.options.series = series;
		return configs;
	}
	
	//根据图表类型获取相应的设置信息
	$.fn.showecharts.options = {
			//标准饼图设置信息
			pie:function(configs){
				return {
						title : {
					        text: configs.title,
					        subtext:configs.subtext == undefined || configs.subtext == null ? '':configs.subtext,
					        x:'center'
					    },
					    tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					    },
					    toolbox: {
					        show : true,
					        feature : {//右上角工具栏
					            mark : {show: false},
					            dataView : {show: true, readOnly: false},
					            magicType : {
					                show: true, 
					                type: ['pie', 'funnel'],
					                option: {
					                    funnel: {
					                        x: '25%',
					                        width: '50%',
					                        funnelAlign: 'center',
					                        max: 100000000,
					                        itemStyle:{ 
					        	                normal:{ 
					        	                      label:{ 
					        	                        formatter: '{b} : {c} ' 
					        	                      } 
					        	                } 
					        	            }
					                    }
					                }
					            },
					            restore : {show: false,title:'刷新'},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : true//模块可拖拽
					};
			},
			//折线图设置信息
			line:function(configs){
				return {
						title : {
					        text: configs.title,
					        subtext:configs.subtext,
					        x:'center'
					    },
					    tooltip : {
					        trigger: 'axis'
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            mark : {show: false},
					            dataView : {show: false, readOnly: false},
					            magicType : {show: true, type: ['line', 'bar'/*, 'stack', 'tiled'*/]},
					            restore : {show: false,title:'刷新'},
					            saveAsImage : {show: true},
					            selfButtons:configs.selfButtons
					        }
					    },
					    calculable : true,
					    xAxis : [
					        {
					            type : 'category',
					            boundaryGap : false
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value'
					        }
					    ]
					};
			},
			//柱状图设置信息
			bar:function(configs){
				return {
						title : {
					        text: configs.title,
					        subtext:configs.subtext
					    },
					    tooltip : {
					        trigger: 'axis'
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            mark : {show: false},
					            dataView : {show: false, readOnly: true},
					            magicType : {show: true, type: ['line', 'bar']},
					            restore : {show: true,title:'刷新'},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : true,
					    xAxis : [
					        {
					            type : 'category'/*,
					            boundaryGap : false*/
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value'
					        }
					    ]
					};
			},
			//标准环形图设置信息
			ringPie:function(configs){
				return {
					    tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					    },
					    toolbox: {
					        show : true,
					        feature : {//右上角工具栏
					            mark : {show: false},
					            dataView : {show: true, readOnly: false},
					            magicType : {
					                show: true, 
					                type: ['pie', 'funnel'],
					                option: {
					                    funnel: {
					                        x: '25%',
					                        width: '50%',
					                        funnelAlign: 'center',
					                        max: 100000000
					                    }
					                }
					            },
					            restore : {show: true,title:'刷新'},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : true//模块可拖拽
					};
			}
	};
	//为showecharts添加常用方法
	$.fn.showecharts.method = {
		/**
		 * 重新加载图表
		 * id 图表ID
		 * params 重新加载时传递到后台的参数,格式：{key1,value1}
		 * 调用方式 ('#chart1').showecharts('reload',{key1,value1});
		 */
		reload:function(id,params){
			params = params || {};
			var config = echartsconfig[id];
			var chart = allecharts[id];
			if(config.params == undefined){
				config['params'] = params;
			}
		    $.extend(config.params,params);//追加参数
		    $.ajax({
				url:config.url ,type: 'post',async: true,timeout: 30000,
				data:config.json == true ? JSON.stringify(config.params) : config.params,
				success: function(jsonLst) {
					setSeries(config,jsonLst);
					chart.setOption(config.options);
					chart.restore();
				},
				error: function(xhr, textStatus, errorThrown) {
					alert(textStatus);
			    }
		    });
		},
		/**
		 * 点击事件
		 * id 图表ID
		 * fun 点击时执行的函数
		 * 调用方式 ('#chart1').showecharts('click',function(params){});
		 */
		click:function(id,fun){
			allecharts[id].on('click', function(param){
				fun.call(this,param);
			});
		}
	};

	 /*数字千分符*/  
	 function rendererZhMoney(v) {  
	     if(isNaN(v)){  
	         return v;  
	     }  
	     v = (Math.round((v - 0) * 100)) / 100;  
	     v = (v == Math.floor(v)) ? v + ".00" : ((v * 10 == Math.floor(v * 10)) ? v  
	             + "0" : v);  
	     v = String(v);  
	     var ps = v.split('.');  
	     var whole = ps[0];  
	     var sub = ps[1] ? '.' + ps[1] : '.00';  
	     var r = /(\d+)(\d{3})/;  
	     while (r.test(whole)) {  
	         whole = whole.replace(r, '$1' + ',' + '$2');  
	     }  
	     v = whole + sub;  
	       
	     return v;  
	 } 


})(jQuery);