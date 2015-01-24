<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
 <script type="text/javascript">
 body,div,p
 {
	 margin:0;
 	 padding:0;
 }
 </script>
<body>
<div>
<%@include file="/header.jsp" %>
<div><table width="901" height="500" border="1" bgcolor="#31a7f8" align="center" brodercolor="white">
  <tr>
    <td width="888" valign="top">
    <table width="654" height="377" align="center" cellspacing="10">
    <tr>
    	<td height="20"></td>
    </tr>
      <tr>
        <td width="205" height="130"><a href="files/UserInfo.jsp"><img src="image/9.jpg" width="139" height="128" /></a></td>
        <td width="203"><a href="/managerwebb/ModifyParkInfo"><img src="image/1.jpg" width="139" height="128" /></a></td>
        <td width="198"><a href="/managerwebb/files/OrderInfoo.jsp"><img src="image/11.jpg" width="139" height="128" /></a></td>
      </tr>
      <tr>
      	<td height="21" 

valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密码修改</td>
        	<td height="21" 

valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;车位信息</td>
            	<td height="21" 

valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预订信息</td>
      </tr>
      <tr>
        <td height="131"><a href="files/OrderHandle.jsp"><img src="image/10.jpg" width="139" height="128" /></a></td>
        <td><a href="/managerwebb/ModifyParkNum"><img src="image/6.jpg" width="139" height="128" /></a></td>
        <td><a href="files/CountInfo.jsp"><img src="image/12.png" width="139" height="128" /></a></td>
      </tr>
      <tr>
      		<td height="21" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预定处理</td>
        	<td height="21" valign="top"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;实时车位</td>
            	<td height="21" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;统计信息</td>
      </tr>
    </table></td>
  </tr>
</table>
</div>
<%@include file="/bottom.jsp" %>
</div>
</body>
</html>