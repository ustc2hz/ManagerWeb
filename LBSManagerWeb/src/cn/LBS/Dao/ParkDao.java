package cn.LBS.Dao;

import java.util.List;

import cn.LBS.model.park;
import cn.LBS.model.user;

public interface ParkDao {
	/*
	 * ����ͣ�����������Ϣ
	 */
	List  find(user user);//����ͣ������Ϣ
	
	int add(user usr, park park) throws Exception;//�޸�ͣ������Ϣ
	
	int update(user usr, park park)throws Exception;
}
