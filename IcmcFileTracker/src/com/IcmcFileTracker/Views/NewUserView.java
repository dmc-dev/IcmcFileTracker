package com.IcmcFileTracker.Views;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.IcmcFileTracker.helpers.EMF;
import com.IcmcFileTracker.model.Role;
import com.IcmcFileTracker.model.User;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class NewUserView extends CustomComponent implements View, ClickListener, ValueChangeListener{

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private HorizontalLayout mainLayout;
	@AutoGenerated
	private VerticalLayout userData;
	@AutoGenerated
	private Button save;
	@AutoGenerated
	private ComboBox role;
	@AutoGenerated
	private TextField password;
	@AutoGenerated
	private TextField userName;
	@AutoGenerated
	private Label title;
	@AutoGenerated
	private Table table;
	
	private Button newUser = new Button("New User");
	CheckBox active = new CheckBox("Active");
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	
	public static final String VIEW_NAME = "User Admin";
	
	BeanItemContainer<User> beans = new BeanItemContainer<User>(User.class);
	
	private User user=null;
	
	public NewUserView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		
		Role last=null;
		for(Role r: Role.getAll()){
			role.addItem(r);
			last=r;
		}
		role.select(last);
		role.setNullSelectionAllowed(false);
		
		table.setContainerDataSource(beans);
		table.setSelectable(true);
		table.addValueChangeListener(this);
		
		save.addClickListener(this);
		newUser.addClickListener(this);
		// TODO add user code here
	}

	@AutoGenerated
	private HorizontalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalLayout();
		//mainLayout.setImmediate(true);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(true);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("100.0%");
		
		// table_1
		table = new Table();
		table.setCaption(VIEW_NAME);
		table.setImmediate(true);
		table.setWidth("-1px");
		table.setHeight("-1px");
		
		
		VerticalLayout v = new VerticalLayout();
		
		v.addComponent(table);
		v.addComponent(newUser);
		
		mainLayout.addComponent(v);
		
		
		
		// verticalLayout_2
		userData = buildVerticalLayout_2();
		mainLayout.addComponent(userData);
		
		return mainLayout;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_2() {
		// common part: create layout
		userData = new VerticalLayout();
		//verticalLayout_2.setImmediate(true);
		userData.setWidth("-1px");
		userData.setHeight("-1px");
		userData.setMargin(true);
		userData.setSpacing(true);
		
		// label_1
		title = new Label();
		title.setWidth("-1px");
		title.setHeight("-1px");
		title.setValue("New User");
		userData.addComponent(title);
		
		// userName
		userName = new TextField();
		userName.setCaption("UserName");
		//userName.setImmediate(true);
		//userName.setBuffered(false);
		userName.setWidth("-1px");
		userName.setHeight("-1px");
		//userName.set�nvalidAllowed(false);
		userData.addComponent(userName);
		
		userData.addComponent(active);
		
		// password
		password = new TextField();
		password.setCaption("Password");
		//password.setImmediate(true);
		//password.setBuffered(false);
		password.setWidth("-1px");
		password.setHeight("-1px");
		//password.set�nvalidAllowed(false);
		userData.addComponent(password);
		
		// role
		role = new ComboBox();
		role.setCaption("Role");
		//role.setImmediate(false);
		role.setWidth("-1px");
		role.setHeight("-1px");
		//role.set�nvalidAllowed(false);
		userData.addComponent(role);
		
		// save
		save = new Button();
		save.setCaption("Save");
		//save.setImmediate(false);
		save.setWidth("-1px");
		save.setHeight("-1px");
		//save.set�nvalidAllowed(false);
		userData.addComponent(save);
		
		return userData;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		userData.setVisible(false);
		beans.removeAllItems();
		for(User u: User.getAll()){
			beans.addItem(u);
		}
	}


	@Override
	public void buttonClick(ClickEvent event) {
		
		if(event.getSource().equals(newUser)){
			user = new User();
			userData.setVisible(true);
			title.setValue("New User");
			userName.setEnabled(true);
			userName.setVisible(true);
			active.setValue(user.isActive());
			userName.setValue("");
			password.setValue("");
			
			table.select(null);
			
			return;
		}
		
		user.setUserName(userName.getValue());
		user.setPassword(password.getValue());
		user.setRole((Role) role.getValue());
		user.setActive(active.getValue());
		
		user.merge();
	    
	    int idx = beans.indexOfId(user);
	    if(idx==-1){
	    	beans.addItem(user);
	    }else{
	    	beans.removeItem(user);
	    	beans.addItemAt(idx, user);
	    }
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		
		user = (User) event.getProperty().getValue();
		
		if(user!=null){
			userData.setVisible(true);
			title.setValue("edit User "+user.getUserName());
			userName.setValue(user.getUserName());
			userName.setEnabled(false);
			userName.setVisible(false);
			active.setValue(user.isActive());
			password.setValue(user.getPassword());
			
			for(Object r : role.getItemIds()){
				if(r.equals(user.getRole())){
					role.select(r);
				}
			}
				
		}
	}

}

