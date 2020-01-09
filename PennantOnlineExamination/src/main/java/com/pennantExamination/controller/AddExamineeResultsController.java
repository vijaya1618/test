package com.pennantExamination.controller;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.ExamineeResultsDAO;
import com.pennantExamination.pojos.ExamineeResultsModel;

public class AddExamineeResultsController extends Window{

	private static final long serialVersionUID = 1L;
	
	ExamineeResultsDAO examineeResultsDAO;
	
	ExamineeResultsModel results=new ExamineeResultsModel();
	
	public void insertExamineeResults()
	{
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		examineeResultsDAO = (ExamineeResultsDAO) ctx.getBean("examResultsDAO");
		
		results.setExamineeId(((Intbox)this.getFellow("addExamineeId")).getValue());
		results.setExamineeFullName(((Textbox) this.getFellow("addName")).getValue());
		results.setExamineeSpecialization(((Textbox) this.getFellow("addSpecialization")).getValue());
	    results.setAptitudeScore(((Intbox)this.getFellow("addAptitudeScore")).getValue());
		results.setTechnicalScore(((Intbox)this.getFellow("addTechnicalScore")).getValue());
		results.setTotalScore(((Intbox)this.getFellow("addTotalScore")).getValue());
		
		examineeResultsDAO.insert(results);
	}
}

