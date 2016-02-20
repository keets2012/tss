<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>课程分配</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script type="text/javascript">
    </script>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 课程分配管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<s:form action="course_arrListPart">
<div id="MainArea">
<!--其他操作-->
			<div id="TableTail">
				<div id="TableTail_inside">
					<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left">
						<tr valign=bottom>                        	
                        	<!--  <td><select name="termName">
                        	<s:iterator id="li" value="termList" var="temp">
                        	<option value="temp">
                                  <s:property />                             
                          </option> </s:iterator></select></td>-->
                        	<td>		
								<s:select name="curriculumId" cssClass="SelectStyle"
                        		list="#curriculumList" listKey="id" listValue="name"
                        		headerKey="0" headerValue="==请选择课程=="
                        	/></td>
                        	<td>	<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="ABSMIDDLE"/>&nbsp;&nbsp;&nbsp;&nbsp;
							</td><td colspan="4" style="float: right;"> <s:a action="course_courseInput"><div  style="font-size: 16px;font-family:微软雅黑;padding-right: 20px; " align="right">批量导入</div></s:a></td>
							</tr></table></div></div>
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="150px">课程编号</td>
            	<td width="100px">课程名称</td>
            	<td width="100px">课程学分</td>
                <td width="200px">课程说明</td>
                <td width="100px">任课教师</td>
                <td width="150px">学期</td>
                <td width="200px">上课班级</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="courseList">
        
        <s:iterator value="recordList">
			<tr class="TableDetail1 template">
				<td>${curriculum.courseNo}&nbsp;</td>
				<td>${curriculum.name}&nbsp;</td>
				<td>${curriculum.credit}&nbsp;</td>
				<td>${curriculum.description}&nbsp;</td>
				<td>${user.name}&nbsp;</td>
				<td>${term}&nbsp;</td>
                	<td><s:iterator value="depts">
                		${name}&nbsp;
                	</s:iterator></td>
				<td>
					<s:a action="course_delete?id=%{id}" onclick="return confirm('确定要删除吗？')">删除</s:a>
					<s:a action="course_arrEditUI?id=%{id}">修改</s:a>
				</td>
			</tr>
        </s:iterator>

        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
		<div id="TableTail_inside">

			<s:a action="course_arrAddUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
       
        </div>
    </div>
</div>
</s:form>
    <!-- 分页信息 -->
<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %> 
</body>
</html>
