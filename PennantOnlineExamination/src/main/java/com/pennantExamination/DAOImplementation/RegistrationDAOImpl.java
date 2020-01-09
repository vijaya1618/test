package com.pennantExamination.DAOImplementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zul.ListModel;

import com.pennantExamination.DAO.*;
import com.pennantExamination.pojos.CollegeModel;
import com.pennantExamination.pojos.Users;

public class RegistrationDAOImpl implements RegistrationDAO {
	
	
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

	public void registerUser(Users user) {
		// TODO Auto-generated method stub
		int st=0;
		String qry = "insert into Examinee(examinee_fullname,examinee_username,examinee_password,examinee_email,examinee_phonenumber,examinee_gender,examinee_dob,examinee_yop,examinee_degree,examinee_specilization,"
				+ "examinee_college,examinee_address,examinee_aadhar_no,examinee_resume,examinee_file) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {user.getFullName(),user.getUserName(),user.getPassword(),user.getEmail(),user.getPhoneNo(),user.getGender()
				,user.getDob(),user.getYop(),user.getCourse(),user.getSpecialization(),user.getCollege(),user.getAddress(),
				user.getAadhar(),user.getResume(),user.getFile()};
		int types[] = new int[] { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.BIGINT,Types.VARCHAR,Types.DATE,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,
				                  Types.VARCHAR,Types.VARCHAR,Types.BIGINT,Types.VARBINARY,Types.VARCHAR};
		st=jdbcTemplate.update(qry, params, types);
		if(st==1)
		{
			System.out.println("success");
			Messagebox.show("Examinee Registered succuessfully !!!!");
		}
		else
		{
			System.out.println("fail");
			Messagebox.show("Examinee Registration failed !!!!");
		}	
		
	}
	
	 protected class CollegeMapper implements RowMapper {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				CollegeModel clg = new CollegeModel();
				clg.setCollegeId(rs.getInt("college_id"));
				clg.setCollegeName(rs.getString("college_name"));
				return clg;
			}
		}

	public List<CollegeModel> getCollegeList() {
		// TODO Auto-generated method stub
		String sql="select * from Colleges";
    	List<CollegeModel> li=(List<CollegeModel>)jdbcTemplate.query(sql,new CollegeMapper());
		return li;
	}

}
