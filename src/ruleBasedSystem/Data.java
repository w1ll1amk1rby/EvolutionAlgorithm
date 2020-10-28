/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruleBasedSystem;

/**
 * @author William Kirby
 */
public class Data {
    private int[] answer;
    private int[] data;
    
    public Data(int[] data,int[] answer) {
        this.answer = answer;
        this.data = data;
    }
    
    public int[] getAnswer() {
        return this.answer;
    }
    
    public int[] getData() {
        return this.data;
    }
    
    public boolean matchesAnswer(int[] testAnswer) {
        for(int x = 0;x < this.answer.length;x++) {
            if(this.answer[x] != testAnswer[x]) {
                return false;
            } 
        }
        return true;
    }
}
