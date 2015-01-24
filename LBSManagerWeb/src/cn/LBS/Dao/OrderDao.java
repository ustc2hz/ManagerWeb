package cn.LBS.Dao;

import java.util.List;

import cn.LBS.model.Order;
import cn.LBS.model.user;

public interface OrderDao {

	List find(Order order);//查找订单

	void add(Order order) throws Exception;//增加订单

	void update(Order order);

}