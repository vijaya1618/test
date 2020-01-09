package com.pennantExamination.controller;


import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


import com.pennantExamination.DAO.DegreeDAO;
import com.pennantExamination.pojos.DegreeModel;
 
public class AddDegreeController extends Window{
    private static final long serialVersionUID = 1L;
    
    @Wire   
    private Window AddDegreeWin;
       
    DegreeDAO degreeDAO;
   	
    DegreeModel dgr=new DegreeModel(); 
    
    
    public void insertDegreeDetails()
   	 {
   		 @SuppressWarnings("deprecation")
   		 ApplicationContext ctx = 
   			WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
   		 degreeDAO = (DegreeDAO)ctx.getBean("degreeDAO");
   		 
   		 	
   		 dgr.setDegreeName(((Textbox) this.getFellow("addDegreeName")).getValue());
   		 Checkbox enabled=(Checkbox) this.getFellow("enabled");
   		 int en=0;
   		if(enabled.isChecked()){
   			en = 1;	
   		}else{
   			en=0;
   		}
   		dgr.setEnabled(en);

   		 degreeDAO.insert(dgr);
   	 }

   	
   	
   }
