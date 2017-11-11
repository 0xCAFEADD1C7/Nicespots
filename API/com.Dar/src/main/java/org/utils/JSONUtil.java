package org.utils;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtil {
	public static String ofList(List<? extends JSONable> objs) throws Exception {
		StringBuilder sb = new StringBuilder("[\n");
		
		for (int i = 0; i < objs.size(); i++) {
			if (i > 0) {
				sb.append(',');
			}
			sb.append(objs.get(i).toJson());
		}
		
		sb.append("]");
		return sb.toString();
	}
	
	public static JSONObject isJSONObject(String str) {
		JSONObject obj = new JSONObject();
		try {
			obj.getJSONObject(str);
			return obj;
		} catch (JSONException e) {
			return null;
		}
	}
}
