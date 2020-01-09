package com.rahul.Sessions;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

import com.rahul.Login.LoginModel;

public class Verify_loginCredentials implements Initiator{

	AuthenticationService as=new AuthenticationServiceImpl();
	
	 public void doInit(Page page, Map<String, Object> args) throws Exception {

	        LoginModel l = as.getLoginCredential();
	        if(l==null){
	            Executions.sendRedirect("../index.zul");
	            return;
	        }
	    }
}
