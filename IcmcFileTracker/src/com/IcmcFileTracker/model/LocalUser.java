package com.IcmcFileTracker.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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

	public static LocalUser find(String fileID){
		EntityManager em = EMF.getEntityManager();
	    return em.find(LocalUser.class, fileID);
	}
}