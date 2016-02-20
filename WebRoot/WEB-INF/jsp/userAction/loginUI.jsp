<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>Teaching Support System</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.5/themes/icon.css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.5/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/common_operation.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/common.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/userForm.css">
	<style type="text/css">
	.style1{


	}
		#content{
			position: absolute;
			top: 40px;
			right: 45px;
		}
		.button{
			position: absolute;
			bottom: 70px;
			left: 160px;
		}
		
	</style>
		<script type="text/javascript">
		$(function(){
		$("#loginName").focus();
			$("#res").click(function(){
			    $("#loginName").val("") ;
				$("#password").val("") ;
				$("#loginName").focus() ;
			}); 
		});
		
		// 在被嵌套时就刷新上级窗口
		if(window.parent != window){
			window.parent.location.reload(true);
		}
	</script>
</head> 
<body>
<div class="container" >
<div style="margin: 10% auto;">
<img src="${pageContext.request.contextPath}/style/images/titlebg.png" alt="index">
	<section id="content" style="width: 350px;" >
		<s:fielderror /> 
		<s:form action="user_login">
			<h1 >登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</h1> 
			<div>
				<input type="text" id="loginName" name="loginName" placeholder="登录名" required="" id="username" />
			</div>
			<div>&nbsp;</div>
			<div>
				<input type="password" id="password" name="password" placeholder="密码" required="" id="password" />
			</div>
			<div>
				<input type="submit" value="登录"/>
				<input type="button" id="res" name="res" value="重置" />
			</div>
		</s:form>
	</section>
	<div class="button" >
		<a href="#"><span id="downloadHint">下载安卓客户端</span></a>
	</div>
	      <div style="background-color:#BEBEBE;text-align: center;top: -21px;font-family: 宋体;"><span class="style1">版权所有 Copyright &copy; 2015 南京工程学院 <br>
          学校地址：南京市江宁科学园弘景大道1号 | 邮编:211167 电话总机人工服务:025-58003988 </span></div>
</div>
<!-- <div id="CopyRight" style="color: blue;">&copy; 2015 版权所有 njit</div> -->


</div>
</body>
</html>
