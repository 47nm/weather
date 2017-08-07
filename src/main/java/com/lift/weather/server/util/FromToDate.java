package com.lift.weather.server.util;

import java.sql.Timestamp;

public class FromToDate {
	Timestamp fromDate;
	Timestamp toDate;
	
	public FromToDate(Timestamp fromDate, Timestamp toDate) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	
	public Timestamp getFromDate() {
		return fromDate;
	}
	public void setFromDate(Timestamp fromDate) {
		this.fromDate = fromDate;
	}
	public Timestamp getToDate() {
		return toDate;
	}
	public void setToDate(Timestamp toDate) {
		this.toDate = toDate;
	}
}
