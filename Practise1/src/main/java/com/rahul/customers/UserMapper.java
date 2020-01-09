package com.rahul.customers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<Custmer_details>{

	 public Custmer_details mapRow(ResultSet rs, int rownum) throws SQLException {
			
			Custmer_details a=new Custmer_details();
		
			a.setCustmr_id(rs.getInt("custm_id"));
			a.setCustmr_accuntno(rs.getInt("custm_accountno"));
			a.setCustmr_name(rs.getString("custm_name"));
			a.setCustmr_dob(rs.getDate("custm_dob"));
			a.setCustmr_regdate(rs.getDate("custm_regdate"));
			a.setCustmr_status(rs.getString("custm_status"));
			
			return a;
		}



	

}
