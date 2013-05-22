package com.IcmcFileTracker.Forms;

import com.IcmcFileTracker.helpers.EnhancedFieldGroupFieldFactory;
import com.IcmcFileTracker.model.Department;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;

public class GenericForm<T> extends CustomComponent {

	private static final long serialVersionUID = 1L;
    
	FormLayout layout = new FormLayout();
     	
    public GenericForm(T model) {
    	
    	setCompositionRoot(layout);
        
    	layout.addComponent(new Label(model.getClass().getSimpleName()));
    	
        BeanItem<T> bean = new BeanItem<T>(model);
        
        @SuppressWarnings("unchecked")
		FieldGroup fieldGroup = new BeanFieldGroup<T>((Class<T>) model.getClass());
        
        fieldGroup.setItemDataSource(bean);

        fieldGroup.setFieldFactory(new EnhancedFieldGroupFieldFactory());
        
        for (Object propertyId : fieldGroup.getUnboundPropertyIds()) {
            layout.addComponent(fieldGroup.buildAndBind(propertyId));
        }
        
    }
	
}
