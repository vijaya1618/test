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

import com.pennantExamination.DAO.TestSegmentDAO;
import com.pennantExamination.pojos.TestSegmentModel;

public class TestSegmentDAOImpl implements TestSegmentDAO{

    private JdbcTemplate jdbcTemplate;
    
    public void setDataSource(DataSource datasource)
    {
    	this.jdbcTemplate=new JdbcTemplate(datasource);
    }
    public void setJdbcTemplate(JdbcTemplate  jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    
    protected class TestSegmentMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TestSegmentModel tst = new TestSegmentModel();
			tst.setTestsegmentId(rs.getInt("section_id"));
			tst.setTestsegmentName(rs.getString("section_name"));
			return tst;
		}
	}
    
	public List<TestSegmentModel> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from Segregation";
    	List<TestSegmentModel> li=(List<TestSegmentModel>)jdbcTemplate.query(sql,new TestSegmentMapper());
    	TestSegmentModel c=new TestSegmentModel();
    	c=li.get(0);
    	System.out.println(c.getTestsegmentName());
		return li;
	}
	public TestSegmentModel findById() {
		// TODO Auto-generated method stub
		Session sess=Sessions.getCurrent();
		int a= Integer.parseInt((String) sess.getAttribute("testsegmentId"));
		String sql="select * from Segregation where section_id = ?";
		return (TestSegmentModel) jdbcTemplate.queryForObject(sql,new Object[] {a},new TestSegmentMapper());
	}
	
	public void insert(TestSegmentModel tst) {
		// TODO Auto-generated method stub
		try {
			
			int st=0;
			String qry = "insert into Segregation(section_name,enabled) values(?,?)";
			Object[] params = new Object[] {tst.getTestsegmentName(),tst.getEnabled()};
			int types[] = new int[] { Types.VARCHAR,Types.INTEGER};
			st=jdbcTemplate.update(qry, params, types);
			
			if(st==1)
			{
				System.out.println("success");
				Messagebox.show("TestSegment Registered succuessfully !!!!");
			}
			else
			{
				System.out.println("fail");
				Messagebox.show("TestSegment Registration failed !!!!");
			}	
		}
		 catch(Exception c)
		 {
			 c.printStackTrace();
		 }
	} 
	
	public void update(TestSegmentModel tst)
	{
		try {
			System.out.println('i');
			Session ses=Sessions.getCurrent();
			int a= Integer.parseInt((String) ses.getAttribute("testsegmentId"));
		      String sql = "UPDATE  Segregation SET section_name=? where section_id = "+a+"";
			  Object[] params = new Object[] {tst.getTestsegmentName()};
			  int types[]=new int[] {Types.VARCHAR};
			  jdbcTemplate.update(sql, params,types);
		  
			  System.out.println(sql);
			}
		catch(Exception c)
			{
				c.printStackTrace();
			}
	}
	public void testsegmentdelete(int id)
	{
		try 
		{
			String sql = " DELETE FROM Segregation WHERE section_id = ? ";
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
