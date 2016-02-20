<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!--<%@ taglib uri="/struts-tags" prefix="s" %>-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>宿舍之家</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="css/style.css" />
  <script type="text/javascript" src="js/modernizr-1.5.min.js"></script>
  <script LANGUAGE="JavaScript">
	function dosomething(){
	    document.LoginForm.action="login.action";
		document.LoginForm.submit();
	}
	function gettime(){
		document.getElementById("t").innerHTML = new Date().toLocaleString()+'  星期'+'日一二三四五六'.charAt(new Date().getDay());
		window.setTimeout("gettime()",1000);
		}
		window.onload = gettime;
</script>
</head>

<body>
  <div id="container">
    <img src="images/sun.png" alt="sunshine" />
    <div id="main">
      <header>
        <div id="logo">
          <div id="logo_text">
            <h1><a href="index.jsp">宿舍<span class="logo_colour">之家</span></a></h1>
            <h2>the home of our dorm</h2>
          </div>
          <div id="logo_text2" >
            <h3>time</h3>
            <h4><div id="t"></div></h4>
          </div>
        </div>
        <nav>
          <ul class="sf-menu" id="nav">
            <li><a href="index.jsp">主页</a></li>
            <li><a href="#">个人中心</a>
            	<ul>
                <li><a href="searchMail.action">我的信件</a></li>
                <li><a href="searchProperty.action">我的财物</a></li>
                <li><a href="searchVisitor.action">我的访客</a></li>
                <li><a href="searchReturn.action">我的通知</a></li>
                </ul>
            </li>
            <li><a href="#">我的宿舍</a>
            	<ul>
                <li><a href="searchFix.action">报修管理</a></li>
                <li><a href="searchDormmate.action">宿舍信息</a></li>
                </ul>
            </li>
            <li><a href="#">信息维护</a>
            	<ul>
                <li><a href="showMessage.action">查看个人信息</a></li>
                <li><a href="cPassword.action">修改用户密码</a></li>
                </ul>
            </li>
            <li><a href="#">交流互动</a>
              <ul>
                <li><a href="searchBoard.action">公告信息</a></li>
              </ul>
            </li>
          </ul>
        </nav>
      </header>
      <div id="site_content">
        <div id="sidebar_container">
          <div class="sidebar">
          <s:if test="%{#session.student == null}">
            <h4>Login here...</h4>
            <form name="LoginForm" method="post">
              <p><span>用户名：</span><input type="text" name="stu.sid"/></p>
              <p><span>密   码：</span><input type="password" name="stu.password"/></p>
              <p  style="padding-top: 15px"><span>&nbsp;</span><input type="button" value="Login"  onclick="dosomething();"/>
              </p>
          </form>
          </s:if>
          <s:else>
          	<h4><font size="3px">当前用户：<s:property value="#session.student.studentname"/><br>您的身份：[学生]</font></h4>
          	<a href="logout.action">退出登录</a>
          </s:else>
          </div>
          <div class="sidebar">
            <h4>学期日历</h4>
            <div id="div">
				<jsp:include page="rili.jsp"></jsp:include>
				</div>
          </div>
          <div class="sidebar">
          		<p><img src="images/lianjie.jpg"/></p>
		        <p><select name="select" onchange="javascript:window.open(this.options[this.selectedIndex].value)">
		              <option>--------教育网链接--------</option>
		              <option value="http://www.moe.edu.cn/">中华人民共和国教育部</option>
		              <option value="http://www.chinaedu.edu.cn/">中国教育信息网</option>
		              <option value="http://www.edu.cn/">中国教育和科研计算机网</option>
		              <option value="http://www.eol.cn/">中国教育在线</option>
		              <option value="http://www.ec.js.edu.cn/">江苏教育</option>
		              <option value="http://www.jsbys.com.cn:88/">江苏毕业生就业网</option>
		              </select>
		         </p>
		         <p><br />
		              <select name="select2"  onchange="javascript:window.open(this.options[this.selectedIndex].value)">
		                <option selected="selected">--------教学单位网站--------</option>
		              <option value="http://jx.njit.edu.cn/">机械工程学院</option>
		              <option value="http://ce.njit.edu.cn/">计算机工程学院</option>
		              <option value="http://zdh.njit.edu.cn/">自动化学院</option>
		              <option value="http://english.njit.edu.cn/">外国语学院</option>
		              <option value="http://mse.njit.edu.cn/">材料工程学院</option>
		              <option value="http://dlx.njit.edu.cn/">电力工程学院</option>
		              <option value="http://emlab.njit.edu.cn:8888/nd/">能源与动力工程学院</option>
		              <option value="http://sce.njit.edu.cn/">通信工程学院</option>
		              <option value="http://sem.njit.edu.cn/">经济与管理学院</option>
		              <option value="http://skb.njit.edu.cn/">人文与社会科学学院</option>
		              <option value="http://jcb1.njit.edu.cn/">数理部</option>
		              <option value="http://pe.njit.edu.cn/">体育部</option>
		              <option value="http://sj.njit.edu.cn/">艺术与设计学院</option>
		              <option value="http://jz.njit.edu.cn/">建筑工程学院</option>
		              <option value="http://clx.njit.edu.cn/">汽车与轨道交通学院</option>
		              <option value="http://hj.njit.edu.cn/">环境工程学院</option>
		              <option value="http://emlab.njit.edu.cn:8888/gyzx/">工业中心</option>
		              <option value="http://fz.njit.edu.cn/">电力仿真与控制工程中心</option>
		              <option value="http://www.njgcxy.com.cn/">继续教育学院</option>
		              <option value="http://kn.njit.edu.cn/">康尼学院</option>
		              </select>
		          </p>
          </div>
        </div>
        <div id="content">
          <h1><span class="logo_colour">Welcome to our dorm</span></h1>
          <img style="margin: 0px 0 10px 20px; float: right;padding: 10px; border: 1px solid #ddd;" src="images/photo.png" alt="photo" />
          <p> 这里是我们的宿舍， <strong>欢迎光临</strong>.</p>
           
        </div>
      </div>
      <footer>
		<p align="center">学校地址：南京市江宁科学园弘景大道1号 | 邮编:211167 电话总机人工服务:025-58003988 </p>
  		<p align="center">版权所有 Copyright &copy;  2014 南京工程学院 苏ICP05007116 | <a href="Manager/login.jsp"><font size="5px" color="red">宿舍管理中心</font></a></p></div>
	 </footer>
    </div>
  <div id="grass">
  </div>
  <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript" src="js/jquery.easing-sooper.js"></script>
  <script type="text/javascript" src="js/jquery.sooperfish.js"></script>
  <script type="text/javascript">
    $(document).ready(function() {
      $('ul.sf-menu').sooperfish();
    });
  </script>
</body>
</html>
