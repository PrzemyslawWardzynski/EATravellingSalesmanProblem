import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Individual {

	
	public ArrayList<Integer> genotype;
	
	public Individual(int genotypeLength) {


		genotype = new ArrayList<Integer>(genotypeLength);
		for(int i = 0; i < genotypeLength; i++) {
			genotype.add(i);
		}
		Collections.shuffle(genotype);
	}
	
	public Individual(Individual other) {
		
		genotype = new ArrayList<Integer>(other.getGenotype());
		
	}
	
	
	
	public void mutateSwap(float mutProb) {
		
		for(int i = 0; i < genotype.size(); i++) {
			
			if(Math.random() < mutProb) {
				
				int firstGene = genotype.get(i);
				int swapIndex = ThreadLocalRandom.current().nextInt(0,genotype.size());
		
				genotype.set(i, genotype.get(swapIndex));
				genotype.set(swapIndex, firstGene);	
			}
		}		
	}
	
	public void mutateInverse() {
		int start = ThreadLocalRandom.current().nextInt(0,genotype.size());
		int end = ThreadLocalRandom.current().nextInt(0,genotype.size());
		
		int startIndex = Math.min(start, end);
		int endIndex = Math.max(start, end);
		
		while(startIndex <= endIndex) {
			int tmp = genotype.get(startIndex);
			genotype.set(startIndex, genotype.get(endIndex));
			genotype.set(endIndex,tmp);
			startIndex++;
			endIndex--;
		}
		
		
		
	}
	
	
	public void crossCX(Individual other) {
		
		ArrayList<Integer> child1Genotype = new ArrayList<Integer>(other.genotype);
		ArrayList<Integer> child2Genotype = new ArrayList<Integer>(this.genotype);
		
		for(int i = 0; i < other.genotype.size(); i++) {
			child1Genotype.set(i,-1);
			child2Genotype.set(i,-1);
		}
		
		child1Genotype.set(0,this.genotype.get(0));
		child2Genotype.set(0,other.genotype.get(0));
		int i = 0;
		
		do {
			int j = this.genotype.indexOf(other.genotype.get(i)); 
			child1Genotype.set(j, this.genotype.get(j));
			child2Genotype.set(j, other.genotype.get(j));
			i = j;
		}while(!child1Genotype.contains(other.genotype.get(i)));
		
		
		for(int k = 0 ; k < child1Genotype.size(); k++) {
		
			if(child1Genotype.get(k) == -1) {
				child1Genotype.set(k,other.genotype.get(k));	
			}
			if(child2Genotype.get(k) == -1) {
				child2Genotype.set(k,this.genotype.get(k));
			}
		}
		this.genotype = child1Genotype;
		other.genotype = child2Genotype;		
	}
	
	
	public void show() {
		for(int i = 0; i < genotype.size(); i++) {
			System.out.printf("%d,", genotype.get(i));
		}
		System.out.println();
	}
	
	public ArrayList<Integer> getGenotype(){
		return genotype;
	}
	
	
	public static void main(String[] args) {
		
	

		
	}

}
