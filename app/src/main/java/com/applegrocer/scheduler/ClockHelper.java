package com.applegrocer.scheduler;

import android.view.View;
import android.widget.TextView;

/**
 * Utility class for setting time of views which launch TimePickerFrag
 * Created by applegrocer on 12/14/15.
 */
public class ClockHelper{

    private final int tfHour;
    private final int minute;

    public ClockHelper(int hour, int minute){
        this.tfHour=hour;
        this.minute=minute;
    }

    public String convertTextTime(){
        String period="am";
        String time;
        int hour=tfHour;
        int min=this.minute;

        //Handles 24h->12h conversion
        if(hour>=12){
            if(hour>12) {
                hour -= 12;
            }
            period="pm";
        }
        if(hour==0){
            hour += 12;
        }

        //Handles minutes <10
        if(min<10){
            time= hour+":0"+min+" "+period;
        }else{
            time= hour+":"+min+" "+period;
        }
        return time;
    }
}
