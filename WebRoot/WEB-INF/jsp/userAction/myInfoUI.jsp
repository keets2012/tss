<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
	<title>个人信息</title>
       <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
    <script type="text/javascript">
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head" ></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 个人信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>
   
<!--显示表单内容-->
<div id=MainArea>
 <s:form action="user_editMyInfo">
  <s:hidden name="id"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 个人信息 </DIV>  -->
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
           
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<tr>
                        <td width="150"><div id="mytable">用户ID（登录名）</div></td>             
                        <td width="300">${loginName}&nbsp;</td>
                    </tr>
                    <tr>
                        <td><div id="mytable">部门（班级）</div></td>
                       <td width="300">${dept}&nbsp;</td>
                    </tr>
					<tr>
                        <td><div id="mytable">姓名</div></td>
                        <td><s:textfield  name="name" cssClass="InputStyle"/> *</td>
                    </tr>
                    <tr>
                        <td><div id="mytable">性别</div></td>
                        <td><s:textfield  name="gender" cssClass="InputStyle"/> *</td>
                    </tr>
										<tr>
                        <td><div id="mytable">电话号码</div></td>
                        <td><s:textfield  name="phoneNumber" cssClass="InputStyle"/> *</td>
                    </tr>
                    <tr>
                        <td><div id="mytable">邮箱</div></td>
                         <td><s:textfield  name="email" cssClass="InputStyle"/> *</td>
                    </tr>
                    <tr>
                        <td><div id="mytable">个人说明</div></td>
                          <td><s:textfield  name="description" cssClass="InputStyle"/> *</td>
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
<div class="Description">
	规则：<br />
	1，修改完成后，修改信息在下次登录生效。<br />
</div>

</body>
</html>
