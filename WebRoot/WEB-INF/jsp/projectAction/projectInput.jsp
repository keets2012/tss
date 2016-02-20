<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>导入课程分配</title>
     <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/mydiv.css" />
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 实验室管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>
 <br/>
<div align="center"  style="width: 80%">  
   <table cellspacing="0" cellpadding="0" >
      <tr >  
     <td  align="center" style="color: #003111"> <div  style="font-size: 16px;font-family:微软雅黑; ">  
        实验项目数据模版&nbsp;&nbsp;&nbsp;&nbsp;<a  href='project_filedownload.action?fileName=projectTemplate.xls'>下载</a>
        </div></td>
      </tr>  
        </table>
        <br/><br/>  
    </div>
    <s:form id="form" name="form1" action="project_projectLogincalInput" method="post" enctype="multipart/form-data" theme="simple">  
        <div align="center"  style="width: 80%;font-size: 18px;">  
   <table cellspacing="0" cellpadding="0" class="TableStyle">
      <tr class="TableDetail1 template">  
        <td colspan="30" align="center" ><div  style="font-size: 16px;font-family:微软雅黑; ">批量导入实验项目</div></td>  
      </tr>  
      <tr class="TableDetail1 template">  
        <td colspan="30" align="center"> 
          <input type="file" name="upload"  size="80px"/>  
        </td>  
      </tr>  
      <tr class="TableDetail1 template">  
        <td colspan="99" align="center" >  
        <s:submit value="导入"></s:submit>  
        <s:reset value="重置"></s:reset>  
        </td>  
      </tr>  
        </table>  
    </div>  
    <br/>
    <br/>
    <br/>
    </s:form>  
        </body>
</html>
