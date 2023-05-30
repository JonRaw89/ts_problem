package travellingSalesmanProblem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TravellingSalesmanProblem {	 

	public static int[][] readSalesmanFileData() throws IOException {
		
	    ArrayList<int[]> list = new ArrayList<>();
	    File fileObj = new File("C:/Users/jpraw/git/ts_problem/TravellingSalesmanProblem/src/travellingSalesmanProblem/data.txt");
	   
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
	
	public static int[][] readSalesmanCsvData() throws IOException {
		
        List<double[]> coordinates = new ArrayList<>();

        Path path = Paths.get("C:/Users/jpraw/git/ts_problem/TravellingSalesmanProblem/src/travellingSalesmanProblem/TSP_matrix.csv");
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            String[] parts = line.split(",");
            double x = Double.parseDouble(parts[0]);
            double y = Double.parseDouble(parts[1]);
            coordinates.add(new double[]{x, y});
        }

        int size = coordinates.size();        
        int[][] tspDistances = new int[size][size];

        // work out euclidean distances between coords.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    tspDistances[i][j] = 0;
                } else {
                    double[] ci = coordinates.get(i);
                    double[] cj = coordinates.get(j);
                    double distance = Math.sqrt(Math.pow(ci[0] - cj[0], 2) + Math.pow(ci[1] - cj[1], 2));
                    tspDistances[i][j] = (int) Math.round(distance);
                }
            }
        }

        return tspDistances;
    }
	    
	
	
	public static void main(String[] args) throws IOException {
		   
		 BasicHillClimbingAlgorithm bhc = new BasicHillClimbingAlgorithm(readSalesmanFileData());
		 
		 int averageRandom = 0;
		 int averageGreedy = 0;
		 
		 int[] random = bhc.random();
		 int[] greedy = bhc.greedy();
		 
//		 for (int i = 0; i < 10; i++) {
//			 
//			 // Random Solution
//			 averageRandom += bhc.solve(random);
//
//			 // Greedy Solution
//			 averageGreedy += bhc.solve(greedy);
//		 }
//		 
//		 System.out.println("Average Random distance: " + averageRandom/10);
//		 System.out.println("Average Greedy distance: " + averageGreedy/10);
		 
		 SteepestAscentAlgorithm steep = new SteepestAscentAlgorithm(readSalesmanCsvData());
		 int[] greedySteep = steep.greedy();
		 
		 
		 steep.solve(greedySteep);
		 
	 }
}
