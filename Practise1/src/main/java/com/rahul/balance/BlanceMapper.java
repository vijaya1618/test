package com.rahul.balance;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import org.springframework.jdbc.core.RowMapper;

public class BlanceMapper implements RowMapper<BalanceDetails>{

	 public BalanceDetails mapRow(ResultSet rs, int rownum) throws SQLException {
			
		 	BalanceDetails a=new BalanceDetails();
		
			a.setLoan_id(rs.getInt("loan_id"));
			a.setDate1(rs.getDate("date1"));
			a.setNarration(rs.getString("Narration"));
			a.setDebit(rs.getFloat("Debit"));
			a.setCredit(rs.getFloat("Credit"));
			a.setBalance(rs.getFloat("Balance"));
			return a;
		}	
}
