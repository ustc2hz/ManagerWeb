package cn.LBS.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.LBS.Dao.UserDao;
import cn.LBS.Dao.UserDaoJdbcImpl;
import cn.LBS.model.user;

/**
 * �û���½ 
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����û���½��Ϣ
		request.setCharacterEncoding("utf-8");
		//��ȡApplication
		ServletContext application=this.getServletContext();
		//��װuser
		user user = new user();
		user existUser = new user();
		
		user.setUsername(request.getParameter("txtName"));
		user.setUserpassword(request.getParameter("txtPwd"));
		System.out.println(user.getUsername());
		System.out.println(user.getUserpassword());
		List list = new ArrayList();
		UserDao dao=new UserDaoJdbcImpl();		
		list = dao.find(user);
		// �жϽ��
		if(list	== null || list.size()== 0 ) 
		{			// û�в鵽 --- ��½ʧ��
			request.setAttribute("msg", "�û��������������");
			request.getRequestDispatcher("/login.jsp").forward(request,response);
			return;
		}else{  
			// ���� --- ��½�ɹ�	 ����½�ɹ���Ϣ ������session
			System.out.println(list.get(0).toString());
			System.out.println(list.get(1).toString());
			existUser.setUsername(list.get(0).toString());
			existUser.setUserpassword(list.get(1).toString());
			application.setAttribute("existUser", existUser);//�����û��ɹ�����session����¼username��ֵ������ҳ����ʾ

			// �жϼ�ס�û���������
			if ("yes".equals(request.getParameter("remember"))) {
				// ��ѡ�� //��ס�û����û��������룬�´ο���ֱ�ӵ�½
				Cookie usernameCookie = new Cookie("username", existUser.getUsername());
				usernameCookie.setMaxAge(60 * 60 * 24 );//����ʱ��Ϊ1��
				usernameCookie.setPath("/managerwebb");
				response.addCookie(usernameCookie);

				Cookie passwordCookie = new Cookie("password", existUser
						.getUserpassword());
				passwordCookie.setMaxAge(60 * 60 * 24 * 30);
				passwordCookie.setPath("/managerwebb");
				response.addCookie(passwordCookie);
			}
			response.sendRedirect("/managerwebb/main.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
