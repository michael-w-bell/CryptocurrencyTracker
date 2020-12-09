package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class readCSV {
	//************reading CSV File*******************
	// file format coin name, coins owned, avg purchase price, total amount spend,(place-holders must be 0) 0, 0, 0, 0
		private final static String DELIM = ",";

		public static void readFile(String file, ArrayList<Crypto> cryptoData, ObservableList<Crypto> data) throws IOException {

			BufferedReader br;
			float currentValue, roi, profit;
			
			try {
				br = new BufferedReader(new FileReader(file));
				String line;
				while((line = br.readLine()) !=null){
					String[] fields = line.split(DELIM, -1);
					if (fields[0].equals("XRP")) 
						fields[4] = getCoinValues.getXRP();
					else if(fields[0].equals("XLM"))
						fields[4] = getCoinValues.getXLM();
					else if(fields[0].equals("ADA"))
						fields[4] = getCoinValues.getADA();
					else if(fields[0].equals("VET"))
						fields[4] = getCoinValues.getVET();
					else if(fields[0].equals("ETH"))
						fields[4] = getCoinValues.getETH();
					else if(fields[0] == null)
						return;

					//calculate current value = coins bought * current price
					currentValue = Float.parseFloat(fields[1]) * Float.parseFloat(fields[4]);
					fields[5] = String.format("%.2f", currentValue);
					
					//calculate profit = current value - total spent
					profit =  Float.parseFloat(fields[5]) - Float.parseFloat(fields[3]);
					fields[6] =String.format("%.2f", profit);
					
					//calculate roi = ((current value - total spent) / total spent) * 100
					roi = ((Float.parseFloat(fields[5]) - Float.parseFloat(fields[3])) / Float.parseFloat(fields[3]) * 100);
					fields[7] = String.format("%.2f", roi);
					

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
