package com.rahul.Login;



import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
public class UserImpl implements UserInterface{
	private static final Logger logger = Logger.getLogger(UserImpl.class);
	JdbcTemplate jdbcTemplate;
	String pass;
	//Fetching DatSource to the Jdbc Template
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//This Method Is Invoking from the Login Controller
public int validuser(String username,String password,String role){
	System.out.println("entered");
	logger.info("enttering");

	String sql="select user_password from admin_login where user_name= ? and user_role= ?";

	try{
	 pass=jdbcTemplate.queryForObject(sql,new Object[]{username,role}, String.class);
		if(password.equals(pass)){
			return 1;
		}else{
			return 2;
		}
	
		}catch(EmptyResultDataAccessException e){
			logger.info("username not found");
	return 3;
		}catch(Exception e2){
			return 4;
		}
	
	}

public String click(String username){
	logger.info("entering");
	String sql="select user_password from admin_login where user_name= ?";
	try{
		 pass=jdbcTemplate.queryForObject(sql,new Object[]{username}, String.class);
		 return pass;
		}catch(EmptyResultDataAccessException e){
			logger.info("username not found");
			return pass;
			}
		}

public LoginModel get_Userphno(String username) {
	logger.info("entered");
	try{
	String sql="select phno from admin_login where user_name=?";
	long phno=jdbcTemplate.queryForObject(sql,new Object[]{username},long.class);
	
	LoginModel l=new LoginModel();
	l.setUser_phno(phno);
	l.setPassword(click(username));
	return l;
	}catch(Exception e){
		return null;
	}
}

public int ChangePassword(String password,String username) {
	
		String sql="update admin_login set user_password=? where user_name=?";
		int n=jdbcTemplate.update(sql,new Object[]{password,username});
		return n;
}



	}
