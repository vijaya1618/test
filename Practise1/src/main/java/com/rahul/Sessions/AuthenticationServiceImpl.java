package com.rahul.Sessions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import com.rahul.Login.*;
public class AuthenticationServiceImpl implements AuthenticationService{
	Session sess = Sessions.getCurrent();
	
	public LoginModel getLoginCredential(){
		
		LoginModel l=(LoginModel)sess.getAttribute("login_Credential");
		return l;
	}
	
	public void setLoginCredential(LoginModel lm){
		
		sess.setAttribute("login_Credential",lm);
		
	}

	public void setDetails(Session_Model sm) {
		
		sess.setAttribute("Details",sm);
	}

	public Session_Model getDetails() {
		
			Session se= Sessions.getCurrent();
			Session_Model sm=(Session_Model)se.getAttribute("Details");
			
		return sm;
	}
}
