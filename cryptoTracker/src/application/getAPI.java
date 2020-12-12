package application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;


public class getAPI {
	//TODO get data from CoinMarketCap API

	private final static String URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
	private final static String APIKEY = "c636cdc7-4bab-4622-8fc4-10c3ad0ca4df";
	private final static int LIMIT = 100;

	static String currentPrice;

	public static Map<String, Double> coinMarketCapAPI(List<String> coinNames) throws IOException, Throwable {
		String result = "";
		Map<String, Double> resultMap = new HashMap<>();
		List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
		paratmers.add(new BasicNameValuePair("start","1"));
		paratmers.add(new BasicNameValuePair("limit",String.valueOf(LIMIT)));
		paratmers.add(new BasicNameValuePair("convert","USD"));

		try {
			result = makeAPICall(URL, paratmers);
			//testing*******
			//System.out.println(result);
			//System.out.println(result.length());
			//**********
		} catch (IOException e) {
			System.out.println("Error: cannont access content - " + e.toString());
		} catch (URISyntaxException e) {
			System.out.println("Error: Invalid URL " + e.toString());
		}

		coinNames.forEach(name -> {
			resultMap.put(name, null);
		});


		return parse(resultMap, result);
	}//END coinMarketCapAPI***********
	
	public static String makeAPICall (String uri, List<NameValuePair> parameters) throws URISyntaxException, org.apache.hc.core5.http.ParseException, IOException{
		String response_content = "";

		URIBuilder query = new URIBuilder(uri);
		query.addParameters(parameters);

		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(query.build());

		request.setHeader(HttpHeaders.ACCEPT, "application/json");
		request.addHeader("X-CMC_PRO_API_KEY", APIKEY);

		CloseableHttpResponse response = client.execute(request);

		try {
			//System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			response_content = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
		} finally {
			response.close();
		}
		return response_content;
	}//END makeAPICall *********

	public static Map<String, Double> parse(Map<String, Double> resultMap, String data) throws ParseException {
		Map<String, Double> coins = new HashMap<String, Double>();


		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(data);

		for(Map.Entry<String, Double> entry: resultMap.entrySet()) {
			String name = entry.getKey();
			Double price;

			for(int i =0; i < LIMIT; i++) {
				JSONArray dataDetail = (JSONArray) obj.get("data");
				JSONObject intObj = (JSONObject) dataDetail.get(i);
				if(name.compareTo((String) intObj.get("symbol")) == 0) {
					JSONObject quoteObj = (JSONObject) intObj.get("quote");
					JSONObject usdObj = (JSONObject) quoteObj.get("USD");
					price = (Double) usdObj.get("price");
					//System.out.println((Double) usdObj.get("price"));
					coins.put(name, price);
				}//END if
			}//END inner for loop
		}//END outer for loop

		//System.out.println("coins" + coins);
		return coins;

	}//END parse


}

