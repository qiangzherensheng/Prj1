<%@page import="bj.entity.UserInfoEntity"%>
<%@page import="bj.util.Keys" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="left_container">
<% 
  	UserInfoEntity entity1=(UserInfoEntity)session.getAttribute(Keys.LOGINUSER);
	if(entity1!=null){
%>
 <div id="space"></div>
<table width="200" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" align="center">&nbsp;</td>
  </tr>
  <tr>
    <td align="center"><img id="myheaderimage" src="${pageContext.request.contextPath}/headerimages/<%= entity1.getHeaderImage() %>" width="100" height="100"/></td>
  </tr>
  <tr>
    <td align="center" class="nickname"><%= entity1.getNickName() %></td>
  </tr>
  <tr>
    <td height="30" align="left">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" align="center"><a href="${pageContext.request.contextPath}/logined/MyknowledgeServlet" class="left_nav">我的知识</a></td>
  </tr>
  <tr>
    <td height="30" align="center"><a href="${pageContext.request.contextPath}/logined/myfavorite.jsp" class="left_nav">我的收藏</a></td>
  </tr>
  <tr>
    <td height="30" align="center" ><a href="${pageContext.request.contextPath}/logined/personal.jsp?id=<%=entity1.getId() %>" class="left_nav">个人设置</a></td>
  </tr>
   <tr> 
    <td height="30" align="center" ><a href="${pageContext.request.contextPath}/logined/upload/setheaderimage1.jsp?id=<%=entity1.getId() %>" class="left_nav">头像设置</a></td>
  </tr>
  <tr>
    <td height="30" align="center" ><a href="${pageContext.request.contextPath}/logined/AddknowledgesServlet" class="left_nav">发表知识</a></td>
  </tr>
  <tr>
    <td height="30" align="center" ><a href="${pageContext.request.contextPath}/logined/logout.jsp" class="left_nav">退出系统</a></td>
  </tr>
</table>
<% }else{ %>
登录后显示内容
<% } %>

</div>

</body>
</html>