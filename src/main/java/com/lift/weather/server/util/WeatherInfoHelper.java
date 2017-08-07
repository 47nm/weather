package com.lift.weather.server.util;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.lift.weather.City;
import com.lift.weather.MainTemperature;
import com.lift.weather.WeatherInfo;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.Timestamp;

public class WeatherInfoHelper {

	static Reader rd;
	static SqlMapClient smc ;
	static Logger logger; 
	static{
		
		try {
			logger = Logger.getLogger(WeatherInfoHelper.class);

			Properties props = new Properties();
			props.load(WeatherInfoHelper.class.getClassLoader().getResourceAsStream("/properties/log4j.properties"));
			PropertyConfigurator.configure(props);
			logger.debug("this is a debug log message");
			rd = Resources.getResourceAsReader("/database/SqlMapConfig.xml");
			if(rd!=null){
		    	smc = SqlMapClientBuilder.buildSqlMapClient(rd);	
		    }
		} catch (IOException e) {
			System.out.println("could not read the file" + e.getCause());
			e.printStackTrace();
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<WeatherInfo> getWeatherInfoFromDataBase(City city, FromToDate fromTodate) throws SQLException{
		
		if(smc == null){
			return null;
		}
		
		WeatherUtility.Temp1 temp1 =  new WeatherUtility.Temp1(city,fromTodate);
		String sqlId = "WeatherInfo.getAllRecordsBetweenTwoDates";
		
	
		List<MainTemperature> tempList =  (List<MainTemperature> )smc.queryForList(sqlId,temp1);
		List<WeatherInfo> weatherInfoList = new ArrayList<WeatherInfo>(); 
		tempList.forEach(mainTemperature->{
			weatherInfoList.add(new WeatherInfo(city,mainTemperature));
		});
		
		return weatherInfoList;
	}
	
	public static List<WeatherInfo> getWeatherInfoForToday(City city) throws SQLException{
		LocalDate today = LocalDate.now();
		
		LocalDateTime todayMidnight = LocalDateTime.of(today, LocalTime.MIDNIGHT);
		LocalDateTime now = LocalDateTime.of(today, LocalTime.now());
		
		
		List<WeatherInfo> listWeatherInfo = getWeatherInfoFromDataBase(
				city,
				new FromToDate(
						new Timestamp(todayMidnight.toEpochSecond(ZoneOffset.UTC)),
						new Timestamp(now.toEpochSecond(ZoneOffset.UTC))
				)
			);
		
		
		return listWeatherInfo;
		
	}
	
	
	public static void putWeatherInfoInDB(WeatherInfo weatherInfo) throws SQLException{
		if(smc == null){
			System.out.println("smc not initialized");
			return ;
		}
		
		String sqlId = "WeatherInfo.insertARecordInWeatherInfo";
		
		smc.insert(sqlId,weatherInfo);
	}
	public static String getWeatherInfoForNowFromExternalAPI(City city) throws IOException{
		String urlString;
		BufferedReader reader = null;
		int read;
        char[] chars = new char[1024];
        try{
			urlString = String.format("http://api.openweathermap.org/data/2.5/weather?id=%s&appid=%s", city.getCityID(),"a6aa31fd5178ee136f672c096a5f9e2e");
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
	
			while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 
			
			
			return buffer.toString();
        
		} finally {
	        if (reader != null)
	            reader.close();
	    }
        
	}
	public static WeatherInfo convertJsonStringtoWeatherInfoObject(String jsonString){
		JSONObject json = new JSONObject(jsonString);
		WeatherInfo weatherInfo = new  WeatherInfo();
		weatherInfo.setCity(new City(String.valueOf(json.getInt("id")), json.getString("name")));
		Timestamp dt = new Timestamp(json.getLong("dt")*1000);
			

		json = json.getJSONObject("main");
		weatherInfo.setmTemp(new MainTemperature(
					json.getDouble("temp_min"),
					json.getDouble("temp_max"),
					json.getDouble("temp"),
					dt
				));
		return weatherInfo;
	}
}
