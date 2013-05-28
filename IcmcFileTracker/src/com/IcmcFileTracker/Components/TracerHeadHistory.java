package com.IcmcFileTracker.Components;

import com.IcmcFileTracker.model.Tracer;
import com.IcmcFileTracker.model.TracerHead;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Component.Listener;

public class TracerHeadHistory extends CustomComponent implements View, Listener{

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	
	private static final long serialVersionUID = 1L;
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private TracerTable table;
	
	private HorizontalLayout horizontalLayout = new HorizontalLayout();
	
	private Label last = new Label("Last");
	
	private NativeSelect number = new NativeSelect();
	
	
	public static final String VIEW_NAME ="TracerHeadHistory";
	
	
	public TracerHeadHistory() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		Object selected = new Integer(15);
		number.addItem(selected) ;
		number.addItem(new Integer(30));
		number.addItem(new Integer(75));
		number.addItem(new Integer(250));
		number.addItem(new Integer(500));
		number.addListener(this);
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		//mainLayout.set�mmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		
		mainLayout.addComponent(horizontalLayout);
		horizontalLayout.addComponent(last);
		horizontalLayout.addComponent(number);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// table
		table = new TracerTable();
		table.setCaption("Recent Tracker History");
		//table.set�mmediate(false);
		table.setWidth("-1px");
		table.setHeight("-1px");
		//table.set�nvalidAllowed(false);
		mainLayout.addComponent(table);
		
		return mainLayout;
	}

	@Override
	public void enter(ViewChangeEvent event) {
	
		Integer n = (Integer) number.getValue();
		table.replaceAllItems(TracerHead.getLatest(n.intValue()));
	}

	@Override
	public void componentEvent(Event event) {
		
		Integer n = (Integer) number.getValue();
		table.replaceAllItems(TracerHead.getLatest(n.intValue()));
	}

}
