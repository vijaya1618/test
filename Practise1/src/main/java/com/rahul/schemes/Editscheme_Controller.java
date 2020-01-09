package com.rahul.schemes;



import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class Editscheme_Controller extends Window {
	Scheme_details_Interface sdi;
	public void onCreate()
	{
    Textbox scheme_name=(Textbox)this.getFellow("scheme_name");	
   	  Listbox schemeloantype=(Listbox)this.getFellow("scheme_loan_type");
   	  	
   	  Textbox product=(Textbox)this.getFellow("scheme_product");
   	 Listbox calcubasis=(Listbox)this.getFellow("scheme_calcBasics");
   	 
   	
   	  Doublebox scheme_minamt=(Doublebox)this.getFellow("scheme_minLoanAmount");
   	  Doublebox scheme_maxamt=(Doublebox)this.getFellow("scheme_maxLoanAmount");
   	 Listbox compound=(Listbox)this.getFellow("scheme_compound");
 	 Listbox payment_freq=(Listbox)this.getFellow("scheme_paymentFrequency");
 
   	  Datebox appfrom=(Datebox)this.getFellow("scheme_applicableFrom");
   	  Datebox appto=(Datebox)this.getFellow("scheme_applicableTo");
  	   Listbox pay_inadvance=(Listbox)this.getFellow("paymentInAdvance");
  	  Listbox scheme_days=(Listbox)this.getFellow("scheme_days");
   	 
    
         Intbox sloanperiod =(Intbox)this.getFellow("scheme_loanPeriod");
         Doublebox schemeinterest =(Doublebox)this.getFellow("scheme_interest");
    
         
         ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
         sdi=(Scheme_details_Interface)ctx.getBean("schemedb");
         Scheme_Model  schme_details=(Scheme_Model)(sdi.get_Scheme_by_id());
     	
         scheme_name.setValue(schme_details.getScheme_name());
         schemeloantype.setSelectedItem(schemeloantype.appendItem(schme_details.getScheme_loan_type(),schme_details.getScheme_loan_type()));
         calcubasis.setSelectedItem( calcubasis.appendItem(schme_details.getScheme_calc(),schme_details.getScheme_calc()));
         product.setValue(schme_details.getScheme_product());
         compound.setSelectedItem( compound.appendItem(schme_details.getScheme_compound(),schme_details.getScheme_compound()));
       
         scheme_minamt.setValue(schme_details.getScheme_min_ammount());
         scheme_maxamt.setValue(schme_details.getScheme_max_ammount());
         payment_freq.setSelectedItem(payment_freq.appendItem(schme_details.getScheme_payment_freq()+"",schme_details.getScheme_payment_freq()+""));
     
         appfrom.setValue(schme_details.getScheme_aplicable_from());
         appto.setValue(schme_details.getScheme_aplicable_to());
         pay_inadvance.setSelectedItem(pay_inadvance.appendItem(schme_details.getScheme_pay_advance(), schme_details.getScheme_pay_advance()));
      
         sloanperiod.setValue(schme_details.getScheme_loan_period());
         schemeinterest.setValue(schme_details.getScheme_intrest());
         scheme_days.setSelectedItem(scheme_days.appendItem(schme_details.getScheme_intrst_days()+"",schme_details.getScheme_intrst_days()+""));
         
	     }
	  public void onsave()throws Exception{
		  ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
	         sdi=(Scheme_details_Interface)ctx.getBean("schemedb");
	         Scheme_Model schme_details= new Scheme_Model();
	         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	         System.out.println("ijio");
	         Textbox st1=(Textbox)this.getFellow("scheme_name");
				schme_details.setScheme_name(st1.getValue());
				
				Textbox st2=(Textbox)this.getFellow("scheme_product");
				schme_details.setScheme_product(st2.getValue());
				
				Listbox st3=(Listbox)this.getFellow("scheme_loan_type");
				schme_details.setScheme_loan_type((String) st3.getSelectedItem().getValue());
				
				Listbox st4=(Listbox)this.getFellow("scheme_paymentFrequency");
				schme_details.setScheme_payment_freq(Integer.parseInt((String) st4.getSelectedItem().getLabel()));
				//schme_details.setScheme_payment_freq(st4.getSelectedItem().getLabel());
				
				Listbox st5=(Listbox)this.getFellow("paymentInAdvance");
				schme_details.setScheme_pay_advance((String) st5.getSelectedItem().getLabel());
				
				Listbox st6=(Listbox)this.getFellow("scheme_days");
				
				schme_details.setScheme_intrst_days(Integer.parseInt(st6.getSelectedItem().getLabel()));
				
				Listbox st7=(Listbox)this.getFellow("scheme_calcBasics");
				schme_details.setScheme_calc((String) st7.getSelectedItem().getLabel());
				
				Listbox st8=(Listbox)this.getFellow("scheme_compound");
				schme_details.setScheme_compound((String) st8.getSelectedItem().getValue());
				
				Doublebox st9=(Doublebox)this.getFellow("scheme_interest");
				schme_details.setScheme_intrest(st9.getValue());
				
				Doublebox st10=(Doublebox)this.getFellow("scheme_minLoanAmount");
				schme_details.setScheme_min_ammount(st10.getValue());
				
				Doublebox st11=(Doublebox)this.getFellow("scheme_maxLoanAmount");
				schme_details.setScheme_max_ammount(st11.getValue());
				
				Intbox st12=(Intbox)this.getFellow("scheme_loanPeriod");
				schme_details.setScheme_loan_period(st12.getValue());
				
				Datebox st13=(Datebox)this.getFellow("scheme_applicableFrom");
			    String strDate= formatter.format(st13.getValue());  
			    
			    schme_details.setScheme_aplicable_from(Date.valueOf(strDate));
			    Datebox st14=(Datebox)this.getFellow("scheme_applicableTo");
			    
			    String strDate2= formatter.format(st14.getValue());  
			    schme_details.setScheme_aplicable_to(Date.valueOf(strDate2));
	            sdi.edit_scheme(schme_details);
	            Messagebox.show("Succesfull..");
	     
	 }
	  public void test(){
		  Clients.evalJavaScript("fireEventFromClient()");
	  }
	}

