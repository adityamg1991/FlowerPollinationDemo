package com.example.fpa;

public class Population {

	private boolean isSorted = false;
	private Solution solutions[];
	
	public Population() {
		solutions = new Solution[Constants.POPULATION_SIZE];
		initializePopulation();
	}

	private void initializePopulation() {
		for (int i=0; i<Constants.POPULATION_SIZE; i++) {
			Solution sol = new Solution();
			solutions[i] = sol;
		}
	}
	
	public Population getPopulation() {
		return this;
	}
	
	public Solution[] getSolutions() {
		return this.solutions;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<Constants.POPULATION_SIZE; i++) {
			Solution sol = this.getSolutions()[i];
			String str = Constants.df.format(sol.getA()) + ", " 
							+ Constants.df.format(sol.getB()) + ", " + Constants.df.format(sol.getC());
			sb.append("[ " + str + " ]");
			sb.append("[Cost : " + sol.getCost() + "]");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public Population sortPopulationByCost() {
		Population pop = this.getPopulation();
		Solution temp = new Solution();
		for (int i=0; i<Constants.POPULATION_SIZE; i++) {
			for (int j=0; j<Constants.POPULATION_SIZE-1; j++) {
				if (pop.getSolutions()[j].getCost() 
						> pop.getSolutions()[j+1].getCost() ) {
					temp = pop.getSolutions()[j];
					pop.getSolutions()[j] = pop.getSolutions()[j+1];
					pop.getSolutions()[j+1] = temp;
				}
			}
		}
		isSorted = true;
		return pop;
	}
	
	public Solution getBestCostSolution() {
		if (!isSorted) {
			this.sortPopulationByCost();
		}
		return this.solutions[0];
	}
	
}
