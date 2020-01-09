package com.pennantExamination.controller;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.LogInDAO;
import com.pennantExamination.DAO.RegistrationDAO;
import com.pennantExamination.pojos.Users;

public class LogInCtrl extends Window{
	
	private Window logIn;
	private static final long	serialVersionUID	= -71422545405325060L;
	private static final Logger	logger				= Logger.getLogger(LogInCtrl.class);
	protected LogInDAO loginDAO;
	 Session ses=Sessions.getCurrent();
	
	public void logInUser(){
		 ApplicationContext ctx = 
					WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		 loginDAO=(LogInDAO)ctx.getBean("logInDAO");
		 String userName=((Textbox)this.getFellow("userName")).getValue();
		 String password=((Textbox)this.getFellow("password")).getValue();
		 String role_id=((Combobox)this.getFellow("usertype")).getValue();
		 Users user = loginDAO.userAuthentication(userName,password);
		 if(user == null){
			 Executions.sendRedirect("/index.zul");
			 Messagebox.show("Examinee login failed !!!!");
		 }else{
			 if(role_id.equals("Admin")){
				 Executions.sendRedirect("/Admin.zul");
				
			 }else{
				 Executions.sendRedirect("/reg.zul");
					ses.setAttribute("user",user);
			 }
		 }
	}

}
