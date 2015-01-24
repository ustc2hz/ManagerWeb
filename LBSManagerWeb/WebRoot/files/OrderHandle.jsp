<%@page import="cn.LBS.model.Order"%>
<%@ page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*,cn.LBS.*,java.sql.Connection,java.sql.DriverManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
    function delcfm() 
    {
        if (!confirm("确认要修改吗？")) {
            window.event.returnValue = false;
        }
    }
    function test()
{
     var page = document.getElementById("goPage").value;    
      window.location.href="/files/OrderHandle.jsp?curPage="+page; 
}
</script>
 </head>
<body>
<div>
<%@include file="/files/headerr.jsp"%>
<div style="width:901px; height:300px;background:#31a7f8;margin-left:auto; margin-right:auto;"  >
<table align="center">
	<tr>
		<td ><h1 align="center">车位预定列表</h1></td>
	</tr>
</table>
<table width="521" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="146" height="44">请输入预定车牌：</td>
    <td width="94">
      <label>
        <input name="textfield" type="text" id="textfield" size="12" />
      </label>
   	</td>
    <td width="111">    
        <input type="submit" name="button" id="button" value="搜索" />
  		</td>
    <td width="142">
      <input type="submit" name="button2" id="button2" value="刷新列表" />
      <input type="submit" name="button3" id="button3" value="提交" />
    </td>
  </tr>
</table>
<%
	String url="jdbc:mysql://localhost:3306/parktable?user=root&password=root";//连接相应的mysql服务器
	Class.forName("com.mysql.jdbc.Driver").newInstance();//载入相应的驱动程序
	Connection connection = DriverManager.getConnection(url);
%>
<%!  
	public static final int PAGESIZE = 4; 
	int pageCount; 
	int curPage = 1; 
	int size = 0 ;
%>
<div style="width:521; height:300; overflow:auto;" id="dv" align="center">
  <table border="1" cellpadding="0" cellspacing="0" id="tb" width="521" align="center">
    <tr>
      <td width="150" class="td_relative1">预定车牌号</td>
        <td width="120" class="td_relative1">订单日期</td>
      <td width="120" class="td_relative1">金额</td>
      <td width="131" class="td_relative1">状态</td>
    </tr>
    <% 
	try{ 
		Order order = new Order();
		String sql = "select * from parktable.order"; 
		Statement stat = connection.createStatement(); 
		ResultSet rs = stat.executeQuery(sql); 
		rs.last(); 
		size = rs.getRow(); //得到总数
		//System.out.println("AAAAAAA"+size);
		pageCount = (size%PAGESIZE==0)?(size/PAGESIZE):(size/PAGESIZE+1); 
		String tmp = request.getParameter("curPage"); 
		if(tmp==null){ 
			tmp="1"; 
		} 
		curPage = Integer.parseInt(tmp); 
		if(curPage>=pageCount) curPage = pageCount; 
		boolean flag = rs.absolute((curPage-1)*PAGESIZE+1); 
		//out.println(curPage); 
		int count = 0; 
		
		do{ 
			if(count>=PAGESIZE) break;  
			order.setDriver_id(rs.getString("driver_id")); 
			order.setOrder_date(rs.getDate("order_date")); 	
			order.setOrder_price(rs.getDouble("order_price")); 
			order.setOrder_status(rs.getString("order_status")); 
			count++; 
	%> 
    <tr>
      <td ><%=order.getDriver_id()%></td>
      <td ><%=order.getOrder_date()%> </td>
      <td ><%=order.getOrder_price()%></td>
      <td ><%=order.getOrder_status() %></td>
		<td align="center"> <a href="/managerwebb/files/OrderHandle.jsp?a=" <%=order.getDriver_id() %>  onclick="delcfm()" style="text-decoration:none">通过 </a></td>  
    </tr>
   	 <%
   	 }
   	 while(rs.next()); 
		connection.close(); 
		} 
		catch(Exception e){ 
		 	System.out.println(e.toString());
		} 
%> 
  </table>
  <div align="center">
<a href = "OrderHandle.jsp?curPage=1" >首页</a>
<a href = "OrderHandle.jsp?curPage=<%=curPage-1%>" >上一页</a>
<a href = "OrderHandle.jsp?curPage=<%=curPage+1%>" >下一页</a>
<a href = "OrderHandle.jsp?curPage=<%=pageCount%>" >尾页</a>
第<%=curPage%>页/共<%=pageCount%>页 共有<%= size %>个订单
<input id="goPage" type="text" size="3"><a href="javascript:test()" style="text-decoration:none">跳转</a>
</div>
</div>
</div>
<%@include file="/bottom.jsp" %>
</div>
</body>
</html>