package com.example.bae.ui.include.menu.menu_navigation;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.bae.Interface.replaceFragement;
import com.example.bae.R;
import com.example.bae.data.SharedPreferences.DataLocalManager;
import com.example.bae.data.User.UserData;
import com.example.bae.ui.Login_SignUp.LoginActivity;
import com.example.bae.ui.Login_SignUp.SignUpActivity;
import com.example.bae.ui.Cart.CartFragment;
import com.example.bae.ui.include.menu.MenuCustome;
import com.google.android.material.navigation.NavigationView;

public class Navigation extends MenuCustome implements replaceFragement {
    private NavigationView navigationView ;
    private DrawerLayout drawerLayout ;


    TextView nav_header_t_fullname , nav_view_t_header ;

    public Navigation(FragmentManager fragmentManager , Activity activity , UserData user , DrawerLayout drawerLayout){
        super(fragmentManager , user , activity);
        this.activity = activity ;

        this.navigationView =  activity.findViewById(R.id.nav_view) ;
        this.drawerLayout = drawerLayout;


        View headerView = navigationView.getHeaderView(0) ;
        nav_header_t_fullname = headerView.findViewById(R.id.nav_header_fullname);
        nav_view_t_header = headerView.findViewById(R.id.nav_header_email);

    }



    public void createView(){
        navigationView.bringToFront();

        Menu menu = navigationView.getMenu() ;
        if(user != null){
            nav_header_t_fullname.setText(user.getName());
            nav_view_t_header.setText(user.getEmail());

            menu.add(R.id.group_login_and_sign_up , 1 , 0 , "Logout");
        }
        else {
            menu.add(R.id.group_login_and_sign_up , 2 , 0 , "Login");
            menu.add(R.id.group_login_and_sign_up , 3 , 0 , "Sign up");

        }
    }



    @Override
    public void action() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int i = item.getItemId() ;
                if(i == R.id.nav_settings){
                    replaceFragement(new CartFragment() ,fragmentManager);
                }
                else if (i == 1) {

                    DataLocalManager.removeUser();
                    activity.finish();
                    activity.startActivity(new Intent(context, LoginActivity.class));
                }
                else if (i == 2) {
                    activity.finish();
                    activity.startActivity(new Intent(context , LoginActivity.class));
                }
                else if (i == 3) {
                    activity.finish();
                    activity.startActivity(new Intent(context , SignUpActivity.class));
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }


}
