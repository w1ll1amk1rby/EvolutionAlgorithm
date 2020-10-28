
package geneticAlgorithm;

import geneticAlgorithm.individuals.Individual;
import geneticAlgorithm.fitnessCalculations.FitnessCalculation;
import geneticAlgorithm.generationCreators.GenerationCreator;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author William Kirby
 */
public class DataOutputEvolutionAlgorithm extends EvolutionAlgorithm {

    protected int currentGeneration;
    protected final int maxGenerations; 
    protected final double[] averageFitnesses;
    protected final double[] bestFitnesses;

    public DataOutputEvolutionAlgorithm(FitnessCalculation fitnessCalculation,
            GenerationCreator generationCreator,Individual[] individuals,int numberOfGenerations){
        super(fitnessCalculation,generationCreator,individuals);
        this.currentGeneration = 0;
        this.maxGenerations = numberOfGenerations;
        this.averageFitnesses = new double[maxGenerations];
        this.bestFitnesses =  new double[maxGenerations];
    }
    
    @Override
    protected boolean shouldEnd() {
        return this.currentGeneration >= this.maxGenerations;
    }

    @Override
    protected void forCurrentGeneration() {
        this.printIndividuals();
        this.averageFitnesses[this.currentGeneration] = this.calculateAverageFitness();
        this.bestFitnesses[this.currentGeneration] = this.bestIndividual.getFitness();
        this.currentGeneration++;
    }

    @Override
    protected void atTheEnd() {
        this.printReport();
        this.printBestIndividual();
        this.printExcelSheet();
    }
    
    @Override
    protected void atTheStart() {
        // do nothing
    }
    
    
    
    protected void printIndividuals() {
       System.out.println("generation: " + this.currentGeneration);
//        for(int x = 0;x < this.individuals.length;x++) {
//            String dnaString = this.individuals[x].getAsString();
//            System.out.print("individual " + x + "\t" + dnaString);
//            System.out.print(" " + this.individuals[x].getFitness());
//            System.out.println("");
//        }
//        System.out.println("");
    }
    
    protected void printBestIndividual() {
        String dnaString = this.bestIndividual.getAsString();
        System.out.println("");
        System.out.println("best individual: " + dnaString + " " + this.bestIndividual.getFitness());
    }
    
    protected double calculateAverageFitness() {
        return this.calculateTotalFitness()/this.individuals.length;
    }
    
    /**
    * print final report
    */
    protected void printReport() {
        System.out.println("");
        System.out.println("Report");
        System.out.println("Average Fitness for Each Generation");
        for(int x = 0;x < maxGenerations;x++) {
            System.out.print(x + "\t| ");
            for(int i = 0;i < this.averageFitnesses[x];i++) {
                System.out.print("[]");
            }
            System.out.println(" " + this.averageFitnesses[x]);
        }
    }  
    
    
    public Individual getBestIndividual() {
        return this.bestIndividual;   
    }

    protected void printExcelSheet() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("D:\\Documents - HDD\\NetBeansProjects\\EvolutionAlgorithm\\src\\mains\\OutputData.csv");
            fileWriter.append("Generation,Average Fitness,Best Fitness So Far");
            for(int x = 0;x < this.maxGenerations;x++) {
                fileWriter.append("\n");
                fileWriter.append(x + "," + this.averageFitnesses[x] + "," + this.bestFitnesses[x]);
            }
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("failed to find file");
        }
        
        

    }
}
