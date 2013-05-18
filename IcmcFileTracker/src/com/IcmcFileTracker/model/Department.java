package com.IcmcFileTracker.model;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Nonnull;
import javax.persistence.*;

import com.IcmcFileTracker.helpers.EMF;
import com.google.appengine.api.datastore.Key;

@Entity
public class Department implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Key id;
	
	@Nonnull
	private String name;

	private boolean active=true;
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	public static List<Department> getAll(){
		
	    EntityManager em = EMF.getEntityManager();
	   
	    Query q = em.createQuery("SELECT d FROM Department d", Department.class);
	   // Query q = em.createQuery("SELECT d FROM Department d WHERE d.active = :active", Department.class);
	   // q.setParameter("active", true);
		return (List<Department>) q.getResultList();
	}
	
}