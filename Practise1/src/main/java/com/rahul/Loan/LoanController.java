package com.rahul.Loan;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.rahul.Login.LoginModel;
import com.rahul.Nominee.Nominee_Model;
import com.rahul.Sessions.AuthenticationService;
import com.rahul.Sessions.AuthenticationServiceImpl;
import com.rahul.golditem.Gold_item_Dao;
import com.rahul.golditem.Gold_item_Dao_impl;
import com.rahul.schemes.Scheme_Model;
import com.rahul.schemes.Scheme_details_Interface;

public class LoanController extends Window{
	private Scheme_details_Interface sdi;
	private Loaninterface lf;
	private Scheme_Model sc=new Scheme_Model();
	private ApplicationContext ctx ;
	private Nominee_Model nm=new Nominee_Model();
	private Loan_Model lm;
	private LoginModel slm;
	private Gold_item_Dao gd;
	private AuthenticationService as=new AuthenticationServiceImpl();
	private int custm_id,item_purity;
	String item_type,schm_name;
	double item_wt;
	private Logger logger=Logger.getLogger(LoanController.class);
	public void onCreate(){
		logger.info("entering");
		Listbox li=(Listbox)this.getFellow("scheme_name");
		ctx= WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		sdi= (Scheme_details_Interface)ctx.getBean("schemedb");
		lm=new Loan_Model();
		ListModelList l=new ListModelList(sdi.getallschemes());
		li.setModel(l);
		slm=new LoginModel();
		slm=as.getLoginCredential();
		Doublebox l1=(Doublebox)this.getFellow("LendingRate");
		if(!slm.getUser_role().equals("Manger")){
		l1.setReadonly(true);
		logger.info("leaving");
		
		}
	}
	public void scheme_details(String scheme_name){
		schm_name=scheme_name;
		sc=sdi.get_Scheme(scheme_name);
		Textbox tenure=(Textbox)this.getFellow("Tenure");
		tenure.setValue(sc.getScheme_loan_period()+" Days");
		Doublebox intrest=(Doublebox)this.getFellow("intrest");
		intrest.setValue(sc.getScheme_intrest());
	}
	public void cust_details(){
		try{
			Intbox custid=(Intbox)this.getFellow("Customerid");
		int id=custid.getValue();
		try{
		custm_id=id;
		lf=(Loaninterface)ctx.getBean("loandb");
		nm=lf.getNominee(id);
		}catch(Exception e2){
			Intbox it=(Intbox)this.getFellow("Customerid");
			Clients.showNotification("User Not Found","error",it,"end_center",2000);
		}
		Textbox cust_name=(Textbox)this.getFellow("CustomerName");
		cust_name.setValue(nm.getCustm_name());
		Textbox nominee_name=(Textbox)this.getFellow("NomineeName");
		nominee_name.setValue(nm.getNominee_name());
		Datebox nominee_dob=(Datebox)this.getFellow("NomineeDOB");
		nominee_dob.setValue(nm.getNominee_dob());
		Textbox nominee_rel=(Textbox)this.getFellow("nominee_relation");
		nominee_rel.setValue(nm.getNominee_relation());
		Intbox nominee_age=(Intbox)this.getFellow("nominee_age");
		nominee_age.setValue(nm.getNominee_age());
		}catch(Exception e){
			
		}
		
	}
	public void onselecttype(){
		Listbox li=(Listbox)this.getFellow("item_purity");
		li.setSelectedIndex(0);
		setValues(new Loan_Model());
	}
	
