/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralNetwork.neurons;

import java.util.ArrayList;
import neuralNetwork.connections.NeuralConnection;

/**
 * @author William Kirby
 */
public abstract class BranchNeuron extends Neuron {
    
    ArrayList<NeuralConnection> connections;
      
    /**
     * constructor: create a new BranchNeuron with no connections
    */
    public BranchNeuron() {
        super();
        this.connections = new ArrayList<>();
    }
    
    /**
     * connect this neuron to another neuron with a given weight
     * @param neuron: add the neuron 
     * @param weight: weight of the connection (output*weight)
    */
    public void connect(Neuron neuron,double weight) {
        connections.add(new NeuralConnection(neuron,weight));
    }
    
    /**
     * pass the charge through the connections and then remove the charge.
    */  
    public void flush() {
        // send charge down each connection
        for(int x = 0;x < this.connections.size();x++) {
            this.connections.get(x).passCharge(this.getOutput());
        }
        this.storedCharge = 0;
    }
 
    /**
     * get list of current connections from neuron
     * @return ArrayList of NeuralConnections
    */  
    public ArrayList<NeuralConnection> getConnections() {
        return this.connections;
    }
    
    /**
     * get list of current connections from neuron
     * @return ArrayList of NeuralConnections
    */  
    protected abstract double getOutput();
}
