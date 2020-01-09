package com.rahul.Nominee;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;



public class Nominee_mapper implements RowMapper<Nominee_Model>{

	 public Nominee_Model  mapRow(ResultSet rs, int rownum) throws SQLException {
			
				Nominee_Model a=new Nominee_Model();
				a.setCustm_id(rs.getInt("custm_id"));
				a.setCustm_name(rs.getString("custm_name"));
				a.setNominee_age(rs.getInt("nominee_age"));
				a.setNominee_dob(rs.getDate("nominee_dob"));
				a.setNominee_id(rs.getInt("nominee_id"));
				a.setNominee_name(rs.getString("nominee_name"));
				a.setNominee_relation(rs.getString("nominee_relation"));
			return a;
		}

}