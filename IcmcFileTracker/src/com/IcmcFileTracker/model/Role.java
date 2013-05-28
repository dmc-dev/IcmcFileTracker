package com.IcmcFileTracker.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.IcmcFileTracker.helpers.EMF;

@Entity
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String name;

	private int level;

	private boolean active=true;

	public static final String admin="admin";
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
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

	public boolean isRole(String role){
		return name.equals(role);
	}
	
	public boolean equals(Object obj){
		return name.equals(((Role)obj).name);
	}
	
	public void persist(){
	    EntityManager em = EMF.getEntityManager();
	    EntityTransaction et = em.getTransaction();
	    et.begin();
	    em.persist(this);
	    et.commit();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Role> getAll(){
	    EntityManager em = EMF.getEntityManager();
	    Query q = em.createQuery("SELECT r FROM Role r", Role.class);
		return (List<Role>) q.getResultList();
	}
	
	public static Role find(String name){
		EntityManager em = EMF.getEntityManager();
		return em.find(Role.class, name);
		
	}
}