/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralNetwork;

import geneticAlgorithm.DataOutputEvolutionAlgorithm;
import geneticAlgorithm.generationCreators.GenerationCreator;
import geneticAlgorithm.individuals.Individual;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author William Kirby
 */
public class NNEvolutionAlgorithm extends DataOutputEvolutionAlgorithm {
    
    protected NNFitnessCalculation checkerCalculation;
    protected double[] checkerFitnesses;
    protected Individual bestCheckerIndividual;
   
    public NNEvolutionAlgorithm(NNFitnessCalculation fitnessCalculation,NNFitnessCalculation checkerCalculation, GenerationCreator generationCreator, Individual[] individuals,int numberOfGenerations) {
        super(fitnessCalculation, generationCreator, individuals,numberOfGenerations);
        this.checkerCalculation = checkerCalculation;
        this.bestCheckerIndividual = this.bestIndividual.createChild();
        double fitness = this.checkerCalculation.calculateFitness(this.bestCheckerIndividual);
        this.bestCheckerIndividual.setFitness(fitness);
        this.checkerFitnesses = new double[this.maxGenerations];
    }
    
    @Override
    protected void forCurrentGeneration() {
        // check if fitness of the best individual has improved for second data set
        double newFitness = this.checkerCalculation.calculateFitness(this.bestIndividual);
        if (this.bestCheckerIndividual.getFitness() <= newFitness) { // update if new best is found
            this.bestCheckerIndividual = this.bestIndividual.createChild();
            this.bestCheckerIndividual.setFitness(newFitness);
        }
        this.checkerFitnesses[this.currentGeneration] = newFitness;
        super.forCurrentGeneration();
    }
    
    @Override
    protected void atTheStart() {
        super.atTheStart();
    }
    
    public Individual getBestCheckerIndividual() {
        return this.bestCheckerIndividual;
        
    }
    @Override
    protected void printExcelSheet() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("D:\\Documents - HDD\\NetBeansProjects\\EvolutionAlgorithm\\src\\mains\\OutputData.csv");
            fileWriter.append("Generation,Average Fitness,Best Fitness So Far,Best with secondary data");
            for(int x = 0;x < this.maxGenerations;x++) {
                fileWriter.append("\n");
                fileWriter.append(x + "," + this.averageFitnesses[x] + "," + this.bestFitnesses[x] + "," + this.checkerFitnesses[x]);
            }
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("failed to find file");
        }
        
        

    }
}
