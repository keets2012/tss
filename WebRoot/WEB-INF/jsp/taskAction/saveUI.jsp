<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>实验任务设置</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
<script type="text/javascript">
$(function(){ 
Date.prototype.Format = function (fmt) { //author: meizz 
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
			//$("#selectDate").val(new Date($("#hiDate").val()).Format("yyyy-MM-dd"));			
        //   alert( $("#hiDate").val());
           var temp=$("#hiDate").val();
           var temp1=temp.split(" ");
           if(temp=='')
           {
          	 $("#selectDate").val(temp1[0]);
           }
           else{
           	$("#selectDate").val("20"+temp1[0]);
           }
            $("#selectDate").datepicker({//添加日期选择功能  
            numberOfMonths:1,//显示几个月  
            showButtonPanel:true,//是否显示按钮面板  
            dateFormat: 'yy-mm-dd',//日期格式  
            clearText:"清除",//清除日期的按钮名称  
            closeText:"关闭",//关闭选择框的按钮名称  
            yearSuffix: '年', //年的后缀  
            showMonthAfterYear:true,//是否把月放在年的后面  
            monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
            dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
            dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
            dayNamesMin: ['日','一','二','三','四','五','六'],  
            onSelect: function(selectedDate) {//选择日期后执行的操作  
             // alert(selectedDate);  
               $("#hiDate").val($("#selectDate").val());
            }  
            }); 
            
            
 
            /* $("#selectDate").change(function(){debugger;
			   alert("11");
			   $("#hiDate").val($("#selectDate").val());
			}); */
            });

             
</script>
</head>
<body>

<!-- 标题显示 --> 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 实验任务信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>

    <s:form action="task_%{id == null ? 'add' : 'edit'}" id="myForm">
        <s:hidden name="id"></s:hidden>
        
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 部门信息 </DIV>  -->
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                   
                    <tr><td>实验任务名称</td>
                        <td><s:textfield  name="name" cssClass="InputStyle"/> *</td>
                    </tr>
                    <tr>
                    <td>实验任务课程</td><td>
                        	<s:select id="selectCourse" name="courseId" cssClass="SelectStyle"
                        		list="#courseList" listKey="id" listValue="curriculum.name"
                        		headerKey="" headerValue="==请选择课程=="
                        	/> *
                        </td></tr>
                     <tr><td>应交日期</td>
                     
                        <td><div style="display: none;">
                        <s:textfield name="dueDate" id="hiDate" /></div>              
                        <input name="duedate" id="selectDate" />
                        *</td>
                    </tr>
				
                    <tr><td>任务说明</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 选择班级</div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
						<td width="100">班级</td>
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

<!--说明-->	
<div id="Description"> 
	说明：<br />
	1，教师下发任务，任务即为教师自己所对应的课程，任教的班级。<br />
	2，任务可对应多个班级。<br />
	3，应交日期格式为：YYYY-MM-DD HH:mm:ss  其中时间为可选项。
</div>
<script>
           
</script>
</body>
</html>
