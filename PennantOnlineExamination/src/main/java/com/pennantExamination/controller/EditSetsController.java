package com.pennantExamination.controller;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.SetsDAO;
import com.pennantExamination.pojos.SetsModel;

public class EditSetsController extends Window implements Composer<Component>{

	private static final long serialVersionUID = 1L;	
	
	@Wire   
	 private Window editSetsWin;
	
	SetsDAO setsDao;
	
	SetsModel sets=new SetsModel();
	
	public void onCreate()
	{
		Intbox setId=(Intbox) this.getFellow("editSetId");
		Intbox setNo=(Intbox) this.getFellow("editSetNo");
		Checkbox setEnabled=(Checkbox) this.getFellow("editEnabled");
		
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		setsDao = (SetsDAO) ctx.getBean("setsDAO");
		sets =setsDao.findById();
		setId.setValue(sets.getSetId());
		setNo.setValue(sets.getSetNo());
		//setEnabled.setValue(sets.getsetEnabled());
		boolean t;
		if(sets.getsetEnabled()==1)
		{
			t=true;
		}
		else
			t=false;
		
		setEnabled.setChecked(t);
	}
	
	public void onUpdate()
	{
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		
		setsDao = (SetsDAO) ctx.getBean("setsDAO");
		
		sets.setSetId(((Intbox) this.getFellow("editSetId")).getValue());
		
		sets.setSetNo(((Intbox) this.getFellow("editSetNo")).getValue());
		
		Checkbox editEnabled=(Checkbox) this.getFellow("editEnabled");
		int enabled ;
		if(editEnabled.isChecked()){
			enabled = 1;
		}else{
			enabled=0;
		}
		
		sets.setsetEnabled(enabled);
		
		setsDao.update(sets);
	}

	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
}
