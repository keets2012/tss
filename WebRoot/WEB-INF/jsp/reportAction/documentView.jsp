<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
  <%@page import="java.net.*"%>  
<%  
    String swfFilePath=request.getSession().getAttribute("swfpath").toString();
	String fName = URLDecoder.decode(swfFilePath, "UTF-8"); 
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>   
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
 <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.js"></script>
 <script language="javascript" src="${pageContext.request.contextPath}/script/flexpaper_flash.js"></script>
 <script language="javascript" src="${pageContext.request.contextPath}/script/flexpaper_flash_debug.js"></script>
<style type="text/css" media="screen">   
            html, body  { height:100%; }  
            body { margin:0; padding:0; overflow:auto; }     
            #flashContent { display:none; }  
        </style>   
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>  
<title>文档在线预览系统</title>  
</head>  
<body>  
        <div style="position:absolute;left:50px;top:10px;">  
            <a id="viewerPlaceHolder" style="width:820px;height:650px;display:block"></a>                
            <script type="text/javascript">   
                var fp = new FlexPaperViewer(     
                         'FlexPaperViewer',  
                         'viewerPlaceHolder', { config : {  
                         SwfFile : decodeURI('<%=fName%>'),  
                         Scale : 0.6,   
                         ZoomTransition : 'easeOut',  
                         ZoomTime : 0.5,  
                         ZoomInterval : 0.2,  
                         FitPageOnLoad : true,  
                         FitWidthOnLoad : false,  
                         FullScreenAsMaxWindow : false,  
                         ProgressiveLoading : false,  
                         MinZoomSize : 0.2,  
                         MaxZoomSize : 5,  
                         SearchMatchAll : false,  
                         InitViewMode : 'SinglePage',  
                           
                         ViewModeToolsVisible : true,  
                         ZoomToolsVisible : true,  
                         NavToolsVisible : true,  
                         CursorToolsVisible : true,  
                         SearchToolsVisible : true,  
                          
                         localeChain: 'zh_CN'  
                         }});  
            </script>              
        </div>  
        <s:form action="report_mark?id=%{id}">
        <s:hidden name="reportId"></s:hidden>
        <div style="position:absolute;left:900px;top:50px;">得分：<s:textfield  name="score" style="width:40px;"></s:textfield>&nbsp;&nbsp;<input type="submit" value="提交" /></div>
<div style="position:absolute;left:993px;top:80px;">
<input type="button" value="返回" onclick="javascript:history.go(-1);"/>
</div>
</s:form>
</body>  
</html>  