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
import com.pennantExamination.DAO.DegreeDAO;
import com.pennantExamination.pojos.DegreeModel;
public class DegreeController extends Window{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

DegreeDAO degreeDAO;
 
 @Wire
 private Window win;
 
 
 private ListModel<DegreeModel> degree;
	public ListModel<DegreeModel> getDegree() {
	return degree;
}
	public void onCreate()
	{
		//List<DegreeModel> result;
		//Listbox lb=(Listbox)this.getFellow("degreeListBox");
		//lb.getItems().clear();
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		degreeDAO = (DegreeDAO)ctx.getBean("degreeDAO");
			Listbox li=(Listbox)this.getFellow("degreeListBox");
			ListModelList<DegreeModel> list=new ListModelList<DegreeModel>(degreeDAO.findAll());
			li.setModel(list);
			///degree=new ListModelList<CityModel>(degreeDAO.findAll());
			//((ListModelList<DegreeModel>) city).setMultiple(true);
	}
	
	public void degreeUpdate(String id){
		Session ses=Sessions.getCurrent();
		ses.setAttribute("degreeId",id);
		 Window window = (Window)Executions.createComponents(
	                "/EditDegree.zul", null, null);
	        window.doModal();
		
		
	}
	
	public void delete(String id) throws Exception {
		System.out.println(id);
		String s = CharMatcher.is('w').removeFrom(id);
		int a = Integer.parseInt(s);
		System.out.println(a);
		degreeDAO.degreedelete(a);
		onCreate();
		Executions.sendRedirect("Admin.zul");
	}
	public void showModal() {
        //create a window programmatically and use it as a modal dialog.
        Window window = (Window)Executions.createComponents(
                "/AddDegree.zul", null, null);
        window.doModal();
    }
	
}

