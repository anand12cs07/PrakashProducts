package com.andyapp.prakashproducts.VolleyParser;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.DefaultRetryPolicy;

import java.util.HashMap;
import java.util.Map;

public class VolleyUtils {

    private static int _LOW_TIMEOUT = 15000;
    private static int _MEDIUM_TIMEOUT = 25000;
    private static int _HIGH_TIMEOUT = 30000;
    private static int _VERY_HIGH_TIMEOUT = 50000;

    public static Map<String, String> getHeaders(String sessionName, String sessionId, String token) {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        String cookies = (sessionName + "=" + sessionId);
        header.put("cookie", cookies);
        header.put("X-CSRF-Token", token);
        return header;
    }

    public static Map<String, String> getEmptyHeaders() {
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        return header;
    }

    public static DefaultRetryPolicy getRetryPolicy(RequestTimeout policy) {
        return new DefaultRetryPolicy(
                getTimeOutDuration(policy),
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    private static int getTimeOutDuration(RequestTimeout policy) {
        switch (policy) {
            case LOW:
                return _LOW_TIMEOUT;
            case MEDIUM:
                return _MEDIUM_TIMEOUT;
            case HIGH:
                return _HIGH_TIMEOUT;
            case VERY_HIGH:
                return _VERY_HIGH_TIMEOUT;
            default:
                return _MEDIUM_TIMEOUT;
        }
    }


    public static boolean isOnline(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public enum RequestTimeout {
        LOW, MEDIUM, HIGH, VERY_HIGH
    }

}
