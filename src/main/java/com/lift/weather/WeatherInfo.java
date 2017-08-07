package com.lift.weather;


public class WeatherInfo {
	public WeatherInfo(City city, MainTemperature mTemp) {
		super();
		this.city = city;
		this.mTemp = mTemp;
	}
	public WeatherInfo() {
		super();
		this.city = new City();
		this.mTemp = new MainTemperature();
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public MainTemperature getmTemp() {
		return mTemp;
	}
	public void setmTemp(MainTemperature mTemp) {
		this.mTemp = mTemp;
	}
	
	private City city;
	private MainTemperature mTemp;
}
