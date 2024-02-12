package com.example.bae.ui.include.menu.menu_bottom;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.bae.Interface.replaceFragement;
import com.example.bae.R;
import com.example.bae.ui.Login_SignUp.LoginActivity;
import com.example.bae.ui.Sub.SubFragment;
import com.example.bae.ui.home.HomeFragment;
import com.example.bae.ui.include.menu.MenuCustome;
import com.example.bae.ui.shorts.shortsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseUser;

public class MenuBottom extends MenuCustome implements replaceFragement {

    private FloatingActionButton fab ;
    private BottomNavigationView bottomNavigationView ;


    public MenuBottom (FragmentManager fragmentManager , Activity activity , FirebaseUser user ){
        super(fragmentManager , user, activity);
        this.bottomNavigationView = activity.findViewById(R.id.bottomNavigationView);
        this.fab = activity.findViewById(R.id.fab); ;
    }

    @Override
    public void createView() {
        bottomNavigationView.setBackground(null);
    }

    public void action(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId() ;
                if(id == R.id.home){
                    replaceFragement(new HomeFragment() , fragmentManager);
                }
                else if(id == R.id.shorts){
                    replaceFragement(new shortsFragment() , fragmentManager);
                }
                else if(id == R.id.subscriptions){
                    replaceFragement(new SubFragment() , fragmentManager);
                }


                return true;
            }
        });
    }

    protected void showBottomDialog() {

            if(user != null){
                final Dialog dialog = new Dialog(bottomNavigationView.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.bottomsheetlayout);

                ListView listView = dialog.findViewById(R.id.listview);
                ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
            else {
                Toast.makeText(context , "Bạn cần đăng nhập" , Toast.LENGTH_LONG).show();
            }

        }




}
