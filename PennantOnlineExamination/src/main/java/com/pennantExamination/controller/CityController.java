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
import com.pennantExamination.DAO.CityDAO;
import com.pennantExamination.pojos.CityModel;
public class CityController extends Window{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

CityDAO cityDAO;
 
 @Wire
 private Window win;
 
 
 private ListModel<CityModel> city;
	public ListModel<CityModel> getCity() {
	return city;
}
	public void onCreate()
	{
		//List<CityModel> result;
		//Listbox lb=(Listbox)this.getFellow("cityListBox");
		//lb.getItems().clear();
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = 
				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		cityDAO = (CityDAO)ctx.getBean("citiDAO");
			Listbox li=(Listbox)this.getFellow("cityListBox");
			ListModelList<CityModel> list=new ListModelList<CityModel>(cityDAO.findAll());
			li.setModel(list);
			///city=new ListModelList<CityModel>(cityDAO.findAll());
			//((ListModelList<CityModel>) city).setMultiple(true);
	}
	
	public void cityUpdate(String id){
		Session ses=Sessions.getCurrent();
		ses.setAttribute("cityId",id);
		 Window window = (Window)Executions.createComponents(
	                "/EditCity.zul", null, null);
	        window.doModal();
		
		
	}
	
	public void delete(String id) throws Exception {
		System.out.println(id);
		String s = CharMatcher.is('w').removeFrom(id);
		int a = Integer.parseInt(s);
		System.out.println(a);
		cityDAO.citydelete(a);
		onCreate();
		Executions.sendRedirect("Admin.zul");
	}
	public void showModal() {
        //create a window programmatically and use it as a modal dialog.
        Window window = (Window)Executions.createComponents(
                "/AddCity.zul", null, null);
        window.doModal();
    }
	
}

