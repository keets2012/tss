<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>历史实验</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<script type="text/javascript">
	$(function () {
		// alert() ;
		$('.expTime').each(function(){
			var old = $(this).text() ;
			var week = old.split('w')[0] ;
			var day = old.split('w')[1] ;
			var num = old.split('w')[2] ;
			var result = '第'+week+'周,周 '+day+' ,'+(num*2-1)+'、'+(num*2)+'节' ;
			 $(this).text(result) ;
		});
	});
</script>
<body>

 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 历史实验
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<s:form action="exp_oldList">
<div id="MainArea">

    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="150px">实验项目名称</td>
				<td width="150px">实验项目课程</td>
				<td width="50px">实验时间</td>
				<td width="100px">学期</td>
				<td width="50px">实验教师</td>
				<td width="100px">实验机房</td>
				<td width="150px">实验班级</td>
				<td width="150px">实验说明</td>				
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="experimentList">
        
        <s:iterator value="recordList">
			<tr class="TableDetail1 template">
				<td>${projectName}&nbsp;</td>
				<td>${courseName}&nbsp;</td>
				<td class="expTime" align="center">${expTime}</td>
				<td>${expTerm}&nbsp;</td>
				<td>${userName}&nbsp;</td>
				<td>${labName}&nbsp;</td>
				<td>${deptName}&nbsp;</td>
				<td>${description}&nbsp;</td>
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
</s:form>
<br/>
    <!-- 分页信息 -->
<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>  

</body>
</html>
