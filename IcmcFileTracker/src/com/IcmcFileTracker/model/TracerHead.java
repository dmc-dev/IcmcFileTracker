package com.IcmcFileTracker.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.InheritanceType;
import javax.persistence.NoResultException;
import javax.persistence.OneToOne;
import javax.persistence.Query;
import javax.persistence.Inheritance;

import com.IcmcFileTracker.helpers.EMF;


@Entity
@Inheritance( strategy=InheritanceType.TABLE_PER_CLASS)
public class TracerHead extends Tracer{
	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = CascadeType.ALL)
	protected Tracer trace;
	
	public Tracer getTrace(){
		return trace;
	}

	public void setTrace(Tracer trace) {
		this.trace = trace;
		localUser = trace.getLocalUser();
		department = trace.getDepartment();	   
		comment = trace.getComment();
		date = trace.getDate();
		checkIN= trace.isCheckIN();
	}

	public void persist(){
	    EntityManager em = EMF.getEntityManager();
	    EntityTransaction et = em.getTransaction();
	    et.begin();
	    em.persist(this);
	    et.commit();
	}
	
	public static TracerHead find(String id){
	    EntityManager em = EMF.getEntityManager();	
		    
	    try{
		    Query q = em.createQuery("SELECT t FROM TracerHead t WHERE t.fileid = :id", TracerHead.class);
		    q.setParameter("id", id);
		    return (TracerHead) q.getSingleResult();
	    } catch (NoResultException e){
	    	return null;
	    }
	}
	
	@SuppressWarnings("unchecked")
	public static List<TracerHead> getAllLatestTracers(){
	    EntityManager em = EMF.getEntityManager();
	    
	    Query q = em.createQuery("SELECT t FROM TracerHead t ORDER BY t.date DESC", TracerHead.class);
	   
	    return q.getResultList();
	}
	
	public static List<TracerHead> getOldTracers(int days){
	    EntityManager em = EMF.getEntityManager();
	     
	    Query q = em.createQuery("SELECT t FROM TracerHead t WHERE t.date <= :date ORDER BY t.date DESC", TracerHead.class);
	   
	    Calendar c = Calendar.getInstance();
	    c.add(Calendar.DATE, -days);
	    q.setParameter("date", c.getTime());
	    
		return (List<TracerHead>) q.getResultList();
	}
}
