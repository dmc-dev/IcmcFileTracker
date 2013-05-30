package com.IcmcFileTracker.Views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class ErrorView extends CustomComponent implements View{

	private static final long serialVersionUID = 1L;

	private VerticalLayout mainLayout = new VerticalLayout();
	
	public ErrorView(){
		
		setCompositionRoot(mainLayout);
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		Notification.show("Your Session has TIMED OUT close Browser Window / Tab and login again", Notification.Type.ERROR_MESSAGE);
	}

}
