package com.applegrocer.scheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class EditClassActivity extends AppCompatActivity implements
        View.OnClickListener, TimePickerFrag.TimePickerFragListener,
        ConfirmationFrag.ConfirmationFragListener, ConfirmDeleteFrag.ConfirmDeleteFragListener{

    private long uID;
    private String editClassName;
    private String editClassLocation;
    private String initStartTime;
    private String initEndTime;
    private boolean[] editMeetDays;

    EditText editName;
    EditText editLocation;
    TextView editStartTime;
    TextView editEndTime;
    CheckBox[] checkArray;

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);
        Intent intent=getIntent();

        //instantiate database
        db=DBHelper.getInstance(this);

        //Set toolbar
        Toolbar toolbar=(Toolbar) findViewById(R.id.add_classes_toolbar);
        setSupportActionBar(toolbar);

        //set fields from intent extras
        uID=intent.getLongExtra(ClassRecyclerAdapter.CLASS_UID_EXTRA, -1);
        editClassName=intent.getStringExtra(ClassRecyclerAdapter.CLASS_NAME_EXTRA);
        editClassLocation=intent.getStringExtra(ClassRecyclerAdapter.CLASS_LOCATION_EXTRA);
        initStartTime=intent.getStringExtra(ClassRecyclerAdapter.CLASS_START_TIME_EXTRA);
        initEndTime=intent.getStringExtra(ClassRecyclerAdapter.CLASS_END_TIME_EXTRA);
        editMeetDays=intent.getBooleanArrayExtra(ClassRecyclerAdapter.CLASS_MEET_DAYS_EXTRA);

        //set starting values for EditText views
        editName=(EditText) findViewById(R.id.editClassName);
        editName.setText(editClassName);

        editLocation=(EditText) findViewById(R.id.editClassLocation);
        editLocation.setText(editClassLocation);

        editStartTime=(TextView) findViewById(R.id.editStartTime);
        editStartTime.setText(initStartTime);

        editEndTime=(TextView) findViewById(R.id.editEndTime);
        editEndTime.setText(initEndTime);

        //create and populate checkbox array for class days
        checkArray=new CheckBox[]{
                (CheckBox) findViewById(R.id.sunEditCheck),
                (CheckBox) findViewById(R.id.monEditCheck),
                (CheckBox) findViewById(R.id.tuesEditCheck),
                (CheckBox) findViewById(R.id.wedEditCheck),
                (CheckBox) findViewById(R.id.thursEditCheck),
                (CheckBox) findViewById(R.id.friEditCheck),
                (CheckBox) findViewById(R.id.satEditCheck)};

        //set starting value for checkboxes
        for(int i=0; i<checkArray.length; i++){
            if(editMeetDays[i]){
                checkArray[i].setChecked(true);
            }
        }

        //set listeners for save and cancel buttons
        Button saveChangesButton=(Button) findViewById(R.id.saveChangesButton);
        saveChangesButton.setOnClickListener(this);
        Button cancelChangesButton=(Button) findViewById(R.id.cancelChangesButton);
        cancelChangesButton.setOnClickListener(this);

        //set listeners for TimePickerFrag
        editStartTime.setOnClickListener(this);
        editEndTime.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_edit_class, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.removeClass:
                AppCompatDialogFragment confirmDelete=new ConfirmDeleteFrag();
                confirmDelete.show(getSupportFragmentManager(), "deleteClass");
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.editStartTime:
            case R.id.editEndTime:
                showTimePicker(v);
                break;
            case R.id.saveChangesButton:
                showSaveChangesDialog();
                break;
            case R.id.cancelChangesButton:
                this.finish();
                break;
        }
    }

    public void showTimePicker(View view){
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

    private void showSaveChangesDialog(){
            AppCompatDialogFragment frag=new ConfirmationFrag();
            frag.show(getSupportFragmentManager(), "confirmationFrag");
    }

    @Override
    public void save(){

        boolean[] editCheckboxArr= new boolean[7];

        for(int i=0; i<editCheckboxArr.length; i++){
            if(checkArray[i].isChecked()){
                editCheckboxArr[i]=true;
            }
        }

        db.updateClass(uID, editName.getText().toString(), editLocation.getText().toString(),
                editStartTime.getText().toString(), editEndTime.getText().toString(),
                editCheckboxArr, this);

        this.finish();
    }

    public void delete(){
        db.removeClass(uID, this);
        this.finish();
    }
}

