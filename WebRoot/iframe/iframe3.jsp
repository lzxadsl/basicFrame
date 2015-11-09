<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
$(function(){
	//$.setCookie('lzx','123456789');
	$('#req').on('click',function(){
		$.ajax({
			url:'user/ajaxmsg.htm',
			type:'post',
			dataType:'json',
			data:{},
			success:function(data){
				//$.getCookie('lzx');
				console.log(data);
			},
			error:function(data){
				console.log(data.responseText);
			}
		});
		/* $.post('user/ajaxmsg.htm',{},function(data,status){
			console.log(data);
			console.log(status);
		}); */
	});
	
});
</script>
<div style="padding:10px;border:solid 1px red;text-align: center;">
	<a class="btn btn-default" id="req">请求</a>
</div>