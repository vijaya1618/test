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

import com.pennantExamination.DAO.CityDAO;

import com.pennantExamination.pojos.CityModel;


public class CityEditController extends Window implements Composer<Component>{
	private static final long serialVersionUID = 1L;

	@Wire   
	 private Window EditCityWin;
	 
	 CityDAO CityDao;
	 
	 public void onCreate(){
		 
		 Textbox cityName=(Textbox) this.getFellow("editCityName");
		 Checkbox cityEnabled = (Checkbox) this.getFellow("enabled");
	 
		 @SuppressWarnings("deprecation")
		 ApplicationContext ctx = 
			WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		 CityDao = (CityDAO)ctx.getBean("citiDAO");
		 CityModel city =CityDao.findById();
		 
		 cityName.setValue(city.getCityName());
		 if(city.getEnabled() == 1){
			 cityEnabled.setChecked(true);
		 }else{
			 cityEnabled.setChecked(false);
		 }
	 }
	 
	 public void onUpdate()
	 {
		 @SuppressWarnings("deprecation")
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		 CityDao = (CityDAO)ctx.getBean("citiDAO");
			CityModel ctyedit =new CityModel();
			
			ctyedit.setCityName(((Textbox) this.getFellow("editCityName")).getValue());
			Checkbox enabled=(Checkbox) this.getFellow("enabled");
			 int en=0;
			if(enabled.isChecked()){
				en = 1;	
			}else{
				en=0;
			}
			ctyedit.setEnabled(en);
			CityDao.update(ctyedit);
	 }

	
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		
	}
	 
	
}
