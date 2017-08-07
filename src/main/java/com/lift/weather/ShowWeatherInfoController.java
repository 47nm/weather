package com.lift.weather;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import com.lift.weather.server.util.WeatherInfoHelper;
public class ShowWeatherInfoController {

	public static Map<String, WeatherInfo> weatherInfoCache ;
	public static Map<String,ConcurrentSkipListMap<Double,Timestamp>> sortedMapOfWeatherInfo;
	static{
		weatherInfoCache = new HashMap<String, WeatherInfo>();
		sortedMapOfWeatherInfo = new HashMap <String,ConcurrentSkipListMap<Double,Timestamp>>();
		try {
			List<WeatherInfo> tempList = WeatherInfoHelper.getWeatherInfoForToday(new City("1277333","Bangalore"));
			String key ;
			
			
			for(WeatherInfo weatherInfo : tempList){
				key = weatherInfo.getCity().getCityID() + "_" +  String.valueOf(weatherInfo.getmTemp().getCurrent_time().getTime());
				if(!weatherInfoCache.containsKey(key)){
					weatherInfoCache.put(key, weatherInfo);
					if(!sortedMapOfWeatherInfo.containsKey(weatherInfo.getCity().getCityID())){
						sortedMapOfWeatherInfo.put(weatherInfo.getCity().getCityID(), new ConcurrentSkipListMap<Double,Timestamp>());
					}
					sortedMapOfWeatherInfo.get(weatherInfo.getCity().getCityID())
					.put(weatherInfo.getmTemp().getCurrent_temp(), weatherInfo.getmTemp().getCurrent_time());
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String envoke(int taskId){
		try {
			if (taskId == 0){ //show current temperature in kelvin

				City city  = new City("1277333","Bangalore"); //currently hard-coded
				String jsonString = WeatherInfoHelper.getWeatherInfoForNowFromExternalAPI(city);
				WeatherInfo weatherInfo = WeatherInfoHelper.convertJsonStringtoWeatherInfoObject(jsonString);
				String key = weatherInfo.getCity().getCityID() + "_" +  String.valueOf(weatherInfo.getmTemp().getCurrent_time().getTime());
				if(!weatherInfoCache.containsKey(key)){
					WeatherInfoHelper.putWeatherInfoInDB(weatherInfo);
					weatherInfoCache.put(key, weatherInfo);
					if(!sortedMapOfWeatherInfo.containsKey(weatherInfo.getCity().getCityID())){
						sortedMapOfWeatherInfo.put(weatherInfo.getCity().getCityID(), new ConcurrentSkipListMap<Double,Timestamp>());
						
					}
					sortedMapOfWeatherInfo.get(weatherInfo.getCity().getCityID())
					.put(weatherInfo.getmTemp().getCurrent_temp(), weatherInfo.getmTemp().getCurrent_time());
				}
				
				Map.Entry<Double,Timestamp> minimumTemp = sortedMapOfWeatherInfo.get(city.getCityID()).firstEntry();
				Map.Entry<Double,Timestamp> maximumTemp = sortedMapOfWeatherInfo.get(city.getCityID()).lastEntry();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				
				return "city = " + city.getCityName() + 
						"; current temperature = " + String.valueOf(weatherInfo.getmTemp().getCurrent_temp()) +
						"; minimum temperature = " + minimumTemp.getKey() + " at " + sdf.format(minimumTemp.getValue()) +
						"; maximum temperature = " + maximumTemp.getKey() + " at " + sdf.format(maximumTemp.getValue()) 
						;
				
				
			}else if(taskId == 1){ //show the array
				
			}else if(taskId == 2){ //show graph
				
			}else{//show defaultPage
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
