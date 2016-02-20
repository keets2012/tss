<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>提交报告列表</title>
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

$("#assignDate",$(".TableStyle>tbody>tr")).each(function(i,item){
//$(item).text(new Date($(item).text().trim()).Format("yyyy-MM-dd"));
var temp=$(item).text().split(' ');
$(item).text(temp[0]);
});
$("#dueDate",$(".TableStyle>tbody>tr")).each(function(i,item){
//$(item).text(new Date($(item).text().trim()).Format("yyyy-MM-dd"));
var temp=$(item).text().split(' ');
$(item).text(temp[0]);
});
});
</script>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 提交报告管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="100px">实验任务名称</td>
				<td width="100px">实验任务课程</td>
				<td width="100px">下发时间</td>
				<td width="150px">下发教师</td>
				<td width="150px">应交日期</td>
				<td width="150px">提交班级</td>
				<td width="100px">任务说明</td>
				
				<td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="taskList">
        
        <s:iterator value="#taskList">
			<tr class="TableDetail1 template">
	
				<td>${name}&nbsp;</td>
				<td>${course.curriculum.name}&nbsp;</td>
				<td id="assignDate">${assignDate}&nbsp;</td>
				<td>${user.name}&nbsp;</td>
				<td id="dueDate">${dueDate}&nbsp;</td>
				<td>
                	<s:iterator value="depts">
                		${name}
                	</s:iterator>
                </td>
				<td>${description}&nbsp;</td>
				
				<td>
					<s:a action="report_addUI?taskId=%{id}">提交报告</s:a>
					&nbsp;
				</td>
			</tr>
		</s:iterator>	
			
        </tbody>
    </table>
    			<!-- 其他功能超链接 -->
			<div id="TableTail">
				<div id="TableTail_inside"></div>
			</div>
</div>

<!--说明-->	
<div id="Description"> 
	说明：<br />
	1，报告上传的附件不允许修改，如有上传失误，请删除后重新上传。<br />
	2，提交的报告可以对名称和说明进行修改。<br />
	3，报告上传为压缩的ZIP格式或者，doc格式，文件大小在10MB之内。
</div>

</body>
</html>
