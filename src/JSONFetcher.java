import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONFetcher {
	
	private String jsonString;
	
	public JSONFetcher(String url) throws IOException {
		jsonString = getJson(url);
	}
	
	public JSONObject toObject() throws JSONException {
			return new JSONObject(jsonString);
	}
	
	public JSONArray toArray() throws JSONException {
			return new JSONArray(jsonString);
	}
	
	public String getJson(String urlString) throws IOException {
		URL url = new URL(urlString);
		URLConnection connexion = url.openConnection();
		String jsonString = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			jsonString+=inputLine;
		}
		in.close();

		return jsonString;
	}
}
