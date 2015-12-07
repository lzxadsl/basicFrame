<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
	<title>登录页</title>
	<script type="text/javascript" src="js/basic/jquery.min.js"></script>
    <style type="text/css">
        .login-center {
            width: 600px;
            margin-left:auto;
            margin-right:auto;
        }
        #loginContainer {
            margin-top: 3em;
        }
        .login-input {
            padding: 4px 6px;
            font-size: 14px;
            vertical-align: middle;
        }
    </style>
</head>

<body>
    <div id="loginContainer" class="login-center">
		<div style="text-align: center;">
            <h2>工作流引擎Activiti项目</h2>
            <h3>作者：lzx</h3>
            <h3>当前版本：V1.0</h3>
            <c:if test="${not empty error}">
	            <h3 id="error" style="color:red;">${error}</h3>
	        </c:if>
	        <c:if test="${not empty isempty}">
	            <h3 id="error" style="color:red;">用户名或密码不能为空！</h3>
	        </c:if>
	        
		</div>
		<hr/>
		<!-- <form action="shiro/login.htm" method="post"> -->
		<form:form action="shiro/doLogin.htm" modelAttribute="loginCommand">
			<table>
				<tr>
					<td width="200" style="text-align: right;">用户名：</td>
					<td><input id="username" name="username" value="${login.username}" class="login-input"/></td>
				</tr>
				<tr>
					<td style="text-align: right;">密码：</td>
					<td><input id="userPwd" name="password" value="${login.password}" type="password" class="login-input"/></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="right">
						<button type="submit">登  录</button>
					</td>
				</tr>
			</table>
		</form:form>
		<!-- </form> -->
    </div>
</body>
</html>
