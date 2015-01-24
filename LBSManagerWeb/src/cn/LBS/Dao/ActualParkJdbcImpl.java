package cn.LBS.Dao;

import java.util.ArrayList;
import java.util.List;

import cn.LBS.db.DBService;
import cn.LBS.db.DBTools;
import cn.LBS.model.actualpark;
import cn.LBS.model.user;

public class ActualParkJdbcImpl implements ActualParkDao{
	private String[] params;
	@Override
	public List find(user user) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		StringBuffer sql;
		DBService db;
		sql = new StringBuffer();
		db = null;
		try{
			db = new DBService();
			sql.append(
					"select park_num,park_public,park_order,park_remain from parkactual ")
					.append(" where admin_name='").append(user.getUsername()).append("'");
			list = db.getStringList(sql.toString());
			//System.out.println("查找成功！");
			System.out.println("查询是否存在用户的停车场信息 list的长度："+list.size());
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return list;
	}

	@Override
	public int add(user user, actualpark actualpark) {
		// TODO Auto-generated method stub
		StringBuffer sql;
		int i=0;
		sql = new StringBuffer();
		try{
			sql.append("insert into parkactual(admin_name,park_num,park_public,park_order,park_remain) values (?,?,?,?,?)");
			params = new String[] { user.getUsername().trim(),(actualpark.getPark_num()+"").trim(),
					(actualpark.getPark_public()+"").trim(),(actualpark.getPark_order()+"").trim(),
					(actualpark.getPark_remain()+"").trim()};
			i = DBTools.update(sql.toString(), params);
			return i;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int update(user user,actualpark actualpark) {
		// TODO Auto-generated method stub
		StringBuffer sql;
		int i = 0;
		sql = new StringBuffer();
		try{
			sql.append("update parkactual set park_num=?, park_public=?, park_order=?, park_remain=?").append("where admin_name ='").append(user.getUsername()).append("'");
			params = new String[] { (actualpark.getPark_num()+"").trim(),
					(actualpark.getPark_public()+"").trim(), (actualpark.getPark_order()+"").trim(),
					(actualpark.getPark_remain()+"").trim()};
			i = DBTools.update(sql.toString(), params);

			return i;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
