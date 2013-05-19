package com.IcmcFileTracker.Components;

import com.IcmcFileTracker.model.Tracer;
import com.IcmcFileTracker.model.TracerHead;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

public class MyView extends CustomComponent implements View{

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private VerticalSplitPanel verticalSplitPanel_2;
	@AutoGenerated
	private Table table_2;
	@AutoGenerated
	private Table table_1;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	
	BeanItemContainer<Tracer> beans1 = new BeanItemContainer<Tracer>(Tracer.class);
	BeanItemContainer<Tracer> beans2 = new BeanItemContainer<Tracer>(Tracer.class);
	
	
	public MyView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		table_1.setContainerDataSource(beans1);
		table_1.setColumnCollapsingAllowed(true);
		table_1.setColumnReorderingAllowed(true);
		
		table_2.setContainerDataSource(beans2);
		table_2.setColumnCollapsingAllowed(true);
		table_2.setColumnReorderingAllowed(true);
		
		
		// TODO add user code here
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
		beans1.removeAllItems();
		for(Tracer t : TracerHead.getAllLatestTracers()){
			beans1.addItem(t);
		}
		
		beans2.removeAllItems();
		for(Tracer t : TracerHead.getAllLatestTracers()){
			beans2.addItem(t);
		}
		
		
		
		
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
		
		// verticalSplitPanel_2
		verticalSplitPanel_2 = buildVerticalSplitPanel_2();
		mainLayout.addComponent(verticalSplitPanel_2);
		
		return mainLayout;
	}

	@AutoGenerated
	private VerticalSplitPanel buildVerticalSplitPanel_2() {
		// common part: create layout
		verticalSplitPanel_2 = new VerticalSplitPanel();
	//	verticalSplitPanel_2.set�mmediate(false);
		verticalSplitPanel_2.setWidth("-1px");
		verticalSplitPanel_2.setHeight("800px");
		
		// table_1
		table_1 = new Table();
	//	table_1.set�mmediate(false);
		table_1.setWidth("-1px");
		table_1.setHeight("-1px");
	//	table_1.set�nvalidAllowed(false);
		verticalSplitPanel_2.addComponent(table_1);
		
		// table_2
		table_2 = new Table();
	//	table_2.set�mmediate(false);
		table_2.setWidth("-1px");
		table_2.setHeight("-1px");
	//	table_2.set�nvalidAllowed(false);
		verticalSplitPanel_2.addComponent(table_2);
		
		return verticalSplitPanel_2;
	}

}
