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
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.SetsDAO;
import com.pennantExamination.pojos.SetsModel;

import org.zkoss.zul.Checkbox;

public class AddSetsController extends Window{
	
	private static final long serialVersionUID = 1L;
	
	SetsDAO setsDAO;
	
	SetsModel sets=new SetsModel();
	
	public void insertSets()
	{
		@SuppressWarnings("deprecation")
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext((ServletContext) getDesktop().getWebApp().getNativeContext());
		setsDAO = (SetsDAO) ctx.getBean("setsDAO");
		
		//sets.setSetId(((Intbox)this.getFellow("addSetId")).getValue());
		sets.setSetNo(((Intbox) this.getFellow("addSetNo")).getValue());
		boolean enabled=(((Checkbox) this.getFellow("addEnabled")).isChecked());
		int e=0;
		if(enabled==true)
		{
			e=1;
		}
		else
		{
			e=0;
		}
		//sets.setsetEnabled(((Checkbox) this.getFellow("addEnabled")).isChecked());

		sets.setsetEnabled(e);
		
		setsDAO.insert(sets);
	}

}
