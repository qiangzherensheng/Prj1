<%@page import="java.net.URLDecoder"%>
<%@page import="bj.util.Keys"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String userName="";
    userName=request.getAttribute("userName")==null?"":(String)request.getAttribute("userName");
    if(userName.equals("")){
    	Cookie[] cookies=request.getCookies();
    	if(cookies!=null){
    		for(int i=0;i<cookies.length;i++){
    			if(cookies[i].getName().equals(Keys.REMEMBER_LOGEN_NAME)){
    			userName=URLDecoder.decode(cookies[i].getValue());
    			break;
    		}
    		}
    	}
    }
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<script>
function changeCode(){
	document.getElementById("code").src="ImageServlet1?id="+new Date().getTime();
}
function check(){ 
	var userName=document.getElementById("userName");
	var userPass=document.getElementById("userPass");
	var code=document.getElementById("code");
	if(userName.value==null||userName.value.length==0){
		alert("用户名不能为空");
		userName.focus();
		return false;
	}
	if(userPass.value.length<4||userPass.value.length>20){
		alert("密码在4到20之间");
		userPass.focus();
		return false;
	}
	return true;
}
</script>
</head>
<body id="logbd">

<table width="865" border="0" align="center" id="logtb1" cellpadding="0" cellspacing="0">
  <tr>
    <td width="546" height="90">&nbsp;</td>
    <td width="319" rowspan="2">
    <form name="form1" method="post" action="${pageContext.request.contextPath }/LoginServlet">
      <table width="340" border="1" style="border-color:#3FF" align="right" cellpadding="0" cellspacing="0">
        <tr>
          <td height="50" colspan="2" align="center"><label for="userName"></label>
            <input name="userName" type="text" class="log_input" id="userName" value="${requestScope.userName}"></td>
        </tr>
        <tr>
          <td height="50" colspan="2" align="center">
          <input name="userPass" type="password" class="log_input" id="userPass" ></td>
        </tr>
        <tr>
          <td height="50" colspan="2" align="center"><label for="textfield7"></label>
            <input type="text" name="textfield3" id="textfield7" class="log_input" value="${requestScope.message}">
            <label for="textfield3"></label></td>
        </tr>
        <tr>
          <td height="50" align="center" id="remember">验证码
            <label for="textfield"></label>
            <input type="text" name="code" id="textfield" class="log_input1"></td>
          <td height="50" align="left" id="remember"><img id="code" src="ImageServlet1"/><a href="javascript:void(0)" onclick="changeCode()">看不清</a></td>
          </tr>
        <tr>
          <td width="300" height="50" align="center"><input type="checkbox" name="remember" id="remember2">
下次自动登录</td>
          <td width="207" height="50" align="center"><a href="javascript:void()">忘记密码</a></td>
        </tr>
        <tr>
          <td height="50" colspan="2" align="center"><input onclick=" return check()" type="image" id="imageField" src="images/btn-login.png" ></td>
        </tr>
        <tr>
          <td height="50" colspan="2" align="center"><a href="RegisterServlet">注册</a></td>
        </tr>
      </table>
    </form>
    </td>
    </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>