package com.applegrocer.scheduler;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by AppleGrocer on 12/27/2015.
 */
public class ChalkboardTextView extends TextView {

    public ChalkboardTextView(Context context){
        super(context);
        applyCustomFont(context);
    }

    public ChalkboardTextView(Context context, AttributeSet attrs){
        super(context, attrs);
        applyCustomFont(context);
    }

    public ChalkboardTextView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context){
        setTypeface(FontCache.getTypeface(context, "fonts/chalk.ttf"));
        setTextColor(ContextCompat.getColor(context, R.color.offWhite));
    }


}
