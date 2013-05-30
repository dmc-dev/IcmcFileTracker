package com.IcmcFileTracker.Components;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class MaxResults extends CustomComponent{
	private static final long serialVersionUID = 1L;

	private HorizontalLayout horizontalLayout = new HorizontalLayout();
	
	private Label last = new Label("Max Results");
	
	private ComboBox number = new ComboBox();
	

	public MaxResults(){
		Object selected = new Integer(15);
		number.addItem(selected) ;
		number.addItem(new Integer(30));
		number.addItem(new Integer(75));
		number.addItem(new Integer(250));
		number.addItem(new Integer(500));
		number.select(selected);
		number.setNullSelectionAllowed(false);
		number.setImmediate(true);
		
		horizontalLayout.setSpacing(true);
		horizontalLayout.addComponent(last);
		horizontalLayout.addComponent(number);
		
		setCompositionRoot(horizontalLayout);
	}
	
	public void addListener(Listener listener){
		number.addListener(listener);
	}
	
	public int getValue(){
		return (Integer) number.getValue();
	}
}
