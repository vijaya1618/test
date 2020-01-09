package com.pennantExamination.controller;


import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import com.google.common.base.CharMatcher;
import com.pennantExamination.DAO.TestSegmentDAO;
import com.pennantExamination.pojos.TestSegmentModel;
public class TestSegmentController extends Window{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TestSegmentDAO testsegmentDAO;
 
 @Wire
 private Window win;
 
 
 private ListModel<TestSegmentModel> testsegment;
	public ListModel<TestSegmentModel> getTestSegment() {
	return testsegment;
}
	public void onCreate()
	{
		//List<TestSegmentModel> result;
		//Listbox lb=(Listbox)this.getFellow("testsegmentListBox");
		//lb.getItems().clear();
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		testsegmentDAO = (TestSegmentDAO)ctx.getBean("testsegmentDAO");
			Listbox li=(Listbox)this.getFellow("testsegmentListBox");
			ListModelList<TestSegmentModel> list=new ListModelList<TestSegmentModel>(testsegmentDAO.findAll());
			li.setModel(list);
			///testsegment=new ListModelList<TestSegmentModel>(testsegmentDAO.findAll());
			//((ListModelList<TestSegmentModel>) testsegment).setMultiple(true);
	}
	
	public void testsegmentUpdate(String id){
		Session ses=Sessions.getCurrent();
		ses.setAttribute("testsegmentId",id);
		 Window window = (Window)Executions.createComponents(
	                "/EditTestSegment.zul", null, null);
	        window.doModal();
		
		
	}
	public void delete(String id) throws Exception {
		System.out.println(id);
		String s = CharMatcher.is('w').removeFrom(id);
		int a = Integer.parseInt(s);
		System.out.println(a);
		testsegmentDAO.testsegmentdelete(a);
		onCreate();
		Executions.sendRedirect("Admin.zul");
	}
	public void showModal() {
        //create a window programmatically and use it as a modal dialog.
        Window window = (Window)Executions.createComponents(
                "/AddTestSegment.zul", null, null);
        window.doModal();
    }
	
}

