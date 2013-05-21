package com.IcmcFileTracker.Components;

import com.IcmcFileTracker.model.Tracer;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class TracerHistory extends CustomComponent implements View, ClickListener{

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Table table;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;
	@AutoGenerated
	private Button send;
	//private TextField fileid;
	@AutoGenerated
	private Label label_1;
	private static final long serialVersionUID = 1L;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	
	public static final String VIEW_NAME ="FileHistory";
	
	final TextField fileid =  new TextField("FileID", new MethodProperty<String>(this, "id"));
	
	private String id = null;
	
	public TracerHistory() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		send.addClickListener(this);
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setCaption("File History");
		//mainLayout.set�mmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		mainLayout.addComponent(horizontalLayout_1);
		
		// table_1
		table = new TracerTable();
		//table_1.set�mmediate(false);
		table.setWidth("-1px");
		table.setHeight("-1px");
		//table_1.set�nvalidAllowed(false);
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
		horizontalLayout_1.setMargin(false);
		horizontalLayout_1.setSpacing(true);
		
		// label_1
		label_1 = new Label();
		//label_1.set�mmediate(false);
		label_1.setWidth("-1px");
		label_1.setHeight("-1px");
		label_1.setValue("File ID");
		horizontalLayout_1.addComponent(label_1);
		
		// fileid
		//fileid = new TextField();
		//fileid.set�mmediate(false);
		fileid.setWidth("-1px");
		fileid.setHeight("-1px");
		//fileid.set�nvalidAllowed(false);
		horizontalLayout_1.addComponent(fileid);
		
		// send
		send = new Button();
		send.setCaption("Find");
		//send.setImmediate(true);
		send.setWidth("-1px");
		send.setHeight("-1px");
		//send.set�nvalidAllowed(false);
		horizontalLayout_1.addComponent(send);
		
		return horizontalLayout_1;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		update();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		update();
	}
	
	private void  update(){
		//String id = fileid.getValue();
		
		if(id!=null && !id.isEmpty()){
			
			table.removeAllItems();
			for(Tracer t : Tracer.getByFileId(id)){
				table.addItem(t);
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
