<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function loaddate(){
	var http=new XMLHttpRequest;
	http.open("post","/prj1/AjaxDemo?id=1");
	http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	http.onreadstatechange=function(){
		callback(http);
	};
	http.send(null);
}
function callback(http){
	if(http.readyState==4&&http.status==200){
		var res=http.responseText;
		document.getElementById("s1").innerHTML=res;
	}
}
</script>
</head>
<body onload="loaddate()">
<table width="500" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>&nbsp;</td>
<td>评论人数(<span id="s1"><img src="../images/loading.gif"/></span>),阅读次数（<span id="s2"><img src="../images/loading.gif"></span>）</td>
</tr>
<tr>
    <td>文章标题</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>文章内容</td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>