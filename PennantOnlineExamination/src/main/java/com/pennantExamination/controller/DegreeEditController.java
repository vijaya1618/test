package com.pennantExamination.controller;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.DegreeDAO;

import com.pennantExamination.pojos.DegreeModel;


public class DegreeEditController extends Window implements Composer<Component>{
	private static final long serialVersionUID = 1L;

	@Wire   
	 private Window EditDegreeWin;
	 
	 DegreeDAO DegreeDao;
	 
	 public void onCreate(){
		 
		 Textbox degreeName=(Textbox) this.getFellow("editDegreeName");
		 
	 
		 @SuppressWarnings("deprecation")
		 ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		 DegreeDao = (DegreeDAO)ctx.getBean("degreeDAO");
		 DegreeModel degree =DegreeDao.findById();
		 Checkbox enabled=(Checkbox) this.getFellow("enabled");
		 int en=0;
		if(degree.getEnabled() == 1){
			enabled.setChecked(true);	
		}else{
			enabled.setChecked(false);	
		}
		
		 
		 degreeName.setValue(degree.getDegreeName());
	 }
	 
	 public void onUpdate()
	 {
		 @SuppressWarnings("deprecation")
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		 DegreeDao = (DegreeDAO)ctx.getBean("degreeDAO");
			DegreeModel dgredit =new DegreeModel();
			
			dgredit.setDegreeName(((Textbox) this.getFellow("editDegreeName")).getValue());
			Checkbox enabled=(Checkbox) this.getFellow("enabled");
			 int en=0;
			if(enabled.isChecked()){
				en = 1;	
			}else{
				en=0;
			}
			dgredit.setEnabled(en);
			
			DegreeDao.update(dgredit);
	 }


	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		
	}
	 
	
}
