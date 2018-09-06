<%@page import="bj.entity.CatagoryEntity"%>
<%@page import="java.util.List"%>
<%@page import="bj.service.CatagoryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@include file="sessionvalid.jsp" %>
    <%-- <% 
     	CatagoryService service=new CatagoryService();
    	List<CatagoryEntity> list=service.findAll();
    %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>知识库-添加知识</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="index">
<%@include file="header.jsp"%>
<div class="space3"></div>
<div  class="container">
 <div id="left"><%@include file="../left.jsp" %></div>
 <div id="right">
 <form name="form1" enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/DoAddKnowledge">
  <table width="900" border="1" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="50" colspan="2" style="border-bottom:1px solid #03C;">添加知识<span>${empty requestScope.message?'':requestScope.message}</span></td>
    </tr>
    <tr>
      <td width="200" height="50" align="right">知识类别：</td>
      <td height="60"><label for="select"></label>
        <select name="name" id="select" class="addk" >
       <%--  <%
        	if(list!=null && list.size()>0){
        		for(int i=0;i<list.size();i++){
        	%> --%>
        	<c:if test="${not empty list }">
        	<c:forEach items="${list }" var="item">
        	<option value="${item.id }">${item.name}</option>	
        	</c:forEach>
        	</c:if>
        <%-- 	<%	
        		}
        	}
        %> --%>
      </select>        <label for="textfield2"></label></td>
    </tr>
    <tr>
      <td height="50" width="200" align="right"><p>知识标题：</p></td>
      <td height="60"><input type="text" name="title" class="addk" id="textfield2" value="${empty requestScope.title?'':requestScope.title}" /></td>
    </tr>
    <tr>
      <td height="50" align="right">知识标签：</td>
      <td height="60"><input type="text" name="label" class="addk" id="textfield3" value="${empty requestScope.label?'':requestScope.label}"/></td>
    </tr>
    <tr>
      <td height="50" align="right">知识内容：</td>
      <td><label for="textarea"></label>
        <textarea name="content" class="addk1" id="textarea" cols="45" rows="5">${empty requestScope.content?'':requestScope.content}</textarea></td>
    </tr>
      <tr>
      <td height="50" align="right">附件：</td>
      <td height="60"><input type="file" name="label" class="addk" id="textfield3"/></td>
    </tr>
    <tr>
    
    <tr>
      <td height="60" colspan="2" align="center"><input type="submit" name="button"  class="submit" id="button" value="提交"></td>
    </tr>
    <tr>
      <td height="50" colspan="2">&nbsp;</td>
    </tr>
  </table>
</form>
<%@include file="../footer.jsp" %>
</div>
</div>
</body>
</html>