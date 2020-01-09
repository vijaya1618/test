package com.rahul.Loan;

import java.sql.Date;

public class Loan_Model {
	private int loan_id;
	
	private int custmer_id;
	private String custmer_name;
	private long custmer_phno;
	private int nomine_id;
	private String nominee_name;
	private Date nominee_dob;
	private String nominee_rel;
	private int nominee_age;
	private String item_type;
	private String item_name;
	private double item_wt;
	private int item_purity;
	private double item_market_rate;
	private double item_lending_rate;
	private double item_total_amount;
	private byte[] image;
	private int scheme_id;
	private String scheme_name;
	private String payment_basis;
	private int tenure;
	private double scheme_intrest;
	private Date loan_date;
	private String frq_of_pay;
	private double req_amount;
	private double avaliable_amount;
	private String intrest_type;
	private String apprisal_name;
	private Date aprvl_date; 
	private String status;
	public Date getAprvl_date() {
		return aprvl_date;
	}
	public void setAprvl_date(Date aprvl_date) {
		this.aprvl_date = aprvl_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getCustmer_id() {
		return custmer_id;
	}
	public void setCustmer_id(int custmer_id) {
		this.custmer_id = custmer_id;
	}
	public String getCustmer_name() {
		return custmer_name;
	}
	public void setCustmer_name(String custmer_name) {
		this.custmer_name = custmer_name;
	}
	public int getNomine_id() {
		return nomine_id;
	}
	public void setNomine_id(int nomine_id) {
		this.nomine_id = nomine_id;
	}
	public String getNominee_name() {
		return nominee_name;
	}
	public void setNominee_name(String nominee_name) {
		this.nominee_name = nominee_name;
	}
	public Date getNominee_dob() {
		return nominee_dob;
	}
	public void setNominee_dob(Date nominee_dob) {
		this.nominee_dob = nominee_dob;
	}
	public String getNominee_rel() {
		return nominee_rel;
	}
	public void setNominee_rel(String nominee_rel) {
		this.nominee_rel = nominee_rel;
	}
	public int getNominee_age() {
		return nominee_age;
	}
	public void setNominee_age(int nominee_age) {
		this.nominee_age = nominee_age;
	}
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public double getItem_wt() {
		return item_wt;
	}
	public void setItem_wt(double item_wt) {
		this.item_wt = item_wt;
	}
	public int getItem_purity() {
		return item_purity;
	}
	public void setItem_purity(int item_purity) {
		this.item_purity = item_purity;
	}
	public double getItem_market_rate() {
		return item_market_rate;
	}
	public void setItem_market_rate(double item_market_rate) {
		this.item_market_rate = item_market_rate;
	}
	public double getItem_lending_rate() {
		return item_lending_rate;
	}
	public void setItem_lending_rate(double item_lending_rate) {
		this.item_lending_rate = item_lending_rate;
	}
	public double getItem_total_amount() {
		return item_total_amount;
	}
	public void setItem_total_amount(double item_total_amount) {
		this.item_total_amount = item_total_amount;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public int getScheme_id() {
		return scheme_id;
	}
	public void setScheme_id(int scheme_id) {
		this.scheme_id = scheme_id;
	}
	public String getScheme_name() {
		return scheme_name;
	}
	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
	}
	public String getPayment_basis() {
		return payment_basis;
	}
	public void setPayment_basis(String payment_basis) {
		this.payment_basis = payment_basis;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public double getScheme_intrest() {
		return scheme_intrest;
	}
	public void setScheme_intrest(double scheme_intrest) {
		this.scheme_intrest = scheme_intrest;
	}
	public Date getLoan_date() {
		return loan_date;
	}
	public void setLoan_date(Date loan_date) {
		this.loan_date = loan_date;
	}
	public String getFrq_of_pay() {
		return frq_of_pay;
	}
	public void setFrq_of_pay(String frq_of_pay) {
		this.frq_of_pay = frq_of_pay;
	}
	public double getReq_amount() {
		return req_amount;
	}
	public void setReq_amount(double req_amount) {
		this.req_amount = req_amount;
	}
	public double getAvaliable_amount() {
		return avaliable_amount;
	}
	public void setAvaliable_amount(double avaliable_amount) {
		this.avaliable_amount = avaliable_amount;
	}
	public String getIntrest_type() {
		return intrest_type;
	}
	public void setIntrest_type(String intrest_type) {
		this.intrest_type = intrest_type;
	}
	public String getApprisal_name() {
		return apprisal_name;
	}
	public void setApprisal_name(String apprisal_name) {
		this.apprisal_name = apprisal_name;
	}
	public int getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}
	public long getCustmer_phno() {
		return custmer_phno;
	}
	public void setCustmer_phno(long custmer_phno) {
		this.custmer_phno = custmer_phno;
	}

}
