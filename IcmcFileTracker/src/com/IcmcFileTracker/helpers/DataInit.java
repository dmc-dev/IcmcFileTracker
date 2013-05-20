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
	
		Role r1 = new Role();
		r1.setName("admin");
		r1.setLevel(0);
		r1.setActive(true);
		
		Role r2 = new Role();
		r2.setName("user");
		r2.setLevel(1);
		r2.setActive(true);
		
		User u1 = new User();
        u1.setUserName("danny");
        u1.setPassword("123");
        u1.setActive(true);
        u1.setRole(r1);
          
        User u2 = new User();
        u2.setUserName("fred");
        u2.setPassword("123");
        u2.setActive(true);
        u2.setRole(r2);
          
        Department d1 = new Department();
        d1.setName("istanbul");
        d1.setActive(true);
          

        Department d2 = new Department();
        d2.setName("istanbul");
        d2.setActive(true);
          
        d1.persist();
        d2.persist();
          
        u1.persist();
        u2.persist();	
	}
}
