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

public class ModifyParkNum extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		ServletContext application=this.getServletContext();
		user user = new user();
		user = (cn.LBS.model.user) application.getAttribute("existUser");
		
		ActualParkJdbcImpl actualImpl = new ActualParkJdbcImpl();
		actualpark actualpark = new actualpark();
		
		List list = new ArrayList();
		System.out.println("停车场的用户名"+user.getUsername());
		list = actualImpl.find(user);
//		System.out.println("停车场属性"+list.size());
		if( list.size() == 0)
		{
			/*
			 * 说明用户还没有添加关于停车场的任何信息
			 */
			request.getRequestDispatcher("/files/UpdatePark.jsp").forward(request,response);
			return;
			
		}else {
			//说明用户已经有停车场的信息
			actualpark.setPark_num(Integer.valueOf(list.get(0).toString()).intValue());
			actualpark.setPark_public(Integer.valueOf(list.get(1).toString()).intValue());
			actualpark.setPark_order(Integer.valueOf(list.get(2).toString()).intValue());
			actualpark.setPark_remain(Integer.valueOf(list.get(3).toString()).intValue());
			System.out.println("总车位："+actualpark.getPark_num());
			System.out.println("公布的："+actualpark.getPark_public());
			System.out.println("剩余的："+actualpark.getPark_remain());
			System.out.println("预定的 ："+actualpark.getPark_order());
			request.setAttribute("actualpark",actualpark);
			request.getRequestDispatcher("files/UpdatePark.jsp").forward(request,response);
			return;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		doGet(request, response);
	}

}
