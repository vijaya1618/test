package com.pennantExamination.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;

public class DashboardCtrlr extends SelectorComposer<Component>{
	@Wire
	public Include  x;
	
	
	

	@Listen("onClick= #educdetails")
	public void educ() {
		x.setSrc("/examinee_EducationalDetails.zul"); 
	}
	@Listen("onClick= #techdetails")
	public void tech() {
		x.setSrc("/examinee_technicalDetails.zul"); 
	}
	/*@Listen("onClick= #more")
	public void more() {
		x.setSrc("/More.zul"); 
	}*/
}
