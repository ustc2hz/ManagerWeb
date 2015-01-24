package cn.LBS.Dao;

import java.util.List;

import cn.LBS.model.park;
import cn.LBS.model.user;

public interface ParkDao {
	/*
	 * 查找停车场的相关信息
	 */
	List  find(user user);//查找停车场信息
	
	int add(user usr, park park) throws Exception;//修改停车场信息
	
	int update(user usr, park park)throws Exception;
}
