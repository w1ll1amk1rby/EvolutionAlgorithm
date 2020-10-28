/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mains;

import geneticAlgorithm.individuals.Individual;
import geneticAlgorithm.generationCreators.TournamentGenerationCreator;
import neuralNetwork.data.DoubleData;
import neuralNetwork.data.DoubleDataSetCreator;
import neuralNetwork.GridNeuralNetwork;
import neuralNetwork.GridNeuralNetworkConvertor;
import neuralNetwork.NNEvolutionAlgorithm;
import neuralNetwork.NNFitnessCalculation;
import neuralNetwork.individual.NNIndividual;

/**
 * @author William Kirby
 */
public class NNMain {

    static final private int NUMBER_OF_INDIVIDUALS = 100;
    static final private double MUTATION_CHANCE = 0.05;
    static final private double CROSSOVER_CHANCE = 0.0;
    static final private int NUMBER_OF_GENERATIONS = 5000;
    static final private int NUMBER_OF_LAYERS = 1;
    static final private int LAYER_DEPTH = 5;
    
    
    public static void main(String[] args) {
        DoubleData[] dataSet = DoubleDataSetCreator.getDataSetFromFile("data3-primary.txt");
        DoubleData[] dataSetTwo = DoubleDataSetCreator.getDataSetFromFile("data3-secondary.txt");
        int numOfOutputs = dataSet[0].getAnswer().length;
        int numOfInputs = dataSet[0].getTest().length;
        
        
        Individual[] individuals = new Individual[NUMBER_OF_INDIVIDUALS];
        for(int x = 0;x < individuals.length; x++ ) {
            GridNeuralNetwork nn = new GridNeuralNetwork(NUMBER_OF_LAYERS,LAYER_DEPTH,numOfInputs,numOfOutputs);
            NNIndividual individual = GridNeuralNetworkConvertor.convertToIndividual(nn);
            individuals[x] = individual;
        }
        
        // create fitness calculation
        NNFitnessCalculation fitnessCalculation = new 
                NNFitnessCalculation(numOfInputs,numOfOutputs,NUMBER_OF_LAYERS,LAYER_DEPTH,dataSet);
        NNFitnessCalculation checkerCalculation = new 
                NNFitnessCalculation(numOfInputs,numOfOutputs,NUMBER_OF_LAYERS,LAYER_DEPTH,dataSetTwo);
        
        // create generationCreator
        TournamentGenerationCreator generationCreator = new 
            TournamentGenerationCreator(MUTATION_CHANCE,CROSSOVER_CHANCE);
        
        
        // create algorithm
        NNEvolutionAlgorithm algorithm = new NNEvolutionAlgorithm(fitnessCalculation,checkerCalculation,generationCreator,individuals,NUMBER_OF_GENERATIONS);
        algorithm.calculate();
        
        
        // test the dataset against the best individual
        GridNeuralNetwork nn = new GridNeuralNetwork(NUMBER_OF_LAYERS,LAYER_DEPTH,numOfInputs,numOfOutputs);
        NNIndividual individual = (NNIndividual) algorithm.getBestCheckerIndividual();
        GridNeuralNetworkConvertor.changeValuesOfNN(nn,individual);
        int correctAnswers = 0;
        for(int i = 0;i < dataSetTwo.length;i++) {
            double[] answer = nn.calculate(dataSetTwo[i].getTest());
            int actualAnswer = dataSetTwo[i].getAnswer()[0];
            int givenAnswer = 0;
            if(answer[0] > 0.5) {
                givenAnswer = 1;
            }
            if(givenAnswer == actualAnswer) {
                correctAnswers++;
            }
            System.out.println((actualAnswer == givenAnswer) + " " + answer[0] + " " + actualAnswer);
        }
        System.out.println((((double) correctAnswers)/ dataSetTwo.length)*100 + "%");
    }
    
    
}
 