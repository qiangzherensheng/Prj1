<%@page import="bj.entity.UserInfoEntity"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="bj.service.UserInfoService"%>
<%@page import="bj.util.Keys"%>
<%@page import="bj.util.MD5" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
  request.setCharacterEncoding("utf-8");
  String userName=request.getParameter("userName");
  String userPass=request.getParameter("userPass");
  String remember=request.getParameter("remember");
  String Code=request.getParameter("code");
  if(userName==null||userName.trim().length()==0){
	  request.setAttribute("message", "请输入用户名");
	  request.setAttribute("userName", userName.trim());
	  request.getRequestDispatcher("login.jsp").forward(request, response);
	  return ;
  }
  if(userPass==null||userPass.trim().length()==0){
	  request.setAttribute("message", "请输入用户密码");
	  request.setAttribute("userPass", userPass.trim());
	  request.getRequestDispatcher("login.jsp").forward(request, response);
	  return ;
  }
if(Code==null||!(Code.equals(request.getSession().getAttribute("code")))){
	 request.setAttribute("message", "验证码输入错误");
	 request.setAttribute("code", Code.trim());
	 request.getRequestDispatcher("login.jsp").forward(request, response);
	 return;
 } 
  //调用业务
  UserInfoService service =new UserInfoService();
  userPass = MD5.createPassword(userPass);
  UserInfoEntity entity=service.findByUserNameandUserPass(userName, userPass);
  if(entity==null){
	  request.setAttribute("message", "用户名或密码错误！");
	  request.setAttribute("userName", userName.trim());
	  request.getRequestDispatcher("login.jsp").forward(request, response);
  }else{
	  //登录成功，记录用户登录状态
	  session.setAttribute(Keys.LOGINUSER,entity);
	  if(remember!=null&&remember.equals("yes")){
		  Cookie cookie=new Cookie(Keys.REMEMBER_LOGEN_NAME,URLEncoder.encode(userName));
		  cookie.setMaxAge(60*60*24*365*10);
		  cookie.setPath(request.getContextPath());
		  response.addCookie(cookie);
	  }
	  response.sendRedirect(request.getContextPath()+"/index.jsp");
  }
  
%>