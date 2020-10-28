/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruleBasedSystem;

import geneticAlgorithm.individuals.Individual;
import geneticAlgorithm.individuals.IntArrayIndividual;

/**
 * @author William Kirby
 */
public class RuleBasedIndividual extends IntArrayIndividual {

    int maxIntValue;
    
    public RuleBasedIndividual(int[] dna,int maxIntValue) {
        super(dna);
        this.maxIntValue = maxIntValue;
    }

    @Override
    protected int getMaxIntValue() {
        return this.maxIntValue;
    }

    @Override
    public Individual createChild() {
        int[] newDna = new int[this.dna.length];
        for(int x = 0;x < this.dna.length;x++) {
            newDna[x] = this.dna[x];
        } 
        return new RuleBasedIndividual(newDna,this.maxIntValue);
    }
    
}
