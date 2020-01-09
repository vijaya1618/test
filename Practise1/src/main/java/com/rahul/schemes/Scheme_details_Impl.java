package com.rahul.schemes;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

public class Scheme_details_Impl implements Scheme_details_Interface {
	private Logger logger=Logger.getLogger(Scheme_details_Impl.class);
	JdbcTemplate jdbcTemplate;
	DataSource dataSource;
	//Fetching DatSource to the Jdbc Template
	public void setDataSource(DataSource dataSource) {
		
		this.dataSource=dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
	}

	public int add_scheme(Scheme_Model scm) {
		logger.info("entering");
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("scheme_details_pro");
		SqlParameterSource in = new MapSqlParameterSource().addValue("schemename", scm.getScheme_name())
				.addValue("schemetype", scm.getScheme_loan_type())
				.addValue("schemeproduct", scm.getScheme_product())
				.addValue("schemecalc",scm.getScheme_calc())
				.addValue("schemecompound", scm.getScheme_compound())
				.addValue("schememin", scm.getScheme_min_ammount())
				.addValue("schememax", scm.getScheme_max_ammount())
				.addValue("schemefreq", scm.getScheme_payment_freq())
				.addValue("schemeadvance", scm.getScheme_pay_advance())
				.addValue("schemefrom", scm.getScheme_aplicable_from())
				.addValue("schemeto", scm.getScheme_aplicable_to())
				.addValue("schemeperiod", scm.getScheme_loan_period())
				.addValue("schemeintrest" ,scm.getScheme_intrest())
				.addValue("schemedays", scm.getScheme_intrst_days());
				Map<String, Object> out = jdbcCall.execute(in);
				int n= ((BigDecimal) out.get("schemeid")).intValue();
				logger.info("leaving");
		return n;
		
	}

	public int edit_scheme(Scheme_Model scm) {
		
			String sql = "UPDATE scheme_details  SET scheme_loan_type =?, scheme_product=?, scheme_calc=?,scheme_compound=?, scheme_min_ammount=?, scheme_max_ammount=?,scheme_payment_freq=?,scheme_pay_advance=?,scheme_aplicable_from=?,scheme_aplicable_to=?,scheme_loan_period=?,scheme_intrest=?,scheme_intrst_days=? where scheme_name = ?";
			Object[] params = new Object[] { scm.getScheme_loan_type(),scm.getScheme_product(),scm.getScheme_calc(),scm.getScheme_compound(),scm.getScheme_min_ammount(),scm.getScheme_max_ammount(),scm.getScheme_payment_freq(),scm.getScheme_pay_advance(),scm.getScheme_aplicable_from(),scm.getScheme_aplicable_to(),scm.getScheme_loan_period(),scm.getScheme_intrest(),scm.getScheme_intrst_days(),scm.getScheme_name()};
			int types[] = new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.DOUBLE,Types.DOUBLE,Types.NUMERIC,Types.VARCHAR,Types.DATE,Types.DATE,Types.NUMERIC,Types.FLOAT,Types.INTEGER,Types.VARCHAR};
			int n=jdbcTemplate.update(sql, params, types);
		      return n;
		
	}

	public int delete_scheme(int sid) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Scheme_Model> getallschemes() {
		logger.info("entering");
		String sql=" select scheme_id, scheme_name,scheme_loan_type,scheme_product,scheme_calc,scheme_min_ammount,scheme_max_ammount,scheme_compound,scheme_payment_freq,scheme_aplicable_from,scheme_aplicable_to,scheme_pay_advance,scheme_loan_period,scheme_intrest,scheme_intrst_days from scheme_details";
		List<Scheme_Model> li=jdbcTemplate.query(sql,new  Scheme_Mapper());
		return li;
	}
	public List<Scheme_Model> get_Scheme_By_Name(String name){
		logger.info("entering");
		String sql=" select scheme_id, scheme_name,scheme_loan_type,scheme_product,scheme_calc,scheme_min_ammount,scheme_max_ammount,scheme_compound,scheme_payment_freq,scheme_aplicable_from,scheme_aplicable_to,scheme_pay_advance,scheme_loan_period,scheme_intrest,scheme_intrst_days from scheme_details where scheme_name=?";
		List<Scheme_Model> li=jdbcTemplate.query(sql,new Object[]{name},new  Scheme_Mapper());
		return li;
	}
	public Scheme_Model get_Scheme(String name){
		logger.info("entering");
		String sql=" select scheme_id, scheme_name,scheme_loan_type,scheme_product,scheme_calc,scheme_min_ammount,scheme_max_ammount,scheme_compound,scheme_payment_freq,scheme_aplicable_from,scheme_aplicable_to,scheme_pay_advance,scheme_loan_period,scheme_intrest,scheme_intrst_days from scheme_details where scheme_name=?";
		List<Scheme_Model> li=jdbcTemplate.query(sql,new Object[]{name},new  Scheme_Mapper());
		return li.get(0);
	}

	public Scheme_Model get_Scheme_by_id() {
		Session sess = Sessions.getCurrent();
		int a=Integer.parseInt((String) sess.getAttribute("id1"));
		String sql="select scheme_id, scheme_name,scheme_loan_type,scheme_product,scheme_calc,scheme_min_ammount,scheme_max_ammount,scheme_compound,scheme_payment_freq,scheme_aplicable_from,scheme_aplicable_to,scheme_pay_advance,scheme_loan_period,scheme_intrest,scheme_intrst_days from scheme_details where scheme_id=?";
		List<Scheme_Model> li= jdbcTemplate.query(sql,new Object[] {a},new Scheme_Mapper());
		return li.get(0);
	}
}
