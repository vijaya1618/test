package com.rahul.Login;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkmax.zul.Tablelayout;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Layout;
import org.zkoss.zul.Textbox; 
import org.zkoss.zul.Window;
import com.rahul.Sessions.*;


public class LoginCntrl extends Window{
UserInterface db;
	
	
	LoginModel l=new LoginModel();
	AuthenticationService as=new AuthenticationServiceImpl();
	
	public void onCreate(){
		Textbox userers=(Textbox)this.getFellow("user_name");
		userers.setFocus(true);
	}
	public void verifylogin(){
		Tablelayout login=(Tablelayout)this.getFellow("login");
		Textbox user=(Textbox)this.getFellow("user_name");
		String username=user.getValue();
		Textbox pass=(Textbox)this.getFellow("user_password");
		String password=pass.getValue();
		Label role_label=(Label)this.getFellow("role");
		String user_role=role_label.getValue();
		if(username.equals("")){
			 Clients.showNotification("Enter Username","error", user, "end_center", 2000);
		}else if(password.equals("")){
			 Clients.showNotification("Enter Password","error", pass, "end_center", 2000);
		}else{
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		db = (UserInterface)ctx.getBean("db");
		int val=db.validuser(username, password,user_role);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(val==1){
			
			l.setUsername(username);
			l.setPassword(password);
			l.setUser_role(user_role);
			as.setLoginCredential(l);
			showNotify("Login Sucessfull","info",login);
			Executions.sendRedirect("/zul/Home.zul");
		}else if(val==2){
			showNotify("Username Or Password Wrong","error",login);
		}else if(val==3){
			showNotify("Username Or Password Wrong","error",login);
		}else{
			
			showNotify("Please Try Agian","error",login);
		}
		}
	}
	private void showNotify(String msg,String type, Component ref) {
        Clients.showNotification(msg, type, ref, "middle_center", 2000);
    }
	public void onDestroy(){
		System.out.println("Destroyed");
	}
}
