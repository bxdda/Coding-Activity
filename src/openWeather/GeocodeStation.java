package openWeather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * OpenWeather API - Java Integration<br><br>
 * 
 * Author : Chaughn Robin<br><br>
 * 
 * 1. Search for Weather Details of a City: Enter the name of a city and get its GeoData.
 * 
 */



public class GeocodeStation {

	private final String apiKey;
	private final int limit = 5;
	private String geocodeURL; 
	
	private String currentCity;
	
	private GeoData currentGeo;
	
	private HttpResponse<String> response;
	private List<GeoData> locations;
	
	
	public GeocodeStation(String apiKey) {
		this.apiKey = apiKey;
		
		locations = new ArrayList<>();
		
//		geocodeURL = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s,%s,%s&limit=%d&appid=%f", cityName, stateCode, countryCode, apiKey);
//		geocodeURL = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=%d&appid=%s", cityName, limit, apiKey);
	}
	
	/**
	 * This mutator sets the current desired city.
	 */
	public void setLocation(String cityName) {
	currentCity = cityName;	
	geocodeURL = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=%d&appid=%s", currentCity, limit, apiKey);
	locations.clear();
	}
	
	
	/**
	 * This method creates HTTPClient and HTTPRequest, makes api call to OpenWeather Geocode API, and stores the json response.
	 */
	public boolean getGeoJson() {
		
		// Create HTTP Client
		HttpClient client = HttpClient.newHttpClient();
		
		// Create HTTP request
		HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(geocodeURL))
	                .GET()
	                .build();
		
		try {
			
			// Make API call for GeoCode
			// Parse the JSON GeoCode returns into differnt GeoPackages
			
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			parseGeoJson(response.body());
//			System.out.println(response.body());

			
		} catch (IOException e) {
			System.out.println("Network error: " + e.getMessage());
			return false;
		} catch (Exception e) {
			System.out.println("Please try again. Error with given city.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * This method creates GeoPackages for each location parsed from the Geocode api JSON return. A GeoPackage is a convenience class for relevant Geo information.<br>
	 * It also adds each GeoPackage to an ArrayList of GeoPackages.
	 */
	private void parseGeoJson(String json) {
	   

		// Remove leading [ and trailing ]
	    String trimmed = json.substring(1, json.length() - 1);

	    // Split between objects: ...}{...}{...
	    String[] objects = trimmed.split("\\},\\{");

	    for (String obj : objects) {
	        // obj might look like:
	        //  "{"name":"Paris","local_names":{...},"lat":48.85,...,"state":"Ile-de-France"}"
	        // or without the leading/trailing braces, depending on position.
	        // Our extract() doesn't care about braces, just key/value patterns.

	        String name = extract(obj, "\"name\":\"", "\"");
	        if (name.isEmpty()) continue; // safety

	        String country = extract(obj, "\"country\":\"", "\"");
	        String state   = extract(obj, "\"state\":\"", "\"");

	        String latStr = extract(obj, "\"lat\":", ",");
	        String lonStr = extract(obj, "\"lon\":", ",");

	        double lat = Double.parseDouble(latStr);
	        double lon = Double.parseDouble(lonStr);

	        GeoData g = new GeoData(name, country, state, lat, lon);

	        locations.add(g);
	    }

	 
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
	
	/**
	 * Lists all cities matching desired name. If only 1 is returned, sets currentGeo to that location.
	 */
	public boolean verifyCity() {
		if(locations.size()>1) {
			System.out.println("Which " + currentCity + " are you looking for?\n");

			for (int i = 0; i < locations.size(); i++) {
				System.out.println(i + ": " + locations.get(i).getName() + ", " + locations.get(i).getState() + ", "
						+ locations.get(i).getCountry() + "\n");
			
			}
			return false;
		} else {
			if(!locations.isEmpty())currentGeo = locations.get(0);
			return true;
		}
	}
	/**
	 * Mutator for currentGeo, sets currentGeo to user's selection from list.
	 */
	public void setGeo(int index) {currentGeo = locations.get(index);}
	public GeoData getGeo(int index) {
		if(locations.size() == 1) return locations.get(0);
		else {return locations.get(index);}
	}	
	public GeoData getGeo() {return currentGeo;}
	public List<GeoData> getLocations(){return locations;}

	
	
}
