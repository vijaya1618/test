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

import com.pennantExamination.DAO.SetsDAO;
import com.pennantExamination.pojos.SetsModel;

public class SetsDAOImpl implements SetsDAO{

private JdbcTemplate jdbcTemplate;
    
    public void setDataSource(DataSource datasource)
    {
    	this.jdbcTemplate=new JdbcTemplate(datasource);
    }
    public void setJdbcTemplate(JdbcTemplate  jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    
    
    protected class SetsMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			SetsModel sm = new SetsModel();
			sm.setSetId(rs.getInt("set_id"));
			sm.setSetNo(rs.getInt("set_number"));
			sm.setsetEnabled(rs.getInt("enabled"));
			return sm;
		}
	}
    
	public List<SetsModel> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from Sets";
    	List<SetsModel> li=(List<SetsModel>)jdbcTemplate.query(sql,new SetsMapper());
    	SetsModel e=new SetsModel();
    	e=li.get(0);
    	//System.out.println(e.getSetNo());
		return li;
	}
	
	public SetsModel findById() {
		// TODO Auto-generated method stub
		Session sess=Sessions.getCurrent();
		int a= Integer.parseInt((String) sess.getAttribute("id3"));
		String sql="select * from Sets where set_id = ?";
		return (SetsModel) jdbcTemplate.queryForObject(sql,new Object[] {a},new SetsMapper());
	}
	
	public void insert(SetsModel sm) {
		// TODO Auto-generated method stub
		try {
			
			int st=0;
			String qry = "insert into Sets(set_number,enabled) values(?,?)";
			Object[] params = new Object[] {sm.getSetNo(),sm.getsetEnabled()};
			int types[] = new int[] {Types.INTEGER,Types.INTEGER};
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
	
	public void update(SetsModel sm)
	{
		try {
				int st=0;
		      String sql = "UPDATE  Sets SET set_number=? ,enabled=? where set_id=?";
			  Object[] params = new Object[] {sm.getSetNo(),sm.getsetEnabled(),sm.getSetId()};
			  int types[]=new int[] {Types.INTEGER,Types.INTEGER,Types.INTEGER};
			  st=jdbcTemplate.update(sql, params,types);
			  if(st==1)
				{
					//System.out.println("success");
					Messagebox.show("Sets Updated !!!");
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
	
	public void setsdelete(int id)
	{
		try 
		{
			int st=0;
			String sql = "DELETE FROM Sets WHERE set_id = ?";
			//System.out.println(sql);
			Object[] params = new Object[] { id };
			int types[] = new int[] { Types.INTEGER};
			st=jdbcTemplate.update(sql, params, types);
			if(st==1)
			{
				//System.out.println("success");
				Messagebox.show("Set Deleted !!!");
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
