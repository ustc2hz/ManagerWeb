package cn.LBS.Dao;

import java.util.List;

import cn.LBS.model.Order;
import cn.LBS.model.user;

public interface OrderDao {

	List find(Order order);//���Ҷ���

	void add(Order order) throws Exception;//���Ӷ���

	void update(Order order);

}