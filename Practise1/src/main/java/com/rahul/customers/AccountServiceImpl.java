package com.rahul.customers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class AccountServiceImpl implements AccountService_Interface{
	private JdbcTemplate jdbcTemplate;
	DataSource dataSource;
	Logger logger=Logger.getLogger(AccountServiceImpl.class);
	public void setDataSource(DataSource dataSource) {
		this.dataSource=dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	public List<Custmer_details> findAll() {
		logger.info("entering");
		// TODO Auto-generated method stub
		String sql = "SELECT custm_id,custm_accountno,custm_name,custm_dob,custm_regdate,custm_status from customer_details";
		List<Custmer_details> li= (List<Custmer_details>)jdbcTemplate.query(sql, new UserMapper());
	logger.info("query excuted");
return li;
		
	}

	public List<Custmer_details> search(String keyword) {
		// TODO Auto-generated method stub
		
			String sql = "SELECT custm_id,custm_accountno,custm_name,custm_dob,custm_regdate,custm_status from customer_details where custm_name=?";
			
		
		
			List<Custmer_details> al=(List<Custmer_details>)jdbcTemplate.query(sql,new Object[]{ keyword},new UserMapper());
			
			return al;
		
    }


	public int CustomerInsert(Custmer_details lm) {
		logger.info("entering");
		try{
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("customerprocedure");
			SqlParameterSource in = new MapSqlParameterSource()
					.addValue("custm_name",lm.getCustmr_name())
					
					.addValue("custm_accountno",lm.getCustmr_accuntno())
					.addValue("custm_aadharno",lm.getCustmr_adharno())
					.addValue("custm_category",lm.getCustmr_categry())
					.addValue("custm_gender",lm.getCustmr_gender())
					.addValue("custm_presentaddress",lm.getCustmr_present_addrs())
					
					.addValue("custm_permanentaddress",lm.getCustmr_perment_addrs())
					.addValue("custm_nationality",lm.getCustmr_nation())
					.addValue("custm_martialstatus",lm.getCustmr_martial())
					.addValue("custm_dob",lm.getCustmr_dob())
					.addValue("custm_mobilenumber",lm.getCustmr_phno())
					.addValue("custm_email",lm.getCustmr_email());
					Map<String, Object> out = jdbcCall.execute(in);
					
					int n=(Integer) out.get("id");
					
			logger.info("leaving");
			return n;
		}
			catch(Exception e){
				logger.error(e);
				return 0;
			}
		}
	public boolean validatecaccountno(long accountno) {
		String sql = "SELECT custm_accountno from customer_details where custm_accountno=?";
		try {
			 Long caccountno=jdbcTemplate.queryForObject(sql,new Object[]{accountno}, Long.class);
				
				return true;
				}
		catch (Exception e) {
			// TODO: handle exception
			return false;

			
		}
	}
}
