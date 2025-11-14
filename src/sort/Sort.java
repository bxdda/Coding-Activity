package sort;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * SortAndFindMedian - Java Integration<br><br>
 * 
 * Author : Chaughn Robin<br><br>
 * 
 * 1. Takes array input from user.
 * 2. Recreates given function.
 * 3. Sorts using ubble sort algorithm.
 * 4. Finds and returns the median of the array.
 * 
 */
public class Sort {

	private ArrayList<Double> array;
	private Scanner input;
	private boolean arraySaved;
	
	
	public Sort() {
		array = new ArrayList<>();
		input = new Scanner(System.in);
		
		takeArray();
		
		displayResults();
		
	}
	
	/**
	 * Takes user input, either values for array, or 'sort' to end input handling and return sortAndFindMedian.
	 */
	public void takeArray() {
		while(!arraySaved) {
		System.out.println("Enter values to add them to array. Enter ' sort ' to move forward with selected values.");
		if(!array.isEmpty()) {
			System.out.print("VALUES: [ ");
			if(!array.isEmpty()) for(Double val: array) if(array.indexOf(val) != array.size()-1)System.out.print(val + ", "); else System.out.print(val + " ");
			System.out.println("]");
		}
		
		
		
		if(!input.hasNextDouble()) {
			if(input.hasNext("sort")) {System.out.println("done");
			return;
			}
			else {
			System.out.println("Incorrect input. Try again.");
			input.nextLine();
			input.nextLine();
			}
		}else {
		
		double value = input.nextDouble();
		array.add(value);
		}
		}
	}
	
	/**
	 * Sorts array, returns median using given function.
	 */
	public double sortAndFindMedian() {
		sort(array);  				// CALL sort(numbers)
		
		int n = array.size();		// DEFINE n AS length of numbers
		
		if(n % 2 == 0) return (array.get(n/2 - 1) + array.get(n/2) / 2);
		else return array.get(n/2);
		
		
	}
	/**
	 * Displays sorted array
	 */
	private void displayResults() {
		if(!array.isEmpty()) {
			System.out.print("SORTED VALUES: [ ");
			if(!array.isEmpty()) for(Double val: array) if(array.indexOf(val) != array.size()-1)System.out.print(val + ", "); else System.out.print(val + " ");
			System.out.println("]\n");
			
			double median = sortAndFindMedian();
			System.out.println("The median of the array is: " + median);
		}
	}

	/**
	 * Sort method using bubble sort.
	 */
	private void sort(ArrayList<Double> list) {
		
		// Bubble sort method
		
	    for (int i = 0; i < list.size() - 1; i++) {
	        for (int j = 0; j < list.size() - 1 - i; j++) {
	            if (list.get(j) > list.get(j + 1)) {
	                // swap
	                double temp = list.get(j);
	                list.set(j, list.get(j + 1));
	                list.set(j + 1, temp);
	            }
	        }
	    }
	}

	
	public static void main(String arg[]) {
		Sort sorter = new Sort();
		
		
	}
	
	
}
