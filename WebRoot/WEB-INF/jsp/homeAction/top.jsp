<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>TopMenu</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
	<LINK href="${pageContext.request.contextPath}/style/blue/top.css" type=text/css rel=stylesheet>
	
<script type="text/JavaScript">  
    function timer(){  
        var time=new Date().toLocaleString();  
        document.getElementById("currentTime").innerHTML="当前时间：" + time;  
        //document.write("当前时间：" + time); //因为页面内容被覆盖，所以页面显示当前时间后不会更新  
    }  
    setInterval("timer()", 1000);  
</script> 
	<style type="text/css">
		#messageArea{
			color: white;
			font-size: 14px;
			font-weight: bold;
		}
	</style>
</head>

<body CLASS=PageBody leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
	
	<div id="Head1">
		<div id="Logo">
        	<!-- <iframe name="autoRefashion" src="" width="0" height="0"></iframe> -->
			<a id="msgLink" href="javascript:void(0)"></a>
            <font color="#0000CC" style="color:#F1F9FE; font-size:18px; font-family:Arial Black, Arial">TEACHING SUPPORT SYSTEM</font> 
			<!--<img border="0" src="css/blue/images/logo.png" />-->
        </div>
		<div id="Head1Right">
			<div id="Head1Right_UserName">
                <img border="0" width="13" height="14" src="${pageContext.request.contextPath}/style/images/top/user.gif" /> 您好，<b>${user.name }</b>
			</div>
			<div id="Head1Right_UserSetup">
            	<s:a action="user_myInfo" target="right"><img border="0" width="13" height="14" src="${pageContext.request.contextPath}/style/images/top/user_setup.gif" /> 个人设置</s:a>
			</div>
		</div>
        <div id="Head1Right_SystemButton">
            <a href="${pageContext.request.contextPath}/user_logout.action" target="_parent">
                <img width="78" height="20" alt="退出系统" src="${pageContext.request.contextPath}/style/blue/images/top/logout.gif" />
            </a>
        </div>
        <div id="Head1Right_Button">
            <a href="javascript:void(0)"><img width="65" height="20" alt="显示桌面" src="${pageContext.request.contextPath}/style/blue/images/top/desktop.gif" /></a>
        </div>
	</div>
    
    <div id="Head2">
        <div id="Head2_Awoke">
            <div id="currentTime">当前时间：时间载入中……</div> 
        </div>
        
        <div id="Head2_FunctionList" style="text-align: left">
        	<a href="javascript: window.parent.right.location.reload(true);">
        	 刷新</a>
        </div>
    </div>

</body>

</html>
