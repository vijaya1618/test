package com.rahul.Nominee;

import java.sql.Date;

public class Nominee_Model {
public int custm_id;
public int nominee_id;
public String custm_name;
public String nominee_name;
public String nominee_relation;
public int nominee_age;
public Date nominee_dob;
public int getCustm_id() {
	return custm_id;
}
public void setCustm_id(int custm_id) {
	this.custm_id = custm_id;
}
public int getNominee_id() {
	return nominee_id;
}
public void setNominee_id(int nominee_id) {
	this.nominee_id = nominee_id;
}
public String getCustm_name() {
	return custm_name;
}
public void setCustm_name(String custm_name) {
	this.custm_name = custm_name;
}
public String getNominee_name() {
	return nominee_name;
}
public void setNominee_name(String nominee_name) {
	this.nominee_name = nominee_name;
}
public String getNominee_relation() {
	return nominee_relation;
}
public void setNominee_relation(String nominee_relation) {
	this.nominee_relation = nominee_relation;
}
public int getNominee_age() {
	return nominee_age;
}
public void setNominee_age(int nominee_age) {
	this.nominee_age = nominee_age;
}
public Date getNominee_dob() {
	return nominee_dob;
}
public void setNominee_dob(Date nominee_dob) {
	this.nominee_dob = nominee_dob;
}
}
