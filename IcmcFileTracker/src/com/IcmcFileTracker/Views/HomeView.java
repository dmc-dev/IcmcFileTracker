package com.IcmcFileTracker.Views;

import com.IcmcFileTracker.Components.TracerHeadHistory;
import com.IcmcFileTracker.Components.TracerHeadWarning;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

public class HomeView extends CustomComponent  implements View {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private static final long serialVersionUID = 1L;
	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private HorizontalSplitPanel verticalSplitPanel_1;
	@AutoGenerated
	private TracerHeadWarning warn;
	@AutoGenerated
	private TracerHeadHistory history;
	
	
	public static final String VIEW_NAME = "Home";
	
	public HomeView() {
		
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// verticalSplitPanel_1
		verticalSplitPanel_1 = buildVerticalSplitPanel_1();
		mainLayout.addComponent(verticalSplitPanel_1);
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalSplitPanel buildVerticalSplitPanel_1() {
		// common part: create layout
		verticalSplitPanel_1 = new HorizontalSplitPanel();
		verticalSplitPanel_1.setCaption(this.VIEW_NAME);
		verticalSplitPanel_1.setImmediate(false);
		verticalSplitPanel_1.setWidth("100.0%");
		verticalSplitPanel_1.setHeight("100.0%");
		
		// trackerHistory_1
		history = new TracerHeadHistory();
		history.setImmediate(false);
		history.setWidth("100.0%");
		history.setHeight("100.0%");
		verticalSplitPanel_1.addComponent(history);
		
		// trackerWarning_1
		warn = new TracerHeadWarning();
		warn.setImmediate(false);
		warn.setWidth("100.0%");
		warn.setHeight("100.0%");
		verticalSplitPanel_1.addComponent(warn);
		
		return verticalSplitPanel_1;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		warn.enter(event);
		history.enter(event);
	}

}
