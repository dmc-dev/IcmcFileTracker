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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class DepartmentView extends CustomComponent implements View, ClickListener, ValueChangeListener{
	
	private static final long serialVersionUID = 1L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private HorizontalLayout mainLayout;


	@AutoGenerated
	private Table table;

	final Button newBean = new Button("New Department");
	
	final Button save = new Button("save");
	
	GenericForm form = new GenericForm<Department>(new Department());
	
	BeanItemContainer<Department> beans = new BeanItemContainer<Department>(Department.class);
	
	public static final String VIEW_NAME = "DepartmentView";
	
	private Department department=null;
	
	public DepartmentView() {
		
		mainLayout = new HorizontalLayout();
		mainLayout.addComponent(buildLayout());
		setCompositionRoot(mainLayout);

		table.setCaption(VIEW_NAME);
		table.setContainerDataSource(beans);
		table.setColumnCollapsingAllowed(true);
		table.setColumnReorderingAllowed(true);
		table.setSelectable(true);
		table.addValueChangeListener(this);
		table.setImmediate(true);
	    
	  
	    newBean.addClickListener(this);
	    save.addClickListener(this);
	    
	    mainLayout.addComponent(form);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		form.setVisible(false);
		
		beans.removeAllItems();
		for(Department d: Department.getAll()){
			beans.addItem(d);
		}
	}

	@AutoGenerated
	private VerticalLayout buildLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setWidth("100%");
		layout.setHeight("-1px");
		layout.setMargin(true);
		layout.setSpacing(true);
		
		setWidth("100.0%");
		setHeight("-1px");
	
		table = new Table();
		table.setCaption("Departments");
		table.setWidth("-1px");
		table.setHeight("-1px");
		layout.addComponent(table);
	    layout.addComponent(newBean);
	
		return layout;
	}

	

	@Override
	public void valueChange(ValueChangeEvent event) {
		
		department = (Department) event.getProperty().getValue();
		if(department!=null){
			form.setVisible(true);
			form.setDataSource(department);
			form.addComponent(save);
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {
        
		if(event.getSource().equals(save)){
			try{
				department.merge();
				int idx = beans.indexOfId(department);
				if(idx==-1){
				    beans.addItem(department);
				}else{
					beans.removeItem(department);
					beans.addItemAt(idx, department);
				}
			}catch(Exception e){
				form.setVisible(false);
			}
			return;
		}
		
		department = new Department();
		form.setVisible(true);     
        form.setDataSource(department);
        form.addComponent(save);
	}

}
