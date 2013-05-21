package com.IcmcFileTracker.Components;

import com.IcmcFileTracker.model.Tracer;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;

public class TracerTable extends Table {
	
	private static final long serialVersionUID = 1L;

	private BeanItemContainer<Tracer> beans = new BeanItemContainer<Tracer>(Tracer.class);
	
	public TracerTable(){
		
		setContainerDataSource(beans);
		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);
		setVisibleColumns(Tracer.getVisableColumes());
		
	} 
	
}
