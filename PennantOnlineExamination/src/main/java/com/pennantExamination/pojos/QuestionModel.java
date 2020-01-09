package com.pennantExamination.pojos;

public class QuestionModel {
   private int question_id;
   private String question;
   private String option_A;
   private String option_B;
   private String option_C;
   private String option_D;
   private String correct_ans;
   private String question_type;
   private int set_id;
public int getQuestion_id() {
	return question_id;
}
public void setQuestion_id(int question_id) {
	this.question_id = question_id;
}
public String getQuestion() {
	return question;
}
public void setQuestion(String question) {
	this.question = question;
}
public String getOption_A() {
	return option_A;
}
public void setOption_A(String option_A) {
	this.option_A = option_A;
}
public String getOption_B() {
	return option_B;
}
public void setOption_B(String option_B) {
	this.option_B = option_B;
}
public String getOption_C() {
	return option_C;
}
public void setOption_C(String option_C) {
	this.option_C = option_C;
}
public String getOption_D() {
	return option_D;
}
public void setOption_D(String option_D) {
	this.option_D = option_D;
}
public String getCorrect_ans() {
	return correct_ans;
}
public void setCorrect_ans(String correct_ans) {
	this.correct_ans = correct_ans;
}
public String getQuestion_type() {
	return question_type;
}
public void setQuestion_type(String question_type) {
	this.question_type = question_type;
}
public int getSet_id() {
	return set_id;
}
public void setSet_id(int set_id) {
	this.set_id = set_id;
}
   
   
}
