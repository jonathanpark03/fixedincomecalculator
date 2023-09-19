package main;

import java.util.Scanner;

public class calculator {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.println("What is the face value of your fixed-income security?");
		double faceValue = in.nextDouble();

		System.out.println("What is the annual interest rate on your investment?");
		double interestRate = in.nextDouble() / 100;

		System.out.println("How many years until maturity?");
		int years = in.nextInt();

		System.out.println("What is the frequency of your coupon payments?");
		int couponPerYear = 0;

		while (couponPerYear == 0) {
			String couponFrequency = in.nextLine();

			if (couponFrequency.equals("annually")) {
				couponPerYear = 1;
			} else if (couponFrequency.equals("semi-annually")) {
				couponPerYear = 2;
			} else if (couponFrequency.equals("quarterly")) {
				couponPerYear = 4;
			} else {
				System.out.println("Please enter annually, semi-annually, or quarterly.");
			}
		}

		double totalIncome = (years * (faceValue * interestRate));
		double couponPayment = (faceValue * interestRate) / couponPerYear;
		double ROI = (totalIncome / faceValue) * 100;

		for (int i = 1; i < years; i++) {
			double currentIncome = i * (faceValue * interestRate);
			int couponCount = couponPerYear * i;
			System.out.println("After " + i + " year(s), you will have received " + couponCount + " coupon payments of $"
					+ couponPayment + ", totaling to $" + currentIncome + " in interest income.");
		}

		System.out.println("After " + years + " year(s), your fixed-income security will "
				+ "have reached maturity and you will be repaid the principal amount of $" + faceValue + ".");

		System.out.println("In addition, you will have received " + couponPerYear * years + " coupon payments of $"
				+ couponPayment + ", totaling to $" + totalIncome + " in interest income, giving an ROI of " + ROI + "%.");

		System.out.println("");
		
		System.out.println("Would you like to adjust ROI for inflation?");
		System.out.println("Please enter yes or no.");
		double inflationRate = 0;

		while (inflationRate == 0) {
			String adjustment = in.nextLine();

			if (adjustment.equals("yes")) {
				System.out.println("What is the annual inflation rate?");
				inflationRate = in.nextDouble() / 100;
			} else if (adjustment.equals("no")) {
				return;
			} else {
				System.out.println("Please enter yes or no.");
			}
		}

		double roundInflation = (Math.round((Math.pow(1 + inflationRate, years) - 1) * 10000));
		double totalInflation = roundInflation / 10000;
		double inflationROI = (1 + ROI / 100) / (1 + totalInflation) - 1;
		double roundInflationROI = Math.round(inflationROI * 10000);
		inflationROI = roundInflationROI / 100;

		System.out.println("Assuming inflation will go up " + inflationRate * 100 + "%" + " each year for " + years
				+ " years, your inflation-adjusted ROI would be " + inflationROI + "%.");
	}
}
