/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralNetwork.neurons;

/**
 * @author William Kirby
 */
public class HiddenNeuron extends BranchNeuron {
    
    private int functionType;
    public static final int LINEAR = 0;
    public static final int STEP = 1;
    public static final int EXPONENTIAL = 2;
    public static final int SIGMOID = 3;
    
    public HiddenNeuron() {
        this.functionType = (int) (Math.random()*4);
    }
    
    @Override
    protected double getOutput() {
        switch(this.functionType) {
            case LINEAR:
                return this.storedCharge;
            case STEP:
                if(this.storedCharge > 0) {
                    return 1;
                } 
                else {
                    return 0;
                }
            case EXPONENTIAL:
                return this.storedCharge*this.storedCharge;
            case SIGMOID:
                return 1/(1 + Math.pow(Math.E,-1*this.storedCharge));
            default:
                return 0;
        }
    }
    
    public int getFunctionType() {
        return this.functionType;
    } 
    public void setFunctionType(int functionType) {
        this.functionType = functionType;
    }
    
}