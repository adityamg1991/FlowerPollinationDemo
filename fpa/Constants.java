package com.example.fpa;

import java.text.DecimalFormat;

public interface Constants {
	
	int POPULATION_SIZE = 5;
	float SWITCH_PROBABILITY = 0.25f;
	int NUM_OF_GENERATION = 200;
	
	float A_SOLUTION_UPPER_LIMIT = 50000;
	float B_SOLUTION_UPPER_LIMIT = 50000;
	float C_SOLUTION_UPPER_LIMIT = 50000;
	
	float A_SOLUTION_LOWER_LIMIT = 0;
	float B_SOLUTION_LOWER_LIMIT = 0;
	float C_SOLUTION_LOWER_LIMIT = 0;
	
	int NUM_OF_ELITE_SOLUTIONS = 1;
	
	// int GLOBAL_POLLINATION_L = 2;
	// int LOCAL_POLLINATION_E = 3;
	float MU = 1.83f;
	
	DecimalFormat df = new DecimalFormat("#.#");
	
}
