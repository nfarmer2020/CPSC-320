import java.util.ArrayList;
import java.util.Random;

public class Population {
	private ArrayList<Chromosome> chromosomes;
    private double mutationRate;
    private Random random = new Random();

    public Population(double mutationRate) {
        this.mutationRate = mutationRate;
        chromosomes = new ArrayList<>();
    }

    public void addChromosome(Chromosome c) {
        chromosomes.add(c);
    }

    public Chromosome evaluate() {
        double bestFitness = chromosomes.get(0).getFitness();
        Chromosome bestChromosome = chromosomes.get(0);
        for (int i = 1; i < chromosomes.size(); i++) {
            double chromosomeFitness = chromosomes.get(i).getFitness();
            if (chromosomeFitness > bestFitness) {
                bestFitness = chromosomeFitness;
                bestChromosome = chromosomes.get(i);
            }
        }
        return bestChromosome;
    }

    public void breed() {
        double worstFitness = worstFitness().getFitness();
        ArrayList<Chromosome> ticketArray = new ArrayList<>();
        for (Chromosome chromosome : chromosomes) {
            int numOfTicket = (int) (chromosome.getFitness() / worstFitness);
            for (int j = 0; j < numOfTicket; j++) {
                ticketArray.add(chromosome);
            }
        }

        ArrayList<Chromosome> newList = new ArrayList<>();
        for (int i = 0; i < chromosomes.size(); i++) {
            Chromosome newChromosome;
            do {
                Chromosome parentOne = ticketArray.get(random.nextInt(0, ticketArray.size() - 1));
                Chromosome parentTwo = ticketArray.get(random.nextInt(0, ticketArray.size() - 1));
                newChromosome = parentOne.crossover(parentTwo);
                newChromosome.mutate(mutationRate);
            } while (newChromosome.getFitness() == 0);
            newList.add(newChromosome);
        }
        chromosomes = newList;
    }

    public void setMutationRate(double rate) {
        mutationRate = rate;
    }

    private Chromosome worstFitness() {
        double worstFitness = chromosomes.get(0).getFitness();
        Chromosome worstChromosome = chromosomes.get(0);
        for (Chromosome chromosome : chromosomes) {
            if (chromosome.getFitness() < worstFitness) {
                worstFitness = chromosome.getFitness();
                worstChromosome = chromosome;
            }
        }
        return worstChromosome;
    }

    int getChromosomeListSize() {
        return chromosomes.size();
    }
}

