package application;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class getAPI {
	//TODO get data from CoinMarketCap API

	private final static String URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=";
	private final static String APIKEY = "c636cdc7-4bab-4622-8fc4-10c3ad0ca4df";
	static String currentPrice;

	public static String coinMarketCapAPI(String coin) {

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL + coin))
				.header("X-CMC_PRO_API_KEY", APIKEY)
				.build();
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
		.thenApply(HttpResponse::body)
		.thenApply(getAPI::parse)
		.join();


		return currentPrice;
	}

	public static void getKey(JSONObject json, String key) {
		boolean exists = json.has(key);
		Iterator<?> keys;
		String nextKeys;
		
		if(!exists) {
			
			keys = json.keys();
			while(keys.hasNext()) {
				nextKeys = (String)keys.next();
				try {
					if(json.get(nextKeys) instanceof JSONObject) {
						
						if(exists == false) {
							getKey(json.getJSONObject(nextKeys), key);
						}
						
					}else if(json.get(nextKeys) instanceof JSONArray) {
						JSONArray jsonarray = json.getJSONArray(nextKeys);
						for(int i =0; i < jsonarray.length(); i++) {
							String jsonarrayString = jsonarray.get(i).toString();
							JSONObject innerJSON = new JSONObject(jsonarrayString);
							if(exists == false) {
								getKey(innerJSON, key);
							}
						}
					}
				}catch (Exception e) {
					//TODO: handle exception
				}
			}

		}else {
			parseObject(json, key);
		}
	}
	public static String parse(String responseBody) {
		JSONObject responseJSONObject = new JSONObject(responseBody);
		getKey(responseJSONObject, "price");
		return null;
	}
	
	public static void parseObject(JSONObject json, String key) {
		//testing correct value was grabbed
		//System.out.println(json.get(key));
		
		currentPrice = String.format("%.4f", json.get(key));
	}
	
	

}		

