<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>共享资料列表</title>
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

$("#uploadTime",$(".TableStyle>tbody>tr")).each(function(i,item){
$(item).text(new Date($(item).text().trim()).Format("yyyy-MM-dd"));
});
});
</script>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 共享资料管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<s:form action="share_listPart">

<div id="MainArea">
<!--其他操作-->
			<div id="TableTail">
				<div id="TableTail_inside">
					<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left">
						<tr valign=bottom>
							<td></td>
							<td>
								
								<s:select name="curriculumId" cssClass="SelectStyle"
                        		list="#curriculumList" listKey="id" listValue="name"
                        		headerKey="0" headerValue="==请选择课程=="
                        	/>
								
								<s:select name="asc" list="#{false:'按时间降序', true:'按时间升序'}"/>
								
								<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="ABSMIDDLE"/>
							</td>
						</tr>
					</table>
				</div>
			</div>
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="150px">文件名</td>
				<td width="150px">所属课程</td>
				<td width="150px">上传者</td>
				<td width="150px">上传时间</td>
				<td width="200px">共享资料说明</td>
				<td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="shareList">
        
        <s:iterator value="recordList">
			<tr class="TableDetail1 template">
	
				<!--  <td><s:property value="name"/></td>-->
				<td>${name}&nbsp;</td>
				<td>${course.curriculum.name}&nbsp;</td>
				<td>${user.name}&nbsp;</td>
				<td id="uploadTime">${uploadTime}&nbsp;</td>
				<td>${description}&nbsp;</td>
			<td><a href='share_filedownload.action?fileName=<s:property value="filePath"/>'>下载&nbsp;</a>
			<s:if test='#session.user.hasPrivilegeByUrl("/forumManage_list")'>
			<s:a action="share_deleteAll?id=%{id}" onclick="return window.confirm('您确定要修改吗？')">删除</s:a>
			</s:if></td>	
			</tr>
		</s:iterator>	
			
        </tbody>
    </table>
    
</div>
</s:form>

<br/>
<!--分页信息-->
<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
</body>
</html>
