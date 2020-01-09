package com.pennantExamination.controller;


import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;
 
public class EditCityController extends Window {
    private static final long serialVersionUID = 1L;
    
 @Wire   
 private Window EditCityWin;
    
    
    
    @Listen("onClick = #EditCity")
    public void showModal(Event e) {
        //create a window programmatically and use it as a modal dialog.
        Window window = (Window)Executions.createComponents(
                "/EditCity.zul", null, null);
        window.doModal();
    }
    
    
    @Listen("onClick=#editClose")
    public void closeWindow()
    
    {
    	EditCityWin.detach();
    }
    
    
}