package com.rahul.LoanSuccess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.swing.Timer;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.rahul.Loan.LoanController;
import com.rahul.Loan.Loan_Model;
import com.rahul.Loan.Loaninterface;
import com.rahul.Login.LoginModel;
import com.rahul.Nominee.Nominee_Model;
import com.rahul.Sessions.AuthenticationService;
import com.rahul.Sessions.AuthenticationServiceImpl;
import com.rahul.golditem.Gold_item_Dao;
import com.rahul.golditem.Gold_item_Dao_impl;
import com.rahul.schemes.Scheme_Model;
import com.rahul.schemes.Scheme_details_Interface;

public class LoanSucessCntrl extends Window{
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
	
	private Logger logger=Logger.getLogger(LoanSucessCntrl.class);
	public void onCreate(){
		logger.info("entering");
		slm=as.getLoginCredential();
			Label label=(Label)this.getFellow("label");
			if(slm.getUser_role().equals("Admin")){
				label.setValue("Edit Loan");
			}else if(slm.getUser_role().equals("Manger")){
				label.setValue("Aprrove Loan");
			}
			
					ctx= WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
					sdi= (Scheme_details_Interface)ctx.getBean("schemedb");
					//ListModelList l=new ListModelList(sdi.getallschemes());
					List<Scheme_Model> li=sdi.getallschemes();
					Listbox scheme_name=(Listbox)this.getFellow("scheme_name");
					scheme_name.getItems().clear();
					for(Iterator it=li.iterator();it.hasNext();){
					//scheme_name.setModel(l);
						sc=(Scheme_Model)it.next();
						Listitem lt=new Listitem();
						lt.appendChild(new Listcell(sc.getScheme_name()));
						scheme_name.appendChild(lt);
					}
					
					
					if(slm.getUser_role().equals("Admin")){
						Doublebox lending_rate=(Doublebox)this.getFellow("LendingRate");
						lending_rate.setReadonly(true);
					}
		
		
		ctx= WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		lf=(Loaninterface)ctx.getBean("loandb");
		lm=lf.getLoandetails();
		nm=lf.getNominee(lm.getCustmer_id());
		Intbox custm_id=(Intbox)this.getFellow("Customerid");
				custm_id.setValue(lm.getCustmer_id());
		Textbox cust_name=(Textbox)this.getFellow("CustomerName");
				cust_name.setValue(lm.getCustmer_name());
		Textbox nominee_name=(Textbox)this.getFellow("NomineeName");
				nominee_name.setValue(nm.getNominee_name());
		Datebox nominee_dob=(Datebox)this.getFellow("NomineeDOB");
				nominee_dob.setValue(nm.getNominee_dob());
		Textbox nominee_rel=(Textbox)this.getFellow("nominee_relation");
				nominee_rel.setValue(nm.getNominee_relation());
		Intbox nominee_age=(Intbox)this.getFellow("nominee_age");
				nominee_age.setValue(nm.getNominee_age());
		Listbox li2=(Listbox)this.getFellow("item_type");
				li2.setSelectedItem(li2.appendItem(lm.getItem_type(), lm.getItem_type()));
		Textbox tx=(Textbox)this.getFellow("ItemName");
			tx.setValue(lm.getItem_name());
		Doublebox wtdouble=(Doublebox)this.getFellow("WeightInGrams");
				wtdouble.setValue(lm.getItem_wt());
		Listbox item_purity=(Listbox)this.getFellow("item_purity");
				item_purity.setSelectedItem(item_purity.appendItem(lm.getItem_purity()+"k",String.valueOf(lm.getItem_purity())));
		Doublebox request_amount=(Doublebox)this.getFellow("RequestedAmount");
				  request_amount.setValue(lm.getReq_amount());
		Doublebox loan_gold_amount=(Doublebox)this.getFellow("gold_amount");
				loan_gold_amount.setValue(lm.getItem_market_rate());
		Doublebox lending_amount=(Doublebox)this.getFellow("lending_amount");
				lending_amount.setValue(lm.getItem_lending_rate());
		Doublebox total_amount=(Doublebox)this.getFellow("total_amount");
				total_amount.setValue(lm.getItem_total_amount());
		Doublebox lending_rate2=(Doublebox)this.getFellow("LendingRate");
				lending_rate2.setValue(lm.getAvaliable_amount());
		Datebox d=(Datebox)this.getFellow("loandate");
				d.setValue(lm.getLoan_date());
		Doublebox intrest=(Doublebox)this.getFellow("intrest");
				intrest.setValue(lm.getScheme_intrest());
		Textbox tenure=(Textbox)this.getFellow("Tenure");
				tenure.setValue(lm.getTenure()+"Days");
		Listbox scheme_name2=(Listbox)this.getFellow("scheme_name");
				scheme_name2.setSelectedItem(scheme_name2.appendItem(lm.getScheme_name(), lm.getScheme_name()));
				
		Listbox payment_basis=(Listbox)this.getFellow("selectType");
				payment_basis.setSelectedItem(payment_basis.appendItem(lm.getPayment_basis(),lm.getPayment_basis()));
				
		Listbox freq_of_pay=(Listbox)this.getFellow("Frequencyofpay");
				freq_of_pay.setSelectedItem(freq_of_pay.appendItem(lm.getFrq_of_pay(),lm.getFrq_of_pay()));
				
		Listbox intrest_type=(Listbox)this.getFellow("intrest_type");
				intrest_type.setSelectedItem(intrest_type.appendItem(lm.getIntrest_type(),lm.getIntrest_type()));
					
		Textbox appraisal_name=(Textbox)this.getFellow("ApprisalName");
				appraisal_name.setValue(lm.getApprisal_name());
				
				if(slm.getUser_role().equals("Admin") && lm.getStatus().equals("Approved")){
					Button side=(Button)this.getFellow("side_button");
					side.setLabel("Back");
				}else if(slm.getUser_role().equals("Manger")){
					Button action=(Button)this.getFellow("action_button");
					action.setLabel("Approve");
					Button side=(Button)this.getFellow("side_button");
					side.setLabel("Re Submmit");
				}
				if(slm.getUser_role().equals("Manger")&&lm.getStatus().equals("Approved")){
					Button action=(Button)this.getFellow("action_button");
					action.setDisabled(true);
				}
						
		
	}
	
