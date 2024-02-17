package com.example.bae;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.example.bae.Interface.getUserAuth;
import com.example.bae.Interface.replaceFragement;

import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.ui.home.HomeFragment;

import com.example.bae.ui.include.menu.MenuCustome;
import com.example.bae.ui.include.menu.menu_bottom.MenuBottom;
import com.example.bae.ui.include.menu.menu_navigation.Navigation;

import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements replaceFragement, getUserAuth {



    private MenuCustome navigation, menuBottom ;
    private FirebaseUser user ;
    private DrawerLayout drawerLayout ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!DataLocalManager.getFirstInstalled()){
            Toast.makeText(getApplicationContext() , "1" , Toast.LENGTH_LONG).show();
            DataLocalManager.setFirstInstalled(true);
        }
        else {
            Toast.makeText(getApplicationContext() , "2" , Toast.LENGTH_LONG).show();
        }

        //==== Thiết lập biến ====//
        this.user = getUserAuth() ;
        this.drawerLayout = findViewById(R.id.drawer_layout) ;
        //==== Tạo ====//
        navigation = new Navigation(getSupportFragmentManager() , this  , user , drawerLayout) ;
        menuBottom = new MenuBottom(getSupportFragmentManager(), this , user );


        Toolbar toolbar = findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , drawerLayout ,toolbar , R.string.open_nav , R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        replaceFragement(new HomeFragment() , getSupportFragmentManager());

        navigation.createView();
        navigation.action();

        menuBottom.createView();
        menuBottom.action();

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }
}