package com.rahul.Loan;



import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.rahul.Login.UserImpl;
import com.rahul.Nominee.Nominee_Model;
import com.rahul.Nominee.Nominee_mapper;
import com.rahul.Sessions.AuthenticationService;
import com.rahul.Sessions.AuthenticationServiceImpl;
import com.rahul.Sessions.Session_Model;

public class LoanImpl implements Loaninterface{
	private static final Logger logger = Logger.getLogger(UserImpl.class);
	AuthenticationService as=new AuthenticationServiceImpl();
	JdbcTemplate jdbcTemplate;
	DataSource dataSource;
	Nominee_Model n=new Nominee_Model();
	//Fetching DatSource to the Jdbc Template
	
	
	public void setDataSource(DataSource dataSource) {
				this.dataSource=dataSource;
				this.jdbcTemplate = new JdbcTemplate(dataSource);
				}


	public Nominee_Model getNominee(int cust_id){
				logger.info("entering");
				String sql="select custm_id,custm_name,nominee_id,nominee_name,nominee_relation,nominee_age,nominee_dob from cust_nominee_details where custm_id=?";
				List<Nominee_Model> li=jdbcTemplate.query(sql,new Object[]{cust_id},new Nominee_mapper());
				n=li.get(0);
				logger.info("leaving");
				return n;
				}

