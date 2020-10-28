/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralNetwork.neurons;

/**
 * @author William Kirby
 */
public class InputNeuron extends BranchNeuron {
    
    /**
    * constructor: create a new InputNeuron with no connections
    */
    public InputNeuron() {
        super();
    }

    /**
     * sets the value of the input 
     * @param input: value to set input as.
    */    
    public void setInput(double input) {
        this.storedCharge = input;
    }

    @Override
    protected double getOutput() {
        return this.storedCharge;
    }
}
