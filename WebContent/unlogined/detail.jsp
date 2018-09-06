<%@page import="bj.service.AttachService"%>
<%@page import="bj.entity.AttachEntity"%>
<%@page import="bj.util.MyDateFormat"%>
<%@page import="bj.entity.KnowledgeEntity"%>
<%@page import="bj.service.KnowledgeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>知识库-添加知识</title>
</head>
<body class="index">
<%@include file="../logined/header.jsp"%>
<div class="space3"></div>
<div  class="container">
 <div id="left"><%@include file="../left.jsp" %></div>
 <div id="right">
 <% 
 	KnowledgeService service=new KnowledgeService();
 	KnowledgeEntity en=service.findById(Integer.parseInt(request.getParameter("id")));
 	if(en==null){
 %>
 <table width="900" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="200" align="center">真不巧，该网页走丢了</td>
  </tr>
</table>
<% }else{
	en.setReadCount(en.getReadCount()+1);
		service.update(en);
	%>
	<% AttachService attachService=new AttachService();
	    AttachEntity attachEntity=attachService.findByKid(en);
	%>
<table width="900" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="50" width="30">&nbsp;</td>
    <td>标题：<%=en.getTitle() %></td>
  </tr>
  <tr>
    <td height="30">&nbsp;</td>
    <td>标签：<%=en.getLabel() %></td>
  </tr>
  <tr>
    <td height="40" >&nbsp;</td>
    <td><%=MyDateFormat.formatDate(en.getPubDate(), "yyyy-MM-dd HH:mm:ss") %>&nbsp;<%=en.getReadCount() %>人阅读，评论（0）<a href="${pageContext.request.contextPath}/logined/myfavorite.jsp">收藏</a></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><hr size="1" noshade="noshade" style="color:gray;"/></td>
  </tr>
  <tr>
    <td height="40">&nbsp;</td>
    <td>本文章已经收录于<%=en.getCatagoryEntity().getName() %></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><hr size="1" noshade="noshade" style="color:gray;"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><%=en.getState()==1?en.getStateContent():en.getContent() %></td>
  </tr>
  <%
  	if(attachEntity!=null){
  	%>
  	  <tr>
    <td>&nbsp;</td>
    <td><a href="${pageContext.request.contextPath}/DownLoadServlet?id=<%=attachEntity.getId() %>">附件</a></td>
  </tr>
  	<%
  	}
    %>
 
  <tr>
    <td>&nbsp;</td>
    <td align="center"><img src="${pageContext.request.contextPath}/images/ding.png ">&nbsp;<img src="${pageContext.request.contextPath}/images/cai.png"></td>
  </tr>
</table>
<% } %>
 </div>
</div>
<div class="space3"></div>
<%@include file="../footer.jsp" %>
</body>
</html>