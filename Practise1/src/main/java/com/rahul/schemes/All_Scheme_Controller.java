package com.rahul.schemes;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import com.rahul.customers.Custmer_details;

public class All_Scheme_Controller extends Window{
	Scheme_details_Interface sdi;
	public void onCreate(){
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		sdi= (Scheme_details_Interface)ctx.getBean("schemedb");
		 ListModelList<Scheme_Model> l=new ListModelList<Scheme_Model>(sdi.getallschemes());
		 Listbox li=(Listbox)this.getFellow("scheme_list");
		 li.getItems().clear();
		 li.setModel(l);
		
	}
	public void search(String name){
		if(name!=null){
		 ListModelList<Scheme_Model> l=new ListModelList<Scheme_Model>(sdi.get_Scheme_By_Name(name));
		 Listbox li=(Listbox)this.getFellow("scheme_list");
		 li.getItems().clear();
		 li.setModel(l);
		}else{
			onCreate();
		}
	}

}
