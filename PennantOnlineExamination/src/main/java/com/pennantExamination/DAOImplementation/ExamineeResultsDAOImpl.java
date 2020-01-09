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

import com.pennantExamination.DAOImplementation.ExamineeDAOImpl.ExamineeMapper;
import com.pennantExamination.pojos.ExamineeResultsModel;
import com.pennantExamination.DAO.ExamineeResultsDAO;

public class ExamineeResultsDAOImpl implements ExamineeResultsDAO{
	
private JdbcTemplate jdbcTemplate;
    
    public void setDataSource(DataSource datasource)
    {
    	this.jdbcTemplate=new JdbcTemplate(datasource);
    }
    public void setJdbcTemplate(JdbcTemplate  jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    

    protected class ExamineeResultsMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExamineeResultsModel results = new ExamineeResultsModel();
			results.setExamineeId(rs.getInt("examinee_id"));
			results.setExamineeFullName(rs.getString("examinee_name"));
			results.setExamineeSpecialization(rs.getString("examinee_specialization"));
			results.setAptitudeScore(rs.getInt("examinee_aptitude_score"));
			results.setTechnicalScore(rs.getInt("examinee_tech_score"));
			results.setTotalScore(rs.getInt("examinee_total_score"));
			return results;
		}
	}
    
    public List<ExamineeResultsModel> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from Examinee_Scores";
    	List<ExamineeResultsModel> li=(List<ExamineeResultsModel>)jdbcTemplate.query(sql,new ExamineeResultsMapper());
    	ExamineeResultsModel e=new ExamineeResultsModel();
    	//e=li.get(1);
    	System.out.println(e.getExamineeId());
		return li;
	}
    
    public ExamineeResultsModel findById() {
		// TODO Auto-generated method stub
		Session sess=Sessions.getCurrent();
		int a= Integer.parseInt((String) sess.getAttribute("id2"));
		String sql="select * from Examinee_Scores where examinee_id = ?";
		return (ExamineeResultsModel) jdbcTemplate.queryForObject(sql,new Object[] {a},new ExamineeResultsMapper());
	}

    public void insert(ExamineeResultsModel results) {
		// TODO Auto-generated method stub
		try {
			
			int st=0;
			String qry = "insert into Examinee_Scores(examinee_id,examinee_name,examinee_specialization,examinee_aptitude_score,examinee_tech_score,examinee_total_score) values(?,?,?,?,?,?)";
			Object[] params = new Object[] {results.getExamineeId(),results.getExamineeFullName(),results.getExamineeSpecialization(),results.getAptitudeScore(),results.getTechnicalScore(),results.getTotalScore()};
			int types[] = new int[] { Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER};
			st=jdbcTemplate.update(qry, params, types);
			
			if(st==1)
			{
				
				Messagebox.show("Success!");
			}
			else
			{
				
				Messagebox.show("Failed!");
			}	
		}
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	}
    
    public void update(ExamineeResultsModel results)
	{
		try {
			  int st=0;
		      String sql = "UPDATE  Examinee_Scores SET examinee_name=? ,examinee_specialization=? ,examinee_aptitude_score=?, examinee_tech_score=? , examinee_total_score=?  where examinee_id=?";
			  Object[] params = new Object[] {results.getExamineeFullName(),results.getExamineeSpecialization(),results.getAptitudeScore(),results.getTechnicalScore(),results.getTotalScore(),results.getExamineeId()};
			  int types[]=new int[] { Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER};
			  st=jdbcTemplate.update(sql, params,types);
			  if(st==1)
				{
					
					Messagebox.show(" Results Updated!!!!");
				}
				else
				{
					
					Messagebox.show("Updation failed !!!!");
				}	
			  
			}
		catch(Exception e)
			{
				e.printStackTrace();
			}
	}
    
    public void examineedelete(int id)
	{
		try 
		{
			int st=0;
			String sql = "DELETE FROM Examinee_Scores WHERE examinee_id = ?";
			//System.out.println(sql);
			Object[] params = new Object[] { id };
			int types[] = new int[] { Types.INTEGER};
			st=jdbcTemplate.update(sql, params, types);
			 if(st==1)
				{
					//System.out.println("success");
					Messagebox.show("Examinee Results Deleted !!!");
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
