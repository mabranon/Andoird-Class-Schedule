package com.applegrocer.scheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by AppleGrocer on 12/29/2015.
 */
public class CustomSpinnerAdapter extends ArrayAdapter<String>{

    private String[] labels;
    private int[] icons;

    public CustomSpinnerAdapter(Context context,int textViewResourceId, String[] labels,
                                int[] icons){
        super(context, textViewResourceId, labels);

        //copy labels array
        this.labels=new String[labels.length];
        for(int i=0; i<labels.length; i++){
            this.labels[i]=labels[i];
        }

        //copy icons array
        this.icons=new int[icons.length];
        for(int i=0; i<icons.length; i++){
            this.icons[i]=icons[i];
        }
    }

    @Override
    public View getDropDownView(int position, View cnvtView, ViewGroup parent){
        return getCustomView(position, cnvtView, parent);
    }

    @Override
    public View getView(int pos, View cnvtView, ViewGroup prnt) {
        return getCustomView(pos, cnvtView, prnt);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent){
        View mySpinner=LayoutInflater.from(parent.getContext()).
                inflate(R.layout.custom_spinner, parent, false);

        TextView label=(TextView) mySpinner.findViewById(R.id.chalkColorLabel);
        label.setText(labels[position]);

        ImageView icon=(ImageView) mySpinner.findViewById(R.id.chalkColorIcon);
        icon.setImageResource(icons[position]);

        return mySpinner;
    }
}
