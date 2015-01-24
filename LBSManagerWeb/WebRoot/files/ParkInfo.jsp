<%@page import="cn.LBS.model.park"%>
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
	document.getElementById("select1").value =  ${ requestScope.Park.park_priceOne };
	document.getElementById("select2").value =  ${ requestScope.Park.park_priceTwo };
	document.getElementById("select3").value = ${ requestScope.Park.park_priceThree };
	document.getElementById("select4").value = ${ requestScope.Park.park_priceFour};	
}
 </script>
<body onload="checkPark()">
<div>
<%@include file="/header.jsp"%>
<div style="width:901px; height:400px;background:#31a7f8;margin-left:auto; margin-right:auto;">
<form action="/managerwebb/ModifyParkInfoS" method="post"  onsubmit="return ValidateFrom();">
<table align="center">
	<tr>
		<td ><h1 align="center">车位信息</h1></td>
	</tr>
</table>
<table  border="1"  align="center" cellpadding="0" cellspacing="0" bgcolor="#31a7f8">
  <tr>
    <td align="right" colspan="4">
    	<input type="submit" name="button6" id="button6" value="提交修改"/>
    </td>
  </tr>
  <tr>
    <td width="80" height="20">总车位：</td>
    <td>
        <input type="text" name="ParkNum" id="ParkTotalNum" value=${ requestScope.Park.park_num } width="20" />
    </td>
    <td>停车场名称： </td>
    <td><input type="text" name="ParkName" id="ParkName" value=${ requestScope.Park.park_name } width="20" /></td>
  </tr>
 <tr>
    <td height="25" >收费标准：</td>
    <td colspan="3">
        <textarea name="ParkPay" id="PriceStandard" cols="48" rows="4">${ requestScope.Park.park_pay }</textarea>
    </td>
  </tr>
  <tr>
    <td>预定时间：</td>
    
    <td >0.5个小时</td>
    <td align="right"> 收费价格：</td>
    <td>
        <select name="select1" id="select1"> 
        	<option value="5" id="option1" >5</option>
  			<option value="10" id="option2">10</option>
  			<option value="15" id="option3">15</option>
 			 <option value="20" id="option4">20</option>
        </select>
    元</td>
  </tr>
  <tr>
    <td >预定时间：</td>
    <td  >1个小时
    </td>
    <td align="right">收费价格：</td>
    <td>
        <select name="select2" id="select2">  
        	<option value="5">5</option>
  			<option value="10">10</option>
  			<option value="15">15</option>
 			 <option value="20">20</option>
        </select>
   元	</td>
  </tr>
  <tr>
    <td >预定时间：</td>
    <td >1.5个小时  </td>
    <td align="right">收费价格: </td>
    <td>
        <select name="select3" id="select3"> 
        	<option value="5">5</option>
  			<option value="10">10</option>
  			<option value="15">15</option>
 			 <option value="20">20</option>
        </select>
   	元</td>
  </tr>
  <tr>
    <td>预定时间：</td>
    <td >2个小时</td>
    <td align="right">收费价格：</td>
    <td>
        <select name="select4" id="select4">
        	<option value="5">5</option>
  			<option value="10">10</option>
  			<option value="15">15</option>
 			 <option value="20">20</option>
        </select>
    元</td>
  </tr>
  <tr>
  	<td colspan="4">
  		停车地点：
  	</td>
  </tr>
</table>
</form>
</div>
<%@include file="/bottom.jsp" %>
</div>
</body>
</html>