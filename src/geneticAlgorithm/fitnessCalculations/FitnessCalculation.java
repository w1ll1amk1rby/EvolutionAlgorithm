
package geneticAlgorithm.fitnessCalculations;

import geneticAlgorithm.individuals.Individual;

/**
 * @author William Kirby
 */
public abstract class FitnessCalculation {
    /**
    * calculate the fitness of the individual
    * @param individual: individual you are calculating
    */
    public abstract double calculateFitness(Individual individual);
    
    /**
    * clean up the individual and change any data that needs to be changed
    * @param individual: individual you are calculating
    */
    public abstract void cleanIndividual(Individual individual);
    
    // Constructor
    public FitnessCalculation() {
        // nothing
    }  
}
