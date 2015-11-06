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
    <!-- <script type="text/javascript" src="js/basic/jquery.min.js"></script> -->
    <script type="text/javascript" src="js/mq/amq_jquery_adapter.js"></script>
    <script type="text/javascript" src="js/mq/amq.js"></script>
    <script type="text/javascript">
	    
	    $(function(){
	    	var amq = org.activemq.Amq;
	    	amq.init({ 
	           uri: 'amq', 
	           logging: true, 
	           timeout: 1, 
	           clientId:(new Date()).getTime().toString()//clientId是为了在一个浏览器的多个页面中使用ActiveMQ Ajax. 
	        });
	        var msg = "<msg type='common'>" 
	              +  "<id>msg1</id>"
	              +  "<content>This is test content</content>"
	              + "</msg>";
            $("#send").click(function(){
          	  console.log('wbdj');
		      amq.sendMessage("topic://logQueue", msg);
		    });
	    });
    </script>
  </head>
  
  <body>
	<button class="btn btn-default" id="send">提交</button>
  </body>
</html>