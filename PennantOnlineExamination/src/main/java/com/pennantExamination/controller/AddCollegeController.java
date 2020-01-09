package com.pennantExamination.controller;

import java.util.ArrayList;
import java.util.List;
import com.pennantExamination.pojos.CityModel;
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


import com.pennantExamination.DAO.CollegeDAO;
import com.pennantExamination.pojos.CollegeModel;
 
public class AddCollegeController extends Window{
    private static final long serialVersionUID = 1L;
    
 @Wire   
 private Window AddCollegeWin;
 private ListModel<CityModel> citiesList;   
 CollegeDAO collegeDao;
 CollegeModel clg=new CollegeModel();
 public void onCreate(){
	 @SuppressWarnings("deprecation")
	 ApplicationContext ctx = 
		WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
  collegeDao = (CollegeDAO)ctx.getBean("collegeDAO"); 
 Combobox citydropdown=(Combobox)this.getFellow("addCityId");
 ListModelList<CityModel> list=new ListModelList(collegeDao.getcityList());
 citydropdown.setModel(list);
 }
    
  public void insertCollegeDetails(){
	  @SuppressWarnings("deprecation")
		 ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
	  collegeDao = (CollegeDAO)ctx.getBean("collegeDAO");
		 
		 	
	  clg.setCollegeName(((Textbox) this.getFellow("addCollegeName")).getValue());
	  Combobox city= (Combobox) this.getFellow("addCityId");
	  Comboitem item1 = (Comboitem) city.getSelectedItem();
	  String cityName = item1.getValue().toString();
	  int cityId= Integer.parseInt(cityName);
	  clg.setCityId(cityId);
		 Checkbox enabled=(Checkbox) this.getFellow("enabled");
		 int en=0;
		if(enabled.isChecked()){
			en = 1;	
		}else{
			en=0;
		}
		clg.setEnabled(en);

		collegeDao.insert(clg);
  }
   
}