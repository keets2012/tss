<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>课程设置</title>
  	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script type="text/javascript"> 
    </script>
</head>
<body> 

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 课程设置
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">
    <s:form action="course_%{id==null?'arrAdd':'arrEdit'}">
    <s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 岗位信息 </DIV>  -->
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100">课程名称</td>
                        <td>
                        	<s:select name="curriculumId" cssClass="SelectStyle"
                        		list="#curriculumList" listKey="id" listValue="name"
                        		headerKey="" headerValue="==请选择课程=="
                        	/>
                        </td>
                    </tr>
					<tr>
                        <td width="100">学期</td>
                       <td><select name="term" >
                       <option value="${year-1}-${year}-2">${year-1}-${year}-2</option>
                       <option value="${year}-${year+1}-1">${year}-${year+1}-1</option>
                       </select></td>
                    </tr>
                    <tr><td width="100">任课教师</td>
                        <td>
                        	<s:select name="userId" cssClass="SelectStyle"
                        		list="#userList" listKey="id" listValue="name"
                        		headerKey="" headerValue="==请选择教师=="
                        	/>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
         <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">                 
                </table>
            </div>
        </div>	
        
         <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
						<td width="100">上课班级</td>
                        <td>
                        	<s:select name="departmentIds" cssClass="SelectStyle"
                        		multiple="true" size="10" 
                        		list="#departmentList" listKey="id" listValue="name"
                        	/>
							按住Ctrl键可以多选或取消选择
                        </td>
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

</body>
</html>
