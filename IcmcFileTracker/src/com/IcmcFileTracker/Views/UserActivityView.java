package com.IcmcFileTracker.Views;

import com.IcmcFileTracker.Components.TracerTable;
import com.IcmcFileTracker.model.User;
import com.IcmcFileTracker.model.Tracer;
import com.IcmcFileTracker.model.TracerHead;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Component.Listener;

public class UserActivityView extends CustomComponent implements View, Listener{

	public static final String VIEW_NAME = "UserActivity";
	
	private VerticalLayout layout = new VerticalLayout();

	
	private Label title = new Label(VIEW_NAME);
		
	private ComboBox users = new ComboBox();
	
	private MyView myView;
	
	private User me;
	
	public UserActivityView(User me){
		this.me = me;
		myView = new MyView(me);
		
		users.setCaption("All Users");
		users.setNullSelectionAllowed(false);
		users.setImmediate(true);
		users.addListener(this);
		
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.addComponent(title);
		layout.addComponent(users);
		layout.addComponent(myView);
	
		setCompositionRoot(layout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		//users.removeListener(this);
		users.removeAllItems();
		
		User last = null;
		for(User u: User.getAll()){
			users.addItem(u);
			last=u;
		}
		
		users.select(last);
		//myView.setUser(last);
		
		//users.addListener(this);
	}

	@Override
	public void componentEvent(Event event) {
		User u = (User)users.getValue();
		myView.setUser(u);
	}
}
