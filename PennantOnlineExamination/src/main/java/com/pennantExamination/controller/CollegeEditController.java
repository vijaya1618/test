package com.pennantExamination.controller;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.CollegeDAO;
import com.pennantExamination.pojos.CityModel;
import com.pennantExamination.pojos.CollegeModel;


public class CollegeEditController extends Window implements Composer<Component>{
	private static final long serialVersionUID = 1L;

	@Wire   
	 private Window EditCollegeWin;
	 
	 CollegeDAO CollegeDao;
	 private ListModel<CityModel> citiesList;
	 public void onCreate(){
		 
		 Textbox collegeName=(Textbox) this.getFellow("editCollegeName");
		 Checkbox Enabled = (Checkbox) this.getFellow("enabled");
		 Textbox degreeName=(Textbox) this.getFellow("editCityId");
		 
		 @SuppressWarnings("deprecation")
		 ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		 CollegeDao = (CollegeDAO)ctx.getBean("collegeDAO");
		 Combobox citydropdown=(Combobox)this.getFellow("editCityId");
		 ListModelList<CityModel> list=new ListModelList(CollegeDao.getcityList());
		 citydropdown.setModel(list);
		 CollegeModel college =CollegeDao.findById();
		 
		collegeName.setValue(college.getCollegeName());
		String degreeNamebyId=CollegeDao.getCityNamebyId(college.getCityId());
		 degreeName.setValue(degreeNamebyId);
		if(college.getEnabled() == 1){
			 Enabled.setChecked(true);
		 }else{
			 Enabled.setChecked(false);
		 }
	 }
	 
	 public void onUpdate()
	 {
		 @SuppressWarnings("deprecation")
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		 CollegeDao = (CollegeDAO)ctx.getBean("collegeDAO");
			CollegeModel clgedit =new CollegeModel();
			
			clgedit.setCollegeName(((Textbox) this.getFellow("editCollegeName")).getValue());
			  Combobox city= (Combobox) this.getFellow("editCityId");
			  Comboitem item1 = (Comboitem) city.getSelectedItem();
			  String cityName = item1.getValue().toString();
			  int cityId= Integer.parseInt(cityName);
			  clgedit.setCityId(cityId);
			  Checkbox enabled=(Checkbox) this.getFellow("enabled");
				 int en=0;
				if(enabled.isChecked()){
					en = 1;	
				}else{
					en=0;
				}
				clgedit.setEnabled(en);
			CollegeDao.update(clgedit);
	 }


	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		
	}
	 
	
}

