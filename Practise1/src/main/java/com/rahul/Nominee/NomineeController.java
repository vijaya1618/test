package com.rahul.Nominee;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.sql.Date;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.rahul.balance.BalanceDetails;
import com.rahul.balance.BalanceInterface;
import com.rahul.customers.Custmer_details;


public class NomineeController  extends Window{
		
		
		private static final long serialVersionUID = 1L;
		public NomineeDAO db;
		ApplicationContext ctx;
		public void onCreate(){
			try{
			 ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
			
			db = (NomineeDAO)ctx.getBean("nomineedb");
			
			List<Nominee_Model> result = db.findAll();
			try{
			Listbox lb = (Listbox)this.getFellow("nomineedetails");
			lb.getItems().clear();
			ListModelList<Nominee_Model> l=new ListModelList<Nominee_Model>(result);
			 lb.setModel(l);
			}catch(Exception e){
				System.out.println("list not availble");
			}
			 int n=0;
				 Session s=Sessions.getCurrent(true);
				 System.out.println(n);
				 n=(Integer) s.getAttribute("custId");
				 s.setAttribute("custId",null);
			 				if(n!=0){
			 					Label l=(Label)this.getFellow("info");
			 					l.setValue("Please Add The Nominee For The Customer..");
			 						Intbox custid=(Intbox)this.getFellow("customerId");
			 							custid.setValue(n);
			 							custid.setReadonly(true);
			 }
			}catch(Exception e){
				System.out.println("try not worked");
			}
			
		}
		
		public void NomineeInsert() throws Exception
		{
			ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
			
			db=(NomineeDAO)ctx.getBean("nomineedb");	
			Nominee_Model task=new Nominee_Model();
		
			
			Intbox cid=(Intbox)this.getFellow("customerId");
			int cusid=cid.getValue();
			
			Textbox nomname=(Textbox) this.getFellow("nomineeName");
			String Nomineename=nomname.getValue();
			
			Textbox nomrel=(Textbox) this.getFellow("nomineerelation");
			String nomineerel=nomrel.getValue();
			
			Intbox age=(Intbox)this.getFellow("nomineeAge");
			int nage=age.getValue();
			
			
			Datebox nomdob=(Datebox) this.getFellow("nomineeDob");
			java.util.Date nominedob=nomdob.getValue();

			task.setCustm_id(cusid);
			task.setNominee_name(Nomineename);
			task.setNominee_relation(nomineerel);
			task.setNominee_age(nage);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
			String strDate2= formatter.format(nominedob);  
			task.setNominee_dob(Date.valueOf(strDate2));
		
		
			int n=db.NomineeInsert(task);
			if(n>0){
				
				Messagebox.show("Succesfully Added");
				Clients.evalJavaScript("fireEventFromClient()");
			}else{
				Div it=(Div)this.getFellow("nominee");
				Clients.showNotification("Please Try Again","error",it,"middle_center",2000);
			}
	
		}
		public void findcustomer(int nominee_id){
			System.out.println(nominee_id+"nominee_id");
			if(nominee_id!=0){
			List<Nominee_Model> nom=db.search(nominee_id);
			Iterator<Nominee_Model> iterator = nom.iterator();
			 while (iterator.hasNext()) {
				 Nominee_Model at=iterator.next();
				// System.out.println(at.getCustmr_name());
				 
			 }
			 Listbox lb = (Listbox)this.getFellow("nomineedetails");
			 lb.getItems().clear();
			 ListModelList<Nominee_Model> l=new ListModelList<Nominee_Model>(nom);
			 lb.setModel(l);
		}
			else{
				
				onCreate();
			}
		}
}
