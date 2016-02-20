<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>实验报告列表</title>
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

$("#handleDate",$(".TableStyle>tbody>tr")).each(function(i,item){
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 实验报告管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<s:form action="report_listPart">
		<div id="MainArea">
			<!--其他操作-->
			<div id="TableTail">
				<div id="TableTail_inside">
					<table border="0" cellspacing="0" cellpadding="0" height="100%"
						align="left">
						<tr valign=bottom>
							<td><s:select name="curriculumId" cssClass="SelectStyle"
									list="#curriculumList" listKey="id" listValue="name"
									headerKey="0" headerValue="==请选择课程==" />
							</td>
							<td><input type="IMAGE"
								src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG"
								align="ABSMIDDLE" /></td>
						</tr>
					</table>
				</div>
			</div>
			<table cellspacing="0" cellpadding="0" class="TableStyle">

				<!-- 表头-->
				<thead>
					<tr align=center valign=middle id=TableTitle>
						<td width="150px">实验报告名称</td>
						<td width="150px">所属教师</td>
						<td width="150px">所属课程</td>
						<td width="100px">所属任务</td>
						<td width="150px">提交时间</td>
						<td width="100px" align="center">报告得分</td>

						<td>相关操作</td>
					</tr>
				</thead>

				<!--显示数据列表-->
				<tbody id="TableData" class="dataContainer" datakey="taskList">

					<s:iterator value="recordList">
						<tr class="TableDetail1 template">

							<td>${name}&nbsp;</td>
							<td>${task.user.name}&nbsp;</td>
							<td>${task.course.curriculum.name}&nbsp;</td>
							<td>${task.name}&nbsp;</td>
							<td id="handleDate">${handleDate}&nbsp;</td>
							<s:if test="score!=0">
							<td align="center">${score}&nbsp;</td></s:if>
							<s:else><td align="center">尚未批阅</td></s:else>
							
							<td><a
								href='report_filedownload.action?fileName=<s:property value="filePath"/>'>下载附件&nbsp;</a>
								<s:a action="report_delete?id=%{id}"
									onclick="return window.confirm('您确定要删除吗？')">删除</s:a> <s:a
									action="report_editUI?id=%{id}">
						修改
					</s:a> &nbsp;</td>
						</tr>
					</s:iterator>

				</tbody>
			</table>

			<!-- 其他功能超链接 -->
			<div id="TableTail">
				<div id="TableTail_inside"></div>
			</div>
		</div>
	</s:form>
<!--说明-->

<br/>
    <!-- 分页信息 -->
<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>  
<br/>	
<div id="Description"> 
	说明：<br />
	1，报告上传的附件不允许修改，如有上传失误，请删除后重新上传。<br />
	2，提交的报告可以对名称和说明进行修改。<br />
	3，报告上传为压缩的ZIP格式或者，doc格式，文件大小在10MB之内。
</div>

</body>
</html>
