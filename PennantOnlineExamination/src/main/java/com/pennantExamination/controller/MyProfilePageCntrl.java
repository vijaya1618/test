package com.pennantExamination.controller;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

public class MyProfilePageCntrl {
	private String myProfileMenuPage;

	public String getMyProfileMenuPage() {
		return myProfileMenuPage;
	}


	public void setMyProfileMenuPage(String myProfileMenuPage) {
		this.myProfileMenuPage = myProfileMenuPage;
	}


	@Init
	public void init() {
		myProfileMenuPage="/widgets/MyProfile/pro.zul";
		
	}
	
	  @Command
	  @NotifyChange("myProfileMenuPage")
	  public void MyProfilePage() {
	  myProfileMenuPage="/widgets/MyProfile/welcome.zul"; }
	 
	
	
	  @Command
	  @NotifyChange("myProfileMenuPage")
	  public void PersonalDetails() {
	  myProfileMenuPage="/widgets/MyProfile/pro.zul"; }
	 
	
	
	@Command
	@NotifyChange("myProfileMenuPage")
	public void TechnicalPage() {
	myProfileMenuPage="/widgets/Details/Technical.zul";
	}
	
	@Command
	@NotifyChange("myProfileMenuPage")
	public void EducationPage() {
	myProfileMenuPage="/widgets/Details/Education.zul";
	}
	
	

}
