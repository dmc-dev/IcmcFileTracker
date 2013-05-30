package com.IcmcFileTracker.Components;

import com.IcmcFileTracker.IcmcFileTrackerUI;
import com.IcmcFileTracker.Forms.LoginForm;
import com.IcmcFileTracker.Views.CreateTrackerView;
import com.IcmcFileTracker.Views.DepartmentView;
import com.IcmcFileTracker.Views.ErrorView;
import com.IcmcFileTracker.Views.FileHistoryView;
import com.IcmcFileTracker.Views.HomeView;
import com.IcmcFileTracker.Views.MyView;
import com.IcmcFileTracker.Views.NewUserView;
import com.IcmcFileTracker.Views.UserActivityView;
import com.IcmcFileTracker.model.Department;
import com.IcmcFileTracker.model.User;
import com.IcmcFileTracker.model.Role;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class NavBar extends CustomComponent{

	private final VerticalLayout layout = new VerticalLayout();
	
	private MenuBar navBar = new MenuBar();
	private User user;
	private Navigator navigator;
	private MenuItem logout;
	
	public NavBar(final Navigator navigator, final User user) {
		
		setCompositionRoot(layout);
		
		this.navigator=navigator;
		this.user=user;
			
		MenuBar.Command command = new MenuBar.Command() {	  
			private static final long serialVersionUID = 1L;

			public void menuSelected(MenuItem selected) {
				
				if(selected.equals(logout)){
					navigator.navigateTo(LoginForm.VIEW_NAME);
				}else{
					navigator.navigateTo(selected.getText());
				}
			}
		};
		
		navigator.setErrorView(new ErrorView());
		
		MenuItem home = navBar.addItem(HomeView.VIEW_NAME, null, command);
		navigator.addView(HomeView.VIEW_NAME, new HomeView());
	
		MenuItem track = navBar.addItem(CreateTrackerView.VIEW_NAME, null, command);
		navigator.addView(CreateTrackerView.VIEW_NAME, new CreateTrackerView(user));
		
		MenuItem fileView = navBar.addItem(FileHistoryView.VIEW_NAME, null, command);
		navigator.addView(FileHistoryView.VIEW_NAME, new FileHistoryView());
		
		MenuItem userView = navBar.addItem(user.getUserName(), null, command);
		navigator.addView(user.getUserName(), new MyView(user));
		
	
		if(user.getRole().isRole(Role.admin)){
			
			MenuItem admin = navBar.addItem("Admin", null, null);
		
			MenuItem create = admin.addItem(DepartmentView.VIEW_NAME, null, command);
			navigator.addView(DepartmentView.VIEW_NAME, new DepartmentView());
			
			MenuItem newUsers = admin.addItem(NewUserView.VIEW_NAME, null, command);
			navigator.addView(NewUserView.VIEW_NAME, new NewUserView());
			
			MenuItem userActivity = admin.addItem(UserActivityView.VIEW_NAME, null, command);
			navigator.addView(UserActivityView.VIEW_NAME, new UserActivityView(user));
		}
		
		logout = navBar.addItem("LogOut", null, command);
		
		navBar.setSizeFull();
		layout.addComponent(navBar);
	}
}