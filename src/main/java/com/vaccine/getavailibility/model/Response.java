package com.vaccine.getavailibility.model;

public class Response {


	public String getCentername() {
		return centername;
	}
	public void setCentername(String centername) {
		this.centername = centername;
	}
	public String getSessiondate() {
		return sessiondate;
	}
	public void setSessiondate(String sessiondate) {
		this.sessiondate = sessiondate;
	}
	public int getSessionmin_age_limit() {
		return sessionmin_age_limit;
	}
	public void setSessionmin_age_limit(int sessionmin_age_limit) {
		this.sessionmin_age_limit = sessionmin_age_limit;
	}
	public int getAvailablecapacity() {
		return availablecapacity;
	}
	public void setAvailablecapacity(int availablecapacity) {
		this.availablecapacity = availablecapacity;
	}
	private String centername;
	private String sessiondate;
	private int sessionmin_age_limit;
	private int availablecapacity;
}
