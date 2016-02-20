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
														<td><input id="countSubmit" type="IMAGE"
								src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG"
								align="ABSMIDDLE" onclick="return check(this.form)" /></td>
						</tr>
					</table><br/><br/>
				</div>
			</div>
			
 <table cellpadding="0" cellspacing="0" border="0" border-width="1px 0 0 1px" class="TableStyle" align="center">
					<tr>
                        <td width="150"><div id="mytable" align="center">班级</div></td>             
                        <td width="300">${deptName}&nbsp;</td>
                    </tr>
                    <tr>
                        <td width="150"><div id="mytable" align="center">课程名称</div></td>             
                        <td width="300" >${courseName}&nbsp;</td>
                    </tr>
                    <tr>
                        <td width="150"><div id="mytable" align="center">任务名称</div></td>             
                        <td width="300">${taskName}&nbsp;</td>
                    </tr>
                    <tr>
                        <td><div id="mytable" align="center">应交数量</div></td>
                       <td width="300">${deptTotal}&nbsp;</td>
                    </tr>
					<tr>
                        <td><div id="mytable" align="center">已交数量</div></td>
                        <td width="300">${subTotal}&nbsp;</td>
                    </tr>
                    <tr>
                        <td><div id="mytable" align="center">未交数量</div></td>
                       <td width="300">${unSubTotal}&nbsp;</td>
                    </tr>
					<tr>
                        <td><div id="mytable" align="center">按时提交数量</div></td>
                       <td width="300">${onTimeSub}&nbsp;</td>
                    </tr>
                    <tr>
                        <td><div id="mytable" align="center">尚未提交名单</div></td>
                       <td width="300">
                       <s:iterator value="#unSubList">
                       ${loginName}&nbsp;${name}&nbsp;&nbsp;
                       </s:iterator></td>
                    </tr>
                    <tr>
                        <td><div id="mytable" align="center">未按时提交名单</div></td>
                       <td width="300">
                       <s:iterator value="#outDateSubList">
                       ${loginName}&nbsp;${name}&nbsp;&nbsp;
                       </s:iterator></td>
                    </tr>
                    
                </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside" style="font-size: 16px;font-family:微软雅黑;color: blue; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <a href='report_exportExcel.action?fileName=excel.xls&courseName=${courseName}&deptName=${deptName}&taskName=${taskName}&deptTotal=${deptTotal}&subTotal=${subTotal}&unSubTotal=${unSubTotal}&onTimeSub=${onTimeSub}'>导出excel </a>
        </div>
    </div>
   </div>
</s:form>
</body>
<script language="javascript" src="${pageContext.request.contextPath}/script/taskquery.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/jquery.form.min.js"></script>
</html>
