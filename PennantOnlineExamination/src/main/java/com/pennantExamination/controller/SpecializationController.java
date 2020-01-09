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
import com.pennantExamination.DAO.SpecializationDAO;
import com.pennantExamination.pojos.SpecializationModel;
public class SpecializationController extends Window{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

SpecializationDAO specializationDAO;
 
 @Wire
 private Window win;
 
 
 private ListModel<SpecializationModel> specialization;
	public ListModel<SpecializationModel> getSpecialization() {
	return specialization;
}
	public void onCreate()
	{
		//List<SpecializationModel> result;
		//Listbox lb=(Listbox)this.getFellow("specializationListBox");
		//lb.getItems().clear();
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		specializationDAO = (SpecializationDAO)ctx.getBean("specializationDAO");
			Listbox li=(Listbox)this.getFellow("specializationListBox");
			ListModelList<SpecializationModel> list=new ListModelList<SpecializationModel>(specializationDAO.findAll());
			li.setModel(list);
			///specialization=new ListModelList<SpecializationModel>(specializationDAO.findAll());
			//((ListModelList<SpecializationModel>) specialization).setMultiple(true);
	}
	
	public void specializationUpdate(String id){
		Session ses=Sessions.getCurrent();
		ses.setAttribute("specializationId",id);
		 Window window = (Window)Executions.createComponents(
	                "/EditSpecialization.zul", null, null);
	        window.doModal();
		
		
	}
	public void delete(String id) throws Exception {
		System.out.println(id);
		String s = CharMatcher.is('w').removeFrom(id);
		int a = Integer.parseInt(s);
		System.out.println(a);
		specializationDAO.specializationdelete(a);
		onCreate();
		Executions.sendRedirect("Admin.zul");
	}
	public void showModal() {
        //create a window programmatically and use it as a modal dialog.
        Window window = (Window)Executions.createComponents(
                "/AddSpecialization.zul", null, null);
        window.doModal();
    }
	
}