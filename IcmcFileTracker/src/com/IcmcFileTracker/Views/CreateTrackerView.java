package com.IcmcFileTracker.Views;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.IcmcFileTracker.Components.TracerTable;
import com.IcmcFileTracker.model.Department;
import com.IcmcFileTracker.model.Tracer;
import com.IcmcFileTracker.model.TracerHead;
import com.IcmcFileTracker.model.User;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class CreateTrackerView extends CustomComponent implements View{
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private TracerTable table;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;
	@AutoGenerated
	private TextArea comment;
	@AutoGenerated
	private ComboBox department;
	@AutoGenerated
	private Label inOutGroup;
	@AutoGenerated
	private TextArea fileID;
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	public static final String VIEW_NAME = "Track";
	private static final Logger log = Logger.getLogger(CreateTrackerView.class.getName());
	
	private User user=null;
	
	
	public CreateTrackerView(final User user) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		this.user=user;	
		
		
		
		department.setNullSelectionAllowed(false);
		
		fileID.addShortcutListener(new ShortcutListener("caption", ShortcutAction.KeyCode.ENTER, null){
			
			private static final long serialVersionUID = 1L;

			@Override
			public void handleAction(Object sender, Object target) {			
				enableUI(false);
				
				String input = fileID.getValue().trim();		
				String[] fileIDs = input.split("\n");		   
	
				for(String id : fileIDs){
					id=id.trim();
					if(!id.equals("")){
						process(id);
					}
				}
				
				fileID.setValue("");
				
				enableUI(true);
			}
			
			
			private void process(String fileid){
				enableUI(false);
				
				TracerHead head = TracerHead.find(fileid);
				Tracer trace = makeTracerFromUI();
				trace.setFileid(fileid);
				
				
				
				if(head!=null && head.getDepartment().same(trace.getDepartment())){
					Notification.show("File "+fileid+" all ready checked to "+head.getDepartment(), Notification.Type.WARNING_MESSAGE);
				}
				else{
					if(head==null){
						head = new TracerHead();	
						head.setFileid(fileid);
					}
					head.setTrace(trace);
					try{
						head.persist();
						table.addItemAt(0, trace);
					}catch(Exception e){
						Notification.show("Error saving Tracker "+fileid, Notification.Type.ERROR_MESSAGE);
						log.log(Level.WARNING, e.getMessage());
					}
				}
				enableUI(true);
			}
			
			
			public void enableUI(boolean enabled){
				department.setEnabled(enabled);
				comment.setEnabled(enabled);
				fileID.setEnabled(enabled);
			}
			
			public Tracer makeTracerFromUI(){
				Tracer trace = new Tracer();
				trace.setUser(user);
				trace.setDepartment((Department) department.getValue());
				trace.setComment(comment.getValue());
				trace.setDate(Calendar.getInstance().getTime());
				return trace;
			}
			
		});
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		department.removeAllItems();
		
		Department last=null;
		for(Department d : Department.getAllActive() ){
			department.addItem(d);
			last=d;
		}
		department.select(last);
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		mainLayout.addComponent(new Label(this.VIEW_NAME));
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1);
		
		// table
		table = new TracerTable();
		table.setCaption("Session History");
		table.setWidth("75.0%");
		table.setHeight("100.0%");
		table.setVisibleColumns(Tracer.getVisableColumesNoUser());
		mainLayout.addComponent(table);
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setWidth("-1px");
		horizontalLayout_1.setHeight("-1px");
		horizontalLayout_1.setSpacing(true);
		
		// fileID
		fileID = new TextArea();
		fileID.setCaption("File ID");
		fileID.setWidth("160px");
		fileID.setHeight("40px");
		horizontalLayout_1.addComponent(fileID);
		
		// inOutGroup
		inOutGroup = new Label("Check IN TO");
		//inOutGroup.setCaption("Check IN TO");
		inOutGroup.setWidth("80px");
		inOutGroup.setHeight("40px");
		horizontalLayout_1.addComponent(inOutGroup);
		
		// department
		department = new ComboBox();
		department.setCaption("Department");
		horizontalLayout_1.addComponent(department);
		
		// comment
		comment = new TextArea();
		comment.setCaption("Comment");
		comment.setWidth("160px");
		comment.setHeight("40px");
		horizontalLayout_1.addComponent(comment);
		
		return horizontalLayout_1;
	}

}
