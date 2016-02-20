<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>课程列表</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script type="text/javascript">
    </script>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 历史数据管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
   <s:form id="form1" name="form1" action="course_del" method="post" enctype="multipart/form-data" theme="simple">  
        <div align="center"  style="width: 80%">  
   <table cellspacing="0" cellpadding="0" class="TableStyle"> 
      <tr class="TableDetail1 template">  
        <td colspan="99" align="center" >  
        <s:submit value="备份删除"></s:submit>  
        </td>  
      </tr>  
        </table>  
    </div>  
    </s:form>  
</div>
<div class="Description">
	说明：<br />
	1，备份删除功能为管理员使用。<br />
	2，管理员可以按照学期进行对系统进行整理。<br />
	3，管理员保存上一学期的历史数据后，删除历史数据。<br />
	4，保存的历史数据为课程分配，实验课表。<br />
</div>
</body>
</html>
