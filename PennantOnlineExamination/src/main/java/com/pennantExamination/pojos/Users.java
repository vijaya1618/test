package com.pennantExamination.pojos;

import java.sql.Blob;
import java.util.Date;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Composer;

public class Users{
    private int examinee_id;
	private String fullName;
	private String userName;
	private String password;
	private String email;
	private String phoneNo;
	private String gender;
	private String course;
	private String college;
	private String specialization;
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	private String address;
	private Long aadhar;
	private Date dob;
	private Integer yop;
	
	public Users(){
		
	}
	public Users(int examinee_id, String fullName, String userName, String password, String email, String phoneNo, String gender,
			String course, String college, String address, Long aadhar, Date dob, Integer yop, String file,byte[] resume) {
		this.examinee_id = examinee_id;
		this.fullName = fullName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.course = course;
		this.college = college;
		this.address = address;
		this.aadhar = aadhar;
		this.dob = dob;
		this.yop = yop;
		this.file = file;
		this.resume=resume;
	}
	private byte[] resume;
	
	private String file;
	
	
	public int getExaminee_id() {
		return examinee_id;
	}
	public void setExaminee_id(int examinee_id) {
		this.examinee_id = examinee_id;
	}
	public byte[] getResume() {
		return resume;
	}
	public void setResume(byte[] resume) {
		this.resume = resume;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Long getAadhar() {
		return aadhar;
	}
	public void setAadhar(Long aadhar) {
		this.aadhar = aadhar;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
	public Integer getYop() {
		return yop;
	}
	public void setYop(Integer yop) {
		this.yop = yop;
	}
	
}
