package cn.LBS.Dao;

import java.util.List;

import cn.LBS.model.actualpark;
import cn.LBS.model.user;

public interface ActualParkDao {
	List find(user user);
	int update(user user,actualpark actualpark);
	int add(user usr,actualpark actualpark);
}
