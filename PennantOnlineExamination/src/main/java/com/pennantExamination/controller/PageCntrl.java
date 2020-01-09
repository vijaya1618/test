package com.pennantExamination.controller;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;

public class PageCntrl {
	private String menuPage;

	public String getMenuPage() {
		return menuPage;
	}

	public void setMenuPage(String menuPage) {
		this.menuPage = menuPage;
	}
	
	@Init
	public void init() {
		menuPage="/widgets/MyProfile/welcome.zul";
	}
	
	@Command
	@NotifyChange("menuPage")
	public void MyProfilePage() {
		menuPage="/widgets/MyProfile/Profile.zul";
		
	}
	
	@Command
	@NotifyChange("menuPage")
	public void MyScorePage() {
		menuPage="/widgets/MyProfile/score.zul";
		
	}
	@Command
	@NotifyChange("menuPage")
	public void StartExam() {
		menuPage="/widgets/MyProfile/StartExam.zul";
		
	}
	
	@Command
	@NotifyChange("menuPage")
	public void MyMorePage() {
		menuPage="/widgets/MyProfile/More.zul";
		
	}

	@Command
	@NotifyChange("menuPage")
	public void LogoutPage() {
		Executions.sendRedirect("index.zul");
		
	}
}
