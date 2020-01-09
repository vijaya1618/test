package com.pennantExamination.pojos;

public class ExamineeResultsModel {

	protected int examineeId;
	protected String examineeFullName;
	protected String examineeSpecialization;
	protected int aptitudeScore;
	protected int technicalScore;
	protected int totalScore;
	
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
	public String getExamineeSpecialization() {
		return examineeSpecialization;
	}
	public void setExamineeSpecialization(String examineeSpecialization) {
		this.examineeSpecialization = examineeSpecialization;
	}
	public int getAptitudeScore() {
		return aptitudeScore;
	}
	public void setAptitudeScore(int aptitudeScore) {
		this.aptitudeScore = aptitudeScore;
	}
	public int getTechnicalScore() {
		return technicalScore;
	}
	public void setTechnicalScore(int technicalScore) {
		this.technicalScore = technicalScore;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	
}
