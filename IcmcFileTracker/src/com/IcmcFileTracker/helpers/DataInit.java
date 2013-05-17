package com.IcmcFileTracker.helpers;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.IcmcFileTracker.model.Department;
import com.IcmcFileTracker.model.LocalUser;
import com.IcmcFileTracker.model.Role;


public class DataInit {

	private static final Logger log = Logger.getLogger(DataInit.class.getName());
	
	public DataInit(){
	
		log.info("running data init");
		
	   EntityManagerFactory emf = EMF.get();
       EntityManager em = null;
       
       try {
           em = emf.createEntityManager();
           
           Department d1 = new Department();
           d1.setName("Istanbul");
           d1.setActive(true);
           
           Department d2 = new Department();
           d2.setName("London");
           d2.setActive(true);
           
           Role admin = new Role();
           admin.setName("admin");
           admin.setLevel(0);
           admin.setActive(true);

           Role user = new Role();
           user.setName("user");
           user.setLevel(1);
           user.setActive(true);
           
           LocalUser u1 = new LocalUser();
           u1.setUserName("danny");
           u1.setPassword("123");
           u1.setActive(true);
           u1.setRole(admin);
           
           LocalUser u2 = new LocalUser();
           u2.setUserName("fred");
           u2.setPassword("123");
           u2.setActive(true);
           u2.setRole(user);
  
           
           EntityTransaction et = em.getTransaction();
           et.begin();
           	em.persist(d1);
           	em.persist(d2);
           et.commit();
           
           et = em.getTransaction();
           et.begin();
           	em.persist(u1);
           	em.persist(u2);
           et.commit();
           
       }finally{
           em.close();
       }   
	}
}
