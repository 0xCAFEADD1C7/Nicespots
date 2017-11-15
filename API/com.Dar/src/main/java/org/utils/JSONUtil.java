package org.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
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
	
	public static List<String> getStringArray(JSONObject o, String fieldName) throws Exception {
		List<String> out = new ArrayList<>();
		JSONArray array = o.getJSONArray(fieldName);
		for (int i = 0; i < array.length(); i++) {
			out.add(array.getString(i));
		}
		return out;
	}
}
