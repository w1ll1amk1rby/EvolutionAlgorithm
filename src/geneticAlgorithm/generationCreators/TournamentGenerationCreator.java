
package geneticAlgorithm.generationCreators;
import geneticAlgorithm.individuals.Individual;


/**
 * @author William Kirby
 */
public class TournamentGenerationCreator extends GenerationCreator {
    
    // Constructor
    public TournamentGenerationCreator(double chanceToMutate,double chanceToCrossOver) {
        super(chanceToMutate,chanceToCrossOver);
    }

    @Override
    public Individual[] getNextGeneration(Individual[] individuals,Individual bestIndividual) {
        Individual[] offSpring = new Individual[individuals.length];
        // get best individual so far and place in to the new generation
        offSpring[0] = bestIndividual.createChild();
        // select two random individuals and at the best to the new generation
        for(int x = 1;x < individuals.length;x++) {
            int parentOneIndex = (int) (Math.random()*individuals.length);
            int parentTwoIndex = (int) (Math.random()*individuals.length);
            if(individuals[parentOneIndex].getFitness() <= individuals[parentTwoIndex].getFitness()) {
                offSpring[x] = individuals[parentTwoIndex].createChild();
            }
            else {
                offSpring[x] = individuals[parentOneIndex].createChild();
            }
        }
        // apply mutation to each individual
        for(int x = 0;x < offSpring.length;x++) {
            offSpring[x].applyMutation(this.chanceToMutate);
        }
        // apply crossover
        this.applyCrossOverToGeneration(offSpring);
        return offSpring;  
    }
    
}
