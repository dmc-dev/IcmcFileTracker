package com.IcmcFileTracker.model;

import javax.jdo.annotations.Persistent;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Basic;
import javax.persistence.Transient;

import com.IcmcFileTracker.helpers.EMF;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Tracer implements Serializable{
	private static final long serialVersionUID = 1L;

	//@Id
    //protected String id;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Key id;
	
	
	@Unowned
	protected LocalUser localUser;
	
	@Unowned
	protected Department department;
		   
	@Basic
	protected String comment;
	
	@Basic
	protected Date date;
	
	@Basic
	protected boolean checkIN;

	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	public LocalUser getLocalUser() {
		return localUser;
	}

	public void setLocalUser(LocalUser param) {
		this.localUser = param;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department param) {
		this.department = param;
	}

	public void setComment(String param) {
		this.comment = param;
	}

	public String getComment() {
		return comment;
	}

	public void setDate(Date param) {
		this.date = param;
	}

	public Date getDate() {
		return date;
	}

	public void setCheckIN(boolean param) {
		this.checkIN = param;
	}

	public boolean isCheckIN() {
		return checkIN;
	}


	@Override
	public String toString() {
		return id+" "+checkIN+" "+date+" "+localUser+" "+comment;
	}

	
	public static List<Tracer> getAll(){
		
	    EntityManager em = EMF.getEntityManager();
	   
	    Query q = em.createQuery("SELECT t FROM Tracer t", Tracer.class);
	    
		return (List<Tracer>) q.getResultList();
	}
	
	
	
}