package com.IcmcFileTracker.Components;

import java.util.List;

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
		setSelectable(true);
	} 
	
	public TracerTable(String caption){
		super(caption);
		setContainerDataSource(beans);
		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);
		setVisibleColumns(Tracer.getVisableColumes());
		setSelectable(true);
	}
	
	public void replaceAllItems(List list){
		removeAllItems();
		for(Object o : list){
			addItem(o);
		}
	}
}
