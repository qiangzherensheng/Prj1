<%@page import="bj.entity.CatagoryEntity"%>
<%@page import="bj.service.CatagoryService"%>
<%@page import="bj.util.MyDateFormat"%>
<%@page import="bj.entity.KnowledgeEntity"%>
<%@page import="java.util.List"%>
<%@page import="bj.service.KnowledgeService"%>
<%@page import="bj.service.UserInfoService"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="bj.util.Keys" %>
    <%@page import="java.net.URLDecoder" %>
    <%
    	Cookie[] cookies=request.getCookies();
    	if(cookies!=null){
    		for(int i=0;i<cookies.length;i++){
    			if(cookies[i].getName().equals(Keys.REMEMBER_LOGEN_NAME)){
    				String userName=URLDecoder.decode(cookies[i].getValue());
    				UserInfoService service=new UserInfoService();
    				UserInfoEntity entity=service.findByUserName(userName);
    				session.setAttribute(Keys.LOGINUSER, entity);
    				break;
    			}
     		}
    	}
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
</head>

<body class="index">
<%@include file="logined/header.jsp"%>
<div class="space3"></div>
<div  class="container">

 <div id="left"><%@include file="../left.jsp" %></div>
 <div id="right">
<table width="100%" border="0" align="center"  cellpadding="0" cellspacing="0" style="border-bottom:2px solid #069; font-size:20px; font-family:微软雅黑;">
<tr>
<td height=60px>我的知识</td>
</tr>
<c:forEach items="${keys}" var="item">
</table>
<table width="800" border="1" align="center" cellpadding="0" cellspacing="0">
</table>
<table width="900" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="200" rowspan="2" align="center" ><img src="${pageContext.request.contextPath }/headerimages/${item.userInfoEntity.headerImage}" width="100" height="100"/></td>
    <td height="40"><a href="${pageContext.request.contextPath}/unlogined/detail.jsp?id=${item.id}">${item.title}</a></td>
  </tr>
  <tr>
    <td height=40 width="900" style="border-bottom:2px solid #00F;"><span><f:formatDate value="${item.pubDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>&nbsp;
    <span >由</span>&nbsp;<span>${item.userInfoEntity.nickName}</span>&nbsp;
    <span>发布到</span>&nbsp;<span>${item.catagoryEntity.name}</span>&nbsp;
    </td>
  </tr>
</table>
</c:forEach>
<!-- 我的知识条目-->
 </div>
 </div>
<div class="space3"></div>
<%@include file="../footer.jsp" %>
</body>
</html>