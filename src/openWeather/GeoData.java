package openWeather;

/**
 * OpenWeather API - Java Integration<br><br>
 * 
 * Author : Chaughn Robin<br><br>
 * 
 * 1. Stores all relevant geo information on a single geo location. 
 * 
 */

public class GeoData {

	private String name;
	

	private String country;
	private String state;
	
	private double lat;
	private double lon;
	
	
	public GeoData(String name, String country, String state, double lat, double lon) {
		this.name = name;
		this.country = country;
		this.state = state;
		
		this.lat = lat;
		this.lon = lon;
	}

	public String getName() {
		return name;
	}


	public String getCountry() {
		return country;
	}


	public String getState() {
		return state;
	}


	public double getLat() {
		return lat;
	}


	public double getLon() {
		return lon;
	}

	public String toString() {
		return name + ", " + state + ", " + country;
	}
	
}
