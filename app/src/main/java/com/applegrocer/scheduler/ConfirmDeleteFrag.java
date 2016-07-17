package com.applegrocer.scheduler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

/**
 * Created by AppleGrocer on 12/26/2015.
 */
public class ConfirmDeleteFrag extends AppCompatDialogFragment{

    ConfirmDeleteFrag.ConfirmDeleteFragListener mListener;

    /**
     * Inner interface to call delete method in parent Activity
     */
    public interface ConfirmDeleteFragListener{
        void delete();
    }

    public ConfirmDeleteFrag(){
        // Required empty constructor
    }

    /**
     * Ensure parent activity implements required interface
     * @param activity
     */
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            mListener=(ConfirmDeleteFragListener) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+" must implement " +
                    "ConfirmDeleteFragListener interface");
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
                ((ConfirmDeleteFragListener)getActivity()).delete();
            }
        });

        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }

}
