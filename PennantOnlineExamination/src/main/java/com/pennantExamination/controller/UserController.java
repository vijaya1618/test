package com.pennantExamination.controller;

import java.sql.Types;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.OnlineExamDAO;
import com.pennantExamination.pojos.ExamineeModel;
import com.pennantExamination.pojos.Users;

public class UserController extends Window{
	
	@Wire
	private Window userWin;

	private static final long	serialVersionUID	= -71422545405325060L;
	private static final Logger	logger				= Logger.getLogger(UserController.class);
	 OnlineExamDAO onlineexamdao;
	
	public void onCreate()
	{
		Session sess=Sessions.getCurrent();
		Users user = (Users) sess.getAttribute("user");
		/*Label userLabel = (Label)this.getFellow("welcomeUser");
		userLabel.setValue("welcome "+user.getFullName()+"!!");
		*/
		
		Intbox examineeId=(Intbox) this.getFellow("examineeid");
		
		Textbox fullName=(Textbox) this.getFellow("fullName");
		
		Textbox Username=(Textbox) this.getFellow("userName");
		
		Textbox  Email=(Textbox) this.getFellow("email");
		
		Textbox Phone=(Textbox) this.getFellow("phoneNo");
		
		 Radiogroup Gender=(Radiogroup) this.getFellow("gender");
		
		 Datebox DOB=(Datebox) this.getFellow("dob");
		 
		 Combobox  YearofPassing=(Combobox) this.getFellow("yop");
		 
		 Combobox Course=(Combobox) this.getFellow("course");
		 
		 Combobox Specialization=(Combobox) this.getFellow("specialization");
		 
		 Combobox Colleges=(Combobox) this.getFellow("college");
		 
		 Textbox Address=(Textbox) this.getFellow("address");
		 
		 Longbox Aadhar=( Longbox) this.getFellow("aadhar");
	
		
		
		examineeId.setValue(user.getExaminee_id());
		fullName.setValue(user.getFullName());
		Username.setValue(user.getUserName());
		Email.setValue(user.getEmail());
		Phone.setValue(user.getPhoneNo());
		
		String genderValue=null;
		Radio value = null;
		for(int i=0;i < Gender.getItems().size(); i++){
			value = (Radio) Gender.getItems().get(i);
			genderValue=value.getValue().toString();
			if(user.getGender().equalsIgnoreCase(genderValue)){
				Gender.setSelectedItem(value);
				
			}
			
		}
		 
		 DOB.setValue(user.getDob());
		 
		 
		 
		 Comboitem item1 = null;
		 int yopValue=0;
		 for(int i=0;i <YearofPassing.getItems().size();i++){
			item1 =(Comboitem) YearofPassing.getItems().get(i);
			 yopValue=Integer.parseInt(item1.getValue().toString());
			 if(user.getYop() == yopValue ){
				 YearofPassing.setSelectedItem(item1);
				 break;
			 }
			 
		 }
		 
		 
		 Comboitem item2 = null;
		 String courseValue=null;
		 for(int i=0;i < Course.getItems().size();i++){
				item2 =(Comboitem) Course.getItems().get(i);
				courseValue=item2.getValue().toString();
				 if(user.getCourse().equalsIgnoreCase(courseValue)){
					 Course.setSelectedItem(item2);
					 break;
				 }
			 }
		 
		 
		 Comboitem item3 = null;
		 String specializationValue=null;
		 for(int i=0;i < Specialization.getItems().size();i++){
				item3 =(Comboitem) Specialization.getItems().get(i);
				specializationValue=item3.getValue().toString();
				 if(user.getSpecialization().equalsIgnoreCase(specializationValue)){
					 Specialization.setSelectedItem(item3);
					 break;
				 }
			 }
		 
		 
		 Comboitem item4 = null;
		 String collegeValue=null;
		 for(int i=0;i < Colleges.getItems().size();i++){
			 item4 =(Comboitem) Colleges.getItems().get(i);
			 collegeValue=item4.getValue().toString();
				 if(user.getCollege().equalsIgnoreCase(collegeValue)){
					 Colleges.setSelectedItem(item4);
					 break;
				 }
			 }
		 
		 
		 Address.setValue(user.getAddress());
		 Aadhar.setValue(user.getAadhar());
		
				 
	}
	public void onUpdate()
	{
		 ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		 onlineexamdao = (OnlineExamDAO)ctx.getBean("onlineExamDAO");
			Users usredit =new Users();
			
			 usredit.setExaminee_id(((Intbox) this.getFellow("examineeid")).getValue());
			
			 usredit.setFullName(((Textbox) this.getFellow("fullName")).getValue());

			 usredit.setUserName(((Textbox) this.getFellow("userName")).getValue());
			 
			 usredit.setEmail(((Textbox) this.getFellow("email")).getValue());
			 
			 usredit.setPhoneNo(((Textbox) this.getFellow("phoneNo")).getValue());
			 
			 usredit.setGender(((Radiogroup) this.getFellow("gender")).getSelectedItem().getValue().toString());
			 
			 usredit.setDob(((Datebox) this.getFellow("dob")).getValue());
			 
			 usredit.setYop(Integer.parseInt(((Combobox) this.getFellow("yop")).getValue()));
			 
			 usredit.setCourse(((Combobox) this.getFellow("course")).getValue());
			 
			 usredit.setSpecialization(((Combobox) this.getFellow("specialization")).getValue());
			 
			 usredit.setCollege(((Combobox) this.getFellow("college")).getValue());
			 
			 usredit.setAddress(((Textbox) this.getFellow("address")).getValue());
			 
			 usredit.setAadhar(((Longbox) this.getFellow("aadhar")).getValue());
			 
			 onlineexamdao.update(usredit);
			 	
	}
	
	
}
