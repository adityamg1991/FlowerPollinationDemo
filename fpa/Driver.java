package com.example.fpa;

public class Driver {

	private static int generationNumber = 1;
	
	public static void main(String args[]) {
		
		FlowerPollination flowerPollination = new FlowerPollination();
		Population initialPopulation = new Population();
		initialPopulation.sortPopulationByCost();
		
		for (int i=0; i<Constants.NUM_OF_GENERATION; i++) {
			printPopulation(initialPopulation);
			generationNumber++;
			initialPopulation = flowerPollination.evolvePopulation(initialPopulation);
			initialPopulation.sortPopulationByCost();
		}
		
	}
	
	private static void printPopulation(Population pop) {

		System.out.println("-----------------------");
		System.out.println("Generation : " + generationNumber);
		System.out.println("-----------------------");	
		System.out.println(pop.toString());
	}
	
}
