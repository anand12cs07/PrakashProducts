package com.andyapp.prakashproducts;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.andyapp.prakashproducts.Utils.ConnectivityReceiver;
import com.andyapp.prakashproducts.Utils.FontUtils;


public class AppController extends Application {

    private String LOGIN = "login";
    private String USER_NAME = "user_name";
    private String USER_EMAIL = "user_email";
    private String PROFILE_IMG = "profile_img";
    private String APP_FIRST_LAUNCH = "app_first_launch";
    private String APP_STATUS = "app_status";
    public static final String TAG = "AppController";

    private SharedPreferences preferences;
    private Editor editor;
    private RequestQueue mRequestQueue;
    private static AppController appController;

    @Override
    public void onCreate() {
        super.onCreate();
        appController = this;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        FontUtils.getInstance().setTypeface(this);
    }

    public static synchronized AppController getInstance() {
        if (appController == null)
            appController = new AppController();
        return appController;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

    public boolean isAppFirstLaunch() {
        return preferences.getBoolean(APP_FIRST_LAUNCH, false);
    }

    public void appLaunchStatus() {
        editor.putBoolean(APP_STATUS, true);
    }

    public boolean isIsLogIn() {
        return preferences.getBoolean(LOGIN, false);
    }

    public void setUserData(String userName, String userEmail, String profileImg) {
        editor.putBoolean(LOGIN, true);
        editor.putString(USER_NAME, userName);
        editor.putString(USER_EMAIL, userEmail);
        editor.putString(PROFILE_IMG, profileImg);
        editor.apply();
    }

    public String[] getUserData() {
        String[] userDetail = new String[3];
        userDetail[0] = preferences.getString(USER_EMAIL, "");
        userDetail[1] = preferences.getString(USER_NAME, "");
        userDetail[2] = preferences.getString(PROFILE_IMG, "");
        return userDetail;
    }

    public void logOut() {
        editor.clear();
        editor.apply();
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


    public DefaultRetryPolicy getRetryPolicy() {
        return new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }


}
