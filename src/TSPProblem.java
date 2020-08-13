import java.util.ArrayList;
import java.util.Collections;

public class TSPProblem {

	private double [][] distanceMatrix;
	private int citiesCount;
	
	
	public TSPProblem(ArrayList<Point> citiesCoords) {
		int citiesCount = citiesCoords.size();
		this.citiesCount = citiesCount;
		distanceMatrix = new double[citiesCount][citiesCount];
		
		for(int i = 0 ; i<citiesCount; i++) {
			
			Point startCity = citiesCoords.get(i);
			
			for(int j = 0 ; j<citiesCount; j++) {
			
				Point endCity = citiesCoords.get(j);
				
				double distance = Math.sqrt((startCity.x - endCity.x) * (startCity.x - endCity.x) +
									(startCity.y - endCity.y) * (startCity.y - endCity.y));
				
				distanceMatrix[i][j] = distance;
			}	
		}
	}
	
	public double getFitness(ArrayList<Integer> genotype) {
		double sum = 0;
		
		for(int i = 0 ; i < genotype.size()-1; i++) {
			int city = genotype.get(i);
			int nextCity = genotype.get(i+1);
			sum += distanceMatrix[city][nextCity];
		}
		sum += distanceMatrix[genotype.get(0)][genotype.get(genotype.size()-1)];
		
		return sum;
	}
	
	public double random(int loopCount) {
		ArrayList<Integer> genotype = new ArrayList<Integer>(distanceMatrix.length);
		for(int j = 0; j < distanceMatrix.length ; j++) {
			genotype.add(j,j);
		}
		double bestFit = Double.MAX_VALUE;
		double fit;
		for(int i = 0; i < loopCount ; i++) {
			Collections.shuffle(genotype);
			fit = getFitness(genotype);
			if( fit < bestFit) {
				bestFit = fit;
			}
		}
		return bestFit;
	}
	
	
	
	public double greedy(int startCity) {
		
		ArrayList<Integer> genotype = new ArrayList<Integer>(distanceMatrix.length);
		genotype.add(0, startCity);
	
		int currentCity = startCity;
		int nextCity = -1;
		int i = 1;
		while(i < distanceMatrix.length) {
			
			double minDistance = Double.MAX_VALUE;
			for(int j = 0; j < distanceMatrix.length; j++) {
				double currDistance = distanceMatrix[currentCity][j];
				//System.out.println(currDistance);
				if(currDistance < minDistance && currDistance != 0 && !genotype.contains(j)) {
					minDistance = currDistance;
					nextCity = j;
			
				}
			}
			currentCity = nextCity;
		
			genotype.add(i, nextCity);
			i++;
		}
		
		
		return getFitness(genotype);
	}
	
	public int getCitiesCount() {
		return citiesCount;
	}
	
		
	public void showDistanceMatrix() {
		for(int i = 0; i < distanceMatrix.length; i++) {
			for (int j = 0; j < distanceMatrix.length; j++) {
				
				System.out.printf("%4.2f ",distanceMatrix[i][j]);
			}
			System.out.println();
		}
	}
	
}
