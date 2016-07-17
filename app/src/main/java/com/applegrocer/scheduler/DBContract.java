package com.applegrocer.scheduler;

import android.provider.BaseColumns;

/**
 * Contract for database
 * Created by AppleGrocer on 12/21/2015.
 */
public final class DBContract{

    //empty constructor
    public DBContract(){}

    /**
     * Table for class info
     */
    public static abstract class ClassInfoTable{

        //Table & column names
        public static final String TABLE_NAME="classTable";
        public static final String UID="_id";
        public static final String COLUMN_NAME_CLASS="nameOfClass";
        public static final String COLUMN_NAME_LOCATION="location";
        public static final String COLUMN_NAME_SUN="sunday";
        public static final String COLUMN_NAME_MON="monday";
        public static final String COLUMN_NAME_TUES="tuesday";
        public static final String COLUMN_NAME_WED="wednesday";
        public static final String COLUMN_NAME_THURS="thursday";
        public static final String COLUMN_NAME_FRI="friday";
        public static final String COLUMN_NAME_SAT="saturday";
        public static final String COLUMN_NAME_START_TIME="startTime";
        public static final String COLUMN_NAME_END_TIME="endTime";
        public static final String COLUMN_COLOR="color";

        //creation and deletion strings
        public static final String CREATE_TABLE="CREATE TABLE "+ClassInfoTable.TABLE_NAME+
                " ("+ClassInfoTable.UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ClassInfoTable.COLUMN_NAME_CLASS+" VARCHAR(225), "+
                ClassInfoTable.COLUMN_NAME_LOCATION+" VARCHAR(225), "+
                ClassInfoTable.COLUMN_NAME_START_TIME+" VARCHAR(255), "+
                ClassInfoTable.COLUMN_NAME_END_TIME+" VARCHAR(255), "+
                ClassInfoTable.COLUMN_NAME_SUN+" INT, "+
                ClassInfoTable.COLUMN_NAME_MON+" INT, "+
                ClassInfoTable.COLUMN_NAME_TUES+" INT, "+
                ClassInfoTable.COLUMN_NAME_WED+" INT, "+
                ClassInfoTable.COLUMN_NAME_THURS+" INT, "+
                ClassInfoTable.COLUMN_NAME_FRI+" INT, "+
                ClassInfoTable.COLUMN_NAME_SAT+" INT, "+
                ClassInfoTable.COLUMN_COLOR+" VARCHAR(255));";
        public static final String CLEAR_TABLE="DROP TABLE IF EXISTS "+ClassInfoTable.TABLE_NAME;
    }


}
