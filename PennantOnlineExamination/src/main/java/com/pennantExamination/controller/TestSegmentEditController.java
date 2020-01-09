package com.pennantExamination.controller;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.TestSegmentDAO;

import com.pennantExamination.pojos.TestSegmentModel;


public class TestSegmentEditController extends Window implements Composer<Component>{
	private static final long serialVersionUID = 1L;

	@Wire   
	 private Window EditTestSegmentWin;
	 
	TestSegmentDAO TestSegmentDao;
	 
	 public void onCreate(){
		 
		 Textbox testsegmentName=(Textbox) this.getFellow("editTestSegmentName");
	 
		 @SuppressWarnings("deprecation")
		 ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		 TestSegmentDao = (TestSegmentDAO)ctx.getBean("testsegmentDAO");
		 TestSegmentModel testsegment =TestSegmentDao.findById();
		 
		 testsegmentName.setValue(testsegment.getTestsegmentName());
	 }
	 
	 public void onUpdate()
	 {
		 @SuppressWarnings("deprecation")
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		 TestSegmentDao = (TestSegmentDAO)ctx.getBean("testsegmentDAO");
		 TestSegmentModel tstedit =new TestSegmentModel();
			
			tstedit.setTestsegmentName(((Textbox) this.getFellow("editTestSegmentName")).getValue());
			
			TestSegmentDao.update(tstedit);
	 }

	
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		
	}
	 
	
}
