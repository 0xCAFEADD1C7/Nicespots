package org.utils;

import org.json.JSONObject;

public interface JSONable {
	public String toJson() throws Exception;
	public void fromJson(JSONObject obj) throws Exception;
}
