package cn.LBS.model;

/*
 * user的JavaBean 用来存放数据的
 */
public class user {
	private String admin_name;
	private String admin_password;
	public String getUsername() {
		return admin_name;
	}
	public void setUsername(String username) {
		this.admin_name = username;
	}
	public String getUserpassword() {
		return admin_password;
	}
	public void setUserpassword(String userpassword) {
		this.admin_password = userpassword;
	}	
}
