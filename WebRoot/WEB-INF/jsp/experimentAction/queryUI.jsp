<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>预约实验时间</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<style>
	#experimentList td{
		border-left: 1px solid  #d2d2d2;
		border-right: 1px solid  #d2d2d2;
		border-top: 1px solid  #d2d2d2;
	}
</style>
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

<!-- 标题显示 --> 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 选择实验时间
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!-- 显示已经预约的实验安排 -->
<div >
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="150px">实验项目名称</td>
				<td width="150px">实验项目课程</td>
				<td width="200px">实验时间</td>
				<td width="50px">实验教师</td>
				<td width="100px">实验机房</td>
				<td width="150px">实验班级</td>
				<td width="100px">实验说明</td>
				<td>相关操作</td>
            </tr>
        </thead>
        
        
		<!--显示数据列表-->
        <tbody id="experimentList" class="dataContainer">
			
        </tbody>
    </table>

</div> 
    <!-- 其他功能超链接 -->
    <div id="TableTail">
	    <div id=PageSelectorBar>
		<div id=PageSelectorMemo>
			页次：<span id="currentPage"></span>/<span id="pageCount"></span>页 &nbsp;
			每页显示：<span id="pageSize"></span>条 &nbsp;
			总记录数：<span id="recordCount"></span>条
		</div>
    	<div id=PageSelectorSelectorArea>
	
		<span id="pagenation">		
		</span>
		转到：
		<select id="pageSelect">
		</select>
		
	</div>
    </div>
</div>
<!--显示表单内容-->
<div id=MainArea>
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            </tr>
        </thead>


    </table>
        <s:hidden name="id"></s:hidden>
        
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 部门信息 </DIV>  -->
        </div>
        <s:form id="expForm">
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                   <tr><td>实验课程</td>
                      <td>
                        	<s:select id="selectCourse" name="courseId" cssClass="SelectStyle"
                        		list="#courseList" listKey="id" listValue="curriculum.name"
                        		headerKey="0" headerValue="==请选择实验课程=="
                        	/> *
                        </td>
                    </tr>
                   <tr><td>实验项目名称</td>
                      <td>
                        <select id="projectName" class="SelectStyle" name="projectId">
							<option value="0">==请选择项目==</option>
	                   </select>
                        </td>
                    </tr>
                     <tr><td>实验时间</td>
                     <td>第<s:select name="weekTime" list="#{1:' 1 ',2:' 2 ',3:' 3 ',4:' 4 ',5:' 5 ',6:' 6 ',7:'7',8:'8',9:'9',10:'10',11:'11',12:'12',13:'13',14:'14',15:'15',16:'16',17:'17',18:'18',18:'19',20:'20'}"/>周
                   &nbsp;<s:select name="dayTime" list="#{1:'周一', 2:'周二', 3:'周三', 4:'周四', 5:'周五'}"/>
                   &nbsp;<s:select name="turnTime" list="#{1:'上午1，2节', 2:'上午3，4节', 3:'下午5，6节', 4:'下午7，8节', 5:'晚上9，10节'}"/>
                   &nbsp;<input type="button" id="queryLabBtn"  value="查询"/>       
                   </td>                 
                   </tr>
                   <tr><td>实验机房</td><td id="availLabTd">
	                   <select id="availLab" class="SelectStyle" name="labId">
							<option value="0">==请选择机房==</option>
	                   </select>
                   </td></tr>
					 <tr><td>实验班级</td>
                      <td>
                        <select id="deptName" class="SelectStyle" name="departmentId">
							<option value="0">==请选择班级==</option>
	                   </select>
                        </td>
                    </tr>
                    <tr><td>实验项目说明</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div> <br/><br/>

          <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input id="commitExp"   type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
        
      </s:form>
    
</div>

<div class="Description">
	说明：<br />
	1，查看自己的课表。<br />
	2，如有特殊情况，可与其他教师进行调课。<br />
	3，调整实验，直接删除后重新添加。
</div>

</body>
<script language="javascript" src="${pageContext.request.contextPath}/script/expquery.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/jquery.form.min.js"></script>
</html>
