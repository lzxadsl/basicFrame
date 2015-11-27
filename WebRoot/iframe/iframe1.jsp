<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <script type="text/javascript" src="iframe/iframe.js"></script>
    <script type="text/javascript">
    	$(function(){
    		console.log($('#ifr',parent.document).attr('src'));
    		$.fn.ifra = function(){
    			alert('ddd');
    		};
    		parent.mainFun('iframe1');
    		$('#req').on('click',function(){
    			$.ajax({
					url:'user/save.htm',
					type:'post',
					dataType:'json',
					data:{},
					success:function(res){
						console.log(res);
					},
					error:function(res){
					}
				});
    		});
    		$('#input').val('dd');
    	});
    </script>
  </head>
  
  <body>
	<span>我是iframe1</span>
	${msg}
	<a class="btn btn-default" id="req">新增</a>
	<input id="input" value="" type="text"/>
  </body>
</html>