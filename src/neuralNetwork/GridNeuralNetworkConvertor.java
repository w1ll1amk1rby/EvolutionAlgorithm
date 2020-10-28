/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralNetwork;
import java.util.ArrayList;
import neuralNetwork.connections.NeuralConnection;
import neuralNetwork.individual.NNIndividual;
import neuralNetwork.neurons.HiddenNeuron;

/**
 * @author William Kirby
 */
public class GridNeuralNetworkConvertor {
    
    public static void changeValuesOfNN(GridNeuralNetwork nn,NNIndividual individual) {
        
        // update hiddenLayerFunctions
        int functionIndex = 0;
        int[] functionValues = individual.getFunctions();
        for(int x = 0;x < nn.hiddenLayers.length;x++) {
            for(int i = 0;i < (nn.hiddenLayers[x].length - 1);i++) {
                HiddenNeuron temp = (HiddenNeuron) nn.hiddenLayers[x][i];
                temp.setFunctionType(functionValues[functionIndex]);
                functionIndex++;
            }
        }
        
        // update weights
        int weightIndex = 0;
        double[] weightValues = individual.getWeights();
        //in input layer
        for(int x = 0;x < nn.inputLayer.length;x++) {
            ArrayList<NeuralConnection> connections = nn.inputLayer[x].getConnections(); // start
            for(int i = 0;i < connections.size();i++) { 
                connections.get(i).setWeight(weightValues[weightIndex]);
                weightIndex++;
            } 
        }
        
        // for every hiddenNeuron in the hidden layer 
        for(int x = 0;x < nn.hiddenLayers.length;x++) { // every layer
            for(int i = 0;i < nn.hiddenLayers[x].length;i++) { // for each hiddenNeuron in each layer
                ArrayList<NeuralConnection> connections = nn.hiddenLayers[x][i].getConnections();
                for(int u = 0;u < connections.size();u++) { // for each connection
                    connections.get(u).setWeight(weightValues[weightIndex]);
                    weightIndex++;
                }
            }
        }
    }

    
    
    
    public static NNIndividual convertToIndividual(GridNeuralNetwork nn) {
        
        int numberOfFunctions = (nn.hiddenLayerDepth)*(nn.numberOfHiddenLayers);
        
        int numberOfConnections = 0;
        for(int x = 0;x < nn.inputLayer.length;x++) {
            numberOfConnections = numberOfConnections + nn.inputLayer[x].getConnections().size();
        }
        for(int x = 0;x < nn.hiddenLayers.length;x++) {
            for(int i = 0;i < nn.hiddenLayers[x].length;i++) {
                numberOfConnections = numberOfConnections + nn.hiddenLayers[x][i].getConnections().size();
            }
        }
        
        int[] functions = new int[numberOfFunctions];
        double[] weights = new double[numberOfConnections];
        
        int functionIndex = 0;
        for(int x = 0;x < nn.hiddenLayers.length;x++) {
            for(int i = 0;i < (nn.hiddenLayers[x].length - 1);i++) {
                HiddenNeuron temp = (HiddenNeuron) nn.hiddenLayers[x][i];
                functions[functionIndex] = temp.getFunctionType();
                functionIndex++;
            }
        }
        
        int weightIndex = 0;
        for(int x = 0;x < nn.inputLayer.length;x++) {
            ArrayList<NeuralConnection> connections = nn.inputLayer[x].getConnections(); // start
            for(int i = 0;i < connections.size();i++) { 
                weights[weightIndex] = connections.get(i).getWeight();
                weightIndex++;
            } 
        }
        
        // for every hiddenNeuron in the hidden layer 
        for(int x = 0;x < nn.hiddenLayers.length;x++) { // every layer
            for(int i = 0;i < nn.hiddenLayers[x].length;i++) { // for each hiddenNeuron in each layer
                ArrayList<NeuralConnection> connections = nn.hiddenLayers[x][i].getConnections();
                for(int u = 0;u < connections.size();u++) { // for each connection
                    weights[weightIndex] = connections.get(u).getWeight();
                    weightIndex++;
                }
            }
        }
        return new NNIndividual(weights,functions);
    }
}
