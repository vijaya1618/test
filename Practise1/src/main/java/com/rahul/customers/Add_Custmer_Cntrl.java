package com.rahul.customers;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class Add_Custmer_Cntrl extends Window{
	
	private static final long serialVersionUID = 1L;
	AccountService_Interface accountsservice;
	boolean b;
	ApplicationContext ctx;

	public void customerinsert() throws Exception
	{
		 ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
			
			accountsservice = (AccountService_Interface)ctx.getBean("accountdb");	
		Custmer_details task=new Custmer_details();
		
		try{
		Textbox custname=(Textbox) this.getFellow("CustomerName");
		String Customername=custname.getValue();
		
		Longbox accno=(Longbox)this.getFellow("AccountNo");
		long accountno=accno.getValue();
		
		Longbox addrno=(Longbox)this.getFellow("AadharNo");
		long aadhartno=addrno.getValue();
		
		Textbox cat=(Textbox) this.getFellow("Category");
		String Category=cat.getValue();
		
		Radiogroup gen=(Radiogroup)this.getFellow("g1");	
		String gender=gen.getSelectedItem().getLabel();
		
		Textbox prsntaddrs=(Textbox) this.getFellow("PresentAddress");
		String presentaddress=prsntaddrs.getValue();
		
		Textbox prmntaddr=(Textbox) this.getFellow("PermanentAddress");
		String permanentaddress=prmntaddr.getValue();
		
		Label l=(Label)this.getFellow("nation");	
		String Nationality=l.getValue();
		
		Label ll=(Label)this.getFellow("mst");	
		String martialstatus=l.getValue();
		
		
		
		Datebox dob=(Datebox) this.getFellow("DateofBirth");
		java.util.Date dateofbirth=(java.util.Date) dob.getValue();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate2= formatter.format(dateofbirth);  
		
		Longbox mobnum=(Longbox)this.getFellow("MobileNumber");
		long mobileno=mobnum.getValue();
		
		Textbox mail=(Textbox) this.getFellow("Email");
		String email=mail.getValue();
		
		
		
	
	
		task.setCustmr_name(Customername);
		task.setCustmr_accuntno(accountno);
		task.setCustmr_adharno(aadhartno);
		task.setCustmr_categry(Category);
		task.setCustmr_gender(gender);
		task.setCustmr_present_addrs(presentaddress);
		task.setCustmr_perment_addrs(permanentaddress);
		task.setCustmr_nation(Nationality);
		task.setCustmr_martial(martialstatus);
		task.setCustmr_dob(Date.valueOf(strDate2));
		task.setCustmr_phno(mobileno);
		task.setCustmr_email(email);
		
	
		if(b==false) {
		int n=accountsservice.CustomerInsert(task);
		if(n>0){
			System.out.println("custmer"+n);
			Session s=Sessions.getCurrent(true);
			s.setAttribute("custId",n);
			Clients.evalJavaScript("fireEventFromClient()");
			
		}else{
			Div it=(Div)this.getFellow("customer");
			Clients.showNotification("Please Try Again","error",it,"middle_center",2000);
		}
		}else {
			Label y=(Label)this.getFellow("x");
			y.setValue("Already loan is exists with this account");
			l.focus();
		}
		
		}catch(NullPointerException e){
			Messagebox.show("Please Fill the All Details","",2,"ERROR");
		}
		
	}
	public void customerValidate(){
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		
		accountsservice = (AccountService_Interface)ctx.getBean("accountdb");	
		Longbox accno=(Longbox)this.getFellow("AccountNo");
		long accountno=accno.getValue();
		b=accountsservice.validatecaccountno(accountno); 
		System.out.println(b);
		Label y=(Label)this.getFellow("x");
		if(b) {
			y.setValue("Already loan is exists with this account");
			
		}
		else
		{
			y.setValue("");
		
			
		}
		
	}
}
