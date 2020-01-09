package com.pennantExamination.controller;
import com.pennantExamination.pojos.CollegeModel;
import com.pennantExamination.pojos.ExamineeModel;

import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialException;

import org.apache.log4j.Logger;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.RegistrationDAO;
import com.pennantExamination.pojos.Users;
import com.pennantExamination.webui.util.WindowBaseCtrl;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class RegistrationCtrl extends Window{
	
	private static final long	serialVersionUID	= -71422545405325060L;
	private static final Logger	logger				= Logger.getLogger(RegistrationCtrl.class);
	

	private Window registrIn;
	protected RegistrationDAO registerDAO;
	public ListModel<CollegeModel> collegeList ;
	Users user=new Users();
	
   public void onCreate(){
	   ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
	   registerDAO=(RegistrationDAO)ctx.getBean("taskDAO"); 
	   Combobox citydropdown=(Combobox)this.getFellow("college");
	   ListModelList<CollegeModel> list=new ListModelList(registerDAO.getCollegeList());
	   citydropdown.setModel(list);
	   
   }
	
   public void registerUser(){
		
	   ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
	   registerDAO=(RegistrationDAO)ctx.getBean("taskDAO");
		
		user.setFullName(((Textbox)this.getFellow("fullName")).getValue());
		user.setUserName(((Textbox)this.getFellow("userName")).getValue());
	    String s1=((Textbox)this.getFellow("password")).getValue();
	    String s2=((Textbox)this.getFellow("re_typepassword")).getValue();
	   
		/*user.setPassword(((Textbox)this.getFellow("password")).getValue());*/
		user.setEmail(((Textbox)this.getFellow("email")).getValue());
		user.setPhoneNo(((Textbox)this.getFellow("phoneNo")).getValue());
		user.setGender(((Radiogroup)this.getFellow("gender")).getSelectedItem().getValue().toString());
		/*user.setDob(((Datebox)this.getFellow("dob")).getValue());*/
		java.util.Date utildate=((Datebox) this.getFellow("dob")).getValue();
		SimpleDateFormat dateformat=new SimpleDateFormat("YYYY-MM-dd");
			final String stringdate=dateformat.format(utildate);
			Date sqlDate=Date.valueOf(stringdate);
			user.setDob(sqlDate);
			
		user.setYop(Integer.parseInt(((Combobox)this.getFellow("yop")).getValue()));
		user.setCourse(((Combobox)this.getFellow("course")).getValue());
		user.setSpecialization(((Combobox)this.getFellow("specialization")).getValue());
		user.setCollege(((Combobox)this.getFellow("college")).getValue());
		user.setAddress(((Textbox)this.getFellow("address")).getValue());
		user.setAadhar(Long.parseLong(((Textbox)this.getFellow("aadhar")).getValue()));
		
		 if(s1.equals(s2))
		    {
		    int cap=0,num=0,spec=0;
		    
		    for(int i=0;i<s1.length();i++)
		    {
		    	System.out.print(s1.charAt(i));
		    	if((int)s1.charAt(i)>=65 && (int)s1.charAt(i)<=90)
		    		cap++;
		    	if((int)s1.charAt(i)>=48 &&(int)s1.charAt(i)<=57)
		    		num++;
		    	if((int)s1.charAt(i)==35 ||(int)s1.charAt(i)==36||(int)s1.charAt(i)==64)
		    		spec++;
		    }
		    
		    if(cap>=1 && num>=1 && spec>=1 && s1.length()>=8 &&s1.length()<=16)
		    {
		    	user.setPassword(s1);
		    	registerDAO.registerUser(user);
		    	if(true)
		    		Executions.sendRedirect("/index.zul");
		    }
		    else
		    	Messagebox.show("Please Enter valid password");
		    
		    }
		   else
		    	Messagebox.show("Password Mismatch");
		 
		   
		
		 
		
		
		
	}
   
   public void fileUpload() throws FileNotFoundException{
	   
	   EventListener<UploadEvent> el = new EventListener<UploadEvent>(){

		public void onEvent(UploadEvent event) throws FileNotFoundException {
			// TODO Auto-generated method stub
			Media m =event.getMedia();
			byte[] b=m.getByteData();
			user.setResume(b);
			user.setFile(m.getName());
			
		}
		   
	   };
	   Fileupload.get(el);
   }
   
   
}
