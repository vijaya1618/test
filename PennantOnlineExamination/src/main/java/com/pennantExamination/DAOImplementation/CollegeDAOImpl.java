package com.pennantExamination.DAOImplementation;

import java.util.List;
import java.sql.*;


import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import com.pennantExamination.DAO.CollegeDAO;
import com.pennantExamination.DAOImplementation.CityDAOImpl.CityMapper;
import com.pennantExamination.DAOImplementation.SpecializationDAOImpl.DegreeMapper;
import com.pennantExamination.pojos.CityModel;
import com.pennantExamination.pojos.CollegeModel;
import com.pennantExamination.pojos.DegreeModel;

public class CollegeDAOImpl implements CollegeDAO{

    private JdbcTemplate jdbcTemplate;
    
    public void setDataSource(DataSource datasource)
    {
    	this.jdbcTemplate=new JdbcTemplate(datasource);
    }
    public void setJdbcTemplate(JdbcTemplate  jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    
    protected class CollegeMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			CollegeModel clg = new CollegeModel();
			clg.setCollegeId(rs.getInt("college_id"));
			clg.setCollegeName(rs.getString("college_name"));
			clg.setCityId(rs.getInt("city_id"));
			clg.setEnabled(rs.getInt("enabled"));
			return clg;
		}
	}
    
	public List<CollegeModel> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from Colleges";
    	List<CollegeModel> li=(List<CollegeModel>)jdbcTemplate.query(sql,new CollegeMapper());
    	CollegeModel c=new CollegeModel();
    	c=li.get(0);
    	System.out.println(c.getCollegeName());
		return li;
	}
	public CollegeModel findById() {
		// TODO Auto-generated method stub
		Session sess=Sessions.getCurrent();
		int a= Integer.parseInt((String) sess.getAttribute("collegeId"));
		String sql="select * from Colleges where college_id = ?";
		return (CollegeModel) jdbcTemplate.queryForObject(sql,new Object[] {a},new CollegeMapper());
	}
	
	public void insert(CollegeModel clg) {
		// TODO Auto-generated method stub
		try {
			
			int st=0;
			String qry = "insert into Colleges(college_name,enabled) values(?,?)";
			Object[] params = new Object[] {clg.getCollegeName(),clg.getEnabled()};
			int types[] = new int[] { Types.VARCHAR,Types.INTEGER};
			st=jdbcTemplate.update(qry, params, types);
			
			if(st==1)
			{
				System.out.println("success");
				Messagebox.show("College Registered succuessfully !!!!");
			}
			else
			{
				System.out.println("fail");
				Messagebox.show("College Registration failed !!!!");
			}	
		}
		 catch(Exception c)
		 {
			 c.printStackTrace();
		 }
	} 
	
	public void update(CollegeModel clg)
	{
		try {
			System.out.println('i');
			Session ses=Sessions.getCurrent();
			int a= Integer.parseInt((String) ses.getAttribute("collegeId"));
		      String sql = "UPDATE  Colleges SET college_name=?,city_id = ?, enabled = ? where college_id = "+a+"";
			  Object[] params = new Object[] {clg.getCollegeName(),clg.getCityId(),clg.getEnabled()};
			  int types[]=new int[] {Types.VARCHAR,Types.INTEGER,Types.INTEGER};
			  jdbcTemplate.update(sql, params,types);
		  
			  System.out.println(sql);
			}
		catch(Exception c)
			{
				c.printStackTrace();
			}
	}
	
	 protected class CityMapper implements RowMapper {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				CityModel cty = new CityModel();
				cty.setCityId(rs.getInt("city_id"));
				cty.setCityName(rs.getString("city_name"));
				cty.setEnabled(rs.getInt("enabled"));
				return cty;
			}
		}
	
	public List<CityModel> getcityList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String sql="select * from Cities";
		    	List<CityModel> li=(List<CityModel>)jdbcTemplate.query(sql,new CityMapper());
		    	CityModel c=new CityModel();
		    	c=li.get(0);
		    	System.out.println(c.getCityName());
				return li;
	}
	
	public void collegedelete(int id)
	{
		try 
		{
			String sql = " DELETE FROM colleges WHERE college_id = ? ";
			//System.out.println(sql);
			Object[] params = new Object[] { id};
			int types[] = new int[] { Types.INTEGER };
			jdbcTemplate.update(sql, params, types);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public String getCityNamebyId(int cityId) {
		String sql="select * from Cities where city_id= ?";
		CityModel cm=(CityModel)jdbcTemplate.queryForObject(sql, new Object[] {cityId}, new CityMapper());
		String cityName= cm.getCityName();
		return cityName;
	}
	
}
