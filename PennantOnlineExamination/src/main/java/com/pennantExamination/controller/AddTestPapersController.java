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
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.TestPapersDAO;
import com.pennantExamination.pojos.TestPapersModel;
import com.pennantExamination.pojos.TestSegmentModel;

import org.zkoss.zul.Combobox;

public class AddTestPapersController extends Window{

	private static final long serialVersionUID = 1L;
	
	TestPapersDAO testPapersDao;

	TestPapersModel tpm=new TestPapersModel();
	
	private ListModel<TestSegmentModel> testSegmentList;

	public ListModel<TestSegmentModel> getTestSegmentList() {
		return testSegmentList;
	}
	
	  public void onCreate(){
	 	   ApplicationContext ctx = 
	 				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
	 	  testPapersDao = (TestPapersDAO) ctx.getBean("TestPapersDAO");
	 	  
	 	   Combobox sectionDropdown=(Combobox)this.getFellow("addQsnType");
	 	   ListModelList<TestSegmentModel> list=new ListModelList(testPapersDao.getTestSegmentList());
	 	  sectionDropdown.setModel(list);  
	    }
	
	public void insertTestQuestions()
	{
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		testPapersDao = (TestPapersDAO) ctx.getBean("TestPapersDAO");
		
		tpm.setQuestion(((Textbox) this.getFellow("addQuestion")).getValue());
		tpm.setOptionA(((Textbox) this.getFellow("addOptionA")).getValue());
		tpm.setOptionB(((Textbox)this.getFellow("addOptionB")).getValue());
		tpm.setOptionC(((Textbox)this.getFellow("addOptionC")).getValue());
		tpm.setOptionD(((Textbox)this.getFellow("addOptionD")).getValue());
		tpm.setCorrectOption(((Textbox) this.getFellow("addCorrectAnswer")).getValue());
		tpm.setQuestionType(((Combobox) this.getFellow("addQsnType")).getValue());
		tpm.setSetId(((Intbox)this.getFellow("addSetId")).getValue());
		
		testPapersDao.insert(tpm);
	}
}
