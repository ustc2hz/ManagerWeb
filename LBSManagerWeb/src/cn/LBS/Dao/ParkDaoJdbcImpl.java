package cn.LBS.Dao;

import java.util.ArrayList;
import java.util.List;

import cn.LBS.db.DBTools;

import cn.LBS.db.DBService;
import cn.LBS.model.park;
import cn.LBS.model.user;

public class ParkDaoJdbcImpl implements ParkDao{

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
					"select park_name,park_num,park_pay,park_priceOne,park_priceTwo,park_priceThree,park_priceFour from parkinfo ")
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
	/*
	 * 更新停车场信息并返回
	 */
	@Override
	public int update(user user ,park park) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql;
		int i = 0;
		sql = new StringBuffer();
		try{
			sql.append("update parkinfo set park_name=?, park_num=?, park_pay=?, park_priceOne=?,park_priceTwo=?,park_priceThree=?,park_priceFour=?").append("where admin_name ='").append(user.getUsername()).append("'");
			params = new String[] { park.getPark_name().trim(),
					(park.getPark_num()+"").trim(), park.getPark_pay().trim(),
					(park.getPark_priceOne()+"").trim(), (park.getPark_priceTwo()+"").trim(),(park.getPark_priceThree()+"").trim(),(park.getPark_priceFour()+"").trim()};
			i = DBTools.update(sql.toString(), params);

			return i;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	/*
	 * 增加用户的停车场信息)
	 */
	public int add(user user ,park park) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql;
		int i=0;
		sql = new StringBuffer();
		try{
			sql.append("insert into parkinfo(admin_name,park_name,park_num,park_pay,park_priceOne,park_priceTwo,park_priceThree,park_priceFour) values (?,?,?,?,?,?,?,?)");
			params = new String[] { user.getUsername().trim(),park.getPark_name().trim(),
					(park.getPark_num()+"").trim(), park.getPark_pay().trim(),
					(park.getPark_priceOne()+"").trim(), (park.getPark_priceTwo()+"").trim(),(park.getPark_priceThree()+"").trim(),(park.getPark_priceFour()+"").trim()};
			i = DBTools.update(sql.toString(), params);
			return i;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
