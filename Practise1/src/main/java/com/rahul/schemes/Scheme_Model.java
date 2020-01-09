package com.rahul.schemes;

import java.sql.Date;

public class Scheme_Model {
	
	private int scheme_id;
	private String scheme_name;
	private String scheme_loan_type;
	private String scheme_product;
	private String scheme_calc;
	private String scheme_compound;
	private double scheme_min_ammount;
	private double scheme_max_ammount;
	private int scheme_payment_freq;
	private String scheme_pay_advance;
	private Date scheme_aplicable_from;
	private Date scheme_aplicable_to ;
	private int scheme_loan_period;
	private double scheme_intrest;
	private int scheme_intrst_days;
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
	public String getScheme_loan_type() {
		return scheme_loan_type;
	}
	public void setScheme_loan_type(String scheme_loan_type) {
		this.scheme_loan_type = scheme_loan_type;
	}
	public String getScheme_product() {
		return scheme_product;
	}
	public void setScheme_product(String scheme_product) {
		this.scheme_product = scheme_product;
	}
	public String getScheme_calc() {
		return scheme_calc;
	}
	public void setScheme_calc(String scheme_calc) {
		this.scheme_calc = scheme_calc;
	}
	public String getScheme_compound() {
		return scheme_compound;
	}
	public void setScheme_compound(String scheme_compound) {
		this.scheme_compound = scheme_compound;
	}
	public double getScheme_min_ammount() {
		return scheme_min_ammount;
	}
	public void setScheme_min_ammount(double scheme_min_ammount) {
		this.scheme_min_ammount = scheme_min_ammount;
	}
	public double getScheme_max_ammount() {
		return scheme_max_ammount;
	}
	public void setScheme_max_ammount(double scheme_max_ammount) {
		this.scheme_max_ammount = scheme_max_ammount;
	}
	public int getScheme_payment_freq() {
		return scheme_payment_freq;
	}
	public void setScheme_payment_freq(int scheme_payment_freq) {
		this.scheme_payment_freq = scheme_payment_freq;
	}
	public String getScheme_pay_advance() {
		return scheme_pay_advance;
	}
	public void setScheme_pay_advance(String scheme_pay_advance) {
		this.scheme_pay_advance = scheme_pay_advance;
	}
	public Date getScheme_aplicable_from() {
		return scheme_aplicable_from;
	}
	public void setScheme_aplicable_from(Date scheme_aplicable_from) {
		this.scheme_aplicable_from = scheme_aplicable_from;
	}
	public Date getScheme_aplicable_to() {
		return scheme_aplicable_to;
	}
	public void setScheme_aplicable_to(Date scheme_aplicable_to) {
		this.scheme_aplicable_to = scheme_aplicable_to;
	}
	public int getScheme_loan_period() {
		return scheme_loan_period;
	}
	public void setScheme_loan_period(int scheme_loan_period) {
		this.scheme_loan_period = scheme_loan_period;
	}
	public double getScheme_intrest() {
		return scheme_intrest;
	}
	public void setScheme_intrest(double scheme_intrest) {
		this.scheme_intrest = scheme_intrest;
	}
	public int getScheme_intrst_days() {
		return scheme_intrst_days;
	}
	public void setScheme_intrst_days(int scheme_intrst_days) {
		this.scheme_intrst_days = scheme_intrst_days;
	}

}
