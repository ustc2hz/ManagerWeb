package cn.LBS.controller;

import java.io.IOException;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.component.UpdateModelException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.LBS.Dao.UserDao;
import cn.LBS.Dao.UserDaoJdbcImpl;
import cn.LBS.model.user;

public class ModifyPassWord extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		user user=new user();
		UserDao dao=new UserDaoJdbcImpl();
		
//			user.setUserpassword(request.getParameter("OldPassword"));
			ServletContext appliation=this.getServletContext();
			user=(user)appliation.getAttribute("existUser");
			String old=request.getParameter("OldPassword");
			String newString=user.getUserpassword();
			if( old.equals(newString))
			{
				//旧密码输入正确
				user.setUserpassword(request.getParameter("NewPassword"));
				//修改密码；
				dao.update(user);
			//	request.setAttribute("msg", "密码修改成功！");
				response.getWriter().print("<script language='javascript'>alert('密码修改成功！');window.location.href='/managerwebb/main.jsp';</script>");
				return;
			}
			else {
				//旧密码输入不正确
				request.setAttribute("msg", "密码输入错误！");
				response.getWriter().print("<script language='javascript'>alert('密码输入错误！');window.location.href='/managerwebb/files/UserInfo.jsp';</script>");
				return;			
			}
//			user.setUsername(user.getUsername());
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.setContentType("text/html");
		doGet(request, response);
	}

}
