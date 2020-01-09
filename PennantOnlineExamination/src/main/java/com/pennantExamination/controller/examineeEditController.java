package com.pennantExamination.controller;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.ExamineeDAO;
import com.pennantExamination.pojos.CollegeModel;
import com.pennantExamination.pojos.DegreeModel;
import com.pennantExamination.pojos.ExamineeModel;
import com.pennantExamination.pojos.SpecializationModel;
 
public class examineeEditController extends Window implements Composer<Component>{
    private static final long serialVersionUID = 1L;
    
 @Wire   
 private Window editExamineeWin;
 
 ExamineeDAO examineeDao;
 ExamineeDAO examineeDaoedit;
 
 private ListModel<DegreeModel> degreeList;

	public ListModel<DegreeModel> getDegreeList() {
		return degreeList;
	}
 
	private ListModel<SpecializationModel> specializationList;

	public ListModel<SpecializationModel> getSpecializationList() {
		return specializationList;
	}
	
	private ListModel<CollegeModel> collegeList;

	public ListModel<CollegeModel> getCollegeList() {
		return collegeList;
	}
 
 public void onCreate(){
	 
	 Intbox examineeId=(Intbox) this.getFellow("editExamineeId");
	 
	 Textbox fullName=(Textbox) this.getFellow("editFullName");
	 
	 Textbox userName=(Textbox) this.getFellow("editUserName");
	 
	 Textbox password=(Textbox) this.getFellow("editPassword");
			 
	 Textbox email=(Textbox) this.getFellow("editEmail");
	 
	 Longbox phn=(Longbox) this.getFellow("editPhn");
	 
	 Radiogroup gender=(Radiogroup) this.getFellow("gender");
	 
	 Datebox DOB=(Datebox) this.getFellow("editDOB");
	 
	 Combobox Yop=(Combobox) this.getFellow("editYop");
	 
	 Combobox course=(Combobox) this.getFellow("editCourse");
	 
	 Combobox specialization=(Combobox) this.getFellow("editSpecialization");
	 
	 Combobox college=(Combobox) this.getFellow("editCollege");
	 
	 Textbox address=(Textbox) this.getFellow("editAddress");
	 
	 Longbox aadhaar=(Longbox) this.getFellow("editAadhaar");
	 
	 //File resume=(File) this.getFellow("editResume");

	 
	 @SuppressWarnings("deprecation")
	 ApplicationContext ctx = 
		WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
	 examineeDao = (ExamineeDAO)ctx.getBean("examDAO");
	 ExamineeModel examinee =examineeDao.findById();
 
	   ListModelList<DegreeModel> list=new ListModelList(examineeDao.getDegreeList());
	   course.setModel(list);
	   
	
	   ListModelList<SpecializationModel> specList=new ListModelList(examineeDao.getSpecializationList());
	   specialization.setModel(specList);
	   
	 
	   ListModelList<CollegeModel> collegeList=new ListModelList(examineeDao.getCollegeList());
	   college.setModel(collegeList);
	 
	 examineeId.setValue(examinee.getExamineeId());
	 fullName.setValue(examinee.getExamineeFullName());
	 userName.setValue(examinee.getExamineeUserName());
	 password.setValue(examinee.getExamineePassword());
	 email.setValue(examinee.getExamineeEmail());
	 phn.setValue(examinee.getExamineePhn());
	 
	String genderValue=null;
	Radio value = null;
	for(int i=0;i < gender.getItems().size(); i++){
		value = (Radio) gender.getItems().get(i);
		genderValue=value.getValue().toString();
		if(examinee.getExamineeGender().equalsIgnoreCase(genderValue)){
			gender.setSelectedItem(value);
		}
		
	}
	 
	 DOB.setValue(examinee.getExamineeDOB());
	 
	 Comboitem item1 = null;
	 int yopValue=0;
	 for(int i=0;i < Yop.getItems().size();i++){
		item1 =(Comboitem) Yop.getItems().get(i);
		 yopValue=Integer.parseInt(item1.getValue().toString());
		 if(examinee.getExamineeYop() == yopValue ){
			 Yop.setSelectedItem(item1);
			 break;
		 }
	 }
	 

	 
	 Comboitem item2 = null;
	 String courseValue=null;
	 //System.out.println("course "+course.getItemCount());
	 for(int i=0;i < course.getItems().size();i++){
			item2 =(Comboitem) course.getItems().get(i);
			courseValue=item2.getLabel().toString();
			 if(examinee.getExamineeCourse().equals(courseValue)){
				 course.setSelectedItem(item2);
				 break;
			 }
		 }
	 Comboitem item3 = null;
	 String specializationValue=null;
	 for(int i=0;i < specialization.getItems().size();i++){
			item3 =(Comboitem) specialization.getItems().get(i);
			specializationValue=item3.getValue().toString();
			 if(examinee.getExamineeSpecialization().equalsIgnoreCase(specializationValue)){
				 specialization.setSelectedItem(item3);
				 break;
			 }
		 }
	 Comboitem item4 = null;
	 String collegeValue=null;
	 for(int i=0;i < college.getItems().size();i++){
		 item4 =(Comboitem) college.getItems().get(i);
		 collegeValue=item4.getValue().toString();
			 if(examinee.getExamineeCollege().equalsIgnoreCase(collegeValue)){
				 college.setSelectedItem(item4);
				 break;
			 }
		 }
	 address.setValue(examinee.getExamineeAddress());
	 aadhaar.setValue(examinee.getExamineeAadhaar());
	// resume.set
	 
 }

 public void onUpdate()
 {
	 ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
	 	examineeDaoedit = (ExamineeDAO)ctx.getBean("examDAO");
		ExamineeModel exmedit =new ExamineeModel();
		
		 exmedit.setExamineeId(((Intbox) this.getFellow("editExamineeId")).getValue());
		
		 exmedit.setExamineeFullName(((Textbox) this.getFellow("editFullName")).getValue());
		 
		 exmedit.setExamineeUserName(((Textbox) this.getFellow("editUserName")).getValue());
		 
		 exmedit.setExamineePassword(((Textbox) this.getFellow("editPassword")).getValue()) ;                                                                                                                                                                                                                  ;
				 
		 exmedit.setExamineeEmail(((Textbox) this.getFellow("editEmail")).getValue());
		 
		 exmedit.setExamineePhn(((Longbox) this.getFellow("editPhn")).getValue());
		 
		 exmedit.setExamineeGender(((Radiogroup) this.getFellow("gender")).getSelectedItem().getValue().toString());
		 
		 exmedit.setExamineeDOB(((Datebox) this.getFellow("editDOB")).getValue());
		 
		 exmedit.setExamineeYop(Integer.parseInt(((Combobox) this.getFellow("editYop")).getValue()));
		 
		 exmedit.setExamineeCourse(((Combobox) this.getFellow("editCourse")).getValue());
		 
		 exmedit.setExamineeSpecialization(((Combobox) this.getFellow("editSpecialization")).getValue());
		 
		 exmedit.setExamineeCollege(((Combobox) this.getFellow("editCollege")).getValue());
		 
		 exmedit.setExamineeAddress(((Textbox) this.getFellow("editAddress")).getValue());
		 
		 exmedit.setExamineeAadhaar(((Longbox) this.getFellow("editAadhaar")).getValue());
		 
		 examineeDaoedit.update(exmedit);
		 	
 }
 
public void doAfterCompose(Component comp) throws Exception {
	// TODO Auto-generated method stub
	
}
    
    
    
    
    
}
