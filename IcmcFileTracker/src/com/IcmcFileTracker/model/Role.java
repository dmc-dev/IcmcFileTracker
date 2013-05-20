package com.IcmcFileTracker.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.IcmcFileTracker.helpers.EMF;
import com.google.appengine.api.datastore.Key;

@Entity
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Key id;
	
	private String name;

	private int level;
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

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
	
	@SuppressWarnings("unchecked")
	public static List<Role> getAll(){
	    EntityManager em = EMF.getEntityManager();
	    Query q = em.createQuery("SELECT r FROM Role r", Role.class);
		return (List<Role>) q.getResultList();
	}
	
	public static Role findByName(String name){
	    EntityManager em = EMF.getEntityManager();	
		    
	    try{
		    Query q = em.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class);
		    q.setParameter("name", name);
		    return (Role) q.getSingleResult();
	    } catch (NoResultException e){
	    	return null;
	    }
	}
}