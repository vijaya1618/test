package com.rahul.Login;

import java.util.LinkedHashMap;
import java.util.Map;
import com.rahul.Sessions.*;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;

public class PageController{
	 NavigationPage currentPage;
	 AuthenticationService ac=new AuthenticationServiceImpl();
	    private Map<String, Map<String, NavigationPage>> pageMap;
	  
	    
	   private LoginModel l=new LoginModel();
	    
	    public LoginModel getL() {
		return l;
	}

	public void setL(LoginModel l) {
		this.l = l;
	}

		@Init
	    public void init() {
			l=ac.getLoginCredential();
	        initPageMap();
	        currentPage = pageMap.get("Gold Loan").get("All Loans");
	        
	    }
	 
	    @Command
	    public void navigatePage(@BindingParam("target") NavigationPage targetPage) {
	        BindUtils.postNotifyChange(null, null, currentPage, "selected");
	        currentPage = targetPage;
	        BindUtils.postNotifyChange(null, null, this, "currentPage");
	    }
	     
	    public NavigationPage getCurrentPage() {
	        return currentPage;
	    }
	 
	    public Map<String, Map<String, NavigationPage>> getPageMap() {
	        return pageMap;
	    }
	     
	    private void initPageMap() {
	        pageMap = new LinkedHashMap<String, Map<String, NavigationPage>>();
	         
	        addPage("Gold Loan", "All Loans", "Loan_search.zul");
	        addPage("Gold Loan", "Apply Loan", "applyLoan.zul");
	        if(l.getUser_role().equals("Manger")){
	        addPage("Gold Loan", "Pennding Loans", "Pending_loans.zul");
	        addPage("Gold Loan","Gold Ledger Details","Bank_details.zul");
	        }
	        
	        
	        addPage("Scheme", "Schemes", "all_schemes.zul", "active");
	        addPage("Scheme", "Add Scheme", "adding_scheme.zul", "inactive");
	      
	        addPage("Item Category", "Gold", "goldtype.zul");
	       
	        addPage("Customers", "Customers Enrollment", "custmr.zul");
	        addPage("Customers", "Add Customer", "add_custmer.zul");
	        addPage("Nominee","Nominee List","nominee_details.zul");
	        addPage("Nominee","Add Nominee","Add_Nominee.zul");
	        
	    }
	 @Command
	    private void addPage(String title, String subTitle, String includeUri) {
	        addPage(title, subTitle, includeUri, null);
	    }
	     
	    private void addPage(String title, String subTitle, String includeUri, String data) {
	        String folder = "/zul/";
	        Map<String, NavigationPage> subPageMap = pageMap.get(title);
	        if(subPageMap == null) {
	            subPageMap = new LinkedHashMap<String, NavigationPage>();
	            pageMap.put(title, subPageMap);
	        }
	        NavigationPage navigationPage = new NavigationPage(title, subTitle,
	                folder + includeUri + "?random=" + Math.random(), data) {
	            @Override
	            public boolean isSelected() {
	                return currentPage == this;
	            }
	        };
	        subPageMap.put(subTitle, navigationPage);
	    }
	
	
@Command
public void logout(){
	Executions.sendRedirect("../index.zul");
	ac.setLoginCredential(null);
}
@Command
public void refreshh(){
	BindUtils.postNotifyChange(null, null, currentPage, "selected");
	addPage("Gold Loan", "Apply Loan", "applyLoan.zul");
	currentPage = pageMap.get("Gold Loan").get("Apply Loan");
	BindUtils.postNotifyChange(null, null, this, "currentPage");
}
@Command
public void refresh(@BindingParam("target") int loan_id){
	Session_Model sm=new Session_Model();
	sm.setLoan_id(loan_id);
	sm.setRole(l.getUser_role());
	ac.setDetails(sm);
	
	BindUtils.postNotifyChange(null, null, currentPage, "selected");
	addPage("Gold Loan", "Apply Loan", "Approv_Edit_Loan.zul");
	currentPage = pageMap.get("Gold Loan").get("Apply Loan");
	BindUtils.postNotifyChange(null, null, this, "currentPage");
}


@Command
public void toloanpage(){
	BindUtils.postNotifyChange(null, null, currentPage, "selected");
	currentPage = pageMap.get("Gold Loan").get("All Loans");
	BindUtils.postNotifyChange(null, null, this, "currentPage");
	
}
@Command
public void edit_scheme(@BindingParam("target") String id) {
	System.out.println(id);
	Session sess = Sessions.getCurrent();
	sess.setAttribute("id1", id);
	BindUtils.postNotifyChange(null, null, currentPage, "selected");
	addPage("Scheme", "edit_scheme", "edit_scheme.zul", "active");
	currentPage = pageMap.get("Scheme").get("edit_scheme");
	BindUtils.postNotifyChange(null, null, this, "currentPage");
}
@Command
public void toallloans(){
	BindUtils.postNotifyChange(null, null, currentPage, "selected");
	addPage("Gold Loan","All Loans", "Loan_search.zul");
	currentPage = pageMap.get("Gold Loan").get("All Loans");
	BindUtils.postNotifyChange(null, null, this, "currentPage");
}
@Command
public void topendingloans(){
	BindUtils.postNotifyChange(null, null, currentPage, "selected");
	currentPage = pageMap.get("Gold Loan").get("Pennding Loans");
	BindUtils.postNotifyChange(null, null, this, "currentPage");
}

@Command
public void toschemepage(){
	BindUtils.postNotifyChange(null, null, currentPage, "selected");
	currentPage = pageMap.get("Scheme").get("Schemes");
	BindUtils.postNotifyChange(null, null, this, "currentPage");
}
@Command
public void tocustmerpage(){
	BindUtils.postNotifyChange(null, null, currentPage, "selected");
	currentPage = pageMap.get("Nominee").get("Add Nominee");
	BindUtils.postNotifyChange(null, null, this, "currentPage");
}
@Command
public void tonominee(){
	BindUtils.postNotifyChange(null, null, currentPage, "selected");
	currentPage = pageMap.get("Nominee").get("Nominee List");
	BindUtils.postNotifyChange(null, null, this, "currentPage");
}
}
