<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>��Ӫ����ϵͳ����ҳ��</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="���Ű���Ѷ,Sapphire,վȺ����ϵͳ,��վȺ">
	<meta http-equiv="description" content="���Ű���Ѷ��רҵ�ĵ��������ṩ�̣�Ϊ�������Ż���վ�ṩȫ��Ľ������">
	<link type="image/vnd.microsoft.icon" rel="shortcut icon" href="images/favicon.ico">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">123
	-->
  </head>
  
  <body onload="floatAD()">
  <div style="height: 300px; width: 300px;" >
   <div id="fly" style="position: absolute;left: 16px; top: 200px;">
   <img  src="<%=path%>/images/404.png">ϵͳ������δ֪��������ϵ��ؼ�����Ա��
   </div>
   </div>
  </body>
</html>
