<%@page import= "bj.util.Keys"  %>
<%@page import="bj.entity.UserInfoEntity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<style>

</style>
</head>
<body>
<div class="header">
<div class="head_container">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="70" height="50" align="left"><img src="${pageContext.request.contextPath}/images/logo.png" width="99" height="35"></td>
    <td width="30"><a href="${pageContext.request.contextPath}/IndexServlet" class="nav">首页</a></td>
    <%
    //获取用户是否登录
    UserInfoEntity entity=(UserInfoEntity)session.getAttribute(Keys.LOGINUSER);
    if(entity!=null){
    %>
    <td width="60"><a href="${pageContext.request.contextPath}/logined/MyknowledgeServlet" class="nav">我的知识库</a></td>
    <td width="60"><a href="${pageContext.request.contextPath}/logined/myfavorite.jsp" class="nav">我的收藏</a></td>
    <td width="60"><a href="${pageContext.request.contextPath}/logined/personal.jsp" class="nav">个人设置</a></td>
    <td width="50"><a href="${pageContext.request.contextPath}/logined/logout.jsp" class="nav">退出</a></td>
    <%
    }else{
    %>
    <td width="25" align="right" ><a href="${pageContext.request.contextPath}/login.jsp" class="nav">登录</a></td>
    <% 
    }
    %>
    <td width="50" align="right" ><a href="${pageContext.request.contextPath}/register.jsp" class="nav">注册</a></td>
    <td width="50" align="right" ><input type="text" name="textfield" id="textfield2"class="head_input" ></td>
    <td width="50"><input type="image"  src="${pageContext.request.contextPath}/images/search.png" width="33" height="33"></td>
  </tr>
</table>
</div>
</div>
<div id="head_ads">
知识库 你身边的百科全书
</div>
</body>
</html>