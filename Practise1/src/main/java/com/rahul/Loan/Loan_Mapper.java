package com.rahul.Loan;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;



public class Loan_Mapper implements RowMapper<Loan_Model>{

	 public Loan_Model mapRow(ResultSet rs, int rownum) throws SQLException {
		 Loan_Model lm=new Loan_Model();
		 lm.setLoan_id(rs.getInt("loan_id"));
		 lm.setCustmer_id(rs.getInt("custmer_id"));
		 lm.setCustmer_name(rs.getString("custmer_name"));
		 lm.setNomine_id(rs.getInt("nominee_id"));
		 lm.setNominee_name(rs.getString("nominee_name"));
		 lm.setItem_type(rs.getString("item_type"));
		 lm.setItem_name(rs.getString("item_name"));
		 lm.setItem_wt(rs.getDouble("item_wt"));
		 lm.setItem_purity(rs.getInt("item_purity"));
		 lm.setItem_market_rate(rs.getDouble("item_marketrate"));
		 lm.setItem_lending_rate(rs.getDouble("item_lendingrate"));
		 lm.setItem_total_amount(rs.getDouble("item_totalrate"));
		 lm.setImage(rs.getBytes("item_image"));
		 lm.setScheme_name(rs.getString("scheme_name"));
		 lm.setPayment_basis(rs.getString("payment_basis"));
		 lm.setTenure(rs.getInt("tenure"));
		 lm.setScheme_intrest(rs.getDouble("intrest"));
		 lm.setLoan_date(rs.getDate("loan_date"));
		 lm.setFrq_of_pay(rs.getString("frequency_of_pay"));
		 lm.setReq_amount(rs.getDouble("req_amount"));
		 lm.setAvaliable_amount(rs.getDouble("avail_amount"));
		 lm.setIntrest_type(rs.getString("intrest_type"));
		 lm.setApprisal_name(rs.getString("appraisal_name"));
		 lm.setStatus(rs.getString("loan_status"));
		 lm.setAprvl_date(rs.getDate("approv_date"));
			return lm;
		}
}
