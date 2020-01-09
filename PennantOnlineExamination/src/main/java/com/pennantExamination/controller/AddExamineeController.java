package com.pennantExamination.controller;

import java.io.FileNotFoundException;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.ExamineeDAO;
import com.pennantExamination.pojos.CollegeModel;
import com.pennantExamination.pojos.DegreeModel;
import com.pennantExamination.pojos.ExamineeModel;
import com.pennantExamination.pojos.SpecializationModel;


public class AddExamineeController extends Window{
	
	private static final long serialVersionUID = 1L;
	
	ExamineeDAO examineeDAO;
	
    ExamineeModel exm=new ExamineeModel(); 
    
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
	
    static byte[] resume;
    
    public void onCreate(){
 	   ApplicationContext ctx = 
 				WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
 	  examineeDAO = (ExamineeDAO)ctx.getBean("examDAO");
 	  
 	   Combobox coursedropdown=(Combobox)this.getFellow("addCourse");
 	   ListModelList<DegreeModel> list=new ListModelList(examineeDAO.getDegreeList());
 	   coursedropdown.setModel(list);
 	   
 	  Combobox specializationdropdown=(Combobox)this.getFellow("addSpecialization");
	   ListModelList<SpecializationModel> specList=new ListModelList(examineeDAO.getSpecializationList());
	   specializationdropdown.setModel(specList);
	   
	   Combobox collegedropdown=(Combobox)this.getFellow("addCollege");
	   ListModelList<CollegeModel> collegeList=new ListModelList(examineeDAO.getCollegeList());
	   collegedropdown.setModel(collegeList);
	   
    }
    
    public void insertExamineeDetails()
	 {
		 @SuppressWarnings("deprecation")
		 ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		 examineeDAO = (ExamineeDAO)ctx.getBean("examDAO");
		 
		 	
		 exm.setExamineeFullName(((Textbox) this.getFellow("addFullName")).getValue());
		 
		 exm.setExamineeUserName(((Textbox) this.getFellow("addUserName")).getValue());
		 
		 exm.setExamineePassword(((Textbox) this.getFellow("addPassword")).getValue()) ;                                                                                                                                                                                                                  ;
				 
		 exm.setExamineeEmail(((Textbox) this.getFellow("addEmail")).getValue());
		 
		 exm.setExamineePhn(((Longbox) this.getFellow("addPhn")).getValue());
		 
		 exm.setExamineeGender(((Radiogroup) this.getFellow("gender")).getSelectedItem().getLabel());
		 
		 exm.setExamineeDOB(((Datebox) this.getFellow("addDOB")).getValue());
		 
		 exm.setExamineeYop(Integer.parseInt(((Combobox) this.getFellow("addYop")).getValue()));
		 
		 exm.setExamineeCourse(((Combobox) this.getFellow("addCourse")).getValue());
		 
		 exm.setExamineeSpecialization(((Combobox) this.getFellow("addSpecialization")).getValue());
		 
		 exm.setExamineeCollege(((Combobox) this.getFellow("addCollege")).getValue());
		 
		 exm.setExamineeAddress(((Textbox) this.getFellow("addAddress")).getValue());
		 
		 exm.setExamineeAadhaar(((Longbox) this.getFellow("addAadhaar")).getValue());

		 examineeDAO.insert(exm);
	 }

	public void fileUpload() throws FileNotFoundException
	{
		EventListener<UploadEvent> el=new EventListener<UploadEvent>()
				{
					public void onEvent(UploadEvent ev) throws FileNotFoundException
					{
						Media m=ev.getMedia();
						resume=m.getByteData();
						exm.setResume(resume);
						exm.setFileName(m.getName());
					}
				};
			Fileupload.get(el);
	}
	
}
