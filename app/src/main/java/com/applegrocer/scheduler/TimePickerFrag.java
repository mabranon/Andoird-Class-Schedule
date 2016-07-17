package com.applegrocer.scheduler;


import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimePickerFrag extends AppCompatDialogFragment
        implements TimePickerDialog.OnTimeSetListener{

    TimePickerFragListener mListener;

    /**
     * Inner interface to pass time values to activity
     */
    public interface TimePickerFragListener{
        void changeTextTime(int hour, int minute, int viewId);
    }

    /**
     * Empty constructor
     */
    public TimePickerFrag() {

    }

    /**
     * Make sure activity implements interface
     * @param activity
     */
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            mListener=(TimePickerFragListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must implement " +
                    "TimePickerFragListener interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceStat){
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm aa");
        int hour;
        int minute;
        String time=getArguments().getCharSequence("time").toString();

        //Parse "time" arg passed by activity
        Calendar c=Calendar.getInstance();
        try {
            Date inTime=sdf.parse(time);
            c.setTime(inTime);
        }catch(ParseException e){
            e.printStackTrace();
        }
        hour=c.get(Calendar.HOUR);
        minute=c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute, false);
    }

    /**
     * Passes time info along with calling view to appropriate setTime method
     * @param view
     * @param hourOfDay is 25h
     * @param minute needs concatenated 0 for minute<10
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        int viewId=getArguments().getInt("viewId");
        ((TimePickerFragListener)getActivity()).changeTextTime(hourOfDay, minute, viewId);
    }

}
