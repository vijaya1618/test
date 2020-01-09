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
import com.pennantExamination.DAO.TestPapersDAO;
import com.pennantExamination.pojos.TestPapersModel;

public class TestPapersController extends Window{

	private static final long serialVersionUID = 1L;
	
	TestPapersDAO testPapersDao;
	
	@Wire
	private Window testPapersWin;
	
	private ListModel<TestPapersModel> testPaperslist;

	public ListModel<TestPapersModel> gettestPaperslist() {
		return testPaperslist;
	}
	
public void onCreate() {
		
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		testPapersDao = (TestPapersDAO) ctx.getBean("TestPapersDAO");
		Listbox li = (Listbox) this.getFellow("testPapersListbox");
		ListModelList<TestPapersModel> list = new ListModelList(testPapersDao.findAll());
		li.setModel(list);
	}

public void testPapersUpdate(String id) {
	Session ses = Sessions.getCurrent();
	ses.setAttribute("id4", id);
	Window window = (Window) Executions.createComponents("/editTestPapers.zul", null, null);
	window.doModal();
}

public void testPapersDelete(String id) throws Exception {
	//System.out.println(id);
	String s = CharMatcher.is('w').removeFrom(id);
	int a = Integer.parseInt(s);
	//System.out.println(a);
	testPapersDao.testpapersDelete(a);
	onCreate();
	Executions.sendRedirect("Admin.zul");
}

public void showModal() {
	// create a window programmatically and use it as a modal dialog.
	Window window = (Window) Executions.createComponents("/addTestPapers.zul", null, null);
	window.doModal();
}

}
