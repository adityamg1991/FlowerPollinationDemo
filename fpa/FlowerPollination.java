package com.example.fpa;

import java.util.HashSet;
import java.util.Random;

public class FlowerPollination {
	
	public Population evolvePopulation(Population population) {
		Population evolvedPop = new Population();
		for (int i=0; i<Constants.NUM_OF_ELITE_SOLUTIONS; i++) {
			evolvedPop.getSolutions()[i] = population.getSolutions()[i];
		}
		for (int i=Constants.NUM_OF_ELITE_SOLUTIONS; i<Constants.POPULATION_SIZE; i++) {
			Solution evolvedSolution = new Solution();
			if (Math.random() < Constants.SWITCH_PROBABILITY) {
				// Do global pollination
				evolvedSolution = getSolutionForGlobal(population.getSolutions()[i], 
						population.getBestCostSolution());
			} else {
				// Do local pollination
				Solution randomSols[] = getTwoRandomSolutions(population);
				evolvedSolution = getSolutionForLocal(population.getSolutions()[i], randomSols);
			}
			
			if (evolvedSolution.getCost() < population.getSolutions()[i].getCost()) {
				evolvedPop.getSolutions()[i] = evolvedSolution;
			} else {
				evolvedPop.getSolutions()[i] = population.getSolutions()[i];
			}
			
		}
		return evolvedPop;
	}

	/*private Solution getSolutionForLocal(Solution originalSolution, Solution[] sols) {
		Solution evolvedSol = new Solution();
		
		float a = applyEquationForLocal(originalSolution.getA(), sols[0].getA(), sols[1].getA());
		float b = applyEquationForLocal(originalSolution.getB(), sols[0].getB(), sols[1].getB());
		float c = applyEquationForLocal(originalSolution.getC(), sols[0].getC(), sols[1].getC());
		
		evolvedSol.setA(a);
		evolvedSol.setB(b);
		evolvedSol.setC(c);
		
		return evolvedSol;
	}*/
	
	private Solution getSolutionForLocal(Solution originalSolution, Solution[] sols) {
		Solution evolvedSol = new Solution();
		
		float a;
		for (;;) {
			a = applyEquationForLocal(originalSolution.getA(), sols[0].getA(), sols[1].getA());
			if (a > 0) {
				break;
			}
		}
		
		float b;
		for (;;) {
			b = applyEquationForLocal(originalSolution.getB(), sols[0].getB(), sols[1].getB());
			if (b > 0) {
				break;
			}
		}
		
		float c;
		for (;;) {
			c = applyEquationForLocal(originalSolution.getC(), sols[0].getC(), sols[1].getC());
			if (c > 0) {
				break;
			}
		}
		
		evolvedSol.setA(a);
		evolvedSol.setB(b);
		evolvedSol.setC(c);
		
		return evolvedSol;
	}
	
	
	private Solution[] getTwoRandomSolutions(Population population) {
		HashSet<Integer> selectedSolutions = new HashSet<>();
		selectedSolutions.clear();
		Random random = new Random();
		Solution solOne = null;
		Solution solTwo = null;
		for (;;) {
			int randomIndex = random.nextInt(population.getSolutions().length);
			if (selectedSolutions.contains(randomIndex)) {
				// Do nothing, go to next iteration
			} else {
				selectedSolutions.add(randomIndex);
				if (null == solOne) {
					solOne = population.getSolutions()[randomIndex];
				} else if (null == solTwo) {
					solTwo = population.getSolutions()[randomIndex];
					Solution sols[] = new Solution[2];
					sols[0] = solOne;
					sols[1] = solTwo;
					return sols;
				}
			}
		}
	}

	private Solution getSolutionForGlobal(Solution originalSolution, Solution bestSolutionInPopulation) {
		Solution evolvedSol = new Solution();
		
		float a;
		for (;;) {
			a = applyEquationForGlobal(originalSolution.getA(), bestSolutionInPopulation.getA());
			if (a > 0) {
				break;
			}
		}
		
		float b;
		for (;;) {
			b = applyEquationForGlobal(originalSolution.getB(), bestSolutionInPopulation.getB());
			if (b > 0) {
				break;
			}
		}
		
		float c;
		for (;;) {
			c = applyEquationForGlobal(originalSolution.getC(), bestSolutionInPopulation.getC());
			if (c > 0) {
				break;
			}
		}
		
		evolvedSol.setA(a);
		evolvedSol.setB(b);
		evolvedSol.setC(c);
		
		return evolvedSol;
	}
	
	private float applyEquationForGlobal(float val, float best) {
		// float mul = Constants.GLOBAL_POLLINATION_L;
		float mul = (float) LevyDistribution.sample(Constants.MU);
		
		/*if (answer < 0) {
			answer = - answer;
		}*/
		
		float multiplier = best - val;
		/*if (multiplier < 0) {
			multiplier = - multiplier;
		}*/
		
		float product = mul * multiplier;
		/*if (product < 0) {
			product = - product;
		}*/
		
		// float answer = (val + (mul * (best - val)));
		// float answer = (val + (mul * multiplier));
		float answer = (val + product);
		if (answer < 0) {
			System.out.println("Global giving negative");
		}
		
		return answer;
	}
	
	private float applyEquationForLocal(float orig, float first, float second) {
		// float mul = Constants.LOCAL_POLLINATION_E;
		float mul = (float) Math.random();
		
		/*if (answer < 0) {
			answer = - answer;
		}*/
		
		float multiplier = first - second;
		/*if (multiplier < 0) {
			multiplier = - multiplier;
		}*/
		
		// float answer = (orig + (mul * (first - second)));
		float answer = (orig + (mul * multiplier));
		
		// Giving negative
		if (answer < 0) {
			System.out.println("Local giving negative");
		}
		
		return answer;
	}
	
}
