package com.IcmcFileTracker.helpers;

import java.io.Serializable;

public class InOutObj implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static final boolean IN=true;
	public static final boolean OUT=false;
	
	private boolean state;
	
	public InOutObj(boolean state){
		this.state=state;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	@Override
	public String toString() {
		if(state)
			return "IN";
		return "OUT";
	}
	
	public static String getString(boolean b){
		if(b)
			return "IN";
		return "OUT";
	}
	
	public boolean getValue(){return state;}
}
