
package geneticAlgorithm.individuals;

/**
 * @author William Kirby
 */
public abstract class Individual {
    
    private double fitness;
    
    // Constructor
    public Individual() {
        this.fitness = 0;
    }
  
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
    public double getFitness() {
        return this.fitness;
    }
    
    /**
     * get the individuals DNA in the form of a string
     * @return Individual as string
    */
    public abstract String getAsString();
    /**
     * apply the mutation chance to the individual
     * @param mutationChance: the chance of a mutation per index
    */
    public abstract void applyMutation(double mutationChance);
    /**
     * apply the mutation chance to the individual
     * @param other: the individual to cross over with
    */
    public abstract void crossOverIndividual(Individual other);
    
    /**
     * create a child from the individual 
     * @return , new child
    */
    public abstract Individual createChild(); 

}
