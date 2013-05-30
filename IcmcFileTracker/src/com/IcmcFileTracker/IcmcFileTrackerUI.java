package com.IcmcFileTracker;

import java.util.logging.Logger;

import com.IcmcFileTracker.Components.NavBar;
import com.IcmcFileTracker.Forms.LoginForm;
import com.IcmcFileTracker.Forms.LoginListener;
import com.IcmcFileTracker.Views.DepartmentView;
import com.IcmcFileTracker.Views.HomeView;
import com.IcmcFileTracker.Views.NewUserView;
import com.IcmcFileTracker.Views.UserActivityView;
import com.IcmcFileTracker.helpers.DataInit;
import com.IcmcFileTracker.model.*;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

//@Theme("chameleon")
@Theme("reindeer")
//@Theme("liferay")
//@Theme("runo")
@PreserveOnRefresh
public class IcmcFileTrackerUI extends UI implements ViewChangeListener, LoginListener{

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(IcmcFileTrackerUI.class.getName());

	private static final String TITLE = "File Tracker";
	
	final VerticalLayout layout = new VerticalLayout();
	
	private User user = null;
	private Navigator navigator;
	
	private Component navBar = null;
	private Panel contentpanel = new Panel();


	@Override
	protected void init(VaadinRequest request) {
		
		this.getPage().setTitle(TITLE);
		contentpanel.setSizeFull();
		layout.setMargin(true);
		layout.addComponent(contentpanel);
	
        navigator = new Navigator(this, contentpanel);
        navigator.addViewChangeListener(this);
        navigator.addView(LoginForm.VIEW_NAME, new LoginForm(this));
 	
		this.setContent(layout);	
	}

	@Override
	public void onLoginSuccess(User user){
		this.user=user;
		this.getPage().setTitle(TITLE+" "+user.getUserName()+" ("+user.getRole().getName()+")");
		
		if(navBar==null){
			navBar = new NavBar(navigator, user);
		}
		layout.addComponentAsFirst(navBar);
		navigator.navigateTo(HomeView.VIEW_NAME); 
	}
			
	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {
	
		if(event.getViewName().equals(LoginForm.VIEW_NAME)){
			
			user=null;
			
			if(navBar!=null){
				layout.removeComponent(navBar);
				navBar=null;
			}
			return true;
		}
		
		if(user==null || user.isActive()==false){
			Notification.show("NOT Authorized", Notification.Type.ERROR_MESSAGE);
			return false;
		}
		
		if(event.getViewName().equals(NewUserView.VIEW_NAME) || event.getViewName().equals(DepartmentView.VIEW_NAME) || event.getViewName().equals(UserActivityView.VIEW_NAME))
		{
			if(!user.getRole().isRole(Role.admin)){
				return false;
			}
		}
		
		return true;
	}

	@Override
	public void afterViewChange(ViewChangeEvent event) {
		
	}	

	
	public User getUser() {
		return user;
	}

	public Navigator getNavigator() {
		return navigator;
	}
}