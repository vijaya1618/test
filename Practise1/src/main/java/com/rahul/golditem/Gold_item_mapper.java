package com.rahul.golditem;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;



public class Gold_item_mapper implements RowMapper<GoldModel>{

	 public GoldModel mapRow(ResultSet rs, int rownum) throws SQLException {
			
			GoldModel gold_item=new GoldModel();
			gold_item.setGold_item_id(rs.getInt("loan_item_id"));
			gold_item.setGold_item_lending_rate(rs.getDouble("loan_item_lending_rate"));
			gold_item.setGold_item_purity(rs.getInt("loan_item_karet"));
			gold_item.setGold_item_market_rate(rs.getDouble("loan_item_market_rate"));
			gold_item.setGold_item_name(rs.getString("loan_item_type"));
			gold_item.setGold_item_date(String.valueOf(rs.getDate("loan_item_date")));
			return gold_item;
		}
}
