<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>实验项目列表</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 实验项目管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<s:form action="project_myList">
<div id="MainArea">
	<!--其他操作-->
			<div id="TableTail">
				<div id="TableTail_inside">
					<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left">
						<tr valign=bottom>
							<td>
								
								<s:select name="curriculumId" cssClass="SelectStyle"
                        		list="#curriculumList" listKey="id" listValue="name"
                        		headerKey="0" headerValue="==请选择课程=="
                        	/></td>
                        	<td>	<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="ABSMIDDLE"/>&nbsp;&nbsp;&nbsp;&nbsp;
							</td><td colspan="4" style="float: right;"> <s:a action="project_projectInput"><div  style="font-size: 16px;font-family:微软雅黑;padding-right: 20px; " align="right">批量导入</div></s:a></td>
							</tr></table></div></div>
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="150px">实验项目编号</td>
            	<td width="150px">实验项目名称</td>
				<td width="150px">实验项目课程</td>
				<td width="100px">实验项目课时</td>
				<td width="200px">实验项目说明</td>
				
				<td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="projectList">
        
        <s:iterator value="recordList">
			<tr class="TableDetail1 template">
				<td>${projectNo}&nbsp;</td>
				<td>${name}&nbsp;</td>
				<td>${curriculum.name}&nbsp;</td>
				<td>${total}&nbsp;</td>
				<td>${description}&nbsp;</td>
				
				<td>
					<s:a action="project_delete?id=%{id}" onclick="return window.confirm('这将删除所有的下级部门，您确定要删除吗？')">删除</s:a>
					<s:a action="project_editUI?id=%{id}">
						修改
					</s:a>
					&nbsp;
				</td>
			</tr>
		</s:iterator>	
			
        </tbody>
    </table>

  
    
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="project_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
        </div>
    </div>
</div>

</s:form>
    <!-- 分页信息 -->
<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>  
</body>
</html>
