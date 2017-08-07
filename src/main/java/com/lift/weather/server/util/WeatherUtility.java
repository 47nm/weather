package com.lift.weather.server.util;

import com.lift.weather.City;


public class WeatherUtility {


	public static class Temp1{
		
		City city;
		FromToDate fromToDate;
		public City getCity() {
			return city;
		}
		public void setCity(City city) {
			this.city = city;
		}
		public FromToDate getFromToDate() {
			return fromToDate;
		}
		public void setFromToDate(FromToDate fromToDate) {
			this.fromToDate = fromToDate;
		}
		public Temp1(City city, FromToDate fromToDate) {
			super();
			this.city = city;
			this.fromToDate = fromToDate;
		}
		

	}
	

	
}
