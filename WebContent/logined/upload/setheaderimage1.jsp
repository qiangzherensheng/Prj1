<%@page import="bj.entity.CatagoryEntity"%>
<%@page import="java.util.List"%>
<%@page import="bj.service.CatagoryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../sessionvalid.jsp" %>
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
<script type="text/javascript">
  function uploadevent(status,picUrl){
    status += '';
    switch(status){
    case '1':
	var filename162 = picUrl+'_162.jpg';
	var filename48 = picUrl+'_48.jpg';
	var filename20 = picUrl+"_20.jpg";

	document.getElementById('myheaderimage').src="${pageContext.request.contextPath}/headerimages/" + filename162;//"头像1 : <img style=\"border-radius:5px;\" src='${pageContext.request.contextPath}/upload/"+filename162+"'/> <br/> 头像2: <img style=\"border-radius:5px;\" src='${pageContext.request.contextPath}/upload/"+filename48+ "'/><br/> 头像3: <img style=\"border-radius:5px;\" src='${pageContext.request.contextPath}/upload/"+filename20+ "'/>" ;
	
    break;
    case '-1':
    window.location.reload();
    break;
    default:
    window.location.reload();
   } 
    window.location.reload();
  }
  </script>
</head>
<body class="index">
<%@include file="../header.jsp"%>
<div class="space3"></div>
<div  class="container">
 <div id="left"><%@include file="../../left.jsp" %></div>
 <div id="right">
 <form name="form1" enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/SetHeaderImage ">
  <table width="900" border="1" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="50" colspan="2" style="border-bottom:1px solid #03C;">上传头像<span></span></td>
    </tr>
      <tr>
      <td height="50" align="right">上传头像<span style="color: red; font-size: 14px;"><%=request.getAttribute("message")==null?"":request.getAttribute("message") %></span></td>
    </tr>
      <tr>
      <td height="80"><img id="myheaderimage1" src="${pageContext.request.contextPath }/headerimages/${sessionScope.logineduser.headerImage}"/></td>
      <td><div id="altContent">
	  <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0" width="650" height="450" id="myMovieName">
	    <param name="movie" value="avatar.swf"/>
	    <param name="quality" value="high"/>
	    <param name="bgcolor" value="#FFFFFF"/>
	    <param name="flashvars" value="imgUrl=./default.jpg&uploadUrl=./upfile.jsp&uploadSrc=false" />
	    <embed src="avatar.swf" quality="high" bgcolor="#FFFFFF" width="650" height="450" wmode="transparent" flashVars="imgUrl=./default.jpg&uploadUrl=./upfile.jsp&uploadSrc=false" name="myMovieName" align="" type="application/x-shockwave-flash" allowScriptAccess="always" pluginspage="http://www.macromedia.com/go/getflashplayer"></embed>
	  </object>
</div></td>
    </tr>
    <tr>
      <td height="50" colspan="2">&nbsp;</td>
    </tr>
  </table>
</form>
<%@include file="../../footer.jsp" %>
</div>
</div>
</body>
</html>