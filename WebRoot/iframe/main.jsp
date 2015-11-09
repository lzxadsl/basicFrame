<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <!--支持 浏览器内核 webkit、ie-comp IE兼容、ie-stand IE标准 -->
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <!--开启IE兼容模式  -->
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <!--移动设备支持， width=device-width 自适应宽度,initial-scale=1.0 初始缩放比例为1:1,user-scalable 用户是否可以手动缩放-->
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-transform"/>
    <script type="text/javascript" src="js/import.inc.js"></script>
    <script type="text/javascript">
    	$(function(){

    		$('#ifr').load(function(){
    			if (!$(this).readyState || $(this).readyState == "complete"){
    				//console.log($('#ifr').contents().find(':root').html());
		            //alert('加载成功，地址:'+window.history);
		            
		        }  
   			});
    		$('#btn').on('click',function(){
    			var src = '';
    			if($('#ifr').attr('src') == 'iframe/iframe1.jsp'){
    				//src = 'http://www.baidu.com';
    				src = 'iframe/iframe2.jsp';
    			}else{
    				src = 'iframe/iframe1.jsp';
    			}
    			
    			document.getElementById('ifr').src = src;
    			var state = {
					url: src
				};
 				
				window.history.pushState(state, document.title,'tesst');
    			//$('#ifr').attr('src',src);
    		});
    		
    	});
    	function mainFun(arg){
    		//alert('我被'+arg+'调用了');
    	}
    </script>
  </head>
  
  <body>
	<h1>IFRAME测试</h1>
	<button id="btn">却换</button>
	<iframe width="1024px" height="400px" src="iframe/iframe1.jsp" id="ifr" frameborder="0"></iframe>
	<jsp:include page="./iframe3.jsp"></jsp:include>
  </body>
</html>