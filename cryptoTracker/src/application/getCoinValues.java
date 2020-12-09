package application;

public class getCoinValues {
	static String getTotalDollar() {
		// TODO Auto-generated method stub
		return null;
	}

	//XRP values
	public static String getXRP() {
		String curPrice;

		curPrice = getAPI.coinMarketCapAPI("XRP");

		return curPrice;
	}

	//get XLM values
	public static String getXLM() {
		String curPrice;

		curPrice = getAPI.coinMarketCapAPI("XLM");

		return curPrice;
	}

	//get ADA values
	public static String getADA() {
		String curPrice;

		curPrice = getAPI.coinMarketCapAPI("ADA");

		return curPrice;
	}

	//get VET values
	public static String getVET() {
		String curPrice;

		curPrice = getAPI.coinMarketCapAPI("VET");

		return curPrice;
	}

	//get ETH values
	public static String getETH() {
		String curPrice;

		curPrice = getAPI.coinMarketCapAPI("ETH");

		return curPrice;
	}
}
