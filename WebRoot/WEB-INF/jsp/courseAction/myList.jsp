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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 课程分配
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="150px">课程编号</td>
            	<td width="150px">课程名称</td>
            	<td width="100px">课程学分</td>
                <td width="200px">课程说明</td>
                <td width="100px">任课教师</td>
                <td width="100px">学期</td>
                <td width="200px">上课班级</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="courseList">
        
        <s:iterator value="#courseList">
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
			</tr>
        </s:iterator>

        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
		<div id="TableTail_inside">
		
        </div>
    </div>
</div>
</body>
</html>
