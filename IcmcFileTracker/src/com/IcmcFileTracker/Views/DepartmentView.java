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
import com.vaadin.ui.Form;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class DepartmentView extends CustomComponent implements View, ClickListener, PropertySetChangeListener, ValueChangeListener{
	
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private CheckBox edit;

	@AutoGenerated
	private Table table;

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	
	final Button newBean = new Button("New Bean");
	
	final Form form = new Form();
	
	final GenericForm f = new GenericForm<Department>(new Department());
	
	BeanItemContainer<Department> beans = new BeanItemContainer<Department>(Department.class);
	
	public static final String VIEW_NAME = "DepartmentView";
	
	public DepartmentView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		table.setContainerDataSource(beans);
		table.setColumnCollapsingAllowed(true);
		table.setColumnReorderingAllowed(true);
		
		table.setSelectable(true);
		
		//edit = new CheckBox("Edit Departments", new MethodProperty<Boolean>(table, "editable"));
		//table.addItemSetChangeListener(this);
		//table.addPropertySetChangeListener(this);
		table.addValueChangeListener(this);
		
		
		
	    form.setCaption("Edit Item");
	    form.setVisible(false);
	    form.setBuffered(true);
		
	    f.setVisible(false);
	    
	    newBean.addClickListener(this);
	    
	    mainLayout.addComponent(newBean);
	    mainLayout.addComponent(f);
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
		// common part: create layout
		mainLayout = new VerticalLayout();
		//mainLayout.set�mmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");
		
		// table
		table = new Table();
		table.setCaption("Departments");
		table.setImmediate(true);
		table.setWidth("-1px");
		table.setHeight("-1px");
		//table.set�nvalidAllowed(false);
		mainLayout.addComponent(table);
		
		// edit
		edit = new CheckBox("Edit Departments", new MethodProperty<Boolean>(table, "editable"));
		//edit.setCaption("Edit Departments");
		edit.setImmediate(false);
		edit.setWidth("-1px");
		edit.setHeight("-1px");
		//edit.set�nvalidAllowed(false);
		mainLayout.addComponent(edit);
		
		return mainLayout;
	}

	/*
	@Override
	public void containerItemSetChange(ItemSetChangeEvent event) {
		// TODO Auto-generated method stub
		
		Notification.show(event.getContainer().toString(), Notification.Type.ERROR_MESSAGE);
	}
*/
	
	@Override
	public void containerPropertySetChange(PropertySetChangeEvent event) {
		// TODO Auto-generated method stub
		Notification.show(event.getContainer().toString(), Notification.Type.ERROR_MESSAGE);
		
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		
		form.setItemDataSource((table.getItem(table.getValue())));
		form.setVisible(true);
		
		Notification.show(event.getProperty().getValue().toString(), Notification.Type.ERROR_MESSAGE);
		Notification.show(table.getItem(table.getValue()).toString(), Notification.Type.ERROR_MESSAGE);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		
		// Create a new item; this will create a new bean
        Object itemId = beans.addItem(new Department());
       
        form.setItemDataSource(table.getItem(itemId));
 
        // The form was opened for editing a new item
        table.setData(itemId);
        
        //table.select(itemId);
        //table.setEnabled(false);
        newBean.setEnabled(false);
        
        f.setVisible(true);
		
	}

}
