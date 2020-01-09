package com.rahul.golditem;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkmax.zul.Tablelayout;
import org.zkoss.zul.Button;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;



public class Gold_item_controller extends Window{
	@Autowired
	Gold_item_Dao gold_item;
	private Component loan_item;
	GoldModel gm;
	public void onCreate(){
		
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
		
		gold_item = (Gold_item_Dao)ctx.getBean("golddb");
		try{
		Listbox lb = (Listbox)this.getFellow("listbox");
		
		lb.getItems().clear();
		ListModelList lml=new ListModelList(gold_item.getallitems());
		lb.setModel(lml);
		}catch(Exception e){
			Listbox lb = (Listbox)this.getFellow("listbox");
			Clients.showNotification("No Items are Added","error", lb, "middle_center", 2000);
		}
		Button button=(Button)this.getFellow("action2");
		button.setVisible(false);
	}

	public void getlendingrate(){
		try{
				Decimalbox market_deci=(Decimalbox)this.getFellow("item_market_rate");
				BigDecimal decimal=market_deci.getValue();
				double market_rate =decimal .doubleValue();
				double lending_rate=gold_item.get_lending_gold_rate(market_rate);
				Decimalbox lending_deci=(Decimalbox)this.getFellow("item_lending_rate");
				lending_deci.setValue(BigDecimal.valueOf(lending_rate));
		   }catch(NullPointerException ne){//Catching Null pointer Exception
			   }
		   }
	
	public void addGold_item(){
		try{
			
			Textbox text_name=(Textbox)this.getFellow("item_name");
			String gold_item_name=text_name.getValue();
			Intbox purity_type=(Intbox)this.getFellow("item_purity");
			int purity=purity_type.getValue();
			Decimalbox market_deci=(Decimalbox)this.getFellow("item_market_rate");
			BigDecimal decimal=market_deci.getValue();
			double market_rate =decimal .doubleValue();
			
			Decimalbox lending_deci=(Decimalbox)this.getFellow("item_lending_rate");
			BigDecimal decimal1=lending_deci.getValue();
			double lending_rate=decimal1.doubleValue();
			int gold_item_id=gold_item.add_gold_item(gold_item_name,purity, market_rate, lending_rate);
			if(gold_item_id>0){
				showNotify("Succesufully Item Added","info",loan_item);
			onCreate();
			Textbox text_name1=(Textbox)this.getFellow("item_name");
			text_name1.setValue(null);
			Intbox purity_type1=(Intbox)this.getFellow("item_purity");
			purity_type1.setValue(null);
			Decimalbox market_deci1=(Decimalbox)this.getFellow("item_market_rate");
			market_deci1.setRawValue(null);
			Decimalbox lending_deci1=(Decimalbox)this.getFellow("item_lending_rate");
			lending_deci1.setRawValue(null);
			}else{
				showNotify("Please Try Again","error",loan_item);
			}
	   }catch(NullPointerException ne){//Catching Null pointer Exception
		   }
	   
		
	}
	private void showNotify(String msg,String type, Component ref) {
        Clients.showNotification(msg, type, ref, "middle_center", 2000);
    }
	
	public void get_editDetails(String id){
		int item_id=Integer.parseInt(id);
		gm=new GoldModel();
		gm=gold_item.get_Golditem(item_id);
		Textbox text_name1=(Textbox)this.getFellow("item_name");
		text_name1.setValue(gm.getGold_item_name());
		Intbox purity_type1=(Intbox)this.getFellow("item_purity");
		purity_type1.setValue(gm.getGold_item_purity());
		Decimalbox market_deci1=(Decimalbox)this.getFellow("item_market_rate");
		market_deci1.setRawValue(new BigDecimal(gm.getGold_item_market_rate()));
		Decimalbox lending_deci1=(Decimalbox)this.getFellow("item_lending_rate");
		lending_deci1.setRawValue(new BigDecimal(gm.getGold_item_lending_rate()));
		Button button1=(Button)this.getFellow("action");
		Button button2=(Button)this.getFellow("action2");
		button1.setVisible(false);
		button2.setVisible(true);
	}
	
	public void save_details(){
		
		Button button1=(Button)this.getFellow("action");
		Button button2=(Button)this.getFellow("action2");
		Textbox text_name1=(Textbox)this.getFellow("item_name");
		Intbox purity_type1=(Intbox)this.getFellow("item_purity");
		Decimalbox market_deci1=(Decimalbox)this.getFellow("item_market_rate");
		Decimalbox lending_deci1=(Decimalbox)this.getFellow("item_lending_rate");
		gm.setGold_item_name(text_name1.getValue());
		gm.setGold_item_purity(purity_type1.getValue());
		gm.setGold_item_market_rate(market_deci1.getValue().doubleValue());
		gm.setGold_item_lending_rate(lending_deci1.getValue().doubleValue());
		int n=gold_item.update_itemDetails(gm);
		Tablelayout tablediv=(Tablelayout)this.getFellow("item_table");
		if(n>0){
			 Clients.showNotification("Updated", "info", tablediv, "end_center", 2000);
			 button1.setVisible(true);
			 button2.setVisible(false);
			 text_name1.setValue(null);
			 purity_type1.setValue(null);
			 market_deci1.setRawValue(null);
			 lending_deci1.setRawValue(null);
			 onCreate();
		}else{
			Clients.showNotification("Smoething Wrong", "error", tablediv, "end_center", 2000);
		}
	}
	}
	

