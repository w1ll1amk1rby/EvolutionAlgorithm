/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralNetwork.neurons;

/**
 *
 * @author William Kirby
 */
public class BiasNeuron extends BranchNeuron {

    @Override
    protected double getOutput() {
        return 1;
    }
    
}
