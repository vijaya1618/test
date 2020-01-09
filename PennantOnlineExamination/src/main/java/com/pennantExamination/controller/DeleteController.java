package com.pennantExamination.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;
 
public class DeleteController extends SelectorComposer<Component> {
    private static final long serialVersionUID = 1L;
    
 @Wire   
 private Window DeleteWin;
    
    
    
    @Listen("onClick = #Delete")
    public void showModal(Event e) {
        //create a window programmatically and use it as a modal dialog.
        Window window = (Window)Executions.createComponents(
                "/Delete.zul", null, null);
        window.doModal();
    }
    
    
    @Listen("onClick=#editClose")
    public void closeWindow()
    
    {
    	DeleteWin.detach();
    }
    
    
}