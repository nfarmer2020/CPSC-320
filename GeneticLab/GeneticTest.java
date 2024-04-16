import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

public class GeneticTest {

	@Test
    public void testAddChromosome() {
       
        Population p = new Population(0.2);
        
        ArrayList<int[]> points = new ArrayList<>();
        points.add(new int[]{1, 2});
        
        Chromosome c = new Line(51, points);
       
        p.addChromosome(c);

       
        if (p.getChromosomeListSize() != 1) {
            fail("List should return one chromosome"); 
        }
    }

	@Test
    public void testGetFitness() {
        
        ArrayList<int[]> points = new ArrayList<>();
        points.add(new int[]{1, 2});
        Chromosome c = new Line(51, points);

        
        double fitness = c.getFitness();
        if (fitness < 0 || fitness > 1) {
            fail("Fitness is out of range");
        }
    }
	@Test
    public void testEvaluate() {
        Population population = new Population(0.2);
        Chromosome chromosome1 = new Line(51, new ArrayList<int[]>());
        Chromosome chromosome2 = new Line(51, new ArrayList<int[]>());
        population.addChromosome(chromosome1);
        population.addChromosome(chromosome2);
        Chromosome bestChromosome = population.evaluate();
        if (bestChromosome == null) {
            fail("A suitable chromosome was not found");
        }
    }
	@Test
    public void testBreed() {
        Population population = new Population(0.2);
        Chromosome chromosome1 = new Line(51, new ArrayList<int[]>());
        Chromosome chromosome2 = new Line(51, new ArrayList<int[]>());
        population.addChromosome(chromosome1);
        population.addChromosome(chromosome2);
        population.breed();
        if (population.getChromosomeListSize() != 2) {
            fail("Population changed after breeding");
        }
    }
	 @Test
	    public void testMakeChromosome() {
	       
	        Controller controller = new Controller();

	       
	        controller.pointsList.add(new int[]{1, 2});
	        controller.pointsList.add(new int[]{3, 4});

	        
	        controller.makeChromosome();

	        
	        if (controller.population.getChromosomeListSize() != 100) { 
	            fail("Chromosomes were not created right");
	        }
	    }
	 @Test
	    public void testMutate() {
	        
	        BinaryChromosome binaryChromosome = new BinaryChromosome(10);

	        
	        for (int i = 0; i < 10; i++) {
	            binaryChromosome.bits[i] = 1;
	        }

	      
	        binaryChromosome.mutate(0.5); 

	        
	        boolean hasChanged = false;
	        for (int i = 0; i < 10; i++) {
	            if (binaryChromosome.bits[i] != 1) {
	                hasChanged = true;
	                break;
	            }
	        }

	        if (!hasChanged) {
	            fail("Mutation did not work");
	        }
	    }
	 @Test
	    public void testCrossover() {
	       
	        BinaryChromosome parent1 = new BinaryChromosome(10);
	        BinaryChromosome parent2 = new BinaryChromosome(10);

	       
	        for (int i = 0; i < 10; i++) {
	            parent1.bits[i] = 1;
	            parent2.bits[i] = 0;
	        }

	    
	        BinaryChromosome newChromosome = (BinaryChromosome) parent1.crossover(parent2);

	      
	        boolean hasMixedBits = false;
	        for (int i = 0; i < 10; i++) {
	            if (newChromosome.bits[i] != parent1.bits[i]) {
	                hasMixedBits = true;
	                break;
	            }
	        }

	        if (!hasMixedBits) {
	            fail("Crossover did not work");
	        }
	    }
}