package com.rahul.Login;

public class LoginModel {
       private String username;
       private String password;
       private String user_role;
       private long user_phno;
       public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	public LoginModel(){
    	   
       }
       public LoginModel(String username,String password){
    	   this.username=username;
    	   this.password=password;
       }
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getUser_phno() {
		return user_phno;
	}
	public void setUser_phno(long user_phno) {
		this.user_phno = user_phno;
	}
	
}
