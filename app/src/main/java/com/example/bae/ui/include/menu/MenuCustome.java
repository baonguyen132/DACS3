package com.example.bae.ui.include.menu;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseUser;

public abstract class MenuCustome {
    protected FragmentManager fragmentManager ;
    protected Context context ;
    protected FirebaseUser user ;
    protected Activity activity ;
    public MenuCustome( FragmentManager fragmentManager , FirebaseUser user , Activity activity ){
        this.fragmentManager = fragmentManager ;
        this.context = activity.getApplicationContext() ;
        this.user = user ;
    }

    public abstract void createView();
    public abstract void action() ;
}
