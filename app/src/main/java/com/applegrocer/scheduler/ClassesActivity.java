package com.applegrocer.scheduler;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

/**
 * Screen where user can view/edit their classes. Navigates to AddClassActivity
 */
public class ClassesActivity extends AppCompatActivity {

    private DBHelper database;
    private ArrayList<Lesson> lessonsArr;
    RecyclerView cardList;
    ClassRecyclerAdapter cardListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        //create database object
        database= DBHelper.getInstance(this);

        //Set recycler view
        cardList=(RecyclerView) findViewById(R.id.recView);
        LinearLayoutManager mLManager=new LinearLayoutManager(this);
        cardList.setLayoutManager(mLManager);

        //Set toolbar
        Toolbar toolbar=(Toolbar) findViewById(R.id.classes_toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_classes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.addClass:
                Intent intent=new Intent(this,AddClassActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        if(cardListAdapter==null) {
            lessonsArr = generateClassList();
            cardListAdapter = new ClassRecyclerAdapter(lessonsArr);
            cardList.setAdapter(cardListAdapter);
        }else {
            cardListAdapter.getList().clear();
            cardListAdapter.getList().addAll(generateClassList());
            cardListAdapter.notifyDataSetChanged();
        }
    }

    private ArrayList<Lesson> generateClassList(){
        ArrayList<Lesson> lessons=new ArrayList<>();

        long uID;
        String className;
        String classLocation;
        String startTime;
        String endTime;
        boolean[] classDays=new boolean[]{false, false, false, false, false, false, false};
        String classColor;

        Cursor cursor=database.getAllClasses(this);
        if(cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                uID=cursor.getLong(0);
                className = cursor.getString(1);
                classLocation = cursor.getString(2);
                startTime = cursor.getString(3);
                endTime = cursor.getString(4);
                //reset boolean array
                for(int j=0; j<classDays.length; j++){
                    classDays[j]=false;
                }
                //columns 5-11 are the days of the week columns in SQLite table
                for(int j=5; j<12; j++){
                    if(cursor.getInt(j)==1){
                        classDays[j-5]=true;
                    }
                }
                classColor=cursor.getString(12);
                lessons.add(new Lesson(uID, className, classLocation, startTime, endTime,
                        classDays, classColor));
                cursor.moveToNext();
            }
        }else{
            lessons.add(new Lesson(-1, "You have no classes", "", "", "", classDays, "Black"));
        }
        cursor.close();
        return lessons;
    }
}
