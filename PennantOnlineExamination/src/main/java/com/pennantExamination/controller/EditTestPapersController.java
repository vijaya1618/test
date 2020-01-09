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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.TestPapersDAO;
import com.pennantExamination.pojos.TestPapersModel;

public class EditTestPapersController extends Window implements Composer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire   
	 private Window editTestPapersWin;
	
	TestPapersDAO testPapersDao;

	TestPapersModel tpm=new TestPapersModel();
	
	public void onCreate()
	{
		Intbox questionId=(Intbox) this.getFellow("editQuestionId");
		Textbox question=(Textbox) this.getFellow("editQuestion");
		Textbox optionA=(Textbox) this.getFellow("editOptionA");
		Textbox optionB=(Textbox) this.getFellow("editOptionB");
		Textbox optionC=(Textbox) this.getFellow("editOptionC");
		Textbox optionD=(Textbox) this.getFellow("editOptionD");
		Textbox correctAnswer=(Textbox) this.getFellow("editCorrectAnswer");
		Combobox qsnType=(Combobox) this.getFellow("editQsnType");
		Intbox setId=(Intbox) this.getFellow("editSetId");
		
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		testPapersDao = (TestPapersDAO) ctx.getBean("TestPapersDAO");
		
		tpm =testPapersDao.findById();
		questionId.setValue(tpm.getQuestionId());
		question.setValue(tpm.getQuestion());
		optionA.setValue(tpm.getOptionA());
		optionB.setValue(tpm.getOptionB());
		optionC.setValue(tpm.getOptionC());
		optionD.setValue(tpm.getOptionD());
		correctAnswer.setValue(tpm.getCorrectOption());
		
		//qsnType.setValue(tpm.getQuestionType());
		 Comboitem item1 = null;
		 String qsnTypeValue=null;
		 for(int i=0;i < qsnType.getItems().size();i++){
			item1 =(Comboitem) qsnType.getItems().get(i);
			qsnTypeValue=(item1.getValue().toString());
			 if(tpm.getQuestionType().equalsIgnoreCase(qsnTypeValue)){	
				 qsnType.setSelectedItem(item1);
				 break;
			 }
		 }
	
		setId.setValue(tpm.getSetId());
		
	}
	
	public void onUpdate()
	{
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		
		testPapersDao = (TestPapersDAO) ctx.getBean("TestPapersDAO");
		
		tpm.setQuestionId(((Intbox) this.getFellow("editQuestionId")).getValue());
		
		tpm.setQuestion(((Textbox) this.getFellow("editQuestion")).getValue());
		
		tpm.setOptionA(((Textbox) this.getFellow("editOptionA")).getValue());
		
		tpm.setOptionB(((Textbox) this.getFellow("editOptionB")).getValue());
		
		tpm.setOptionC(((Textbox) this.getFellow("editOptionC")).getValue());
		
		tpm.setOptionD(((Textbox) this.getFellow("editOptionD")).getValue());
		
		tpm.setCorrectOption(((Textbox) this.getFellow("editCorrectAnswer")).getValue());
		
		tpm.setQuestionType(((Combobox) this.getFellow("editQsnType")).getValue());
		
		tpm.setSetId(((Intbox) this.getFellow("editSetId")).getValue());
		
		testPapersDao.update(tpm);
	}

	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
