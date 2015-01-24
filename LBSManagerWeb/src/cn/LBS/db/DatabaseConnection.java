package cn.LBS.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * ���ݿ⹫����
 * 
 * @author Administrator
 * 
 */
public class DatabaseConnection {
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/parktable";// ���ݿ������
	private static final String DBUSER = "root";
	private static final String DBPASSWORD = "root";
	private Connection conn = null;

	// ����Ĭ�Ϲ��췽����ʵ�����ݿ������ļ���
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
			System.out.println("���ݿ����Ӳ��ɹ�");
		} else {
			System.out.println("���ݿ����ӳɹ�");
		}
		return this.conn;
	}

	// �ر����ݿ�����
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
