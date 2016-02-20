<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>报告列表</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<script type="text/javascript">
$(function(){ Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

$("#assignDate",$(".TableStyle>tbody>tr")).each(function(i,item){
//$(item).text(new Date($(item).text().trim()).Format("yyyy-MM-dd"));
var temp=$(item).text().split(' ');
//alert(temp[0]);
$(item).text(temp[0]);
});
$("#dueDate",$(".TableStyle>tbody>tr")).each(function(i,item){
//$(item).text(new Date($(item).text().trim()).Format("yyyy-MM-dd"));
var temp=$(item).text().split(' ');
$(item).text(temp[0]);
});
$("#handleDate",$(".TableStyle>tbody>tr")).each(function(i,item){
//$(item).text(new Date($(item).text().trim()).Format("yyyy-MM-dd"));
var temp=$(item).text().split(' ');
$(item).text(temp[0]);
});
});
</script>
</head>

<body>

	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				实验报告
			</div>
			<div id="Title_End"></div>
		</div>
	</div>


	<s:form action="report_myCount">
		<div id="MainArea">
			<!--其他操作-->
			<div id="TableTail">
				<div id="TableTail_inside">
					<table border="0" cellspacing="0" cellpadding="0" height="100%"
						align="left">
						<tr valign=bottom>
							<td><s:select id="clickCourse" name="courseId"
									cssClass="SelectStyle" list="#courseList" listKey="id"
									listValue="curriculum.name" headerKey="0"
									headerValue="==请选择课程==" />
							</td>
							<td><select id="taskName" class="SelectStyle" name="taskId">
									<option value="0">==请选择项目==</option>
							</select></td>
							<td><select id="deptName" class="SelectStyle" name="departmentId">
							<option value="0">==请选择班级==</option>
	                   		</select>
							</td>


							<td><input  type="IMAGE"
								src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG"
								align="ABSMIDDLE"  /></td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</div>
			</div>
			<table cellspacing="0" cellpadding="0" class="TableStyle">

				<!-- 表头-->
				<thead>
					<tr align=center valign=middle id=TableTitle>
						<td width="100">报告名称</td>
						<td width="100">学生姓名</td>
						<td width="100">下发日期</td>
						<td width="100">上交日期</td>
						<td width="100">截止日期</td>
						<td width="100">所属班级</td>
						<td width="100">所属课程</td>
						<td width="100">所属任务</td>
						<td width="100">得分</td>
						<td>相关操作</td>
					</tr>
				</thead>

				<!--显示数据列表-->
				<tbody id="TableData" class="dataContainer" datakey="recordList">

					<s:iterator value="recordList">
						<tr class="TableDetail1 template">
							<td><input type="checkbox" checked="checked" name="downloadFile" value="<s:property value="filePath"/>"/>${name}</td>
							<td>${user.name}&nbsp;</td>
							<td id="assignDate">${task.assignDate}&nbsp;</td>
							<td id="dueDate">${task.dueDate}&nbsp;</td>
							<td id="handleDate">${handleDate}&nbsp;</td>
							<td>${user.department.name}&nbsp;</td>
							<td>${task.course.curriculum.name}&nbsp;</td>
							<td>${task.name}&nbsp;</td>
							<s:if test="score!=0">
							<td align="center">${score}&nbsp;</td></s:if>
							<s:else><td align="center">尚未批阅</td></s:else>
							<td>
							
							<a href='report_fileRead.action?reportId=<s:property value="id"/>&fileName=<s:property value="filePath"/>'>预览</a>
							<a href='report_filedownload.action?fileName=<s:property value="filePath"/>'>下载&nbsp;</a>
							</td>
						</tr>
					</s:iterator>

				</tbody>
			</table>

			<!-- 其他功能超链接 -->
			<div id="TableTail">
				<div id="TableTail_inside">
				   <input type="button" value="全选" title="选择下载的文件" 
				   onclick="SelectAll()" />
   					<input type="button" value="反选" title="反向选择下载的文件" onclick="TurnOver(this.form)" > 
   					<input type="button" value="批量下载" title="选择下载的文件" onclick="DownLodSelected()" > 
   	
   </div>
			</div>
		</div>
	</s:form>

	<!-- 分页信息 -->
	<%@ include file="/WEB-INF/jsp/public/pageView.jspf"%>



</body>
<script language="javascript"
	src="${pageContext.request.contextPath}/script/taskquery.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/script/jquery.form.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/large.js"></script>
</html>
