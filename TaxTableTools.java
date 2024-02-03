package edu.pasadena.cs.cs03b;

import java.util.Scanner;

public class TaxTableTools {

	// TODO: Declare the private salary and tax_rate tables as arrays of int 
	private int[] salaryRanges; // Upper bounds of salary ranges
    private int[] taxRates; // Corresponding tax rates for each range
	// ***********************************************************************

	// TODO: Implements Default constructor that initializes the private salary and tax_rate tables
	public TaxTableTools() {
		salaryRanges = new int[]{0, 11000, 44725, 95375, 182100, 231250, 578125, Integer.MAX_VALUE};
        taxRates = new int[]{0, 10, 12, 22, 24, 32, 35, 37};
	}
	// ***********************************************************************

	// TODO: Write a void setter method that sets new values for the private
	// salary and tax_rate tables to override default salary and tax_rate tables
	// ***********************************************************************
	public void setTaxTable(int[] ranges, int[] rates) {
        salaryRanges = ranges;
        taxRates = rates;
    }

	// TODO: Method to get a tax rate from salary table based on income
	public double getTaxRate(double annualIncome) {
		double result = 0;
		for (int i = 0; i < salaryRanges.length; i++) {
			if (annualIncome <= salaryRanges[i]) {
                result = ((double )taxRates[i]) / 100 ;
				break;
            }
        }
		return result;
	}

    // TODO: Method to get a tax owned based on income and tax rate
	public double calculateTax(double annualIncome) {
		double result = 0;
		double temp = annualIncome;
		int index = salaryRanges.length - 1;
		while(temp != 0){
			if(temp > salaryRanges[index]) {
				double taxRate = getTaxRate(temp);
				result += (temp - salaryRanges[index]) * taxRate;
				//System.out.println(temp + " - " + salaryRanges[index] + " = "+ (temp - salaryRanges[index]) + " * " + taxRate + " = " + (temp - salaryRanges[index]) * taxRate);
				temp = salaryRanges[index];
			}
			index--;	
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO: Add your code here for interactive console mode
        Scanner input = new Scanner(System.in);
		// Salary and tax rate table
		int[] ranges = new int[]{0, 11000, 44725, 95375, 182100, 231250, 578125, Integer.MAX_VALUE};
        int[] rates = new int[]{0, 10, 12, 22, 24, 32, 35, 37};
		// Create an instance of the class with default salary and tax_rate tables
		TaxTableTools taxTableTools = new TaxTableTools();
		// override default salary and tax_rate tables
		taxTableTools.setTaxTable(ranges, rates);

		while (true) {
			System.out.print("Enter annual income (-1 to exit): ");
			double annualIncome = input.nextDouble();
			double taxRate = 0;
			if (annualIncome == -1) break;
			double taxToPay = taxTableTools.calculateTax(annualIncome);
			System.out.println("Annual Income: (" + annualIncome + ") Tax rate: (" + taxRate + ") Tax to pay: (" + taxToPay + ")");
		}
		System.out.println("Goodbye!");
        input.close();
	}
}

