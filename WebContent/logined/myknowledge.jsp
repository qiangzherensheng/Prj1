<%@page import="bj.util.MyDateFormat"%>
<%@page import="bj.entity.KnowledgeEntity"%>
<%@page import="bj.service.KnowledgeService"%>
<%@page import="bj.entity.CatagoryEntity"%>
<%@page import="java.util.List"%>
<%@page import="bj.service.CatagoryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 导入jstl标签库 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@taglib uri="http://www.sentu.cn/pagetag" prefix="p" %>
  <%--   <%@include file="sessionvalid.jsp"  %> --%>
    <%-- <% 
    	CatagoryService service=new CatagoryService();
    	List<CatagoryEntity> list=service.findAll();
    %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的知识库-添加知识</title>
<style type="text/css">
a.pageLink:link{color: black;text-decoration: none; font-size: 16px;}
a.pageLink:visited{color: black;text-decoration: none; font-size: 16px;}
a.pageLink:hover{color: black;text-decoration: none; font-size: 16px; font-weight: bold;}

a.pageLink1:link{color: red;text-decoration: none; font-size: 16px;}
a.pageLink1:visited{color: red;text-decoration: none; font-size: 16px;}
a.pageLink1:hover{color: red;text-decoration: none; font-size: 16px; font-weight: bold;}

.classText{color:black; font-size: 16px;}
</style>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="index">
<%@include file="header.jsp"%>
<div class="space3"></div>
<div  class="container">
 <div id="left"><%@include file="../left.jsp" %></div>
 <div id="right">
  <!-- 我的知识标题 -->
<table width="100%" border="0" align="center"  cellpadding="0" cellspacing="0" style="border-bottom:2px solid #069; font-size:20px; font-family:微软雅黑;">
<tr>
<td height=60px>我的知识</td>
</tr>
</table>
<!-- 我的知识条目 -->
	<%--  <%
	//获取所有的知识try {
			//控制器调用模型获取数据，并将数据保存到请求范围中，然后通知视图到请求范围中获取数据并显示
			KnowledgeService service =new KnowledgeService();
			List<KnowledgeEntity> list = service.findByUserId(((UserInfoEntity)request.getSession().getAttribute(Keys.LOGINUSER)).getId());
			request.setAttribute("key", list);
			request.getRequestDispatcher("/logined/myknowledge.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	KnowledgeService s2 =new KnowledgeService();
	List<KnowledgeEntity> list1 = s2.findByUserId(((UserInfoEntity)session.getAttribute(Keys.LOGINUSER)).getId());
	if(list1==null || list1.size()==0){
	 %> --%>
	 <c:if test="${empty key}">
<table width="800" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="30" align="center">&nbsp;</td>
    <td width="300" align="center">你还没有知识，赶紧点击<a href="${pageContext.request.contextPath }/logined/AddknowledgesServlet" class="left_nav">这里</a>发布吧</td>
  </tr>
</table>
</c:if>
<%-- <% }else{
	for(int i=0;i<list1.size();i++){
	%> --%>
	<c:forEach items="${key }" var="item">
<table width="900" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="200" rowspan="2" align="center" ><img src="${pageContext.request.contextPath }/headerimages/${sessionScope.logineduser.headerImage}" width="100" height="100"/></td>
    <td height="40"><a href="${pageContext.request.contextPath}/unlogined/detail.jsp?id=${item.id}">${item.title}</a></td>
  </tr>
  <tr>
    <td height=40 width="900" style="border-bottom:2px solid #00F;"><span><f:formatDate value="${item.pubDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>&nbsp;
    <span >由</span>&nbsp;<span>${item.userInfoEntity.nickName}</span>&nbsp;
    <span>发布到</span>&nbsp;<span>${item.catagoryEntity.name}</span>&nbsp;
    <a href="${pageContext.request.contextPath }/logined/EditServlet?id=${item.id}" class="left_nav">编辑</a>&nbsp;
    <a onclick="javascript:return confirm('你确认删除吗？')" href="${pageContext.request.contextPath }/logined/DeleteServlet?id=${item.id}" class="left_nav">删除</a>
    </td>
  </tr>
</table>
</c:forEach>
<p:pageTag pageIndex="${page.pageIndex }" className="pageLink" actionName="MyknowledgeServlet" classText="classText" recordCount="${page.recordCount }" pageSize="${page.pageSize }"/>
 <%-- <table width="900" border="1" align="center" cellpadding="0" cellspacing="0">
  <form id="form1" name="form1" method="get" action="${pageContext.request.contextPath }/logined/MyknowledgeServlet" >
    <tr>
      <td height="40" align="center">
      <c:if test="${page.pageIndex!=1}">
       <a href="${pageContext.request.contextPath }/logined/MyknowledgeServlet?pageIndex=1">第一页</a>&nbsp;
       <a href="${pageContext.request.contextPath }/logined/MyknowledgeServlet?pageIndex=${page.pageIndex-1}">上一页</a>&nbsp;
      </c:if>
      <c:if test="${page.pageIndex!=page.pageCount}">
       <a href="${pageContext.request.contextPath }/logined/MyknowledgeServlet?pageIndex=${page.pageIndex+1}">下一页</a>&nbsp;
       <a href="${pageContext.request.contextPath }/logined/MyknowledgeServlet?pageIndex=${page.pageCount}">最后页</a>&nbsp;
      </c:if>
      </td>
   
      <td align="left">共${page.recordCount}条/共${page.pageCount }页/当前第${page.pageIndex}页</td>
      <td>有${applicationScope.onLineNum}人浏览</td>
      <td align="right"><input type="text" name="pageIndex" value="${page.pageIndex}"style="width:40px; height:20px;border:1px solid gray;"></td>
      <td align="left"><input type="submit" value="GO"style="width:30px; height:22px; border:1px solid gray; border-left:none;"  ></td>
    </tr>
    </form>
  </table> --%>

<%-- <% 		}
	}
	%> --%>
<!-- 我的知识条目 -->
 </div>
</div>
<div class="space3"></div>
<%@include file="../footer.jsp" %>
</body>
</html>