package com.example.caisse.utils;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HttpHandleUtils {

    /* Convert http error response to map */
    public static Map<String, String> onErrorHandler(VolleyError response) throws JSONException {
        NetworkResponse networkResponse = response.networkResponse;
        Map<String, String> errMap = new HashMap<>();

        /* This block is to handle if there is no internet connection */
        if (networkResponse == null) {
            errMap.put("code", "-1");
            return errMap;
        }

        int errCode = networkResponse.statusCode;

        String jsonError = new String(networkResponse.data);
        JSONObject errJson = new JSONObject(jsonError);

        errMap = MapUtils.convertJsonToMap(errJson);
        errMap.put("code", String.valueOf(errCode));

        return errMap;
    }

}
