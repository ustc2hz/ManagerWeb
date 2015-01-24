package cn.LBS.Dao;

import java.util.ArrayList;
import java.util.List;

import cn.LBS.db.DBService;
import cn.LBS.model.Order;

public class OrderDaoJdbcImpl implements OrderDao {

	public void add(Order order) throws Exception {
	}

	// username = ' or 1=1 or username=' java--class
	public List find(Order order) {
		StringBuffer sql;
		DBService db;
		sql = new StringBuffer();
		db = null;
		List list = new ArrayList();
		try {
			db = new DBService();
			sql.append("select * from parktable.order");
			list = db.getStringList(sql.toString());
		} catch (Exception e) {// 数据库的实现有问题 ，你得到的list
								// 是全是string在一起的，根本出不来一行一行的，全是字符串
			e.printStackTrace();
		} finally {
			db.close();
		}
		return list;
	}

	public void update(Order order) {
	}
	// }
}
