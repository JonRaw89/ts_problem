package travellingSalesmanProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SteepestAscentAlgorithm {
	
	private final int MAX_ITERATIONS = 100;
	private int numCities;
	
	private int[][] tspDistances;
	private Random rand = new Random();

	public SteepestAscentAlgorithm(int[][] tspDistances) {
		
		this.tspDistances = tspDistances;
		this.numCities = tspDistances.length;
		
	}
	
	
	public int solve(int[] solution) {
		
	    int i = 0;
	    
	    while(i < MAX_ITERATIONS) {
	    	
	        int[] newSolution = null;	
	        int bestDistance = calculateTotalDistance(solution);

	        for (int city = 0; city < numCities - 1; city++) {
	            int[] tempSolution = solution.clone();	
	            int tempSwap = tempSolution[city];
	            
	            tempSolution[city] = tempSolution[city+1];
	            tempSolution[city+1] = tempSwap;
	            
	            int tempDistance = calculateTotalDistance(tempSolution);

	            if (tempDistance < bestDistance) {
	                bestDistance = tempDistance;
	                newSolution = tempSolution;
	            }
	        }
	        if (newSolution != null) {
	            solution = newSolution;
	            i = 0;
	            
	        } else {
	            i++;
	        }
	    }
	    System.out.println("Steepest Final Solution: " + Arrays.toString(solution));
	    System.out.println("Steepest Final Solution Distance: " + calculateTotalDistance(solution) + "\n");

	    return calculateTotalDistance(solution);
	}
	
	public int[] greedy() {
		
		ArrayList<Integer> visitedCities = new ArrayList<>();
		int[] solution = new int[numCities];		
		
		int city = rand.nextInt(numCities);
		solution[0] = city;
		visitedCities.add(city);
		
		for (int i = 1; i < numCities; i++) {
			int nxtCity = findNextCity(city, visitedCities);
			solution[i] = nxtCity;
			visitedCities.add(nxtCity);
			city = nxtCity;
		}

		return solution;
		
	}	
	
	public int findNextCity(int city, ArrayList<Integer> visitedCities) {
		
		int nearest = -1;
		int shortestDist = Integer.MAX_VALUE;
		
		for(int i = 0; i < numCities; i++) {
			
			// check city not self and not visited
			if (i != city && !visitedCities.contains(i)) {
				
				// assign distance between cities
				int distance = tspDistances[city][i];
				
				// nearest city assigned if found
				if (distance < shortestDist) {
					shortestDist = distance;
					nearest = i;
				}
				
			}
		}
		return nearest;
		
	}
	
	public int calculateTotalDistance(int[] solution) {
		
		int totalDistance = 0;
		
		// adds all distances
		for (int i = 0; i < numCities-1; i++) {
			totalDistance += tspDistances[solution[i]][solution[i+1]];
		}
		// adds returns to starting city
		totalDistance += tspDistances[solution[numCities-1]][solution[0]];
		
		return totalDistance;
	}
	
	
}
