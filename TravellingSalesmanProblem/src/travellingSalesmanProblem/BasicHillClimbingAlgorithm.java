package travellingSalesmanProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class BasicHillClimbingAlgorithm {
	
	private final int MAX_ITERATIONS = 25;
	private int numCities;
	private Random rand = new Random();
	
	private int[][] tspDistances;

	public BasicHillClimbingAlgorithm(int[][] tspDistances) {
		
		this.tspDistances = tspDistances;
		this.numCities = tspDistances.length;
		
	}
	
	public int solve(int[] solution) {
		
		int i = 0;
		while(i < MAX_ITERATIONS) {
			
			// random initial solution
			int[] newSolution = solution.clone();	
			int city1 = rand.nextInt(numCities-1);
			int tempSwap = newSolution[city1];
			newSolution[city1] = newSolution[city1+1];
			newSolution[city1+1] = tempSwap;
			
			if (calculateTotalDistance(newSolution) < calculateTotalDistance(solution)) {
				solution = newSolution;
				//resets count to 0
				i = 0;
			}
			else {
				i++;
			}
		}
		System.out.println("Final Solution: " + Arrays.toString(solution));
		System.out.println("Final Solution Distance: " + calculateTotalDistance(solution) + "\n");
		
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
	
	public int[] random(){
		ArrayList<Integer> randomSolution = new ArrayList<>();
		
		for (int i = 0; i < numCities; i++) {
			randomSolution.add(i);
			}
		Collections.shuffle(randomSolution);
		
		return randomSolution.stream().mapToInt(i -> i).toArray();
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
