package com.IcmcFileTracker.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Query;

import com.IcmcFileTracker.helpers.EMF;
import com.google.appengine.datanucleus.annotations.Unowned;

@Entity
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id   
	private String userName;
	private String password;	
	private boolean active;

	@Unowned
	@OneToOne(cascade = CascadeType.ALL)
	private Role role;
		
	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	
	@Override
	public String toString() {
		return userName;
	}

	
	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void persist(){
	    EntityManager em = EMF.getEntityManager();
	    EntityTransaction et = em.getTransaction();
	    if(!et.isActive()){
	    	et.begin();
	    }
	    em.persist(this);
	    et.commit();
	}
	
	public void merge(){
		EntityManager em = EMF.getEntityManager();
	    EntityTransaction et = em.getTransaction();
	    if(!et.isActive()){
	    	et.begin();
	    }
	    em.merge(this);
	    et.commit();
	}
	
	public static User find(String userName){
		EntityManager em = EMF.getEntityManager();
	    return em.find(User.class, userName);
	}
	
	@SuppressWarnings("unchecked")
	public static List<User> getAll(){
	    EntityManager em = EMF.getEntityManager();
	    Query q = em.createQuery("SELECT u FROM User u", User.class);
		return (List<User>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<User> getAllActive(){
		
	    EntityManager em = EMF.getEntityManager();
	    Query q = em.createQuery("SELECT u FROM User u WHERE u.active = :active", User.class);
	    q.setParameter("active", true);
		return (List<User>) q.getResultList();
	}
}