package com.IcmcFileTracker.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.IcmcFileTracker.helpers.EMF;
import com.google.appengine.api.datastore.Key;

@Entity
public class FileTracker implements Serializable{
	private static final long serialVersionUID = 1L;

	
	@Override
	public String toString() {
		return fileID;
	}

	
	@OneToOne(cascade = CascadeType.ALL)
	private Tracker tracker;
	
	@Id
	private String fileID;
	
	@Basic
	private Date date_updated;
	
	public Date getDate_updated() {
		return date_updated;
	}

	public void setDate_updated(Date date_updated) {
		this.date_updated = date_updated;
	}

	public Tracker getTracker() {
		return tracker;
	}

	public void setTracker(Tracker param) {
		this.tracker = param;
	}

	public void setFileID(String param) {
		this.fileID = param;
	}

	public String getFileID() {
		return fileID;
	}

	public void persist(){
		
		//EntityManagerFactory emf = EMF.get();
	    EntityManager em = EMF.getEntityManager();
	    EntityTransaction et = em.getTransaction();
	    et.begin();
	    em.persist(this);
	    et.commit();
	    //em.close();
	}
	
	public static FileTracker find(String fileID){
		
		//EntityManagerFactory emf = EMF.get();
	    //EntityManager em = emf.createEntityManager();
		
	    EntityManager em = EMF.getEntityManager();
		
	    FileTracker f =  em.find(FileTracker.class, fileID);
		
		//em.close();
		
		return f;
	}
	
	public static List<Tracker> getAllLatestTrackers(){
	    EntityManager em = EMF.getEntityManager();
	    
	    //ASC | DESC
	    
	    Query q = em.createQuery("SELECT ft.tracker FROM FileTracker ft ORDER BY ft.date_updated DESC", Tracker.class);
	    //q.setParameter("bool", true);
	    q.setMaxResults(5);
		return (List<Tracker>) q.getResultList();
	}
	
	public static List<Tracker> getOldTrackers(int days){
	    EntityManager em = EMF.getEntityManager();
	     
	    Query q = em.createQuery("SELECT ft.tracker FROM FileTracker ft WHERE ft.date_updated <= :date ORDER BY ft.date_updated DESC", Tracker.class);
	   
	    Calendar c = Calendar.getInstance();
	    c.add(Calendar.DATE, -days);
	    q.setParameter("date", c.getTime());
	    
		return (List<Tracker>) q.getResultList();
	}
}