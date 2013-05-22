package com.IcmcFileTracker.Views;

import com.IcmcFileTracker.Forms.GenericForm;
import com.IcmcFileTracker.model.Department;
import com.sun.xml.internal.ws.api.server.Container;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Container.ItemSetChangeEvent;
import com.vaadin.data.Container.ItemSetChangeListener;
import com.vaadin.data.Container.PropertySetChangeEvent;
import com.vaadin.data.Container.PropertySetChangeListener;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class DepartmentView extends CustomComponent implements View, ClickListener, ValueChangeListener{
	
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private CheckBox edit;

	@AutoGenerated
	private Table table;

	final Button newBean = new Button("New Bean");
	
	GenericForm form = new GenericForm<Department>(new Department());
	
	BeanItemContainer<Department> beans = new BeanItemContainer<Department>(Department.class);
	
	public static final String VIEW_NAME = "DepartmentView";
	
	public DepartmentView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		table.setContainerDataSource(beans);
		table.setColumnCollapsingAllowed(true);
		table.setColumnReorderingAllowed(true);
		table.setSelectable(true);
		table.addValueChangeListener(this);
		
			
	    form.setCaption("Edit Item");
	  
	    newBean.addClickListener(this);
	    
	    mainLayout.addComponent(newBean);
	    mainLayout.addComponent(form);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		beans.removeAllItems();
		for(Department d: Department.getAll()){
			beans.addItem(d);
		}
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		mainLayout = new VerticalLayout();
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");
	
		table = new Table();
		table.setCaption("Departments");
		table.setImmediate(true);
		table.setWidth("-1px");
		table.setHeight("-1px");
		mainLayout.addComponent(table);
		
		edit = new CheckBox("Edit Departments", new MethodProperty<Boolean>(table, "editable"));
		edit.setImmediate(false);
		edit.setWidth("-1px");
		edit.setHeight("-1px");
		mainLayout.addComponent(edit);
		
		return mainLayout;
	}

	

	@Override
	public void valueChange(ValueChangeEvent event) {
		
		Department d = (Department) event.getProperty().getValue();
		if(d!=null)
			form.setDataSource(d);
	}

	@Override
	public void buttonClick(ClickEvent event) {
        //newBean.setEnabled(false);
		
		Department d = new Department();
		table.select(beans.addItem(d));
        
     
        //form.setDataSource(d);
	}

}
