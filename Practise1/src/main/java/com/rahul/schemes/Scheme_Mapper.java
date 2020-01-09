package com.rahul.schemes;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rahul.customers.Custmer_details;

public class Scheme_Mapper implements RowMapper<Scheme_Model>{
	 public Scheme_Model mapRow(ResultSet rs, int rownum) throws SQLException {
			
			Scheme_Model sm=new Scheme_Model();
			sm.setScheme_id(rs.getInt("scheme_id"));
			sm.setScheme_name(rs.getString("scheme_name"));
			sm.setScheme_product(rs.getString("scheme_product"));
			sm.setScheme_min_ammount(rs.getDouble("scheme_min_ammount"));
			sm.setScheme_max_ammount(rs.getDouble("scheme_max_ammount"));
			sm.setScheme_aplicable_from(rs.getDate("scheme_aplicable_from"));
			sm.setScheme_aplicable_to(rs.getDate("scheme_aplicable_to"));
			sm.setScheme_loan_period(rs.getInt("scheme_loan_period"));
			sm.setScheme_intrest(rs.getDouble("scheme_intrest"));
			sm.setScheme_intrst_days(rs.getInt("scheme_intrst_days"));
			sm.setScheme_calc(rs.getString("scheme_calc"));
			sm.setScheme_loan_type(rs.getString("scheme_loan_type"));
			sm.setScheme_compound(rs.getString("scheme_compound"));
			sm.setScheme_payment_freq(rs.getInt("scheme_payment_freq"));
			sm.setScheme_pay_advance(rs.getString("scheme_pay_advance"));
			return sm;
		}

}
