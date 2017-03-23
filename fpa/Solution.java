package com.example.fpa;

public class Solution {

	private float a, b, c;
	
	public Solution() {
		initialiseSolution();
	}

	public void setA(float a) {
		this.a = a;
	}

	public void setB(float b) {
		this.b = b;
	}

	public void setC(float c) {
		this.c = c;
	}

	private void initialiseSolution() {
		this.a = Constants.A_SOLUTION_LOWER_LIMIT + 
				(float) Math.random() * (Constants.A_SOLUTION_UPPER_LIMIT - Constants.A_SOLUTION_LOWER_LIMIT);
		
		this.b = Constants.B_SOLUTION_LOWER_LIMIT + 
				(float) Math.random() * (Constants.B_SOLUTION_UPPER_LIMIT - Constants.B_SOLUTION_LOWER_LIMIT);
		
		this.c = Constants.C_SOLUTION_LOWER_LIMIT + 
				(float) Math.random() * (Constants.C_SOLUTION_UPPER_LIMIT - Constants.C_SOLUTION_LOWER_LIMIT);
	}

	public float getA() {
		return a;
	}

	public float getB() {
		return b;
	}
	
	public float getC() {
		return c;
	}
	
	public float getCost() {
		return Calculator.calculateValue(this.a, this.b, this.c);
	}
}
