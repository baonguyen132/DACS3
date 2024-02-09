package com.example.bae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.bae.Interface.replaceFragement;
import com.example.bae.ui.Login_SignUp.SignUpActivity;
import com.example.bae.ui.Sub.SubFragment;
import com.example.bae.ui.home.HomeFragment;
import com.example.bae.ui.Login_SignUp.LoginActivity;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity  implements replaceFragement  {


    DrawerLayout drawerLayout ;

    NavigationView navigationView ;
    Menu menu ;

    FirebaseAuth firebaseAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance() ;

        FirebaseUser user = firebaseAuth.getCurrentUser() ;


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView= findViewById(R.id.nav_view) ;
        menu = navigationView.getMenu() ;

        if(user != null){
            Toast.makeText(getApplicationContext() , user.getEmail().toString() , Toast.LENGTH_LONG).show();
            menu.add(R.id.group_login_and_sign_up , 1 , 0 , "Logout");
        }
        else {
            menu.add(R.id.group_login_and_sign_up , 2 , 0 , "Login");
            menu.add(R.id.group_login_and_sign_up , 3 , 0 , "Sign up");

        }


        navigationView.bringToFront();

        Toolbar toolbar = findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , drawerLayout ,toolbar , R.string.open_nav , R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            replaceFragement(new HomeFragment() , getSupportFragmentManager());
            navigationView.setCheckedItem(R.id.nav_home);
        }
        replaceFragement(new HomeFragment() , getSupportFragmentManager());



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId() ;
                if(i == R.id.nav_settings){
                    replaceFragement(new SubFragment() , getSupportFragmentManager());
                }
                else if (i == 1) {
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    startActivity(new Intent(getApplicationContext() , LoginActivity.class));
                }
                else if (i == 2) {
                    finish();
                    startActivity(new Intent(getApplicationContext() , LoginActivity.class));
                }
                else if (i == 3) {
                    finish();
                    startActivity(new Intent(getApplicationContext() , SignUpActivity.class));
                }
                Log.d("ssss" , (i + "-" + item));
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

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