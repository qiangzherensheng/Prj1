<%@page import="bj.entity.OptionlogEntity"%>
<%@page import="bj.service.KnowledgeService"%>
<%@page import="bj.entity.KnowledgeEntity"%>
<%@page import="bj.entity.UserInfoEntity"%>
<%@page import="bj.util.Keys"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@page import="java.util.Date"%>  
<%
	request.setCharacterEncoding("utf-8");
	String title=request.getParameter("title");
	String content=request.getParameter("content");
	Date pubDate=new Date();
	int cid=Integer.valueOf(request.getParameter("name"));
	
	String label=request.getParameter("label");
	int readCount=0;
	int ding=0;
	int cai=0;
	int uid=((UserInfoEntity)session.getAttribute(Keys.LOGINUSER)).getId();
	int state=0;
	int stateUid=0;
	Date stateDate=pubDate;
	String stateContent="";
	//用于回显
	request.setAttribute("title", title);
	request.setAttribute("content", content);
	request.setAttribute("label", label);
	//数据验证
	if(title==null||title.length()==0){
		request.setAttribute("message", "请填写标题");
		request.getRequestDispatcher("addknowledge.jsp").forward(request, response);
		return ;
	}
	if(label==null||label.length()==0){
		request.setAttribute("message", "请填写标签");
		request.getRequestDispatcher("addknowledge.jsp").forward(request, response);
		return ;
	}
	if(content==null || content.length()==0){
		request.setAttribute("message", "请填写内容");
		request.getRequestDispatcher("addknowledge.jsp").forward(request, response);
		return;
	}
	//封装并添加
	KnowledgeEntity knowledgeEntity=new KnowledgeEntity();
	knowledgeEntity.setCai(cai);
	knowledgeEntity.setCid(cid);
	knowledgeEntity.setContent(content.replace(" ", "&nbsp").replace("\r\n", "<br/>"));
	knowledgeEntity.setDing(ding);
	knowledgeEntity.setLabel(label);
	knowledgeEntity.setPubDate(pubDate);
	knowledgeEntity.setReadCount(readCount);
	knowledgeEntity.setState(state);
	knowledgeEntity.setStateContent(stateContent);
	knowledgeEntity.setStateDate(stateDate);
	knowledgeEntity.setStateUid(stateUid);
	knowledgeEntity.setTitle(title);
	knowledgeEntity.setUid(uid);
	OptionlogEntity oe=new OptionlogEntity();
	oe.setUserid(uid);
	oe.setOptionDate(pubDate);
	oe.setOption("发表了"+title);
	KnowledgeService service=new KnowledgeService();
	//转向到视图
	if(service.save(knowledgeEntity,oe)>0){
		response.sendRedirect(request.getContextPath()+"/logined/myknowledge.jsp");
	}else{
		request.setAttribute("message", "服务器忙，请稍后在试");
		request.getRequestDispatcher("addknowledge.jsp").forward(request, response);
	}
	
%>