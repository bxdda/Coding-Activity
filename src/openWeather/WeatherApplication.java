package openWeather;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * OpenWeather API - Java Integration<br><br>
 * 
 * Author : Chaughn Robin<br><br>
 * 
 * 1. Weather Application that uses a WeatherStation & GeoStation to get weather from geocode. 
 * 2. Allows favourites to be stored.
 * 
 * 
 */

public class WeatherApplication {

	private final String apiKey;
	
	private Scanner input;
	
	private ArrayList<AppData> favourites;
	
	private WeatherStation weather;
	private GeocodeStation geo;
	
	
	public WeatherApplication() {

		apiKey = "16c9dd5243a6b6f19ff7872b38494c5b";
		
		favourites = new ArrayList<>(3);
		input = new Scanner(System.in);
		
		weather = new WeatherStation(apiKey);
		geo = new GeocodeStation(apiKey);
		
		introDisplay();
		favoriteDisplay();
		optionDisplay();
		
		handleInput(input.nextInt());
	}
	
	// -- DISPLAY METHODS -- 
	
	public void introDisplay() {
		clear();
		System.out.println("== WEATHER APPLICATION ==");
		System.out.println("== Author: Chaughn Robin == \n");
	}
	public void favoriteDisplay() {
		if(favourites.size() == 0) System.out.println("Favourites list is empty.\n");
		else { 
			System.out.println("FAVOURITES LIST:");
			for(AppData data: favourites) System.out.println(data);}
		System.out.println();
		
	}
	public void optionDisplay() {
		System.out.println("Select an option. Enter 1 - 3:");
		System.out.println("1. Search by City Name \n");
		System.out.println("2. Edit Favorites \n");	
		System.out.println("3. Exit \n");	
	}
	
	// -- INPUT HANDLING --
	public void handleInput(int index) {
		if(index == 1) { clear(); handleCity();}
		if(index == 2) { clear(); handleFavorites();}
		if(index == 3) { clear(); exit();}
	}
	
	public void handleCity() {
		input.nextLine();
		System.out.println("Enter a city: " );
		if(inputCity()) {clear(); handleWeather();}
		else {}
	}
	
	
	public void handleWeather() {
		
		// This function sets the geo location to check weather for, sends the api call & gets the api response, and displays results.
		
		weather.setGeo(geo.getGeo());
		weather.getWeatherJson();
		weather.displayWeather();
		addToFav();
		
		
	}
	
	public void handleFavorites() {
		favoriteDisplay();
		
		if(!favourites.isEmpty())System.out.println("\nEnter a value 1-3 to remove that item.");
		else System.out.println("\nEnter any value to return to main menu.");
		
		while (!input.hasNextInt()) {
		    System.out.println("Invalid input, try again.");
		    input.nextLine();
		}
		int value = input.nextInt();
		input.nextLine();
		if( !favourites.isEmpty() && (value >=1 && value <= 3)) {
			if (favourites.size() >= value) {
				favourites.remove(value - 1);
				System.out.println("Item " + (value) + " removed");
			}
			
		}
		introDisplay();
		favoriteDisplay();
		optionDisplay();
		
		handleInput(input.nextInt());
		
	}
	public void addToFav() {
		if(favourites.size() < 3) {
		System.out.println("Enter 1 to add '" + geo.getGeo() + "' to your favorites. Enter 0 to return to main menu.");
		
		
		int value = input.nextInt();
		input.nextLine();

		AppData favData = new AppData();
		if(value == 1) favData = new AppData(weather.getWeatherData(), geo.getGeo());
		favourites.add(favData);
		
		introDisplay();
		favoriteDisplay();
		optionDisplay();
		
		handleInput(input.nextInt());
		}
		else {
			System.out.println("Enter anything to return to main menu.");
			
			
			
			input.nextLine();

			
			introDisplay();
			favoriteDisplay();
			optionDisplay();
			
			handleInput(input.nextInt());
		}
		
		
	}
	
	/**
	 * This method makes the geocodeStation call, receives geocode response json, and sets the weatherStation's location.
	 */
	public boolean inputCity() {
		String city = null;
		
		
		city = input.nextLine();
		System.out.println("Searching for " + city + "...");
		
		geo.setLocation(city);
		if(geo.getGeoJson()) {
		
		if(geo.verifyCity()) weather.setGeo(geo.getGeo());
		else {
			while (!input.hasNextInt()) {
			    System.out.println("Invalid input, try again.");
			    input.nextLine();
			}
			int value = input.nextInt();
			input.nextLine();

			
			geo.setGeo(value);
		}
		System.out.println("Selected " + geo.getGeo());
		return true;
		}
		else {return false;}
	}
	
	
	
	
	public void clear() {
		 try {
		        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		    } catch (Exception e) {
		        // fallback
		        for (int i = 0; i < 50; i++) System.out.println();
		    }
	}

	public void exit() {
		System.exit(0);
	} 
	
	
	public static void main(String arg[]) {
//		WeatherStation weather = new WeatherStation("16c9dd5243a6b6f19ff7872b38494c5b");
//
//		GeocodeStation geo = new GeocodeStation("16c9dd5243a6b6f19ff7872b38494c5b");
//		geo.setLocation("Downingtown");
//		geo.getGeoJson();
//		
//		weather.setGeo(geo.getGeo(0));
//		weather.getWeatherJson();
//		weather.displayWeather();
		
		WeatherApplication app = new WeatherApplication();
		
		
		
		
		
	}
	
}
