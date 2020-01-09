package com.rahul.balance;
import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.Button;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import com.rahul.balance.BalanceInterface;



public class BalanceController extends Window{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

BalanceInterface balance;
	
ApplicationContext ctx;
	
	public void onCreate(){
		 ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		
		balance = (BalanceInterface)ctx.getBean("balancedb");
		
		List<BalanceDetails> result = balance.findAll();
		
		Listbox lb = (Listbox)this.getFellow("balancelist");
		lb.getItems().clear();
		ListModelList<BalanceDetails> l=new ListModelList<BalanceDetails>(result);
		 lb.setModel(l);
		
		
	}
	
	public void findcustomer(){
		Intbox loan_box=(Intbox)this.getFellow("loan_id");
		try{
		int loan_id=loan_box.getValue();
		if(loan_id!=0){
		List<BalanceDetails> bal=balance.search(loan_id);
		Iterator<BalanceDetails> iterator = bal.iterator();
		 while (iterator.hasNext()) {
			 BalanceDetails at=iterator.next();
			
		 }
		 Listbox lb = (Listbox)this.getFellow("balancelist");
		 lb.getItems().clear();
		 ListModelList<BalanceDetails> l=new ListModelList<BalanceDetails>(bal);
		 lb.setModel(l);
	}
		else{
			
			onCreate();
		}
		}catch(Exception e){
			onCreate();
		}
	}
}

