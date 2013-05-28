package com.IcmcFileTracker.Views;

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
	private VerticalLayout verticalLayout_2;
	@AutoGenerated
	private Button save;
	@AutoGenerated
	private ComboBox role;
	@AutoGenerated
	private TextField password;
	@AutoGenerated
	private TextField userName;
	@AutoGenerated
	private Label label_1;
	@AutoGenerated
	private Table table_1;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	
	public static final String VIEW_NAME = "CreateUser";
	
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
		
		table_1.setContainerDataSource(beans);
		table_1.setSelectable(true);
		table_1.addValueChangeListener(this);
		
		save.addClickListener(this);
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
		table_1 = new Table();
		table_1.setCaption("All Users");
		//table_1.setImmediate(false);
		table_1.setWidth("-1px");
		table_1.setHeight("-1px");
		//table_1.set�nvalidAllowed(false);
		mainLayout.addComponent(table_1);
		
		// verticalLayout_2
		verticalLayout_2 = buildVerticalLayout_2();
		mainLayout.addComponent(verticalLayout_2);
		
		return mainLayout;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_2() {
		// common part: create layout
		verticalLayout_2 = new VerticalLayout();
		//verticalLayout_2.setImmediate(true);
		verticalLayout_2.setWidth("-1px");
		verticalLayout_2.setHeight("-1px");
		verticalLayout_2.setMargin(true);
		verticalLayout_2.setSpacing(true);
		
		// label_1
		label_1 = new Label();
		//label_1.setImmediate(false);
		label_1.setWidth("-1px");
		label_1.setHeight("-1px");
		label_1.setValue("New User");
		verticalLayout_2.addComponent(label_1);
		
		// userName
		userName = new TextField();
		userName.setCaption("UserName");
		//userName.setImmediate(true);
		//userName.setBuffered(false);
		userName.setWidth("-1px");
		userName.setHeight("-1px");
		//userName.set�nvalidAllowed(false);
		verticalLayout_2.addComponent(userName);
		
		// password
		password = new TextField();
		password.setCaption("Password");
		//password.setImmediate(true);
		//password.setBuffered(false);
		password.setWidth("-1px");
		password.setHeight("-1px");
		//password.set�nvalidAllowed(false);
		verticalLayout_2.addComponent(password);
		
		// role
		role = new ComboBox();
		role.setCaption("Role");
		//role.setImmediate(false);
		role.setWidth("-1px");
		role.setHeight("-1px");
		//role.set�nvalidAllowed(false);
		verticalLayout_2.addComponent(role);
		
		// save
		save = new Button();
		save.setCaption("Save");
		//save.setImmediate(false);
		save.setWidth("-1px");
		save.setHeight("-1px");
		//save.set�nvalidAllowed(false);
		verticalLayout_2.addComponent(save);
		
		return verticalLayout_2;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
		beans.removeAllItems();
		for(User u: User.getAll()){
			beans.addItem(u);
		}
	}


	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		user.setPassword(password.getValue());
		//user.setPassword("abc");
		user.persist();
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		
		user = (User) event.getProperty().getValue();
		if(user!=null){
			userName.setValue(user.getUserName());
			userName.setEnabled(false);	
			password.setValue(user.getPassword());
			password.markAsDirty();
			
			//userName.setprova
		}
		
	}

}
