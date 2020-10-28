/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticAlgorithm.individuals;

/**
 * @author William Kirby
 */
abstract public class IntArrayIndividual extends Individual {
    
    protected int[] dna; // dna 
    
    /**
     * returns the maximum int allowed in array
     * @return Max int that can be represented in array
    */
    protected abstract int getMaxIntValue();
    
    public IntArrayIndividual(int[] dna) {
        this.dna = dna;
    }
    
    @Override
    public String getAsString() {
        String asString = "";
        for(int x = 0;x < this.dna.length;x++) {
            asString = asString + this.dna[x];
        }
        return asString;
    }

    @Override
    public void applyMutation(double mutationChance) {
        int largestIntAllowed = this.getMaxIntValue();
        for(int x = 0;x < this.dna.length;x++) {
            double mutationValue = Math.random();
            // see if you should mutate
            if(mutationValue < mutationChance) {
                int randomInt = (int) (Math.random()*largestIntAllowed);
                if(randomInt == this.dna[x]) {
                    this.dna[x] = largestIntAllowed;
                }
                else {
                    this.dna[x] = randomInt;
                }
            }
        }
    }

    @Override
    public void crossOverIndividual(Individual other) {
        // find a crossover point and switch each half over
        IntArrayIndividual spareInd = (IntArrayIndividual) other;
        int[] dnaTwo = spareInd.getDNA();
        int crossOverPoint = (int) (Math.random()*this.dna.length);
        for(int x = crossOverPoint;x < this.dna.length;x++) {
            int spare = this.dna[x];
            this.dna[x] = dnaTwo[x]; 
            dnaTwo[x] = spare;
        }
    }

    public int[] getDNA() {
        return this.dna;
    }
    
}
