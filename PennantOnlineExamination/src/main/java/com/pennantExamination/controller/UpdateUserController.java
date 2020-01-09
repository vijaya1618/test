package com.pennantExamination.controller;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zhtml.Div;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.pennantExamination.DAO.ExamineeDAO;
import com.pennantExamination.DAO.OnlineExamDAO;
import com.pennantExamination.pojos.ExamineeModel;
import com.pennantExamination.pojos.Users;

public class UpdateUserController extends Div implements Composer<Component>{
    private static final long serialVersionUID = 1L;
    
 @Wire   
 private Window userWin;
 
 OnlineExamDAO onlineexamdao;


public void doAfterCompose(Component comp) throws Exception {
	// TODO Auto-generated method stub
	
}



	
	
}
