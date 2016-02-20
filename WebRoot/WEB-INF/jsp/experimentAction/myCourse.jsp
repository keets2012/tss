<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>实验课表</title>
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 实验课表
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<s:form action="exp_myCoursePart">
<div id="MainArea">
<!--其他操作-->
			<div id="TableTail">
				<div id="TableTail_inside">
					<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left">
						<tr valign=bottom>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        	<td>第&nbsp;<s:select headerKey="0" headerValue="==请选择=="  name="weekTime" list="#{1:' 1 ',2:' 2 ',3:' 3 ',4:' 4 ',5:' 5 ',6:' 6 ',7:'7',8:'8',9:'9',10:'10',11:'11',12:'12',13:'13',14:'14',15:'15',16:'16',17:'17',18:'18',18:'19',20:'20'}"/>周
                   &nbsp;</td>
                        	<td><input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="ABSMIDDLE"/>
							</td>
							</tr></table></div></div>
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="50px">实验时间</td>
            	<td width="100px">实验学期</td>
            	<td width="150px">实验项目名称</td>
				<td width="150px">实验项目课程</td>				
				<td width="50px">实验教师</td>
				<td width="100px">实验机房</td>
				<td width="150px">实验班级</td>
				<td width="200px">实验说明</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="experimentList">
        
        <s:iterator value="recordList">
			<tr class="TableDetail1 template">
				<td class="expTime" align="center">${expTime}</td>
				<td>${expTerm}&nbsp;</td>
				<td>${project.name}&nbsp;</td>
				<td>${project.curriculum.name}&nbsp;</td>				
				<td>${user.name}&nbsp;</td>
				<td>${lab.name}&nbsp;</td>
				<td>${dept.name}&nbsp;</td>
				<td>${description}&nbsp;</td>
			</tr>
		</s:iterator>	
			
        </tbody>
    </table>
        <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside" style="font-size: 16px;font-family:微软雅黑;color: blue; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <a href='exp_exportExcel.action?fileName=excel.xls&type=single'>导出EXCEL </a>
        </div>
    </div>
</div>
</s:form>
<br/>
    <!-- 分页信息 -->
<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>  
</body>
</html>
