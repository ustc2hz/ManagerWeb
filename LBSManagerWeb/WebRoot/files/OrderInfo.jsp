<%@page import="cn.LBS.model.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="java.util.*,cn.LBS.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
 <body>
 <%
 	List<Order> order =  (List)request.getAttribute("order");
 	for(int i=0;i<order.size();i++)
 %>
<div>
<%@include file="/files/headerr.jsp"%>
<div style="width:901px; height:300px;background:#31a7f8;margin-left:auto; margin-right:auto;"  >
<table align="center">
	<tr>
		<td ><h1 align="center">查看预定信息列表</h1></td>
	</tr>
</table>
<table width="522"  border="1"  align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td >输入车牌号</td>
    <td width="85"><input name="textfield" type="text" id="textfield" size="12" />	</td>
    <td width="85">输入日期：</td>
    <td width="75">
        	<select name="select" id="select">
       	 		<option value="10月11日">10月11日</option>
  				<option value="10月12">10月12</option>
        	</select>
    </td>
    <td width="42">
    	<form action="/managerwebb/files/OrderInfo.jsp " method="post">
       		 <input type="submit" name="button" id="button" value="搜索" />
       	</form>
    </td>
    <td width="77">
    	<form action="/managerwebb/files/OrderInfo.jsp" method="post">
       		 <input type="submit" name="button2" id="button2" value="刷新列表" />
       	</form>
    </td>
  </tr>
</table>
 <table border="1" id="tb" width="521" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="150" class="td_relative1">预定车牌号</td>
      <td width="120" class="td_relative1">提前预定时间</td>
      <td width="120" class="td_relative1">预定时间</td>
      <td width="120" class="td_relative1">金额</td>
      <td width="131" class="td_relative1">请求</td>
    </tr>
    <%
    {
    %>
    <tr>
      <td >${order.driver_id }</td>
      <td >${order.order_date }</td>
      <td >${order.order_price }</td>
      <td >${order.order_status }</td>
    </tr>
    <%
    }
    %>
  </table>
</div>
<%@include file="/bottom.jsp" %>
</div>
</body>
</html>