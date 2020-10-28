/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralNetwork;

import neuralNetwork.data.DoubleData;
import geneticAlgorithm.individuals.Individual;
import geneticAlgorithm.fitnessCalculations.FitnessCalculation;
import neuralNetwork.individual.NNIndividual;

/**
 * @author William Kirby
 */
public class NNFitnessCalculation extends FitnessCalculation {
    protected final int numberOfInputs;
    protected final int numberOfOutputs;
    protected final int numberOfHiddenLayers;
    protected final int hiddenLayerDepth;
    protected final GridNeuralNetwork nn;
    protected final DoubleData[] dataSet;
    
    public NNFitnessCalculation(int numberOfInputs,int numberOfOutputs,
    int numberOfHiddenLayers,int hiddenLayerDepth,DoubleData[] dataSet) {
        super();
        this.hiddenLayerDepth = hiddenLayerDepth;
        this.numberOfHiddenLayers = numberOfHiddenLayers;
        this.numberOfInputs = numberOfInputs;
        this.numberOfOutputs = numberOfOutputs;
        this.dataSet = dataSet;
        this.nn = new GridNeuralNetwork(numberOfHiddenLayers,hiddenLayerDepth,numberOfInputs,numberOfOutputs);
    }
    
    @Override
    public double calculateFitness(Individual individual) {
        NNIndividual tempInd = (NNIndividual) individual;
        GridNeuralNetworkConvertor.changeValuesOfNN(this.nn,tempInd);// update nn with weights and functions
        double totalDifference = 0;
        for(int x = 0;x < dataSet.length;x++) { // for every dataSet
            double difference = getTotalAnswerDifference(dataSet[x]);
            totalDifference = totalDifference + difference;
        }
        
        return totalDifference/this.dataSet.length;
        
    }
    
    private double getTotalAnswerDifference(DoubleData data) {
        double[] test = data.getTest();
        int[] actualAnswer = data.getAnswer();
        double[] predictedAnswer = nn.calculate(test);
        // find differences
        double totalDifference = 0;
        for(int i = 0;i < actualAnswer.length;i++) { // for each answer
            double difference = 0;
            difference = predictedAnswer[i] - actualAnswer[i];
            if(difference > 0) {
                difference = -1*difference;
            }
            totalDifference = totalDifference + difference;
        } 
        return totalDifference;
    }

    @Override
    public void cleanIndividual(Individual individual) {
        // doNothing
    }
    
}
