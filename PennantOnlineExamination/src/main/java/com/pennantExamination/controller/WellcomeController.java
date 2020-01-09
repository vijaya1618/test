package com.pennantExamination.controller;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.OnlineExamDAO;
import com.pennantExamination.pojos.Users;

public class WellcomeController extends Window {

	@Wire
	private Window userWin;

	private static final long	serialVersionUID	= -71422545405325060L;
	private static final Logger	logger				= Logger.getLogger(UserController.class);
	 OnlineExamDAO onlineexamdao;
	
	public void onCreate()
	{
		Session sess=Sessions.getCurrent();
		Users user = (Users) sess.getAttribute("user");
		Label userLabel = (Label)this.getFellow("welcomeUser");
		userLabel.setValue("welcome "+user.getFullName()+"!!");
	}
}
