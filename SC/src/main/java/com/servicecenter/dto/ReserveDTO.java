package com.servicecenter.dto;

import java.util.Date;

public class ReserveDTO extends BoardDTO{
	private Date reservedate;
	private int state;
	private String userid;
	private String reserveloc;

	public Date getReservedate() {
		return reservedate;
	}
	public void setReservedate(Date reservedate) {
		this.reservedate = reservedate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getReserveloc() {
		return reserveloc;
	}
	public void setReserveloc(String reserveloc) {
		this.reserveloc = reserveloc;
	}
	
	
	
}
