<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>运营管理系统出错页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="厦门安科讯,Sapphire,站群管理系统,网站群">
	<meta http-equiv="description" content="厦门安科讯是专业的电子政务提供商，为政府的门户网站提供全面的解决方案">
	<link type="image/vnd.microsoft.icon" rel="shortcut icon" href="images/favicon.ico">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <div style="height: 300px; width: 300px;" align="center" >
   <div id="fly" style="position: absolute;left: 16px; top: 200px;" align="center" >
		<img  src="<%=path%>/images/500.png">系统出现了未知错误，请联系相关技术人员！
   </div>
   </div>
  </body>
</html>
