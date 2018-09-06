<%@page import="bj.util.Keys"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//用户退出
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(Keys.REMEMBER_LOGEN_NAME)) {
				cookies[i].setMaxAge(0);
				cookies[i].setPath(request.getContextPath());
				response.addCookie(cookies[i]);
				break;
			}
		}
	}
	session.invalidate();
	response.sendRedirect(request.getContextPath() + "/IndexServlet");
%>
