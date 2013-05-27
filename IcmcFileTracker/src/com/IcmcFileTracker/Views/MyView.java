package com.IcmcFileTracker.Views;

import com.IcmcFileTracker.Components.TracerTable;
import com.IcmcFileTracker.model.User;
import com.IcmcFileTracker.model.Tracer;
import com.IcmcFileTracker.model.TracerHead;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

public class MyView extends CustomComponent implements View{

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private HorizontalSplitPanel verticalSplitPanel_2;
	@AutoGenerated
	private TracerTable all;
	@AutoGenerated
	private TracerTable owned;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	
	private final User user;
	
	public MyView(User user) {
		this.user=user;
		
		buildMainLayout();
		setCompositionRoot(mainLayout);

		//owned.setCaption(user.getUserName()+" Has Last Commit");
		owned.setVisibleColumns(Tracer.getVisableColumesNoUser());
		
		//all.setCaption(user.getUserName()+" All Commit");
		all.setVisibleColumns(Tracer.getVisableColumesNoUser());
	}

	@Override
	public void enter(ViewChangeEvent event) {
		owned.replaceAllItems(TracerHead.getByUser(user));
		all.replaceAllItems(Tracer.getByUser(user));
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		mainLayout.addComponent(new Label(user.getUserName()) );
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// verticalSplitPanel_2
		verticalSplitPanel_2 = buildVerticalSplitPanel_2();
		mainLayout.addComponent(verticalSplitPanel_2);
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalSplitPanel buildVerticalSplitPanel_2() {
		// common part: create layout
		verticalSplitPanel_2 = new HorizontalSplitPanel();
		verticalSplitPanel_2.setWidth("100.0%");
		verticalSplitPanel_2.setHeight("100.0%");
		
		
		
		// table_1
		owned = new TracerTable();
		//owned.setWidth("-1px");
		//owned.setHeight("-1px");
		//verticalSplitPanel_2.addComponent(owned);
		
		VerticalLayout lhs = new VerticalLayout();
		lhs.addComponent(new Label("Trackers Owned by "+user.getUserName()));
		lhs.addComponent(owned);
		verticalSplitPanel_2.addComponent(lhs);
		
		// table_2
		all = new TracerTable();
		//all.setWidth("-1px");
		//all.setHeight("-1px");
		//verticalSplitPanel_2.addComponent(all);
		
		VerticalLayout rhs = new VerticalLayout();
		rhs.setMargin(true);
		rhs.addComponent(new Label("All Tracker Hystory by "+user.getUserName()));
		rhs.addComponent(all);
		verticalSplitPanel_2.addComponent(rhs);
		
		return verticalSplitPanel_2;
	}

}
