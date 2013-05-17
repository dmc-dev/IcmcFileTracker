package com.IcmcFileTracker.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;

import com.IcmcFileTracker.helpers.EMF;
import com.google.appengine.datanucleus.annotations.Unowned;

@Entity
public class LocalUser implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id   
	private String userName;
	private String password;	
	private boolean active;

	@Unowned
	@OneToOne(cascade = CascadeType.ALL)
	private Role role;
	
	
	@Unowned
	@OneToMany(cascade = CascadeType.ALL)
	private List<Tracker> trackers;
	
	
	
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


	public List<Tracker> getTrackers() {
		return trackers;
	}

	public void setTrackers(List<Tracker> trackers) {
		this.trackers = trackers;
	}


	public static LocalUser find(String fileID){
		EntityManager em = EMF.getEntityManager();
	    return em.find(LocalUser.class, fileID);
	}
}