import java.io.FileWriter;
import java.io.IOException;

public class Logger {

	
	private static int LOOP_COUNT = 10;
	
	
	public void testPop(){
		
		double args[][] = {
				{100,100,0.7,0.7,0.05},
				{300,100,0.7,0.7,0.05},
				{500,100,0.7,0.7,0.05}
		}; 
			
		String files [] = {"kroa100","kroa150","kroa200"}; 
		
		
		
		for(int j = 0; j < files.length; j++) {
			
			String fileName = "TSP/" +files[j]+".tsp";
			Loader load = new Loader();
			TSPProblem problem = new TSPProblem(load.loadData(fileName));
			
			for(int k = 0; k < args.length; k++ ) {
				
				int popSize = (int) args[k][0];
				int genCnt = (int) args[k][1];
				float crossProb = (float)args[k][2];
				float mutProb = (float)args[k][3];
				int tourSize = (int)(popSize * args[k][4]) ;
				
				
				
				String toCSVName = "popSize_"+popSize+"_"+files[j]+".csv";
				
				for(int i = 0 ; i < LOOP_COUNT; i++){
					
					EvolutionaryAlgorithm EA = new EvolutionaryAlgorithm(popSize, genCnt, crossProb, mutProb, tourSize, problem);
					try {
						EA.start(toCSVName,true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			}
		}
		
		
	}
	
	public void testGen(){
		
		double args[][] = {
				{500,300,0.7,0.7,0.05},
				{500,500,0.7,0.7,0.05},
				{500,700,0.7,0.7,0.05}
		}; 
		
		
		String files [] = {"kroa100","kroa150","kroa200"}; 
			
		
	
		for(int j = 0; j < files.length; j++) {
			
			String fileName = "TSP/" +files[j]+".tsp";
			Loader load = new Loader();
			TSPProblem problem = new TSPProblem(load.loadData(fileName));
			
			for(int k = 0; k < args.length; k++ ) {
				
				int popSize = (int) args[k][0];
				int genCnt = (int) args[k][1];
				float crossProb = (float)args[k][2];
				float mutProb = (float)args[k][3];
				int tourSize = (int)(popSize * args[k][4]) ;
				
				
				
				String toCSVName = "genCnt_"+genCnt+"_"+files[j]+".csv";
				
				for(int i = 0 ; i < LOOP_COUNT; i++){
					System.out.println(toCSVName + "loop: " + i);
					EvolutionaryAlgorithm EA = new EvolutionaryAlgorithm(popSize, genCnt, crossProb, mutProb, tourSize, problem);
					try {
						EA.start(toCSVName,true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			}
		}
		
	}
	
	public void testSelect() {
		
		double args[][] = {
				{500,400,0.7,0.7,0.25},
				//{500,400,0.7,0.7,0.50},
				//{500,400,0.7,0.7,0.75}
		};  
				
		
		String files [] = {"kroa100","kroa150","kroa200"}; 
			
		
		
		for(int j = 0; j < files.length; j++) {
			
			String fileName = "TSP/" +files[j]+".tsp";
			Loader load = new Loader();
			TSPProblem problem = new TSPProblem(load.loadData(fileName));
			
			for(int k = 0; k < args.length; k++ ) {
				
				int popSize = (int) args[k][0];
				int genCnt = (int) args[k][1];
				float crossProb = (float)args[k][2];
				float mutProb = (float)args[k][3];
				int tourSize = (int)(popSize * args[k][4]) ;
				
				
				//String toCSVName = "select_"+args[k][4]+"_"+files[j]+".csv";
				String toCSVName = "roulette_"+files[j]+".csv";
				
				for(int i = 0 ; i < LOOP_COUNT; i++){
					System.out.println(toCSVName + "loop: " + i);
					EvolutionaryAlgorithm EA = new EvolutionaryAlgorithm(popSize, genCnt, crossProb, mutProb, tourSize, problem);
					try {
						EA.start(toCSVName,false);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			}
		}
		
	}
	
	public void testCross(){

		double args[][] = {
				{500,400,0.7,0.9,0.5},
				{500,400,0.8,0.9,0.5},
				{500,400,0.9,0.9,0.5}
		}; 
			
		
		String files [] = {"kroa100","kroa150","kroa200"}; 
			
		
	
		for(int j = 0; j < files.length; j++) {
			
			String fileName = "TSP/" +files[j]+".tsp";
			Loader load = new Loader();
			TSPProblem problem = new TSPProblem(load.loadData(fileName));
			
			for(int k = 0; k < args.length; k++ ) {
				
				int popSize = (int) args[k][0];
				int genCnt = (int) args[k][1];
				float crossProb = (float)args[k][2];
				float mutProb = (float)args[k][3];
				int tourSize = (int)(popSize * args[k][4]) ;
				
				
				
				String toCSVName = "cross_"+crossProb+"_"+files[j]+".csv";
				
				for(int i = 0 ; i < LOOP_COUNT; i++){
					System.out.println(toCSVName + "loop: " + i);
					EvolutionaryAlgorithm EA = new EvolutionaryAlgorithm(popSize, genCnt, crossProb, mutProb, tourSize, problem);
					try {
						EA.start(toCSVName,true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			}
		}
		
		
	}
	
	
	public void testMut(){
		

		double args[][] = {
				{500,400,0.9,0.2,0.5},
				{500,400,0.9,0.5,0.5},
				{500,400,0.9,0.9,0.5}
		}; 
			
		
		String files [] = {"kroa100","kroa150","kroa200"}; 
		
		
		
		for(int j = 0; j < files.length; j++) {
			
			String fileName = "TSP/" +files[j]+".tsp";
			Loader load = new Loader();
			TSPProblem problem = new TSPProblem(load.loadData(fileName));
			
			for(int k = 0; k < args.length; k++ ) {
				
				int popSize = (int) args[k][0];
				int genCnt = (int) args[k][1];
				float crossProb = (float)args[k][2];
				float mutProb = (float)args[k][3];
				int tourSize = (int)(popSize * args[k][4]) ;
				
				
				
				String toCSVName = "mut_"+mutProb+"_"+files[j]+".csv";
				
				for(int i = 0 ; i < LOOP_COUNT; i++){
					System.out.println(toCSVName + "loop: " + i);
					EvolutionaryAlgorithm EA = new EvolutionaryAlgorithm(popSize, genCnt, crossProb, mutProb, tourSize, problem);
					try {
						EA.start(toCSVName,true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			}
		}
		
		
		
		
	}
	
	public void testFINAL(){
		
		double args[][] = {
				{500,1000,0.9,0.9,0.5},
			
		}; 
				
		
		String files [] = {/*"berlin52","kroa100","kroa150","kroa200",*/"fl417"}; 
		
		
	
		for(int j = 0; j < files.length; j++) {
			
			String fileName = "TSP/" +files[j]+".tsp";
			Loader load = new Loader();
			TSPProblem problem = new TSPProblem(load.loadData(fileName));
			
			for(int k = 0; k < args.length; k++ ) {
				
				int popSize = (int) args[k][0];
				int genCnt = (int) args[k][1];
				float crossProb = (float)args[k][2];
				float mutProb = (float)args[k][3];
				int tourSize = (int)(popSize * args[k][4]) ;
				
				
				
				String toCSVName = "instance_" + files[j]+".csv";
				
				for(int i = 0 ; i < LOOP_COUNT; i++){
					System.out.println(toCSVName + "loop: " + i);
					EvolutionaryAlgorithm EA = new EvolutionaryAlgorithm(popSize, genCnt, crossProb, mutProb, tourSize, problem);
					try {
						EA.start(toCSVName,true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			}
		}
	}
	
	public void testGreedy() {
		
		String files [] = {"berlin52","kroa100","kroa150","kroa200","fl417"}; 

		
	
		for(int j = 0; j < files.length; j++) {
			
			String fileName = "TSP/" +files[j]+".tsp";
			Loader load = new Loader();
			TSPProblem problem = new TSPProblem(load.loadData(fileName));
			
			String toCSVName = "greedy_" + files[j]+".csv";
			try {
				FileWriter csvWriter = new FileWriter(toCSVName);
				
				for(int k = 0; k < problem.getCitiesCount(); k++) {
					String dataRow = String.format("%10.6f\n", problem.greedy(k)); 
					csvWriter.append(dataRow);
				}
				csvWriter.flush();
				csvWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void testRandom() {
		
		String files [] = {"berlin52","kroa100","kroa150","kroa200","fl417"}; 

		
		
		for(int j = 0; j < files.length; j++) {
			
			String fileName = "TSP/" +files[j]+".tsp";
			Loader load = new Loader();
			TSPProblem problem = new TSPProblem(load.loadData(fileName));
			String toCSVName = "random_" + files[j]+".csv";
			System.out.println("random_"+toCSVName);
			try {
				FileWriter csvWriter = new FileWriter(toCSVName);
				
				for(int k = 0; k < 10000; k++) {
					String dataRow = String.format("%10.6f\n", problem.random(1)); 
					csvWriter.append(dataRow);
				}
				csvWriter.flush();
				csvWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {

			//Logger a = new Logger();
			//a.testPop();
			//a.testGen();
			//a.testSelect();
			//a.testCross();
			//a.testMut();
			//a.testFINAL();
			//a.testGreedy();
			//a.testRandom();
	}

}
