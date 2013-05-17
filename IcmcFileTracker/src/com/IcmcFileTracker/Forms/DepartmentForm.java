package com.IcmcFileTracker.Forms;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.IcmcFileTracker.helpers.DataInit;
import com.IcmcFileTracker.helpers.EMF;
import com.IcmcFileTracker.model.Department;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


public class DepartmentForm extends CustomComponent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	FormLayout layout = new FormLayout();
     	
    public DepartmentForm(Department department) {
    	setCompositionRoot(layout);
        
    	layout.setCaption(department.getClass().getName());
    	
        /*
        layout.setCaption(d.getClass().getName());
        
        BeanItem<Department> item = new BeanItem<Department>(d);
        
        BeanFieldGroup<Department> binder = new BeanFieldGroup<Department>(Department.class);
        binder.setItemDataSource(d);
        
        binder.bindMemberFields(this);
		*/
        
        BeanItem<Department> model = new BeanItem<Department>(department);
        
        FieldGroup fieldGroup = new BeanFieldGroup<Department>(Department.class);
        
        fieldGroup.setItemDataSource(model);

        // Loop through the properties, build fields for them and add the fields
        // to this UI
        for (Object propertyId : fieldGroup.getUnboundPropertyIds()) {
            layout.addComponent(fieldGroup.buildAndBind(propertyId));
        }
        
    }
}