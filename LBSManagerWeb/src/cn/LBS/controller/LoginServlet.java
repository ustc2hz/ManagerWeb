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
 * 用户登陆 
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得用户登陆信息
		request.setCharacterEncoding("utf-8");
		//获取Application
		ServletContext application=this.getServletContext();
		//封装user
		user user = new user();
		user existUser = new user();
		
		user.setUsername(request.getParameter("txtName"));
		user.setUserpassword(request.getParameter("txtPwd"));
		System.out.println(user.getUsername());
		System.out.println(user.getUserpassword());
		List list = new ArrayList();
		UserDao dao=new UserDaoJdbcImpl();		
		list = dao.find(user);
		// 判断结果
		if(list	== null || list.size()== 0 ) 
		{			// 没有查到 --- 登陆失败
			request.setAttribute("msg", "用户名或者密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request,response);
			return;
		}else{  
			// 查找 --- 登陆成功	 将登陆成功信息 ，保存session
			System.out.println(list.get(0).toString());
			System.out.println(list.get(1).toString());
			existUser.setUsername(list.get(0).toString());
			existUser.setUserpassword(list.get(1).toString());
			application.setAttribute("existUser", existUser);//查找用户成功，用session来记录username的值，在主页中显示

			// 判断记住用户名和密码
			if ("yes".equals(request.getParameter("remember"))) {
				// 勾选了 //记住用户的用户名和密码，下次可以直接登陆
				Cookie usernameCookie = new Cookie("username", existUser.getUsername());
				usernameCookie.setMaxAge(60 * 60 * 24 );//保持时间为1天
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
