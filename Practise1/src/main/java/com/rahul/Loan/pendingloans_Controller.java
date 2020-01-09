package com.rahul.Loan;

import java.util.List;

import javax.servlet.ServletContext;


import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

public class pendingloans_Controller extends Window {
	private Loaninterface lf;
ApplicationContext ctx;

	public void onCreate()
		{
		
		try{
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		 lf=(Loaninterface)ctx.getBean("loandb");
		List<Loan_Model> result;
		Listbox lb = (Listbox)this.getFellow("loanlist");
		lb.getItems().clear();
		
		
		
		ListModelList<Loan_Model> lst = new ListModelList<Loan_Model>(lf.pendingAll());
		lb.setModel(lst);
		
		}catch(Exception e){
			
		}
	  
		}
	
	public void search(){
		int id=0;
		
		
		 
		 try {
		
				 Intbox i1=(Intbox)this.getFellow("loanbox");
				   id=i1.getValue();
			
		
		List<Loan_Model> result = lf.psearch(id);
		
		Listbox lb = (Listbox)this.getFellow("loanlist");
		lb.getItems().clear();
		ListModelList<Loan_Model> l=new ListModelList<Loan_Model>(result);
		 lb.setModel(l);
			}
		 catch(NullPointerException ne) {
					
				}

		}
	
	
	
}
