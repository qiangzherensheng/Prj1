<%@page import="bj.entity.CatagoryEntity"%>
<%@page import="java.util.List"%>
<%@page import="bj.service.CatagoryService"%>
<%@page import="bj.service.KnowledgeService"%>
<%@page import="bj.entity.KnowledgeEntity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@include file="sessionvalid.jsp" %>
   <%--  <% 
    //获取要编辑的知识的id
    	int id=Integer.valueOf(request.getParameter("id"));
    KnowledgeService knowService =new KnowledgeService();
    KnowledgeEntity knowledgeEntity=knowService.findById(id);
    %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>知识库—编辑知识</title>
</head>
<body class="index">
<%@include file="header.jsp"%>
<div class="space3"></div>
<div  class="container">
 <div id="left"><%@include file="../left.jsp" %>
 </div>
 <div id="right">
 <form name="form1" method="post" action="${pageContext.request.contextPath}/logined/DoEditServlet">
 <input type="hidden" name="id" value="${entity.id }"/>
  <table width="900" border="1" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="50" colspan="2" style="border-bottom:1px solid #03C;">添加知识<span>${requestScope.message}</span></td>
    </tr>
    <tr>
      <td width="200" height="50" align="right">知识类别：</td>
      <td height="60"><label for="select"></label>
        <select name="name" id="select" class="addk" >
       <%--  <%
        	//获取知识类别
        	CatagoryService service=new CatagoryService();
    		  List<CatagoryEntity> list=service.findAll();
    		  if(list!=null&&list.size()>0){
    			  for(int i=0;i<list.size();i++){
    				  
        	%> --%>
        	<c:if test="${not empty list }">
        	<c:forEach items="${list }" var="item">
        	<option
        		value="${item.id }" >${item.name }</option>
        	<%-- <% 
        		if(list.get(i).getId()==knowledgeEntity.getCid()){out.print("selected='selected'");}%>
        	 value="<%=list.get(i).getId()%>"><%=list.get(i).getName() %>	
        	<%	
        		}
        	}
        %> --%>
               </c:forEach>
         </c:if> 
         
        
      </select> 
          
       <label for="textfield2"></label></td>
    </tr>
    <tr>
      <td height="50" width="200" align="right"><p>知识标题：</p></td>
      <td height="60"><input type="text" name="title" class="addk" id="textfield2" value="${entity.title}" /></td>
    </tr>
    <tr>
      <td height="50" align="right">知识标签：</td>
      <td height="60"><input type="text" name="label" class="addk" id="textfield3" value="${entity.label}"/></td>
    </tr>
    <tr>
      <td height="50" align="right">知识内容：</td>
      <td><label for="textarea"></label>
        <textarea name="content" class="addk1" id="textarea" cols="45" rows="5">${entity.content}</textarea></td>
    </tr>
    <tr>
      <td height="60" colspan="2" align="center"><input type="submit" name="button"  class="submit" id="button" value="提交"></td>
    </tr>
    <tr>
      <td height="50" colspan="2">&nbsp;</td>
    </tr>
  </table>
</form>
 </div>
</div>
<div class="space3"></div>
<%@include file="../footer.jsp" %>
 
</body>
</html>