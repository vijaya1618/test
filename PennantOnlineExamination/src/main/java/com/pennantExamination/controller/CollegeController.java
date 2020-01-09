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
import com.pennantExamination.DAO.CollegeDAO;
import com.pennantExamination.pojos.CollegeModel;
public class CollegeController extends Window{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

CollegeDAO collegeDAO;
 
 @Wire
 private Window win;
 
 
 private ListModel<CollegeModel> college;
	public ListModel<CollegeModel> getCollege() {
	return college;
}
	public void onCreate()
	{
		//List<CollegeModel> result;
		//Listbox lb=(Listbox)this.getFellow("collegeListBox");
		//lb.getItems().clear();
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		collegeDAO = (CollegeDAO)ctx.getBean("collegeDAO");
			Listbox li=(Listbox)this.getFellow("collegeListBox");
			ListModelList<CollegeModel> list=new ListModelList<CollegeModel>(collegeDAO.findAll());
			li.setModel(list);
			///college=new ListModelList<CollegeModel>(collegeDAO.findAll());
			//((ListModelList<CollegeModel>) college).setMultiple(true);
	}
	
	public void collegeUpdate(String id){
		Session ses=Sessions.getCurrent();
		ses.setAttribute("collegeId",id);
		 Window window = (Window)Executions.createComponents(
	                "/EditCollege.zul", null, null);
	        window.doModal();
		
		
	}
	
	public void delete(String id) throws Exception {
		System.out.println(id);
		String s = CharMatcher.is('w').removeFrom(id);
		int a = Integer.parseInt(s);
		System.out.println(a);
		collegeDAO.collegedelete(a);
		onCreate();
		Executions.sendRedirect("Admin.zul");
	}
	public void showModal() {
        //create a window programmatically and use it as a modal dialog.
        Window window = (Window)Executions.createComponents(
                "/AddCollege.zul", null, null);
        window.doModal();
    }
	
}
	
