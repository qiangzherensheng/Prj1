<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人设置</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="index">
<%@include file="header.jsp"%>
<div class="space3"></div>
<div  class="container">
 <div id="left"><%@include file="../left.jsp" %></div>
 <div id="right">
 <form name="form1" method="post" action="dopersonal.jsp">
  <table width="700" border="1" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td height="40" align="center">用户名：</td>
      <td width="491"><input type="text" name="userName" id="sss" class="input_text"></td>
    </tr>
    <tr>
      <td height="40" align="center">密码：</td>
      <td><input type="password" name="userPass" id="userPass" class="input_text"></td>
    </tr>
    <tr>
      <td height="40" align="center">昵称：</td>
      <td><input type="text" name="nickName" id="nickName" class="input_text"></td>
    </tr>
    <tr>
      <td height="40" align="center">头像：</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td align="center">个人介绍：
        <label for="introdece"></label></td>
      <td align="left"><textarea name="introduce" id="introdece" cols="45" rows="5" class="textarea"></textarea></td>
    </tr>
    <tr>
      <td height="50" colspan="2" align="center"><input type="submit" name="submit" id="submit" class="submit" value="提交"></td>
    </tr>
  </table>
</form>
 </div>
</div>
<div class="space3"></div>
<%@include file="../footer.jsp" %>

</body>
</html>