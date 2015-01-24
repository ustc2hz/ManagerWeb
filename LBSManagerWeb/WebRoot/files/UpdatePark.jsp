<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
 <script type="text/javascript">
 function ValidateFrom()
 {
 	
 }
 function checkPark()
 {
 	document.getElementById("select").value =  ${ requestScope.actualpark.park_public };
 	document.getElementById("select2").value =  ${ requestScope.actualpark.park_remain };	
 }
 </script>
<body onload="checkPark()">
<div>
<%@include file="/header.jsp"%>
<form action="/managerwebb/ModifyParkNumS" method="post" onsubmit="return ValidateFrom();">
<div style="width:901px; height:250px;background:#31a7f8;margin-left:auto; margin-right:auto;">
<table align="center">
	<tr>
		<td ><h1 align="center">目前车位信息</h1></td>
	</tr>
</table>
<table width="420" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="98">总车位：</td>
    <td width="70">
        <input name="parknumtotal" type="text"  size="8" value= ${ requestScope.actualpark.park_num } />
     </td>
    <td width="116">已预订车位数：</td>
    <td width="78">
        <input name="parkorder" type="text"  size="10" value= ${ requestScope.actualpark.park_order} />
    </td>
  </tr>
  <tr>
    <td>发布车位数：</td>
    <td>
        <select name="select" size="1" id="select">
          <option value="20">20</option>
          <option value="30">30</option>
          <option value="40">40</option>
        </select>
     </td>
    <td colspan="2">
        <input type="submit" name="button" id="button" value="提交" />     
    </td>
  </tr>
  <tr>
    <td>剩余车位数：</td>
    <td>
        <select name="select2" size="1" id="select2">
          <option value="10">10</option>
          <option value="20">20</option>
          <option value="30">30</option>
        </select>
     </td>
    <td colspan="2">&nbsp;</td>
  </tr>
</table>
</div>
</form>
<%@include file="/bottom.jsp"%>
</div>
</body>
</html>