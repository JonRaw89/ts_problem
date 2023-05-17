package travellingSalesmanProblem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TravellingSalesmanProblem {	 

	public static int[][] readSalesmanData() throws IOException {
		
	    ArrayList<int[]> list = new ArrayList<>();
	    File fileObj = new File("src/travellingSalesmanProblem/data.txt");
	   
	    try (Scanner scanner = new Scanner(fileObj)) {
			while (scanner.hasNextLine()) {
				String[] line = scanner.nextLine().trim().split(",");
				int[] distances = new int[line.length];
				for (int i = 0; i < line.length; i++) {
					distances[i] = Integer.parseInt(line[i]);
				}
				list.add(distances);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	    int [][] tspDistances = list.toArray(new int[0][]);
	    
	    return tspDistances;
	}
	
	 public static void main(String[] args) throws IOException {
		   
		 BasicHillClimbingAlgorithm bhc = new BasicHillClimbingAlgorithm(readSalesmanData());
		 
		 
		 for (int i = 0; i < 10; i++) {
			 // Random Solution
			 
			 bhc.solveRandom();
			 
			 // Greedy Solution
			 bhc.solveGreedy();
		 }
	 }
}
