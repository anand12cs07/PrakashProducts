package com.andyapp.prakashproducts.Utils;


import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtil {

    private String PREF_TAG = "prakash_products_pref";
    private String USER_PROFILE = "user_profile";
    private String USER_ID = "user_id";
    private String USER_NAME = "user_name";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private PrefUtil(Context context){
        preferences = context.getSharedPreferences(PREF_TAG,Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public PrefUtil getInstance(Context context){
        return new PrefUtil(context);
    }

    public String getUserProfile() {
        return preferences.getString(USER_PROFILE,"");
    }

    public void setUserProfile(String userProfile) {
        editor.putString(USER_PROFILE,userProfile).apply();
    }

    public String getUserID() {
        return preferences.getString(USER_ID,"");
    }

    public void setUserID(String userID) {
        editor.putString(USER_ID, userID).apply();
    }

    public String getUserName() {
        return preferences.getString(USER_NAME,"");
    }

    public void setUserName(String userName) {
        editor.putString(USER_NAME,userName).apply();
    }

    public void logout(){
        editor.clear().apply();
    }
}
