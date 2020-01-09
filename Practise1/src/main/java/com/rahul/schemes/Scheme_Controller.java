package com.rahul.schemes;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkmax.zul.Tablelayout;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.rahul.golditem.Gold_item_Dao;

public class Scheme_Controller extends Window{
	Logger logger=Logger.getLogger(Scheme_Controller.class);
	Scheme_details_Interface sdi;
	Scheme_Model schme_details=new Scheme_Model();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	public void add_scheme(){
			logger.info("entering");
			ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
			sdi= (Scheme_details_Interface)ctx.getBean("schemedb");
			try{
			Textbox st1=(Textbox)this.getFellow("scheme_name");
			schme_details.setScheme_name(st1.getValue());
			Textbox st2=(Textbox)this.getFellow("scheme_product");
			schme_details.setScheme_product(st2.getValue());
			Listbox st3=(Listbox)this.getFellow("scheme_loan_type");
			schme_details.setScheme_loan_type((String) st3.getSelectedItem().getValue());
			Listbox st4=(Listbox)this.getFellow("scheme_paymentFrequency");
			schme_details.setScheme_payment_freq(Integer.parseInt((String) st4.getSelectedItem().getValue()));
			Listbox st5=(Listbox)this.getFellow("paymentInAdvance");
			schme_details.setScheme_pay_advance((String) st5.getSelectedItem().getValue());
			Listbox st6=(Listbox)this.getFellow("scheme_days");
			schme_details.setScheme_intrst_days(Integer.parseInt((String) st6.getSelectedItem().getValue()));
			Listbox st7=(Listbox)this.getFellow("scheme_calcBasics");
			schme_details.setScheme_calc((String) st7.getSelectedItem().getValue());
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
		    
			int n=sdi.add_scheme(schme_details);
			if(n>0){
				Messagebox.show("Scheme Added");
				Clients.evalJavaScript("fireEventFromClient()");
			}
			else{
				Tablelayout tab=(Tablelayout)this.getFellow("scheme_table");
				Clients.showNotification("Please Try again or Check Details..","error",tab,"middle_center",2000);
			}
			}catch(NullPointerException e){
				Tablelayout tab=(Tablelayout)this.getFellow("scheme_table");
				Clients.showNotification("Please Fill The All Details","error",tab,"middle_center",2000);
			}
	}
	
	

}
