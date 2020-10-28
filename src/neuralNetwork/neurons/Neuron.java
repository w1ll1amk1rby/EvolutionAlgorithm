/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralNetwork.neurons;

/**
 * @author William Kirby
 */
public abstract class Neuron {
    
    protected double storedCharge; // charge stored inside the neuron
    
    /**
    * constructor: create a new random Neuron
    */
    public Neuron() {
        this.storedCharge = 0;
    }
    
    /**
     * add a given charge to the neuron
     * @param charge: charge to add to the neuron
    */
    public void addCharge(double charge) {
        this.storedCharge = this.storedCharge + charge;
    }
    
}
