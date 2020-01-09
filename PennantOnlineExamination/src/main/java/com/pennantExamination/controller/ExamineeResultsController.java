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
import com.pennantExamination.DAO.ExamineeResultsDAO;
import com.pennantExamination.pojos.ExamineeResultsModel;

public class ExamineeResultsController extends Window{

	private static final long serialVersionUID = 1L;
	
	ExamineeResultsDAO examineeResultsDAO;
	
	@Wire
	private Window resultswin;
	
	private ListModel<ExamineeResultsModel> examineeResults;

	public ListModel<ExamineeResultsModel> getExaminee() {
		return examineeResults;
	}
	
	public void onCreate() {
		
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		examineeResultsDAO = (ExamineeResultsDAO) ctx.getBean("examResultsDAO");
		Listbox li = (Listbox) this.getFellow("examineeResultslistbox");
		ListModelList<ExamineeResultsModel> list = new ListModelList(examineeResultsDAO.findAll());
		li.setModel(list);
	}

	public void examineeResultsUpdate(String id) {
		Session ses = Sessions.getCurrent();
		ses.setAttribute("id2", id);
		Window window = (Window) Executions.createComponents("/editExamineeResults.zul", null, null);
		window.doModal();

	}
	
	
	public void examineeResultsdelete(String id) throws Exception {
		//System.out.println(id);
		String s = CharMatcher.is('w').removeFrom(id);
		int a = Integer.parseInt(s);
		System.out.println(a);
		examineeResultsDAO.examineedelete(a);
		onCreate();
		Executions.sendRedirect("examineeResults.zul");
	}

	public void showModal() {
		// create a window programmatically and use it as a modal dialog.
		Window window = (Window) Executions.createComponents("/addExamineeResults.zul", null, null);
		window.doModal();
	}
	
}