	public Loan_Model getLoan_rate(Loan_Model lm){
				logger.info("entering");
				String sql="select loan_item_market_rate,loan_item_lending_rate from gold_loan_item where loan_item_type= ? and loan_item_karet=?";
				Loan_Model lmm=jdbcTemplate.query(sql,new Object[]{lm.getItem_type(),lm.getItem_purity()},new ResultSetExtractor<Loan_Model>(){  
		    
					public Loan_Model extractData(ResultSet rs) throws SQLException,DataAccessException {  
						Loan_Model loan=new Loan_Model();  
						while(rs.next()){  
		        		loan.setItem_market_rate(rs.getDouble("loan_item_market_rate"));
		        		loan.setItem_lending_rate(rs.getDouble("loan_item_lending_rate"));
										}  
						return loan;
						}  
						});  
				lmm.setItem_purity(lm.getItem_purity());
				lmm.setItem_type(lm.getItem_type());
				lmm.setItem_wt(lm.getItem_wt());
				logger.info("leaving");
				return lmm;
				}
	
	
	public double getTotalvalue(double marketrate,double item_wt){
				logger.info("entering");
				double totalValue=marketrate*item_wt;
				return totalValue;
				}

	
	public int applyLoan(Loan_Model lm){
		
				logger.info("entering");
				SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("loan_details_pro");
				SqlParameterSource in = new MapSqlParameterSource().addValue("custmer_id",lm.getCustmer_id())
				.addValue("custmer_name",lm.getCustmer_name())
				.addValue("nominee_id",lm.getNomine_id())
				.addValue("nominee_name",lm.getNominee_name())
				.addValue("item_type",lm.getItem_type())
				.addValue("item_name",lm.getItem_name())
				.addValue("item_wt",lm.getItem_wt())
				.addValue("item_purity",lm.getItem_purity())
				.addValue("item_marketrate",lm.getItem_market_rate())
				.addValue("item_lendingrate",lm.getItem_lending_rate())
				.addValue("item_totalrate",lm.getItem_total_amount())
				.addValue("scheme_name",lm.getScheme_name())
				.addValue("payment_basis",lm.getPayment_basis())
				.addValue("tenure",lm.getTenure())
				.addValue("intrest",lm.getScheme_intrest())
				.addValue("loan_date",lm.getLoan_date())
				.addValue("frequency_of_pay",lm.getFrq_of_pay())
				.addValue("req_amount",lm.getReq_amount())
				.addValue("avail_amount",lm.getAvaliable_amount())
				.addValue("intrest_type",lm.getIntrest_type())
				.addValue("appraisal_name",lm.getApprisal_name())
			
				.addValue("item_image",lm.getImage())
				.addValue("loan_status",lm.getStatus());
				
				
				Map<String, Object> out = jdbcCall.execute(in);
				
				int n1= ((BigDecimal) out.get("loan_id")).intValue();
				String sql="update loan_temp set item_image=? where loan_id="+n1;
				Object[] params = new Object[] {lm.getImage()};
				int types[] = new int[] {Types.LONGVARBINARY}; 
			
				int n=	jdbcTemplate.update(sql, params, types);
				System.out.println(n);
				
				logger.info("leaving");
				return n1;
			}
	
	
	public List<Loan_Model> findAll() {
				String sql=" SELECT * FROM loan_temp UNION ALL SELECT * FROM loan_details";
				List<Loan_Model> li=jdbcTemplate.query(sql,new Loan_Mapper());
				return li;
			}
	
	

	
	public int loanapprove(Loan_Model lm) {
				logger.info("entering");
				SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("main_loan_pro");
				SqlParameterSource in = new MapSqlParameterSource().addValue("custmer_id",lm.getCustmer_id())
				.addValue("custmer_name",lm.getCustmer_name())
				.addValue("nominee_id",lm.getNomine_id())
				.addValue("nominee_name",lm.getNominee_name())
				.addValue("item_type",lm.getItem_type())
				.addValue("item_name",lm.getItem_name())
				.addValue("item_wt",lm.getItem_wt())
				.addValue("item_purity",lm.getItem_purity())
				.addValue("item_marketrate",lm.getItem_market_rate())
				.addValue("item_lendingrate",lm.getItem_lending_rate())
				.addValue("item_totalrate",lm.getItem_total_amount())
				.addValue("item_image", lm.getImage())
				.addValue("scheme_name",lm.getScheme_name())
				.addValue("payment_basis",lm.getPayment_basis())
				.addValue("tenure",lm.getTenure())
				.addValue("intrest",lm.getScheme_intrest())
				.addValue("loan_date",lm.getLoan_date())
				.addValue("frequency_of_pay",lm.getFrq_of_pay())
				.addValue("req_amount",lm.getReq_amount())
				.addValue("avail_amount",lm.getAvaliable_amount())
				.addValue("intrest_type",lm.getIntrest_type())
				.addValue("appraisal_name",lm.getApprisal_name())
				.addValue("loan_status",lm.getStatus())
				.addValue("loan_id", lm.getLoan_id());
				
				
				try{
					
					Map<String, Object> out = jdbcCall.execute(in);
					logger.info("leaving");
					return 1;
					}catch(Exception e){
						logger.info("leaving");
						return 0;
					}
		}
	
	
	public List<Loan_Model> getLoan_By_id(int loan_id, String loan_date) {
				StringBuilder sql=new StringBuilder();
				if(loan_id>0 || loan_date!=null){
				sql.append("SELECT * from all_loans where");
					if(loan_id>0){
							sql.append(" loan_id="+loan_id);
					}
					if(loan_id>0 && loan_date!=null){
							sql.append(" and");
					}
					if(loan_date!=null){
							sql.append(" approv_date='"+loan_date+"'");
					}
					}else{
							sql.append("SELECT * FROM all_loans");
					}
				System.out.println(sql.toString());
				List<Loan_Model> li=jdbcTemplate.query(sql.toString(),new Loan_Mapper());
				return li;
	
			}
	
	
	public List<Loan_Model> psearch(int id) {
					logger.info("entering");
						// TODO Auto-generated method stub
					String sql = "SELECT * from loan_temp where loan_id=?";
					List<Loan_Model> li= (List<Loan_Model>)jdbcTemplate.query(sql,new Object[] {id}, new Loan_Mapper());
					logger.info("query excuted");
					return li;
		
			}
	
	
	public List<Loan_Model> pendingAll() {
			logger.info("enetring");
			String sql="select * from loan_temp";
		
		
			List<Loan_Model> li=(List<Loan_Model>)jdbcTemplate.query(sql,new Loan_Mapper());
		
			return li;
			}
	
	
	public Loan_Model getLoandetails() {
			logger.info("entering");
			String sql = "SELECT * from all_loans where loan_id=?";
			Session_Model s=as.getDetails();
			List<Loan_Model> li= (List<Loan_Model>)jdbcTemplate.query(sql,new Object[] {s.getLoan_id()}, new Loan_Mapper());
			logger.info("query excuted");
			return li.get(0);
		}
	
	
	public int saveLoan(Loan_Model lm) {
		logger.info("entering");
		
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("update_loan");
		SqlParameterSource in = new MapSqlParameterSource().addValue("custmer_id",lm.getCustmer_id())
		.addValue("custmer_name",lm.getCustmer_name())
		.addValue("nominee_id",lm.getNomine_id())
		.addValue("nominee_name",lm.getNominee_name())
		.addValue("item_type",lm.getItem_type())
		.addValue("item_name",lm.getItem_name())
		.addValue("item_wt",lm.getItem_wt())
		.addValue("item_purity",lm.getItem_purity())
		.addValue("item_marketrate",lm.getItem_market_rate())
		.addValue("item_lendingrate",lm.getItem_lending_rate())
		.addValue("item_totalrate",lm.getItem_total_amount())
		.addValue("item_image", lm.getImage())
		.addValue("scheme_name",lm.getScheme_name())
		.addValue("payment_basis",lm.getPayment_basis())
		.addValue("tenure",lm.getTenure())
		.addValue("intrest",lm.getScheme_intrest())
		.addValue("loan_date",lm.getLoan_date())
		.addValue("frequency_of_pay",lm.getFrq_of_pay())
		.addValue("req_amount",lm.getReq_amount())
		.addValue("avail_amount",lm.getAvaliable_amount())
		.addValue("intrest_type",lm.getIntrest_type())
		.addValue("appraisal_name",lm.getApprisal_name())
		.addValue("loan_status",lm.getStatus())
		.addValue("loan_id", lm.getLoan_id());
		
		
		Map<String, Object> out = jdbcCall.execute(in);
		
		int n=(Integer) out.get("conform");
		if(lm.getImage()!=null){
			
			String sql="update loan_temp set item_image=? where loan_id="+lm.getLoan_id();
			Object[] params = new Object[] {lm.getImage()};
			int types[] = new int[] {Types.LONGVARBINARY}; 
		
			int n2=	jdbcTemplate.update(sql, params, types);
		
			}
		return n;
		
	}


