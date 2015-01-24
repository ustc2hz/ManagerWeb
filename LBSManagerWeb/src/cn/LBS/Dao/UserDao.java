package cn.LBS.Dao;

import java.util.List;

import cn.LBS.model.user;

public interface UserDao {

	List find(user user);//查找用户

	void add(user user) throws Exception;//增加用户

	void update(user user);

}