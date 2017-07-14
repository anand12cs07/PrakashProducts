package com.andyapp.prakashproducts.Utils;


import android.app.Activity;
import android.content.Context;

public class FontUtils {

    private String ubuntu = "fonts/Ubuntu.ttf";
    private Context context;
    private static FontUtils fontUtils;

    public static FontUtils getInstance() {
        if (fontUtils == null)
            fontUtils = new FontUtils();
        return fontUtils;
    }

    public void setTypeface(Activity activity, Context context) {


    }
}
