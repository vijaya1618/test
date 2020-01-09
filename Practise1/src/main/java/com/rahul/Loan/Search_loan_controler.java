package com.rahul.Loan;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

public class Search_loan_controler extends Window{
	private Loaninterface lf;
	
	public void onCreate()
	{
		Listbox lb = (Listbox)this.getFellow("loanlistbox");
		lb.getItems().clear();
		
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		lf=(Loaninterface)ctx.getBean("loandb");
		ListModelList ll=new ListModelList(lf.findAll());
		lb.setModel(ll);
		
		
}
	public void search(){
		int loan_id = 0;
		Date d = null;
		String loan_date = null;
		try{
		Intbox loan_box=(Intbox)this.getFellow("LoanID");
		 loan_id=loan_box.getValue();
		}catch(Exception e){
		}
		try{
		Datebox loan_dbox=(Datebox)this.getFellow("loan_date");
		d=loan_dbox.getValue();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		 loan_date= formatter.format(d); 
		}catch(Exception e){}
		 
		Listbox lb = (Listbox)this.getFellow("loanlistbox");
		ListModelList ll=new ListModelList(lf.getLoan_By_id(loan_id, loan_date));
		lb.getItems().clear();
		lb.setModel(ll);
	}
}
