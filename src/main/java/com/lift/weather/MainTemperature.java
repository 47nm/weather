package com.lift.weather;

import java.sql.Timestamp;

public class MainTemperature {
	private double current_min_temp;
	private double current_max_temp;
	private double current_temp;
	private Timestamp current_time;
	
	public MainTemperature(double current_min_temp, double current_max_temp, double current_temp, Timestamp current_time) {
		super();
		this.current_min_temp = current_min_temp;
		this.current_max_temp = current_max_temp;
		this.current_temp = current_temp;
		this.current_time = current_time;
	}
	public MainTemperature() {
		super();
		this.current_min_temp = 0;
		this.current_max_temp = 0;
		this.current_temp = 0;
		this.current_time = new Timestamp(0);
	}
	
	
	public double getCurrent_min_temp() {
		return current_min_temp;
	}
	public void setCurrent_min_temp(double current_min_temp) {
		this.current_min_temp = current_min_temp;
	}
	public double getCurrent_max_temp() {
		return current_max_temp;
	}
	public void setCurrent_max_temp(double current_max_temp) {
		this.current_max_temp = current_max_temp;
	}
	public double getCurrent_temp() {
		return current_temp;
	}
	public void setCurrent_temp(double current_temp) {
		this.current_temp = current_temp;
	}
	public Timestamp getCurrent_time() {
		return current_time;
	}
	public void setCurrent_timeTimestamp (Timestamp current_time) {
		this.current_time = current_time;
	}

}
