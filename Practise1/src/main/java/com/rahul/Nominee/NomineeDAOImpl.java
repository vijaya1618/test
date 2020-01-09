package com.rahul.Nominee;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import com.rahul.Nominee.NomineeDAOImpl;
import com.rahul.Nominee.Nominee_Model;



public class NomineeDAOImpl implements NomineeDAO{

	protected JdbcTemplate jdbcTemplate;
	DataSource dataSource;
	protected DataFieldMaxValueIncrementer taskIncer;
	private static final Logger logger = Logger.getLogger(NomineeDAOImpl.class);
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource=dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int NomineeInsert(Nominee_Model nm){
	logger.info("entering");
	SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("nominee_procedure");
	
	SqlParameterSource in = new MapSqlParameterSource()
			
			.addValue("custm_id",nm.getCustm_id())
			.addValue("nominee_name",nm.getNominee_name())
			.addValue("nominee_dob",nm.getNominee_dob())
			.addValue("nominee_relation",nm.getNominee_relation())
			.addValue("nominee_age",nm.getNominee_age());
			Map<String, Object> out = jdbcCall.execute(in);
			int n=  (Integer) out.get("id");
			logger.info("leaving");
			return n;
	}
	public List<Nominee_Model> findAll() {
		logger.info("entering");
		String sql = "SELECT  nominee_id,custm_id,nominee_name,nominee_relation,nominee_age,nominee_dob,custm_name from nominees_view";
		List<Nominee_Model> li= (List<Nominee_Model>)jdbcTemplate.query(sql, new Nominee_mapper());
		logger.info("query excuted");
		return li;
		
	}

	public List<Nominee_Model> search(int nominee_id) {

		
			String sql ="SELECT nominee_id,custm_id,nominee_name,nominee_relation,nominee_age,nominee_dob,custm_name from nominee where nominee_id=?";
			List<Nominee_Model> al=(List<Nominee_Model>)jdbcTemplate.query(sql,new Object[]{ nominee_id},new Nominee_mapper());
			return al;
		
    }
}
