package com.pennantExamination.DAOImplementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import com.pennantExamination.DAO.LogInDAO;
import com.pennantExamination.controller.LogInCtrl;
import com.pennantExamination.pojos.Users;

public class LogInDAOImplementation implements LogInDAO{

	private JdbcTemplate jdbcTemplate;
	private static final Logger	logger				= Logger.getLogger(LogInDAOImplementation.class);
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
	
	protected class UserMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Users user = new Users();
			user.setExaminee_id(rs.getInt("examinee_id"));
			user.setFullName(rs.getString("examinee_fullname"));
			user.setUserName(rs.getString("examinee_username"));
			user.setPassword(rs.getString("examinee_password"));
			user.setPhoneNo(rs.getString("examinee_phonenumber"));
			user.setEmail(rs.getString("examinee_email"));
			user.setDob(rs.getDate("examinee_dob"));
			user.setGender(rs.getString("examinee_gender"));
			user.setYop(rs.getInt("examinee_yop"));
			user.setCourse(rs.getString("examinee_degree"));
			user.setSpecialization(rs.getString("examinee_specilization"));
			user.setCollege(rs.getString("examinee_college"));
			user.setAddress(rs.getString("examinee_address"));
			user.setAadhar(rs.getLong("examinee_aadhar_no"));
			user.setResume(rs.getBytes("examinee_resume"));
			user.setFile(rs.getString("examinee_file"));
			
			return user;
		}
	}

	public Users userAuthentication(String userName, String password) {
		String sql="select * from Examinee where examinee_username = ?";
		logger.info("user authentication :"+sql);
        Users user=(Users)jdbcTemplate.queryForObject(sql, new Object[] {userName}, new UserMapper());
		
		return user;
	}
}
