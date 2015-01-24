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
		 * ��ȡ����Ϣ������ͣ������Ϣ���з�װ
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
		System.out.println("ͣ�������ԣ�"+list.size());
		if( list.size()  == 0)
		{//û�й����û�������Ϣ��������Ҫ�����ӹ����û�����ͣ������Ϣ��
			try{
					i = actualImpl.add(user, actualpark);
					if(i == 0)
					{
						//���ʧ��
						response.getWriter().print("<script language='javascript'>alert('���ʵʱ��λ��Ϣʧ�ܣ�');window.location.href='/managerwebb/main.jsp';</script>");
						System.out.println("���ʵʱ��λ��Ϣʧ��");
					}else {
						//��ӳɹ�
						request.setAttribute("actualpark", actualpark);
						response.getWriter().print("<script language='javascript'>alert('���ʵʱ��λ��Ϣ�ɹ���');window.location.href='/managerwebb/main.jsp';</script>");
						//request.getRequestDispatcher("files/ParkInfo.jsp").forward(request,response);
						return;
					}
			}catch(Exception e)
				{	e.printStackTrace(); }
			
		}else{
			//�޸��û�����ͣ������Ϣ
			try {
				i = actualImpl.update(user, actualpark);
				if( i == 0)
				{//�޸��û�����ͣ������Ϣʧ��
					response.getWriter().print("<script language='javascript'>alert('�޸ĳ�λʵʱ��Ϣʧ�ܣ�');window.location.href='/managerwebb/main.jsp';</script>");
					System.out.println("�޸ĳ�λʵʱ��Ϣʧ��");
				}else {
				//�޸��û�����ͣ������Ϣ�ɹ�
					request.setAttribute("actualpark", actualpark);
					response.getWriter().print("<script language='javascript'>alert('�޸ĳ�λʵʱ��Ϣ�ɹ���');window.location.href='/managerwebb/main.jsp';</script>");
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
