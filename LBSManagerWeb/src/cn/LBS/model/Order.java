package cn.LBS.model;

import java.sql.Date;

import com.sun.corba.se.spi.ior.iiop.JavaCodebaseComponent;

public class Order implements java.io.Serializable{
	private int orderId;
	private String driver_id;
	private String admin_name;
	private Date order_date;
	private double order_price;
	private String order_status;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public double getOrder_price() {
		return order_price;
	}
	public void setOrder_price(double order_price) {
		this.order_price = order_price;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", driver_id=" + driver_id
				+ ", admin_name=" + admin_name + ", order_date=" + order_date
				+ ", order_price=" + order_price + ", order_status="
				+ order_status + "]";
	}
	
}
