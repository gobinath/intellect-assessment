package com.user.crud.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResponseUtil {

	public static String jsonFormatter(String resMsg, String userId, String errCode, String errField, String errMsg)
			throws JSONException {

		JSONObject response = new JSONObject();

		response.put("UserId", userId);
		response.put("resMsg", resMsg);
		

		if (errField != null  && errMsg != null ) {

			JSONArray errArr = new JSONArray();
			JSONObject errObj = new JSONObject();

			errObj.put("code", errCode);
			errObj.put("field", errField);
			errObj.put("message", errMsg);

			errArr.put(errObj);
			response.put("valErrors", errArr);
		}

		return response.toString();
	}
}
