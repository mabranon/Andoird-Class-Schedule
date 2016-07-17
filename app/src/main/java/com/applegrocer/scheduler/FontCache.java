package com.applegrocer.scheduler;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by AppleGrocer on 12/27/2015.
 */
public class FontCache {

    private static HashMap<String, Typeface> fontMap=new HashMap<>();

    public static Typeface getTypeface(Context context, String key){
        if(fontMap.containsKey(key)){
            return fontMap.get(key);
        }else{
            Typeface typeface=Typeface.createFromAsset(context.getAssets(), key);
            fontMap.put(key,typeface);
            return typeface;
        }
    }
}
