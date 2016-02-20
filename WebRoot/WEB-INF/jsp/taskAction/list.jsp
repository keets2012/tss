<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>实验任务列表</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
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

$("#date",$(".TableStyle>tbody>tr")).each(function(i,item){
var temp=$(item).text().split(' ');
$(item).text(temp[0]);
//$(item).text(new Date($(item).text().trim()).Format("yyyy-MM-dd"));
});
$("#assignDate",$(".TableStyle>tbody>tr")).each(function(i,item){
var temp=$(item).text().split(' ');
$(item).text(temp[0]);
//$(item).text(new Date($(item).text().trim()).Format("yyyy-MM-dd"));
});
});
</script>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 实验任务管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<s:form action="task_listPart">
<div id="MainArea">
<!--其他操作-->
			<div id="TableTail">
				<div id="TableTail_inside">
					<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left">
						<tr valign=bottom>
							<td>
								
								<s:select name="courseId" cssClass="SelectStyle"
                        		list="#courseList" listKey="id" listValue="curriculum.name"
                        		headerKey="0" headerValue="==请选择课程=="
                        	/></td>														
                        	<td><input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="ABSMIDDLE"/>
							</td>
							</tr></table></div></div>
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="150px">实验任务名称</td>
				<td width="150px">实验任务课程</td>
				<td width="100px">下发时间</td>
				<td width="50px">下发教师</td>
				<td width="150px" align="center" style="text-align: center;">应交日期</td>
				<td width="150px">提交班级</td>
				<td width="100px">任务说明</td>
				
				<td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="taskList">
        
        <s:iterator value="recordList">
			<tr class="TableDetail1 template">
	
				<td>${name}&nbsp;</td>
				<td>${course.curriculum.name}&nbsp;</td>
				<td id="assignDate">${assignDate}&nbsp;</td>
				<td style="text-align: center;">${user.name}&nbsp;</td>
				<td id="date" style="text-align: center;">${dueDate}&nbsp;</td>
				<td>
                	<s:iterator value="depts">
                		${name}
                	</s:iterator>
                </td>
				<td>${description}&nbsp;</td>
				
				<td>
					<s:a action="task_delete?id=%{id}" onclick="return window.confirm('您确定要删除吗？')">删除</s:a>
					<s:a action="task_editUI?id=%{id}">
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
            <s:a action="task_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
        </div>
    </div>
</div>
</s:form>
<br/>
    <!-- 分页信息 -->
<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>  

</body>
</html>
