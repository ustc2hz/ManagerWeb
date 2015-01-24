package cn.LBS.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.LBS.Dao.ParkDaoJdbcImpl;
import cn.LBS.model.park;
import cn.LBS.model.user;

/*
 *��һ�η���ʱ��ʾͣ��������Ϣ
 */
public class ModifyParkInfo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		ServletContext application=this.getServletContext();
		user user = new user();
		user = (cn.LBS.model.user) application.getAttribute("existUser");
		
		ParkDaoJdbcImpl parkImpl = new ParkDaoJdbcImpl();
		park park = new park();
		
		List list = new ArrayList();
		System.out.println("ͣ�������û���"+user.getUsername());
		list = parkImpl.find(user);
//		System.out.println("ͣ��������"+list.size());
		if( list.size() == 0)
		{
			/*
			 * ˵���û���û����ӹ���ͣ�������κ���Ϣ
			 */
			request.getRequestDispatcher("/files/ParkInfo.jsp").forward(request,response);
			return;
			
		}else {
			//˵���û��Ѿ���ͣ��������Ϣ
			park.setPark_name(list.get(0).toString());
			park.setPark_num(Integer.valueOf(list.get(1).toString()).intValue());
			park.setPark_pay(list.get(2).toString());
			park.setPark_priceOne(Integer.valueOf(list.get(3).toString()).intValue());
			park.setPark_priceTwo(Integer.valueOf(list.get(4).toString()).intValue());
			park.setPark_priceThree( Integer.valueOf(list.get(5).toString()).intValue());
			park.setPark_priceFour(Integer.valueOf(list.get(6).toString()).intValue());	
			
			request.setAttribute("Park",park);
			request.getRequestDispatcher("files/ParkInfo.jsp").forward(request,response);
			return;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		doGet(request, response);
	}

}
