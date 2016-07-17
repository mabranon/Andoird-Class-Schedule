package com.applegrocer.scheduler;

/**
 * Class for holding and describing a user inputted class
 * Created by AppleGrocer on 12/21/2015.
 */
public class Lesson {

    private long uID;
    private String name;
    private String location;
    private String startTime;
    private String endTime;
    private boolean[] meetDays=new boolean[7];
    private String classColor;

    public Lesson(long uID, String name, String location, String startTime,
                 String endTime, boolean[] meetDays, String classColor){
        this.uID=uID;
        this.name=name;
        this.location=location;
        this.startTime=startTime;
        this.endTime=endTime;

        //copy boolean days array
        for(int i=0; i<meetDays.length; i++){
            this.meetDays[i]=meetDays[i];
        }

        this.classColor=classColor;
    }

    public long getUID(){
        return uID;
    }

    public String getClassName(){
        return name;
    }

    public String getLocation(){
        return location;
    }

    public String getStartTime(){
        return startTime;
    }

    public String getEndTime(){
        return endTime;
    }

    public boolean[] getMeetDays(){
        return meetDays;
    }

    public String getClassColor(){return classColor;}

}
