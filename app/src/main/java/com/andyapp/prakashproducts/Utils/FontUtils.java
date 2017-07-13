package com.andyapp.prakashproducts.Utils;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FontUtils {

    private String ubuntu = "fonts/Ubuntu.ttf";
    private Context context;
    private static FontUtils fontUtils;

    public static FontUtils getInstance(){
        if (fontUtils == null)
            fontUtils = new FontUtils();
        return fontUtils;
    }

    public void setTypeface(Context context) {

        final Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), ubuntu);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Map<String, Typeface> newMap = new HashMap<String, Typeface>();
            newMap.put("serif", customFontTypeface);
            try {
                final Field staticField = Typeface.class.getDeclaredField("sSystemFontMap");
                staticField.setAccessible(true);
                staticField.set(null, newMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                final Field defaultFontTypefaceField = Typeface.class.getDeclaredField("SERIF");
                defaultFontTypefaceField.setAccessible(true);
                defaultFontTypefaceField.set(null, customFontTypeface);
            } catch (Exception e) {
            }
        }
    }
}
