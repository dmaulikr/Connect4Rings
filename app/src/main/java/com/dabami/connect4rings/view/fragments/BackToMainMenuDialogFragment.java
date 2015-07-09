package com.dabami.connect4rings.view.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import dabami.connect4rings.R;

public class BackToMainMenuDialogFragment extends DialogFragment {

    public interface BackToMainMenuDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
    }

    BackToMainMenuDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (BackToMainMenuDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.back_main_menu_title)
                .setMessage(R.string.back_main_menu_message)
                .setPositiveButton(R.string.back_main_menu_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onDialogPositiveClick(BackToMainMenuDialogFragment.this);
                    }
                })
                .setNegativeButton(R.string.back_main_menu_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
        return builder.create();
    }
}
