package com.pennantExamination.DAOImplementation;

import java.util.List;
import java.sql.*;


import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import com.pennantExamination.DAO.SpecializationDAO;
import com.pennantExamination.DAOImplementation.DegreeDAOImpl.DegreeMapper;
import com.pennantExamination.DAOImplementation.LogInDAOImplementation.UserMapper;
import com.pennantExamination.pojos.DegreeModel;
import com.pennantExamination.pojos.SpecializationModel;
import com.pennantExamination.pojos.Users;

public class SpecializationDAOImpl implements SpecializationDAO{

    private JdbcTemplate jdbcTemplate;
    
    public void setDataSource(DataSource datasource)
    {
    	this.jdbcTemplate=new JdbcTemplate(datasource);
    }
    public void setJdbcTemplate(JdbcTemplate  jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    
    protected class SpecializationMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SpecializationModel slz = new SpecializationModel();
			slz.setSpecializationId(rs.getInt("spec_id"));
			slz.setSpecializationName(rs.getString("spec_name"));
			slz.setDegreeId(rs.getInt("degree_id"));
			slz.setEnabled(rs.getInt("enabled"));
			
			return slz;
		}
	}
    
	public List<SpecializationModel> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from Specializations";
    	List<SpecializationModel> li=(List<SpecializationModel>)jdbcTemplate.query(sql,new SpecializationMapper());
    	SpecializationModel c=new SpecializationModel();
    	c=li.get(0);
    	System.out.println(c.getSpecializationName());
		return li;
	}
	public SpecializationModel findById() {
		// TODO Auto-generated method stub
		Session sess=Sessions.getCurrent();
		int a= Integer.parseInt((String) sess.getAttribute("specializationId"));
		String sql="select * from Specializations where spec_id = ?";
		return (SpecializationModel) jdbcTemplate.queryForObject(sql,new Object[] {a},new SpecializationMapper());
	}
	
	public void insert(SpecializationModel slz) {
		// TODO Auto-generated method stub
		try {
			
			int st=0;
			String qry = "insert into Specializations(spec_name,degree_id,enabled) values(?,?,?)";
			Object[] params = new Object[] {slz.getSpecializationName(),slz.getDegreeId(),slz.getEnabled()};
			int types[] = new int[] { Types.VARCHAR,Types.INTEGER,Types.INTEGER};
			st=jdbcTemplate.update(qry, params, types);
			
			if(st==1)
			{
				System.out.println("success");
				Messagebox.show("Specialization Registered succuessfully !!!!");
			}
			else
			{
				System.out.println("fail");
				Messagebox.show("Specialization Registration failed !!!!");
			}	
		}
		 catch(Exception c)
		 {
			 c.printStackTrace();
		 }
	} 
	
	public void update(SpecializationModel slz)
	{
		try {
			System.out.println('i');
			Session ses=Sessions.getCurrent();
			int a= Integer.parseInt((String) ses.getAttribute("specializationId"));
		      String sql = "UPDATE  Specializations SET spec_name=?,degree_id=?,enabled=? where spec_id = "+a+"";
			  Object[] params = new Object[] {slz.getSpecializationName(),slz.getDegreeId(),slz.getEnabled(),slz.getSpecializationId()};
			  int types[]=new int[] {Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER};
			  jdbcTemplate.update(sql, params,types);
		  
			  System.out.println(sql);
			}
		catch(Exception c)
			{
				c.printStackTrace();
			}
	}
	
	 protected class DegreeMapper implements RowMapper {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				DegreeModel dgr = new DegreeModel();
				dgr.setDegreeId(rs.getInt("degree_id"));
				dgr.setDegreeName(rs.getString("degree_name"));
				return dgr;
			}
		}
	
	public List<DegreeModel> getdegreeList() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String sql="select * from Degree";
		    	List<DegreeModel> li=(List<DegreeModel>)jdbcTemplate.query(sql,new DegreeMapper());
		    	DegreeModel c=new DegreeModel();
		    	c=li.get(0);
		    	System.out.println(c.getDegreeName());
				return li;
	}
	
	public String getDegreeNamebyId(int degreeId) {
		String sql="select * from Degree where degree_id= ?";
		DegreeModel dm=(DegreeModel)jdbcTemplate.queryForObject(sql, new Object[] {degreeId}, new DegreeMapper());
		String degreeName= dm.getDegreeName();
		return degreeName;
	}
	
	public void collegedelete(int id)
	{
		try 
		{
			String sql = " DELETE FROM Specializations WHERE spec_id = ? ";
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
	
	public void specializationdelete(int id) {
		// TODO Auto-generated method stub
		
	}
	
}
