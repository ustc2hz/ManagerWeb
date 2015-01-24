package cn.LBS.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.LBS.Dao.UserDao;
import cn.LBS.Dao.UserDaoJdbcImpl;
import cn.LBS.model.user;


import com.sun.jmx.snmp.UserAcl;

public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		String checkcode=request.getParameter("txtValditateCode");
		String checkcode_session=(String)request.getSession().getAttribute("checkcode_session");
		request.getSession().invalidate();//������֤��
		if(checkcode_session == null || !checkcode_session.equals(checkcode) )
		{
			request.setAttribute("msg", "��֤���������");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		//����bean
		user user=new user();
		user existUser = new user();
		List list = new ArrayList();
		user.setUsername(request.getParameter("txtName"));
		user.setUserpassword(request.getParameter("txtPwd"));
		
		UserDao dao = new UserDaoJdbcImpl();	
		list = dao.find(user);
		boolean flag = false;
		if (list  == null || list.size() == 0) {//�û���������
			flag=true;
		}else{
			flag=false;
		}
		response.setContentType("text/html;charset=utf-8");
			if(flag == false)
			{
				request.setAttribute("msg", "�û����Ѿ�����!");
				request.getRequestDispatcher("/regist.jsp").forward(request, response);
//				response.setHeader("refresh","5;url=/managerwebb/login.jsp");//5������ת����½����
			}
			else {
				try {
					dao.add(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("msg", "�û�ע��ɹ�!");
				request.getRequestDispatcher("/regist.jsp").forward(request, response);
//				response.setHeader("refresh","5;url=/managerwebb/login.jsp");//5������ת����½����
			}
		}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
