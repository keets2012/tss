<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>实验项目设置</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>

<!-- 标题显示 --> 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 实验项目信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>

    <s:form action="project_%{id == null ? 'add' : 'edit'}">
        <s:hidden name="id"></s:hidden>
        
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 部门信息 </DIV>  -->
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                   <tr><td>实验项目编号</td>
                        <td><s:textfield  name="projectNo" cssClass="InputStyle"/> *</td>
                    </tr>
                    <tr><td>实验项目名称</td>
                        <td><s:textfield  name="name" cssClass="InputStyle"/> *</td>
                    </tr>
                    <tr>
                    <td>实验项目课程</td><td>
                        	<s:select name="curriculumId" cssClass="SelectStyle"
                        		list="#curriculumList" listKey="id" listValue="name"
                        		headerKey="" headerValue="==请选择课程=="
                        	/> *
                        </td></tr>
                     <tr><td>实验项目课时</td>
                        <td><s:textfield  name="total" cssClass="InputStyle"/> *</td>
                    </tr>

                    <tr><td>实验项目说明</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<!--说明-->	
<div id="Description"> 
	说明：<br />
	1，实验项目对应大纲规定课程所需的实验。<br />
	2，实验项目是具体实验安排的依据。<br />
	3，实验报告也是一句相应的实验项目。
</div>

</body>
</html>
