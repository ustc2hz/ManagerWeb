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

public class ModifyParkInfoS extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		int i;
		park park = new park();
		List list = new ArrayList();
		/*
		 * ��ȡ����Ϣ������ͣ������Ϣ���з�װ
		 */
		park.setPark_name(request.getParameter("ParkName"));
		park.setPark_num(Integer.valueOf(request.getParameter("ParkNum")).intValue());
		park.setPark_pay(request.getParameter("ParkPay"));
		park.setPark_priceOne(Integer.valueOf(request.getParameter("select1")).intValue());
		park.setPark_priceTwo(Integer.valueOf(request.getParameter("select2")).intValue());
		park.setPark_priceThree(Integer.valueOf(request.getParameter("select3")).intValue());
		park.setPark_priceFour(Integer.valueOf(request.getParameter("select4")).intValue());
		
		ServletContext application=this.getServletContext();
		user user = new user();
		user = (cn.LBS.model.user) application.getAttribute("existUser");
		
		ParkDaoJdbcImpl parkImpl = new ParkDaoJdbcImpl();
		list = parkImpl.find(user);
		System.out.println("ͣ�������ԣ�"+list.size());
		if( list.size()  == 0)
		{//û�й����û�������Ϣ��������Ҫ�����ӹ����û�����ͣ������Ϣ��
			try{
					i = parkImpl.add(user, park);
					if(i == 0)
					{
						//���ʧ��
						response.getWriter().print("<script language='javascript'>alert('���ͣ������Ϣʧ�ܣ�');window.location.href='/managerwebb/main.jsp';</script>");
						System.out.println("����û���Ϣʧ��");
					}else {
						//��ӳɹ�
						request.setAttribute("Park", park);
						response.getWriter().print("<script language='javascript'>alert('���ͣ������Ϣ�ɹ���');window.location.href='/managerwebb/main.jsp';</script>");
						//request.getRequestDispatcher("files/ParkInfo.jsp").forward(request,response);
						return;
					}
			}catch(Exception e)
				{	e.printStackTrace(); }
			
		}else{
			//�޸��û�����ͣ������Ϣ
			try {
				i = parkImpl.update(user, park);
				if( i == 0)
				{//�޸��û�����ͣ������Ϣʧ��
					response.getWriter().print("<script language='javascript'>alert('�޸�ͣ������Ϣʧ�ܣ�');window.location.href='/managerwebb/main.jsp';</script>");
					System.out.println("�޸�ͣ������Ϣʧ��");
				}else {
				//�޸��û�����ͣ������Ϣ�ɹ�
					request.setAttribute("Park", park);
					response.getWriter().print("<script language='javascript'>alert('�޸�ͣ������Ϣ�ɹ���');window.location.href='/managerwebb/main.jsp';</script>");
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
