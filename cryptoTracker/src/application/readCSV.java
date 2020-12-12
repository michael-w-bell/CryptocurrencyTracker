package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;

public class readCSV {
	//************reading CSV File*******************
	//Crypto class format (coin name, coins bought, avg buy price, total spent, current price, current value, profit, roi)
	//File format coin name, coins owned, avg purchase price, total amount spend,(place-holders must be 0) 0, 0, 0, 0
	private final static String DELIM = ",";

	public static void readFile(String file, ArrayList<Crypto> cryptoData, ObservableList<Crypto> data) throws Throwable {

		BufferedReader br;
		float currentValue, roi, profit;
		List<String> coinNames = new ArrayList<String>();
		Map<String,Double> coinMap = new HashMap<>();

		//get name of coins first
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) !=null){
				String[] fields = line.split(DELIM, -1);

				coinNames.add(fields[0]);


			}
			br.close();	
		} catch (FileNotFoundException ex){
			ex.printStackTrace();
		}
		coinMap = getAPI.coinMarketCapAPI(coinNames);

		//get values from file and from map, calculate, add to crypto class
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) !=null){
				String[] fields = line.split(DELIM, -1);
				//File format (coin name, coins bought, avg buy price, total spent, current price, current value, profit, roi)

				if(coinMap.containsKey(fields[0])) {
					fields[4] = String.valueOf(coinMap.get(fields[0]));
					//calculate current value = coins bought * current price
					currentValue = Float.parseFloat(fields[1]) * Float.parseFloat(fields[4]);
					fields[5] = String.format("%.2f", currentValue);

					//calculate profit = current value - total spent
					profit =  Float.parseFloat(fields[5]) - Float.parseFloat(fields[3]);
					fields[6] =String.format("%.2f", profit);

					//calculate roi = ((current value - total spent) / total spent) * 100
					roi = ((Float.parseFloat(fields[5]) - Float.parseFloat(fields[3])) / Float.parseFloat(fields[3]) * 100);
					fields[7] = String.format("%.2f", roi);
					
				}




				Crypto crypto = new Crypto(fields[0],fields[1], fields[2], fields[3], fields[4], fields[5], fields [6], fields[7]);
				cryptoData.add(crypto);


			}
			br.close();	
		} catch (FileNotFoundException ex){
			ex.printStackTrace();
		}

		
		for(Crypto coin : cryptoData) {
			data.add(coin);
		}
		
		//testing data was input correctly
		/*
			  for(Crypto coin : cryptoData) {
				System.out.println(coin.toString());
			}
		 */

	}//**************END Read CSV File*************
}
