package com.IcmcFileTracker.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.IcmcFileTracker.helpers.EMF;

@Entity
public class Department implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
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

	public void persist(){
	    EntityManager em = EMF.getEntityManager();
	    EntityTransaction et = em.getTransaction();
	    et.begin();
	    em.persist(this);
	    et.commit();
	}
	
	static Department find(String name){
		EntityManager em = EMF.getEntityManager();
		return em.find(Department.class, name);
	}

	@SuppressWarnings("unchecked")
	public static List<Department> getAll(){
	    EntityManager em = EMF.getEntityManager();
	    Query q = em.createQuery("SELECT d FROM Department d", Department.class);
		return (List<Department>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Department> getAllActive(){
		
	    EntityManager em = EMF.getEntityManager();
	    Query q = em.createQuery("SELECT d FROM Department d WHERE d.active = :active", Department.class);
	    q.setParameter("active", true);
		return (List<Department>) q.getResultList();
	}
}