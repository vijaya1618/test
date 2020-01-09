package com.pennantExamination.pojos;


import java.sql.Blob;
import java.util.Date;

import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Fileupload;

public class ExamineeModel {
	
	protected int examineeId;
	protected String examineeFullName;
	protected String examineeUserName;
	protected String examineePassword;
	protected String examinee_re_Password;
	protected String examineeEmail;
	protected long examineePhn;
	protected String examineeGender;
	protected Date examineeDOB;
	protected int examineeYop;
	protected String examineeCourse;
	protected String examineeSpecialization;
	protected String examineeCollege;
	protected String examineeAddress;
	protected byte[] resume;
	protected String fileName;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getResume() {
		return resume;
	}
	public void setResume(byte[] resume) {
		this.resume = resume;
	}
	protected long examineeAadhaar;
	
	
	public void setExamineeAadhaar(long examineeAadhaar) {
		this.examineeAadhaar = examineeAadhaar;
	}
	public int getExamineeId() {
		return examineeId;
	}
	public void setExamineeId(int examineeId) {
		this.examineeId = examineeId;
	}
	public String getExamineeFullName() {
		return examineeFullName;
	}
	public void setExamineeFullName(String examineeFullName) {
		this.examineeFullName = examineeFullName;
	}
	public String getExamineeUserName() {
		return examineeUserName;
	}
	public void setExamineeUserName(String examineeUserName) {
		this.examineeUserName = examineeUserName;
	}
	public String getExamineePassword() {
		return examineePassword;
	}
	public void setExamineePassword(String examineePassword) {
		this.examineePassword = examineePassword;
	}
	
	public String getExaminee_re_Password() {
		return examinee_re_Password;
	}
	public void setExaminee_re_Password(String examinee_re_Password) {
		this.examinee_re_Password = examinee_re_Password;
	}
	public String getExamineeEmail() {
		return examineeEmail;
	}
	public void setExamineeEmail(String examineeEmail) {
		this.examineeEmail = examineeEmail;
	}
	public long getExamineePhn() {
		return examineePhn;
	}
	public void setExamineePhn(long examineePhn) {
		this.examineePhn = examineePhn;
	}
	public String getExamineeGender() {
		return examineeGender;
	}
	public void setExamineeGender(String examineeGender) {
		this.examineeGender = examineeGender;
	}
	public Date getExamineeDOB() {
		return examineeDOB;
	}
	public void setExamineeDOB(Date examineeDOB) {
		this.examineeDOB = examineeDOB;
	}
	public int getExamineeYop() {
		return examineeYop;
	}
	public void setExamineeYop(int examineeYop) {
		this.examineeYop = examineeYop;
	}
	public String getExamineeCourse() {
		return examineeCourse;
	}
	public void setExamineeCourse(String examineeCourse) {
		this.examineeCourse = examineeCourse;
	}
	public String getExamineeSpecialization() {
		return examineeSpecialization;
	}
	public void setExamineeSpecialization(String examineeSpecialization) {
		this.examineeSpecialization = examineeSpecialization;
	}
	public String getExamineeCollege() {
		return examineeCollege;
	}
	public void setExamineeCollege(String examineeCollege) {
		this.examineeCollege = examineeCollege;
	}
	public String getExamineeAddress() {
		return examineeAddress;
	}
	public void setExamineeAddress(String examineeAddress) {
		this.examineeAddress = examineeAddress;
	}
	public long getExamineeAadhaar() {
		return examineeAadhaar;
	}
	public void setExamineeAadhaar(Long examineeAadhaar) {
		this.examineeAadhaar = examineeAadhaar;
	}

	
	
	
}