	public void loan_amounts(){
		try{
		Listbox purity_item=(Listbox)this.getFellow("item_purity");
		item_purity=Integer.valueOf((String) purity_item.getSelectedItem().getValue());
		Listbox li=(Listbox)this.getFellow("item_type");
		item_type=li.getSelectedItem().getValue();
		Doublebox wtdouble=(Doublebox)this.getFellow("WeightInGrams");
		item_wt=wtdouble.getValue();
		
		lm.setItem_purity(item_purity);
		lm.setItem_type(item_type);
		lm.setItem_wt(item_wt);
		lm=lf.getLoan_rate(lm);
		double total=lf.getTotalvalue(lm.getItem_market_rate(), lm.getItem_wt());
		lm.setItem_total_amount(total);
		gd=new Gold_item_Dao_impl();
		double avialrate=gd.get_lending_gold_rate(lm.getItem_total_amount());
		lm.setAvaliable_amount(avialrate);
		setValues(lm);
		}catch(Exception e){
			Listbox li=(Listbox)this.getFellow("item_type");
			Intbox custid=(Intbox)this.getFellow("Customerid");
			try{
			int id=custid.getValue();
			}catch(NullPointerException mp){
				Clients.showNotification("Please Fill The Field","error",custid,"end_center",2000);
				return;
			}
			Clients.showNotification("Please Select Type","error",li,"end_center",2000);
		}
	}
	public void setValues(Loan_Model ll){
		Doublebox m=(Doublebox)this.getFellow("gold_amount");
		m.setValue(ll.getItem_market_rate());
		Doublebox l=(Doublebox)this.getFellow("lending_amount");
		l.setValue(ll.getItem_lending_rate());
		Doublebox t=(Doublebox)this.getFellow("total_amount");
		t.setValue(ll.getItem_total_amount());
		Doublebox a=(Doublebox)this.getFellow("LendingRate");
		a.setValue(ll.getAvaliable_amount());
	}
	
	public void fileUpload(Media media){
		lf=(Loaninterface)ctx.getBean("loandb");
		 Textbox image=(Textbox)this.getFellow("gold_image");
         image.setValue(media.getName());

          byte[] b=media.getByteData();
          lm.setImage(b);
         
	}
	
	public void apply_loan(){
		logger.info("entering");
		try{
		Loan_Model apl=new Loan_Model();
		apl.setCustmer_id(custm_id);
		apl.setCustmer_name(nm.getCustm_name());
		apl.setNomine_id(nm.getNominee_id());
		apl.setNominee_name(nm.getNominee_name());
		apl.setItem_type(item_type);
		apl.setItem_purity(item_purity);
		Textbox tx=(Textbox)this.getFellow("ItemName");
		apl.setItem_name(tx.getValue());
		apl.setItem_wt(item_wt);
		apl.setItem_market_rate(lm.getItem_market_rate());
		apl.setItem_lending_rate(lm.getItem_lending_rate());
		apl.setItem_total_amount(lm.getItem_total_amount());
		apl.setScheme_name(schm_name);
		Listbox li=(Listbox)this.getFellow("selectType");
		String payment_bases=li.getSelectedItem().getValue();
		apl.setPayment_basis(payment_bases);
		apl.setTenure(sc.getScheme_loan_period());
		apl.setScheme_intrest(sc.getScheme_intrest());
		Datebox d=(Datebox)this.getFellow("loandate");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate2= formatter.format(d.getValue());  
		apl.setLoan_date(Date.valueOf(strDate2)); 
		Listbox lif=(Listbox)this.getFellow("Frequencyofpay");
		String fpay=lif.getSelectedItem().getValue();
		apl.setFrq_of_pay(fpay);
		Doublebox ra=(Doublebox)this.getFellow("RequestedAmount");
		apl.setReq_amount(ra.getValue());
		Doublebox lr=(Doublebox)this.getFellow("LendingRate");
		apl.setAvaliable_amount(lr.getValue());
		Listbox it=(Listbox)this.getFellow("intrest_type");
		String inttype=it.getSelectedItem().getValue();
		apl.setIntrest_type(inttype);
		Textbox txt=(Textbox)this.getFellow("ApprisalName");
		apl.setApprisal_name(txt.getValue());
		apl.setImage(lm.getImage());
		
			apl.setStatus("Pending");
			int n=lf.applyLoan(apl);
			if(n>0){
				Window w=(Window)this.getFellow("loan");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Messagebox.show("Loan Succesfully Appiled");
				Clients.evalJavaScript("fireEventFromClient()");
			}
		
			else{
				Window w=(Window)this.getFellow("loan");
				Clients.showNotification("Authentication Error..","error",w,"middle_center",2000);
			}
		}catch(NullPointerException e){
			//Messagebox.show("Please Fill The All Details","",1,"info");
			Window w=(Window)this.getFellow("loan");
			Clients.showNotification("Please Fill The Deatils","error",w, "middle_center",2000);
		}
		logger.info("leaving");
	}
	public void appl(){
		 Clients.evalJavaScript("fireEventFromClient()");
	}
}
