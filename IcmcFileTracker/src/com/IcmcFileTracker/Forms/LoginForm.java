package com.IcmcFileTracker.Forms;

import java.util.logging.Logger;

import com.IcmcFileTracker.model.User;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LoginForm extends CustomComponent implements ClickListener, View{

	private static final Logger log = Logger.getLogger(LoginForm.class.getName());
	
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "";
	
	private final VerticalLayout layout = new VerticalLayout();
	private final TextField userName = new TextField();
	private final PasswordField passWord = new PasswordField();
	private final Button login = new Button("Login");

	private LoginListener listener=null;
	
	public LoginForm(LoginListener listener) {
		setCompositionRoot(layout);
		
		this.listener = listener;
		
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.addComponent(new Label("UserName"));
		layout.addComponent(userName);
		layout.addComponent(new Label("Password"));
		layout.addComponent(passWord);
		layout.addComponent(login);
		
		passWord.setRequired(true);
		passWord.addValidator(new PasswordValidator());
		
		
		passWord.addShortcutListener(new ShortcutListener("", ShortcutAction.KeyCode.ENTER, null){
			private static final long serialVersionUID = 1L;
			public void handleAction(Object sender, Object target) {
				doLogin();
			}
		});
		
		login.addClickListener(this);
	}

	public void buttonClick(ClickEvent event) {
		doLogin();
	}
	
	public void doLogin(){
		
		String username = userName.getValue();
		if(username==null  || username.isEmpty())
			return;
		
		
		if(!passWord.isValid())
			return;
					
		String password = passWord.getValue();
					
		User u = User.find(username);
        
        if(u!=null && u.isActive() && u.getPassword().equals(password)){
	      	listener.onLoginSuccess(u);
        }
        else{
        	Notification.show("Invalid Username OR Password", Notification.Type.ERROR_MESSAGE);
        }
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
		Notification.show("Welcome");
	}
	
	
	
	private static final class PasswordValidator extends AbstractValidator<String> {
		
		private static final long serialVersionUID = 1L;

		public PasswordValidator() {
		     super("The password provided is not valid");
		 }
		
		 @Override
		 protected boolean isValidValue(String value) {
		     
		     if (value == null)
		    	 return false;
		     
		     if(value.length() < 3)
		         return false;
		     
		     //not 1 number
		     //if(value.matches(".*\\d.*")
		     
		     return true;
		 }
		
		 @Override
		 public Class<String> getType() {
		     return String.class;
		 }
	}
}