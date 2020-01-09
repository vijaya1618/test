package com.pennantExamination.DAOImplementation;

import java.util.List;
import java.sql.*;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Listbox;

import com.pennantExamination.DAO.DegreeDAO;
import com.pennantExamination.pojos.DegreeModel;

public class DegreeDAOImpl implements DegreeDAO{

    private JdbcTemplate jdbcTemplate;
    
    public void setDataSource(DataSource datasource)
    {
    	this.jdbcTemplate=new JdbcTemplate(datasource);
    }
    public void setJdbcTemplate(JdbcTemplate  jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    
    protected class DegreeMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DegreeModel dgr = new DegreeModel();
			dgr.setDegreeId(rs.getInt("degree_id"));
			dgr.setDegreeName(rs.getString("degree_name"));
			dgr.setEnabled(rs.getInt("enabled"));
			return dgr;
		}
	}
    
	public List<DegreeModel> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from Degree";
    	List<DegreeModel> li=(List<DegreeModel>)jdbcTemplate.query(sql,new DegreeMapper());
    	DegreeModel c=new DegreeModel();
    	c=li.get(0);
    	System.out.println(c.getDegreeName());
		return li;
	}
	public DegreeModel findById() {
		// TODO Auto-generated method stub
		Session sess=Sessions.getCurrent();
		int a= Integer.parseInt((String) sess.getAttribute("degreeId"));
		String sql="select * from Degree where degree_id = ?";
		return (DegreeModel) jdbcTemplate.queryForObject(sql,new Object[] {a},new DegreeMapper());
	}
	
	public void insert(DegreeModel dgr) {
		// TODO Auto-generated method stub
		try {
			
			int st=0;
			String qry = "insert into Degree (degree_name,enabled) values(?,?)";
			Object[] params = new Object[] {dgr.getDegreeName(),dgr.getEnabled()};
			int types[] = new int[] { Types.VARCHAR,Types.INTEGER};
			st=jdbcTemplate.update(qry, params, types);
			
			if(st==1)
			{
				System.out.println("success");
				Messagebox.show("Degree Registered succuessfully !!!!");
			}
			else
			{
				System.out.println("fail");
				Messagebox.show("Degree Registration failed !!!!");
			}	
		}
		 catch(Exception c)
		 {
			 c.printStackTrace();
		 }
	} 
	
	public void update(DegreeModel dgr)
	{
		try {
			System.out.println('i');
			Session ses=Sessions.getCurrent();
			int a= Integer.parseInt((String) ses.getAttribute("degreeId"));
		      String sql = "UPDATE  Degree SET degree_name=?,enabled=? where degree_id = "+a+"";
			  Object[] params = new Object[] {dgr.getDegreeName(),dgr.getEnabled()};
			  int types[]=new int[] {Types.VARCHAR,Types.INTEGER};
			  jdbcTemplate.update(sql, params,types);
			  Messagebox.show("Degree Updated succuessfully !!!!");
			  System.out.println(sql);
			}
		catch(Exception c)
			{
				c.printStackTrace();
			}
	}
	
	public void degreedelete(int id)
	{
		try 
		{
			String sql = " DELETE FROM Degree WHERE degree_id = ? ";
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
	
}
