<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bj.util.Keys" %>
<%
 if(session.getAttribute(Keys.LOGINUSER)==null){
	 request.setAttribute("mssage", "请先登录之后在操作");
	 request.getRequestDispatcher("/login.jsp").forward(request,response);
 	 return ;
 }
%>