package com.pennantExamination.DAOImplementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import com.pennantExamination.DAO.OnlineExamDAO;
import com.pennantExamination.pojos.EduModel;
import com.pennantExamination.pojos.QuestionModel;
import com.pennantExamination.pojos.TechModel;
import com.pennantExamination.pojos.Users;


public class OnlineExamDAOImpl implements OnlineExamDAO {
	
	private JdbcTemplate jdbcTemplate;
	protected DataFieldMaxValueIncrementer taskIncer;
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void setJdbcTemplate(JdbcTemplate  jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setTaskIncer(DataFieldMaxValueIncrementer taskIncer) {
		this.taskIncer = taskIncer;
	}

	public void TechInsert(TechModel tm) {
		// TODO Auto-generated method stub
		int st=0;
		String qry = "insert into tech_details(project_name,project_guide,project_duration,institution_name,project_description,languages_known,experience) values(?,?,?,?,?,?,?)";
		Object[] params = new Object[] {tm.getProjectName(),tm.getProjectGuide(),tm.getProjectDuration(),tm.getInstitutionName(),tm.getDescription(),tm.getLanguageKnown(),tm.getExperience()};
		int types[] = new int[] { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		st=jdbcTemplate.update(qry, params, types);
		if(st==1)
		{
			System.out.println("success");
		}
		else
		{
			System.out.println("fail");
		}	
	}

public void EducInsert(EduModel em) {
		
		// TODO Auto-generated method stub
		
		int sm=0;
		String qry="insert into edc_details(School_Name,School_city,School_state,school_Citypincode,school_boardname,school_yop,school_agg,inter_clg_name,Clg_city,Clg_state,Clg_Citypincode,Clg_boardname,Clg_yop,Clg_agg) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {em.getSchoolName(),em.getSchoolCity(),em.getSchoolState(),em.getSchool_CityPincode(),em.getBoardName(),em.getSchool_YOP(),em.getSchool_Agg(),em.getInter_clg_name(),em.getClgCity(),em.getClgState(),em.getClg_CityPincode(),em.getBoard_Name(),em.getClg_YOP(),em.getClg_Agg()};
		int types[] = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.INTEGER};
		sm=jdbcTemplate.update(qry, params, types);
		if(sm==1)
		{
			System.out.println("success");
		}
		else
		{
			System.out.println("fail");
		}
	
	
	}
    public List<QuestionModel> getQuestions() {
	String sql="select * from Test_Questions";
	List<QuestionModel> quesList = (List<QuestionModel>)jdbcTemplate.query(sql,new QuestionMapper());
	return quesList;
}


     protected class  QuestionMapper implements RowMapper
     {
	  public Object mapRow(ResultSet rs,int rowNum) throws SQLException{
		QuestionModel qm=new QuestionModel();
		qm.setQuestion_id(rs.getInt("quest_id"));
		qm.setQuestion(rs.getString("question"));
		qm.setOption_A(rs.getString("option_a"));
		qm.setOption_B(rs.getString("option_b"));
		qm.setOption_C(rs.getString("option_c"));
		qm.setOption_D(rs.getString("option_d"));
		qm.setCorrect_ans(rs.getString("correct_ans"));
		qm.setQuestion_type(rs.getString("question_type"));
		qm.setSet_id(rs.getInt("set_id"));
		return qm;
	}
}
     
     public String getAnswerById(String id) {
		 String qry="select correct_ans from Test_Questions where quest_id=?";
		 String res=jdbcTemplate.queryForObject(qry, new Object[] {Integer.parseInt(id)},String.class);
		 return res;
	 }
	
     public void update(Users usr)
  	{
  		try {
  			//System.out.println('i');
  		      String sql = "UPDATE  Examinee SET examinee_fullname=? ,examinee_username=?,examinee_email=? ,examinee_phonenumber=? ,examinee_gender=? ,examinee_dob=? ,examinee_yop=? ,examinee_degree=? ,examinee_specilization=? ,examinee_college=? ,examinee_address=? ,examinee_aadhar_no=?  where examinee_id=?";
  		      Object[] params = new Object[] {usr.getFullName(), usr.getUserName(), usr.getEmail(), usr.getPhoneNo(), usr.getGender(), usr.getDob(), usr.getYop(), usr.getCourse(), usr.getSpecialization(), usr.getCollege(), usr.getAddress(), usr.getAadhar(), usr.getExaminee_id()};
  			  int types[]=new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.DATE,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.BIGINT,Types.INTEGER};
  			  int i= jdbcTemplate.update(sql, params,types);
  		  
  			  System.out.println(sql);
  			}
  		catch(Exception e)
  			{
  				e.printStackTrace();
  			}
  	}
 		 
 	
     public void Totalscore(int count){
    		// TODO Auto-generated method stub
   	  Session sess=Sessions.getCurrent();
   	  Users user = (Users) sess.getAttribute("user");
   	  int examid=user.getExaminee_id();
   	  String username=user.getFullName();
   	  String specialization=user.getSpecialization();
   	  int sn=0;
   	  String qry="insert into Examinee_Scores(examinee_id, examinee_name, examinee_total_score, examinee_specialization) values(?,?,?,?)";
     Object[] params = new Object[] {examid, username, count, specialization};
     int types[] = new int[] {Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.VARCHAR};
     sn=jdbcTemplate.update(qry, params, types); 	
     }
}



