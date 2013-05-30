package com.IcmcFileTracker.Components;

import java.util.List;


import com.IcmcFileTracker.model.Department;
import com.IcmcFileTracker.model.TracerHead;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component.Listener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class TracerHeadWarning extends CustomComponent implements View, Listener{

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private TracerTable table;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;
	@AutoGenerated
	private Label label_2;
	@AutoGenerated
	private NativeSelect days;
	@AutoGenerated
	private Label label_3;
	@AutoGenerated
	private NativeSelect department;
	@AutoGenerated
	private Label toFromLabel;
	@AutoGenerated
	private Label label_1;
	
	private MaxResults max = new MaxResults();
	
	private static final long serialVersionUID = 1L;
	
	public static final String VIEW_NAME ="TrackerWarnings";
	
	
	private static final String ALL = "All";
	
	public TracerHeadWarning() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		days.setNullSelectionAllowed(false);
		department.setNullSelectionAllowed(false);
		
		Object selected = new Integer(7);
		days.addItem(selected) ;
		days.addItem(new Integer(14));
		days.addItem(new Integer(28));
		days.addItem(new Integer(90));
		days.addItem(new Integer(180));
		days.addItem(new Integer(365));
		
		days.select(selected);
		
		days.addListener(this);
		department.addListener(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		initDepartment();
	}
	
	@Override
	public void componentEvent(Event event) {
		
		getWarnings();
	}
	
	@SuppressWarnings("rawtypes")
	private void getWarnings() {
		
		int dayInt = ((Integer)days.getValue()).intValue();
			
		List l;
		if(department.getValue().equals(ALL)){
			l = TracerHead.getOld(dayInt);
		}else{
			l = TracerHead.getOld(dayInt, (Department)(department.getValue()));
		}
		
		table.setCaption(l.size()+" Trackers older than "+dayInt+" days");
		
		
		table.replaceAllItems(l);
	}

	private void initDepartment() {
		
		department.removeListener(this);
		department.removeAllItems();
		
		for(Department d : Department.getAllActive() ){
			department.addItem(d);
		}
		department.addItem(ALL);

		department.addListener(this);
		department.select(ALL);
	}
	
	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setWidth("100.0%");
		mainLayout.setHeight("100.0%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1);
		
		// warning_links
		table = new TracerTable();
		table.setWidth("100%");
		table.setHeight("100%");
		mainLayout.addComponent(table);
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setMargin(true);
		horizontalLayout_1.setSpacing(true);
		
	
		// toFromLabel
		toFromLabel = new Label();
		toFromLabel.setValue("Files Checked in to Department");
		horizontalLayout_1.addComponent(toFromLabel);
		
		// department
		department = new NativeSelect();
		department.setImmediate(true);
		horizontalLayout_1.addComponent(department);
		
		// label_3
		label_3 = new Label();
		label_3.setValue("for more than");
		horizontalLayout_1.addComponent(label_3);
		
		// days
		days = new NativeSelect();
		days.setImmediate(true);
		horizontalLayout_1.addComponent(days);
		horizontalLayout_1.setComponentAlignment(days, new Alignment(9));
		
		// label_2
		label_2 = new Label();
		label_2.setValue("Days");
		horizontalLayout_1.addComponent(label_2);
		
		horizontalLayout_1.addComponent(max);
		
		return horizontalLayout_1;
	}

	
}
