package com.applegrocer.scheduler;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * ViewHolder for ClassActivity Recycler
 * Created by AppleGrocer on 12/21/2015.
 */
public class ClassViewHolder extends RecyclerView.ViewHolder{

    protected Context context;

    protected CardView card;

    protected TextView classNameTV;
    protected TextView classLocationTV;
    protected TextView startTimeTV;
    protected TextView endTimeTV;
    protected TextView[] daysArr;


    public ClassViewHolder(View view){
        super(view);
        classNameTV=(TextView) view.findViewById(R.id.className);
        classLocationTV=(TextView) view.findViewById(R.id.classLocation);
        startTimeTV=(TextView) view.findViewById(R.id.classStartTime);
        endTimeTV=(TextView) view.findViewById(R.id.classEndTime);

        //populate daysArr to hold day views
        daysArr=new TextView[]{
                (TextView) view.findViewById(R.id.sunIcon),
                (TextView) view.findViewById(R.id.monIcon),
                (TextView) view.findViewById(R.id.tuesIcon),
                (TextView) view.findViewById(R.id.wedIcon),
                (TextView) view.findViewById(R.id.thursIcon),
                (TextView) view.findViewById(R.id.friIcon),
                (TextView) view.findViewById(R.id.satIcon)};



        card=(CardView) view;

        context=itemView.getContext();
    }

}
