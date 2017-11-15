package org.utils;

import java.util.Map;

import org.json.JSONObject;

public interface JSONable {
	public String toJson() throws Exception;
	public void fromJson(JSONObject obj, Map<String, Object> infos) throws Exception;
}
