/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralNetwork.data;

/**
 * @author William Kirby
 */
 public class DoubleData {
    private int[] answer;
    private double[] test;
    
    public DoubleData(double[] test,int[] answer) {
        this.answer = answer;
        this.test = test;
    }
    
    public int[] getAnswer() {
        return this.answer;
    }
    
    public double[] getTest() {
        return this.test;
    }
}
