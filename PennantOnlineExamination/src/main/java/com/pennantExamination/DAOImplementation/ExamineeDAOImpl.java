package com.pennantExamination.DAOImplementation;

import java.util.List;
import java.sql.*;
import javax.sql.*;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Listbox;

import com.google.common.base.CharMatcher;
import com.pennantExamination.DAO.ExamineeDAO;
import com.pennantExamination.pojos.CollegeModel;
import com.pennantExamination.pojos.DegreeModel;
import com.pennantExamination.pojos.ExamineeModel;
import com.pennantExamination.pojos.SpecializationModel;

public class ExamineeDAOImpl implements ExamineeDAO{

    private JdbcTemplate jdbcTemplate;
    
    public void setDataSource(DataSource datasource)
    {
    	this.jdbcTemplate=new JdbcTemplate(datasource);
    }
    public void setJdbcTemplate(JdbcTemplate  jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    
    ExamineeModel exm = new ExamineeModel();
    
    protected class ExamineeMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExamineeModel exm = new ExamineeModel();
			exm.setExamineeId(rs.getInt("examinee_id"));
			exm.setExamineeFullName(rs.getString("examinee_fullname"));
			exm.setExamineeUserName(rs.getString("examinee_username"));
			exm.setExamineePassword(rs.getString("examinee_password"));
			exm.setExamineeEmail(rs.getString("examinee_email"));
			exm.setExamineePhn(rs.getLong("examinee_phonenumber"));
			exm.setExamineeGender(rs.getString("examinee_gender"));
			exm.setExamineeDOB(rs.getDate("examinee_dob"));
			exm.setExamineeYop(rs.getInt("examinee_yop"));
			exm.setExamineeCourse(rs.getString("examinee_degree"));
			exm.setExamineeSpecialization(rs.getString("examinee_specilization"));
			exm.setExamineeCollege(rs.getString("examinee_college"));
			exm.setExamineeAddress(rs.getString("examinee_address"));
			exm.setExamineeAadhaar(rs.getLong("examinee_aadhar_no"));
			exm.setResume(rs.getBytes("examinee_resume"));
			return exm;
		}
	}
    protected class DegreeMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DegreeModel degree = new DegreeModel();
			degree.setDegreeId(rs.getInt("degree_id"));
			degree.setDegreeName(rs.getString("degree_name"));
			return degree;
		}
	}

    public List<DegreeModel> getDegreeList() {
	// TODO Auto-generated method stub
	String sql="select degree_id , degree_name  from Degree";
	List<DegreeModel> li=(List<DegreeModel>)jdbcTemplate.query(sql,new DegreeMapper());
	return li;
    }

    protected class SpecializationMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpecializationModel specialization = new SpecializationModel();
			specialization.setSpecializationId(rs.getInt("spec_id"));
			specialization.setSpecializationName(rs.getString("spec_name"));
			return specialization;
		}
	}

    public List<SpecializationModel> getSpecializationList() {
	// TODO Auto-generated method stub
	String sql="select spec_id , spec_name  from Specializations";
	List<SpecializationModel> li=(List<SpecializationModel>)jdbcTemplate.query(sql,new SpecializationMapper());
	return li;
    }
    
    protected class CollegeMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			CollegeModel college = new CollegeModel();
			college.setCollegeId(rs.getInt("college_id"));
			college.setCollegeName(rs.getString("college_name"));
			return college;
		}
	}

    public List<CollegeModel> getCollegeList() {
	// TODO Auto-generated method stub
	String sql="select college_id , college_name  from Colleges";
	List<CollegeModel> li=(List<CollegeModel>)jdbcTemplate.query(sql,new CollegeMapper());
	return li;
    } 
    
	public List<ExamineeModel> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from Examinee";
    	List<ExamineeModel> li=(List<ExamineeModel>)jdbcTemplate.query(sql,new ExamineeMapper());
    	ExamineeModel e=new ExamineeModel();
    	//e=li.get(0);
    	//System.out.println(e.getExamineeId());
		return li;
	}
	public ExamineeModel findById() {
		// TODO Auto-generated method stub
		Session sess=Sessions.getCurrent();
		int a= Integer.parseInt((String) sess.getAttribute("id1"));
		String sql="select * from Examinee where examinee_id = ?";
		return (ExamineeModel) jdbcTemplate.queryForObject(sql,new Object[] {a},new ExamineeMapper());
	}
	
	public void insert(ExamineeModel exm) {
		// TODO Auto-generated method stub
		try {
			
			int st=0;
			String qry = "insert into Examinee(examinee_fullname,examinee_username,examinee_password,examinee_email,examinee_phonenumber,examinee_gender,examinee_dob,examinee_yop,examinee_degree,examinee_specilization,"
					+ "examinee_college,examinee_address,examinee_aadhar_no,examinee_resume,examinee_file) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object[] params = new Object[] {exm.getExamineeFullName(), exm.getExamineeUserName(),exm.getExamineePassword(),exm.getExamineeEmail(),exm.getExamineePhn(),exm.getExamineeGender(),exm.getExamineeDOB(),exm.getExamineeYop(),exm.getExamineeCourse(),exm.getExamineeSpecialization(),exm.getExamineeCollege(),exm.getExamineeAddress(),exm.getExamineeAadhaar(),exm.getResume(),exm.getFileName()};
			int types[] = new int[] { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.BIGINT,Types.VARCHAR,Types.DATE,Types.INTEGER,Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.BIGINT,Types.VARBINARY,Types.VARCHAR};
			st=jdbcTemplate.update(qry, params, types);
			
			if(st==1)
			{
				//System.out.println("success");
				Messagebox.show("Examinee Registered !!!");
			}
			else
			{
				//System.out.println("fail");
				Messagebox.show("Registration failed !!!!");
			}	
		}
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	} 
	
	public void update(ExamineeModel exm)
	{
		try {
				int st=0;
		      String sql = "UPDATE  Examinee SET examinee_fullname=? ,examinee_username=? ,examinee_password=?,examinee_email=? ,examinee_phonenumber=? ,examinee_gender=? ,examinee_dob=? ,examinee_yop=? ,examinee_degree=? ,examinee_specilization=? ,examinee_college=? ,examinee_address=? ,examinee_aadhar_no=? ,examinee_resume=?,examinee_file=? where examinee_id=?";
			  Object[] params = new Object[] {exm.getExamineeFullName(), exm.getExamineeUserName(),exm.getExamineePassword(),exm.getExamineeEmail(),exm.getExamineePhn(),exm.getExamineeGender(),exm.getExamineeDOB(),exm.getExamineeYop(),exm.getExamineeCourse(),exm.getExamineeSpecialization(),exm.getExamineeCollege(),exm.getExamineeAddress(),exm.getExamineeAadhaar(),exm.getResume(),exm.getFileName(),exm.getExamineeId()};
			  int types[]=new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.BIGINT,Types.VARCHAR,Types.DATE,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.BIGINT,Types.VARBINARY,Types.VARCHAR,Types.INTEGER};
			  st=jdbcTemplate.update(sql, params,types);
			  if(st==1)
				{
					//System.out.println("success");
					Messagebox.show("Examinee Details Updated !!!");
				}
				else
				{
					//System.out.println("fail");
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
			int a=id;
			int b=id;
			String sql = "DELETE FROM Examinee_Scores WHERE examinee_id = ? ; DELETE FROM Examinee_Edudetails WHERE examinee_id = ? ; DELETE FROM Examinee WHERE examinee_id = ?";
			//System.out.println(sql);
			Object[] params = new Object[] { id,a,b};
			int types[] = new int[] { Types.INTEGER , Types.INTEGER , Types.INTEGER};
			st=jdbcTemplate.update(sql, params,types);
			  if(st==1)
				{
					//System.out.println("success");
					Messagebox.show("Examinee Details Deleted !!!");
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