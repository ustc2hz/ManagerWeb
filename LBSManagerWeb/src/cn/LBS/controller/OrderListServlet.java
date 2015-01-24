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
 * 订单列表
 * 
 * @author Administrator
 * 
 */
public class OrderListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 获取Application
		ServletContext application = this.getServletContext();
		// order没有对应的javabean吗 我还没有建呢，我建好后再
		Order order = new Order();

		List list = new ArrayList();
		OrderDao dao = new OrderDaoJdbcImpl();
		list = dao.find(order);
		// 判断结果
		if (list == null || list.size() == 0) { // 没有查到 --- 登陆失败
			response.getWriter()
					.print("<script language='javascript'>alert('无订单信息！');window.location.href='/managerwebb/main.jsp';</script>");
			System.out.println("订单信息无");
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
