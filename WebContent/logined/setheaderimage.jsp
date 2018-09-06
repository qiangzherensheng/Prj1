<%@page import="bj.entity.CatagoryEntity"%>
<%@page import="java.util.List"%>
<%@page import="bj.service.CatagoryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="sessionvalid.jsp" %>
    <% 
     	CatagoryService service=new CatagoryService();
    	List<CatagoryEntity> list=service.findAll();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>知识库-设置头像</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="index">
<%@include file="header.jsp"%>
<div class="space3"></div>
<div  class="container">
 <div id="left"><%@include file="../left.jsp" %></div>
 <div id="right">
 <form name="form1" enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/SetHeaderImage ">
  <table width="900" border="1" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="50" colspan="2" style="border-bottom:1px solid #03C;">上传头像<span></span></td>
    </tr>
      <tr>
      <td height="50" align="right">上传头像</td>
      <td height="60"><input type="file" name="headerImage" class="addk" id="textfield3"/></td>
    </tr>
    <tr>
      <td height="80">&nbsp;</td>
    </tr>
    <tr>
      <td height="60" colspan="2" align="center"><input type="submit" class="submit" id="button" ></td>
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