package com.applegrocer.scheduler;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * Created by AppleGrocer on 12/27/2015.
 */
public class ChalkboardCheckboxes extends CheckBox {
    public ChalkboardCheckboxes(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public ChalkboardCheckboxes(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public ChalkboardCheckboxes(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context){
        setTypeface(FontCache.getTypeface(context, "fonts/chalk.ttf"));
        setTextColor(ContextCompat.getColor(context, R.color.offWhite));
    }
}
