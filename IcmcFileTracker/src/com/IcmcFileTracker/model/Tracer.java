package com.IcmcFileTracker.model;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Basic;


import com.IcmcFileTracker.helpers.EMF;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Tracer implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Key id;
	
	@Unowned
	protected User user;
	
	@Unowned
	protected Department department;
		   
	@Basic
	protected String comment;
	
	@Basic
	protected Date date;
	
	@Basic
	protected boolean checkIN;

	@Basic
	protected String fileid;
	
	public static String[] getVisableColumes(){
		return new String []{"Fleid", "checkIN", "department", "date", "user", "comment"};
	}
	
	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	
	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		return id+" "+checkIN+" "+date+" "+user+" "+comment;
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Tracer> getAll(){
	    EntityManager em = EMF.getEntityManager();
	    Query q = em.createQuery("SELECT t FROM Tracer t", Tracer.class);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Tracer> getLatest(){
	    EntityManager em = EMF.getEntityManager();
	    Query q = em.createQuery("SELECT t FROM Tracer t ORDER BY t.date DESC", Tracer.class);
	    return q.getResultList();
	}


	@SuppressWarnings("unchecked")
	public static List<Tracer> getByFileId(String id){
	    EntityManager em = EMF.getEntityManager();	
		    
	    Query q = em.createQuery("SELECT t FROM Tracer t WHERE t.fileid = :id ORDER BY t.date DESC", Tracer.class);   
		q.setParameter("id", id);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Tracer> getByUser(User user){
	    EntityManager em = EMF.getEntityManager();
	    Query q = em.createQuery("SELECT t FROM Tracer t WHERE t.user = :user ORDER BY t.date DESC", Tracer.class);  
	    q.setParameter("user", user);    
		return q.getResultList();
	}

	
	@SuppressWarnings("unchecked")
	public static List<Tracer> getOld(boolean state, int days){
	    EntityManager em = EMF.getEntityManager();
	    Query q = em.createQuery("SELECT t FROM Tracer t WHERE t.checkIN = :state AND t.date <= :date ORDER BY t.date DESC", Tracer.class);
	   
	    Calendar c = Calendar.getInstance();
	    c.add(Calendar.DATE, -days);
	    q.setParameter("date", c.getTime());
	    q.setParameter("state", state);
	    
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Tracer> getOld(boolean state, int days, Department department){
	    EntityManager em = EMF.getEntityManager();
	    Query q = em.createQuery("SELECT t FROM Tracer t WHERE t.checkIN = :state AND t.department = :department AND t.date <= :date ORDER BY t.date DESC", Tracer.class);
	   
	    Calendar c = Calendar.getInstance();
	    c.add(Calendar.DATE, -days);
	    q.setParameter("date", c.getTime());
	    q.setParameter("state", state);
	    q.setParameter("department", department);
	    
		return q.getResultList();
	}
	
}