	public void onselecttype(){
		Listbox li=(Listbox)this.getFellow("item_purity");
		li.setSelectedIndex(0);
		setValues(new Loan_Model());
	}
	
	public void loan_amounts(String purity){
		item_purity=Integer.valueOf(purity);
		Listbox li=(Listbox)this.getFellow("item_type");
		item_type=li.getSelectedItem().getValue();
		Doublebox wtdouble=(Doublebox)this.getFellow("WeightInGrams");
		item_wt=wtdouble.getValue();
	
		lm.setItem_purity(item_purity);
		lm.setItem_type(item_type);
		lm.setItem_wt(item_wt);
		Loan_Model loan=new Loan_Model();
		loan=lf.getLoan_rate(lm);
		lm.setItem_purity(loan.getItem_purity());
		lm.setItem_type(loan.getItem_type());
		lm.setItem_wt(loan.getItem_wt());
		double total=lf.getTotalvalue(lm.getItem_market_rate(), lm.getItem_wt());
		lm.setItem_total_amount(total);
		gd=new Gold_item_Dao_impl();
		double avialrate=gd.get_lending_gold_rate(lm.getItem_total_amount());
		lm.setAvaliable_amount(avialrate);
		setValues(lm);
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
	public void scheme_details(String scheme_name){
		schm_name=scheme_name;
		sc=sdi.get_Scheme(scheme_name);
		Textbox tenure=(Textbox)this.getFellow("Tenure");
		tenure.setValue(sc.getScheme_loan_period()+" Days");
		Doublebox intrest=(Doublebox)this.getFellow("intrest");
		intrest.setValue(sc.getScheme_intrest());
	}
	public void fileUpload(Media media){
		
		 Textbox image=(Textbox)this.getFellow("gold_image");
         image.setValue(media.getName());

          byte[] b=media.getByteData();
          lm.setImage(b);
         
	}
	public void submit(String label){
		Textbox apraisal_name=(Textbox)this.getFellow("ApprisalName");
		lm.setApprisal_name(apraisal_name.getValue());
		Textbox item_name=(Textbox)this.getFellow("ItemName");
		lm.setItem_name(item_name.getValue());
		Doublebox request_amount=(Doublebox)this.getFellow("RequestedAmount");
		lm.setReq_amount(request_amount.getValue());
		Doublebox weight_in_grams=(Doublebox)this.getFellow("WeightInGrams");
		lm.setItem_wt(weight_in_grams.getValue());
		Doublebox lending_amount=(Doublebox)this.getFellow("LendingRate");
		lm.setAvaliable_amount(lending_amount.getValue());
		Datebox loan_date=(Datebox)this.getFellow("loandate");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate2= formatter.format(loan_date.getValue());
		lm.setLoan_date(Date.valueOf(strDate2));
		Listbox scheme_name=(Listbox)this.getFellow("scheme_name");
		lm.setScheme_name((String) scheme_name.getSelectedItem().getValue());
		Listbox select_type=(Listbox)this.getFellow("selectType");
		lm.setPayment_basis((String)select_type.getSelectedItem().getValue());
		Listbox freq_of_pay=(Listbox)this.getFellow("Frequencyofpay");
		lm.setFrq_of_pay((String)freq_of_pay.getSelectedItem().getValue());
		Listbox intrest_type=(Listbox)this.getFellow("intrest_type");
		lm.setIntrest_type((String)intrest_type.getSelectedItem().getValue());
		Listbox item_type=(Listbox)this.getFellow("item_type");
		lm.setItem_type((String) item_type.getSelectedItem().getValue());
		System.out.println(label);
		System.out.println(lm.getStatus());
		Window w=(Window)this.getFellow("loan");
		if((label.equals("Re Submit")||label.equals("Save")) && slm.getUser_role().equals("Admin") && (lm.getStatus().equals("Pending")||lm.getStatus().equals("Re Submited"))){
						lm.setStatus("Pending");
						int n=lf.saveLoan(lm);
							if(n>0){
									if(label.equals("Re Submit")){
										Messagebox.show("Loan Re Submited");
									}else{
									Messagebox.show("Loan Saved");
									}
									redirect(1);
							}else{
								Clients.showNotification("Please Try again or Check Details..","error",w,"middle_center",2000);
							}
		}else if(label.equals("Re Submit")&& slm.getUser_role().equals("Manger") && lm.getStatus().equals("Pending")){
					lm.setStatus("Re Submited");
					int n=lf.saveLoan(lm);
					if(n>0){
						Messagebox.show("Loan Re Submited");
						redirect(2);
					}else{
						Clients.showNotification("Please Try again or Check Details..","error",w,"middle_center",2000);
					}
		}else if(label.equals("Approve")){
					lm.setStatus("Approved");
					int n=lf.loanapprove(lm);
					if(n>0){
						Messagebox.show("Loan Succesfully Approved");
						redirect(1);
					}
						else{
							Clients.showNotification("Please Try again or Check Details..","error",w,"middle_center",2000);
						}
		}else if(label.equals("Re Submit") && lm.getStatus().equals("Approved")){
				lm.setStatus("Re Submited");
					int n1=lf.resubmitLoan(lm);
					Messagebox.show("Loan Re Submited");
					redirect(1);
		}else if(label.equals("Back")){
			redirect(1);
		}
		}
	public void redirect(int n){
		if(n==1){
			 Clients.evalJavaScript("fireEventFromClient()");
		}if(n==2){
			Clients.evalJavaScript("pendingloan()");
		}
	}
	}