package com.IcmcFileTracker.Components;

import java.util.Calendar;
import java.util.logging.Logger;

import com.IcmcFileTracker.IcmcFileTrackerUI;
import com.IcmcFileTracker.helpers.InOutObj;
import com.IcmcFileTracker.model.Department;
import com.IcmcFileTracker.model.Tracer;
import com.IcmcFileTracker.model.TracerHead;
import com.IcmcFileTracker.model.User;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class CreateTracker extends CustomComponent implements View{
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
	private OptionGroup inOutGroup;
	@AutoGenerated
	private TextArea fileID;
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	public static final String VIEW_NAME = "Track";
	private static final Logger log = Logger.getLogger(CreateTracker.class.getName());
	
	private User user=null;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	
	public CreateTracker(final User user) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		this.user=user;	
		
		
		InOutObj in = new InOutObj(InOutObj.IN);
		inOutGroup.addItem(in);
		inOutGroup.addItem(new InOutObj(InOutObj.OUT));
		inOutGroup.select(in);
		
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
				
				if(head!=null && head.isCheckIN() == trace.isCheckIN()){
					Notification.show("File "+fileid+" all ready checked "+InOutObj.getString(head.isCheckIN()), Notification.Type.WARNING_MESSAGE);
				}
				else{
					if(head==null){
						head = new TracerHead();	
						head.setFileid(fileid);
					}
					head.setTrace(trace);
					head.persist();
					table.addItem(trace);
				}
				
				enableUI(true);
			}
			
			
			public void enableUI(boolean enabled){
				department.setEnabled(enabled);
				inOutGroup.setEnabled(enabled);
				comment.setEnabled(enabled);
				fileID.setEnabled(enabled);
			}
			
			public Tracer makeTracerFromUI(){
				Tracer trace = new Tracer();
				trace.setUser(user);
				trace.setDepartment((Department) department.getValue());
				trace.setCheckIN( ((InOutObj)inOutGroup.getValue()).getValue() );
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
		//mainLayout.set�mmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1);
		
		// table
		table = new TracerTable();
		table.setCaption("Check Out History ");
		//table.set�mmediate(false);
		table.setWidth("580px");
		table.setHeight("260px");
		//table.set�nvalidAllowed(false);
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
		
		// fileID
		fileID = new TextArea();
		fileID.setCaption("File ID");
		//fileID.set�mmediate(false);
		fileID.setWidth("160px");
		fileID.setHeight("40px");
		//fileID.set�nvalidAllowed(false);
		horizontalLayout_1.addComponent(fileID);
		
		// inOutGroup
		inOutGroup = new OptionGroup();
		inOutGroup.setCaption("Check IN / OUT");
		//inOutGroup.set�mmediate(false);
		inOutGroup.setWidth("80px");
		inOutGroup.setHeight("40px");
		//inOutGroup.set�nvalidAllowed(false);
		horizontalLayout_1.addComponent(inOutGroup);
		
		// department
		department = new ComboBox();
		department.setCaption("Department");
		//department.set�mmediate(false);
		department.setWidth("120px");
		department.setHeight("40px");
		//department.set�nvalidAllowed(false);
		horizontalLayout_1.addComponent(department);
		
		// comment
		comment = new TextArea();
		comment.setCaption("Comment");
		//comment.set�mmediate(false);
		comment.setWidth("160px");
		comment.setHeight("40px");
		//comment.set�nvalidAllowed(false);
		horizontalLayout_1.addComponent(comment);
		
		return horizontalLayout_1;
	}

}
