package com.pennantExamination.controller;



import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.TestSegmentDAO;
import com.pennantExamination.pojos.TestSegmentModel;
 
public class AddTestSegmentController extends Window{
    private static final long serialVersionUID = 1L;
    
 @Wire   
 private Window AddTestSegmentWin;
    
 TestSegmentDAO testsegmentDAO;
	
 TestSegmentModel tst=new TestSegmentModel(); 
 
 
 public void insertTestSegmentDetails()
	 {
		 @SuppressWarnings("deprecation")
		 ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		 testsegmentDAO = (TestSegmentDAO)ctx.getBean("testsegmentDAO");
		 
		 	
		 tst.setTestsegmentName(((Textbox) this.getFellow("addTestName")).getValue());
		 Checkbox enabled=(Checkbox) this.getFellow("enabled");
		 int en=0;
		if(enabled.isChecked()){
			en = 1;	
		}else{
			en=0;
		}
		tst.setEnabled(en);

		testsegmentDAO.insert(tst);
	 }

	
	
}
