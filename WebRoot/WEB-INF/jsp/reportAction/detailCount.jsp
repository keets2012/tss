<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>报告统计</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 实验报告
        </div>
        <div id="Title_End"></div>
    </div>
</div>


<s:form action="report_detailCount">
<div id="MainArea">
				<!--其他操作-->
			<div id="TableTail">
				<div id="TableTail_inside">
					<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left">
						<tr valign=bottom>
							<td>
								
								<s:select id="clickCourse" name="courseId" cssClass="SelectStyle"
                        		list="#courseList" listKey="id" listValue="curriculum.name"
                        		headerKey="" headerValue="==请选择课程=="
                        	/></td>
                        	<td>
								
							<select id="taskName" class="SelectStyle" name="taskId">
							<option value="0">==请选择项目==</option>
	                   		</select>
                        	</td>
                        	<td>
								
							<select id="deptName" class="SelectStyle" name="departmentId">
							<option value="0">==请选择班级==</option>
	                   		</select>
                        	</td>		
							<td>	<input  id="countSubmit" type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="ABSMIDDLE"/>
							</td>
						</tr>
					</table>
				</div>
			</div>
 
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
           
        </div>
    </div>
   </div>
</s:form>

</body>
<script language="javascript" src="${pageContext.request.contextPath}/script/taskquery.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/jquery.form.min.js"></script>
</html>
