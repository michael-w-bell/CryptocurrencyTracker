package application;

import java.util.ArrayList;

public class Totals {
	//gets the current value from each coin and adds them for a total current value. Returns total current value.
	public static String totalValue(ArrayList<Crypto> cryptoData) {
		float totals = 0;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(Crypto currentValue : cryptoData) {
			totals += Float.parseFloat(currentValue.getCurrentValue());
		}

		return String.format("%.2f",totals);
	}//END totalValue*********

	//gets the total spent for each coin and adds them for a total spent of all coins. Returns total spent.
	public static String totalSpent(ArrayList<Crypto> cryptoData) {
		return null;
	}

	
	//gets the profit from each coin and adds them for a total profit. Returns total profit.
	public static String totalProfit(ArrayList<Crypto> cryptoData) {
		return null;
	}

	
	//gets the roi from each coin and adds them for a total roi. Returns total ROI.
	public static String totalROI(ArrayList<Crypto> cryptoData) {
		return null;
	}

}
