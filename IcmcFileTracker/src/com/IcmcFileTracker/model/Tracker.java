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
public class Tracker implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Key id;
	
	@Unowned
	private LocalUser localUser;
	
	@Unowned
	private Department department;
	
	
	@Unowned
    @Persistent(mappedBy="tracker")
	private FileTracker fileTracker;
	   
	@Basic
	private String comment;
	@Basic
	private Date date;
	@Basic
	private boolean checkIN;

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

	public FileTracker getFileTracker() {
		return fileTracker;
	}

	public void setFileTracker(FileTracker param) {
		this.fileTracker = param;
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
		return fileTracker.getFileID()+" "+checkIN+" "+date+" "+localUser+" "+comment;
				
		//return "Tracker [id=" + id + ", localUser=" + localUser
		//		+ ", department=" + department + ", fileTracker=" + fileTracker.getFileID()
		//		+ ", comment=" + comment + ", date=" + date + ", checkIN="
		//		+ checkIN + "]";
	}

	
	public static List<Tracker> getAll(){
		
	    EntityManager em = EMF.getEntityManager();
	   
	    Query q = em.createQuery("SELECT t FROM Tracker t", Tracker.class);
	    
		return (List<Tracker>) q.getResultList();
	}
	
	
	
}