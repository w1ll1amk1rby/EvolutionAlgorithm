
package geneticAlgorithm;
import geneticAlgorithm.individuals.Individual;
import geneticAlgorithm.fitnessCalculations.FitnessCalculation;
import geneticAlgorithm.generationCreators.GenerationCreator;

/**
 * @author William Kirby
 */
public abstract class EvolutionAlgorithm {
    
    protected final FitnessCalculation fitnessCalculation;
    protected final GenerationCreator generationCreator;
    protected Individual[] individuals;
    protected Individual bestIndividual;
    
    /**
    * use this to stop after a given generation
    * @return boolean for if it should end
    */
    protected abstract boolean shouldEnd(); 
    
    /**
    * use this to do anything for the current generation
    */
    protected abstract void forCurrentGeneration();
    
    /**
    * when all calculations have ended
    */   
    protected abstract void atTheEnd();
    
    /**
    * before any calculations start
    */
    protected abstract void atTheStart();
    
    // Constructor
    public EvolutionAlgorithm(FitnessCalculation fitnessCalculation,GenerationCreator generationCreator,Individual[] individuals) {
        this.fitnessCalculation = fitnessCalculation;
        this.generationCreator = generationCreator;
        this.individuals = individuals;
        this.bestIndividual = individuals[0];
        this.calculateFitnesses();
        this.updateBest();
    }
    
    /**
    * start computing the genetic algorithm
    */
    public void calculate() {   
        this.atTheStart();
        while(true) {
            this.forCurrentGeneration();
            if(this.shouldEnd()) {
                break;
            }
            this.individuals = this.generationCreator.getNextGeneration(this.individuals,this.bestIndividual);
            this.calculateFitnesses();
            this.updateBest();
        }
        this.atTheEnd();
    }
    
    private void calculateFitnesses() {
        for(int x = 0;x < this.individuals.length;x++) {
            this.fitnessCalculation.cleanIndividual(this.individuals[x]);
            double fitness = this.fitnessCalculation.calculateFitness(this.individuals[x]);
            this.individuals[x].setFitness(fitness);
        } 
    }
    
    /**
     * @return total fitness of current generation
    */
    protected double calculateTotalFitness() {
        double totalFitness = 0;
        for(int x = 0;x < this.individuals.length;x++) {
            totalFitness = totalFitness + this.individuals[x].getFitness();
        }
        return totalFitness;
    }  

    private void updateBest() {
        for(int x = 0;x < this.individuals.length;x++) {
            if(this.individuals[x].getFitness() > this.bestIndividual.getFitness()) {
                this.bestIndividual = this.individuals[x];
            }
        }
    }   
}
