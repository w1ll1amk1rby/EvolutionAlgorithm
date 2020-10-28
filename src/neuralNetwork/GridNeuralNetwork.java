/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralNetwork;

import neuralNetwork.neurons.BiasNeuron;
import neuralNetwork.neurons.BranchNeuron;
import neuralNetwork.neurons.HiddenNeuron;
import neuralNetwork.neurons.InputNeuron;
import neuralNetwork.neurons.OutputNeuron;

/**
 * @author William Kirby
 */
public class GridNeuralNetwork {
    
    protected final BranchNeuron[][] hiddenLayers; // hiddenLayers making up the network
    protected final OutputNeuron[] outputLayer; // the output neurons
    protected final BranchNeuron[] inputLayer; // the inputLayer
    protected final int numberOfInputs;
    protected final int numberOfOutputs;
    protected final int numberOfHiddenLayers;
    protected final int hiddenLayerDepth;
    
    /**
     * creates a random neuralNetwork of specified size
     * @param numberOfHiddenLayers: number of layers in the network
     * @param hiddenLayerDepth: hiddenLayers on each layer
     * @param numberOfInputs: number of inputLayer
     * @param numberOfOutputs: number of outputLayer
    */
    public GridNeuralNetwork(int numberOfHiddenLayers,int hiddenLayerDepth, int numberOfInputs,int numberOfOutputs) {    
        this.numberOfInputs = numberOfInputs;
        this.numberOfOutputs = numberOfOutputs;
        this.numberOfHiddenLayers = numberOfHiddenLayers;
        this.hiddenLayerDepth = hiddenLayerDepth;
        //create the input neurons
        this.inputLayer = new BranchNeuron[numberOfInputs + 1];
        this.populateInputs();
        //create the output neurons
        this.outputLayer = new OutputNeuron[numberOfOutputs];     
        this.populateOutputs();
        //create the hiddenLayer
        this.hiddenLayers = new BranchNeuron[numberOfHiddenLayers][hiddenLayerDepth + 1];
        this.populateHiddenLayers();
        // establish connections with random weights
        this.establishConnections();
    }
    
    /**
    * populate lists with neurons but no connections
    */
    private void populateInputs() {
        for(int x = 0;x < inputLayer.length - 1;x++) {
            this.inputLayer[x] = new InputNeuron();
        }
        this.inputLayer[inputLayer.length - 1] = new BiasNeuron();
    }
    private void populateOutputs() {
        for(int x = 0;x < this.outputLayer.length;x++) {
            this.outputLayer[x] = new OutputNeuron();
        }
    }
    private void populateHiddenLayers() {
        for(int x = 0;x < this.hiddenLayers.length;x++) {
            for(int i = 0;i < (this.hiddenLayers[x].length - 1);i++) {
                this.hiddenLayers[x][i] = new HiddenNeuron();
            }
            this.hiddenLayers[x][this.hiddenLayerDepth] = new BiasNeuron();
        }  
    }
    
    /**
    * create connections
    */
    private void establishConnections() {
        // link inputLayer to the to hidden layer
        // start
        final int firstLayer = 0;
        for(int start = 0;start < this.inputLayer.length;start++) {
            //destination
            for(int destination = 0;destination < (this.hiddenLayers[firstLayer].length - 1);destination++) {
                double weight = (Math.random()*2) - 1;
                this.inputLayer[start].connect(this.hiddenLayers[firstLayer][destination],weight);
            }
        }
        //connect up the hidden layer
        // for every layer except the last
        for(int x = 0;x < (this.hiddenLayers.length - 1);x++) {
            //start
            for(int start = 0;start < this.hiddenLayers[x].length;start++) {
                //destination
                for(int destination = 0;destination < (this.hiddenLayers[x + 1].length - 1);destination++) {
                    double weight = (Math.random()*2) - 1;
                    this.hiddenLayers[x][start].connect(this.hiddenLayers[x + 1][destination],weight);
                }
            }
        }
        int lastLayer = this.hiddenLayers.length - 1;
        // start
        for(int start = 1;start < this.hiddenLayers[lastLayer].length;start++) {
            // destination
            for(int destination = 0;destination < this.outputLayer.length;destination++) {
                double weight = (Math.random()*2) - 1;
                this.hiddenLayers[lastLayer][start].connect(this.outputLayer[destination], weight);
            }
        }
    }
    
    /**
     * calculate the output of the neural network for a given input
     * @param inputValues: inputValues to the neuralNetwork
     * @return outputLayer of the neural network
    */
    public double[] calculate(double[] inputValues) {
        //set input layer
        for(int x = 0;x < (this.inputLayer.length - 1);x++) {
            InputNeuron inputNeuron = (InputNeuron) this.inputLayer[x];
            inputNeuron.setInput(inputValues[x]);
        }
        // flush inputLayer in to the network
        for(int x = 0;x < this.inputLayer.length;x++) {
            this.inputLayer[x].flush();
        }
        // flush through the hidden layers
        for(int x = 0;x < this.hiddenLayers.length;x++) { // each layer
            for(int i = 0;i < this.hiddenLayers[x].length;i++) {
                this.hiddenLayers[x][i].flush();
            }
        }
        
        double[] outputValues = new double[this.outputLayer.length];
        for(int x = 0;x < this.outputLayer.length;x++) {
            outputValues[x] = this.outputLayer[x].getAndResetValue();
        }
        
        return   outputValues;
    }
}
