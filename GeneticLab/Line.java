import java.util.ArrayList;

public class Line  extends BinaryChromosome {
	 private ArrayList<int[]> pointList;

	    public Line(int numbits, ArrayList<int[]> pointList) {
	        super(numbits);
	        this.pointList = pointList;
	    }

	    @Override
	    public double getFitness() {
	        double fitness = 0;
	        double A = getNumberA();
	        double B = getNumberB();
	        double C = getNumberC();

	        for (int i = 0; i < pointList.size(); i++) {
	            int X = pointList.get(i)[0];
	            int Y = pointList.get(i)[1];
	            double distance = Math.abs((A * X) + (B * Y) + C) / Math.sqrt((A * A) + (B * B));
	            fitness += distance;
	        }

	        return 1 / (1 + fitness);
	    }

	    @Override
	    public Chromosome crossover(Chromosome other) {
	        if (!(other instanceof Line)) {
	            throw new IllegalArgumentException("The chromosomes do not match!");
	        }
	        Line otherChromosome = (Line) other;
	        Line newChromosome = new Line(bits.length, pointList);
	        super.crossover(otherChromosome, newChromosome);
	        return newChromosome;
	    }

	    double getNumberA() {
	        int sign = super.getNumber(0, 0);
	        int numerator = super.getNumber(1, 8);
	        int denominator = super.getNumber(9, 16);

	        if (sign == 0) {
	            sign = -1;
	        }

	        double numberA = 0.0;
	        if (denominator != 0 && numerator != 0) {
	            numberA = (double) numerator / denominator;
	            numberA = (double) numberA * sign;
	        }

	        return numberA;
	    }

	    double getNumberB() {
	        int sign = super.getNumber(17, 17);
	        int numerator = super.getNumber(18, 25);
	        int denominator = super.getNumber(26, 33);

	        if (sign == 0) {
	            sign = -1;
	        }

	        double numberB = 0;
	        if (denominator != 0 && numerator != 0) {
	            numberB = (double) numerator / denominator;
	            numberB = (double) numberB * sign;
	        }
	        return numberB;
	    }

	    double getNumberC() {
	        int sign = super.getNumber(34, 34);
	        int numerator = super.getNumber(35, 42);
	        int denominator = super.getNumber(43, 50);

	        if (sign == 0.0) {
	            sign = -1;
	        }

	        double numberC = 0;
	        if (denominator != 0 && numerator != 0) {
	            numberC = (double) numerator / denominator;
	            numberC = (double) numberC * sign;
	        }
	        return numberC;
	    }
	}

