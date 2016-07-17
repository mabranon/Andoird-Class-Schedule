package com.applegrocer.scheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AppleGrocer on 12/21/2015.
 */

public class DBHelper extends SQLiteOpenHelper{

    private static DBHelper sInstance;

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="scheduler.db";

    public static synchronized DBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContract.ClassInfoTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBContract.ClassInfoTable.CLEAR_TABLE);
    }

    /**
     * Insertion for class information table
     * @param className=name of class
     * @param location=location of class
     * @param inDays=days of the week class meets represented by boolean array
     *              class meets 0 means it does not
     * @param startTime=start of class
     * @param endTime=end of class
     */
    public void insertClass(String className, String location, String startTime, String endTime,
                            boolean[] inDays, String classColor, Context context){
        SQLiteDatabase db=getInstance(context).getWritableDatabase();

        //prep column names for days of week
        String[] dayStrings=new String[]{
                DBContract.ClassInfoTable.COLUMN_NAME_SUN,
                DBContract.ClassInfoTable.COLUMN_NAME_MON,
                DBContract.ClassInfoTable.COLUMN_NAME_TUES,
                DBContract.ClassInfoTable.COLUMN_NAME_WED,
                DBContract.ClassInfoTable.COLUMN_NAME_THURS,
                DBContract.ClassInfoTable.COLUMN_NAME_FRI,
                DBContract.ClassInfoTable.COLUMN_NAME_SAT};

        //add table info
        ContentValues values=new ContentValues();
        values.put(DBContract.ClassInfoTable.COLUMN_NAME_CLASS, className);
        values.put(DBContract.ClassInfoTable.COLUMN_NAME_LOCATION, location);
        values.put(DBContract.ClassInfoTable.COLUMN_NAME_START_TIME, startTime);
        values.put(DBContract.ClassInfoTable.COLUMN_NAME_END_TIME, endTime);
        //loop to fill in days of weeks columns, convert booleans to ints
        int dayTrav=0;
        for(String dbDay:dayStrings){
            if(inDays[dayTrav]) {
                values.put(dbDay, 1);
            }else{
                values.put(dbDay, 0);
            }
            dayTrav++;
        }

        values.put(DBContract.ClassInfoTable.COLUMN_COLOR, classColor);

        //make insertions and close
        db.insert(DBContract.ClassInfoTable.TABLE_NAME, null, values);
        db.close();
    }

    public void removeClass(long uID, Context context){
        SQLiteDatabase db=getInstance(context).getWritableDatabase();
        String selection=DBContract.ClassInfoTable.UID+" LIKE ?";
        String[] selectionArgs={String.valueOf(uID)};
        db.delete(DBContract.ClassInfoTable.TABLE_NAME, selection, selectionArgs);
        db.close();
    }

    public void updateClass(long uID, String className, String location, String startTime,
                            String endTime, boolean[] inDays, Context context){
        SQLiteDatabase db=getInstance(context).getWritableDatabase();

        //prep column names for days of week
        String[] dayStrings=new String[]{
                DBContract.ClassInfoTable.COLUMN_NAME_SUN,
                DBContract.ClassInfoTable.COLUMN_NAME_MON,
                DBContract.ClassInfoTable.COLUMN_NAME_TUES,
                DBContract.ClassInfoTable.COLUMN_NAME_WED,
                DBContract.ClassInfoTable.COLUMN_NAME_THURS,
                DBContract.ClassInfoTable.COLUMN_NAME_FRI,
                DBContract.ClassInfoTable.COLUMN_NAME_SAT};

        //add table info
        ContentValues values=new ContentValues();
        values.put(DBContract.ClassInfoTable.COLUMN_NAME_CLASS, className);
        values.put(DBContract.ClassInfoTable.COLUMN_NAME_LOCATION, location);
        values.put(DBContract.ClassInfoTable.COLUMN_NAME_START_TIME, startTime);
        values.put(DBContract.ClassInfoTable.COLUMN_NAME_END_TIME, endTime);

        //loop to fill in days of weeks columns, convert booleans to ints
        int dayTrav=0;
        for(String dbDay:dayStrings){
            if(inDays[dayTrav]) {
                values.put(dbDay, 1);
            }else{
                values.put(dbDay, 0);
            }
            dayTrav++;
        }

        String selection=DBContract.ClassInfoTable.UID+" LIKE ?";
        String[] selectionArgs={String.valueOf(uID)};
        db.update(DBContract.ClassInfoTable.TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }

    public Cursor getAllClasses(Context context){
        SQLiteDatabase db=getInstance(context).getReadableDatabase();
        Cursor cursor=db.query(false, DBContract.ClassInfoTable.TABLE_NAME,
                null, null, null, null, null, null, null, null);
        return cursor;
    }
}
