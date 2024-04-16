
public interface Chromosome {
	Chromosome crossover(Chromosome other);
    double getFitness();
    void mutate(double mutationRate);
}

