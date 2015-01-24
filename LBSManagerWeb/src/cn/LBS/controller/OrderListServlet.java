package cn.LBS.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.LBS.Dao.OrderDao;
import cn.LBS.Dao.OrderDaoJdbcImpl;
import cn.LBS.model.Order;

/**
 * �����б�
 * 
 * @author Administrator
 * 
 */
public class OrderListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// ��ȡApplication
		ServletContext application = this.getServletContext();
		// orderû�ж�Ӧ��javabean�� �һ�û�н��أ��ҽ��ú���
		Order order = new Order();

		List list = new ArrayList();
		OrderDao dao = new OrderDaoJdbcImpl();
		list = dao.find(order);
		// �жϽ��
		if (list == null || list.size() == 0) { // û�в鵽 --- ��½ʧ��
			response.getWriter()
					.print("<script language='javascript'>alert('�޶�����Ϣ��');window.location.href='/managerwebb/main.jsp';</script>");
			System.out.println("������Ϣ��");
			return;
		} else {
			request.setAttribute("order", order);
			request.getRequestDispatcher("/files/OrderInfo.jsp").forward(
					request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
