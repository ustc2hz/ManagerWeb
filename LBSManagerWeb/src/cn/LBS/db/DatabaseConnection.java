package cn.LBS.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库公共类
 * 
 * @author Administrator
 * 
 */
public class DatabaseConnection {
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/parktable";// 数据库的名字
	private static final String DBUSER = "root";
	private static final String DBPASSWORD = "root";
	private Connection conn = null;

	// 覆盖默认构造方法，实现数据库驱动的加载
	public DatabaseConnection() throws Exception {
		try {
			Class.forName(DBDRIVER);
			this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		} catch (Exception e) {
			throw e;
		}
	}

	public Connection getConnection() {
		if (this.conn == null) {
			System.out.println("数据库连接不成功");
		} else {
			System.out.println("数据库连接成功");
		}
		return this.conn;
	}

	// 关闭数据库连接
	public void close() throws Exception {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (Exception e) {
				throw e;
			}
		}

	}
}
