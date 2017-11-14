package org.utils;

import org.json.JSONObject;

public interface JSONable {
	public String toJson() throws Exception;
	public Object fromJson(JSONObject o) throws Exception;
}
