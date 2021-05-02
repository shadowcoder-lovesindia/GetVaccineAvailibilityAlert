package com.vaccine.getavailibility.model;

import java.util.List;

public class Center{
	 public int center_id;
	 public String name;
	 public String state_name;
	 public String district_name;
	 public String block_name;
	 public int pincode;
	 public int lat;

	 public String from;
	 public String to;
	 public int getCenter_id() {
		return center_id;
	}
	public void setCenter_id(int center_id) {
		this.center_id = center_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public String getDistrict_name() {
		return district_name;
	}
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}
	public String getBlock_name() {
		return block_name;
	}
	public void setBlock_name(String block_name) {
		this.block_name = block_name;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public int getLat() {
		return lat;
	}
	public void setLat(int lat) {
		this.lat = lat;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public List<Session> getSessions() {
		return sessions;
	}
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	public List<VaccineFee> getVaccine_fees() {
		return vaccine_fees;
	}
	public void setVaccine_fees(List<VaccineFee> vaccine_fees) {
		this.vaccine_fees = vaccine_fees;
	}
	public String fee_type;
	 public List<Session> sessions;
	 public List<VaccineFee> vaccine_fees;
	}