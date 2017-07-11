package com.andyapp.prakashproducts.Utils;


import android.content.Context;
import android.graphics.Typeface;

public class FontUtils {

    private String novecentosanswide = "fonts/Novecentosanswide-Medium.otf";
    private String multicolor = "fonts/Multicolor.otf";

    public static int NOVECENTOSANWIDE = 0;
    public static int MULTICOLOR = 1;

    private Context context;
    private static FontUtils fontUtils;

    public static FontUtils getInstance(){
        if (fontUtils == null)
            fontUtils = new FontUtils();
        return fontUtils;
    }

    public Typeface getTypeFace(int typeface, Context context){
        this.context = context;

        if (typeface == NOVECENTOSANWIDE)
            return createTypeface(novecentosanswide);
        else
            return createTypeface(multicolor);
    }

    private Typeface createTypeface(String typeface){
        return Typeface.createFromAsset(context.getAssets(),typeface);
    }
}
