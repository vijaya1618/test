package com.pennantExamination.controller;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.SpecializationDAO;

import com.pennantExamination.pojos.SpecializationModel;


public class SpecializationEditController extends Window implements Composer<Component>{
	private static final long serialVersionUID = 1L;

	@Wire   
	 private Window EditSpecializationWin;
	 
	SpecializationDAO SpecializationDao;
	 
	 public void onCreate(){
		 
		 Textbox specializationName=(Textbox) this.getFellow("editSpecializationName");
		 Intbox specializationId=(Intbox) this.getFellow("editSpecializationId");
		 Textbox degreeName=(Textbox) this.getFellow("editDegreeName");
		 Checkbox Enabled=(Checkbox) this.getFellow("enabled");
		 @SuppressWarnings("deprecation")
		 ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		 SpecializationDao = (SpecializationDAO)ctx.getBean("specializationDAO");
		 SpecializationModel specialization =SpecializationDao.findById();
		 
		 specializationName.setValue(specialization.getSpecializationName());
		 specializationId.setValue(specialization.getSpecializationId());
		 String degreeNamebyId=SpecializationDao.getDegreeNamebyId(specialization.getDegreeId());
		 degreeName.setValue(degreeNamebyId);
		 if(specialization.getEnabled()==1){
			 Enabled.setChecked(true);
		 }
		 else
		 {
			 Enabled.setChecked(false);
		 }
		 
		 
	 }
	 
	 public void onUpdate()
	 {
		 Textbox specializationName=(Textbox) this.getFellow("editSpecializationName");
		 Intbox specializationId=(Intbox) this.getFellow("editSpecializationId");
		 Textbox degreeName=(Textbox) this.getFellow("editDegreeName");
		 Checkbox Enabled=(Checkbox) this.getFellow("enabled");
		 @SuppressWarnings("deprecation")
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		 SpecializationDao = (SpecializationDAO)ctx.getBean("specializationDAO");
		 SpecializationModel slzedit =new SpecializationModel();
			
			slzedit.setSpecializationName(((Textbox) this.getFellow("editSpecializationName")).getValue());
		
			
			SpecializationDao.update(slzedit);
	 }


	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		
	}
	 
	
}

