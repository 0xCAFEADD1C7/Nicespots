import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class MeteoApiClient {
	//private static final String meteokey = "ee00446ef1fe8a54ea3f6a995144ebdc";	//TODO TEST IN 10MIN
	private static final String meteokey = "b1b15e88fa797225412429c1c50c122a1";
	
	/**
	 * 
	 * @param lat
	 * @param lon
	 * @param day from 1 to 5
	 * @param hour from 0 to 24
	 * @return Clear, Rain, Snow, Extreme etc.
	 * @throws Exception 
	 */
	public String getMeteo(double lat, double lon, int day, int hour) throws Exception {
		return getMeteo(lat, lon, findIndex(day, hour));
	}
	
	/**
	 * 
	 * @param lat
	 * @param lon
	 * @param index
	 * @return Clear, Rain, Snow, Extreme etc.
	 * @throws Exception 
	 */
	public String getMeteo(double lat, double lon, int index) throws Exception {
		if (index < 0 || index > 40) {
			throw new Exception("Index not in range");
		}
		String url = "http://openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=";
		url += meteokey;
		JSONFetcher jf;
		JSONObject meteoJson = null;
		try {
			jf = new JSONFetcher(url);
			meteoJson = jf.toObject();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (String)meteoJson.query("/list/" + Integer.toString(index) + "/weather/0/main");
	}
	
	/**
	 * 
	 * @param day from 1 to 5
	 * @param hour from 1 to 24
	 * @return index for the meteo api
	 * @throws Exception 
	 */
	public int findIndex(int day, int hour) throws Exception {
		if (day < 0 || day > 6) {
			throw new Exception("Days not in range");
		} else if (hour < 0 || hour > 24) {
			throw new Exception("Hours not in range");
		}
		int hday = (day - 1) * 24;
		int tmp = hday + hour;
		return (int) Math.floor(tmp/3);
	}
}
