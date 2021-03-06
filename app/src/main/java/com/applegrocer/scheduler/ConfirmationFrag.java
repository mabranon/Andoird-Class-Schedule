package com.applegrocer.scheduler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class ConfirmationFrag extends AppCompatDialogFragment{

    ConfirmationFragListener mListener;

    /**
     * Inner interface to call save method (avoids passing data to frag)
     */
    public interface ConfirmationFragListener{
        void save();
    }
    public ConfirmationFrag() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            mListener=(ConfirmationFragListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+" must implement " +
                    "ConfirmationFragListener interface");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       //build alert dialog
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.conf);
        //create buttons for dialog frag
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((ConfirmationFragListener)getActivity()).save();
            }
        });

        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }

}
