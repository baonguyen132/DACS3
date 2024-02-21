package com.example.bae.ui.include.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.bae.R;

public class LoadingDialog {
    private Activity activity ;
    private AlertDialog dialog ;
    public LoadingDialog(Activity activity){
        this.activity = activity ;
    }

   public void startAlerDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.laout_dialog_loading , null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void dismissDialog(){
        dialog.dismiss();
    }
}
