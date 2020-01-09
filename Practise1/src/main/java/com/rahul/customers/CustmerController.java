package com.rahul.customers;

import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.rahul.customers.AccountService_Interface;



public class CustmerController extends Window{
AccountService_Interface accountsservice;
	
ApplicationContext ctx;
	
	public void onCreate(){
		 ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		
		accountsservice = (AccountService_Interface)ctx.getBean("accountdb");
		
		List<Custmer_details> result = accountsservice.findAll();
		
		Listbox lb = (Listbox)this.getFellow("accountlist");
		lb.getItems().clear();
		ListModelList<Custmer_details> l=new ListModelList<Custmer_details>(result);
		 lb.setModel(l);
		
		
	}
	public void findcustomer(){
		Textbox tx=(Textbox)this.getFellow("Accountbox");
		String account_no=tx.getValue();
		System.out.println(account_no+"account no");
		if(!account_no.equals("")){
		List<Custmer_details> cust=accountsservice.search(account_no);
		Iterator<Custmer_details> iterator = cust.iterator();
		 while (iterator.hasNext()) {
			 Custmer_details at=iterator.next();
			// System.out.println(at.getCustmr_name());
			 
		 }
		 Listbox lb = (Listbox)this.getFellow("accountlist");
		 lb.getItems().clear();
		 ListModelList<Custmer_details> l=new ListModelList<Custmer_details>(cust);
		 lb.setModel(l);
	}
		else{
			
			onCreate();
		}
	
	}
}

