package com.applegrocer.scheduler;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import android.view.View.OnClickListener;

/**
 * Adapter for RecyclerView in ClassesActivity
 * Created by AppleGrocer on 12/21/2015.
 */
public class ClassRecyclerAdapter extends RecyclerView.Adapter<ClassViewHolder>{

    private List<Lesson> classes;
    public final static String CLASS_UID_EXTRA="com.applegrocer.scheduler." +
            "CLASS_UID_EXTRA";
    public final static String CLASS_NAME_EXTRA="com.applegrocer.scheduler." +
            "CLASS_NAME_EXTRA";
    public final static String CLASS_LOCATION_EXTRA="com.applegrocer.scheduler." +
            "CLASS_LOCATION_EXTRA";
    public final static String CLASS_START_TIME_EXTRA="com.applegrocer.scheduler." +
            "CLASS_START_TIME_EXTRA";
    public final static String CLASS_END_TIME_EXTRA="com.applegrocer.scheduler." +
            "CLASS_END_TIME_EXTRA";
    public final static String CLASS_MEET_DAYS_EXTRA="com.applegrocer.scheduler." +
            "CLASS_MEET_DAYS_EXTRA";

    public ClassRecyclerAdapter(List<Lesson> classes){
        this.classes=new ArrayList<>();
        this.classes.addAll(classes);
    }

    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_view, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ClassViewHolder holder, int position) {
        final Lesson lesson=classes.get(position);

        //set text fields in card
        holder.classNameTV.setText(lesson.getClassName());
        holder.classLocationTV.setText(lesson.getLocation());
        holder.startTimeTV.setText(lesson.getStartTime());
        holder.endTimeTV.setText(lesson.getEndTime());

        //loop to set class day indicators
        for(int i=0; i<holder.daysArr.length; i++){
            if(lesson.getMeetDays()[i]) {
                holder.daysArr[i].setTextColor(Color.RED);
            }else{
                holder.daysArr[i].setTextColor
                        (ContextCompat.getColor(holder.context, R.color.offWhite));
            }
        }

        //set BG color for card
        switch(lesson.getClassColor()){
            case "Blue":
                holder.card.setBackgroundColor
                        (ContextCompat.getColor(holder.context, R.color.blue));
                break;
            case "Green":
                holder.card.setBackgroundColor
                        (ContextCompat.getColor(holder.context, R.color.green));
                break;
            case "Orange":
                holder.card.setBackgroundColor
                        (ContextCompat.getColor(holder.context, R.color.orange));
                break;
            case "Purple":
                holder.card.setBackgroundColor
                        (ContextCompat.getColor(holder.context, R.color.purple));
                break;
            case "Red":
                holder.card.setBackgroundColor
                        (ContextCompat.getColor(holder.context, R.color.red));
                break;
            case "Teal":
                holder.card.setBackgroundColor
                        (ContextCompat.getColor(holder.context, R.color.teal));
                break;
            case "Yellow":
                holder.card.setBackgroundColor
                        (ContextCompat.getColor(holder.context, R.color.yellow));
                break;
            default:
                holder.card.setBackgroundColor
                        (ContextCompat.getColor(holder.context, R.color.black));
        }

        holder.card.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.context, EditClassActivity.class);
                intent.putExtra(CLASS_UID_EXTRA, lesson.getUID());
                intent.putExtra(CLASS_NAME_EXTRA, lesson.getClassName());
                intent.putExtra(CLASS_LOCATION_EXTRA, lesson.getLocation());
                intent.putExtra(CLASS_START_TIME_EXTRA, lesson.getStartTime());
                intent.putExtra(CLASS_END_TIME_EXTRA, lesson.getEndTime());
                intent.putExtra(CLASS_MEET_DAYS_EXTRA, lesson.getMeetDays());
                holder.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public List<Lesson> getList(){
        return classes;
    }
}
