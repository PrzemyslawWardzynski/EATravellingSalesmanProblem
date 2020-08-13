import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class EvolutionaryAlgorithm {

	
	
	private ArrayList<Individual> generation;
	private TSPProblem problem;
	private double[] probability;
	private int genCnt;
	private int tourSize;
	private int popSize;
	private float crossProb;
	private float mutProb;
	
	
	public EvolutionaryAlgorithm(int popSize, int genCnt, float crossProb, float mutProb, int tourSize, TSPProblem problem) {
		
		generation = new ArrayList<Individual>(popSize);
		int citiesCount = problem.getCitiesCount();
		for(int i = 0; i < popSize; i++) {
			generation.add(new Individual(citiesCount));
		}
		this.problem = problem;
		this.genCnt = genCnt;
		this.popSize = popSize;
		this.tourSize = tourSize;
		this.crossProb = crossProb;
		this.mutProb = mutProb;
	}
	
	public void start(String outFileName, boolean selectTour) throws IOException {
		
		int currentGeneration = 0;
		FileWriter csvWriter = new FileWriter(outFileName,true);
		
	
		
		
		while(currentGeneration < genCnt) {
		
			ArrayList <Individual>newGeneration = new ArrayList<Individual>(problem.getCitiesCount());
			
			probability = new double[popSize];
			double constE = 2;
			double totalFitness = 0;
			double worstFitnessP = 0;
			for(int i = 0 ; i < generation.size(); i++) {
				
				double fitness = problem.getFitness(generation.get(i).getGenotype());
				probability[i] = -fitness + constE;
				if(fitness > worstFitnessP) {
					worstFitnessP = fitness;
				}	
			}
			
			for(int i = 0 ; i < probability.length; i++) {
				probability[i] += worstFitnessP;
				totalFitness += probability[i];
			}
			

			for(int i = 0 ; i<probability.length;i++) {
				probability[i] /= totalFitness;
			}
			
			
			
			
			
			while(newGeneration.size() != generation.size()) {
				
				
				int firstParentIndex;
				int secondParentIndex;
				
				if(selectTour) {
					firstParentIndex  = selectTour();
					secondParentIndex = selectTour();
				}
				else
				{
					firstParentIndex = selectRoulette();
					secondParentIndex = selectRoulette();
				}
				Individual firstParent = new Individual(generation.get(firstParentIndex));
				Individual secondParent = new Individual(generation.get(secondParentIndex));
		
				if(Math.random() < crossProb) {
				
					firstParent.crossCX(secondParent);
				}
				
				if(Math.random() < mutProb) {
					
					firstParent.mutateInverse();
				}
				if(Math.random() < mutProb) {
					
					secondParent.mutateInverse();
				}
				
				newGeneration.add(firstParent);
				newGeneration.add(secondParent);		
			}
			
			generation = newGeneration;
			
			
			double sum = 0;
			double worstFitness = 0;
			double bestFitness = Double.MAX_VALUE;
			
			for(int i = 0 ; i < generation.size(); i++) {
				
				double fitness = problem.getFitness(generation.get(i).getGenotype());
				sum += fitness;
				if(fitness > worstFitness) {
					worstFitness = fitness;
				}
				if(fitness < bestFitness) {
					bestFitness = fitness;
				}
				
			}
			double avg = sum/popSize;
			//System.out.printf("GEN:%3d | BestF:%4.3f | Avg:%4.3f | Worst:%4.3f\n",currentGeneration,bestFitness,avg,worstFitness);
			String dataRow = String.format("%d;%10.6f;%10.6f;%10.6f\n",currentGeneration,bestFitness,avg,worstFitness);
			csvWriter.append(dataRow);
			currentGeneration++;
		}
		csvWriter.flush();
		csvWriter.close();
		
	}
	
	
	public int  selectTour() {
		
		double bestFitness = Double.MAX_VALUE;
		int bestIndex = 0;
		
		for(int i = 0; i < tourSize; i++) {
			
			int selectIndex = ThreadLocalRandom.current().nextInt(0, popSize);
			double fitness = problem.getFitness(generation.get(selectIndex).getGenotype());
			if ( fitness < bestFitness) {
				bestFitness = fitness;
				bestIndex = selectIndex;
			}
		
		}
		return bestIndex;
	}
	
	public int selectRoulette() {
		
		
		double offset = 0.0;
		int index = 0;
		double random = Math.random();
	
		for(int i = 0; i<probability.length;i++) {
			offset += probability[i];
			if(random < offset) {
				index = i;
				return index;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		
		String fileName = "TSP/kroa100.tsp";
		Loader load = new Loader();
		TSPProblem problem = new TSPProblem(load.loadData(fileName));
		int genCnt = 400;
		int popSize = 500;
		int tourSize = (int)(popSize * 0.5) ;
		float crossProb = 0.9f;
		float mutProb = 0.9f;
		EvolutionaryAlgorithm EA = new EvolutionaryAlgorithm(popSize, genCnt, crossProb, mutProb, tourSize, problem);
		try {
			EA.start("TESTESTSETETSET.csv",false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.printf("ZACHLANNY:%4.3f\n",problem.greedy(ThreadLocalRandom.current().nextInt(0, problem.getCitiesCount())));
		//System.out.printf("LOSOWY:%4.3f\n",problem.random(popSize*genCnt));
	
	
	}

}
