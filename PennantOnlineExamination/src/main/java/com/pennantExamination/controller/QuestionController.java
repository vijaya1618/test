package com.pennantExamination.controller;

import java.util.List;

import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.OnlineExamDAO;
import com.pennantExamination.pojos.QuestionModel;
import com.pennantExamination.pojos.Users;

public class QuestionController extends Window
{

	private static final long serialVersionUID = 1L;
	
	protected OnlineExamDAO regdao;
	
	@Wire
	private Window Win;
	private ListModel<QuestionModel> questions;
	private int count=0;
	

	public void onCreate()
	{
		
		Clients.evalJavaScript("countdown()");
	ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
	regdao=(OnlineExamDAO)ctx.getBean("onlineExamDAO");
	Listbox li=(Listbox)this.getFellow("questionPaperList");
    ListModelList<QuestionModel> list= new ListModelList(regdao.getQuestions());
	li.setModel(list);
}

	     public void submit(String id) {
		  ApplicationContext ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
			regdao = (OnlineExamDAO) ctx.getBean("onlineExamDAO");
		  	Radiogroup rg=(Radiogroup)this.getFellow(id);
		  	String ans=rg.getSelectedItem().getValue();
		  	String res=regdao.getAnswerById(id);
		  	if(ans.equals(res)) {
		  		count++;
		  	}
		  	System.out.println(count);
		  	Session sess=Sessions.getCurrent();
			Users user = (Users) sess.getAttribute("user");
			
		  
	  }
		  
		  
		  public void redirect()
		  {
			  Executions.sendRedirect("reg.zul");
		  }
		  public void final_submit(){		  
			  ApplicationContext ctx = 
						WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
				regdao=(OnlineExamDAO)ctx.getBean("onlineExamDAO");
			  regdao.Totalscore(count);
			  if(Messagebox.show("Thanks for your participation")==1){
					redirect();
				    }
			  
		  }
	}
