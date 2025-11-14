package openWeather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * OpenWeather API - Java Integration<br><br>
 * 
 * Author : Chaughn Robin<br><br>
 * 
 * 1. Search for Weather Details of a City: Given a city from a GeocodeStation, weatherStation returns that location's WeatherData.
 * 
 */



public class WeatherStation {

	private final String apiKey;
	private String openWeatherURL;
	
	private WeatherData weather;
	private GeoData geo;
	
	
	private HttpResponse<String> response;
	
	public WeatherStation(String apiKey) {
		this.apiKey = apiKey;
		
	}
	
	public void setGeo(GeoData geo) {
		this.geo = geo;
		
		openWeatherURL = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s&units=imperial", geo.getLat(), geo.getLon(), apiKey);
	}

	public String getWeatherJson() {
		
		// Create HTTP Client
		HttpClient client = HttpClient.newHttpClient();
		
		// Create HTTP request
		HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(openWeatherURL))
	                .GET()
	                .build();
		
		try {
			
			// Make API call for GeoCode
			// Parse the JSON GeoCode returns into differnt GeoPackages
			
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			parseWeatherJson(response.body());
//			System.out.println(response.body());
			
			
		} catch (IOException e) {
			System.out.println("Network error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Unexpected problem: " + e.getMessage());
		}
		
		return "";
	}
	
	private void parseWeatherJson(String json) {

	    // Split on "{"
	    String[] objects = json.split("\\{");

	    for (String obj : objects) {

	        // ===== WEATHER ARRAY BLOCK =====
	        if (obj.contains("\"main\":\"") && obj.contains("\"description\"")) {

	            String weatherMain = extract(obj, "\"main\":\"", "\"");
	            String weatherDescription = extract(obj, "\"description\":\"", "\"");

	            
	        }

	        // ===== MAIN BLOCK =====
	        if (obj.contains("\"temp\":") && obj.contains("\"feels_like\"")) {

	            String temp       = extract(obj, "\"temp\":", ",");
	            String feelsLike  = extract(obj, "\"feels_like\":", ",");
	            String tempMin    = extract(obj, "\"temp_min\":", ",");
	            String tempMax    = extract(obj, "\"temp_max\":", ",");
	            String pressure   = extract(obj, "\"pressure\":", ",");
	            String humidity   = extract(obj, "\"humidity\":", ",");

	            
	        }

	        // ===== WIND BLOCK =====
	        if (obj.contains("\"speed\":")) {

	            String windSpeed = extract(obj, "\"speed\":", ",");
	            String windGust = extract(obj, "\"gust\":", "}");

	            
	        }

	        // ===== CLOUD BLOCK =====
	        if (obj.contains("\"all\":")) {

	            String cloudPercentage = extract(obj, "\"all\":", "}");

	            
	        }
	    }

	    // But we can't return inside the loop — so instead, re-process the JSON
	    // in one pass identical to your Geo code pattern:

	    String weatherMain       = extract(json, "\"main\":\"", "\"");
	    String weatherDescription = extract(json, "\"description\":\"", "\"");

	    String temp       = extract(json, "\"temp\":", ",");
	    String feelsLike  = extract(json, "\"feels_like\":", ",");
	    String tempMin    = extract(json, "\"temp_min\":", ",");
	    String tempMax    = extract(json, "\"temp_max\":", ",");
	    String pressure   = extract(json, "\"pressure\":", ",");
	    String humidity   = extract(json, "\"humidity\":", ",");

	    String windSpeed  = extract(json, "\"speed\":", ",");
	    String windGust   = extract(json, "\"gust\":", "}");

	    String cloudPercentage = extract(json, "\"all\":", "}");


	    // EXACT FORMAT LIKE YOUR GEO CODE:
	    // one constructor call, all extracted inline
	    weather = new WeatherData(
	            weatherMain,
	            weatherDescription,
	            Double.parseDouble(temp),
	            Double.parseDouble(feelsLike),
	            Double.parseDouble(tempMin),
	            Double.parseDouble(tempMax),
	            Double.parseDouble(pressure),
	            Double.parseDouble(humidity),
	            Double.parseDouble(windSpeed),
	            windGust.isEmpty() ? 0.0 : Double.parseDouble(windGust),
	            Double.parseDouble(cloudPercentage)
	    );
	}

	/**
	 * Custom json parsing algorithm, so I do not need to add dependencies to the project.
	 */
	private String extract(String src, String start, String end) {
	    int s = src.indexOf(start);
	    if (s < 0) return "";
	    s += start.length();
	    int e = src.indexOf(end, s);
	    if (e < 0) return src.substring(s).trim();
	    return src.substring(s, e);
	}

	public void displayWeather() {
		System.out.println(
				"Weather: " + weather.getWeatherMain() + "\n" +
				"Weather Desc: " + weather.getWeatherDescription() + "\n\n" +
				
				"Temperature: " + weather.getTemp() + "\n" + 
				"Temp Min: " + weather.getTempMin() + "\n" +
				"Temp Max: " + weather.getTempMax() + "\n\n" +
				
				"Cloud Percentage:" + weather.getCloudPercentage() + "%" +"\n\n" +
				
				"Feels Like: " + weather.getFeelsLike() + "\n" +
				"Air Pressure: " + weather.getPressure()+ " hPa" +"\n" +
				"Humidity: " + weather.getHumidity() + "%" + "\n\n" + 
				
				"Wind Speed: " + weather.getWindSpeed()+ "mph" + "\n" +
				"Wind Gust: " + weather.getWindGust()+ "mph" + "\n" 
				
				
				
				
				);
	}
	
	public WeatherData getWeatherData() {return weather;}
	
}
