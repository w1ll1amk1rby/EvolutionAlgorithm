/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mains;

import geneticAlgorithm.DataOutputEvolutionAlgorithm;
import geneticAlgorithm.EvolutionAlgorithm;
import geneticAlgorithm.individuals.Individual;
import geneticAlgorithm.generationCreators.TournamentGenerationCreator;
import ruleBasedSystem.Data;
import ruleBasedSystem.DataSetCreator;
import ruleBasedSystem.RuleBasedFitnessCalculation;
import ruleBasedSystem.RuleBasedIndividual;



/**
 * @author William Kirby
 */
public class RuleBasedMain {

    static final private int NUMBER_OF_INDIVIDUALS = 1000;
    static final private double MUTATION_CHANCE = 0.005;
    static final private double CROSSOVER_CHANCE = 0.2;
    static final private int LARGEST_INT_ALLOWED = 1;
    static final private int NUMBER_OF_RULES = 5;
    static final private int NUMBER_OF_GENERATIONS = 100;
    
    
    public static void main(String[] args) {
        
        // get data from file
        Data[] dataSet = DataSetCreator.getDataSetFromFile("data1.txt");
        int outputsLength = dataSet[0].getAnswer().length;
        int inputsLength = dataSet[0].getData().length;
        
        int dnaLength = NUMBER_OF_RULES*(outputsLength + inputsLength);
        
        // create individuals
        Individual[] individuals = new Individual[NUMBER_OF_INDIVIDUALS];
        for(int x = 0;x < individuals.length; x++ ) {
            int[] dna = new int[dnaLength];
            for(int i = 0;i < dnaLength;i++) {
                dna[i] = (int) (Math.random()*(LARGEST_INT_ALLOWED + 1));
            }
            individuals[x] = new RuleBasedIndividual(dna,LARGEST_INT_ALLOWED);
        }
        
        // create fitness calculation
        RuleBasedFitnessCalculation fitnessCalculation = new 
            RuleBasedFitnessCalculation(dataSet,NUMBER_OF_RULES ,inputsLength,outputsLength);
        
        // create generationCreator
        TournamentGenerationCreator generationCreator = new 
            TournamentGenerationCreator(MUTATION_CHANCE,CROSSOVER_CHANCE);
        
        // create algorithm
        EvolutionAlgorithm algorithm = new DataOutputEvolutionAlgorithm(fitnessCalculation,generationCreator,individuals,NUMBER_OF_GENERATIONS);
        algorithm.calculate();
    }
}
