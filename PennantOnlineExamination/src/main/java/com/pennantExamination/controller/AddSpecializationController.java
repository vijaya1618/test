package com.pennantExamination.controller;

import java.util.ArrayList;
import java.util.List;

import com.pennantExamination.pojos.CityModel;
import com.pennantExamination.pojos.DegreeModel;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


import com.pennantExamination.DAO.SpecializationDAO;
import com.pennantExamination.pojos.SpecializationModel;
 
public class AddSpecializationController extends Window{
    private static final long serialVersionUID = 1L;
    
 @Wire   
 private Window AddSpecializationWin;
    
 SpecializationDAO specializationDao;
 private ListModel<CityModel> citiesList; 
 SpecializationModel slz=new SpecializationModel();
 
 public void onCreate(){
 @SuppressWarnings("deprecation")
 ApplicationContext ctx = 
	WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
specializationDao = (SpecializationDAO)ctx.getBean("specializationDAO"); 
Combobox citydropdown=(Combobox)this.getFellow("addDegreeName");
ListModelList<CityModel> list=new ListModelList(specializationDao.getdegreeList());
citydropdown.setModel(list);

}
    
  public void insertSpecializationDetails(){
	  @SuppressWarnings("deprecation")
		 ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
	  specializationDao = (SpecializationDAO)ctx.getBean("specializationDAO");	
	  slz.setSpecializationName(((Textbox) this.getFellow("addSpecializationName")).getValue());
	  Combobox degree= (Combobox) this.getFellow("addDegreeName");
	  Comboitem item1 = (Comboitem) degree.getSelectedItem();
	  String degreeName = item1.getValue().toString();
	  int degreeId= Integer.parseInt(degreeName);
	  slz.setDegreeId(degreeId);
		 Checkbox enabled=(Checkbox) this.getFellow("enabled");
		 int en=0;
		if(enabled.isChecked()){
			en = 1;	
		}else{
			en=0;
		}
		slz.setEnabled(en);

		specializationDao.insert(slz);
  }
   
}