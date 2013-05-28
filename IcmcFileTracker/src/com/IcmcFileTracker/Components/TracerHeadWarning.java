package com.IcmcFileTracker.Components;

import java.util.List;

import com.IcmcFileTracker.helpers.InOutObj;
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
	private NativeSelect state;
	@AutoGenerated
	private Label label_1;
	private static final long serialVersionUID = 1L;
	
	public static final String VIEW_NAME ="TrackerWarnings";
	
	private static final InOutObj in = new InOutObj(InOutObj.IN);
	private static final InOutObj out = new InOutObj(InOutObj.OUT);
	
	private static final String ALL = "All";
	
	public TracerHeadWarning() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		state.addItem(in);
		state.addItem(out);
		state.select(out);
		
		days.setNullSelectionAllowed(false);
		department.setNullSelectionAllowed(false);
		state.setNullSelectionAllowed(false);
		
		Object selected = new Integer(7);
		days.addItem(selected) ;
		days.addItem(new Integer(14));
		days.addItem(new Integer(28));
		days.addItem(new Integer(56));
		days.addItem(new Integer(90));
		
		days.select(selected);
		
		days.addListener(this);
		state.addListener(this);
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
		
		boolean bool = ( (InOutObj)state.getValue() ).getValue();
		
		List l;
		
		if(department.getValue().equals(ALL)){
			l = TracerHead.getOld(bool, dayInt);
		}else{
			l = TracerHead.getOld(bool, dayInt, (Department)(department.getValue()));
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
		//mainLayout.set�mmediate(false);
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
		//horizontalLayout_1.set�mmediate(false);
		horizontalLayout_1.setWidth("-1px");
		horizontalLayout_1.setHeight("-1px");
		horizontalLayout_1.setMargin(true);
		horizontalLayout_1.setSpacing(true);
		
		// label_1
		label_1 = new Label();
		//label_1.set�mmediate(false);
		label_1.setWidth("-1px");
		label_1.setHeight("-1px");
		label_1.setValue("Files Checked");
		horizontalLayout_1.addComponent(label_1);
		
		// state
		state = new NativeSelect();
		state.setImmediate(true);
		state.setWidth("-1px");
		state.setHeight("-1px");
		//state.set�nvalidAllowed(false);
		horizontalLayout_1.addComponent(state);
		
		// toFromLabel
		toFromLabel = new Label();
		//toFromLabel.set�mmediate(false);
		toFromLabel.setWidth("-1px");
		toFromLabel.setHeight("-1px");
		toFromLabel.setValue("to Department");
		horizontalLayout_1.addComponent(toFromLabel);
		
		// department
		department = new NativeSelect();
		department.setImmediate(true);
		department.setWidth("-1px");
		department.setHeight("-1px");
		//department.set�nvalidAllowed(false);
		horizontalLayout_1.addComponent(department);
		
		// label_3
		label_3 = new Label();
		//label_3.set�mmediate(false);
		label_3.setWidth("-1px");
		label_3.setHeight("-1px");
		label_3.setValue("for more than");
		horizontalLayout_1.addComponent(label_3);
		
		// days
		days = new NativeSelect();
		days.setImmediate(true);
		days.setWidth("-1px");
		days.setHeight("-1px");
		//days.set�nvalidAllowed(false);
		horizontalLayout_1.addComponent(days);
		horizontalLayout_1.setComponentAlignment(days, new Alignment(9));
		
		// label_2
		label_2 = new Label();
		//label_2.set�mmediate(false);
		label_2.setWidth("-1px");
		label_2.setHeight("-1px");
		label_2.setValue("Days");
		horizontalLayout_1.addComponent(label_2);
		
		return horizontalLayout_1;
	}

	
}
