package com.lift.weather.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import com.lift.weather.ShowWeatherInfoController;


import com.lift.weather.server.util.WeatherConstants;

@Path(WeatherConstants.APP_PATH_V1)
public class WeatherRequestHandler {

	@Context
	private HttpServletResponse httpResponse;
	
	@Context
	private HttpServletRequest httpRequest;
	
	@Context 
	private HttpHeaders httpHeaders;
	

    
/*  @GET
    @Path("v2/{param}")
    public String testServer(@PathParam("param") String param) {
    	return "Server Response :" + param;
    }*/
    
    @GET
    @Path("minmax")
    public String getMinMax() {
    	ShowWeatherInfoController swic  = new ShowWeatherInfoController();
    	String response = swic.envoke(0);
    	return "Server Response :" + response;
    }
    
}
