package com.pennantExamination.controller;
	import org.zkoss.bind.annotation.Init;
	import org.zkoss.zhtml.Messagebox;
	import org.zkoss.zk.ui.Component;
	import org.zkoss.zk.ui.select.annotation.Listen;
	import org.zkoss.zk.ui.select.annotation.Wire;
	import org.zkoss.zul.Include;
	import org.zkoss.zul.Textbox;

import com.pennantExamination.DAO.OnlineExamDAO;
import com.pennantExamination.pojos.EduModel;
import com.pennantExamination.pojos.TechModel;

import org.zkoss.zul.Doublebox;
	import org.zkoss.zul.Combobox;
	import org.zkoss.zul.Intbox;
	import javax.servlet.ServletContext; 
	import org.springframework.context.ApplicationContext;
	import org.springframework.web.context.support.WebApplicationContextUtils;
	import org.zkoss.zul.Div;

	public class OnlineCntrl extends Div{
		
		
		private static final long serialVersionUID = 1L;
		
		protected OnlineExamDAO regdao;
		
	//	private Component register;
		
		
		
		public void submit()
		{
			ApplicationContext ctx = 
					WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
			regdao=(OnlineExamDAO)ctx.getBean("onlineExamDAO");
			
			TechModel tm=new TechModel();
			tm.setProjectName(((Textbox)this.getFellow("project_name")).getValue());
			tm.setProjectGuide(((Textbox)this.getFellow("project_guide")).getValue());
			tm.setProjectDuration(((Textbox)this.getFellow("project_duration")).getValue());
			tm.setInstitutionName(((Textbox)this.getFellow("institution_name")).getValue());
			tm.setDescription(((Textbox)this.getFellow("project_description")).getValue());
			tm.setLanguageKnown(((Textbox)this.getFellow("languages_known")).getValue());
			tm.setExperience(((Combobox)this.getFellow("experience")).getValue());
			regdao.TechInsert(tm);
			 Messagebox.show("Click on Start Exam to start your Exam");
			  
			
			
		}
	
	  public void educreg_submit() 
	  { 
		  ApplicationContext ctx =
	             WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
	            regdao=(OnlineExamDAO)ctx.getBean("taskDAO");
	  
	  EduModel em=new EduModel();
	  em.setSchoolName(((Textbox)this.getFellow("school_name")).getValue());
	  em.setSchoolCity(((Textbox)this.getFellow("school_city")).getValue());
	  em.setSchoolState(((Textbox)this.getFellow("School_state")).getValue());
	  em.setSchool_CityPincode(((Intbox)this.getFellow("School_pincode")).getValue());
	  em.setBoardName(((Textbox)this.getFellow("Sch_board_name")).getValue());
	  em.setSchool_YOP(((Intbox)this.getFellow("school_yop")).getValue());
	  em.setSchool_Agg(((Doublebox)this.getFellow("School_agg")).getValue());
	  em.setInter_clg_name(((Textbox)this.getFellow("inter_clz_name")).getValue());
	  em.setClgCity(((Textbox)this.getFellow("college_city")).getValue());
	  em.setClgState(((Textbox)this.getFellow("College_state")).getValue());
	  em.setClg_CityPincode(((Intbox)this.getFellow("college_pincode")).getValue());
	  em.setBoard_Name(((Textbox)this.getFellow("clz_board_name")).getValue());
	  em.setClg_YOP(((Intbox)this.getFellow("clz_yop")).getValue());
	  em.setClg_Agg(((Doublebox)this.getFellow("clz_agg")).getValue());
	  regdao.EducInsert(em);
	  Messagebox.show("Form successfully submitted!");
	  
	  } 
	
	}

