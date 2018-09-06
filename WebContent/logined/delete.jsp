<%@page import="bj.service.KnowledgeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
//删除指定文件
	int id=Integer.valueOf(request.getParameter("id"));
  	KnowledgeService service=new KnowledgeService();
  	int i=service.delete(id);
  	response.sendRedirect(request.getContextPath()+"/logined/myknowledge.jsp");
  %>  