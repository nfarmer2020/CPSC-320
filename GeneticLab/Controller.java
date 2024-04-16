import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Controller {
	Population population = new Population(0.02);
    
	private int numOfChromosomes = 100;
    ArrayList<int[]> pointsList = new ArrayList<int[]>();

    public static void main(String args[]) {
        Controller controller = new Controller();
        controller.go();
    }

    public void go() {
        addPoints();
        makeChromosome();
        
        Line best = null;
        for (int i = 0; i <= 100000; i++) {
            best = (Line) population.evaluate();
            double fit = best.getFitness();
            System.out.println("Gen : " + i + " fitness : " + fit);
            if (fit >= 0.99999) {
                System.out.println("The best fitness is : ");
                break;
            }
            population.breed();
        }
       
        System.out.println("Best Fitness: " + best.getFitness());
        
        System.out.println("X =  " + best.getNumberA());
        
        System.out.println("Y =   " + best.getNumberB());
        
        System.out.println("B = : " + best.getNumberC());
    }

    public void addPoints() {
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextInt()) {
                int point[] = new int[2];
                point[0] = scan.nextInt();
                point[1] = scan.nextInt();
                pointsList.add(point);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        
        
        
        }
    }

    public void makeChromosome() {
        
    	for (int i = 0; i < numOfChromosomes; i++) {
            Line chromosome = new Line(51, pointsList);
            chromosome.randomize();
            while (chromosome.getFitness() == 0) {
                chromosome.randomize();
            }
            System.out.println("CHROME " + chromosome.toString());
            population.addChromosome(chromosome);
        }
    }
}

