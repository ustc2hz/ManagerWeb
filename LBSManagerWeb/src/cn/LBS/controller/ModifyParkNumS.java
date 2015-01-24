package cn.LBS.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.LBS.Dao.ActualParkJdbcImpl;
import cn.LBS.Dao.ParkDaoJdbcImpl;
import cn.LBS.model.actualpark;
import cn.LBS.model.park;
import cn.LBS.model.user;

public class ModifyParkNumS extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		int i;
		actualpark actualpark = new actualpark();
		List list = new ArrayList();
		/*
		 * 获取表单信息，并对停车场信息进行封装
		 */
		actualpark.setPark_num(Integer.valueOf(request.getParameter("parknumtotal")).intValue());
		actualpark.setPark_public(Integer.valueOf(request.getParameter("select")).intValue());
		actualpark.setPark_order(Integer.valueOf(request.getParameter("parkorder")).intValue());
		actualpark.setPark_remain(Integer.valueOf(request.getParameter("select2")).intValue());
		
		ServletContext application=this.getServletContext();
		user user = new user();
		user = (cn.LBS.model.user) application.getAttribute("existUser");
		
		ActualParkJdbcImpl actualImpl = new ActualParkJdbcImpl();
		list = actualImpl.find(user);
		System.out.println("停车场属性："+list.size());
		if( list.size()  == 0)
		{//没有关于用户名的信息，所以需要新增加关于用户名的停车场信息。
			try{
					i = actualImpl.add(user, actualpark);
					if(i == 0)
					{
						//添加失败
						response.getWriter().print("<script language='javascript'>alert('添加实时车位信息失败！');window.location.href='/managerwebb/main.jsp';</script>");
						System.out.println("添加实时车位信息失败");
					}else {
						//添加成功
						request.setAttribute("actualpark", actualpark);
						response.getWriter().print("<script language='javascript'>alert('添加实时车位信息成功！');window.location.href='/managerwebb/main.jsp';</script>");
						//request.getRequestDispatcher("files/ParkInfo.jsp").forward(request,response);
						return;
					}
			}catch(Exception e)
				{	e.printStackTrace(); }
			
		}else{
			//修改用户名的停车场信息
			try {
				i = actualImpl.update(user, actualpark);
				if( i == 0)
				{//修改用户名的停车场信息失败
					response.getWriter().print("<script language='javascript'>alert('修改车位实时信息失败！');window.location.href='/managerwebb/main.jsp';</script>");
					System.out.println("修改车位实时信息失败");
				}else {
				//修改用户名的停车场信息成功
					request.setAttribute("actualpark", actualpark);
					response.getWriter().print("<script language='javascript'>alert('修改车位实时信息成功！');window.location.href='/managerwebb/main.jsp';</script>");
					//request.getRequestDispatcher("files/ParkInfo.jsp").forward(request,response);
					return;
				}
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
				}
		}
	}		
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		doGet(request, response);
	}
}
