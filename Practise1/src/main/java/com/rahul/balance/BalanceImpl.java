package com.rahul.balance;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.rahul.customers.Custmer_details;
import com.rahul.customers.UserMapper;

public class BalanceImpl implements BalanceInterface{
	private JdbcTemplate jdbcTemplate;
	DataSource dataSource;
	Logger logger=Logger.getLogger(BalanceImpl.class);
	public void setDataSource(DataSource dataSource) {
		this.dataSource=dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	public List<BalanceDetails> findAll() {
		logger.info("entering");
		// TODO Auto-generated method stub
		String sql = "SELECT loan_id,date1,Narration,Debit,Credit,Balance from balance_details";
		List<BalanceDetails> li= (List<BalanceDetails>)jdbcTemplate.query(sql, new BlanceMapper());
		logger.info("query excuted");
		return li;
		
	}

	public List<BalanceDetails> search(int loan_id) {
		// TODO Auto-generated method stub
		
			String sql ="SELECT loan_id,date1,Narration,Debit,Credit,Balance from balance_details where loan_id=? order by date1 desc";
			List<BalanceDetails> al=(List<BalanceDetails>)jdbcTemplate.query(sql,new Object[]{ loan_id},new BlanceMapper());
			return al;
		
    }


	public int BalanceView(BalanceDetails lm) {
		logger.info("entering");
		try{
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("baldetails");
			SqlParameterSource in = new MapSqlParameterSource()
					.addValue("loan_id",lm.getLoan_id())
					.addValue("date1",lm.getDate1())
					.addValue("Narration",lm.getNarration())
					.addValue("Debit",lm.getDebit())
					.addValue("Crerdit",lm.getCredit())
					.addValue("Balance",lm.getBalance());

					Map<String, Object> out = jdbcCall.execute(in);
					
					int n=(Integer) out.get("id");
					System.out.println(n);
			logger.info("leaving");
			return n;
		}
			catch(Exception e){
				logger.error(e);
				return 0;
			}
		}


	
	}

