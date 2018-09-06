<%@page import="bj.service.UserInfoService"%>
<%@page import="bj.entity.UserInfoEntity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("utf-8");

	String userName=request.getParameter("userName");
	String userPass=request.getParameter("userPass");
	String nickName=userName;
	String introduce=request.getParameter("introduce");
	UserInfoEntity entity=new UserInfoEntity(userName,userPass,nickName,introduce);
	UserInfoService service=new UserInfoService();
	int i=service.update(entity);
	if(i>0){
		response.sendRedirect(request.getContextPath()+ "/index.jsp");
	}else{
		response.sendRedirect(request.getContextPath()+"/logined/personal.jsp");	
	}
%>