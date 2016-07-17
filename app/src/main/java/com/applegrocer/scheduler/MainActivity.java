package com.applegrocer.scheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set toolbar
        Toolbar toolbar=(Toolbar) findViewById(R.id.menu_toolbar);
        setSupportActionBar(toolbar);

        //set listeners for buttons
        Button plannerButton=(Button) findViewById(R.id.plannerButton);
        plannerButton.setOnClickListener(this);
        Button classButton=(Button) findViewById(R.id.classesButton);
        classButton.setOnClickListener(this);
        Button mapButton=(Button) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item){
       switch(item.getItemId()) {
           case R.id.settings:
               return true;
           default:
               return super.onOptionsItemSelected(item);
       }
   }

    @Override
    public void onClick(View button){
        //button switch
        switch(button.getId()){
            case R.id.plannerButton:
                break;
            case R.id.classesButton:
                Intent intent=new Intent(this, ClassesActivity.class);
                startActivity(intent);
            case R.id.mapButton:
                break;
        }
    }

}

