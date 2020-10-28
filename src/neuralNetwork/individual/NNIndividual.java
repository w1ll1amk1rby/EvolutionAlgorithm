/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralNetwork.individual;

import geneticAlgorithm.individuals.Individual;

/**
 * @author William Kirby
 */
public class NNIndividual extends Individual {
    private final double[] weights; // weights of connections
    private final int[] functions; // functions of each neuron
    private final int LARGEST_FUNCTION_INT = 3;
    
    // Constructor
    public NNIndividual(double[] weights,int[] functions) {
        this.weights = weights;
        this.functions = functions;
    }

    @Override
    public String getAsString() {
        String asString = "";
        for(int x = 0;x < this.functions.length;x++) {
            asString = asString + this.functions[x];
        }
        asString = asString + " ";
        for(int x = 0;x < this.weights.length;x++) {
            asString = asString + this.weights[x] + " ";
        }
        return asString;
    }

    @Override
    public void applyMutation(double mutationChance) {
        // Mutate functions
        int largestIntAllowed = LARGEST_FUNCTION_INT;
        for(int x = 0;x < this.functions.length;x++) {
            double mutationValue = Math.random();
            // see if you should mutate
            if(mutationValue < mutationChance) {
                // set to a value it currently isn't 
                int randomInt = (int) (Math.random()*largestIntAllowed);
                if(randomInt == this.functions[x]) {
                    this.functions[x] = largestIntAllowed;
                }
                else {
                    this.functions[x] = randomInt;
                }
            }
        }
        
        // Mutate weights
        for(int x = 0;x < this.weights.length;x++) {
            double mutationValue = Math.random();
            if(mutationValue < mutationChance) { // should mutate
                this.weights[x] = this.weights[x] + ((Math.random()*0.3) - 0.15);
                if(this.weights[x] > 1) this.weights[x] = 1;
                else if(this.weights[x] < -1) this.weights[x] = -1;
            }
        }
    }
    
    @Override
    public void crossOverIndividual(Individual other) {
        NNIndividual tempInd = (NNIndividual) other;
        
        double[] tempWeights = tempInd.getWeights();
        int[] tempFunc = tempInd.getFunctions();
        
        // split functions
        int functionSplit = (int) (Math.random()*this.functions.length);
        for(int x = 0;x < functionSplit;x++) {
            int spare = this.functions[x];
            this.functions[x] = tempFunc[x];
            tempFunc[x] = spare;
        } 
        
        // split weights
        int weightSplit = (int) (Math.random()*this.weights.length);
        for(int x = 0;x < weightSplit;x++) {
            double spare = this.weights[x];
            this.weights[x] = tempWeights[x];
            tempWeights[x] = spare;
        }
    }

    @Override
    public Individual createChild() {
        double[] newWeights = new double[this.weights.length];
        int[] newFunctions = new int[this.functions.length];
        System.arraycopy(this.weights, 0, newWeights, 0, this.weights.length);
        System.arraycopy(this.functions, 0, newFunctions, 0, this.functions.length);
        return new NNIndividual(newWeights,newFunctions);
    }
    
    public double[] getWeights() {
        return this.weights;
    }
    
    public int[] getFunctions() {
        return this.functions;
    }
}
