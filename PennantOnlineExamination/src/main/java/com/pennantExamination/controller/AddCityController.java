package com.pennantExamination.controller;



import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.CityDAO;
import com.pennantExamination.pojos.CityModel;
 
public class AddCityController extends Window{
    private static final long serialVersionUID = 1L;
    
 @Wire   
 private Window AddCityWin;
    
 CityDAO citiDAO;
	
 CityModel cty=new CityModel(); 
 
 
 public void insertCityDetails()
	 {
		 @SuppressWarnings("deprecation")
		 ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		 citiDAO = (CityDAO)ctx.getBean("citiDAO");
		 
		 	
		 cty.setCityName(((Textbox) this.getFellow("addCityName")).getValue());
		 Checkbox enabled=(Checkbox) this.getFellow("enabled");
		 int en=0;
		if(enabled.isChecked()){
			en = 1;	
		}else{
			en=0;
		}
		cty.setEnabled(en);

		 citiDAO.insert(cty);
	 }

	
	
}
