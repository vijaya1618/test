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

import com.pennantExamination.DAO.CityDAO;
import com.pennantExamination.pojos.CityModel;

public class CityDAOImpl implements CityDAO{

    private JdbcTemplate jdbcTemplate;
    
    public void setDataSource(DataSource datasource)
    {
    	this.jdbcTemplate=new JdbcTemplate(datasource);
    }
    public void setJdbcTemplate(JdbcTemplate  jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
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
    
	public List<CityModel> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from Cities";
    	List<CityModel> li=(List<CityModel>)jdbcTemplate.query(sql,new CityMapper());
    	CityModel c=new CityModel();
    	c=li.get(0);
    	System.out.println(c.getCityName());
		return li;
	}
	public CityModel findById() {
		// TODO Auto-generated method stub
		Session sess=Sessions.getCurrent();
		int a= Integer.parseInt((String) sess.getAttribute("cityId"));
		String sql="select * from Cities where city_id = ?";
		return (CityModel) jdbcTemplate.queryForObject(sql,new Object[] {a},new CityMapper());
	}
	
	public void insert(CityModel cty) {
		// TODO Auto-generated method stub
		try {
			
			int st=0;
			String qry = "insert into Cities(city_name,enabled) values(?,?)";
			Object[] params = new Object[] {cty.getCityName(),cty.getEnabled()};
			int types[] = new int[] { Types.VARCHAR,Types.INTEGER};
			st=jdbcTemplate.update(qry, params, types);
			
			if(st==1)
			{
				System.out.println("success");
				Messagebox.show("City Registered succuessfully !!!!");
			}
			else
			{
				System.out.println("fail");
				Messagebox.show("City Registration failed !!!!");
			}	
		}
		 catch(Exception c)
		 {
			 c.printStackTrace();
		 }
	} 
	
	public void update(CityModel cty)
	{
		try {
			System.out.println('i');
			Session ses=Sessions.getCurrent();
			int a= Integer.parseInt((String) ses.getAttribute("cityId"));
		      String sql = "UPDATE  Cities SET city_name=? where city_id = "+a+"";
			  Object[] params = new Object[] {cty.getCityName()};
			  int types[]=new int[] {Types.VARCHAR};
			  jdbcTemplate.update(sql, params,types);
		  
			  System.out.println(sql);
			}
		catch(Exception c)
			{
				c.printStackTrace();
			}
	}
	
	public void citydelete(int id)
	{
		try 
		{
			int a=id;
			String sql = "DELETE FROM Colleges WHERE city_id = ? ; DELETE FROM cities WHERE city_id = ? ";
			//System.out.println(sql);
			Object[] params = new Object[] { id,a};
			int types[] = new int[] { Types.INTEGER , Types.INTEGER};
			jdbcTemplate.update(sql, params, types);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
