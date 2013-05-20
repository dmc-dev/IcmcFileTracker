package com.IcmcFileTracker.helpers;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.IcmcFileTracker.model.Department;
import com.IcmcFileTracker.model.User;
import com.IcmcFileTracker.model.Role;


public class DataInit {

	private static final Logger log = Logger.getLogger(DataInit.class.getName());
	
	public DataInit(){
	
		log.info("running data init");
	
		  User u1 = new User();
          u1.setUserName("danny");
          u1.setPassword("123");
          u1.setActive(true);
          u1.setRole(Role.findByName("admin"));
          
          User u2 = new User();
          u2.setUserName("fred");
          u2.setPassword("123");
          u2.setActive(true);
          u2.setRole(Role.findByName("user"));
          
          u1.persist();
          u2.persist();
		
	}
}
