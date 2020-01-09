package com.pennantExamination.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.google.common.base.CharMatcher;
import com.pennantExamination.DAO.ExamineeDAO;
import com.pennantExamination.pojos.ExamineeModel;

public class ExamineeController extends Window {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ExamineeDAO examineeDAO;

	@Wire
	private Window win;

	private ListModel<ExamineeModel> examinee;

	public ListModel<ExamineeModel> getExaminee() {
		return examinee;
	}

	public void onCreate() {
		// List<ExamineeModel> result;
		// Listbox lb=(Listbox)this.getFellow("examineeListBox");
		// lb.getItems().clear();
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		examineeDAO = (ExamineeDAO) ctx.getBean("examDAO");
		Listbox li = (Listbox) this.getFellow("examineeListBox");
		ListModelList<ExamineeModel> list = new ListModelList(examineeDAO.findAll());
		li.setModel(list);
		/// examinee=new ListModelList<ExamineeModel>(examineeDAO.findAll());
		// ((ListModelList<ExamineeModel>) examinee).setMultiple(true);
	}

	public void examineeUpdate(String id) {
		Session ses = Sessions.getCurrent();
		ses.setAttribute("id1", id);
		Window window = (Window) Executions.createComponents("/editExaminee.zul", null, null);
		window.doModal();

	}

	public void delete(String id) throws Exception {
		System.out.println(id);
		String s = CharMatcher.is('w').removeFrom(id);
		int a = Integer.parseInt(s);
		//System.out.println(a);
		examineeDAO.examineedelete(a);
		onCreate();
		//Executions.sendRedirect("Admin.zul");
	}

	public void showModal() {
		// create a window programmatically and use it as a modal dialog.
		Window window = (Window) Executions.createComponents("/addExaminee.zul", null, null);
		window.doModal();
	}

}
