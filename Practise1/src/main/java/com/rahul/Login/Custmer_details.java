package com.rahul.Login;

import java.sql.Date;

public class Custmer_details {
	private int custmr_id;
	private String custmr_accuntno;
	private String custmr_name;
	private Date custmr_dob;
	private Date custmr_regdate;
	private String custmr_status;
	public int getCustmr_id() {
		return custmr_id;
	}
	public void setCustmr_id(int custmr_id) {
		this.custmr_id = custmr_id;
	}
	public String getCustmr_accuntno() {
		return custmr_accuntno;
	}
	public void setCustmr_accuntno(String custmr_accuntno) {
		this.custmr_accuntno = custmr_accuntno;
	}
	public String getCustmr_name() {
		return custmr_name;
	}
	public void setCustmr_name(String custmr_name) {
		this.custmr_name = custmr_name;
	}
	public Date getCustmr_dob() {
		return custmr_dob;
	}
	public void setCustmr_dob(Date custmr_dob) {
		this.custmr_dob = custmr_dob;
	}
	public Date getCustmr_regdate() {
		return custmr_regdate;
	}
	public void setCustmr_regdate(Date custmr_regdate) {
		this.custmr_regdate = custmr_regdate;
	}
	public String getCustmr_status() {
		return custmr_status;
	}
	public void setCustmr_status(String custmr_status) {
		this.custmr_status = custmr_status;
	}
}