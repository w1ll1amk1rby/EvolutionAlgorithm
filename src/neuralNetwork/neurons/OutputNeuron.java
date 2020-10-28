/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralNetwork.neurons;

/**
 * @author William Kirby
 */
public class OutputNeuron extends Neuron {
    
    /**
     * @author William Kirby
    */
    public OutputNeuron() {
        super();
    }
    
    /**
     * retrieve the stored charge (doesn't reset it).
     * @return stored value.
    */
    public double getValue() {
        return this.storedCharge;
    }
    
    /**
     * wipes the stored charge ready for reuse.
    */
    public void resetValue() {
        this.storedCharge = 0;
    }
    
    /**
     * gets the value and resets it for recalculating
     * @return value that was stored
    */
    public double getAndResetValue() {
        double value = this.storedCharge;
        this.storedCharge = 0;
        return value;
    }
}
