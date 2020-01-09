package com.pennantExamination.controller;


import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;
 
public class EditTestSegmentController extends Window {
    private static final long serialVersionUID = 1L;
    
 @Wire   
 private Window EditTestSegmentWin;
    
    
    
    @Listen("onClick = #EditTestSegment")
    public void showModal(Event e) {
        //create a window programmatically and use it as a modal dialog.
        Window window = (Window)Executions.createComponents(
                "/EditTestSegment.zul", null, null);
        window.doModal();
    }
    
    
    @Listen("onClick=#editClose")
    public void closeWindow()
    
    {
    	EditTestSegmentWin.detach();
    }
    
    
}