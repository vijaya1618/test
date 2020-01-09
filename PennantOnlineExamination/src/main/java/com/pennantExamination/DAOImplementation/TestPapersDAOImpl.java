package com.pennantExamination.DAOImplementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;


import com.pennantExamination.DAO.TestPapersDAO;
import com.pennantExamination.pojos.TestPapersModel;
import com.pennantExamination.pojos.TestSegmentModel;

public class TestPapersDAOImpl implements TestPapersDAO{

private JdbcTemplate jdbcTemplate;
    
    public void setDataSource(DataSource datasource)
    {
    	this.jdbcTemplate=new JdbcTemplate(datasource);
    }
    public void setJdbcTemplate(JdbcTemplate  jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    
    protected class TestPapersMapper implements RowMapper {
 		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
 			TestPapersModel tpm = new TestPapersModel();
 			tpm.setQuestionId(rs.getInt("quest_id"));
 			tpm.setQuestion(rs.getString("question"));
 			tpm.setOptionA(rs.getString("option_A"));
 			tpm.setOptionB(rs.getString("option_B"));
 			tpm.setOptionC(rs.getString("option_C"));
 			tpm.setOptionD(rs.getString("option_D"));
 			tpm.setCorrectOption(rs.getString("correct_ans"));
 			tpm.setQuestionType(rs.getString("question_type"));
 			tpm.setSetId(rs.getInt("set_id"));
 			return tpm;
 		}
 	}
    
    public List<TestPapersModel> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from Test_Questions";
    	List<TestPapersModel> li=(List<TestPapersModel>)jdbcTemplate.query(sql,new TestPapersMapper());
    	TestPapersModel tpm=new TestPapersModel();
    	//tpm=li.get(1);
    	//System.out.println(tpm.getSetNo());
		return li;
	}
    
    public TestPapersModel findById() {
		// TODO Auto-generated method stub
		Session sess=Sessions.getCurrent();
		int a= Integer.parseInt((String) sess.getAttribute("id4"));
		String sql="select * from Test_Questions where quest_id = ?";
		return (TestPapersModel) jdbcTemplate.queryForObject(sql,new Object[] {a},new TestPapersMapper());
	}
    
    protected class TestSegmentMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TestSegmentModel testSegment = new TestSegmentModel();
			testSegment.setTestsegmentId(rs.getInt("section_id"));
			testSegment.setTestsegmentName(rs.getString("section_name"));
			return testSegment;
		}
	}

    public List<TestSegmentModel> getTestSegmentList() {
	// TODO Auto-generated method stub
	String sql="select section_id , section_name  from Segregation";
	List <TestSegmentModel> li=(List<TestSegmentModel>)jdbcTemplate.query(sql,new TestSegmentMapper());
	return li;
    } 
    
	public void insert(TestPapersModel tpm) {
		// TODO Auto-generated method stub
		try {
			
			int st=0;
			String qry = "insert into Test_Questions(question,option_A,option_B,option_C,option_D,correct_ans,question_type,set_id) values(?,?,?,?,?,?,?,?)";
			Object[] params = new Object[] {tpm.getQuestion(),tpm.getOptionA(),tpm.getOptionB(),tpm.getOptionC(),tpm.getOptionD(),tpm.getCorrectOption(),tpm.getQuestionType(),tpm.getSetId()};
			int types[] = new int[] { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
			st=jdbcTemplate.update(qry, params, types);
			
			if(st==1)
			{
				//System.out.println("success");
				Messagebox.show("Success!");
			}
			else
			{
				//System.out.println("fail");
				Messagebox.show("Fail!");
			}	
		}
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	}
	
	public void update(TestPapersModel tpm)
	{
		try {
				int st=0;
		      String sql = "UPDATE Test_Questions SET question=? ,option_A=? ,option_B=? ,option_C=? ,option_D=? ,correct_ans=? ,question_type=? ,set_id=? where quest_id=?";
			  Object[] params = new Object[] {tpm.getQuestion(),tpm.getOptionA(),tpm.getOptionB(),tpm.getOptionC(),tpm.getOptionD(),tpm.getCorrectOption(),tpm.getQuestionType(),tpm.getSetId(),tpm.getQuestionId()};
			  int types[]=new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER};
			  st=jdbcTemplate.update(sql, params,types);
			  if(st==1)
				{
					//System.out.println("success");
					Messagebox.show("Test Question Updated !!");
				}
				else
				{
					//System.out.println("fail");
					Messagebox.show("Updation failed !!");
				}	
			  
			}
		catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public void testpapersDelete(int id)
	{
		try 
		{
			int st=0;
			String sql = "DELETE FROM Test_Questions WHERE quest_id = ?";
			//System.out.println(sql);
			Object[] params = new Object[] { id };
			int types[] = new int[] { Types.INTEGER};
			st=jdbcTemplate.update(sql, params, types);
			if(st==1)
			{
				//System.out.println("success");
				Messagebox.show("Test Question Deleted !!!");
			}
			else
			{
				//System.out.println("fail");
				Messagebox.show("Deletion failed !!!!");
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
    
}
