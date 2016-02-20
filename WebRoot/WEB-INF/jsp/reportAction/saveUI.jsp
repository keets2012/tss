<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>上传报告</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>

<!-- 标题显示 --> 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 实验报告信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>

    <s:form action="report_add?taskId=%{thisId}" method="post" theme="simple" enctype="multipart/form-data">
        <s:hidden name="id"></s:hidden>
        
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 部门信息 </DIV>  -->
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                   
                
                    <tr><td>实验报告名称</td>
                        <td><s:textfield  name="name" cssClass="InputStyle"/> *</td>
                    </tr>
                    <tr><td>所属课程</td>
                        <td><s:textfield  name="courseName"  readonly="true"  cssClass="InputStyle"/></td>
                    </tr>
                    <tr><td>所属任务</td>
                        <td><s:textfield  name="taskName" readonly="true"  cssClass="InputStyle"/></td>
                    </tr>
                    <tr><td>实验教师</td>
                        <td><s:textfield  name="userName" readonly="true" cssClass="InputStyle"/></td>
                    </tr>
                     <tr> <td>选择文件</td>  
                <td><input type="file" name="upload" /></td>  
                    </tr>

                    <tr><td>实验报告说明</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="submit" value='提交' />
            <input type="reset" value='重置' />
        </div>
    </s:form>
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
