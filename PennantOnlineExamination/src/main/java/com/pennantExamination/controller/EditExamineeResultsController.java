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
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.ExamineeResultsDAO;
import com.pennantExamination.pojos.ExamineeResultsModel;

public class EditExamineeResultsController extends Window implements Composer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire   
	 private Window editExamineeResultsWin;
	
	ExamineeResultsDAO examineeResultsDAO;
	
	ExamineeResultsModel results=new ExamineeResultsModel();
	
	public void onCreate()
	{
		Intbox examineeId=(Intbox) this.getFellow("editExamineeId");
		Textbox fullName=(Textbox) this.getFellow("editName");
		Textbox specialization=(Textbox) this.getFellow("editSpecialization");
		Intbox aptitudescore=(Intbox) this.getFellow("editAptitudeScore");
		Intbox technicalscore=(Intbox) this.getFellow("editTechnicalScore");
		Intbox totalscore=(Intbox) this.getFellow("editTotalScore");
		
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		examineeResultsDAO = (ExamineeResultsDAO) ctx.getBean("examResultsDAO");
		 results =examineeResultsDAO.findById();
		examineeId.setValue(results.getExamineeId());
		fullName.setValue(results.getExamineeFullName());
		specialization.setValue(results.getExamineeSpecialization());
		aptitudescore.setValue(results.getAptitudeScore());
		technicalscore.setValue(results.getTechnicalScore());
		totalscore.setValue(results.getTotalScore());
	}
	
	public void onUpdate()
	{
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		examineeResultsDAO = (ExamineeResultsDAO) ctx.getBean("examResultsDAO");
		
		results.setExamineeId(((Intbox) this.getFellow("editExamineeId")).getValue());
		
		results.setExamineeFullName(((Textbox) this.getFellow("editName")).getValue());
		
		results.setExamineeSpecialization(((Textbox) this.getFellow("editSpecialization")).getValue());
		
		results.setAptitudeScore(((Intbox) this.getFellow("editAptitudeScore")).getValue());
		
		results.setTechnicalScore(((Intbox) this.getFellow("editTechnicalScore")).getValue());
		
		results.setTotalScore(((Intbox) this.getFellow("editTotalScore")).getValue());
		
		examineeResultsDAO.update(results);
	}
	
	
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
