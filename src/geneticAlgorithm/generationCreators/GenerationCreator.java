
package geneticAlgorithm.generationCreators;

import geneticAlgorithm.individuals.Individual;

/**
 * @author William Kirby
 */
public abstract class GenerationCreator {
    
        protected final double chanceToMutate;
        private final double chanceToCrossOver;
        
        // used to create the next generation of individuals depending on the type of generation creator
        public abstract Individual[] getNextGeneration(Individual[] individuals,Individual bestIndividual);
        
        public GenerationCreator(double chanceToMutate,double chanceToCrossOver) {
            this.chanceToMutate = chanceToMutate;
            this.chanceToCrossOver = chanceToCrossOver;
        }
        
        protected void applyCrossOverToGeneration(Individual[] individuals)
        {
            for(int x = 0;x < individuals.length - 1;x = x + 2)
            {
                double randomNumber = Math.random();
                if(randomNumber >= this.chanceToCrossOver) {
                    individuals[x].crossOverIndividual(individuals[x + 1]);
                }
            }
        }
}
