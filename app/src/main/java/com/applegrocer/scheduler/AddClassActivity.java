package com.applegrocer.scheduler;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Screen where user describes and inputs a new class
 */
public class AddClassActivity extends AppCompatActivity
        implements TimePickerFrag.TimePickerFragListener,
        ConfirmationFrag.ConfirmationFragListener, View.OnClickListener {

    private DBHelper database;

    private Spinner spinner;

    private String[]labels={"Black", "Blue", "Green", "Orange", "Purple", "Red", "Teal", "Yellow"};

    private int[] icons=
            {R.color.black,
            R.color.blue,
            R.color.green,
            R.color.orange,
            R.color.purple,
            R.color.red,
            R.color.teal,
            R.color.yellow};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        //Set toolbar
        Toolbar toolbar=(Toolbar) findViewById(R.id.add_classes_toolbar);
        setSupportActionBar(toolbar);

        // set spinner
        spinner=(Spinner) findViewById(R.id.chalkSpinner);
        spinner.setAdapter(new CustomSpinnerAdapter(this, R.layout.custom_spinner,
                labels, icons));

        //set button listeners
        Button saveButton=(Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        Button cancelButton=(Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(this);

        //set timePicker listeners
        TextView startTime=(TextView) findViewById(R.id.startTime);
        startTime.setOnClickListener(this);
        TextView endTime=(TextView) findViewById(R.id.endTime);
        endTime.setOnClickListener(this);

        //create database object
        database= DBHelper.getInstance(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.startTime:
            case R.id.endTime:
                showTimePicker(view);
                break;
            case R.id.saveButton:
                showSaveDialog();
                break;
            case R.id.cancelButton:
                cancel();
                break;
        }
    }

    private void showTimePicker(View view){
        AppCompatDialogFragment frag=new TimePickerFrag();
        TextView inView= (TextView) view;

        //Pass "time" information to fragment
        Bundle bundle=new Bundle();
        bundle.putCharSequence("time", inView.getText());
        bundle.putInt("viewId", inView.getId());
        frag.setArguments(bundle);

        frag.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void changeTextTime(int hour, int minute, int viewId) {
        TextView textView=(TextView) findViewById(viewId);
        ClockHelper clockHelper = new ClockHelper(hour, minute);
        textView.setText(clockHelper.convertTextTime());
    }

    private void showSaveDialog(){
        AppCompatDialogFragment frag=new ConfirmationFrag();
        frag.show(getSupportFragmentManager(), "confirmationFrag");
    }

    public void save(){
        String className;
        String classLocation;
        String startTime;
        String endTime;
        boolean[] meetDays=new boolean[7];
        String classColor;

        className=((EditText) findViewById(R.id.classNameIn)).getText().toString();
        classLocation=((EditText) findViewById(R.id.classLocation)).getText().toString();
        startTime=((TextView) findViewById(R.id.startTime)).getText().toString();
        endTime=((TextView) findViewById(R.id.endTime)).getText().toString();

        //create and populate checkbox array for class days
        CheckBox[] checkArray=new CheckBox[]{
        (CheckBox) findViewById(R.id.sunCheck),
        (CheckBox) findViewById(R.id.monCheck),
        (CheckBox) findViewById(R.id.tuesCheck),
        (CheckBox) findViewById(R.id.wedCheck),
        (CheckBox) findViewById(R.id.thursCheck),
        (CheckBox) findViewById(R.id.friCheck),
        (CheckBox) findViewById(R.id.satCheck)};

        //create and populate boolean array to store when class meets
        int boxTrav=0;
        for(int i=0; i<meetDays.length; i++){
            meetDays[i]=checkArray[boxTrav].isChecked();
            boxTrav++;
        }

        classColor=spinner.getSelectedItem().toString();

        database.insertClass(className, classLocation, startTime, endTime, meetDays,
                classColor, this);
        this.finish();
    }

    public void cancel(){
        this.finish();
    }
}
