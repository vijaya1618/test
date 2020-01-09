package com.pennantExamination.controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

public class AdminLogoutController extends SelectorComposer<Component>{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Listen("onClick = #Logout")
	public void logout() {
		
		Executions.sendRedirect("index.zul");
	}

}