	public int resubmitLoan(Loan_Model lm) {
		logger.info("entering");
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("resubmit_loan");
		SqlParameterSource in = new MapSqlParameterSource().addValue("custmer_id",lm.getCustmer_id())
		.addValue("custmer_name",lm.getCustmer_name())
		.addValue("nominee_id",lm.getNomine_id())
		.addValue("nominee_name",lm.getNominee_name())
		.addValue("item_type",lm.getItem_type())
		.addValue("item_name",lm.getItem_name())
		.addValue("item_wt",lm.getItem_wt())
		.addValue("item_purity",lm.getItem_purity())
		.addValue("item_marketrate",lm.getItem_market_rate())
		.addValue("item_lendingrate",lm.getItem_lending_rate())
		.addValue("item_totalrate",lm.getItem_total_amount())
		.addValue("item_image", lm.getImage())
		.addValue("scheme_name",lm.getScheme_name())
		.addValue("payment_basis",lm.getPayment_basis())
		.addValue("tenure",lm.getTenure())
		.addValue("intrest",lm.getScheme_intrest())
		.addValue("loan_date",lm.getLoan_date())
		.addValue("frequency_of_pay",lm.getFrq_of_pay())
		.addValue("req_amount",lm.getReq_amount())
		.addValue("avail_amount",lm.getAvaliable_amount())
		.addValue("intrest_type",lm.getIntrest_type())
		.addValue("appraisal_name",lm.getApprisal_name())
		.addValue("loan_status",lm.getStatus())
		.addValue("loan_id", lm.getLoan_id());
		
		
		try{
			
			Map<String, Object> out = jdbcCall.execute(in);
			logger.info("leaving");
			return 1;
			}catch(Exception e){
				logger.info("leaving");
				return 0;
			}
	}
}
