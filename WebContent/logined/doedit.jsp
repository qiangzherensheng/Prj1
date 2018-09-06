<%@page import="bj.entity.KnowledgeEntity"%>
<%@page import="bj.service.KnowledgeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	int id=Integer.valueOf(request.getParameter("id"));
	int cid=Integer.valueOf(request.getParameter("name"));
	String title=request.getParameter("title");
	String label=request.getParameter("label");
	String content=request.getParameter("content");
	//修改数据时，要先查询数据，将查询到的数据保存到实体对象中。需要修改的属性重新赋值，最后将实体对象传递给业务，由业务更新。
	KnowledgeService service=new KnowledgeService();
	KnowledgeEntity entity=service.findById(id);
	entity.setId(id);
	entity.setTitle(title);
	entity.setContent(content.replace(" ", "&nbsp;").replace("\r\n", "<br/>"));
	entity.setCid(cid);
	service.update(entity);
	response.sendRedirect(request.getContextPath()+"/logined/myknowledge.jsp");
	
	
%>