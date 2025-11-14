package openWeather;
/**
 * OpenWeather API - Java Integration<br><br>
 * 
 * Author : Chaughn Robin<br><br>
 * 
 * 1. Stores all relevant weather information on a single geo location. 
 * 
 */
public class WeatherData {

	private String weatherMain;
	private String weatherDescription;
	
	private double temp;
	private double feelsLike;
	private double tempMin;
	private double tempMax;
	private double pressure;
	private double humidity;
	
	private double windSpeed;
	private double windGust;
	
	private double cloudPercentage;
	
	public WeatherData(String weatherMain, String weatherDescription, double temp, double feelsLike, double tempMin,
			double tempMax, double pressure, double humidity, double windSpeed, double windGust,
			double cloudPercentage) {
		
		this.weatherMain = weatherMain;
		this.weatherDescription = weatherDescription;
		this.temp = temp;
		this.feelsLike = feelsLike;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.pressure = pressure;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.windGust = windGust;
		this.cloudPercentage = cloudPercentage;
	}
	
	public String getWeatherMain() {
		return weatherMain;
	}

	public String getWeatherDescription() {
		return weatherDescription;
	}

	public double getTemp() {
		return temp;
	}

	public double getFeelsLike() {
		return feelsLike;
	}

	public double getTempMin() {
		return tempMin;
	}

	public double getTempMax() {
		return tempMax;
	}

	public double getPressure() {
		return pressure;
	}

	public double getHumidity() {
		return humidity;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public double getWindGust() {
		return windGust;
	}

	public double getCloudPercentage() {
		return cloudPercentage;
	}
	

}
