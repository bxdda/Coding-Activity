package openWeather;
/**
 * OpenWeather API - Java Integration<br><br>
 * 
 * Author : Chaughn Robin<br><br>
 * 
 * 1. Stores all relevant weather & geo information on a single favourite item.
 * 
 */
public class AppData {

	public AppData() {}
	
	public AppData(String city, String state, String country, String weatherMain, String weatherDescription,
			double temp, double tempMin, double tempMax, double feelsLike, double humidity) {
		super();
		this.city = city;
		this.state = state;
		this.country = country;
		this.weatherMain = weatherMain;
		this.weatherDescription = weatherDescription;
		this.temp = temp;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.feelsLike = feelsLike;
		this.humidity = humidity;
	}

	public AppData(WeatherData wData, GeoData gData) {
		super();
		this.city = gData.getName();
		this.state = gData.getState();
		this.country = gData.getCountry();
		this.weatherMain = wData.getWeatherMain();
		this.weatherDescription = wData.getWeatherDescription();
		this.temp = wData.getTemp();
		this.tempMin = wData.getTempMin();
		this.tempMax = wData.getTempMax();
		this.feelsLike = wData.getFeelsLike();
		this.humidity = wData.getHumidity();
		
		
		
	}
	
	private String city;

	private String state;
	private String country;
	
	private String weatherMain;
	private String weatherDescription;
	
	private double temp;
	private double tempMin;
	private double tempMax;
	
	private double feelsLike;
	
	private double humidity;
	
	
	public String toString() {
		return city + ", " + state + ", " + country + ": " + weatherDescription + " | Temp: " + temp + " " 
	+ " | Feels Like: " + feelsLike+ " " + " | Humidity: " + humidity + " " + " | Temp Min: " + tempMin + " " + " | Temp Max: " + tempMax;
	}
	
	
}
