/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruleBasedSystem;

/**
 * @author William Kirby
 */
public class Rule {
    
    private int[] test;
    private int[] answer;
    
    // Constructor
    public Rule(int[] ruleData,int testLength,int answerLength) {
        this.test = new int[testLength];
        this.answer = new int[answerLength];
        
        for(int x = 0;x < testLength;x++) {
            this.test[x] = ruleData[x]; 
        }
        for(int x = testLength;x < (testLength + answerLength);x++) {
            this.answer[x - testLength] = ruleData[x]; 
        }
    }
    
    public boolean passesTheTest(int[] data) {
        
        for(int x = 0 ; x < data.length ; x++ ) {
            if((this.test[x] != 2) && (data[x] != this.test[x])) {
                return false;
            }
        }
        
        return true;
    }
    
    public int[] getAnswer() {
        return this.answer;
    
    }
}
