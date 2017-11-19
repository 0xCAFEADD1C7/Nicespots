package org.utils;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class MapApiClient {
	private static final String mapkey = "AIzaSyAdbZcM0GSwNMuuua7FYCPRZv-LBe3QrTc";
	
		/**
		 * Return the address of the specific location
		 * @param lat
		 * @param lon
		 * @return XXX RoadName, code city, Country
		 * @throws JSONException 
		 */
		public static String getAddress(double lat, double lon) throws JSONException {
			String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lon+"&key=";
			url += mapkey;
			System.out.println("URL : "+url);
			JSONFetcher jf;
			JSONObject mapJson = null;
			try {
				jf = new JSONFetcher(url);
				mapJson = jf.toObject();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				return mapJson.getJSONArray("results").getJSONObject(0).getString("formatted_address");
			} catch(Exception e) { // surement aucun résultat...
				return "";
			}
		}
}
