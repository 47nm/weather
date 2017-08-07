package com.lift.weather;

public class City {
		
	public City(String cityID, String cityName) {
		super();
		this.cityID = cityID;
		this.cityName = cityName;
	}
	
	public City() {
		super();
		this.cityID = "";
		this.cityName = "";
	}
	
	private String cityID;
	private String cityName;
	
	public String getCityID() {
		return cityID;
	}
	public void setCityID(String cityID) {
		this.cityID = cityID;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
		
}
