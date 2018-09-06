<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册界面</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<script>
function check(chk){
	if(chk.checked==true){
		//设置注册按钮激活
		document.getElementById("btnReg").removeAttribute("disabled");
	}else{
		document.getElementById("btnReg").setAttribute("disabled","disabled");
		}
	}
	//输入用户
	function userNameCss1(){
		document.getElementById("userName1").className="input_text1";
		document.getElementById("message_userName").innerHTML="请填写用户名";
	}
	function userNameCss2(){
		if(document.getElementById("userName1").value==""){
			document.getElementById("userName1").style.borderColor="#F00";
		    document.getElementById("message_userName").innerHTML="用户名不能为空";
	}else{
			
			document.getElementById("userName1").style.borderColor="#CCC";
			document.getElementById("message_userName").innerHTML="<img src='images/right.jpg'>";
			}
	}
	//验证用户名是否可用
	function validuserName(){
		document.getElementById("loading").style.display="inline";
		var userName=document.getElementById("userName1").value;
		//alert(userName);
		var http=new XMLHttpRequest();
		http.open("post","${pageContext.request.contextPath }/UserExistServlet");
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		http.onreadystatechange=function(){
			callback(http);
		};
		//alert(userName);
		http.send("userName="+userName);
	}
	function callback(http){
		if(http.readyState==4){
			if(http.status==200){
				var res=http.responseText;
				//alert(res);
				document.getElementById("message_userName1").innerHTML=res;
			    document.getElementById("loading").style.display="none";
			}
		}
	}
	//输入密码
	function userPassCss1(){
		document.getElementById("userPass").className="input_text1";
		document.getElementById("message_userPass").innerHTML="请填写密码，长度在4到20之间";
	}
	function userPassCss2(){
		if(document.getElementById("userPass").value==""){
			document.getElementById("userPass").style.borderColor="#F00";
		    document.getElementById("message_userPass").innerHTML="密码不能为空";
	}else if(document.getElementById("userPass").value.length>20||document.getElementById("userPass").value.length<4){
		document.getElementById("userPass").style.borderColor="#F00";
	    document.getElementById("message_userPass").innerHTML="密码长度在4到20之间";
	}
	else{
			document.getElementById("userPass").style.borderColor="#CCC";
			document.getElementById("message_userPass").innerHTML="<img src='images/right.jpg'>";
			}
	}
	//确认密码
	function userPass1Css1(){
		document.getElementById("userPass1").className="input_text1";
		document.getElementById("message_userPass1").innerHTML="请填写密码，长度在4到20之间";
	}
	function userPass1Css2(){
		if(document.getElementById("userPass1").value==""){
			document.getElementById("userPass1").style.borderColor="#F00";
		    document.getElementById("message_userPass1").innerHTML="请输入确认密码";	
		}
		else if(document.getElementById("userPass1").value != document.getElementById("userPass").value){
			document.getElementById("userPass1").style.borderColor="#F00";
		    document.getElementById("message_userPass1").innerHTML="两次密码不一致，请重新输入";
	}
	else{
			document.getElementById("userPass1").style.borderColor="#CCC";
			document.getElementById("message_userPass1").innerHTML="<img src='images/right.jpg'>";
			}
	}
</script>
</head>
<body id=regbd>
<div id="containerreg">
<p>&nbsp;</p>
<p>&nbsp;&nbsp;<a href="index.jsp">首页</a>  <a href="login.jsp">登录</a></p>
<div id="space1"></div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="30" bgcolor="#3F9">&nbsp;</td>
        <td bgcolor="#3FC">&nbsp;</td>
        <td bgcolor="#3FF">&nbsp;</td>
      </tr>
    </table>
    <div id=space></div>
    <form name="form1" method="post" action="${pageContext.request.contextPath }/RegisterServlet">
<table width="900" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="300" height="50" align="right">邮箱：</td>
    <td width="250" ><input type="text" name="userName"  class="input_text" onfocus="userNameCss1()" onblur="userNameCss2; validuserName()" id="userName1"></td>
  	<td><span id="message_userName" class="messageStyle" ></span>
  	<span id="message_userName1"></span><span id="loading" style="display:none"><img src="images/loading.gif"></span></td>
  </tr>
  <tr>
    <td height="50" align="right">密码：</td>
    <td ><input type="password" name="userPass" class="input_text" id="userPass" onfocus="userPassCss1()" onblur="userPassCss2()" ></td>
  	<td><span id="message_userPass" class="messageStyle" ></span>&nbsp;</td>
  </tr>
  <tr>
    <td height="50" align="right">确认密码：</td>
    <td><input type="password" name="userPass1" class="input_text" id="userPass1" onfocus="userPass1Css1()" onblur="userPass1Css2()"></td>
  	<td><span id="message_userPass1" class="messageStyle" ></span></td>
  </tr>
  <tr>
    <td  height="50" align="right">&nbsp;</td>
    <td  height="50" align="left"><input type="checkbox" name="checkbox" id="checkbox3" onClick="check(this)">
我已经仔细阅读并接受<a href="javascript:void(0)">注册条款</a></td>
  </tr>
  <tr>
    <td height="50" align="center">&nbsp;</td>
    <td height="50" align="center"><input  type="image" id="btnReg" disabled="disabled" onMouseOver="this.src='images/btn-reg2.png'" onMouseOut="this.src='images/btn-reg1.png'" name="imageField" src="images/btn-reg1.png"></td>
    </tr>
</table>
</form>
</div>
<%@include file="footer.jsp" %>
</body>
</html>