package com.rahul.golditem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;



public class Gold_item_Dao_impl implements Gold_item_Dao{
Logger logger=Logger.getLogger(Gold_item_Dao_impl.class);
JdbcTemplate jdbcTemplate;
DataSource dataSource;
//Fetching DatSource to the Jdbc Template
public void setDataSource(DataSource dataSource) {
	
	this.dataSource=dataSource;
	this.jdbcTemplate = new JdbcTemplate(dataSource);
	
}
//This Method Return The 75% lending Gold rate
public double get_lending_gold_rate(double gold_market_rate){
	logger.info("entering");
	double lending_percentage=75;
	double gold_lending_rate=(lending_percentage*gold_market_rate)/100;
	logger.info("leaving");
	return gold_lending_rate;
	
}
//this method return the gold_item details
public List<GoldModel> getallitems(){
	logger.info("enttering");
	List<GoldModel> gl=null;
	String sql="select loan_item_id,loan_item_type,loan_item_karet,loan_item_market_rate,loan_item_lending_rate,loan_item_date from gold_loan_item";
	try{
	gl=(List<GoldModel>)jdbcTemplate.query(sql, new Gold_item_mapper());
	}catch(CannotGetJdbcConnectionException ce){
		logger.error("DataBase Connection Not Established");
	}catch(Exception e){
		e.printStackTrace();
	}
	logger.info("leaving");
	return gl;
}
//this method is for adding the gold items into db
public int add_gold_item(String item_name,int purity,double gold_market_rate,double gold_lending_rate){
	logger.info("entering");
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("gold_loan_item_procedure");
		SqlParameterSource in = new MapSqlParameterSource().addValue("loanitemtype", item_name)
				.addValue("loanitemkaret", purity)
				.addValue("loanitemmrate", gold_market_rate)
				.addValue("loanitemlrate", gold_lending_rate);
				Map<String, Object> out = jdbcCall.execute(in);
				
				int n= ((BigDecimal) out.get("loanid")).intValue();
				logger.info("leaving");
				return n;
}
public GoldModel get_Golditem(int id) {
	logger.info("enttering");
	String sql="select loan_item_id,loan_item_type,loan_item_karet,loan_item_market_rate,loan_item_lending_rate,loan_item_date from gold_loan_item where loan_item_id=?";
	List<GoldModel> gl=(List<GoldModel>)jdbcTemplate.query(sql,new Object[]{id},new Gold_item_mapper());
	logger.info("leaving");
	return gl.get(0);
}
public int update_itemDetails(GoldModel gm) {
	logger.info("enttering");
	String sql="update gold_loan_item set loan_item_type=?,loan_item_karet=?,loan_item_market_rate=?,loan_item_lending_rate=?,loan_item_date=CURRENT_TIMESTAMP where loan_item_id=?";
	int n=jdbcTemplate.update(sql, new Object[]{gm.getGold_item_name(),gm.getGold_item_purity(),gm.getGold_item_market_rate(),gm.getGold_item_lending_rate(),gm.getGold_item_id()});
	return n;
}
}
