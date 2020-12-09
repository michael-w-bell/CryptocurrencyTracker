package application;

import javafx.beans.property.SimpleStringProperty;

//*******Crypto Class************
public class Crypto{
	private SimpleStringProperty coinName, 
	coinsBought,
	costPerCoin,
	totalSpent,
	currentPrice,
	currentValue,
	profit,
	roi;

	public String getCoinName() {
		return coinName.get();
	}
	public String getCoinsBought() {
		return coinsBought.get();
	}
	public String getCostPerCoin() {
		return costPerCoin.get();
	}
	public String getTotalSpent() {
		return totalSpent.get();
	}
	public String getCurrentPrice() {
		return currentPrice.get();
	}
	public String getCurrentValue() {
		return currentValue.get();
	}
	public String getProfit() {
		return profit.get();
	}
	public String getRoi() {
		return roi.get();
	}

	Crypto(String name, String numBought, String costPerCoin, String totalSpent, String curPrice, String curValue, String profit, String roi){
		this.coinName = new SimpleStringProperty(name);
		this.coinsBought = new SimpleStringProperty(numBought);
		this.costPerCoin = new SimpleStringProperty(costPerCoin);
		this.totalSpent = new SimpleStringProperty(totalSpent);
		this.currentPrice = new SimpleStringProperty(curPrice);
		this.currentValue = new SimpleStringProperty(curValue);
		this.profit = new SimpleStringProperty(profit);
		this.roi = new SimpleStringProperty(roi);
	}

	@Override
	public String toString() {
		return ("[" + coinName.get() + ", " + coinsBought.get() + ", " + costPerCoin.get() + ", " +
				totalSpent.get() + ", " + currentPrice.get() + ", " + currentValue.get()  + ", " +
				profit.get() + ", " + roi.get() + "]");

	}

}//*********END Crypto Class*************