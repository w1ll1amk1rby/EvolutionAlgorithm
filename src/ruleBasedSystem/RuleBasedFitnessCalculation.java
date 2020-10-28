/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruleBasedSystem;

import ruleBasedSystem.Rule;
import geneticAlgorithm.individuals.Individual;
import geneticAlgorithm.fitnessCalculations.FitnessCalculation;
import java.util.ArrayList;

/**
 * @author William Kirby
 */
public class RuleBasedFitnessCalculation extends FitnessCalculation {
    
    private final int numberOfRules;
    private final int numberOfInputs;
    private final int numberOfOutputs;
    private final int ruleLength;
    private final Data[] testData;
    
    // Constructor
    public RuleBasedFitnessCalculation(Data[] testData,
            int numberOfRules,int numberOfInputs,int numberOfOutputs) {
        this.testData = testData;
        this.numberOfRules = numberOfRules;
        this.numberOfInputs = numberOfInputs;
        this.numberOfOutputs = numberOfOutputs;
        this.ruleLength = numberOfInputs + numberOfOutputs;
    }
    
    @Override
    public double calculateFitness(Individual individual) {
        Rule[] rules = this.convertToRuleSet(individual);
        double fitness = 0;
        // for every data set
        for(int x = 0;x < this.testData.length;x++) {
            int[] answer = this.getAnswerFromInput(rules, this.testData[x].getData());
            // if the answer matches the actual data output then fitness increases
            if(this.testData[x].matchesAnswer(answer)) {
                fitness = fitness + 1;
            }
        }
        return fitness;
    }

    @Override
    public void cleanIndividual(Individual individual) {
        RuleBasedIndividual tempInd = (RuleBasedIndividual) individual;
        int[] dna = tempInd.getDNA();
        for(int x = 0;x < numberOfRules;x++ ) {
            int startPoint = this.numberOfInputs + (x*(this.ruleLength));
            for(int i = startPoint;i < (startPoint + this.numberOfOutputs);i++) {
                if(dna[i] == 2) {
                    dna[i] = (int) (Math.random()*2);
                }
            }
        }
    }
    
    
    private int[] getAnswerFromInput(Rule[] rules, int[] input) {
            ArrayList<Rule> passedTests = new ArrayList<>();
            // get a list of all the past tests
            for(int x = 0;x < rules.length;x++) {
                if(rules[x].passesTheTest(input)) {
                      passedTests.add(rules[x]);
                }
            }
            // if no tests were passed
            if(passedTests.isEmpty()) {
                int[] basicAnswer = new int[this.numberOfOutputs];
                return basicAnswer;
            }
            // if a test was passed then pick one by random
            else {
                int chosenIndex = (int) (Math.random()*passedTests.size());
                return rules[chosenIndex].getAnswer();
            }
    }
    
    private Rule[] convertToRuleSet(Individual individual) {
        RuleBasedIndividual tempInd = (RuleBasedIndividual) individual;
        int[] dna = tempInd.getDNA();
        Rule[] rules = new Rule[this.numberOfRules];
        
        
        for(int x = 0;x < this.numberOfRules;x++) {
            int[] rule = new int[this.ruleLength];
            int startPoint = (x*this.ruleLength);
            int endPoint = this.ruleLength + (x*this.ruleLength);
            for(int i = startPoint;i < endPoint;i++) {
                rule[i - startPoint] = dna[i];
            }
            rules[x] = new Rule(rule,this.numberOfInputs,this.numberOfOutputs);
        }
        return rules;
    }
}
