package cn.LBS.Dao;

import java.util.List;

import cn.LBS.model.user;

public interface UserDao {

	List find(user user);//�����û�

	void add(user user) throws Exception;//�����û�

	void update(user user);

}