package cn.LBS.Dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.LBS.db.DBService;


import cn.LBS.exception.DaoException;
import cn.LBS.model.user;
import cn.LBS.utils.JdbcUtils;
import cn.LBS.db.DBTools;

public class UserDaoJdbcImpl implements cn.LBS.Dao.UserDao {

	public void add(user user) throws Exception {
		StringBuffer sql = new StringBuffer();
		Object[] params = { user.getUsername().trim(), user.getUserpassword(),};
		try{
			sql.append("insert into admin (admin_name,admin_password) values(?,?)");
			
			DBTools.update(sql.toString(), params);
			System.out.println("update success!");
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	//username = ' or 1=1 or username='  java--class
	public List find(user user) {
		StringBuffer sql;
		DBService db;
		sql = new StringBuffer();
		db = null;
		List list = new ArrayList();
		try{
			db = new DBService();
			sql.append(
					"select admin_name,admin_password from admin ")
					.append(" where admin_name='").append(user.getUsername()).append("'")
					.append(" and admin_password='").append(user.getUserpassword())
					.append("'");
			list = db.getStringList(sql.toString());
			//System.out.println("≤È’“≥…π¶£°");
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return list;
	}

	public void update(user user) {
		StringBuffer sql = new StringBuffer();
		String[] params = null;
		try{
			sql.append("update admin set admin_password=? where admin_name=?");
			params = new String[] { user.getUserpassword().trim(),
					user.getUsername().trim() };
			DBTools.update(sql.toString(), params);
			System.out.println("update success!");
		}catch (Exception e) {
			throw new DaoException(e);
		}
	}
}
