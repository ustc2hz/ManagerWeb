<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
 <script type="text/javascript">
	function  ValidateFromone()
	{
		var oldpassword=document.getElementById("OldPassword").value;
		if(oldpassword == "")
		{
			alert("旧密码不能为空!");
			return false;
		}
		var password=document.getElementById("NewPassword").value;
		var repassword=document.getElementById("AgainPassword").value;
		if(password == "")
		{ 
			alert("新密码不能为空!");
			return false;
		}
		if(password != repassword)
		{
			alert("两次输入的密码不一致!");
			return false;
		}
		var validatepassword=document.getElementById("NewPassword").value;
		if((validatepassword.match("^[a-zA-Z]{1}[a-zA-Z0-9]{4,19}$"))== null)
		{
			alert("密码必须是5-20位以字母开头的字母和数字的组合！");
			return false;
		}
	}
 </script>
</head>
<body>
<div>
<%@include file="/files/headerr.jsp"%>
<div style="width:901px; height:250px;background:#31a7f8;margin-left:213; margin-right:auto;">
<form action="/managerwebb/Modify" method="post" onsubmit="return ValidateFromone();">
<table align="center">
	<tr>
		<td ><h1 align="center">用户修改信息</h1></td>
	</tr>
</table>
<table align="center" width="473" border="1"  cellPadding="0"  bordercolor="white" cellspacing="0">
  <tr>
    <td width="244" height="78" rowspan="6" align="center"><img src="/managerwebb/image/9.jpg" width="180" height="130" /></td>
  </tr>
  <tr>
  	<td style="color:black;" colspan="2" align="center" height="45"><font size="6" style="font-weight:BOLD">修改密码</font></td>
  </tr>
  <tr>
    <td  width=100>旧 密 码:</td>
    <td  width=120>
    <input type="password" style="WIDTH: 130px" name="OldPassword" id="OldPassword"> </td>
  </tr>
  <tr>
    <td  width=100>新 密 码:</td>
    <td  width=120><input type="password" style="WIDTH: 130px" name="NewPassword" id="NewPassword"/></td>
  </tr>
  <tr>
    <td  width=100 >确认密码:</td>
    <td  width=120><input type="password" style="WIDTH: 130px" name="AgainPassword" id="AgainPassword"/></td>
  </tr>
  <tr>
    <td colspan="2" align="center">
        <input type="submit" name="submit" id="button1" value="提交"  />  
    </td>
  </tr>
</table>
</form>
</div>
<%@include file="/bottom.jsp" %>
</div>
</body>
</html>