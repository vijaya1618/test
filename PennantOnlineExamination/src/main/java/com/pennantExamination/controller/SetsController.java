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
import com.pennantExamination.DAO.SetsDAO;
import com.pennantExamination.pojos.SetsModel;

public class SetsController extends Window{

	private static final long serialVersionUID = 1L;
	
	SetsDAO setsDao;
	
	@Wire
	private Window setswin;
	
	private ListModel<SetsModel> sets;

	public ListModel<SetsModel> getSets() {
		return sets;
	}
	
	public void onCreate() {
		
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		setsDao = (SetsDAO) ctx.getBean("setsDAO");
		Listbox li = (Listbox) this.getFellow("setslistbox");
		ListModelList<SetsModel> list = new ListModelList(setsDao.findAll());
		li.setModel(list);
	}
	
	public void setsUpdate(String id) {
		Session ses = Sessions.getCurrent();
		ses.setAttribute("id3", id);
		Window window = (Window) Executions.createComponents("/editSets.zul", null, null);
		window.doModal();

	}
	
	public void setsdelete(String id) throws Exception {
		//System.out.println(id);
		String s = CharMatcher.is('w').removeFrom(id);
		int a = Integer.parseInt(s);
		//System.out.println(a);
		setsDao.setsdelete(a);
		onCreate();
		Executions.sendRedirect("Admin.zul");
	}
	
	public void showModal() {
		// create a window programmatically and use it as a modal dialog.
		Window window = (Window) Executions.createComponents("/addSets.zul", null, null);
		window.doModal();
	}
	
	
	
}
